<template>
    <div>
        <div class="p-4 p-md-5 w-100 d-flex">
            <!-- 좌측 네비 -->
            <my-page-cate-navi></my-page-cate-navi>

            <div class="w-100">
                <my-page-cate-navi-toggle></my-page-cate-navi-toggle>

                <div class="fs-3 mb-5">
                    <p class="fw-bold">닉네임</p>
                    <b-container class="bedu-bg-custom-blue rounded-3">
                        <div class="text-white p-2">{{ this.$store.getters.getNickname }}</div>
                    </b-container>
                </div>
                <div class="fs-3 mb-5">
                    <p>이메일</p>
                    <b-container class="bedu-bg-custom-blue rounded-3">
                        <div class="text-white p-2">{{ this.$store.getters.getEmail }}</div>
                    </b-container>
                </div>
                <div class="mb-5">
                    <b-button v-if="showPasswordCbtn" class="fs-4" @click="togglePasswordChange">비밀번호 변경</b-button>
                    <div v-if="showPasswordChk">
                        <input
                            type="hidden"
                            id="emailInputSection"
                            :value="email"
                            @input="updateEmail"
                        />
                        <input
                            id="pwdInputSection"
                            v-model="password"
                            type="password"
                            placeholder="비밀번호를 입력해주세요"
                        />
                        <b-button @click="checkPasswordChk">확인</b-button>
                        <b-button @click="cancelPasswordChk">취소</b-button>
                    </div>
                    <div v-if="showPasswordChange">
                        <input
                            type="hidden"
                            id="emailInputSection"
                            :value="email"
                            @input="updateEmail"
                        />
                        <input
                            id="pwdInputSection"
                            v-model="newPassword"
                            type="password"
                            placeholder="비밀번호를 입력해주세요"
                        />
                        <input
                            id="pwdInputSection"
                            v-model="newPasswordChk"
                            type="password"
                            placeholder="비밀번호를 확인해주세요"
                        />
                        <p
                            v-show="
                                newPassword !== newPasswordChk
                            "
                            id="registInputError"
                        >
                            비밀번호가 일치하지 않습니다.
                        </p>
                        <b-button @click="PasswordChange">확인</b-button>
                        <b-button @click="cancelPasswordChange">취소</b-button>
                    </div>
                </div>
                <div>
                    <b-button variant="danger" class="fs-4">회원탈퇴</b-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import MyPageCateNavi from '../../components/myPage/MyPageCateNavi.vue'
import MyPageCateNaviToggle from '../../components/myPage/MyPageCateNaviToggle.vue'
import axios from 'axios'
export default{
  components: { MyPageCateNavi, MyPageCateNaviToggle },
    name : 'profile',
    data() {
        return {
            showPasswordCbtn: true,
            showPasswordChk: false,
            showPasswordChange: false,
            email: this.$store.getters.getEmail,
            password: '',
            newPassword: '',
            newPasswordChk: '',
            valid : {
                password: false
            }
        }
    },
    methods: {
        togglePasswordChange() {
            this.showPasswordCbtn = false,
            this.showPasswordChk = true;
        },
        cancelPasswordChk() {
            this.showPasswordCbtn = true,
            this.showPasswordChk = false;
            this.password = ''; // 비밀번호 초기화
        },
        cancelPasswordChange() {
            this.showPasswordCbtn = true,
            this.showPasswordChange = false;
            this.password = '', // 비밀번호 초기화
            this.newPassword = '',
            this.newPasswordChk = '';
        },
        updateEmail(event) {
            const newEmail = event.target.value;
            this.email = newEmail;
        },
        checkPasswordChk() {
            const email = this.email; // 이메일 가져오기
            const password = this.password; // 사용자가 입력한 비밀번호 가져오기
            
            const reqData = {
                email: email,
                password: password
            };
            return axios.post('/api/passwordChk', reqData, {
                headers: {
                    'Content-type': 'application/json'
                }
            })
            .then((response) => {
                // 서버에서 받은 응답 처리
                console.log(response.data.success)
                if (response.data.success) {
                    // 비밀번호 일치
                    // 원하는 조치를 취하거나 메시지를 표시할 수 있음
                    this.$swal("correct.");
                    this.showPasswordChange = true;
                    this.showPasswordChk = false;
                } else {
                    // 비밀번호 불일치
                    // 원하는 조치를 취하거나 메시지를 표시할 수 있음
                    this.$swal("비밀번호가 일치하지 않습니다.");
                }
            })
            .catch((error) => {
                console.log(error);
                this.$swal("Error");
            });
        },
        // 비밀번호의 유효성을 검사하는 메서드
        checkPassword() {
            // 유효한 비밀번호 형식인지 확인하기 위한 정규식
            const validatePassword = /^[1-9a-zA-Z].{6,15}$/;
            if (
                !validatePassword.test(this.newPassword) ||
                !this.newPassword ||
                this.newPassword.length < 6 ||
                this.newPassword.length > 15
            ) {
                this.valid.password = true;
                return;
            }
            this.valid.password = false;
        },
        PasswordChange() {
            console.log(this.valid.password)
            console.log(this.newPassword)
            console.log(this.newPasswordChk)
            console.log(this.newPassword.length)
            
            if (
                !this.valid.password ||
                !this.newPassword ||
                this.newPassword !== this.newPasswordChk ||
                this.newPassword.length < 6 ||
                this.newPassword.length > 15
            ) {
                this.$swal("비밀번호를 다시 확인해주세요.");
            } else if (
                this.valid.password &&
                this.newPassword &&
                this.newPassword == this.newPasswordChk &&
                this.newPassword.length >= 6 &&
                this.newPassword.length <= 15
            ) {
                this.$swal("ㅇㅇ");
            }
        }
    },
}

</script>