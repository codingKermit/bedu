<template>
    <div class="container w-75 mt-5 mb-3" id="qna-detail-main">
        <div class="mb-3 qna-detail-top" id="qna-detail-top">
            <h1>질문 / 답변</h1>
        </div>
        <hr>
        <b-container class="justify-content-start text-start qna-detail-body" id="qna-detail-body">
            <h5>
                {{ qna.user_id}}
            </h5>
            {{ qna.date }} 
            <h2 class="mt-3 mb-5 fw-bold qna-detail-title" id="qna-detail-title">
                {{ qna.title }}
            </h2>
            <div id="qna-detail-contents">
                {{ qna.content }}
            </div>

            <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2 qna-detail-likeyn" id="qna-detail-likeyn">
                    {{ qna.qna_like_yn }}
                </text>
            </b-container>
            <b-button type="submit" class="btn-custom ms-2 qna-detail-editpath" id="qna-detail-editpath" @click="qnaeditPath()">글수정</b-button>
            <b-button type="submit" class="btn-custom ms-2 qna-detail-deletepath" id="qna-detail-deletepath" @click="qnadelete()">삭제</b-button>
            <b-button type="button" class="btn-custom ms-2 qna-detail-viewpath" id="qna-detail-viewpath" :to="'/comm/qna'">목록</b-button>
        </b-container>
        <hr>
        <h3>답글을 작성해주세요</h3>
                <b-form @submit="replywrite()">
                    <b-form-textarea class="form-control col-sm-5 qna-write-content" rows="5" id="qna-write-content" v-model="form.content" placeholder="답변내용을 작성해주세요" ref="content"></b-form-textarea>
                    <b-container class="my-3 justify-content-md-end d-md-flex qna-write-contain" id="qna-write-contain">
                        <b-button type="reset" class="btn-custom ms-2 qna-detail-censell" id="qna-detail-censell">취소</b-button>
                        <b-button type="submit" class="btn-custom ms-2 qna-detail-rewrite" id="qna-detail-rewrite">답글작성</b-button>
                    </b-container>
                </b-form>
    </div>
</template>

<script>
    import router from '@/router';

    export default{
        
        data() {
            return {
                result : 0,
                form:{
                    replyNum:0,
                    userId:'',
                    content:'',
                    date:'',
                    faqNum:0,
                    rwNum:0,
                    lecturenum:0,
                    regDate:'',
                    regId:'',
                },
                qna : {
                    title : '',
                    content : '',
                    user_id : '',
                    date : '',
                    qna_cnt : 0,
                    qna_like_yn : 0,
                }
            }
        },

        mounted() {
            const num = this.$route.params.num;
            this.qnaRead(num);
            this.path(num);
            this.form.faqNum = num;
        },

        methods: {

            qnaRead(num){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/qna/qnaDetail',{
                        num : num,
                })
                .then(res=>{
                    this.qna = res.data;
                })
                .catch((error)=>{
                    alert(error);
                })
            },

            replyread(num){
            this.$axiosSend('get', '/api/reply/getreply', {
                num:num
            }).then(res=>{
                this.form = res.data;
            })
            .catch((error)=>{
                this.$swal('Error', '게시글이 정상적으로 작성되지 않았습니다.', error);
            })
        },

            qnadelete() {
                alert('게시글을 삭제합니다.');
                this.$axiosSend('get','/api/qna/qnaDelete', {
                        num: this.result,
                })
                .then(res => {
                    if(res.data ===1){
                    this.$swal('Success', '글삭제완료!', 'success'),
                        router.push({
                            name: "qnaBoard"
                        })
                    }    
                })
                .catch(error => {
                    alert(error);
                })
            },
            qnaeditPath(){
                router.push({
                    name: 'qnaBoardedit', 
                    params:{
                        num :this.result
                    }
                })
                    
            },

            replywrite(){

                if(this.form.content == null || this.form.content == ""){
                    this.$swal({
                        title :'warning!',
                        text :"내용을 입력하세요",
                        type :'warning',
                        icon : 'warning',
                    })
                    return;
                }
                const form = new FormData();
                form.append("faqNum",this.form.faqNum);
                form.append("content",this.form.content);

                console.log('faq번호:', this.form.faqNum);
                console.log('내용:', this.form.content);

                this.$axiosSend('post', '/api/reply/write', this.form)
                .then(res=>{
                    if(res.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        router.push({
                            name: "qnaBoard"
                        })
                    }else{
                        this.$swal('Success','작성실패!','success')
                    }
                }
                )
                .catch((error)=>{
                    this.$swal('Error','답글이 정상적으로 작성되지 않았습니다', error)
                })

            },
            path(num){
                this.result = num;
            },

        },
    }
</script>
<style scoped>
    .category{
        align-items: center;
        display: flex;
    }
    .category::before{
        line-height: 0px;
        height: 1px;
        background : black;
        margin : 0px 10px;
        flex-grow: 1;
        content : "";
    }
    .category::after{
        line-height: 0px;
        height: 1px;
        background : black;
        margin : 0px 10px;
        flex-grow: 6;
        content : "";
    }

    .thisCategory{
        color : #3498db;
    }

    blockquote {
        background-color: skyblue;
        margin : 10px;
    }

</style>