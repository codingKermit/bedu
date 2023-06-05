<template>
    <div class="container w-75 mt-5 mb-3">
        <div class="mb-3">
            <h1>자유게시판</h1>
        </div>
        <hr>
        <b-container class="justify-content-start text-start">
            <h5>
                {{ free.user_id}}
            </h5>
            {{ free.comm_date }} 
            <h2 class="mt-3 mb-5 fw-bold">
                {{ free.title }}
            </h2>
            <div v-html="free.content"></div>

            <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2">
                    {{ free.comm_like_yn }}
                </text>
            </b-container>
            <b-button type="submit" class="btn-custom ms-2" @click="freeeditPath()">글수정</b-button>
            <b-button type="submit" class="btn-custom ms-2" @click="freedelete()">삭제</b-button>
            <hr class="my-5">

            <b-button type="submit" class="btn-custom ms-2" @click="freeBoardpath()">목록</b-button>
        </b-container>
    </div>
    
</template>

<script>
import router from '@/router';

export default{
    
    data() {
        return {
            result : 0,
            free : {
                comm_num:0,
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
        
        this.freeRead(num);
        this.path(num);
    },

    methods: {
        freeRead(num){ // 게시글 데이터 조회
            // console.log('번호:', num);
            this.$axiosSend('get','/api/freBd/detail',{
                    num : num,
            })
            .then(response=>{
                this.free = response.data;
            })
            .catch((error)=>{console.log(error)})
        },
        freedelete() {
            alert('게시글을 삭제합니다.');
            this.$axiosSend('get','/api/freBd/delete', {
                    num: this.result,
            })
                .then(res => {
                    if(res.data === 1){
                        this.$swal('Success', '글삭제완료!', 'success'),
                    router.push({
                        name: "freeBoard"
                    })
                        
                    }
                })
                .catch(error => {
                    console.log(error)
                })
        },
        freeeditPath(){
            router.push({
                name: 'freeBoardEdit', 
                params:{
                    num :this.result
                }
            })
                
        },
        freeBoardpath(){
            router.push({
                name: 'freeBoard', 
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