<template>
  <div class="lecture">
    <img src="@/assets/imgs/character/reverse_byul_book.png" class="character">
    <p class="fw-bold fs-1 text-center">어떤 강의를 찾고 있나요?</p>
    <p class="fs-5 fw-light fs-bedu-yellow text-center">What's wanna be?</p>
    <b-form class="w-50 mx-auto">
    <div class="border border-3 rounded-pill p-2 d-flex align-middle text-center w-75 m-auto mb-5 border-bedu">
        <font-awesome-icon class="m-auto mx-3" :icon="['fas', 'magnifying-glass']" />
        <b-form-input class="border-0 me-2"></b-form-input>
    </div>
    </b-form>

    <p class="fw-bold fs-4 text-start ms-5">분야별 강의</p>
    <b-container class="d-flex mb-5">
      <b-row cols="9" class="text-center">
        <b-col>
          <categories :imgPath="base.img" :title="base.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="data.img" :title="data.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="web.img" :title="web.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="lang.img" :title="lang.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="ai.img" :title="ai.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="programming.img" :title="programming.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="tools.img" :title="tools.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="major.img" :title="major.title"></categories>
        </b-col>
        <b-col>
          <categories :imgPath="design.img" :title="design.title"></categories>
        </b-col>
    </b-row>
    </b-container>

    <p class="fw-bold fs-4 text-start ms-5">분야별 강의</p>
  </div>
</template>

<script>
import Categories from '../components/Categories.vue'


export default{
  components: { Categories },
  name : 'lectureView',
  data() {
    return {
      base : {
        img : require('@/assets/imgs/categories/free-icon-base.png'),
        title : '기초 강의'
      },
      data : {
        img : require('@/assets/imgs/categories/free-icon-data.png'),
        title : '데이터 분석',
      },
      web : {
        img : require('@/assets/imgs/categories/free-icon-website-2463068.png'),
        title : '웹 개발',
      },
      lang : {
        img : require('@/assets/imgs/categories/free-icon-coding-language-2721593.png'),
        title : '프로그래밍 언어',
      },
      ai : {
        img : require('@/assets/imgs/categories/free-icon-ai-2814666.png'),
        title : '인공지능'
      },
      programming : {
        img : require('@/assets/imgs/categories/free-icon-programming.png'),
        title : '프로그래밍 교양'
      },
      tools: {
        img : require('@/assets/imgs/categories/free-icon-programming-tools.png'),
        title :'개발 도구',
      },
      major : {
        img : require('@/assets/imgs/categories/free-icon-graduation-2997279.png'),
        title : '프로그래밍 전공 지식',
      },
      design : {
        img : require('@/assets/imgs/categories/free-icon-design-1688390.png'),
        title : '디자인'
      },
    }
  },
  methods: {
    
  },
  mounted() {
    
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
  top : 120px;
  left : -170px;
  width: 350px;
}

</style>