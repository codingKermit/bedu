<template>
   <div class = "d-block d-xxl-flex">
      <div class="freeboard-view d-none d-xxl-block" id="freeboard-view">
         <CommCategory :currentTab="'free'"></CommCategory>
      </div> 
         <div class="freeboardMain">
            <div class="commFreeContainer">
               <h2 style="margin: 0;">자유게시판</h2>

               <div v-if="freeBdSearch" class="freeBoradSearch">
                  <div @submit="freesearch()" id="freeSearch">
                     <font-awesome-icon id="free-search-icon" :icon="['fas', 'magnifying-glass']" />
                     <input class="freeViewKeyword" @keyup.enter="freesearch" ref="keyword" v-model="form.keyword">       
                  </div>
                  <b-button :to="'/comm/freBdWrite'" id="free-keywordbtn" class="btn btn-primary free-keywordbtn">
                     <font-awesome-icon :icon="['fas', 'pencil']" />
                     글쓰기
                  </b-button>
               </div>

               <div v-else class="freeBoradSearch" toggleable="xxl">
                  <div @submit="freesearch()" id="freeSearch">
                     <font-awesome-icon id="free-search-icon" :icon="['fas', 'magnifying-glass']" />
                     <input class="freeViewKeyword" @keyup.enter="freesearch" ref="keyword" v-model="form.keyword">       
                  </div>
                  <b-button :to="'/comm/freBdWrite'" id="free-keywordbtn" class="btn btn-primary free-keywordbtn">
                     <font-awesome-icon :icon="['fas', 'pencil']" />
                     @@@
                  </b-button>
               </div>

            </div>   
            <div class="freeSelectBox">
                  <select class="freeQnaSortOption" v-model="sortOption" @change="sortReviews">
                     <option value="default">최신 순</option>
                     <option value="highViews">조회수 순</option>
                  </select>
               </div>

            <div class="w-100 pe-0 pe-sm-3 freeboard-table" id="freeboard-table">
               <div id="freeCommContainer" v-for="free in freelist" :key="free">
                  <div class="freeuser">
                     <font-awesome-icon :icon="['fas', 'user']" size="xl" /> 
                     {{ free.userName }}
                     <div id="commDateTime">{{ freeDateTime(free.commDate) }}</div>
                  </div>
                  <div id="freeboard-table-tds">
                     <b-link class="text-start text-body" :to="'/comm/freBdDetail/' + free.commNum">
                        {{ free.title }}
                     </b-link>
                  </div>
                  <div class="d-none d-xxl-flex">
                     <p id="CommContentDemo">테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트
                     </p>
                  </div>
                  <div id="commCntIcon">
                     <div id="commCnt">
                        <font-awesome-icon :icon="['fas', 'eye']" /> {{ free.commCnt }}
                     </div>
                     <div id="commReply">
                        <font-awesome-icon :icon="['far', 'comment']" />
                        {{replytotal}} 0
                     </div>
                     <div id="commHeart">
                        <font-awesome-icon :icon="['fas', 'heart']"/>
                        0
                     </div>
                  </div>
               </div>
            </div>
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
</template>
<script>
import CommCategory from '@/components/CommCategory.vue';
import { InfiniteLoading } from 'infinite-loading-vue3-ts';
import '@/assets/css/freeBoardStyle.css';
export default {

   components:{ InfiniteLoading, CommCategory },

   data() {
      return {
         // 정렬 옵션
         sortOption: "default", 
         freelist:[],
         form: {
            keyword: ''
         },
         freeOption: "recent",
        
         currentPage : 1,
         freeBdSearch: false
      };


   },

   methods: {
      
      //게시글 검색
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

      //정렬
      sortReviews() {
         if (this.sortOption === "default") {
                // 최신 순으로 정렬
            this.freelist.sort((a, b) => {
               return new Date(b.commDate) - new Date(a.commDate);
            });
         } else if (this.sortOption === "highViews") {
               // 조회수 순으로 정렬
            this.freelist.sort((a, b) => {
               return b.commCnt - a.commCnt;
            });
         }
      },

      //게시글 작성날짜 변환
      freeDateTime(value) {
                
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


      // 스크롤 이벤트 핸들러 조회
      infiniteHandler($state){ 
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

   }
}

</script>
