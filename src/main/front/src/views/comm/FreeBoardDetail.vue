<template>
    <div class="container w-75 mt-5 mb-3 freeboard-detail-main" id="freeboard-detail-main">
        <hr>
        <b-container class="justify-content-start text-start">
            <div class="mb-3 freeboard-detail-top" id="freeboard-detail-top">
                <b-button type="button" class="btn-custom ms-1 free-detail-replybtn" id="free-detail-replybtn" @click="replyopen()">댓글작성</b-button>
                <b-button type="submit" class="btn-custom ms-2 freeboard-detail-editbtn" @click="freeeditPath()">글수정</b-button>
                <b-button type="submit" class="btn-custom ms-2 freeboard-detail-deletebtn" @click="freedelete()">삭제</b-button>
            </div>
            <h5>
                {{ free.user_id}}
            </h5>
            {{ free.str_comm_date }} 
            <h2 class="mt-3 mb-5 fw-bold free-detail-title" id="free-detail-title">
                {{ free.title }}
            </h2>
            <div id="freeboard-detail-contents">
                {{ free.content }}
            </div>

            <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2 free-detail-likeyn" id="free-detail-likeyn">
                    {{ free.comm_like_yn }}
                </text>
            </b-container>
            <hr>
        </b-container>
        <br>
        <div class="w-50 free-detail-replywrite" id="free-detail-replywrite" style="display: none;">
            <h4>댓글을 작성하시오</h4>
            <b-form @submit="replywrite()">
                <b-form-textarea class="form-control col-sm-5 qna-detail-replycontent" rows="5" id="qna-detail-replycontent" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"></b-form-textarea>
                <b-container class="my-3 justify-content-md-end d-md-flex qna-detail-replycont" id="qna-detail-replycont">
                    <b-button type="reset" class="btn-custom ms-2" id="qna-detail-replycont">취소</b-button>
                    <b-button type="button" class="btn-custom ms-2 qna-detail-recensell" @click="censells()" id="qna-detail-recensell">닫기</b-button>
                    <b-button type="submit" class="btn-custom ms-2" id="qna-detail-rewrite">댓글등록</b-button>
                </b-container>
            </b-form>
        </div>
        <div>
            <div v-for="reply in replylist" :key="reply.replyNum" class="free-detail-replylist" id="free-detail-replylist">
                <span>
                    <h5>
                        {{ reply.userId }}
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
    
</template>

<script>
import router from '@/router';

export default{
    
    data() {
        return {
            result : 0,
            replylist:[],
            form: {
                commNum:0,
                replyNum: 0,
                userId:'test@bedu.com',
                content: '',
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
                user_id : '',
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
        this.path(num);
    },

    methods: {

        freeRead(commnum){ // 게시글 데이터 조회
            // console.log('번호:', num);
            this.$axiosSend('get','/api/freBd/detail',{
                    num : commnum,
            })
            .then(response=>{
                this.free = response.data;
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
            })
            .catch((error) => {
                console.log(error);
            })
        },

        censells(){
            document.getElementById("free-detail-replywrite").style.display="none";
            document.getElementById("free-detail-replybtn").style.display="block";
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
                form.append("userId",this.form.userId);
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
            document.getElementById("free-detail-replywrite").style.display="block";
        },

        path(commnum){
            this.result = commnum;
        },

    },
}
</script>
<style>

</style>