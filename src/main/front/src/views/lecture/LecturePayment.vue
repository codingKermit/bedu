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
                        {{ paymentList.length }}  / {{ carts.length }}
                    </div>
                    <b-button @click="removeFromCart(paymentList)">
                        선택삭제 <font-awesome-icon :icon="['fas', 'xmark']" />
                    </b-button>
                </div>
                <hr>
                <!-- 장바구니 목록  -->
                <div>
                    <ul class="list-unstyled">
                        <li v-for="(cart, index ) in carts" :key="index">
                            <div class="d-flex mb-3 border p-1">
                                <div class="form-check">
                                    <input class="form-check-input cart-list" type="checkbox" :id="'cart-check-'+index" 
                                    v-model="paymentList" :value="cart"
                                    >
                                </div>
                                <div class="cart-payment-thumbnail-container">
                                    <b-img :src="cart.thumbnail" class="w-100 h-100"></b-img>
                                </div>
                                <div class="w-75 ms-3 row">
                                    <span></span>
                                    <span class="d-block">{{ cart.title }}</span>
                                    <span class="text-secondary">{{ cart.teacher }}</span>
                                </div>
                                <div class="cart-payment-delete-xmark">
                                    <font-awesome-icon :icon="['fas', 'xmark']" role="button"
                                    @click="removeFromCart([cart])"
                                    />
                                </div>
                                <div class="d-flex cart-payment-price-container">
                                    <div class="align-self-center d-flex px-4">
                                        <span class="text-danger fw-bold">
                                            {{ cart.price }} 
                                        </span>
                                        &nbsp;원
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </b-container>

            <!-- 오른쪽 결제 내용 박스 -->
            <b-container class="w-25 ms-auto py-5">
                <b-container class="border rounded-3 py-3 mb-2">
                    <p class="fw-bold">구매자 정보</p>
                    <p>
                        <span>닉네임 :</span> {{ userInfo.nickname }}
                    </p>
                    <p>
                        <span>이메일 : </span> {{ userInfo.email }}
                    </p>
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
import '@/assets/css/lectureStyle.css';

export default{
    name : 'lecturePayment',
    data() {
        return {
            total : 0, // 수강 바구니에 있는 강의 전체 갯수 (int)
            carts : [], // 수강 바구니 강의 목록 (array)
            paymentList : [], // 결제할 강의 목록
            userInfo : {
                userNum : this.$store.state.usernum,
                email : '',
                password : '',
                nickname : this.$store.state.nickname,
                regDate : '',
                cls : '',
                urd : '',
                udy : 0

            }, // 사용자 정보
        }
    },
    methods: {
        getCarts(){ /** 장바구니 조회 */
            this.$axiosSend('get','/api/lect/getCart',{
                userNum : this.$store.state.usernum
            })
            .then((res)=>{
                this.carts = res.data.item;
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        removeFromCart(item){ /** 장바구니에서 삭제 메서드 */
            console.log(item)
            this.$axiosSend('post','/api/lect/removeFromCart'
            ,{
                userNum : this.userInfo.userNum,
                list : item,
                // list : encodeURIComponent(JSON.stringify(item)),
            }
            )
            .then((res)=>{
                console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        selectAll(){ /** 전체 선택  */

        }
    },
    created() {
        this.getCarts();
    },
    mounted() {
        
    },
}

</script>