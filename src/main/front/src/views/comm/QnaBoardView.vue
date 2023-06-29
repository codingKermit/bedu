<template>
    <div class="qna-view" id="qna-view">
        <CommCategory></CommCategory>
        <div class="qna-main" id="qna-main">
            <div class="qna-box" id="qna-box">
                <h2>질문 & 답변</h2>
                <div class="qnaBoradSearch" id="qnaBoradSearch">
                    <b-form @submit="qnasearch()">
                        <font-awesome-icon id="qna-search-icon" :icon="['fas', 'magnifying-glass']" />
                        <input class="my-1 qna-view-keyword" id="qna-view-keyword" v-model="form.keyword" ref="keyword" @keyup.enter="qnasearch">
                        <b-button :to="'/comm/qnaWrite'" class="bedu-bg-custom-blue qna-writepath-btn" id="qna-writepath-btn">
                        <font-awesome-icon :icon="['fas', 'pencil']" />
                        글쓰기
                        </b-button>
                    </b-form>
                </div>
            </div>
            <div class="qna-main-1" id="qna-main-1">
                <div id="qna-sort">
                    <select id="qnaSortOption" v-model="sortOption" @change="sortReviews">
                        <option value="default">최신 순</option>
                        <option value="highViews">조회수 순</option>
                    </select>
                </div>
                <table class="w3-table-all" id="qnaboard-table">
                    <thead>
                        <tr>
                            <th id = "qnaTitle">제목</th>
                            <th>작성자</th>
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
                            <td>{{ qna.user_name }}</td>
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
    
    import CommCategory from '@/components/CommCategory.vue';
    import { InfiniteLoading } from 'infinite-loading-vue3-ts';
    import router from '@/router';
    import '@/assets/css/qnaStyle.css';
    export default {

        data() {
            return {
                qnalist: [
                ],
                form: {
                    keyword: '',
                },
                sortOption: "default", // 정렬 옵션
                totalItems: 0,
                totalPage: 0,
                currentPage: 1
            };

        },

        created() {
        },

        components:{
            InfiniteLoading,
            CommCategory
        },

        mounted() {
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
                if(this.form.keyword === null || this.form.keyword ===''){
                    alert('검색어를 입력해주세요!');
                    return;
                }
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
            },      

            infiniteHandler($state){
                this.$axiosSend('get','/api/qna/qnaList',{
                    page : this.currentPage,
                })
                .then(res=>{
                    if(res.data.length){
                        this.currentPage++;
            
                        this.qnalist.push(...res.data);
                        // const nickname = this.$store.getters.getNickname;
                        // for(var i=0; i<res.data.length; i++){
                        //     this.qnalist[i].user_name = nickname;
                        // }

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