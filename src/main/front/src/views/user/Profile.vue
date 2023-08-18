<!-- 작성자 우지원 -->


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
                            :value="member.email"
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
                            :value="member.email"
                            @input="updateEmail"
                        />
                        <input
                            id="pwdInputSection"
                            v-model="member.newPassword"
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
                                member.newPassword !== newPasswordChk
                            "
                            id="registInputError"
                        >
                            비밀번호가 일치하지 않습니다.
                        </p>
                        <b-button @click="PasswordChange">비밀번호 변경</b-button>
                        <b-button @click="cancelPasswordChange">취소</b-button>
                    </div>
                </div>
                <div>
                    <b-button v-if="showWithPasswordCbtn" variant="danger" class="fs-4" @click="toggleShowWithdraw">회원탈퇴</b-button>
                    <div v-if="showWithPasswordChk">
                        <input
                            type="hidden"
                            id="emailInputSection"
                            :value="member.email"
                            @input="updateEmail"
                        />
                        <input
                            id="pwdInputSection"
                            v-model="withPassword"
                            type="password"
                            placeholder="비밀번호를 입력해주세요"
                        />
                        <b-button @click="withDrawPasswordChk">확인</b-button>
                        <b-button @click="cancelWithPasswordChk">취소</b-button>
                    </div>
                    <div v-if="showWithPasswordChange">
                        <b-button variant="danger" @click="withDrawBtn">회원탈퇴</b-button>
                        <b-button @click="cancelWithPasswordChange">취소</b-button>
                    </div>
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
            member: {
                email: this.$store.getters.getEmail,
                newPassword: '',
            },
            password: '',
            newPasswordChk: '',
            valid : {
                password: false
            },
            showWithPasswordCbtn: true,
            showWithPasswordChk: false,
            showWithPasswordChange: false,
            withPassword: '',
        }
    },
    watch: {
        // Password Data 변경 감지
        "member.newPassword": function () {
            this.checkPassword();
        },
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
            this.member.newPassword = '',
            this.newPasswordChk = '';
        },
        updateEmail(event) {
            const newEmail = event.target.value;
            this.member.email = newEmail;
        },
        toggleShowWithdraw() {
            this.showWithPasswordCbtn = false,
            this.showWithPasswordChk = true;
        },
        cancelWithPasswordChk() {
            this.showWithPasswordCbtn = true,
            this.showWithPasswordChk = false;
            this.password = ''; // 비밀번호 초기화
        },
        cancelWithPasswordChange() {
            this.showWithPasswordCbtn = true,
            this.showWithPasswordChange = false;
            this.withPassword = ''; // 비밀번호 초기화
        },
        checkPasswordChk() {
            const email = this.member.email; // 이메일 가져오기
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
        withDrawPasswordChk() {
            const email = this.member.email; // 이메일 가져오기
            const password = this.withPassword; // 사용자가 입력한 비밀번호 가져오기
            
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
                if (response.data.success) {
                    // 비밀번호 일치
                    // 원하는 조치를 취하거나 메시지를 표시할 수 있음
                    this.$swal("correct.");
                    this.showWithPasswordChange = true;
                    this.showWithPasswordChk = false;
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
            const validatePassword = /^[0-9a-zA-Z].{6,15}$/;
            if (
                !validatePassword.test(this.member.newPassword) ||
                !this.member.newPassword ||
                this.member.newPassword.length < 6 ||
                this.member.newPassword.length > 15
            ) {
                this.valid.password = true;
                return;
            }
            this.valid.password = false;
        },
        PasswordChange() {
            if (
                !this.valid.password ||
                !this.member.newPassword ||
                this.member.newPassword !== this.newPasswordChk ||
                this.member.newPassword.length < 6 ||
                this.member.newPassword.length > 15
            ) {
                this.$swal("비밀번호를 다시 확인해주세요.");
            } else if (
                this.valid.password &&
                this.member.newPassword &&
                this.member.newPassword == this.newPasswordChk &&
                this.member.newPassword.length >= 6 &&
                this.member.newPassword.length <= 15
            ) {
            const email = this.member.email; // 이메일 가져오기
            const password = this.member.newPassword; // 사용자가 입력한 비밀번호 가져오기
            const passwordData = {
                email: email,
                password: password
            };
            return axios.post('/api/passwordChange', passwordData, {
                headers: {
                    'Content-type': 'application/json'
                }
            })
            .then((response) => {
                this.$swal(response.data);
                localStorage.removeItem("user_token");
                localStorage.removeItem('login_time');
                localStorage.removeItem('cbnumList');
                localStorage.removeItem('qsbnumList');
                this.$store.commit('IS_AUTH', false);
                this.$store.commit('NICKNAME', null);
                this.$store.commit('USERNUM', null);
                this.$store.commit('CBNUMLIST', null);
                this.$store.commit('CLS', null);
                this.$store.commit('UDY', null);
                this.$store.commit('QSBNUMLIST', null);
                this.$store.commit('EMAIL', null);
                this.$store.commit('LESSONS', []);
                this.$store.commit('SUBSCRIBE',null);
                this.$router.push({
                    name: "login",
                });
            })
            .catch((error) => {
                console.log(error);
                this.$swal("비밀번호 변경 실패");
            });
            } else {
                this.$swal("비밀번호를 다시 확인해주세요.");
                return;
            }
        },
        withDrawBtn() {
            this.$swal({
                title: '회원탈퇴 하시겠습니까?',
                showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                cancelButtonColor: '#6c757d', // cancel 버튼 색깔 지정
                confirmButtonColor: '#303076',
                confirmButtonText: '확인', // confirm 버튼 텍스트 지정
                cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                
                }).then(result => {
                // 만약 Promise리턴을 받으면,
                if (result.isConfirmed) { // 만약 모달창에서 confirm 버튼을 눌렀다면
                    this.$axiosSend("post", `/api/withDraw/${this.member.email}`)
                    this.$swal({
                        confirmButtonColor: '#303076',
                        title: '회원탈퇴가 완료되었습니다.',
                        
                    }).then(function() {
                        window.location.reload(true);
                    });
                    localStorage.removeItem("user_token");
                    localStorage.removeItem('login_time')
                    localStorage.removeItem('cbnumList')
                    localStorage.removeItem('qsbnumList')
                    this.$store.commit('IS_AUTH', false);
                    this.$store.commit('NICKNAME', null);
                    this.$store.commit('USERNUM', null);
                    this.$store.commit('CLS', null);
                    this.$store.commit('UDY', null);
                    this.$store.commit('CBNUMLIST', null);
                    this.$store.commit('QSBNUMLIST', null);
                    this.$store.commit('EMAIL', null);
                    this.$store.commit('LESSONS', []);
                    this.$store.commit('SUBSCRIBE',null);
                    this.$router.push({
                        name: 'main',
                    })
                    .catch((error) => {
                        console.log(error);
                        this.$swal("회원탈퇴 실패");
                    });
                } else {
                    // 취소(No)를 선택한 경우
                    // 아무 작업도 하지 않음
                }
            });

        }
    },
}

</script>