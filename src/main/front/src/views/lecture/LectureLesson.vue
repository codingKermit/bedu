<template>
    <b-container class="pt-5">
        <div class="mx-auto">
            <!-- 동영상 목록 콜랩스 -->
                <b-container>
                    <span type="button" data-bs-toggle="offcanvas" data-bs-target="#lesson-list" aria-controls="lesson-list">
                        <font-awesome-icon class="fs-1" :icon="['fas', 'bars']"/>
                    </span>
                <div class="offcanvas offcanvas-start " tabindex="-1" id="lesson-list" aria-labelledby="lesson-list-Label">
                    <div class="offcanvas-header">
                        <p class="offcanvas-title fs-3 fw-bold text-truncate d-inline-block" id="lesson-list-Label">{{ lessonInfo.lectDtlTitle }}</p>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <div class="dropdown mt-3">
                            <ul class="list-unstyled">
                                <li v-for="(item, index ) in lessonList" :key="index" class="mb-3 ms-3">
                                    <b-link class="text-body text-decoration-none fs-6 d-flex" 
                                    :to="'/lectureLesson?lectDtlNum='+item.lectDtlNum">
                                        <p class="me-auto">
                                            {{ item.lectDtlIndex }}.{{ item.lectDtlTitle }}
                                        </p>
                                        <p class="ms-auto">{{ item.times }}</p>
                                    </b-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
               </b-container>
            <!-- 동영상 재생 컨테이너 -->
            <b-container class="mb-5">
                <p class="fs-2 fw-bold text-center">{{ lessonInfo.lectDtlTitle }}</p>
                <iframe width="100%" height="650px" src="https://www.youtube.com/embed/wk8jsBXTG_A" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
            </b-container>
            
            <!-- 하단 네비 -->
            <b-container class="text-light" v-if="lessonList.length">
                <div class="row">
                <div class="col"></div>
                <div class="col d-flex m-auto">
                    <div v-if="lessonInfo.lectDtlIndex > 1" class="w-100">
                        <b-button class="rounded-5 p-3 bedu-custom-blue w-100"
                        :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex-2].lectDtlNum">
                            <font-awesome-icon class="px-2" :icon="['fas', 'left-long']" />
                            <span>{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlTitle }}</span>
                        </b-button>
                    </div>
                    
                    <div v-if="lessonInfo.lectDtlIndex < lessonList.length" class="w-100">
                        <b-button class="rounded-5 p-3 ms-3 bedu-custom-blue w-100"
                        :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex].lectDtlNum">
                            <span>{{ lessonList[lessonInfo.lectDtlIndex].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex].lectDtlTitle }}</span>
                            <font-awesome-icon class="px-2" :icon="['fas', 'right-long']" />
                        </b-button>
                    </div>
                    <div class="position-absolute">
                    </div>
                </div>
                <div class="col text-end">
                    <b-button class="bedu-custom-yellow border rounded-5 p-3">
                        수강 완료
                    </b-button>
                </div>
            </div>
            </b-container>
        </div>
        <div class="ratio ratio-16x9">
           <iframe :src="lessonInfo.lessonUrl" title="YouTube video" allowfullscreen></iframe>
        </div>
        {{ lessonInfo.lessonUrl }}
    </b-container>
</template>

<script>
import '@/assets/css/lectureStyle.css'


export default{
    name : 'lectureLesson',
    data() {
        return {
            lessonInfo : {
                lectDtlIndex : 0,
                lectDtlNum : 0,
                lectDtlTitle : '',
                lectNum : 0,
                lessonUrl : 'file://172.30.1.7/%EA%B3%B5%EC%9C%A0%ED%8F%B4%EB%8D%94/BEDU/pexels-peter-fowler-1093667-1920x1080-30fps.mp4',
            },
            isAvailable : '',
            lessonList : [],
            lectInfo : {
                lectNum : 0,
                lectCateCode : '',
                lectCateKor : '',
            },
        }
    },
    methods: {
        /** 강의 정보 및 수강 가능 여부 확인 */
        getLesson(){
            this.$axiosSend('get','/api/lect/getLesson',{
                num : this.lessonInfo.lectDtlNum
            })
            .then((res)=>{
                this.lessonInfo = res.data.lessonItem
                this.lessonList = res.data.lessonList
                this.lessonList.indexOf()
            })
            .catch((err)=>{
                console.log(err)
            })
        }
        
    },
    mounted() {
        this.getLesson();
    },
    created() {
        this.lessonInfo.lectDtlNum = this.$route.query.lectDtlNum;
    },
    watch:{
        '$route.query.lectDtlNum':{
            immediate: true,
            handler(newNum){
                this.lessonInfo.lectDtlNum = newNum;
                this.getLesson();
            }
        }
    }
}

</script>