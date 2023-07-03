<template>
    <div class = "d-flex">
        <div class = "writeMain">
            <CommCategory></CommCategory>
        </div>
            <div class="qna-write" id="qna-write">
                <h2>질문 & 답변</h2>
                <b-form @submit="qnaWrite()">
                    <!--게시글 Title, content 인풋-->
                    <b-form-input placeholder="제목을 작성해주세요" class="mt-4 mb-2" v-model="form.title" ref="title"></b-form-input>
                    <ckeditor :editor="editor" v-model="form.content" :config="editorConfig"></ckeditor>
                    <!--게시글 Title, content 인풋 종료-->

                    <!--파일 업로드 인풋-->
                    <div class="m-0 my-5 d-flex justify-content-between align-items-center">
                        <input class="form-control me-auto" type="file" :state="Boolean(form.fileYn)" name="file" ref="file">
                    </div>
                    <!--파일 업로드 인풋 종료-->

                    <!--저장 취소 버튼셋-->
                    <div id = "buttonSet">
                        <b-button type="submit" class="px-4 bedu-bg-custom-blue qna-writebtn">등록</b-button>
                        <b-button class="px-4 qna-cansellbtn" type="reset" :to="'/comm/qna'">취소</b-button>
                    </div>
                    <!--저장 취소 버튼셋 종료-->
                </b-form>

                <!-- <b-form @submit="qnaWrite()">
                    <div id="app">
                        <ckeditor v-model="editorData" :config="editorConfig"></ckeditor>
                    </div>
                    <b-form-input placeholder="제목을 작성해주세요" class="my-3 qna-write-title" id="qna-write-title" v-model="form.title" ref="title"></b-form-input>
                    <b-form-textarea  id="qna-write-content" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"></b-form-textarea>
                    <div class="qna-btn">
                        <b-button class="qna-cansellbtn" id="qna-cansellbtn" type="reset" :to="'/comm/qna'">취소</b-button>
                        <b-button type="submit" class="bedu-bg-custom-blue qna-writebtn" id="qna-writebtn">등록</b-button>
                    </div>
                </b-form> -->
        </div>
    </div>
</template>
<script>
    import Editor from 'ckeditor5-custom-build/build/ckeditor';
    import CommCategory from '@/components/CommCategory.vue';
    import router from '@/router';
    import '@/assets/css/qnaStyle.css';
    export default {
    
        name: 'qnaWrite',
        data() {
            return {
                editor: Editor,
                editorConfig: {
                    // The configuration of the editor.
                    simpleUpload: {
                        // 업로드 URL
                        uploadUrl: '/api/studyUpload',
                        method : 'POST'
                    },
                    mediaEmbed: {
                        previewsInData: true
                    },
                },
                form:{
                    user_name: '',
                    title:'',
                    content : '',
                },

                userlist:[]
            };
        },

        components:{
            CommCategory
        },

        mounted() {
            const nick = this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
                return;
            }
            this.getUserId();
        },

        created() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
            }
        },

        methods: {

            getUserId(){
                const nickname = this.$store.getters.getNickname;
            
                this.$axiosSend('get', '/api/qna/getUserId', {
                    userName: nickname
                }).then(res => {
                    
                    this.userlist = res.data;
                    for(var i=0; i< this.userlist.length; i++){
                        this.form.user_name = this.userlist[i].user_id;
                    }
                   
                })
                .catch((error) => {
                    this.$swal('Error', '회원아이디가 정상적으로 불러오지 않았습니다!', error);
                })

            },

            qnaWrite(){

                if(this.form.title == null || this.form.title == ""){
                    this.$swal({
                        title :'warning!',
                        text :"제목을 입력해주세요",
                        type :'warning',
                        icon : 'warning',
                        didClose: () => {
                            this.$refs.title.focus()
                        }
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
                const form = new FormData();
                
                form.append("user_name", this.form.user_name);
                form.append("title",this.form.title);
                form.append("content",this.form.content);

                this.$axiosSend('post','/api/qna/qnaWrite', this.form)
                .then((response) =>{
                    if(response.data === 1){
                        this.$swal('Success','작성완료!','success'),
                        router.push({
                            name: "qnaBoard"
                        })
                    }
                })
                .catch((error)=>{
                    this.$swal('Error','게시글이 정상적으로 작성되지 않았습니다',error)
                })
            }
        },
    }
</script>