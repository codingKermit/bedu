<template>
    <div class="qna-view" id="qna-view">
        <div class="qna-view-side" id="qna-view-side">
            <ul>
                <li>
                    <div>
                        <h3>커뮤니티</h3>
                    </div>
                </li>
                <ul>
                    <li><div class="qna-view-freepath" id="qna-view-freepath" @click="freepath()">자유게시판</div></li>
                    <li><div div class="qna-view-qnapath" id="qna-view-qnapath" @click="qnapath()">질문답변</div></li>
                </ul>
            </ul>
        </div>
        <div class="qna-main" id="qna-main">
            <div class="qna-box" id="qna-box">
                <h2>질문 / 답변</h2>
                <b-form @submit="qnasearch()">
                    <input placeholder="제목, 내용" class="my-1 qna-view-keyword" id="qna-view-keyword" v-model="form.keyword" ref="keyword">
                    <b-button type="submit" class="btn-primary qna-search-btn" id="qna-search-btn">검색</b-button>
                </b-form>
                <div id="qna-main-write">
                    <b-button :to="'/comm/qnaWrite'" class="qna-writepath-btn" id="qna-writepath-btn">글쓰기</b-button>
                </div>
            </div>
            <div class="qna-main-1" id="qna-main-1">
                <table class="w3-table-all" id="qnaboard-table">
                    <tbody>
                        <tr v-for="qna in qnalist" :key="qna">
                            <td id="qnaboard-table-tds">
                                <b-link class="text-start" :to="'/comm/qnaDetail/' + qna.qna_bd_num">
                                    {{ qna.title }}
                                </b-link>
                            </td>
                            <td>{{ qna.user_id }}</td>
                            <td>{{ qna.str_qna_date }}</td>
                            <td>
                                <b-container class="ms-auto text-end">
                                    <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                                    <font-awesome-icon :icon="['fas', 'thumbs-up']" @click="qnalikeUp(qna.qna_bd_num)"/> 
                                        {{ qna.qna_like_yn }}
                                </b-container>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <InfiniteLoading @infinite="infiniteHandler" @distance="1">
                    <template #spinner> <!-- 로딩중일때 보여질 부분 -->
                    </template>
                    <template #no-more> <!-- 처리 완료 후, 최하단에 보여질 부분-->
                    <span></span>
                    </template>
                    <template #no-results> <!-- 처리 실패 후, 보여질 부분 -->
                    </template>
                </InfiniteLoading>
            </div>
        </div>
    </div>
</template>

<script>
    import router from '@/router';
    import { InfiniteLoading } from 'infinite-loading-vue3-ts';
    export default {

    data() {
        return {
            qnalist: [
            ],
            form: {
                keyword: '',
            },
            totalItems: 0,
            totalPage: 0,
            currentPage: 1,
        };

    },

    created() {
        this.currentPage = 1;
        this.getTotal();
    },

    mounted() {
        this.currentPage = 1;
        this.getTotal();
    },

    components:{
        InfiniteLoading
    },

    methods: {

        List() {
            
            this.$axiosSend('get','/api/qna/qnaList', {
                page: this.currentPage,
            })
            .then(res => {
                this.qnalist = res.data;
            })
            .catch(error => {
                console.log(error);
            });
        },


        qnasearch() {

            const form = new FormData();
            form.append('keyword', this.form.keyword);

            this.$axiosSend('post','/api/qna/qnaList', this.form)
            .then(res => {
                this.qnalist = res.data;
            })
            .catch(error => {
                alert(error);
            });
        },

        getTotal() {
            this.$axiosSend('get','/api/qna/total')
            .then((response) => {
                this.totalItems = response.data;
                this.totalPage = Math.ceil(this.totalItems / 5);
            })
            .catch((error) => {
                alert(error);
            })
            },
            pageChange(val) { // 페이지 변경

            if (val <= 0) {
                return;
            }
            if (val > this.totalPage) {
                return;
            }
            this.currentPage = val;
            this.List();
        },

        freepath(){
            router.push({
                name: "freeBoard"
            })
        },

        qnapath(){
            router.push({
                name: "qnaBoard"
            })
        },      

        qnalikeUp(qnum) {
            this.$axiosSend('get', '/api/qna/likeUp', {
                num: qnum
            })
            .then(res => {
                if (res.data === 1) {
                    for (var i = 0; i < this.qnalist.length; i++) {
                        if (qnum === this.qnalist[i].qna_bd_num) {
                            this.qnalist[i].qna_like_yn++;
                        }
                    }
                }
            })
            .catch((error) => {
                alert(error);
            })
        },

        infiniteHandler($state){
            this.$axiosSend('get','/api/qna/qnaList',{
                page : this.currentPage,
            })
            .then(res=>{
                if(res.data.length){
                    this.currentPage++;
                    this.qnalist.push(...res.data);
                    $state.loaded();
                } else{
                    $state.complete();
                }
            })
            .catch(err=>{
                console.log(err);
            })
        }
    },
    };
</script>
<style scoped>
    
    table{
        margin-left:auto; 
        margin-right:auto;
        width: 500px;
    }

    td{
      font-size: 140%;
      height: 100px;
    }
    
    #qna-view{
        margin-left:auto; 
        margin-right:auto;
        width: 1400px;
    }

    .qna-main{
        margin-left:auto; 
        margin-right:auto;
        width: 1000px;
    }
    .qna-main-1{
        margin-right: 100px;
    }

    #qna-box{
      display: flex;
      margin-left: 200px;
      max-width: 1000px;
    }

    #qna-view-keyword{
        border-radius: 5px 5px / 5px 5px;
    }

    #qna-writepath-btn{
        margin-left: -600px;
    }

    #qna-search-btn{
        margin-left: 20px;
    }

    #qna-main-write{
        margin-left: 100px;
    }

    #qna-view-side{
        margin-left: 60px;
    }

    body, ul, li {
        margin: 0;
        padding: 0;
        list-style: none;   	    /* 해당 태그의 list-style을 none으로 하는 것으로 ●을 제거한다 */    
    }
</style>