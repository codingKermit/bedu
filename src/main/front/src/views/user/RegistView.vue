<template>
  <div class="regist">
    <div class="change">
      <button @click="$router.push('/login')" class="loginbtn">로그인</button>
      <button @click="$router.push('/regist')" class="registbtn">회원가입</button>
    </div>
    <div class="container">
      <div v-if="!showForm" class="regist-container">
        <label class="areeAll" for="agree_all">
          <input class="form-check-input" type="checkbox" name="agree_all" id="agree_all" v-model="allChecked"
            @change="toggleAllAgreements">
          <span style="cursor: pointer;">모든 이용 약관에 동의.</span>
        </label>

        <label class="choice" v-for="(item, index) in agreements" :key="index" :for="'agree_' + item.value"
          @click="toggleCheckbox(item.value)">
          <input class="form-check-input" type="checkbox" :id="'agree_' + item.value" :name="'agree'" :value="item.value"
            v-model="selectedAgreements" @change="updateAllChecked">
          <span style="cursor: pointer;">{{ item.label }}<strong v-if="item.optional"
              class="select_disable">(선택)</strong></span>
          <textarea v-if="item.value === 1" readonly :name="'agree_' + item.value" v-model="fileText1" rows="7"
            cols="85"></textarea>
          <textarea v-else-if="item.value === 2" readonly :name="'agree_' + item.value" v-model="fileText2" rows="7"
            cols="85"></textarea>
        </label>

        <a>※위 항목에 동의하지 않는 경우 회원가입이 불가합니다.</a>

        <div>
          <button v-if="selectedAgreements.length === agreements.length" class="submit"
            :style="{ background: submitButtonColor, color: submitButtonTextColor }" type="button"
            @click="showRegistrationForm">동의합니다</button>
          <button v-if="selectedAgreements.length != agreements.length" class="submit2"
            :style="{ background: submitButtonColor, color: submitButtonTextColor }" type="button">
            동의합니다</button>
        </div>
      </div>

      <div class="container2" v-else>
        <div class="regist-container2">
          <form class="regist-form">
            <div class="form-group">
              <input class="email" placeholder="이메일 입력" v-model="email" required :rules="emailRules">
              <button class="emailChk">중복체크</button>
            </div>
            <div class="form-group">
              <input class="password" placeholder="비밀번호 입력" v-model="password" required :rules="passwordRules">
            </div>
            <div class="form-group">
              <button class="submitformbtn" @click="signUpSubmit()">회원가입</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import fileText1 from 'raw-loader!./이용 약관 동의.txt';
import fileText2 from 'raw-loader!./개인정보 수집, 이용 동의.txt';

export default {
  data() {
    return {
      allChecked: false,
      selectedAgreements: [],
      agreements: [
        { value: 1, label: '이용약관 동의', optional: false },
        { value: 2, label: '개인정보 수집, 이용 동의', optional: false }
      ],
      fileText1: fileText1,
      fileText2: fileText2,
      showForm: false,

      email: "",
      emailRules: [
        v => !!v || '이메일을 작성해주세요',
        v => /.+@.+\..+/.test(v) || '이메일 형식으로 작성해주세요',
      ],
      password: "",
      passwordRules: [
        v => !!v || '비밀번호을 작성해주세요',
        v => (v && v.length >= 5) || '비밀번호는 5글자 이상 작성해주세요',
        v => /(?=.*\d)/.test(v) || '숫자를 포함해야합니다',
        v => /([!@$%])/.test(v) || '특수문자를 포함해야합니다 [!@#$%]'
      ]
    };
  },
  methods: {
    toggleAllAgreements(event) {
      this.selectedAgreements = event.target.checked ? this.agreements.map(item => item.value) : [];
      this.updateAllChecked();
    },
    updateAllChecked() {
      this.allChecked = this.selectedAgreements.length === this.agreements.length;
    },
    showRegistrationForm() {
      this.showForm = true;
    },
    toggleCheckbox(value) {
      const checkbox = document.getElementById(`agree_${value}`);
      checkbox.checked = !checkbox.checked;
      this.updateAllChecked();
    },

    signUpSubmit() {
      const validate = this.$refs.form.validate()
      if (validate) {
        let saveData = {};
        saveData.email = this.email;
        saveData.password = this.password;

        try {
          this.$axios.post("/api/member", JSON.stringify(saveData), {
            headers: {
              "Content-Type": `application/json`,
            },
          })
            .then((response) => {
              console.log(response)
              if (response.data.errorCode === 400) {
                alert(response.data.errorMessage)

              }
              else {
                alert("회원가입이 완료되었습니다. 로그인 화면으로 돌아갑니다")
                this.$router.push({ path: './login' });
              }
            })
            .catch(error => {
              console.log(error.response);

            });
        } catch (error) {
          console.error(error);
        }
      }
    }
  },

  computed: {
    submitButtonColor() {
      return this.selectedAgreements.length === this.agreements.length ? '#303076' : '';
    },
    submitButtonTextColor() {
      return this.selectedAgreements.length === this.agreements.length ? 'white' : '';
    },
  },
};
</script>

<style scoped>
.regist {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: auto;
  background: #EDF0F5;
  text-align: center;
  padding-bottom: 40px;
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
  height: auto;
  padding-bottom: 40px;
}

.regist-container {
  margin-left: 30px;
  margin-top: 30px;
}

.areeAll {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.regist-container .choice {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin-bottom: 15px;
}

.regist-container a {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.regist-container label input {
  margin-right: 10px;
}

.regist-container label span {
  font-size: 17px;
  white-space: nowrap;
  /* Prevent line break */
}

textarea {
  margin-top: 15px;
  width: 95%;
  font-size: 1em;
  resize: none;
  align-self: flex-start;
  /* Adjust vertical alignment of textarea */
}

input[type=checkbox] {
  padding: 10px;
}

.submit,
.submit2 {
  width: 90%;
  padding: 0.8rem;
  margin-top: 40px;
  margin-left: -23px;
  font-size: 1.3rem;
  font-weight: 400;
  border: none;
  cursor: pointer;
  background-color: white;
  color: #303076;
  border: 3px solid #303076;
}

.submit2:active {
  box-shadow: inset 1px 2px 5px 0px rgb(203, 203, 203), inset -1px 0px 3px 0px rgb(203, 203, 203);
}

.container2 {
  padding-bottom: 30px;
}

.email {
  width: 74%;
  padding: 0.8rem;
  margin-top: 40px;
  margin-left: -23px;
  font-size: 1.1rem;
  font-weight: 400;
}

.emailChk {
  width: 20%;
  padding: 0.8rem;
  margin-top: 40px;
  margin-left: -23px;
  font-size: 1.1rem;
  font-weight: 400;
  color: white;
  background-color: #707070;
}

.password {
  width: 90%;
  padding: 0.8rem;
  margin-top: 40px;
  margin-left: -23px;
  font-size: 1.1rem;
  font-weight: 400;
}

.submitformbtn {
  width: 90%;
  padding: 0.8rem;
  margin-top: 80px;
  margin-left: -23px;
  font-size: 1.3rem;
  font-weight: 400;
  border: none;
  cursor: pointer;
  background-color: white;
  color: #303076;
  border: 3px solid #303076;
}
</style>