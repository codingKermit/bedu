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

<style scoped>
    #loginChangeContainer {
        display: flex;
        flex-direction: column;
        align-items: center;
        min-height: auto;
        text-align: center;
        padding-top: 15%;
        padding-bottom: 20%;
    }

    #loginChangeSection {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #loLoginChangeBtn {
        width: 300px;
        padding: 1rem;
        font-size: 1rem;
        font-weight: bold;
        border: none;
        cursor: pointer;
        color: #ffffff;
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

    #loRegistChangeBtn {
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

    #loginContainer {
        position: relative;
        background: white;
        width: 600px;
        height: 460px;
    }

    #loginLogoContainer {
        position: relative;
        font-size: 1.5rem;
        border-radius: 1rem;
        background: white;
        margin-top: 50px;
    }

    #loginLogo {
        width: 180px;
    }

    #emailInputSection,
    #pwdInputSection {
        position: relative;
        width: 90%;
        padding: 0.8rem;
        margin-top: 15px;
        font-size: 1.1rem;
        font-weight: 400;
    }

    #idRememberSection {
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        margin-top: 20px;
        margin-left: 30px;
    }

    #idRememberSection span {
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        margin-left: 10px;
        margin-top: 3px;
    }

    #loginSubmitBtn {
        position: relative;
        width: 90%;
        padding: 0.8rem;
        margin-top: 40px;
        font-size: 1.3rem;
        font-weight: 400;
        color: #ffffff;
        background: #303076;
    }
</style>
