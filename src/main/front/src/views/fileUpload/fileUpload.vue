<template>
    <div>
        <b-container class="py-5">
            <!-- 상단 검색 부분 -->
            <div class="py-5">
                <b-form class="mx-auto d-flex" @submit="getLectureList">
                    <div class="w-75">
                        <b-form-input 
                            v-model="keyword" 
                            class="w-75 m-auto form-control-lg" 
                            type="search"
                        ></b-form-input>
                    </div>
                    <div class="w-25">
                        <b-button class="fs-5 px-5" type="submit">검색</b-button>
                    </div>
                </b-form>
            </div>
            
            <!-- 메인 컨테이너-->
            <b-container class="d-flex">
                <!-- 좌측 검색된 강의 목록 컨테이너-->
                <div class="w-50">
                    <div class="p-4">
                        <b-container class="border rounded-4 py-5 vh-100">
                            <ul class="list-unstyled">
                                <li v-for="(item, index) in lists" :key="index">
                                    {{ item }}
                                </li>
                            </ul>
                        </b-container>
                    </div>
                </div>

                <!-- 우측 폼 컨테이너-->
                <div class="w-50">
                    <b-container class="py-4">
                        <b-form>

                            <!-- 제목 입력 -->
                            <b-form-group
                            label="동영상 제목"
                            label-for="video-title"
                            description="동영상의 제목을 입력해주세요"
                            class="mb-5"
                            >
                                <b-form-input id="video-title"
                                    v-model="input.videoTitle"
                                    required :state="state" trim
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
                                    v-model="input.videoTime"
                                    required
                                    :state="state"
                                    trim
                                    type="number"
                                ></b-form-input>
                            </b-form-group>

                            <!-- 동영상 업로드 -->
                            <div class="mb-3">
                                <label for="video-file" class="form-label"></label>
                                <input class="form-control" type="file" id="video-file" accept="video/mp4,video/mkv, video/x-m4v,video/*">
                            </div>
                        </b-form>
                    </b-container>
                </div>
            </b-container>
        </b-container>
    </div>
</template>


<script>
export default{
    name : 'fileUpload',
    data() {
        return {
            keyword : '',
            input: {
                lectNum : 0,
                videoTitle: '',
                videoTime: 0,
                videoFile : null,
            },
            lists:[],
        }
    },
    methods: {
        getLectureList(){
            this.$axiosSend('post','/api/file/getLectureList',{
                keyword : this.keyword
            })
            .then((res)=>{
                console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })
        }
    },
}
</script>
