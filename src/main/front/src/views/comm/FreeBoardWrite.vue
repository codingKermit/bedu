<template>
    <div>
        <CommCategory :titleShow="false"></CommCategory>
        <b-container class="w-50 freeboard-write" id="freeboard-write">
            <h1 id="freeboard-title">자유게시판</h1>
            <b-form @submit="submit()">
                <b-form-input placeholder="제목을 작성해주세요" class="my-5" id="freeboard-write-title" v-model="form.title" ref="title"></b-form-input>
                <b-form-textarea id="freeboard-write-content" v-model="form.content" placeholder="내용을 작성해주세요" ref="content"></b-form-textarea>
                <b-container class="my-3 justify-content-md-end d-md-flex" id="freeboard-write-conbtn">
                    <b-button type="reset" class="freeboard-write-reset" id="freeboard-write-reset" :to="'/comm/freBd'" >취소</b-button>
                    <b-button type="submit" class="btn-custom ms-2 freeboard-write-submit" id="freeboard-write-submit">등록</b-button>
                </b-container>
            </b-form>
        </b-container>
    </div>
</template>

<script>
    import router from '@/router';
    import CommCategory from '@/components/CommCategory.vue';
    import '@/assets/css/freeBoardStyle.css';
    export default {
    
        name: 'freeWrite',
        data() {
            return {
                form:{
                    user_name:'',
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
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                alert('로그인을 해주세요.');
                window.history.back();
            }
            this.getUserId();
        },

        created() {
            const nick =this.$store.getters.getNickname;
            if(nick === '' || nick === null){
                alert('로그인을 해주세요.');
                window.history.back();
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
                        this.form.user_name = this.userlist[i].user_id;
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