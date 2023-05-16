<!-- PageHeader.vue -->
<template>
    <header>
      <div id="nav1">
        <router-link to="/">
            <img src="@/assets/imgs/Logo.png" width="150">
        </router-link>
        <router-link to="/lecture">분야별 강의</router-link>
        <router-link to="/lecture2">분야별 강의</router-link>
        <div class="search-popup">
          <form action="#" class="search-form" role="search">
              <fieldset>
                <div class="search-form-con">
                  <input type="text" name="search">
                </div>
              </fieldset>
          </form>
        </div>
      </div>
      <div id="nav2">
        <router-link to="/community">커뮤니티</router-link>
        <router-link to="/review">수강후기</router-link>
        <router-link to="/companyStudy">기업교육</router-link>
        <router-link to="/login">로그인</router-link>
        <router-link to="/regist">회원가입</router-link>
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
    display: flex;
    justify-content: space-between;
  }

  #nav1 {
    display: flex;
    align-items: center;
  }

  #nav1 img {
    margin-right: 10px;
  }

  #nav1 a {
    font-family: 'Noto Sans CJK KR', sans-serif;
    font-weight: bold;
    color: #2c3e50;
    text-decoration: none;
    margin-left: 20px;
  }

  .search-form {
    margin-left: 70px;
  }

  .search-form-con {
    display: flex;
    align-items: center;
  }

  .search-form-con input {
    width: 100%;
    height: 40px;
    padding: 5px 10px;
    border: none;
    background-color: #F6E0B3;
    border-radius: 30px;
  }

  #nav2 {
    display: flex;
    align-items: center;
  }

  #nav2 a {
    font-family: 'Noto Sans CJK KR', sans-serif;
    font-weight: bold;
    color: #2c3e50;
    text-decoration: none;
    margin-left: 20px;
  }
</style>