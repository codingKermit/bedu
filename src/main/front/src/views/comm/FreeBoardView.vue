<template>
   <div class = "d-flex">
      <div class="freeboard-view" id="freeboard-view">
         <CommCategory></CommCategory>
      </div> 
         <div class="freeboard-main" id="freeboard-main">
            <div id="freeboard-box">
               <h2>자유게시판</h2>
               <div class="freeBoradSearch" id="freeBoradSearch">
                  <b-form @submit="freesearch()" class = "freeSerch">
                     <font-awesome-icon id="free-search-icon" :icon="['fas', 'magnifying-glass']" />
                     <input class="free-view-keyword" id="free-view-keyword" @keyup.enter="freesearch" ref="keyword" v-model="form.keyword">       
                     <b-button :to="'/comm/freBdWrite'" id="free-keywordbtn" class="btn btn-primary free-keywordbtn">
                     <font-awesome-icon :icon="['fas', 'pencil']" />
                     글쓰기
                     </b-button>
                  </b-form>
               </div>
            </div>
            <div class="freeboard-main-1">
               <table class="w3-table-all freeboard-table" id="freeboard-table">
               <thead>
                     <tr>
                        <th class = "freeTitle">제목</th>
                        <th>작성자</th>
                        <th>작성일자</th>
                        <th>조회 수</th>
                     </tr>
                  </thead>
                  <tbody>
                     <tr v-for="free in freelist" :key="free.comm_num">
                        <td id="freeboard-table-tds">
                           <b-link class="text-start text-body" :to="'/comm/freBdDetail/' + free.comm_num">
                              {{ free.title }}
                           </b-link>
                        </td>
                        <td>{{ free.user_name }}</td>
                        <td>{{ free.str_comm_date }}</td>
                        <td><font-awesome-icon :icon="['fas', 'eye']" /> {{ free.comm_cnt }}</td>  
                     </tr>
                  </tbody>
               </table>
            <InfiniteLoading @infinite="infiniteHandler" @distance="1">
               <!-- 로딩중일때 보여질 부분 -->
               <template #spinner></template>
               <!-- 처리 완료 후, 최하단에 보여질 부분-->
               <template #no-more> <span></span></template>
               <!-- 처리 실패 후, 보여질 부분 -->
               <template #no-results></template>
            </InfiniteLoading>
         </div>
      </div>
   </div>
</template>
<script>
import CommCategory from '@/components/CommCategory.vue';
import { InfiniteLoading } from 'infinite-loading-vue3-ts';
import '@/assets/css/freeBoardStyle.css';
export default {

   components:{ InfiniteLoading, CommCategory },

   data() {
      return {
         sortOption: "default", // 정렬 옵션
         freelist:[],
         form: {
            keyword: ''
         },
         freeOption: "recent",
         totalItems : 0,
         totalPage : 0,
         currentPage : 1,
      };


   },

   methods: {
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
               this.sortReviews();
            })
            .catch((err)=>{
               alert(err);
            })
      },

      sortReviews() {
         if (this.sortOption === "default") {
                // 최신 순으로 정렬
            this.freelist.sort((a, b) => {
            return new Date(b.comm_date) - new Date(a.comm_date);
            });
         } else if (this.sortOption === "highViews") {
               // 조회수 순으로 정렬
            this.freelist.sort((a, b) => {
            return b.comm_cnt - a.comm_cnt;
            });
         }
      },

      infiniteHandler($state){ // 스크롤 이벤트 핸들러
         this.$axiosSend('get','/api/freBd/boardList',{page : this.currentPage})
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

   }
}

</script>
