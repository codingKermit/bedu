<template>
    <div class="container w-75 mt-5 mb-3 qna-detail-main" id="qna-detail-main">
        <b-container class="justify-content-start text-start qna-detail-body" id="qna-detail-body">
            <h2 class="pt-5 mb-3 fw-bold qna-detail-title" id="qna-detail-title">
                {{ qna.title }}
            </h2>
            <div id="qna-userinfo">
                <p id="qna-userid">
                    {{ qna.user_name}}
                </p>
                <p id="qna-comm">
                    <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                </p>
                <p id="qna-date">
                    {{ qna.str_qna_date }} 
                </p>
            </div>
            <hr class="mt-0"/>
            <div id="qna-detail-contents">
                {{ qna.content }}
            </div>
            <div id="qna-likeyn">
                <button id="qna-likebtn" @click="qnalikeUp(qna.qna_bd_num)">
                    <font-awesome-icon :icon="['fas', 'thumbs-up']"/> 
                        <text class="fw-bold ms-2 qna-detail-likeyn" id="qna-detail-likeyn">
                            {{ qna.qna_like_yn }}
                        </text>
                </button>    
            </div>
            <hr  />
            <div>
                <div class="w-50 mb-5 qna-detail-replywrite" id="qnaboard-detail-replywrite" style="display: none;">
                    <h4>답글을 작성하시오</h4>
                    <input class="form-control col-sm-5 qna-detail-replycontent" rows="5" id="qna-detail-replycontent" v-model="form.content" placeholder="내용을 작성해주세요" ref="content" style="width: 200%; height: 100px;"/>
                </div>
                <div>
                    <div v-for="ans in anslist" :key="ans.ansBdNum" class="qna-detail-replylist" id="qna-detail-replylist">
                        <span>
                            <h5>
                                {{ ans.userName }}
                            </h5>
                            <div>
                                <h5>
                                    {{ ans.content }}
                                </h5>
                            </div>
                            <h5>
                                {{ ans.strAnsDate }}
                            </h5>
                        </span>
                    </div>
                </div>
            </div>
            <div class="mb-3 qna-detail-btns" id="qna-detail-btns">
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-rewrite"  @click="answrite()" id="qnaboard-detail-rewrite">답글등록</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-recensell" @click="censells()" id="qnaboard-detail-recensell">취소</b-button>
                <b-button type="button" class="btn-custom ms-1 qnaboard-detail-replybtn" id="qnaboard-detail-replybtn" @click="ansopen()">답글작성</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-editbtn" id="qnaboard-detail-editbtn" @click="qnaeditPath()">글수정</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-deletebtn" id="qnaboard-detail-deletebtn" @click="qnadelete()">삭제</b-button>
            </div>
                
        </b-container>
        <br>
    </div>
</template>

<script>
    import router from '@/router';
    import '@/assets/css/qnaStyle.css'; 
    export default{
        
        data() {
            return {
                qnum:0,
                anslist:[],
                userlist:[],
                form:{
                    ansBdNum:0,
                    ansDate:'',
                    qsBdNum:0,
                    regDate:'',
                    regId:'',
                },
                qna:{
                    qna_bd_num:0,
                    title : '',
                    content : '',
                    user_name : '',
                    str_qna_date : '',
                    qna_cnt : 0,
                    qna_like_yn : 0,
                }
            }
        },

        mounted() {
            const qnanum = this.$route.params.num;
            this.qnaRead(qnanum);
            this.ansread(qnanum);
            this.getUserId();
            document.getElementById("qnaboard-detail-recensell").style.display="none";
            document.getElementById("qnaboard-detail-rewrite").style.display="none";
            this.form.ansBdNum = qnanum;
        },

        methods: {

            getUserId(){
                const nickname = this.$store.getters.getNickname;
                this.$axiosSend('get', '/api/qna/getUserId', {
                    userName: nickname
                }).then(res => {
                    this.userlist = res.data;
                    for(var i=0; i< this.userlist.length; i++){
                        this.form.userName = this.userlist[i].user_id;
                    }
                    console.log('값:',this.form.userName);
                })
                .catch((error) => {
                    this.$swal('Error', '회원아이디가 정상적으로 불러오지 않았습니다.', error);
                })

            },

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
                    console.log('리스트',res.data);
                    this.anslist = res.data;
                    console.log('확인', this.anslist.userName);
                })
                .catch((error) => {
                    this.$swal('Error', '답글이 정상적으로 조회되지 않았습니다.', error);
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
                        num :this.qna.qna_bd_num,
                })
            },

            qnalikeUp(qnum){
                
                this.$axiosSend('get','/api/qna/likeUp', {
                        num: qnum,
                })
                .then(res => {
                    console.log('값', res.data.nums);
                    if(res.data.nums === this.qna.qna_bd_num){
                        this.qna.qna_like_yn++;
                        this.qna.user_name = res.data.user_name;
                        this.qnum++;
                    }else if(res.data.nums === 0){
                        alert('같은 아이디로는 한번밖에 증가하지 못합니다.');
                        return;
                    }    
                })
                .catch(error => {
                    alert(error);
                })
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

            censells(){
                
                document.getElementById("qnaboard-detail-replybtn").style.display="inline";
                document.getElementById("qnaboard-detail-rewrite").style.display="none";
                document.getElementById("qnaboard-detail-replywrite").style.display="none";
                document.getElementById("qnaboard-detail-editbtn").style.display="inline";
                document.getElementById("qnaboard-detail-deletebtn").style.display="inline";
                document.getElementById("qnaboard-detail-recensell").style.display="none";
            },

            ansopen(){
                document.getElementById("qnaboard-detail-replybtn").style.display="none";
                document.getElementById("qnaboard-detail-rewrite").style.display="inline";
                document.getElementById("qnaboard-detail-replywrite").style.display="block";
                document.getElementById("qnaboard-detail-editbtn").style.display="none";
                document.getElementById("qnaboard-detail-deletebtn").style.display="none";
                document.getElementById("qnaboard-detail-recensell").style.display="inline";
            },

        },
    }
</script>
