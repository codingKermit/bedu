<template>
    <div class="container w-75 mt-5 mb-3 qna-detail-main" id="qna-detail-main">
        <div class="mb-3 qna-detail-top" id="qna-detail-top">
        </div>
        <hr>
        <b-container class="justify-content-start text-start qna-detail-body" id="qna-detail-body">
            <h5>
                {{ qna.user_id}}
            </h5>
            {{ qna.str_qna_date }} 
            <h2 class="mt-3 mb-5 fw-bold qna-detail-title" id="qna-detail-title">
                {{ qna.title }}
            </h2>
            <div id="qna-detail-contents">
                {{ qna.content }}
            </div>
            <b-container class="ms-auto text-end qna-detail-textend" id="qna-detail-textend">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2 qna-detail-likeyn" id="qna-detail-likeyn">
                    {{ qna.qna_like_yn }}
                </text>
            </b-container>
            <b-button type="submit" class="btn-custom ms-2 qna-detail-editpath" id="qna-detail-editpath" @click="qnaeditPath()">글수정</b-button>
            <b-button type="submit" class="btn-custom ms-2 qna-detail-deletepath" id="qna-detail-deletepath" @click="qnadelete(qna.qna_bd_num)">삭제</b-button>
            <div class="qna-detail-btncontent" id="qna-detail-btncontent">
                <div class="qna-detail-openbtn" id="qna-detail-openbtn">
                    <b-button type="button" class="btn-custom ms-1 qna-detail-replybtn" id="qna-detail-replybtn" @click="replyopen()">답글작성</b-button>
                </div>
            </div>
        </b-container>
        <br>

        <div class="w-50 qna-detail-replywrite" id="qna-detail-replywrite" style="display: none;">
            <div>
                <h4>답글을 작성하시오</h4>
            </div>
            <div>
                <b-form @submit="answrite()">
                    <b-form-textarea class="form-control col-sm-5 qna-detail-replycontent" rows="5" id="qna-detail-replycontent" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"></b-form-textarea>

                    <b-container class="my-3 justify-content-md-end d-md-flex qna-detail-replycont" id="qna-detail-replycont">
                        <b-button type="reset" class="btn-custom ms-2" id="qna-detail-replycont">취소</b-button>
                        <b-button type="button" class="btn-custom ms-2 qna-detail-recensell" @click="censells()" id="qna-detail-recensell">닫기</b-button>
                        <b-button type="submit" class="btn-custom ms-2" id="qna-detail-rewrite">답변등록</b-button>
                    </b-container>
                </b-form>
            </div>
        </div>
        
        <div>
            <div v-for="ans in anslist" :key="ans.ansBdNum" class="qna-detail-anslist" id="qna-detail-anslist">
                <span>
                    <h5>
                        {{ ans.userId }}
                    </h5>
                    <div>
                        <h5>
                            {{ ans.content }}
                        </h5>
                    </div>
                    <h5>
                        {{ ans.strAnsDate }}
                    </h5>
                    <h5>
                        {{ ans.ansLikeCnt }}
                    </h5>
                </span>
            </div>
        </div>
                
    </div>
</template>

<script>
    import router from '@/router';

    export default{
        
        data() {
            return {
                qnum : 0,
                anslist:[],
                form:{
                    ansBdNum:0,
                    userId:'test@bedu.com',
                    content:'',
                    ansDate:'',
                    qsBdNum:0,
                    regDate:'',
                    regId:'',
                },
                qna:{
                    qna_bd_num:0,
                    title : '',
                    content : '',
                    user_id : '',
                    str_qna_date : '',
                    qna_cnt : 0,
                    qna_like_yn : 0,
                }
            }
        },

        mounted() {
            const qnanum = this.$route.params.num;
            this.path(qnanum);
            this.qnaRead(qnanum);
            this.ansread(qnanum);
            this.form.ansBdNum = qnanum;
        },

        methods: {

            qnaRead(qnanum){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/qna/qnaDetail',{
                        num : qnanum,
                })
                .then(res=>{
                    this.qna = res.data;
                })
                .catch((error)=>{
                    alert(error);
                })
            },

            //답글 게시글조회
            ansread(qnanum) {

                this.$axiosSend('get', '/api/ans/getans', {
                    qsBdNum: qnanum
                }).then(res => {
                    this.anslist = res.data;
                })
                .catch((error) => {
                    this.$swal('Error', '게시글이 정상적으로 작성되지 않았습니다.', error);
                })
            },

            qnadelete(qnanum) {
                this.$axiosSend('get','/api/qna/qnaDelete', {
                        num: qnanum,
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
                        num :this.qnum,
                    }
                })
                    
            },

            censells(){
                document.getElementById("qna-detail-replywrite").style.display="none";
                document.getElementById("qna-detail-replybtn").style.display="block";
            },

            answrite(){

                if(this.form.content == null || this.form.content == ""){
                    this.$swal({
                        title :'warning!',
                        text :"내용을 입력하세요",
                        type :'warning',
                        icon : 'warning',
                    })
                    return;
                }
                
                this.form.qsBdNum = this.qna.qna_bd_num;
                var qnanum = this.form.qsBdNum;

                this.$axiosSend('post', '/api/ans/write', this.form)
                .then(res=>{
                    if(res.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        this.censells();
                        this.ansread(qnanum);
                    }else{
                        this.$swal('Success','작성실패!','success')
                    }
                }
                )
                .catch((error)=>{
                    this.$swal('Error','답글이 정상적으로 작성되지 않았습니다', error)
                })

            },

            replyopen(){
                document.getElementById("qna-detail-replybtn").style.display="none";
                document.getElementById("qna-detail-replywrite").style.display="block";
            },

            path(qnanum){
                this.qnum = qnanum;
            },

        },
    }
</script>
<style scoped>

    #qna-detail-replylist{
        border:1px solid;
        margin-top:20px;
        border-radius: 20px 20px / 20px 20px;
        text-align: center;
    }

    #qna-detail-btncontent{
        margin-top: 60px;
        display: flex;
    }
    #qna-detail-replywrite{
        float: left;
    }

</style>