<template>
    <div class = "d-flex">
        <div class="qna-view" id="qna-view">
            <CommCategory :currentTab="'qna'"></CommCategory>
        </div> 
        
        <div id="qna-main">
            <h2>질문 & 답변</h2>
                <div class="qnaBoradSearch" id="qnaBoradSearch">
                    <b-form @submit="qnasearch()" class = "serchForm">
                        <font-awesome-icon id="qna-search-icon" :icon="['fas', 'magnifying-glass']" />
                        <input class="qna-view-keyword" id="qna-view-keyword" v-model="form.keyword" ref="keyword" @keyup.enter="qnasearch">
                        <b-button :to="'/comm/qnaWrite'" class="bedu-bg-custom-blue qna-writepath-btn" id="qna-writepath-btn">
                        <font-awesome-icon :icon="['fas', 'pencil']" />
                        글쓰기
                        </b-button>
                    </b-form>
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
                                <b-link class="text-start text-body" :to="'/comm/qnaDetail/' + qna.qna_bd_num">
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
                    this.sortReviews();
                })
                .catch(error => {
                    console.log(error);
                });
            },

            sortReviews() {
                if (this.sortOption === "default") {
                    // 최신 순으로 정렬
                    this.qnalist.sort((a, b) => {
                    return new Date(b.qna_date) - new Date(a.qna_date);
                });
                } else if (this.sortOption === "highViews") {
                // 조회수 순으로 정렬
                    this.qnalist.sort((a, b) => {
                    return b.qna_cnt - a.qna_cnt;
                });
                }
            },

            qnaDateTime(value) {
                // value는 날짜 값입니다
                const now = new Date();
                const date = new Date(value);

                const diffInMilliseconds = now - date;
                const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
                const diffInMinutes = Math.floor(diffInSeconds / 60);
                const diffInHours = Math.floor(diffInMinutes / 60);
                const Days = Math.floor(diffInHours / 24);
                
                if (Days > 0) {
                    return `${Days}일 전`;
                } else if (diffInHours > 0) {
                    
                    return `${diffInHours}시간 전`;
                } else if (diffInMinutes > 0) {
                    return `${diffInMinutes}분 전`;
                } else {
                    return '방금 전';
                }
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
                    // this.sortReviews(); // 정렬 수행
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
                console.log('번호:', this.currentPage);
                this.$axiosSend('get','/api/qna/qnaList',{
                    page : this.currentPage,
                })
                .then(res=>{
                    console.log('데이터',res.data);
                    if(res.data.length){
                        // console.log(res.data[0].user_name);
                        this.currentPage++;
                        this.qnalist.push(...res.data);
                        $state.loaded();
                        for(var i=0; i< this.qnalist.length; i++){
                            this.qnalist[i].str_qna_date = this.qnaDateTime(this.qnalist[i].str_qna_date);
                            // console.log('데이트:', this.qnalist[i].str_qna_date);
                        }
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