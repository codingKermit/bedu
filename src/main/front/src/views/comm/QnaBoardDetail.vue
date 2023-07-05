<template>
    <div class = "d-flex">
        <div class = "qnaDetail">
            <CommCategory></CommCategory>
        </div>
    <div class="qna-detail-main" id="qna-detail-main">
        <div class="justify-content-start text-start qna-detail-body" id="qna-detail-body">
            <h2 class=" fw-bold qna-detail-title" id="qna-detail-title">
                {{ qna.title }}
            </h2>
            
            <div id="qna-userinfo">
                <p id="qna-userid">
                    {{ qna.userName}}
                </p>
                <p id="qna-comm">
                    <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qnaCnt }}
                </p>
                <p id="qna-date">
                    {{ qna.strQnaDate }} 
                </p>
            </div>
            <hr class="mt-10">
            <div id="qna-detail-contents">
                <!-- {{ qna.content }} -->
                <div v-html="qna.content"></div>
            </div>
            <div id="qna-likeyn">
                <button id="qna-likebtn" @click="qnalikeUp(qna.qnaBdNum)">
                    <font-awesome-icon :icon="['fas', 'heart']"
                            />
                        <text class="fw-bold ms-2 qna-detail-likeyn" id="qna-detail-likeyn">
                            {{ qna.qnaLikeCnt }}
                        </text>
                </button>    
            </div>
            <hr style="margin-top: 9%;"/>
            <div class="mb-3 qna-detail-btns" id="qna-detail-btns">
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-rewrite"  @click="answrite()" id="qnaboard-detail-rewrite">답글등록</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-recensell" @click="censells()" id="qnaboard-detail-recensell">취소</b-button>
                <b-button type="button" class="btn-custom ms-1 qnaboard-detail-replybtn" id="qnaboard-detail-replybtn" @click="ansopen()">답글작성</b-button>
                <b-button type="button" class="bedu-bg-custom-blue btn-custom ms-2 qnaboard-detail-editbtn" id="qnaboard-detail-editbtn" @click="qnaeditPath(qna.qnaBdNum)">글수정</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-deletebtn" id="qnaboard-detail-deletebtn" @click="qnadelete(qna.qnaBdNum)">삭제</b-button>
            </div>
            <div>
                <p class = "fw-bold fs-5">
                    <font-awesome-icon :icon="['far', 'comment']" />
                    {{anstotal}}개의 답글이 있습니다.
                </p>
            </div>
            <div>
                <div class="qna-detail-replywrite" id="qnaboard-detail-replywrite" >
                    <h4>답글을 작성하시오</h4>
                    <textarea id = "qna-detail-replycontent" class="form-control col-sm-5 qna-detail-replycontent" rows="5" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"/>
                </div>
                <div v-for="ans in anslist" :key="ans.ansBdNum" class="qna-detail-replylist">
                    <div class="d-flex mb-3 mt-4">
                        <div  class = "name">
                            {{ ans.userName }}
                        </div>
                        <div  class = "date">
                            {{ ans.strAnsDate }}
                        </div>
                    </div>
                    <div class="qnaReplyContent">
                        {{ ans.content }}
                    </div>
                    <hr/>
                    <div id="qna-detail-replyDelBtn">
                        <b-button type="button" @click="ansdelete(ans.ansBdNum, ans.userName, ans.regId)">답글삭제</b-button>
                    </div>
                </div>
            </div>
        </div>
        <br>
    </div>
</div>
</template>

<script>
    import CommCategory from '@/components/CommCategory.vue';
    import router from '@/router';
    import '@/assets/css/qnaStyle.css'; 
    export default{
        
        data() {
            return {
                qnum:0,
                anslist:[],
                anstotal:0,
                likenum:0,
                username:'',
                userNickName:'',
                userlist:[],
                ansid:'',
                
                form:{
                    ansBdNum:0,
                    ansDate:'',
                    qsBdNum:0,
                    regDate:'',
                    regId:'',
                },
                qna:{
                    qnaBdNum:0,
                    userName : '',
                    regId:''
                }
            }
        },

        components:{
            CommCategory
        },

        mounted() {
            const qnanum = this.$route.params.num;
            this.userNickName =this.$store.getters.getNickname;
            this.form.regId = this.$store.getters.getEmail;
            this.qnaRead(qnanum);
  
            this.ansread(qnanum);
            this.ansgetTotal(qnanum);
            document.getElementById("qnaboard-detail-recensell").style.display="none";
            document.getElementById("qnaboard-detail-rewrite").style.display="none";
            this.form.ansBdNum = qnanum;
        },

        created() {
        },

        methods: {

            getCountAns(){
                this.$axiosSend('get', '/api/ans/count', {

                }).then(res => {
                    this.qsCount = res.data;
                })
                .catch((error) => {
                    this.$swal('Error', '답글이 정상적으로 조회되지 않았습니다.', error);
                })
            },

            nicknameEquals(nickname){
                
                if(this.userNickName === null || this.userNickName ===""){
                    document.getElementById("qnaboard-detail-replybtn").style.display="none";
                }

                if(this.userNickName !== nickname){
                    document.getElementById("qnaboard-detail-editbtn").style.display="none";
                    document.getElementById("qnaboard-detail-deletebtn").style.display="none";
                }

            },

            ansgetTotal(qnanum){
                
                this.$axiosSend('get','/api/ans/ansTotal', {
                        qnaNum: qnanum,
                })
                .then(res => {
                       this.anstotal=res.data;
                })
            },

            qnaRead(qnanum){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/qna/qnaDetail',{
                        num : qnanum,
                })
                .then(res=>{
                    this.qna = res.data;                
                    this.qna.strQnaDate = this.qnaDateTime(this.qna.strQnaDate);
                    this.nicknameEquals(this.qna.userName);
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
                    this.$swal('Error', '답글이 정상적으로 조회되지 않았습니다.', error);
                })
            },

            //게시글 삭제
            qnadelete(qnanum) {

                if(this.userNickName === null || this.userNickName ===""){
                    this.$swal('로그인을 해주세요.', 'success');
                    router.push({
                        name: "login"
                    })
                    return;
                }

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

            qnaeditPath(qnum){              //수정페이지로 이동 (질문글번호)
                router.push({
                    name: 'qnaBoardedit', 
                        num :qnum,
                })
            },

            qnalikedown(){                                                      
            
                this.$axiosSend('get','/api/qna/likeDown', {
                        num : this.qna.qnaBdNum,
                        userName : this.userNickName,
                        likebdnum : this.likenum,
                })
                .then(res => {
                    
                    if(res.data === 1){
                        this.qna.qnaLikeCnt--;
                        return;
                    }else if(res.data === 0){
                        return;
                    }    
                })
                .catch(error => {
                    alert(error);
                })
            },

            qnalikeUp(qnum){
                
                if(this.userNickName === null || this.userNickName===""){
                    this.$swal('로그인을 해주세요.');
                    return;
                }

                if(this.qna.userName === this.userNickName){
                    return;
                }

                var regid = this.form.regId;

                this.$axiosSend('get','/api/qna/likeUp', {
                        num: qnum,                              //질문게시글
                        regId : regid,                          //현제 로그인한 닉네임에해당하는 아이디
                        userName : this.userNickName               
                })
                .then(res => {
                    if(res.data.result === 1){                      //성공값인 result값이 1이 있을 경우 기존 아이디좋아요 증가                
                        this.qna.qnaLikeCnt++;
                        return;
                    }else if(res.data.result === 0){                //실패값인 result 0일 경우 기존 아이디좋아요 감소

                        this.likenum = res.data.likenum;             // 기존 좋아요 증가한게 있을 경우 결과값으로 가져오는 likeNum값()
                        this.qnalikedown();      
                        return;
                    }    
                })
                .catch(error => {
                    alert(error);
                })
            },

            qnaDateTime(value) {
                // value는 날짜 값입니다
                const now = new Date();
                const date = new Date(value);

                const diffInMilliseconds = now - date;
                const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
                const diffInMinutes = Math.floor(diffInSeconds / 60);
                const diffInHours = Math.floor(diffInMinutes / 60);
                const qnaDays = Math.floor(diffInHours / 24);
                
                if (qnaDays > 0) {
                    return `${qnaDays}일 전`;
                } else if (diffInHours > 0) {
                    
                    return `${diffInHours}시간 전`;
                } else if (diffInMinutes > 0) {
                    return `${diffInMinutes}분 전`;
                } else {
                    return '방금 전';
                }
            },

            answrite(){

                if(this.userNickName === null || this.userNickName ===""){
                    this.$swal('로그인을 해주세요.', 'success');
                    router.push({
                        name: "login"
                    })
                    return;
                }

                if(this.form.content == null || this.form.content == ""){
                    this.$swal({
                        title :'warning!',
                        text :"내용을 입력하세요",
                        type :'warning',
                        icon : 'warning',
                    })
                    return;
                }
                
                this.form.qsBdNum = this.qna.qnaBdNum;
                this.form.userName = this.userNickName;
                var qnanum = this.form.qsBdNum;

                this.$axiosSend('post', '/api/ans/write', this.form)
                .then(res=>{
                    if(res.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        this.censells();
                        this.ansread(qnanum);
                        this.ansgetTotal(qnanum);
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
                if(this.qna.userName === this.userNickName){
                    document.getElementById("qnaboard-detail-replybtn").style.display="inline";
                    document.getElementById("qnaboard-detail-deletebtn").style.display="inline";
                    document.getElementById("qnaboard-detail-editbtn").style.display="inline";
                    return;
                }else{
                    document.getElementById("qnaboard-detail-deletebtn").style.display="none";
                    document.getElementById("qnaboard-detail-editbtn").style.display="none";
                }
            },

            ansopen(){
                document.getElementById("qnaboard-detail-replybtn").style.display="none";
                document.getElementById("qnaboard-detail-rewrite").style.display="inline";
                document.getElementById("qnaboard-detail-replywrite").style.display="block";
                document.getElementById("qnaboard-detail-editbtn").style.display="none";
                document.getElementById("qnaboard-detail-deletebtn").style.display="none";
                document.getElementById("qnaboard-detail-recensell").style.display="inline";
                this.form.content = "";
            },

            ansdelete(ansNum, userName, regid){
                
                if(this.userNickName === null || this.userNickName ===""){
                    this.$swal('로그인을 해주세요.', 'success');
                    router.push({
                        name: "login"
                    })
                    return;
                }
                if(userName !== this.userNickName){
                    this.$swal('답글은 본인 글만 삭제 가능합니다!', 'success');
                    return;
                }else{
                    
                    this.$axiosSend('get','/api/ans/ansdelete', {
                        ansBdNum : ansNum
                    })
                    .then(res => {
                        
                        if(res.data ===1){
                            this.$swal('Success', '답글삭제가 완료 되었습니다.', 'success');
                            this.ansread(this.qna.qnaBdNum);
                            this.ansgetTotal(this.qna.qnaBdNum);
                            return;
                        }else{
                            this.$swal('error', '답글삭제실패!', 'error');
                            this.ansread(this.qna.qnaBdNum);
                            return;
                        }    
                    })
                    .catch(error => {
                        this.$swal(error, '답글삭제실패!', 'error');
                    })
                }
            }

        },
    }

</script>
