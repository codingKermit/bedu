<template>
    <div class="main">
      <carousel>
        <div class="banner">
          <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
              <div class="carousel-item active">
                <img src="../assets/imgs/test.png" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item">
                <img src="../assets/imgs/test.png" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item">
                <img src="../assets/imgs/test.png" class="d-block w-100" alt="...">
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>
      </carousel>

      <div class="login">
        <form action="">
          <div>
            <label for="username"></label><input id="username" type="text" placeholder="아이디를 입력해주세요."/>
            <label for="password"></label><input id="password" type="password" placeholder="비밀번호를 입력해주세요."/>
            <button>로그인</button>
            <a>아이디 찾기/비밀번호 찾기</a>
          </div>
        </form>
      </div>

      <div class="quickLink">
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
      </div>

      <div class="content">
        <h2>베스트 컨텐츠 입니다.</h2>
      </div>

      <div class="notice">
        <h2>공지사항</h2>
      </div>
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
  }
}
</script>

<style scoped>
  @import '../assets/css/main.css';
</style>