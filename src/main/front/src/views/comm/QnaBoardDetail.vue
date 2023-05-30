<template>
    <div class="container w-75 mt-5 mb-3">
        <div class="mb-3">
            <h1>질문 / 답변</h1>
        </div>
        <hr>
        <b-container class="justify-content-start text-start">
            <h5>
                {{ qna.user_id}}
            </h5>
            {{ qna.date }} 
            <h2 class="mt-3 mb-5 fw-bold">
                {{ qna.title }}
            </h2>
            <div v-html="qna.content"></div>

            <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2">
                    {{ qna.qna_like_yn }}
                </text>
            </b-container>
            <b-button type="submit" class="btn-custom ms-2" @click="qnaeditPath()">글수정</b-button>
            <b-button type="submit" class="btn-custom ms-2" @click="qnadelete()">삭제</b-button>
            <hr class="my-5">
            <b-button :to="'/comm/qna'">목록</b-button>
        </b-container>
    </div>
</template>

<script>
import router from '@/router';

export default{
    
    data() {
        return {
            result : 0,
            qna : {
                title : '',
                content : '',
                user_id : '',
                date : '',
                qna_cnt : 0,
                qna_like_yn : 0,
            }
        }
    },

    mounted() {
        const num = this.$route.params.num;
        
        this.qnaRead(num);
        this.path(num);
    },

    methods: {

        qnaRead(qnanum){ // 게시글 데이터 조회
            console.log('번호:', qnanum);
            this.$axios.get('/api/qna/qnaDetail',{
                params : {
                    num : qnanum,
                }
            })
            .then(res=>{
                this.qna = res.data;
            })
            .catch((error)=>{console.log(error)})
        },
        qnadelete() {
            alert('게시글을 삭제합니다.');
            this.$axios.get('/api/qna/qnaDelete', {
                params: {
                    num: this.result,
                }
            })
                .then(res => {
                    this.$swal('Success', '글삭제완료!', 'success'),
                        console.log(res);
                    router.push({
                        name: "qnaBoard"
                    })
                })
                .catch(error => {
                    console.log(error)
                })
        },
        qnaeditPath(){
            this.$router.push({
                name: 'qnaBoardedit', 
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