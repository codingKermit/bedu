<template>
    <div>
        <b-container class="py-5">
            <!-- 상단 검색 부분 -->
            <div class="py-5">
                <p class="text-center fs-4 fw-bold">강의를 조회하세요</p>
                <b-form class="d-flex" @submit="getLectureList">
                    <div class="d-flex m-auto w-75">
                        <div class="me-4 w-75">
                            <b-form-input 
                            v-model="keyword" 
                            class="w-100 m-auto form-control-lg py-1"
                            type="search"
                            ></b-form-input>
                        </div>
                        <div class="w-25">
                            <b-button class="fs-5 px-5  py-2 bedu-submit-button-lg w-100" type="submit">검색</b-button>
                        </div>
                    </div>
                </b-form>
            </div>
            
            <!-- 메인 컨테이너-->
            <b-container class="d-flex">
                <!-- 좌측 검색된 강의 목록 컨테이너-->
                <div class="w-50">
                    <div class="p-4">
                        <b-container class="border rounded-4 pt-3 pb-5 vh-100 file-search-list">
                            <b-form-group
                                class="fs-4 text-center"
                            >
                            <p class="text-center fs-3 mt-2 mb-4">조회된 강의 목록</p>
                                <ul class="list-unstyled ">
                                    <li v-for="(item, index) in lists" :key="index" class="mb-3 fs-5">
                                        <div class="border rounded-3 p-3 d-flex">
                                            <div class="align-self-center me-3">
                                                <b-form-radio 
                                                v-model="form.lectNum" 
                                                name="lect-num" 
                                                :value="item.lectNum"
                                                ></b-form-radio>
                                            </div>
                                            <div class="me-5 text-start">
                                                <p>강의 번호 : <span class="fw-bold">{{ item.lectNum }}</span></p>
                                                <p>강의 제목 : <span class="fw-bold">{{ item.title }}</span></p>
                                                <p>강사 : <span class="fw-bold">{{ item.teacher }}</span></p>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </b-form-group>
                        </b-container>
                    </div>
                </div>

                <!-- 우측 폼 컨테이너-->
                <div class="w-50">
                    <b-container class="py-4">
                        <b-form @submit.prevent="uploadVideo">
                            <!-- 제목 입력 -->
                            <b-form-group
                            label="동영상 제목"
                            label-for="video-title"
                            description="동영상의 제목을 입력해주세요"
                            class="mb-5"
                            >
                                <b-form-input id="video-title"
                                    v-model="form.lectDtlTitle"
                                    required :state="state" trim
                                    class="form-control-lg"
                                ></b-form-input>
                            </b-form-group>


                            <!-- 재생시간 입력 -->
                            <b-form-group
                            label="동영상 재생 시간"
                            label-for="video-time"
                            description="동영상의 재생시간을 입력해주세요"
                            >
                                <b-form-input
                                    id="video-time"
                                    v-model="form.lectDtlTime"
                                    required
                                    :state="state"
                                    trim
                                    type="number"
                                    class="form-control-lg"
                                ></b-form-input>
                            </b-form-group>

                            <!-- 동영상 업로드 -->
                            <div class="mb-5">
                                <label for="video-file" class="form-label"></label>
                                <input 
                                    class="form-control-lg form-control" 
                                    type="file" 
                                    id="video-file" 
                                    accept="video/mp4,video/mkv, video/x-m4v,video/*"
                                    @change="fileChange"
                                    >
                            </div>
                            <b-button 
                                class="w-100 py-3 bedu-submit-button-lg fs-5"
                                type="submit"
                            >
                                등록하기
                            </b-button>
                        </b-form>
                    </b-container>
                </div>
            </b-container>
        </b-container>
    </div>
</template>


<script>
import axios from 'axios'

export default{
    name : 'fileUpload',
    data() {
        return {
            keyword : '',
            form: {
                lectNum : 0,
                lectDtlTitle: '',
                lectDtlTime: 0,
            },
            videoFile : null,
            lists:[],
        }
    },
    created(){
        this.getTotalLecture();
    },
    methods: {
        /** 입력된 검색어가 포함된 제목을 가진 강의 목록 반환 무한스크롤X */
        getLectureList(){
            this.$axiosSend('post','/api/file/getLectureList',{
                keyword : this.keyword
            })
            .then((res)=>{
                this.lists = res.data.item
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        /** 파일 변경, 업로드시 데이터 바인딩을 위한 메서드 */
        fileChange(e){
            this.videoFile = e.target.files[0];
        },
        uploadVideo(){
        
            // 데이터 공백 체크
            if(this.form.lectNum == 0){
                this.$swal({
                    title : '경고!',
                    icon : 'error',
                    text : '강의를 선택해주세요'
                })
                return;
            }
            if(this.form.videoTime<=0){
                this.$swal({
                    title: '경고!',
                    icon : 'error',
                    text : '동영상 재생 시간을 확인해주세요'
                })
                return;
            }
            if(this.videoFile == null){
                this.$swal({
                    title: '경고!',
                    icon : 'error',
                    text : '동영상 파일을 확인해주세요'
                })
                return;
            }
            // 공백 체크 종료

            const fileSize =  this.videoFile.size // 업로드하는 파일 크기
            
            const chunkSize = 1024*1024; // 1mb 
            
            const totalChunk = Math.floor(fileSize/chunkSize)+1; // 청크 갯수

            let currentChunk = 0; // 현재 청크 번호


            /** 업로드된 파일을 10mb로 나누어 업로드 하는 재귀함수 */
            const fileUpload = () => {

                const begin = currentChunk * chunkSize;
                const end = Math.min(begin+chunkSize, fileSize);

                const chunk = this.videoFile.slice(begin,end)

                const formData = new FormData();
                formData.append("lectNum",this.form.lectNum);
                formData.append("lectDtlTitle", this.form.lectDtlTitle);
                formData.append("lectDtlTime",this.form.lectDtlTime);
                formData.append("videoFile",chunk);
                formData.append("chunkNumber",currentChunk);
                formData.append("totalChunk",totalChunk);
                
                axios.post('/api/file/uploadFormAction',formData,{
                    headers:{
                        "Content-Type" : "multipart/form-data"
                    }
                })
                .then((res)=>{
                    console.log(res.status);
                    currentChunk++;
                    if(res.status == 206){ // 부분만 완료 되었으면 제귀함수 호출
                        fileUpload();
                    } else if (res.status == 200){ // 전체 완료시 종료, 이후 데이터 처리 코드는 추후 작성
                        return;
                    }
                })
                .catch((err)=>{
                    console.log(err)
                })
            }

            fileUpload(); // 제귀함수 호출



            // 공통유틸의 axiosSend()로는 헤더 적용이 되지 않는 문제가 발생하여 바닐라 axios를 사용
            // this.$axiosSend('post','/api/file/uploadFormAction',formData, this.headers)
            // .then((res)=>{
            //     console.log(res)
            // })
            // .catch((err)=>{
            //     console.log(err)
            // })
        },
        /** 모든 강의 목록을 조회하는 메서드 */
        getTotalLecture(){
            this.$axiosSend('get','/api/file/getTotalLecture')
            .then((res)=>{
                console.log(res);
                this.lists = res.data.item;
            })
            .catch((err)=>{
                console.log(err)
            })
        }

    },
}
</script>

<style scoped>
.file-search-list{
    overflow-y: scroll;
}

.bedu-submit-button-lg{
    background: var(--blue) !important;
}

</style>