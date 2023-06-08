<template>
    <div class="freeboard" id="freeboard">
        <div class="freeboard-left" id="freeboard-left">
            <h4>커뮤니티</h4>
            <router-link class="bedu-hd-cate-le" to="/comm/qna">질문/답변</router-link><br>
            <router-link class="bedu-hd-cate-le" to="/comm/freBd">자유게시판</router-link>
        </div>
        <div class="freeboard-main" id="freeboard-main">
            <div id="freeboard-box">
                <h1>자유게시판</h1>
                    <b-form @submit="search()">
                        <input type="text" class="search-form" ref="keyword" v-model="form.keyword">       
                        <b-button type="submit" class="btn btn-primary">검색</b-button>
                    </b-form>
                <div class="freewrite" id="freewrite">
                    <b-button type="button" :to="'/comm/freBdWrite'" id="keywordbtn" class="btn btn-primary">글쓰기</b-button>
                </div>
            </div>
            <div class="freeboard-reviews" id="freeboard-reviews">
                <select v-model="freeOption" @change="sortReviews" style="border: none">
                    <option value="default">최신 순</option>
                    <option value="highRating">추천순</option>
                </select>
            </div>
            <div class="freeboard-main-1" id="freeboard-reviews">
                <table class="w3-table-all" id="freeboard-table">
                    <tbody>
                        <tr v-for="free in freelist" :key="free.comm_num">
                        <td id="freeboard-table-tds">
                            <b-link class="text-start" :to="'/comm/freBdDetail/' + free.comm_num">
                            {{ free.title }}
                            </b-link>
                        </td>
                        <td>{{ free.user_id }}</td>
                        <td>{{ free.comm_date }}</td>
                        <td>
                            <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}
                            <font-awesome-icon :icon="['fas', 'thumbs-up']" @click="freelikeUp(free.comm_num)" />
                            <text class="fw-bold ms-2" id="likes">
                            {{ free.comm_like_yn }}
                            </text>
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
                page : 1,
                freelist:[],
                form: {
                    keyword: '',
                },
                freeOption: "default",
                totalItems : 0,
                totalPage : 0,
                currentPage : 1,
            };
        },
        mounted() {
            this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
            this.getTotal();
        },

        components:{
            InfiniteLoading
        },

        created(){
            this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
            this.getTotal();
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
                    alert(err);
                })
            },

            search() {                                   
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

            freelikeUp(num) {
                this.$axiosSend('get', '/api/freBd/likeUp', {
                    num: num
                })
                .then(res => {
                    if(res.data === 1){
                        for(var i=0; i<this.freelist.length; i++){
                            if(num === this.freelist[i].comm_num){
                                this.freelist[i].comm_like_yn++;
                            }
                        }
                    }
                })
                .catch((error) => {
                    alert(error);
                })
            }
        }
    }

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

    #freewrite{
        margin-left: 70px;
    }

    .freeboard-reviews{
        width: 100px;
        margin-left: 700px;
        
    }

    .freeboard-main{
        margin-left:auto; 
        margin-right:auto;
        width: 1000px;
       
    }
    .freeboard-main-1{
        margin-right: 100px;
    }
    #freeboard-box{
      display: flex;
      margin-left: 200px;
      max-width: 1000px;
    }

    #keywordbtn{
        margin-left: -600px;
    }
</style>