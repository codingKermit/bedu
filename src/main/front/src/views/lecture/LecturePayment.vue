<template>
    <div>
        <b-container class="py-5 d-flex">
            <!-- 왼쪽 장바구니 목록 컨테이너 -->
            <b-container class="w-75 me-auto">
                <!-- 장바구니 목록 컨테이너 상단 전체선택,삭제 -->
                <p class="fs-3 fw-bold">수강 바구니</p>
                <div class="d-flex">
                    <div class="form-check me-auto">
                        <input class="form-check-input" type="checkbox" id="check-all">
                        <label class="form-check-label" for="check-all">
                            전체선택
                        </label>
                    </div>
                    <b-button>선택삭제 <font-awesome-icon :icon="['fas', 'xmark']" /></b-button>
                </div>
                <hr>
                <!-- 장바구니 목록  -->
                <div>
                    <ul class="list-unstyled">
                        <li v-for="(cart, index ) in carts" :key="index">
                            
                        </li>
                    </ul>
                </div>
            </b-container>

            <!-- 오른쪽 결제 내용 박스 -->
            <b-container class="w-25 ms-auto py-5">
                <b-container class="border rounded-3 py-3 mb-2">
                    <p class="fw-bold">구매자 정보</p>
                    <p>닉네임 :</p> {{ userInfo.nickname }}
                    <p>이메일 : </p> {{ userInfo.email }}
                </b-container>
                <b-container class="border rounded-3 py-3">
                    <div class="d-flex">
                        <span class="mb-3 me-auto">선택상품 금액</span>
                        <span class="ms-auto"></span>
                    </div>
                    <div class="d-flex">
                        <span class="mb-3 me-auto">할인 금액</span>
                        <span class="ms-auto"></span>
                    </div>
                    <div class="d-flex mb-3">
                        <span class="me-auto fw-bold fs-5">총 결제 금액</span>
                        <span></span>
                    </div>
                    <b-button class="w-100">결제하기</b-button>
                </b-container>
            </b-container>
        </b-container>
    </div>
</template>

<script>
export default{
    name : 'lecturePayment',
    data() {
        return {
            total : 0, // 수강 바구니에 있는 강의 전체 갯수 (int)
            carts : [], // 수강 바구니 강의 목록 (array)
            paymentList : [], // 결제할 강의 목록
            userInfo : {
                usernum : 0,
                email : '',
                password : '',
                nickname : '',
                regDate : '',
                cls : '',
                urd : '',
                udy : 0

            }, // 사용자 정보
        }
    },
    methods: {
        getCarts(){
            const userNum = localStorage.getItem();
            this.$axiosSend('get','/api/lect/getCart',{
                userNum : userNum
            })
        }
    },
    created() {
        
    },
    mounted() {
        
    },
}

</script>