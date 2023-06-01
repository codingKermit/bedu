<template>
<div class="lecture py-5">
    <img src="@/assets/imgs/character/im.png" class="character">
        <p class="fw-bold fs-1 text-center">어떤 강의를 찾고 있나요?</p>
        <p class="fs-5 fw-light fw-bold text-center" style="color: blueviolet;">What's wanna be?</p>
        <b-form class="w-50 mx-auto" @submit="lectSearch">
            <div
                class="border border-3 rounded-pill p-2 d-flex align-middle text-center w-75 m-auto mb-5 border-bedu">
                <font-awesome-icon class="m-auto mx-3" :icon="['fas', 'magnifying-glass']"/>
                <b-form-input class="border-0 me-2" v-model="keyword"></b-form-input>
            </div>
        </b-form>

        <b-container class="mb-5">
            <p class="fw-bold fs-4 text-start">분야별 강의</p>
            <categories></categories>
        </b-container>

        <!-- 신규 오픈 강좌 컨테이너 -->
        <b-container class="mb-5">
            <p class="fs-4 fw-bold">신규 오픈 강좌</p>
            <div>
                <b-row cols="4" class="justify-content-center">
                    <b-col v-for="(item, index) in newestLectures" :key="index">
                        <b-link
                            class="text-decoration-none text-body"
                            :to='"/lectureDetail?num="+item.lect_num'>
                            <b-container class="border">
                                <b-img :src='item.thumbnail' class="w-100 h-100 mb-3" thumbnail="thumbnail"></b-img>
                                <div class="ps-3">
                                    <p class="fs-5">{{ item.title }}</p>
                                    <p class="text-secondary fs-6">{{ item.korCategory }}</p>
                                    <div class="text-end mb-3">
                                        <span class="fw-bold">{{ item.price }}</span>
                                        원
                                    </div>
                                </div>
                            </b-container>
                        </b-link>
                    </b-col>
                </b-row>
            </div>
        </b-container>

        <!-- 금주의 인기 강좌 컨테이너 인기 기준이 없는 관계로 임시로 신규 강의와 동일한 데이터 출력 -->
        <b-container>
            <p class="fs-4 fw-bold">금주의 인기 강좌</p>
            <div>
                <b-row cols="4" class="justify-content-center">
                    <b-col v-for="(item, index) in newestLectures" :key="index">
                        <b-link
                            class="text-decoration-none text-body"
                            :to='"/lectureDetail?num="+item.lect_num'>
                            <b-container class="border">
                                <b-img :src='item.thumbnail' class="w-100 h-100 mb-3" thumbnail="thumbnail"></b-img>
                                <div class="ps-3">
                                    <p class="fs-5">{{ item.title }}</p>
                                    <p class="text-secondary fs-6">{{ item.korCategory }}</p>
                                    <div class="text-end mb-3">
                                        <span class="fw-bold">{{ item.price }}</span>
                                        원
                                    </div>
                                </div>
                            </b-container>
                        </b-link>
                    </b-col>
                </b-row>
            </div>
        </b-container>
    </div >
</template>

<script>
import Categories from '../components/Categories.vue'


export default{
  components: { Categories },
  name : 'lectureView',
  data() {
    return {
      keyword : '',
      newestLectures:[],
    }
  },
  methods: {
    test(){
      this.$axiosSend('get','/api/hello')
      .then((res)=>console.log(res.data))
      .catch((error)=>{error})
    },
    lectSearch(){
      this.$routerPush(
        'lectureSearch',
        {
          keyword : this.keyword
        },
        true
      )
    },
    getNewestLecture(){
      this.$axiosSend('get','/api/getNewestLecture')
      .then((res)=>{
        console.log(res);
        this.newestLectures = res.data.item;
      })
      .catch((err)=>{console.log(err)})
    }

  },
  computed: {
  },
  mounted() {
    this.getNewestLecture();
  },
  created() {
  
  },
}


</script>

<style scoped>

.border-bedu {
  border-color: var(--yellow) !important;
}

.fs-bedu-yellow{
  color : var(--blue) !important;
}

.character{
  position: absolute;
  top : 11%;
  left : -130px;
  height: 30%;
}


</style>