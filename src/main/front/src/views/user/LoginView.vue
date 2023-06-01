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
        <form class="login-form" @submit.prevent="fnLogin">
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
import { mapActions, mapGetters } from 'vuex'

export default {
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    // 체크박스 토글
    toggleCheckbox() {
      const checkbox = this.$refs.saveIdCheckbox;
      checkbox.checked = !checkbox.checked;
    },
    // Vuex의 login 액션을 매핑하여 사용
    ...mapActions(['login']),
    
    // 로그인 함수를 비동기로 변경
    async fnLogin() {
      if (this.email === '') {
        alert('ID를 입력하세요.')
        return
      }

      if (this.password === '') {
        alert('비밀번호를 입력하세요.')
        return
      }

      try {
        // 로그인 액션을 호출하고 결과를 받아옴
        let loginResult = await this.login({ email: this.email, password: this.password })
        if (loginResult) this.goToPages();
      } catch (err) {
        if (err.message.indexOf('Network Error') > -1) {
          alert('서버에 접속할 수 없습니다. 상태를 확인해주세요.')
        } else {
          alert('로그인 정보를 확인할 수 없습니다.')
        }
      }
    },
    
    // 페이지 이동 함수
    goToPages() {
      this.$router.push({
        name: 'main'
      })
    }
  },
  computed: {
    ...mapGetters({
      errorState: 'getErrorState'
    })
  }
}
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