<template>
  <div class="freeboard">
    <div class="freeboard-left">
      <h4>커뮤니티</h4>
        <router-link class="bedu-hd-cate-le" to="/comm/qna">질문/답변</router-link><br>
        <router-link class="bedu-hd-cate-le" to="/comm/qna">커뮤니티</router-link>
    </div>
    <div class="freeboard-main">
      <div id="freeboard-box">
        <h1>자유게시판</h1>
        <b-form @submit="search()">
          <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
          <b-button type="submit" class="btn btn-primary">검색</b-button>
        </b-form>
        <b-button :to="'/comm/freBdWrite'" style="margin-left: 200px;">글쓰기</b-button>
      </div>
      <div class="freeboard-main-1">
        <table class="w3-table-all">
          <tbody>
            <tr v-for="community in communitylist" :key="community.comm_cnt">
              <td>
                <b-link class="text-start" :to="'/comm/freBdDetail/' + community.comm_num">
                  {{ community.title }}
                </b-link>
              </td>
              <td>{{ community.user_id }}</td>
              <td>{{ community.comm_date }}</td>
              <td>
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ community.comm_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" @click="freelikeUp(community.comm_num)"/> 
                <text class="fw-bold ms-2">
                    {{ community.comm_like_yn }}
                </text>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <hr>
    <InfiniteLoading @infinite="infiniteHandler">
                        <template #spinner> <!-- 로딩중일때 보여질 부분 -->
                        </template>
                        <template #no-more> <!-- 처리 완료 후, 최하단에 보여질 부분-->
                            <span></span>
                        </template>
                        <template #no-results> <!-- 처리 실패 후, 보여질 부분 -->
                        </template>
                    </InfiniteLoading>
    <b-container>
      <page-nav></page-nav>
    </b-container>
  </div>
</template>
<script>
import { InfiniteLoading } from 'infinite-loading-vue3-ts';
export default {
  
  data() {
    return {
      page : 1,
      communitylist:[],
      form: {
        keyword: '',
      },
      totalItems : 0,
      totalPage : 0,
      currentPage : 1,
    };
  },
  mounted() {
    this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
    this.infiniteHandler();
    this.getTotal();          //끝페이지 번호 설정
    this.List();              //설정한 페이지 번호를 기반으로 게시물 조회
  },

  components:{
    InfiniteLoading
  },

  created(){
    this.currentPage = 1;     //기본 첫 페이지 번호 초기 설정
    this.getTotal();          //끝페이지 번호 설정
    this.List();           //설정한 페이지 번호를 기반으로 게시물 조회
  },

  methods: {
    List() {
      this.$axios.get('/api/community/boardList', {
        params:{
          page: this.currentPage,
        }
      })
        .then(res => {
          this.communitylist = res.data;
        })
        .catch(error => {
          console.log(error, '실패함!!');
        });
    },
    search() {                                   
      const form = new FormData();
      form.append('keyword', this.form.keyword);
      this.$axios
        .post('/api/community/boardList', form)
        .then(res => {
          this.communitylist = res.data;
        })
        .catch(error => {
          console.log(error);
        });
    },

    infiniteHandler($state){ // 스크롤 이벤트 핸들러
      this.$axios.get('/api/community/boardList',{
        params:{
          category : this.category,
          page : this.page,
        }
      })
      .then(res=>{
        console.log('스크롤:', res);
        if(res.data.item.length){
          this.page++;
          this.communitylist.push(...res.data.item);
          $state.loaded();
        } else{
          $state.complete();
        }
      })
      .catch((err)=>{
        console.log(err)
      })
    },

    getTotal(){ // 게시글 총 갯수 조회
          this.$axios.get('/api/community/total')
          .then((response)=>{
            this.totalItems = response.data;
            this.totalPage = Math.ceil(this.totalItems/10);
          })
          .catch((error)=>{
            console.log(error)
          })
    },

    pageChange(val){ // 페이지 변경
          if(val<=0){
            return;
          }
          if(val>this.totalPage){
            return;
          }
          this.currentPage = val;
          this.List();
    },

    freelikeUp(num) {
      console.log('받은번호: ', num);
      this.pathnum = num;
      this.$axios.get('/api/community/likeUp', {
        params: {
          num: num
        }
      })
        .then(res => {
          console.log(res.data);
          if(res.data === 1){
            this.List();
          }
        })
        .catch((error) => {
          console.log(error);
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

    .freeboard-main{
        margin-left:auto; 
        margin-right:auto;
        width: 1000px;
       
    }
    #freeboard-box{
      display: flex;
      margin-left: 200px;
      max-width: 1000px;
     
    }
</style>