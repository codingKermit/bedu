<template>
    <b-container class="w-50 qna-write" id="qna-write">
        <h1>질문 / 답변</h1>
            
        <b-form @submit="submit()">

            <b-form-input placeholder="제목을 작성해주세요" class="my-5 qna-write-title" id="qna-write-title" v-model="form.title" ref="title"></b-form-input>
            <b-form-textarea class="form-control col-sm-5 qna-write-content" id="qna-write-content" rows="5" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"></b-form-textarea>
            <b-container class="my-3 justify-content-md-end d-md-flex">
                <b-button class="qna-cansellbtn" id="qna-cansellbtn" type="reset">취소</b-button>
                <b-button type="submit" class="btn-custom ms-2 qna-writebtn" id="qna-writebtn">등록</b-button>
            </b-container>
        </b-form>
        <b-button class="qna-qnaview-btn" id="qna-qnaview-btn" :to="'/comm/qna'">목록</b-button>
    </b-container>
</template>

<script>
    import router from '@/router';

    export default {
    
        name: 'qnaWrite',
        data() {
            return {
                form:{
                    user_id: 'test@bedu.com',
                    title:'',
                    content : '',
                }
            };
        },

        methods: {
            submit(){

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
                form.append("user_id", this.form.user_id);
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