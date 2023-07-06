<template>
    <div class = "d-flex">
        <div class = "writeMain">
            <CommCategory></CommCategory>
        </div>
        <div class="freeboard-write" id="freeboard-write">
            <div class="justify-content-start text-start"  id="freeboard-detail-body">
                <h2 class=" mb-3 fw-bold free-detail-title" id="free-detail-title">
                    {{ free.title }}
                </h2>
                <div id="freeboard-userinfo">
                    <p id="freeboard-userid">
                        {{ free.userName}}
                    </p>
                    <p id="free-comm">
                        <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.commCnt }}
                    </p>
                    <p id="free-date">
                        {{ free.strCommDate }} 
                    </p>
                </div>
                <hr class="mt-10"/>
                <div id="freeboard-detail-contents">
                    <!-- {{ free.content }} -->
                    <div v-html="free.content"></div>
                </div>
                <div id="free-likeyn">
                    <button id="free-likebtn" @click="freelikeUp(free.commNum)">
                        <font-awesome-icon :icon="['fas', 'heart']"
                            />
                            <text class="fw-bold ms-2 free-detail-likeyn" id="free-detail-likeyn">
                                {{ free.commLikeYn }}
                            </text>
                    </button>    
                </div>
                <hr style="margin-top: 9%;"/>
                <div class="mb-3 freeboard-detail-top" id="freeboard-detail-top">
                    <b-button type="button" class="btn-custom ms-2" id="qna-detail-rewrite" @click="replywrite()">댓글등록</b-button>
                    <b-button type="button" class="btn-custom ms-2 qna-detail-recensell" @click="censells()" id="qna-detail-recensell">취소</b-button>
                    <b-button type="button" class="btn-custom ms-1 free-detail-replybtn" id="free-detail-replybtn" @click="replyopen()">댓글작성</b-button>
                    <b-button type="button" class="bedu-bg-custom-blue btn-custom ms-2 freeboard-detail-editbtn" id="freeboard-detail-editbtn" @click="freeeditPath()">글수정</b-button>
                    <b-button type="button" class="btn-custom ms-2 freeboard-detail-deletebtn" id="freeboard-detail-deletebtn" @click="freedelete()">삭제</b-button>
                </div>
                <div>
                    <p class = "fw-bold fs-5">
                        <font-awesome-icon :icon="['far', 'comment']" />
                        {{replytotal}}개의 댓글이 있습니다.
                    </p>
                </div>
                <div>
                    <div class="free-detail-replywrite" id="free-detail-replywrite" style="display: none;">
                        <h4>댓글을 작성하시오</h4>
                        <textarea class="form-control col-sm-5 qna-detail-replycontent" rows="5" id="qna-detail-replycontent" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"/>
                    </div>
                    <div v-for="reply in replylist" :key="reply.replyNum" class="free-detail-replylist" id="free-detail-replylist">
                        <div class="d-flex mb-3 mt-4">
                            <div class="freeReplyName">
                                {{ reply.userName }}
                            </div>
                            <div class="freeReplyDate">
                                {{ reply.strReplyDate }}
                            </div>
                        </div>
                        <div class="freeReplycontent">
                            {{ reply.content }}
                        </div>
                        <hr/>
                        <div id="freeReplydelbtn" v-if="replybtneq(reply.userName) == 1">
                            <b-button type="button" id="free-reply-delb" @click="replydelete(reply.replyNum, reply.userName)">댓글삭제</b-button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import CommCategory from '@/components/CommCategory.vue';
import router from '@/router';
import '@/assets/css/freeBoardStyle.css';
export default{
    
    data() {
        return {
            result : 0,
            replylist:[],
            userlist:[],
            likenum:0,
            replytotal:0,
            username:'',
            userNickName:'',

            form: {
                commNum:0,
                replyNum: 0,
                replyDate: '',
                userName:'',
                regDate: '',
                regId: '',
            },

            free : {
                commNum:0,
                title : '',
                content : '',
                userName : '',
                strCommDate:'',
                userId:'',
                commCnt : 0,
                commLikeYn : 0,
            }
        }
    },

    components:{
            CommCategory
        },


    mounted() {
        this.userNickName =this.$store.getters.getNickname;
        this.form.regId = this.$store.getters.getEmail;
        const cnum = this.$route.params.num;
        this.freeRead(cnum);
        this.replygetTotal(cnum);
        this.replyread(cnum);
        
    },

    created() {
        this.userNickName =this.$store.getters.getNickname;
    },

    methods: {

        nicknameEquals(nickName){
                                           //게시글 닉네임값과 현제 로그인된 닉네임을 가져와 값이 같은지 다른지 비교                                                              //비교 결과에 따라 수정및 삭제 버튼 노출(같은면 노출, 다르면 댓글버튼만 노출)                                                                       
            
            if(this.userNickName === null || this.userNickName ===""){
                document.getElementById("free-detail-replybtn").style.display="none";
            }

            if(this.userNickName !== nickName){
                    
                document.getElementById("freeboard-detail-editbtn").style.display="none";
                document.getElementById("freeboard-detail-deletebtn").style.display="none";
            }
        },

        replygetTotal(cnum){
            
            this.$axiosSend('get','/api/reply/replyTotal', {
                num: cnum,
            })
            .then(res => {
                this.replytotal = res.data;
            })
        },

        freeRead(commnum){ // 게시글 데이터 조회
           
            this.$axiosSend('get','/api/freBd/detail',{
                    num : commnum,
            })
            .then(response=>{
                this.free = response.data;
                this.free.strCommDate = this.freeDateTime(this.free.strCommDate);
                this.nicknameEquals(this.free.userName);
            })
            .catch((error)=>{
                this.$swal('Error', '게시글이 정상적으로 조회되지 않았습니다.', error);
            })
        },

        replybtneq(username){
            if(this.userNickName === username){
                return 1;
            }else{
                return 0;
            }
        },

        replyread(commnum) {
            if(commnum === 0 || commnum === null){
                return;
            }
            this.$axiosSend('get', '/api/reply/getreply', {
                commNum: commnum
            }).then(res => {
                this.replylist = res.data;
            })
            .catch((error) => {
                console.log(error);
            })
        },


        freedelete() {

            if(this.userNickName === null || this.userNickName ===""){
                this.$swal('로그인을 해주세요.', 'success');
                router.push({
                    name: "login"
                })
                return;
            }

            this.$axiosSend('get','/api/freBd/delete', {
                    num: this.free.commNum,
            })
                .then(res => {
                    if (res.data === 1) {
                        this.$swal('Success', '글삭제완료!', 'success'),
                            router.push({
                                name: "freeBoard"
                            })

                    }
                })
                .catch(error => {
                    this.$swal('Error', '게시글이 정상적으로 삭제되지 않았습니다.', error);
                })
        },

        replywrite(){
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
                this.form.commNum = this.free.commNum;
                this.form.userName = this.userNickName;
                
                var commNum = this.form.commNum;

                this.$axiosSend('post', '/api/reply/write', this.form)
                .then(res=>{
                    if(res.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        this.censells();
                        this.replyread(commNum);
                        this.replygetTotal(commNum);
                    }else{
                        this.$swal('Success','작성실패!','success')
                    }
                }
                )
                .catch((error)=>{
                    this.$swal('Error','댓글이 정상적으로 작성되지 않았습니다', error)
                })

        },

        replydelete(replyNum, userName){
            
            if(this.userNickName === null || this.userNickName ===""){
                this.$swal('로그인을 해주세요.', 'success');
                router.push({
                    name: "login"
                })
                return;
            }

            if(userName !== this.userNickName){
                this.$swal('댓글은 본인 글만 삭제 가능합니다!', 'success');
                return;
            }else{
                
                this.$axiosSend('get','/api/reply/replydelete', {
                    rnum: replyNum
                })
                .then(res => {
                    
                    if(res.data ===1){
                        this.$swal('Success', '댓글삭제가 완료 되었습니다.', 'success');
                        this.replyread(this.free.commNum);
                        this.replygetTotal(this.free.commNum);
                        return;
                    }else{
                        this.$swal('error', '댓글삭제실패!', 'error');
                        this.replyread(this.free.commNum);
                        this.replygetTotal(this.free.commNum);
                        return;
                    }    
                })
                .catch(error => {
                    this.$swal(error, '댓글삭제실패!', 'error');
                })
            }
        },

        freeeditPath(){
            router.push({
                name: 'freeBoardEdit', 
                params:{
                    num :this.free.commNum
                }
            })
                
        },


        freelikedown(){
                
            this.$axiosSend('get','/api/free/likeDown', {
                    num : this.free.commNum,
                    userName : this.userNickName,
                    likebdnum : this.likenum,
            })
            .then(res => {
               
                if(res.data === 1){
                    this.free.commLikeYn--;
                    return;
                }else if(res.data === 0){
                    return;
                }    
            })
            .catch(error => {
                alert(error);
            })
        },

        freelikeUp(cnum){
            
            if(this.userNickName === null || this.userNickName===""){
                this.$swal('로그인을 해주세요.');
                return;
            }

            if(this.free.userName === this.userNickName){
                return;
            }

            var regid = this.form.regId;

            this.$axiosSend('get','/api/free/likeUp', {
                    num: cnum,
                    regId : regid,
                    userName : this.userNickName
            })
            .then(res => {
                
                if(res.data.result === 1){                      //기존 아이디좋아요 없음

                    this.likenum = res.data.likenum;             //테이블의 LIKE_NUM
                    this.free.commLikeYn++;
                    return;
                }else if(res.data.result === 0){                //기존 아이디좋아요 있음

                    this.likenum = res.data.likenum;

                    this.freelikedown();
                    return;
                }    
            })
            .catch(error => {
                alert(error);
            })
        },                

        freeBoardpath(){
            router.push({
                name: 'freeBoard', 
            })
        },

        freeDateTime(value) {
                // value는 날짜 값입니다
                const now = new Date();
                const date = new Date(value);

                const diffInMilliseconds = now - date;
                const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
                const diffInMinutes = Math.floor(diffInSeconds / 60);
                const diffInHours = Math.floor(diffInMinutes / 60);
                const freeDays = Math.floor(diffInHours / 24);
                
                if (freeDays > 0) {
                    return `${freeDays}일 전`;
                } else if (diffInHours > 0) {
                    return `${diffInHours}시간 전`;
                } else if (diffInMinutes > 0) {
                    return `${diffInMinutes}분 전`;
                } else {
                    return '방금 전';
                }
        },

        replyopen(){
            document.getElementById("free-detail-replybtn").style.display="none";
            document.getElementById("qna-detail-rewrite").style.display="inline";
            document.getElementById("free-detail-replywrite").style.display="block";
            document.getElementById("freeboard-detail-editbtn").style.display="none";
            document.getElementById("freeboard-detail-deletebtn").style.display="none";
            document.getElementById("qna-detail-recensell").style.display="inline";
            this.form.content = "";
            // for(var i=0; this.replylist.length; i++){
            //     console.log('성');
            //     if(this.userNickName !== this.replylist[i].userName){
            //         document.getElementById("freeReplydelbtn").style.display="none";
            //     }
            // }
            
        },

        censells(){
            document.getElementById("free-detail-replybtn").style.display="inline";
            document.getElementById("qna-detail-rewrite").style.display="none";
            document.getElementById("free-detail-replywrite").style.display="none";
            document.getElementById("freeboard-detail-editbtn").style.display="inline";
            document.getElementById("freeboard-detail-deletebtn").style.display="inline";
            document.getElementById("qna-detail-recensell").style.display="none";
            if(this.free.userName === this.userNickName){
                document.getElementById("free-detail-replybtn").style.display="inline";
                document.getElementById("freeboard-detail-editbtn").style.display="inline";
                document.getElementById("freeboard-detail-deletebtn").style.display="inline";
                
                return;
            }else{
                
                document.getElementById("freeboard-detail-editbtn").style.display="none";
                document.getElementById("freeboard-detail-deletebtn").style.display="none";
                return;
            }
        },


    },
}
</script>