<template>
    <CommCategory :titleShow="false"></CommCategory>
    <b-container class="w-50 qna-edit" id="qna-edit">
        <h1>질문 / 답변</h1>
        <b-form @submit="edit()">
            <input type="hidden" v-model="form.comm_num" ref="qna_bd_num"/>
            <b-form-input class="my-5 qna-edit-title" ref="title" id="qna-edit-title" v-model="form.title"></b-form-input>
            <b-form-textarea class="form-control col-sm-5 qna-edit-content" id="qna-edit-content" ref="content" v-model="form.content"></b-form-textarea>
            <b-container class="my-3 justify-content-md-end d-md-flex">
                <b-button class="btn-custom ms-2 qna-edit-censell" id="qna-edit-censell" type="reset" :to="'/comm/qna'" >취소</b-button>
                <b-button type="submit" class="btn-custom ms-2 qna-edit" id="qna-edit">수정</b-button>
            </b-container>
        </b-form>
    </b-container>
</template>

<script>
    import CommCategory from '@/components/CommCategory.vue';
    import router from '@/router';
    export default{

        data(){
            return {
                form:{
                    qna_bd_num: 0,  
                    title: '',
                    content: '',
                    // str_qna_date:'',
                    // str_reg_date:'',
                }
            }
        },

        components:{
            CommCategory
        },

        mounted() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "main"
                })
            }
            const qnum = this.$route.params.num;
            this.qnadetail(qnum);
        },

        created() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                alert('로그인을 해주세요.');
                window.history.back();
            }
        },

        methods: {

            qnadetail(num){ // 게시글 데이터 조회
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
                })
                .catch((error)=>{
                    this.$swal('Error','게시글이 정상적으로 수정되지 않았습니다',error)
                })
            }
        },
    }
</script>
