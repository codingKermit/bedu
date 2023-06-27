<template>
    <div>
        <!-- 상단 검색 부분 -->
        <div class="py-5">
                <p class="text-center fs-4 fw-bold">강의를 조회하세요</p>
                <div class="d-flex m-auto w-75">
                    <div class="me-4 w-75">
                        <b-form-input 
                        v-model="keyword" 
                        class="w-100 m-auto form-control-lg py-1"
                        type="search"
                        @keyup="getLectureList"
                        ></b-form-input>
                    </div>
                    <div class="w-25">
                        <b-button class="fs-5 px-5  py-2 bedu-bg-custom-blue w-100" type="submit">검색</b-button>
                    </div>
                </div>
            </div>
            
            <!-- 메인 컨테이너-->
            <b-container class="d-flex">
                <!-- 좌측 검색된 강의 목록 컨테이너-->
                <div class="w-50">
                    <div class="p-4">
                        <b-container class="border rounded-4 pt-3 pb-5 vh-100 scroll-y">
                            <b-form-group
                                class="fs-4 text-center"
                            >
                            <p class="text-center fs-3 mt-2 mb-4">조회된 강의 목록</p>
                                <ul class="list-unstyled ">
                                    <li v-for="(item, index) in lists" :key="index" class="mb-3 fs-5">
                                        <div v-if="item.total > 0" class="border rounded-3 p-3 d-flex">
                                            <div class="align-self-center me-3">
                                                <b-form-radio 
                                                v-model="form.lectNum" 
                                                name="lect-num" 
                                                :value="item.lectNum"
                                                @change="getVideoList"
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

                <!-- 우측 컨테이너-->
                <div class="w-50">
                    <!-- 재생목록 컨테이너 -->
                    <b-container class="pt-4">
                        <b-container class="border rounded-4 pt-3 vh-50 scroll-y">
                            <p class="text-center fs-3">재생목록</p>
                            <ul class="list-unstyled">
                                <li class="row">
                                    <div class="col-1"></div>
                                    <div class="col-1 fw-bold">No</div>
                                    <div class="col-2 text-center fw-bold">Index</div>
                                    <div class="col text-center fw-bold">Title</div>
                                </li>
                                <li v-for="(item, index) in videoList" :key="index" class="row mb-2">
                                    <div class="col-1">
                                        <b-form-radio v-model="form" :value="item" name="indexOptions" ></b-form-radio>
                                    </div>
                                    <div class="col-1">
                                        {{ item.lectDtlNum }}
                                    </div>
                                    <div class="col-2 text-center">
                                        {{ item.lectDtlIndex }}
                                    </div>
                                    <div class="col text-center">
                                        {{ item.lectDtlTitle }}
                                    </div>
                                </li>
                            </ul>
                        </b-container>
                    </b-container>
                    <!-- 폼 컨테이너 -->
                    <b-container class="py-4">
                        <b-form @submit.prevent="deleteVideo">
                            <!-- 제목 입력 -->
                            <b-form-group
                            label="동영상 제목"
                            label-for="video-title"
                            description="동영상의 제목을 입력해주세요"
                            class="mb-5"
                            disabled
                            v-model="form.lectDtlTitle"
                            >
                                <b-form-input id="video-title"
                                    v-model="form.lectDtlTitle"
                                    required :state="state" trim
                                    class="form-control-lg"
                                    :disabled="form.lectNum == 0"
                                ></b-form-input>
                            </b-form-group>

                            <b-form-group
                            label="동영상 재생 인덱스"
                            label-for="video-index"
                            description="동영상의 인덱스를 선택해주세요"
                            >
                                <b-form-select
                                v-model="form.lectDtlIndex"
                                required
                                :options="indexOptions"
                                :disabled="form.lectNum == 0"
                                ></b-form-select>
                            </b-form-group>

                            <b-button 
                                class="w-100 py-3 bg-danger fs-5"
                                type="submit"
                            >
                                삭제하기
                            </b-button>
                        </b-form>
                    </b-container>
                </div>
            </b-container>
        
    </div>
</template>


<script>
import axios from 'axios'

export default{
    name:'fileUp',
    data() {
        return {
            keyword : '',
            form: {
                lectNum : 0,
                lectDtlTitle: '',
                lectDtlTime: 0,
                lectDtlIndex : 0,
            },
            videoFile : null,
            lists:[],
            totalLists : [],
            progress : 0,
            videoList : [],
            indexOptions : [],
        }
    },
    methods: {
                /** 입력된 검색어가 포함된 제목을 가진 강의 목록 반환 무한스크롤X */
                getLectureList(){
            this.lists = this.totalLists.filter((item)=> item.title.includes(this.keyword))
        },
        /** 파일 변경, 업로드시 데이터 바인딩을 위한 메서드 */
        fileChange(e){
            this.videoFile = e.target.files[0];
            var video = document.createElement('video');
            video.preload = 'metadata';
            
            var self = this;

            video.onloadedmetadata = function() {
                self.form.lectDtlTime =  Math.round(video.duration);
            }

            if(this.videoFile != undefined){ 
                video.src = URL.createObjectURL(this.videoFile)
            }

        },
        deleteVideo(){
        
            // 데이터 공백 체크
            if(this.form.lectNum == 0){
                this.$swal({
                    icon : 'info',
                    text : '강의를 선택해주세요'
                })
                return;
            }
            if(this.form.videoTime<=0){
                this.$swal({
                    icon : 'info',
                    text : '동영상 재생 시간을 확인해주세요'
                })
                return;
            }
            if(this.videoFile == null){
                this.$swal({
                    icon : 'info',
                    text : '동영상 파일을 확인해주세요'
                })
                return;
            }
            // 공백 체크 종료

            this.$swal({
                title : '정말 삭제하시겠습니까?',
                icon : 'warning',
                text : '한번 삭제한 강의는 복구할 수 없습니다.',
                showCancelButton : true,
                confirmButtonText : '삭제',
                cancelButtonText : '취소',
            })
            .then((result)=>{
                if(result){
                    this.$axiosSend('get','/api/file/deleteFile',{num : this.form.lectDtlNum})
                    .then(()=>{
                        this.$swal({
                            title : 'Success',
                            icon : 'Success',
                            text : '강의가 삭제되었습니다'
                        })
                    })
                    .catch((err)=>{
                        console.log(err)
                    })
                }
            })


        },
        /** 모든 강의 목록을 조회하는 메서드 */
        getTotalLecture(){
            this.$axiosSend('get','/api/file/getTotalLecture')
            .then((res)=>{
                this.totalLists = res.data.item;
                this.lists = res.data.item;
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        /** 선택된 강의에 따른 동영상 조회 메서드 */
        getVideoList() { 
            this.$nextTick(()=>{
                this.$axiosSend('get', '/api/lect/getVideoList', {num: this.form.lectNum})
                .then((res) => {
                    if(this.$isNotEmptyArray(res.data)){
                        this.indexOptions = [];
                        this.videoList = res.data;
                        if(res.data.length){
                            for(var i =0; i < res.data.length; i++){
                                this.indexOptions.push(i+1)
                            }
                            this.indexOptions.push(this.indexOptions.length+1)
                        } else {
                            this.indexOptions.push(1);
                        }
                    } else {
                        this.$swal({
                            title : 'ERROR!',
                            icon : 'error',
                            text : '데이터를 불러오는데 에러가 발생했습니다.'
                        })
                        return;
                    }
                })
                .catch((err) => {
                    console.log(err)
                });
            })
            },
    },
    created(){
        this.getTotalLecture();
    },
}

</script>

<style scoped>
.scroll-y{
    overflow-y: scroll;
}

#file-upload-progress{
    transition: 0.1s;
}

.vh-50{
    height: 50vh !important;
}
</style>