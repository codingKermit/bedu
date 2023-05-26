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
          <tr v-for="qna in qnalist" :key="qna.num">
            <td>
              <b-link class="text-start" :to="'/comm/qnaDetail/' + qna.qna_bd_num">
                {{ qna.title }}
              </b-link>
            </td>
            <td>{{ qna.user_id }}</td>
            <td>{{ qna.date }}</td>
            <td>
              <b-container class="ms-auto text-end">
                <font-awesome-icon :icon="['fas', 'eye']" /> {{ qna.qna_cnt }}
                <font-awesome-icon :icon="['fas', 'thumbs-up']" /> 
                <text class="fw-bold ms-2">
                    {{ qna.qna_like_yn }}
                </text>
            </b-container>
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
  </div>
</template>

<script>
import { InfiniteLoading } from 'infinite-loading-vue3-ts';
export default {

  data() {
    return {
      page : 1,
      qnalist: [],
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
    this.List();
  },

  mounted() {
    this.infiniteHandler();
    this.currentPage = 1;
    this.getTotal();
    this.List();
  },

  components:{
    InfiniteLoading
  },

  methods: {

    List() {
      this.$axios.get('/api/qna/qnaList', {
        params: {
          page: this.currentPage,
        }
      })
        .then(res => {
          this.qnalist = res.data;

        })
        .catch(error => {
          console.log(error, '실패함!!');
        });
    },


    qnasearch() {

      const form = new FormData();
      form.append('keyword', this.form.keyword);
      this.$axios
        .post('/api/qna/qnaList', form)
        .then(res => {
          this.qnalist = res.data;
        })
        .catch(error => {
          console.log(error);
        });
    },

    getTotal() {
      this.$axios.get('/api/qna/total')
        .then((response) => {
          this.totalItems = response.data;
          this.totalPage = Math.ceil(this.totalItems / 10);
        })
        .catch((error) => {
          console.log(error);
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

    infiniteHandler($state){
      this.$axios.get('/api/qna/qnaList',{
        params:{
          page : this.page,
        }
      })
      .then(res=>{
        if(res.data.length){
          this.page++;
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