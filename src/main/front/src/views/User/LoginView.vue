<template>
  <div class="login">
    <div class="container">
      <div class="change">
        <button @click="$router.push('/login')">
          로그인
        </button>
        <button @click="$router.push('/regist')">
          회원가입
        </button>
      </div>
      <div class="logo-container">
        <img src="@/assets/imgs/Logo2.png" width="180"><br/>
        <p>강의는 역시 B:EDU</p>
      </div>
      <div class="button-container">
        <form class="login-form">
          <div class="form-group">
            <button class="kakao-login-btn">카카오 계정으로 로그인</button>
          </div>
          <div class="form-group">
            <button class="email-login-btn">이메일로 로그인</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      black: this.black,
      blue: this.blue,
      yellow: this.yellow
    };
  },
};
</script>

<style>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #EDF0F5;
  text-align: center;
}

.change {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 1rem;
}

.change button {
  width: 150px;
  padding: 0.5rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  color: #FFFFFF;
}

.container {
  padding-top: 50px;
  padding-bottom: 150px;
  border-radius: 1rem;
  background: white;
  margin-left: 350px;
  margin-right: 350px;
}

.logo-container {
  font-size: 1.5rem;
  padding: 0.5rem;
  border-radius: 1rem;
  background: white;
}

.button-container {
  padding: 1rem;
  text-align: center;
  align-items: center;
}

.kakao-login-btn,
.email-login-btn {
  width: 300px;
  padding: 1rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  color: #FFFFFF;
  margin-top: 1rem;
}

.kakao-login-btn {
  background-color: #FEE500;
}

.email-login-btn {
  background-color: #FFFFFF;
  color: #4A4A4A;
}

</style>