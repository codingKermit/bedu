<template>
    <div class="qna">
        <div class="qna-left">
            <h4>커뮤니티</h4>
            <router-link class="bedu-hd-cate-le" to="/comm/qna">질문/답변</router-link><br>
            <router-link class="bedu-hd-cate-le" to="/comm/freBd">자유게시판</router-link>
        </div>
        <div class="qna-main">
            <div id="qna-box">
                <h2>질문 / 답변</h2>
                <b-form @submit="qnasearch()">
                    <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
                    <b-button type="submit" class="btn btn-primary">검색</b-button>
                </b-form>
                <b-button :to="'/comm/qnaWrite'" style="margin-left: 200px;">글쓰기</b-button>
            </div>
            <div class="qna-main-1">
                <table class="w3-table-all">
                    <tbody>
                        <tr v-for="qna in qnalist" :key="qna">
                            <td>
                                <b-link class="text-start" :to="'/comm/qnaDetail/' + qna.qna_bd_num">
                                    {{ qna.title }}
                                </b-link>
                            </td>
                            <td>{{ qna.user_id }}</td>
                            <td>{{ qna.qna_date }}</td>
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
                <hr>
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
                alert(error);
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
            this.currentPage = 1;
            this.currentPage = val;
            this.List();
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
                alert(err);
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

    .qna-main{
        margin-left:auto; 
        margin-right:auto;
        width: 1000px;
    }
    #qna-box{
      display: flex;
      margin-left: 200px;
      max-width: 1000px;
    }
</style>