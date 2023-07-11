<template>
    <div id="registChangeContainer">
        <!-- 로그인 및 회원가입 이동 버튼 -->
        <div id="registChangeSection">
            <!-- 로그인 버튼 -->
            <button @click="$router.push('/login')" id="reLoginChangeBtn">
                로그인
            </button>
            <!-- 회원가입 버튼 -->
            <button @click="$router.push('/regist')" id="reRegistChangeBtn">
                회원가입
            </button>
        </div>
        <div id="registContainer">
            <!-- 이용약관 동의 섹션 -->
            <div v-if="!showForm" id="registCheckBox">
                <!-- "모든 이용 약관에 동의" 체크박스 -->
                <label id="registChkAll" for="agree_all">
                    <input
                        class="form-check-input"
                        type="checkbox"
                        name="agree_all"
                        id="agree_all"
                        v-model="allChecked"
                        @change="toggleAllAgreements"
                    />
                    <span style="cursor: pointer">모든 이용 약관에 동의.</span>
                </label>

                <!-- 개별 약관 체크박스 -->
                <label
                    id="registChoice"
                    v-for="(item, index) in agreements"
                    :key="index"
                    :for="'agree_' + item.value"
                    @click="toggleCheckbox(item.value)"
                >
                    <input
                        class="form-check-input"
                        type="checkbox"
                        :id="'agree_' + item.value"
                        :name="'agree'"
                        :value="item.value"
                        v-model="selectedAgreements"
                        @change="updateAllChecked"
                    />
                    <span style="cursor: pointer"
                        >{{ item.label
                        }}<strong v-if="item.optional" class="select_disable"
                        >(선택)</strong
                        ></span
                    >
                    <!-- 약관 내용 표시 -->
                    <div id="agreeitemPa">
                        <div
                            id="agreeItem"
                            v-if="item.value === 1"
                            :name="'agree_' + item.value"
                            v-html="fileText1"
                        ></div>
                        <div
                            id="agreeItem"
                            v-else-if="item.value === 2"
                            :name="'agree_' + item.value"
                            v-html="fileText2"
                        ></div>
                        <div
                            id="agreeItem"
                            v-else-if="item.value === 3"
                            :name="'agree_' + item.value"
                            v-html="fileText3"
                        ></div>
                    </div>
                </label>

                <!-- 약관 동의 여부에 따른 '동의합니다' 버튼 표시 -->
                <div>
                    <!-- 1,2 약관에 동의한 경우 '동의합니다' 버튼 색변경 및 회원가입 섹션으로 이동 -->
                    <button
                        v-if="selectedAgreements.includes(1) && selectedAgreements.includes(2)"
                        id="registSubmit"
                        :style="{
                        background: submitButtonColor,
                        color: submitButtonTextColor,
                        }"
                        type="button"
                        @click="handleAgreementSubmit"
                    >
                        동의합니다
                    </button>
                    <!-- 1이나 2 약관에 동의하지 않은 경우 회원가입 섹션으로 이동 안됨-->
                    <button
                        v-else-if="
                            selectedAgreements.includes(1) ||
                            selectedAgreements.includes(2)
                        "
                        id="registSubmit2"
                        :style="{
                            background: submitButtonColor,
                            color: submitButtonTextColor,
                        }"
                        type="button"
                        disabled
                    >
                        동의합니다
                    </button>
                    <!-- 약관에 동의하지 않은 경우 회원가입 섹션으로 이동X -->
                    <button
                        v-else
                        id="registSubmit2"
                        :style="{
                            background: submitButtonColor,
                            color: submitButtonTextColor,
                        }"
                        type="button"
                        disabled
                    >
                        동의합니다
                    </button>
                </div>
            </div>

            <!-- 회원가입 양식 섹션 -->
            <div id="registSection" v-else>
                <div class="regist-container2">
                    <form class="regist-form" @submit.prevent="register">
                        <!-- 이메일 입력 필드 -->
                        <div id="registFormGroup">
                            <input
                                id="registEmail"
                                placeholder="이메일 입력"
                                v-model="emailValue"
                                @input="showEmailOptions"
                            />
                            <!-- 이메일 목록 -->
                            <ul v-show="showEmailList" id="emailOptions">
                                <li v-for="(email, index) in filteredEmailList" :key="index" @click="selectEmailOption(email)">
                                {{ email }}
                                </li>
                            </ul>
                            <button
                                id="registEmailChk"
                                @click="checkEmailDuplicate"
                                :disabled="
                                    isChecking || valid.email || !member.email
                                "
                            >
                                중복체크
                            </button>
                            <p
                                v-show="valid.email && member.email"
                                id="registInputError"
                            >
                                이메일 주소를 정확히 입력해주세요. 예)
                                bedu@bedu.com
                            </p>
                            <p
                                v-show="emailChecked && !valid.emailChk"
                                id="registInputError"
                            >
                                중복된 이메일 입니다.
                            </p>
                            <p v-show="!valid.email && !valid.emailDomain && emailChecked" id="registInputError">
                                도메인을 포함한 올바른 이메일 주소를 입력해주세요.
                            </p>
                            <p
                                v-show="emailChecked && valid.emailChk && valid.emailDomain"
                                id="registInputCorrect"
                            >
                                사용가능한 이메일 입니다.
                            </p>
                        </div>
                        <!-- 닉네임 입력 필드 -->
                        <div id="registFormGroup">
                            <input
                                id="registNickname"
                                placeholder="닉네임 입력"
                                v-model="member.nickname"
                            />
                            <button
                                id="registNickChk"
                                @click="checkNickDuplicate"
                                :disabled="
                                    isChecking ||
                                    valid.nickname ||
                                    !member.nickname
                                "
                            >
                                중복체크
                            </button>
                            <p
                                v-show="valid.nickname && member.nickname"
                                id="registInputError"
                            >
                                닉네임은 2자리 이상 10자리 이하이며 특수문자는
                                사용하실 수 없습니다.
                            </p>
                            <p
                                v-show="nickChecked && !valid.nickChk"
                                id="registInputError"
                            >
                                중복된 닉네임 입니다.
                            </p>
                            <p
                                v-show="nickChecked && valid.nickChk"
                                id="registInputCorrect"
                            >
                                사용 가능한 닉네임 입니다.
                            </p>
                        </div>
                        <!-- 비밀번호 입력 필드 -->
                        <div id="registFormGroup">
                            <input
                                type="password"
                                id="registPassword"
                                placeholder="비밀번호 입력"
                                v-model="member.password"
                            />
                            <p
                                v-show="
                                    valid.password &&
                                    member.password &&
                                    (member.password.length < 6 ||
                                        member.password.length > 15)
                                "
                                id="registInputError"
                            >
                                비밀번호는 6자리 이상 15자리 이하로
                                작성해주세요.
                            </p>
                        </div>
                        <!-- 비밀번호 확인 필드 -->
                        <div id="registFormGroup">
                            <input
                                type="password"
                                id="registConfirmPassword"
                                placeholder="비밀번호 입력 확인"
                                v-model="confirmPassword"
                            />
                            <p
                                v-show="
                                    member.password !== confirmPassword
                                "
                                id="registInputError"
                            >
                                비밀번호가 일치하지 않습니다.
                            </p>
                            <p
                                v-show="
                                    !valid.password &&
                                    member.password &&
                                    member.password == confirmPassword &&
                                    member.password.length >= 6 &&
                                    member.password.length <= 15
                                "
                                id="registInputCorrect"
                            >
                                사용가능한 비밀번호 입니다.
                            </p>
                        </div>
                        <!-- 회원가입 Submit 버튼 -->
                        <div id="registFormGroup">
                            <button
                                id="registSubmitFormBtn"
                                type="submit"
                            >
                                회원가입
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    // 필요한 모듈 및 CSS 파일을 가져옵니다.
    import '@/assets/css/userStyle.css';
    export default {
        name: 'EmailInput',
        data() {
            return {
                allChecked: false, // "모든 이용 약관에 동의" 체크박스의 상태를 나타내는 데이터 속성
                selectedAgreements: [], // 선택된 약관 체크박스의 값을 저장하는 배열
                agreements: [
                    // 이용약관과 개인정보 수집에 대한 동의 항목을 포함
                    { value: 1, label: "이용약관 동의", optional: false },
                    {
                        value: 2,
                        label: "개인정보 수집, 이용 동의",
                        optional: false,
                    },
                    { value: 3, label: "이벤트 수신 동의", optional: true },
                ],
                fileText1: '', // 이용약관에 대한 내용을 담을 변수
                fileText2: '', // 개인정보 수집에 대한 내용을 담을 변수
                fileText3: '', // 이벤트 수신에 대한 내용을 담을 변수
                showForm: false, // 회원가입 양식을 보여줄지 여부를 결정하는 데이터 속성
                confirmPassword: "", // 비밀번호 확인을 위해 입력된 값을 저장하는 변수
                isChecking: false, // 중복 체크 상태를 나타내는 변수
                emailChecked: false, // 이메일 중복 체크 상태를 나타내는 변수
                nickChecked: false, // 닉네임 중복 체크 상태를 나타내는 변수
                emailValue: '', // 이메일 입력 필드의 값
                emailList: [], // 이메일 목록을 담을 배열
                member: {
                    email: "", // 회원의 이메일
                    nickname: "", // 회원의 닉네임
                    password: "", // 회원의 비밀번호
                },
                showEmailList: false, // 이메일 목록 표시 여부를 나타내는 변수
                filteredEmailList: [], // 필터링된 이메일 목록을 저장하는 변수
                valid: {
                    email: false, // 이메일의 유효성 검사 결과
                    nickname: false, // 닉네임의 유효성 검사 결과
                    password: false, // 비밀번호의 유효성 검사 결과
                    emailChk: false, // 이메일 중복 체크 결과
                    nickChk: false, // 닉네임 중복 체크 결과
                    emailDomain: false // 이메일 도메인 유효성 검사 결과
                },
            };
        },
        watch: {
            "member.email": function () {
                this.checkEmail();
            },
            "member.nickname": function () {
                this.checkNick();
            },
            "member.password": function () {
                this.checkPassword();
            },
            emailValue(value) {
                this.member.email = value;
                const frequencyEmails = [
                    '@naver.com',
                    '@gmail.com',
                    '@daum.net',
                    '@hanmail.net',
                    '@yahoo.com',
                    '@outlook.com',
                    '@nate.com',
                    '@kakao.com',
                ];
                const userEmails = frequencyEmails.map((email) => {
                    return value.includes('@') ? value.split('@')[0] + email : value + email;
                });
                this.emailList = userEmails;
            },
        },
        methods: {
            // 이메일 입력 시 이메일 목록을 보여주는 메서드
            showEmailOptions() {
                if (this.emailValue) {
                    // 이메일 목록을 필터링하여 보여줄 목록을 설정합니다.
                    this.filteredEmailList = this.emailList.filter((email) =>
                        email.includes(this.emailValue)
                    );
                    this.showEmailList = true;
                } else {
                    this.showEmailList = false;
                }
            },
            // 선택된 이메일 옵션을 emailValue에 할당하고 이메일 목록을 숨깁니다.
            selectEmailOption(email) {
                this.emailValue = email;
                this.showEmailList = false;
            },
            // 약관 동의 항목을 조회하는 메서드
            fetchAgreements() {
                const ids = [1, 2, 3]; // 변경할 id 값들을 배열로 선언

                // 각 id에 대해 순회하면서 약관 데이터 요청
                ids.forEach(id => {
                    this.$axiosSend("get", `/api/agree/${id}`)
                    .then(response => {
                        if (id === 1) {
                            this.fileText1 = response.data[0].content;
                        } else if (id === 2) {
                            this.fileText2 = response.data[0].content;
                        } else if (id === 3) {
                            this.fileText3 = response.data[0].content;
                        }
                    })
                    .catch(error => {
                        console.error(error);
                    });
                });
            },
            // 이메일의 유효성을 검사하는 메서드
            checkEmail() {
                const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
                if (!this.member.email) {
                    this.valid.email = false;
                    this.emailChecked = false;
                    return;
                }
                if (!validateEmail.test(this.member.email)) {
                    this.valid.email = true;
                    this.emailChecked = false;
                    this.valid.emailDomain = false;
                    return;
                }
                this.valid.email = false;
                this.valid.emailChk = false;
                this.emailChecked = false;
                const domain = this.member.email.split('@')[1];
                const allowedDomains = [
                    'naver.com',
                    'gmail.com',
                    'daum.net',
                    'hanmail.net',
                    'yahoo.com',
                    'outlook.com',
                    'nate.com',
                    'kakao.com',
                ];
                this.valid.emailDomain = allowedDomains.includes(domain);
            },
            // 이메일 중복 체크를 수행하는 메서드
            checkEmailDuplicate() {
                this.isChecking = true;
                this.$axiosSend("get", `/api/register/email/${this.member.email}`)
                .then((response) => {
                    const isDuplicate = response.data;
                    this.emailChecked = true;
                    this.valid.emailChk = !isDuplicate;
                })
                .catch((error) => {
                    console.log(error);
                    this.$swal("이메일 중복 체크 실패");
                })
                .finally(() => {
                    this.isChecking = false;
                });
            },
            // 닉네임의 유효성을 검사하는 메서드
            checkNick() {
                const validateNick = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,10}$/;
                if (!this.member.nickname) {
                    this.valid.nickname = false;
                    this.nickChecked = false;
                    return;
                }
                if (!validateNick.test(this.member.nickname)) {
                    this.valid.nickname = true;
                    this.nickChecked = false;
                    return;
                }
                this.valid.nickname = false;
                this.valid.nickChk = false;
                this.nickChecked = false;
            },
            // 닉네임 중복 체크를 수행하는 메서드
            checkNickDuplicate() {
                this.isChecking = true;
                this.$axiosSend(
                    "get",
                    `/api/register/nickname/${this.member.nickname}`
                )
                .then((response) => {
                    const isDuplicate = response.data;
                    this.nickChecked = true;
                    this.valid.nickChk = !isDuplicate;
                })
                .catch((error) => {
                    console.log(error);
                    this.$swal("닉네임 중복 체크 실패");
                })
                .finally(() => {
                    this.isChecking = false;
                });
            },
            // 비밀번호의 유효성을 검사하는 메서드
            checkPassword() {
                const validatePassword = /^.{6,15}$/;
                if (
                    !validatePassword.test(this.member.password) ||
                    !this.member.password ||
                    this.member.password.length < 6 ||
                    this.member.password.length > 15
                ) {
                    this.valid.password = true;
                    return;
                }
                this.valid.password = false;
            },
            // "모든 이용 약관에 동의" 체크박스 토글 메서드
            toggleAllAgreements(event) {
                this.selectedAgreements = event.target.checked
                    ? this.agreements.map((item) => item.value)
                    : [];
                this.updateAllChecked();
            },
            // 체크박스 상태에 따라 "모든 이용 약관에 동의" 체크박스 업데이트
            updateAllChecked() {
                this.allChecked =
                    this.selectedAgreements.length === this.agreements.length;
            },
            // 개별 약관 체크박스 토글 메서드
            toggleCheckbox(value) {
                const checkbox = document.getElementById(`agree_${value}`);
                checkbox.checked = !checkbox.checked;
                this.updateAllChecked();
            },
            // 약관 동의 버튼 클릭 시 처리하는 메서드
            handleAgreementSubmit() {
                // 스크롤을 페이지 상단으로 이동
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth',
                });
                this.showForm = true;
            },
            // 회원가입 양식을 보여주기 위해 showForm 값을 변경
            showRegistrationForm() {
                this.showForm = true;
            },
            // 사용자의 회원가입을 처리하는 메서드
            register() {
                if (
                    !this.emailChecked ||
                    !this.valid.emailChk ||
                    !this.valid.emailDomain
                ) {
                    this.$swal("이메일을 다시 확인해주세요.");
                } else if (
                    !this.nickChecked ||
                    !this.valid.nickChk 
                ) {
                    this.$swal("닉네임을 다시 확인해주세요.");
                } else if (
                    this.valid.password ||
                    !this.member.password ||
                    this.member.password !== this.confirmPassword ||
                    this.member.password.length < 6 ||
                    this.member.password.length > 15
                ) {
                    this.$swal("비밀번호를 다시 확인해주세요.");
                } else if (
                    this.emailChecked &&
                    this.valid.emailChk &&
                    this.valid.emailDomain &&
                    this.nickChecked &&
                    this.valid.nickChk &&
                    !this.valid.password &&
                    this.member.password &&
                    this.member.password == this.confirmPassword &&
                    this.member.password.length >= 6 &&
                    this.member.password.length <= 15
                ) {
                    this.$axiosSend('post', '/api/register', this.member)
                    .then((response) => {
                        this.$swal(response.data);
                        this.$router.push({
                            name: "login",
                        });
                    })
                    .catch((error) => {
                        console.log(error);
                        this.$swal("회원가입 실패");
                    });
                } else {
                    this.$swal("회원가입 양식을 <br>다시 확인해주세요.");
                    return;
                }
            },
        },
        created() {
            this.fetchAgreements(); // 컴포넌트 생성 시 약관 동의 항목과 내용을 조회
        },
        computed: {
            // 약관 동의 여부에 따라 Submit 버튼의 스타일을 변경하는 계산된 속성
            submitButtonColor() {
                return this.selectedAgreements.includes(1) &&
                    this.selectedAgreements.includes(2)
                    ? "#303076"
                    : "";
            },
            submitButtonTextColor() {
                return this.selectedAgreements.includes(1) &&
                    this.selectedAgreements.includes(2)
                    ? "white"
                    : "";
            },
        },
    };
</script>