<template>
    <div class="qna-view" id="qna-view">
        <div class="qna-view-side" id="qna-view-side">
            <p class="fs-2 fw-bold mb-3 mt-15">커뮤니티</p>
            <ul class="nav flex-column w-100">
                <li class="nav-item me-4" style="width: 150px;">
                    <span class="qna-view-freepath" id="qna-view-freepath">
                        <a class="fs-5 text-body text-decoration-none" @click="freepath()"
                        style="cursor: pointer; text-align: right;">
                            <p>자유게시판</p>
                        </a>
                    </span>
                </li>
                <li class="nav-item me-4" style="width: 150px;">
                    <span class="qna-view-qnapath" id="qna-view-qnapath">
                        <a class="fs-5 text-body text-decoration-none" @click="qnapath()"
                        style="cursor: pointer; text-align: right;">
                            <p>질문 & 답변</p>
                        </a>
                    </span>
                </li>
            </ul>
        </div>
        <div class="qna-main" id="qna-main">
            <div class="qna-box" id="qna-box">
                <h1>질문 & 답변</h1>
                <div class="qnaBoradSearch" id="qnaBoradSearch">
                    <b-form @submit="qnasearch()">
                        <input placeholder="검색어를 입력해주세요." class="my-1 qna-view-keyword" id="qna-view-keyword" v-model="form.keyword" ref="keyword">
                        <b-button type="submit" class="btn-primary qna-search-btn" id="qna-search-btn">검색</b-button>
                        <b-button :to="'/comm/qnaWrite'" class="qna-writepath-btn" id="qna-writepath-btn">글쓰기</b-button>
                    </b-form>
                </div>
            </div>
            <div class="qna-main-1" id="qna-main-1">
                <table class="w3-table-all" id="qnaboard-table">
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>좋아요 수</th>
                            <th>작성일자</th>
                            <th>조회 수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="qna in qnalist" :key="qna">
                            <td id="qnaboard-table-tds">
                                <b-link class="text-start" :to="'/comm/qnaDetail/' + qna.qna_bd_num">
                                    {{ qna.title }}
                                </b-link>
                            </td>
                            <td>{{ qna.user_id }}</td>
                            <td>
                                <font-awesome-icon :icon="['fas', 'thumbs-up']" @click="qnalikeUp(qna.qna_bd_num)" />
                                <text class="fw-bold ms-2" id="likes">
                                    {{ qna.qna_like_yn }}
                                </text>
                            </td>
                            <td>{{ qna.str_qna_date }}</td>
                            <td>
                                <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
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
    import '@/assets/css/qnaStyle.css';
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
            currentPage: 1
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
    
    
</style>