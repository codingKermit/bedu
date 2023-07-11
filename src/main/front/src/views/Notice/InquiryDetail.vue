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
                    <!-- {{ qna.content }}
              <div v-html=""></div> -->
                    <div v-html="inquiry.content"></div>
                </div>
                <hr style="margin-top: 9%;" />
                <!--                 <div class="mb-3 qna-detail-btns" id="qna-detail-btns">
                  <b-button type="button" class="btn-custom ms-2 qnaboard-detail-rewrite" @click="answrite()"
                      id="qnaboard-detail-rewrite">답글등록</b-button>
                  <b-button type="button" class="btn-custom ms-2 qnaboard-detail-recensell" @click="censells()"
                      id="qnaboard-detail-recensell">취소</b-button>
                  <b-button type="button" class="btn-custom ms-1 qnaboard-detail-replybtn" id="qnaboard-detail-replybtn"
                      @click="ansopen()">답글작성</b-button>
                  <b-button type="button" class="btn-custom ms-2 qnaboard-detail-deletebtn" id="qnaboard-detail-deletebtn"
                      @click="inquirydelete(qna.qnaBdNum)">삭제</b-button>
              </div> -->
            </div>
            <div>
                <p class="fw-bold fs-5">
                    <font-awesome-icon :icon="['far', 'comment']" />
                    {{ anstotal }}개의 답글이 있습니다.
                </p>
            </div>
        </div>
    </div>
  </template>
  
  <script>
  import CscCategory from '@/components/CscCategory.vue';
  import router from '@/router';
  import axios from 'axios';
  import '@/assets/css/CscViewStyle.css';
  
  export default {
    data() {
        return {
            vocNum:0,
            inquiry: {
                vocNum: 0,
                title: '',
                content: '',
                userName: '',
  
            },
            params: this.$route.params.num,
        };
    },
  
    components: {
        CscCategory
    },
  
    mounted() {
        const vocNum = this.$route.params.num; //현재 라우트의 파라미터중 num을 가져와 변수 qnanum에 할당
    },
  
    created() {
        this.fetchInquiryDetail();
    },
  
    methods: {
        fetchInquiryDetail() {
  
            this.$axiosSend('get', '/api/inquiry/inquiryDetail')
                .then(res => {
                    this.inquiry = res.data;
                })
                .catch(error => {
                    console.log(error);
                });
        },
    },
  };
  </script>