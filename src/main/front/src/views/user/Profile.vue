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
                    <b-button class="fs-4" @click="togglePasswordChange">비밀번호 변경</b-button>
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
                        <b-button @click="cancelPasswordChange">취소</b-button>
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
                            v-model="password"
                            type="password"
                            placeholder="비밀번호를 입력해주세요"
                        />
                        <b-button>확인</b-button>
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
            showPasswordChk: false,
            showPasswordChange: false,
            email: this.$store.getters.getEmail,
            password: '',
        }
    },
    methods: {
        togglePasswordChange() {
            this.showPasswordChk = true;
        },
        cancelPasswordChange() {
            this.showPasswordChk = false;
            this.password = ''; // 비밀번호 초기화
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
    },
}

</script>