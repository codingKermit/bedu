<template>
    <div class = "d-flex">
            <div class = "writeMain">
                <CommCategory></CommCategory>
            </div>
        <div id="qna-write">
            <h2>질문 & 답변 수정</h2>
            <b-form @submit="edit()">
                <input type="hidden" v-model="form.comm_num" ref="qna_bd_num"/>
                    <b-form-input class="mt-4 mb-2" ref="title" id="qna-edit-title" v-model="form.title"></b-form-input>
                        
                <ckeditor :editor="editor" v-model="form.content" :config="editorConfig"></ckeditor>

                <div id="buttonSet">
                    <b-button type="submit" class="px-4 btn-custom ms-2 qna-edit bedu-bg-custom-blue" id="qna-edit">수정</b-button>
                    <b-button class="px-4 btn-custom ms-2 qna-edit-censell" id="qna-edit-censell" type="reset" :to="'/comm/qna'" >취소</b-button>
                </div>

            </b-form>
        </div>
    </div>
</template>

<script>
    import Editor from 'ckeditor5-custom-build/build/ckeditor';
    import CommCategory from '@/components/CommCategory.vue';
    import router from '@/router';
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
                    qna_bd_num: 0,  
                    title: '',
                    content: '',
                    // str_qna_date:'',
                    // str_reg_date:'',
                }
            }
        },

        components:{
            CommCategory
        },

        mounted() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
                return;
            }
            const qnum = this.$route.params.num;
            this.qnadetail(qnum);
        },

        created() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                this.$swal('Error','로그인을 해주세요!');
                router.push({
                    name: "qnaBoard"
                })
                return;
            }
        },

        methods: {

            qnadetail(num){ // 게시글 데이터 조회
                this.$axiosSend('get','/api/qna/qnaDetail',{
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

                const form = new FormData();

                form.append("qna_bd_num", this.form.qna_bd_num);
                form.append("title", this.form.title);
                form.append("content", this.form.content);

                alert('게시글을 수정합니다.');
                this.$axiosSend('post','/api/qna/qnaEdit', this.form)
                .then(res => {
                    if(res.data === 1){
                        this.$swal('Success','수정완료!','success'),
                        router.push({
                            name: "qnaBoard"
                        })
                    }
                })
                .catch((error)=>{
                    this.$swal('Error','게시글이 정상적으로 수정되지 않았습니다',error)
                })
            }
        },
    }
</script>
