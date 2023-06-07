<template>
    <div id="loginChangeContainer">
        <div id="loginChangeSection">
            <button @click="$router.push('/login')" id="loLoginChangeBtn">
                로그인
            </button>
            <button @click="$router.push('/regist')" id="loRegistChangeBtn" class="registbtn">
                회원가입
            </button>
        </div>
        <div id="loginContainer">
            <div id="loginLogoContainer">
                <img id="loginLogo" src="@/assets/imgs/Logo2.png" /><br />
                <p>강의는 역시 B:EDU</p>
            </div>
            <div id="loginSection">
                <form class="login-form" @submit.prevent="login">
                    <div class="form-group">
                        <input
                            id="emailInputSection"
                            v-model="email"
                            placeholder="사용자 이메일"
                        />
                    </div>
                    <div class="form-group">
                        <input
                            id="pwdInputSection"
                            v-model="password"
                            placeholder="비밀번호"
                        />
                    </div>
                    <div id="idRememberSection">
                        <input
                            class="form-check-input"
                            type="checkbox"
                            ref="saveIdCheckbox"
                        />
                        <span style="cursor: pointer" @click="toggleCheckbox"
                            >아이디 저장</span
                        >
                    </div>
                    <div>
                        <button id="loginSubmitBtn" type="submit">로그인</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import '@/assets/css/userStyle.css';

export default {
    data() {
        return {
            email: "",
            password: "",
        };
    },
    methods: {
        // 체크박스 토글
        toggleCheckbox() {
            const checkbox = this.$refs.saveIdCheckbox;
            checkbox.checked = !checkbox.checked;
        },
        login() {
            this.$axiosSend("post", "/api/login", {
                email: this.email,
                password: this.password,
            })
                .then((response) => {
                    if (response.status === 200) {
                        localStorage.setItem("isLoggedIn", "true");
                        this.isLoggedIn = true;
                        this.$router.push("/");
                        alert("로그인 성공");
                    } else {
                        alert("로그인 실패");
                    }
                })
                .catch((error) => {
                        console.log(error);
                        alert("로그인 실패");
                });
        }
    },
};
</script>
