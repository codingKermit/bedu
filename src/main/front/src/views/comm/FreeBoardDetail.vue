<template>
    <div class="container w-75 mt-5 mb-3 freeboard-detail-main" id="freeboard-detail-main">
        <b-container class="justify-content-start text-start">
            <h2 class="pt-5 mb-3 fw-bold free-detail-title" id="free-detail-title">
                {{ free.title }}
            </h2>
            <div id="freeboard-userinfo">
                <p id="freeboard-userid">
                    {{ free.user_name}}
                </p>
                <p id="free-comm">
                    <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}
                </p>
                <p id="free-date">
                    {{ free.str_comm_date }} 
                </p>
            </div>
            <hr class="mt-0"/>
            <div id="freeboard-detail-contents">
                {{ free.content }}
            </div>
            <div id="free-likeyn">
                <button id="free-likebtn" @click="freelikeUp(free.comm_num)">
                    <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                        <text class="fw-bold ms-2 free-detail-likeyn" id="free-detail-likeyn">
                            {{ free.comm_like_yn }}
                        </text>
                </button>    
            </div>
            <hr  />
            <div>
                <div class="w-50 mb-5 free-detail-replywrite" id="free-detail-replywrite" style="display: none;">
                    <h4>댓글을 작성하시오</h4>
                    <input class="form-control col-sm-5 qna-detail-replycontent" rows="5" id="qna-detail-replycontent" v-model="form.content" placeholder="내용을 작성해주세요" ref="content" style="width: 200%; height: 100px;"/>
                </div>
                <div>
                    <div v-for="reply in replylist" :key="reply.replyNum" class="free-detail-replylist" id="free-detail-replylist">
                        <span>
                            <h5>
                                {{ reply.userName }}
                            </h5>
                            <div>
                                <h5>
                                    {{ reply.content }}
                                </h5>
                            </div>
                            <h5>
                                {{ reply.strReplyDate }}
                            </h5>
                        </span>
                    </div>
                </div>
            </div>
            <div class="mb-3 freeboard-detail-top" id="freeboard-detail-top">
                <b-button type="button" class="btn-custom ms-2" id="qna-detail-rewrite" @click="replywrite()">댓글등록</b-button>
                <b-button type="button" class="btn-custom ms-2 qna-detail-recensell" @click="censells()" id="qna-detail-recensell">취소</b-button>
                <b-button type="button" class="btn-custom ms-1 free-detail-replybtn" id="free-detail-replybtn" @click="replyopen()">댓글작성</b-button>
                <b-button type="button" class="btn-custom ms-2 freeboard-detail-editbtn" id="freeboard-detail-editbtn" @click="freeeditPath()">글수정</b-button>
                <b-button type="button" class="btn-custom ms-2 freeboard-detail-deletebtn" id="freeboard-detail-deletebtn" @click="freedelete()">삭제</b-button>
            </div>
        </b-container>
        <br>
        
    </div>
    
</template>

<script>
import router from '@/router';
import '@/assets/css/freeBoardStyle.css';
export default{
    
    data() {
        return {
            result : 0,
            replylist:[],
            userlist:[],
            form: {
                commNum:0,
                replyNum: 0,
                replyDate: '',
                faqNum: 0,
                rwNum: 0,
                lecturenum: 0,
                regDate: '',
                regId: '',
            },
            free : {
                comm_num:0,
                title : '',
                content : '',
                user_name : '',
                str_comm_date:'',
                comm_cnt : 0,
                comm_like_yn : 0,
            }
        }
    },

    mounted() {
        const num = this.$route.params.num;
        this.freeRead(num);
        this.replyread(num);
        this.getUserId();
        this.path(num);
    },

    methods: {

        getUserId(){
            const nickname = this.$store.getters.getNickname;
            this.$axiosSend('get', '/api/free/getUserId', {
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

        freeRead(commnum){ // 게시글 데이터 조회
            // console.log('번호:', num);
            this.$axiosSend('get','/api/freBd/detail',{
                    num : commnum,
            })
            .then(response=>{
                this.free = response.data;
                const nickname = this.$store.getters.getNickname;
                this.free.user_name = nickname;
            })
            .catch((error)=>{
                this.$swal('Error', '게시글이 정상적으로 조회되지 않았습니다.', error);
            })
        },

        replyread(commnum) {
            if(commnum === 0 || commnum === null){
                return;
            }
            this.$axiosSend('get', '/api/reply/getreply', {
                commNum: commnum
            }).then(res => {
                this.replylist = res.data;
                console.log('리스트즈:',this.replylist);
            })
            .catch((error) => {
                console.log(error);
            })
        },


        freedelete() {
            alert('게시글을 삭제합니다.');
            this.$axiosSend('get','/api/freBd/delete', {
                    num: this.free.comm_num,
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

            if(this.form.content == null || this.form.content == ""){
                    this.$swal({
                        title :'warning!',
                        text :"내용을 입력하세요",
                        type :'warning',
                        icon : 'warning',
                    })
                    return;
                }
                this.form.commNum = this.free.comm_num;
                var commNum = this.form.commNum;
                
                const form = new FormData();
                form.append("userName",this.form.userName);
                form.append("commNum",this.form.commNum);
                form.append("content",this.form.content);

                this.$axiosSend('post', '/api/reply/write', this.form)
                .then(res=>{
                    if(res.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        this.censells();
                        this.replyread(commNum);
                    }else{
                        this.$swal('Success','작성실패!','success')
                    }
                }
                )
                .catch((error)=>{
                    this.$swal('Error','댓글이 정상적으로 작성되지 않았습니다', error)
                })

        },
        freeeditPath(){
            router.push({
                name: 'freeBoardEdit', 
                params:{
                    num :this.free.comm_num
                }
            })
                
        },
        freeBoardpath(){
            router.push({
                name: 'freeBoard', 
            })
        },

        replyopen(){
            document.getElementById("free-detail-replybtn").style.display="none";
            document.getElementById("qna-detail-rewrite").style.display="inline";
            document.getElementById("free-detail-replywrite").style.display="block";
            document.getElementById("freeboard-detail-editbtn").style.display="none";
            document.getElementById("freeboard-detail-deletebtn").style.display="none";
            document.getElementById("qna-detail-recensell").style.display="inline";
        },

        censells(){
            document.getElementById("free-detail-replybtn").style.display="inline";
            document.getElementById("qna-detail-rewrite").style.display="none";
            document.getElementById("free-detail-replywrite").style.display="none";
            document.getElementById("freeboard-detail-editbtn").style.display="inline";
            document.getElementById("freeboard-detail-deletebtn").style.display="inline";
            document.getElementById("qna-detail-recensell").style.display="none";
        },

        freelikeUp(commnum){
            this.$axiosSend('get','/api/freBd/likeUp', {
                num: commnum,
            })
            .then(res => {
                if(res.data === this.free.comm_num){
                    this.free.comm_num++;
                }    
            })
            .catch(error => {
                alert(error);
            })
        },

        path(commnum){
            this.result = commnum;
        },

    },
}
</script>