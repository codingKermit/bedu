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
                    {{ qna.user_name}}
                </p>
                <p id="qna-comm">
                    <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                </p>
                <p id="qna-date">
                    {{ qna.str_qna_date }} 
                </p>
            </div>
            <hr class="mt-10">
            <div id="qna-detail-contents">
                <!-- {{ qna.content }} -->
                <div v-html="qna.content"></div>
            </div>
            <div id="qna-likeyn">
                <button id="qna-likebtn" @click="qnalikeUp(qna.qna_bd_num)">
                    <font-awesome-icon :icon="['fas', 'thumbs-up']"/> 
                        <text class="fw-bold ms-2 qna-detail-likeyn" id="qna-detail-likeyn">
                            {{ qna.qna_like_yn }}
                        </text>
                </button>    
            </div>
            <hr style="margin-top: 9%;"/>
            <div class="mb-3 qna-detail-btns" id="qna-detail-btns">
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-rewrite"  @click="answrite()" id="qnaboard-detail-rewrite">답글등록</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-recensell" @click="censells()" id="qnaboard-detail-recensell">취소</b-button>
                <b-button type="button" class="btn-custom ms-1 qnaboard-detail-replybtn" id="qnaboard-detail-replybtn" @click="ansopen()">답글작성</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-editbtn" id="qnaboard-detail-editbtn" @click="qnaeditPath()">글수정</b-button>
                <b-button type="button" class="btn-custom ms-2 qnaboard-detail-deletebtn" id="qnaboard-detail-deletebtn" @click="qnadelete()">삭제</b-button>
            </div>
            <div>
                <p class = "fw-bold fs-5">
                    <font-awesome-icon :icon="['far', 'comment']" />
                    개의 댓글이 있습니다.
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
                likenum:0,
                username:'',
                userNickName:'',
                userlist:[],
                likeok:false,
                form:{
                    ansBdNum:0,
                    ansDate:'',
                    qsBdNum:0,
                    regDate:'',
                    regId:'',
                },
                qna:{
                    qna_bd_num:0,
                    user_name : '',
                }
            }
        },

        components:{
            CommCategory
        },

        mounted() {
            const qnanum = this.$route.params.num;
            this.userNickName =this.$store.getters.getNickname;
            this.qnaRead(qnanum);
            this.ansread(qnanum);
            this.getUserId();
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

            getUserId(){
                const nickname = this.$store.getters.getNickname;
                this.$axiosSend('get', '/api/qna/getUserId', {
                    userName: nickname
                }).then(res => {
                    this.userlist = res.data;
                    for(var i=0; i< this.userlist.length; i++){
                        this.form.userName = this.userlist[i].user_id;
                    }
                    
                    if(this.userNickName !== this.qna.user_name){
                        
                        document.getElementById("qnaboard-detail-editbtn").style.display="none";
                        document.getElementById("qnaboard-detail-deletebtn").style.display="none";
                    }else{
                        document.getElementById("qnaboard-detail-replybtn").style.display="none";
                    }
                })
                .catch((error) => {
                    console.log(error);
                })

                this.$axiosSend('get', '/api/qna/getUserId', {
                    userName: nickname
                }).then(res => {
                    this.userlist = res.data;
                    for(var i=0; i< this.userlist.length; i++){
                        this.form.userName = this.userlist[i].user_id;
                    }
                })
                .catch((error) => {
                    console.log(error);
                })

            },

            qnaRead(qnanum){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/qna/qnaDetail',{
                        num : qnanum,
                })
                .then(res=>{
                    this.qna = res.data;
                    this.qna.str_qna_date = this.qnaDateTime(this.qna.str_qna_date);
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

            qnalikedown(){
            
                this.$axiosSend('get','/api/qna/likeDown', {
                        num : this.qna.qna_bd_num,
                        userName : this.userNickName,
                        likebdnum : this.likenum,
                })
                .then(res => {
                    
                    if(res.data === 1){
                        this.qna.qna_like_yn--;
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
                var regid = this.form.userName;

                this.$axiosSend('get','/api/qna/likeUp', {
                        num: qnum,
                        regId : regid,
                        userName : this.userNickName
                })
                .then(res => {
                    
                    if(res.data.result === 1){                      //기존 아이디좋아요 없음

                        this.likenum = res.data.likenum;
                        this.likeok = res.data.likes;
                        this.userNickName = res.data.email;
                        this.qna.qna_like_yn++;
                    }else if(res.data.result === 0){                //기존 아이디좋아요 있음
                        this.likenum = res.data.likenum;
                        this.likeok = res.data.likes;
                        this.userNickName = res.data.email;
                        if(this.likeok === true){
                            
                            this.qnalikedown();
                            return;
                        }
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
                this.form.content = "";
            },

        },
    }
</script>
