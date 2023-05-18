<template>
  <div class="login">
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
        <form class="login-form">
          <div class="form-group">
            <input class="email" placeholder="이메일을 입력해주세요.">
          </div>
          <div class="form-group">
            <input class="password" placeholder="비밀번호를 입력해주세요.">
          </div>
        </form>
        <div>
          <button class="submit" type="button">로그인</button>
        </div>
        <div>
          <a>아이디 찾기</a> |
          <a>비밀번호 찾기</a> |
          <a>회원가입</a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login {
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
  color: #FFFFFF;
  background: #303076;
  border-bottom: 3px solid #303076;
  animation: fadein 0.5s;
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
  color: black;
  background: white;
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