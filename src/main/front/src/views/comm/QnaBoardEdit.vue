<template>
    <b-container class="w-50">
        <h1>질문 / 답변</h1>
      <b-form @submit="edit()">
          <input type="hidden" v-model="form.comm_num" ref="qna_bd_num"/>
          <b-form-input class="my-5" ref="title" v-model="form.title"></b-form-input>
          <b-form-textarea class="form-control col-sm-5" rows="5" ref="content" v-model="form.content"></b-form-textarea>
          <b-container class="my-3 justify-content-md-end d-md-flex">
              <b-button class="" type="reset">취소</b-button>
              <b-button type="submit" class="btn-custom ms-2">수정</b-button>
          </b-container>
      </b-form>
      <b-button :to="'/comm/qna'">목록</b-button>
    </b-container>
  </template>

<script>
import router from '@/router';
export default{

    data(){
        return {
            form:{
                qna_bd_num: 0,  
                title: '',
                content: '',
            }
        }
    },

    mounted() {
        // console.log(this.$route.params.num)
        const qnum = this.$route.params.num;
        this.qnadetail(qnum);
     
    },

    methods: {

        qnadetail(num){ // 게시글 데이터 조회
            console.log('받아온번호:', num);
            this.$axiosSend('get','/api/qna/qnaDetail',{
                    num : num,
            })
            .then(response=>{
                this.form = response.data;
            })
            .catch((error)=>{
                alert(error);
            })
        },

        edit(){

          const form = new FormData();

          form.append("qna_bd_num", this.form.qna_bd_num);
          form.append("title", this.form.title);
          form.append("content", this.form.content);

          alert('게시글을 수정합니다.');
          this.$axiosSend('post','/api/qna/qnaEdit', this.form)
          .then(res => {
              if(res.data === 1){
                this.$swal('Success','수정완료!','success'),
                router.push({
                    name: "qnaBoard"
                })
              }
            }
          )
          .catch((error)=>{
            this.$swal('Error','게시글이 정상적으로 수정되지 않았습니다',error)
          })

        }

    },

}

</script>
<style scoped>
.btn-custom{
  --bs-btn-bg:#3498db !important;
  --bs-btn-border-color : null !important;
}

.btn-custom:hover{
  background-color: #7ebae2 !important;
}


</style>