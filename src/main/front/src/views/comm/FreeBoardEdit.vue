<template>
    <div class = "d-flex">
        <div class = "writeMain">
        <CommCategory></CommCategory>
        </div>
        <div id="freeboard-write">
            <h2>자유게시판 수정</h2>
            <b-form @submit="edit()">
                <input type="hidden" v-model="form.commNum" ref="commNum"/>
                <b-form-input class="mt-4 mb-2" id="freeboard-edit-title" ref="title" v-model="form.title"></b-form-input>
                <ckeditor :editor="editor" v-model="form.content" :config="editorConfig"></ckeditor>
                
                <div class="m-0 my-5 d-flex justify-content-between align-items-center">
                <input class="form-control me-auto" type="file" :state="Boolean(form.fileYn)" name="file" ref="file">
                </div>

                <div class="my-3 justify-content-md-end d-md-flex freeboard-edit-btncontain" id="freeboard-edit-btncontain">
                    <b-button type="submit" class="px-4 bedu-bg-custom-blue freeboard-edit-submit" id="freeboard-edit-submit">수정</b-button>
                    <b-button class="px-4 btn-custom ms-2 freeboard-edit-reset" id="freeboard-edit-reset" type="reset" :to="'/comm/freBd'">취소</b-button>
                </div>
            </b-form>
        </div>
    </div>
</template>

<script>
    import Editor from 'ckeditor5-custom-build/build/ckeditor'; 
    import CommCategory from '@/components/CommCategory.vue';
    import router from '@/router';
    import '@/assets/css/freeBoardStyle.css';
    export default{

        data(){
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
                    commNum : 0,  
                    title: '',
                    content: '',
                }
            }
        },

        components:{
            CommCategory
        },

        created() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "login"
                })
                return;
            }
        },


        mounted() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "login"
                })
                return;
            }
            const num = this.$route.params.num;
            this.freedetail(num);
            
        },

        methods: {
            freedetail(num){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/freBd/detail',{
                        num : num,
                })
                .then(response=>{
                    this.form = response.data;
                })
                .catch((error)=>{
                    alert(error);
                })
            },

            edit(){
                alert('게시글을 수정합니다.');
                // const form = new FormData();
                // form.append("comm_num",this.form.commNum);
                // form.append("title",this.form.title);
                // form.append("content",this.form.content);
                
                this.$axiosSend('post','/api/freBd/edit', this.form)
                .then(res => {
                    if(res.data === 1){
                        this.$swal('Success','수정완료!','success'),
                        router.push({
                            name:"freeBoard"
                        })
                    }
                })
                .catch((error)=>{
                    this.$swal('Error','게시글이 정상적으로 수정되지 않았습니다',error);
                })
            }
        },
    }
</script>