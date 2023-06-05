<template>
    <b-container class="w-50">
        <h1>자유게시판</h1>
        <b-form @submit="edit()">
            <input type="hidden" v-model="form.comm_num" ref="comm_num"/>
            <b-form-input class="my-5" ref="title" v-model="form.title"></b-form-input>
            <b-form-textarea class="form-control col-sm-5" rows="5" ref="content" v-model="form.content"></b-form-textarea>
            <b-container class="my-3 justify-content-md-end d-md-flex">
                <b-button class="" type="reset">취소</b-button>
                <b-button type="submit" class="btn-custom ms-2">수정</b-button>
            </b-container>
        </b-form>
        <b-button type="submit" class="btn-custom ms-2" :to="'/comm/freBd'">목록</b-button>
    </b-container>
</template>

<script>
    import router from '@/router';
    export default{

        data(){
            return {
                form:{
                    comm_num : 0,  
                    title: '',
                    content: '',
                }
            }
        },

        mounted() {
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
                const form = new FormData();
                form.append("comm_num",this.form.comm_num);
                form.append("title",this.form.title);
                form.append("content",this.form.content);
                
                this.$axiosSend('post','/api/freBd/edit', this.form)
                .then(res => {
                    if(res.data === 1){
                        this.$swal('Success','수정완료!','success'),
                        router.push({name:"freeBoard"})
                    }
                })
                .catch((error)=>{
                    this.$swal('Error','게시글이 정상적으로 수정되지 않았습니다',error);
                })
            }
        },
    }
</script>
<style scoped>
    .btn-custom{
        --bs-btn-bg:#3498db !important;
        --bs-btn-border-color : null !important;
    }

    .btn-custom:hover{
        background-color: #7ebae2 !important;
    }


</style>