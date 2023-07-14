<template>
    <div class="d-flex">
        <div class="cscDetail">
            <CscCategory></CscCategory> 
        </div>
        <div class="csc-detail-main" id="csc-detail-main">
            <div class="justify-content-start text-start csc-detail-body" id="csc-detail-body">
                <h2 class=" fw-bold csc-detail-title" id="csc-detail-title">
                    {{ inquiry.title }}
                </h2>
                <div id="csc-userinfo">
                    <p id="csc-userid">
                        {{ inquiry.userName }}
                    </p>
                    <p id="csc-date">
                        {{ }}
                    </p>
                </div>
                <hr class="mt-10">
                <div id="csc-detail-contents">
                    <div v-html="inquiry.content"></div>
                </div>
                <hr style="margin-top: 9%;" />
            </div>
            <div>
                <p class="fw-bold fs-5">
                    <font-awesome-icon :icon="['far', 'comment']" />
                    {{ inquiry.replyCnt }}개의 답글이 있습니다.
                </p>
            </div>
        </div>
    </div>
  </template>
  
  <script>
  import CscCategory from '@/components/CscCategory.vue';
  import router from '@/router';
  import '@/assets/css/CscViewStyle.css';
  
  export default {

    components: {
        CscCategory
    },
    name : 'inquiryDetail',
    data() {
        return {
            params: parseInt(this.$route.query.vocNum), //CscView에서 넘버를 받아올 때 문, String으로 받아와 져서 parseInt로 형변환
            inquiry: {
                vocNum: null, //삽입되어 있는 숫자들은 null 부터 시작
                title: '',
                userName: '',
                content: '',
                replyCnt: 0, //추가 할 숫자들은 0 부터 시작
            },
        };
    },  
    mounted() {
        this.fetchInquiryDetail(this.params) //return에서 params로 받아 옴

    },
    methods: {
        fetchInquiryDetail(vocNum) {
            this.$axiosSend('get', '/api/inquiry/inquiryDetail', {
                vocNum: vocNum
            })
            .then(res => {
                this.inquiry = res.data;
                console.log(this.inquiry);
            })
            .catch(error => {
                console.log(error); 
            });
        },
    },
  };
  </script>