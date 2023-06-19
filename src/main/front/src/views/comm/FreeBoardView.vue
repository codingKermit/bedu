<template>
    <div class="freeboard-view" id="freeboard-view">
        <CommCategory></CommCategory>
        <div class="freeboard-main" id="freeboard-main">
            <div id="freeboard-box">
                <h1>자유게시판</h1>
                <div class="freeBoradSearch" id="freeBoradSearch">
                    <b-form @submit="freesearch()">
                        <input type="text" class="search-form free-view-keyword" id="free-view-keyword" placeholder="검색어를 입력해주세요." ref="keyword" v-model="form.keyword">       
                        <b-button type="submit" class="btn btn-primary free-view-keywordbtn" id="free-view-keywordbtn">검색</b-button>
                        <b-button type="button" :to="'/comm/freBdWrite'" id="free-keywordbtn" class="btn btn-primary free-keywordbtn">글쓰기</b-button>
                    </b-form>
                </div>
            </div>
            <div class="freeboard-main-1">
                <table class="w3-table-all freeboard-table" id="freeboard-table">
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                            <th>조회 수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="free in freelist" :key="free.comm_num">
                            <td id="freeboard-table-tds">
                                <b-link class="text-start" :to="'/comm/freBdDetail/' + free.comm_num">
                                {{ free.title }}
                                </b-link>
                            </td>
                            <td>{{ free.user_id }}</td>

                            <td>{{ free.str_comm_date }}</td>
                            <td>
                                <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}
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
    import CommCategory from '@/components/CommCategory.vue';
    import { InfiniteLoading } from 'infinite-loading-vue3-ts';
    import '@/assets/css/freeBoardStyle.css';
    export default {
    
        data() {
            return {
                freelist:[],
                form: {
                    keyword: '',
                },
                freeOption: "recent",
                totalItems : 0,
                totalPage : 0,
                currentPage : 1,
            };
        },
        mounted() {
            // this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
        },

        components:{
            InfiniteLoading, CommCategory
        },

        created(){
            // this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
        },

        methods: {

            freeList() {
                this.$axiosSend('get','/api/freBd/boardList', {
                    page: this.currentPage,
                })
                .then(res=>{
                    this.freelist = res.data;
                })
                .catch((err)=>{
                    console.log(err);
                })
            },

            freesearch() {    
                if(this.form.keyword === null || this.form.keyword ===''){
                    alert('검색어를 입력해주세요!');
                    return;
                }                               
                const form = new FormData();
                form.append('keyword', this.form.keyword);
                this.$axiosSend('post','/api/freBd/boardList', this.form)
                .then(res=>{
                    this.freelist = res.data;
                })
                .catch((err)=>{
                    alert(err);
                })
            },

            

            infiniteHandler($state){ // 스크롤 이벤트 핸들러
                this.$axiosSend('get','/api/freBd/boardList',{
                    page : this.currentPage,
                })
                .then(res=>{
                    if(res.data.length){
                        this.currentPage++;
                        this.freelist.push(...res.data);
                        $state.loaded();
                    } else{
                        $state.complete();
                    }
                })
                .catch(err=>{
                    alert(err);
                })
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

            getTotal(){ // 게시글 총 갯수 조회
                this.$axiosSend('get', '/api/freBd/total')
                .then(response=>{
                    this.totalItems = response.data;
                    this.totalPage = Math.ceil(this.totalItems/5);
                })
                .catch((error)=>{
                    alert(error);
                })
            },

            // freelikeUp(num) {
            //     this.$axiosSend('get', '/api/freBd/likeUp', {
            //         num: num
            //     })
            //     .then(res => {
            //         if(res.data === 1){
            //             for(var i=0; i<this.freelist.length; i++){
            //                 if(num === this.freelist[i].comm_num){
            //                     this.freelist[i].comm_like_yn++;
            //                 }
            //             }
            //         }
            //     })
            //     .catch((error) => {
            //         alert(error);
            //     })
            // }
        }
    }

</script>
