<template>
    <div>
        <b-container class="py-5">
            <!-- category -->
            <div>
                <p class="text-secondary fs-5 ms-3">{{ form.korCategory }}</p>
            </div>

            <!-- 썸네일, 강의정보 컨테이너-->
            <div class="d-flex mb-4">
                <!-- thumbnail -->
                <div class="w-50 me-5">
                    <b-img thumbnail="thumbnail" rounded="5" :src="form.thumbnail" fluid="fluid"></b-img>
                </div>

                <!-- info start-->
                <div class="w-50">
                    <p class="fw-bold fs-3">{{ form.title }}</p>

                    <!-- score start-->
                    <div class="d-flex mb-3">
                        <div>
                            <span v-for="(star, i) in form.stars" :key="i" class="mx-1 fs-5">
                                <font-awesome-icon
                                    :class="star =='y' ? 'text-danger' : 'text-secondary'"
                                    :icon="star =='y' ? ['fas','star'] : ['fas','star']"/>
                            </span>
                        </div>
                        <div class="ms-2 pt-1 align-middle">
                            {{ form.score }}
                            점 (
                            {{ form.scoreUsers }}
                            개)
                        </div>
                    </div>
                    <!-- score end-->
                    <div class="list-unstyled align-middle w-100">
                        <div class="mb-2 row">
                            <span class="text-secondary col-2">강사</span>
                            <span class="text-body col">{{ form.teacher }}</span>
                        </div>
                        <div class="mb-2 row">
                            <span class="text-secondary col-2">수강기간</span>
                            <span class="text-body col">
                                구매일로부터
                                {{ form.lectPeriod }}일</span>
                        </div>
                        <div class="mb-2 row">
                            <span class="text-secondary col-2">학습시간</span>
                            <span class="text-body col">{{ form.totalTimes }}({{ form.total }}강)</span>
                        </div>
                    </div>

                </div>
                <!-- info end-->
            </div>

            <!-- 결제, 장바구니 컨테이너 -->
            <div class="bg-secondary bg-opacity-10 p-3 py-5 mb-5">
                <b-container class="d-flex">
                    <div class="w-50 d-flex pt-4">
                        <span class="fw-5 text-secondary ms-3 fs-6">강좌</span>
                        <span class="mx-auto fs-5">{{ form.title }}</span>
                    </div>
                    <div class="align-middle pt-3 ms-auto fw-bold me-4">
                        <span class="fs-2 text-danger">{{ form.price }}</span>
                        <span>원</span>
                    </div>
                    <div class="w-10">
                        <b-button class="d-block mb-1 w-auto px-5 py-2">
                            결제하기
                        </b-button>
                        <b-button class="px-5">
                            장바구니
                        </b-button>
                    </div>
                </b-container>
            </div>

            <!-- 소개, 목록, 후기 탭 -->
            <div class="form-contents-container">
                <ul class="nav nav-tabs nav-fill">
                    <li class="nav-item ">
                        <a class="nav-link py-4 text-body " href="#description-body"><span class="py-4">강좌소개</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-4 text-body" href="#videoList-container">강의목록</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link py-4 text-body" href="#lecture-review-container">수강후기</a>
                    </li>
                </ul>

                <!-- 강좌 소개 -->
                <b-container class="px-4">
                    <h2 class="fw-bold my-5" id="description-body">강좌 소개</h2>
                    <div class="w-100 overflow-visible">
                        <div v-html="form.lectDesc" class="w-100 "></div>
                    </div>

                    <!-- 강의 목록 -->
                    <h2 class="fw-bold my-5" id="videoList-container">강의목록</h2>
                    <ul class="list-unstyled mb-5">
                        <li
                            v-for="(video,i) in videos"
                            :key="i"
                            class="py-3 fs-5 border-bottom border-1  d-flex">
                            <span class="me-auto ms-3 fw-bold" @click="toLesson(video)">
                                {{video.lectDtlTitle}}
                            </span>
                            <span class="ms-auto me-3">
                                {{ video.times }}
                            </span>
                        </li>
                    </ul>

                    <!-- 수강 후기 -->
                    <div class="mb-5">
                        <div class="d-flex">
                            <p class="fs-2 fw-bold me-auto" id="lecture-review-container">수강후기</p>
                            <div>
                                <span v-for="(star, i) in form.stars" :key="i" class="mx-1 fs-5">
                                    <font-awesome-icon
                                        :class="star =='y' ? 'text-danger' : 'text-secondary'"
                                        :icon="star =='y' ? ['fas','star'] : ['fas','star']"/>
                                </span>
                            </div>
                            <div class="ms-2 pt-1 align-middle">
                                {{ form.score }}
                                점 (
                                {{ form.scoreUsers }}
                                개)
                            </div>
                        </div>
                        <div class="bg-secondary p-5 bg-opacity-10"></div>
                    </div>
                </b-container>
            </div>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: 'lectureDetail',
        data() {
            return {
                form: {
                    num: this.$route.query.num,
                    title: '',
                    score: 0,
                    scoreUsers: 0,
                    teacher: '',
                    duration: 0,
                    thumbnail: '',
                    view: 0,
                    regiDate: '',
                    price: 0,
                    description: '',
                    likeYn: '',
                    totalTimes: 0,
                    category: '',
                    stars: [],
                    videoLink:"https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                },
                videos: [],
                reviews: []
            }
        },
        methods: {
            toLesson(val){
                this.$routerPush('lectureLesson',{ lessonId : val.lectDtlNum},true);
            },
            getDetail() {
                this
                    .$axiosSend('get', '/api/lect/lectureDetail', {num: this.form.num})
                    .then((res) => {
                        console.log(res)
                        this.form = res.data;
                        this.form.stars = [];

                        // 별점 체크하기 위한 배열 생성
                        for (var i = 0; i < 5; i++) {
                            if (i < this.form.score) {
                                this
                                    .form
                                    .stars
                                    .push('y')
                            } else {
                                this
                                    .form
                                    .stars
                                    .push('n')
                            }
                        }
                    })
                    .catch((err) => {
                        console.log(err)
                    })
                },
            getVideoList() { /** 동영상 목록 조회 */
                this
                    .$axiosSend('get', '/api/lect/getVideoList', {num: this.form.num})
                    .then((res) => {
                        this.videos = res.data;
                    })
                    .catch((err) => {
                        console.log(err)
                    });
            },
            getReview(){ 
                this.$axiosSend('get','/api/lect/getReview',{num : this.form.num})
                .then((res)=>{console.log(res)})
                .catch((err)=>{console.log(err)})
            }
        },
        mounted() {
            this.getDetail();
            this.getVideoList();
        },
        created() {}
    }
</script>

<style scoped="scoped">

    b-img {
        width: 100%;
    }

    .form-contents-container {
        border-top: 3px solid black;
        border-left: 1px solid #e6e7e8;
        border-right: 1px solid #e6e7e8;
        border-bottom: 1px solid #e6e7e8;
    }

    div ::v-deep img {
        width: auto !important;
        height: auto !important;
        max-width: 100%;
    }
</style>