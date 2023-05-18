<template>
  <div class="regist">
    <div class="change">
      <button @click="$router.push('/login')" class="loginbtn">
        로그인
      </button>
      <button @click="$router.push('/regist')" class="registbtn">
        회원가입
      </button>
    </div>
    <div class="container">
      <div class="logo-container">
        <img src="@/assets/imgs/Logo2.png" width="180"><br/>
        <p>강의는 역시 B:EDU</p>
      </div>
      <div class="login-container">
        회원가입
      </div>
    </div>
  </div>
</template>

<style scoped>
.regist {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 90vh;
  background: #EDF0F5;
  text-align: center;
}

.change {
  margin-top: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.loginbtn {
  width: 300px;
  padding: 1rem;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  cursor: pointer;
  color: black;
  background: white;
  border-bottom: 3px solid #303076;
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.registbtn {
  width: 300px;
  padding: 1rem;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  cursor: pointer;
  color: white;
  background: #303076;
  animation: fadein 0.5s;
  border-bottom: 3px solid #303076;
}

.container {
  position: relative;
  background: white;
  width: 600px;
  height: 460px;
}

.logo-container {
  position: relative;
  font-size: 1.5rem;
  padding: 0.5rem;
  border-radius: 1rem;
  background: white;
  margin-top: 50px;
}

.email,
.password {
  width: 300px;
  padding: 0.4rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  margin-top: 1rem;
}

.submit {
  width: 300px;
  padding: 0.4rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  margin-top: 1rem;
  color: #FFFFFF;
  background: #303076;
}
</style>