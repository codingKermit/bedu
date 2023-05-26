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
        <img src="@/assets/imgs/Logo2.png" width="180"><br />
        <p>강의는 역시 B:EDU</p>
      </div>
      <div class="login-container">
        <form class="login-form" @submit.prevent="login">
          <div class="form-group">
            <input v-model="email" class="email" placeholder="사용자 이메일">
          </div>
          <div class="form-group">
            <input v-model="password" class="password" placeholder="비밀번호">
          </div>
          <div class="idrm">
            <input class="form-check-input" type="checkbox" ref="saveIdCheckbox">
            <span style="cursor: pointer;" @click="toggleCheckbox">아이디 저장</span>
          </div>
          <div>
            <button class="submit" type="submit">로그인</button>
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
      email: '',
      password: ''
    }
  },
  methods: {
    toggleCheckbox() {
      const checkbox = this.$refs.saveIdCheckbox;
      checkbox.checked = !checkbox.checked;
    },
    login() {
      if (this.email === '') {
        alert('이메일을 입력하세요.');
        return;
      }

      if (this.password === '') {
        alert('비밀번호를 입력하세요.');
        return;
      }

      const memberDto = {
        email: this.email,
        password: this.password,
      };

      this.$axios.post('/api/login', memberDto)
        .then(response => {
          alert(response.data);
          this.$router.push({
            name: 'main'
          });
        })
        .catch(error => {
          console.log(error);
          alert('로그인 실패');
        });
    }
  }
};
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: auto;
  text-align: center;
  padding-top: 15%;
  padding-bottom: 20%;
}

.change {
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
  border-radius: 1rem;
  background: white;
  margin-top: 50px;
}

.email,
.password {
  position: relative;
  width: 90%;
  padding: 0.8rem;
  margin-top: 15px;
  font-size: 1.1rem;
  font-weight: 400;
}

.idrm {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin-top: 20px;
  margin-left: 30px;
}

input[type=checkbox] {
  padding: 10px;
}

.idrm span {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin-left: 10px;
  margin-top: 3px;
}

.submit {
  position: relative;
  width: 90%;
  padding: 0.8rem;
  margin-top: 40px;
  font-size: 1.3rem;
  font-weight: 400;
  color: #FFFFFF;
  background: #303076;
}
</style>