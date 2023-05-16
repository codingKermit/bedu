<!-- PageHeader.vue -->
<template>
  <header>
    <div class="dien">
      <div id="nav1">
        <router-link to="/">
            <img src="@/assets/imgs/Logo.png" width="150">
        </router-link>
        <router-link to="/">분야별 강의</router-link>
        <router-link to="/community">커뮤니티</router-link>
        <router-link to="/review">수강후기</router-link>
        <router-link to="/companyStudy">기업교육</router-link>
      </div>
      <div id="nav2">
        <div class="search-popup">
            <div class="border-3 rounded-pill p-2 d-flex align-middle text-center m-auto mb-5 border-bedu">
              <font-awesome-icon class="m-auto mx-3" :icon="['fas', 'magnifying-glass']" />
              <b-form-input class="border-0 me-2"></b-form-input>
            </div>
        </div>
        <router-link to="/login">로그인</router-link>
        <router-link to="/regist">회원가입</router-link>
      </div>
    </div>
  </header>
</template>

<script>
export default {

}
</script>

<style scoped>
header {
margin: 0 auto;
background-color: #EDC268;
padding-top: 3%;
padding-bottom: 5%;
}

.dien {
width: auto;
}

.dien #nav1 {
float: left;
}

.dien #nav2 {
float: right;
}

#nav1 img {
margin-right: 20px;
}

#nav1 a {
margin-right: 30px;
font-family: 'Noto Sans CJK KR', sans-serif;
font-weight: bold;
color: #2c3e50;
text-decoration: none;
}

#nav2 a {
margin-left: 20px;
font-family: 'Noto Sans CJK KR', sans-serif;
font-weight: bold;
color: #2c3e50;
text-decoration: none;
}

#nav2 .search-popup {
display: inline-block;
margin-left: -20px;
margin-right: -50px;
}

#nav2 .search-popup input {
width: 100%;
height: 15px;
padding: 5px 10px;
border: none;
background-color: #F6E0B3;
border-radius: 30px;
}

.border-bedu{
background-color: #F6E0B3;
width: 70%;
border-color: #EDC268;
}


</style>