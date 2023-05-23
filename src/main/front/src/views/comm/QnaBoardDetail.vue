<template>
    <div class="container w-75 mt-5 mb-3">
        <div class="mb-3">
            <h1>Q&A게시판</h1>
        </div>
        <hr>
        <b-container class="justify-content-start text-start">
            <h5>
                {{ community.user_id}}
            </h5>
            {{ community.comm_date }} 
            <h2 class="mt-3 mb-5 fw-bold">
                {{ community.title }}
            </h2>
            <div v-html="community.content"></div>

            <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ community.comm_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2">
                    {{ community.comm_like_yn }}
                </text>
            </b-container>
            <b-button type="submit" class="btn-custom ms-2" @click="communityeditPath()">글수정</b-button>
            <b-button type="submit" class="btn-custom ms-2" @click="communitydelete()">삭제</b-button>
            <hr class="my-5">
        </b-container>
    </div>
</template>

<script>
import router from '@/router';

export default{
    
    data() {
        return {
            result : 0,
            community : {
                title : '',
                content : '',
                user_id : '',
                comm_date : '',
                comm_cnt : 0,
                comm_like_yn : 0,
            }
        }
    },

    mounted() {
        const num = this.$route.params.num;
        
        this.communityRead(num);
        this.path(num);
    },

    methods: {
        communityRead(num){ // 게시글 데이터 조회
            // console.log('번호:', num);
            this.$axios.get('/api/community/detail',{
                params : {
                    num : num,
                }
            })
            .then(response=>{
                this.community = response.data;
            })
            .catch((error)=>{console.log(error)})
        },
        communitydelete() {
            alert('게시글을 삭제합니다.');
            this.$axios.get('/api/community/delete', {
                params: {
                    num: this.result,
                }
            })
                .then(res => {
                    this.$swal('Success', '글삭제완료!', 'success'),
                        console.log(res);
                    router.push({
                        name: "community"
                    })
                })
                .catch(error => {
                    console.log(error)
                })
        },
        communityeditPath(){
            this.$router.push({
                name: 'communityedit', 
                params:{
                    num :this.result
                }
            })
                
        },

        path(num){
            this.result = num;
        },

    },
}
</script>
<style scoped>
.category{
    align-items: center;
    display: flex;
}
.category::before{
    line-height: 0px;
    height: 1px;
    background : black;
    margin : 0px 10px;
    flex-grow: 1;
    content : "";
}
.category::after{
    line-height: 0px;
    height: 1px;
    background : black;
    margin : 0px 10px;
    flex-grow: 6;
    content : "";
}

.thisCategory{
    color : #3498db;
}

blockquote {
    background-color: skyblue;
    margin : 10px;
}

</style>