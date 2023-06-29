<template>
    <div class = "d-flex">
        <div class = "writeMain">
            <CommCategory></CommCategory>
        </div>  
            <div class="freeboard-write" id="freeboard-write">
                <h2>자유게시판</h2>
                <b-form @submit="submit()">
                    <b-form-input placeholder="제목을 작성해주세요" class="mt-4 mb-2" id="freeboard-write-title" v-model="form.title" ref="title"></b-form-input>
                        <ckeditor :editor="editor" v-model="form.content" :config="editorConfig"></ckeditor>

                    <div class="m-0 my-5 d-flex justify-content-between align-items-center">
                    <input class="form-control me-auto" type="file" :state="Boolean(form.fileYn)" name="file" ref="file">
                    </div>    
                    
                    <div id="freeboard-write-conbtn">
                        <b-button type="reset" class="freeboard-write-reset" id="freeboard-write-reset" :to="'/comm/freBd'" >취소</b-button>
                        <b-button type="submit" class="bedu-bg-custom-blue freeboard-write-submit" id="freeboard-write-submit">등록</b-button>
                    </div>
                </b-form>
            </div>
    </div>
</template>

<script>
    import Editor from 'ckeditor5-custom-build/build/ckeditor'; 
    import router from '@/router';
    import CommCategory from '@/components/CommCategory.vue';
    import '@/assets/css/freeBoardStyle.css';
    export default {
    
        name: 'freeWrite',
        data() {
            return {
                editor: Editor,
                editorConfig: {
                    resize_minHeight : 800,
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
                    user_name:'',
                    title:'',
                    content : '',
                    reg_id:'',
                },

                userlist:[]
                
            };
        },

        components:{
            CommCategory
        },

        mounted() {
            this.form.user_name =this.$store.getters.getNickname;
            if(this.form.user_name === '' || this.form.user_name === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
                return;
            }
            this.getUserId();
        },

        created() {
            this.form.user_name =this.$store.getters.getNickname;
            if(this.form.user_name === '' || this.form.user_name === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
                return;
            }
        },

        methods: {

            getUserId(){
                const nickname = this.$store.getters.getNickname;
                this.$axiosSend('get', '/api/free/getUserId', {
                    userName: nickname
                }).then(res => {
                    this.userlist = res.data;
                    for(var i=0; i< this.userlist.length; i++){
                        this.form.reg_id = this.userlist[i].user_id;
                    }
                })
                .catch((error) => {
                    this.$swal('Error', '회원아이디가 정상적으로 불러오지 않았습니다.', error);
                })

            },
        
            submit() {

                if (this.form.title == null || this.form.title == "") {
                    this.$swal({
                        title: 'warning!',
                        text: "제목을 입력해주세요",
                        type: 'warning',
                        icon: 'warning',
                        didClose: () => {
                            this.$refs.title.focus()
                        }
                    })
                    return;
                }
                if (this.form.content == null || this.form.content == "") {
                    this.$swal({
                        title: 'warning!',
                        text: "내용을 입력하세요",
                        type: 'warning',
                        icon: 'warning',
                    })
                    return;
                }

                var form = new FormData();
                console.log('닉', this.form.userNick);
                console.log('id', this.form.reg_id);
                form.append("reg_id", this.form.reg_id);
                form.append("user_name", this.form.user_name);
                form.append("title", this.form.title);
                form.append("content", this.form.content);

                this.$axiosSend('post', '/api/freBd/write', this.form)
                .then((res) => {
                    if (res.data === 1) {
                        this.$swal('Success', '작성완료!', 'success'),
                        router.push({
                            name: "freeBoard"
                        })
                    }
                })
                .catch((error) => {
                    this.$swal('Error', '게시글이 정상적으로 작성되지 않았습니다.', error);
                })
            }

        },
    }
</script>