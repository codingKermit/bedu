<!-- PageHeader.vue -->
<template>
    <header>
      <div id="nav">
        <router-link to="/">
            <img src="@/assets/imgs/Logo.png" width="150">
        </router-link>
        <router-link to="/lecture">분야별 강의</router-link>
        <router-link to="/lecture2">분야별 강의</router-link>
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

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 50px;
  background-color: #EDC268;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
  
  </style>