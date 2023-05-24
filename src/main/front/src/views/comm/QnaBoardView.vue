<template>
  <div class="qna">
    <div class="qna-title">
      <h1>질문 / 답변</h1>
    </div>
    <div class="qna-main">
      <div id="qna-box">
        <b-form @submit="qnasearch()">
          <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
          <b-button type="submit" class="btn btn-primary">검색</b-button>
        </b-form>
        <b-button :to="'/comm/qnaWrite'" style="margin-left: 310px;">글쓰기</b-button>
      </div>
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
    <b-container>
    </b-container>
  </div>
</template>

<script>
export default {

  data() {

    return {
      qnalist: [],
      form: {
        keyword: '',
      },
      totalItems : 0,
      totalPage : 0,
      currentPage : 1,
    };

  },

  mounted() {
    
    this.List();              
  },

  created() {
    this.List();
  },

  methods: {

    List() {
      this.$axios.get('/api/qna/qnaList', {
        params:{
          page: this.currentPage,
        }
      })
        .then(res => {
          console.log(res);
          this.qnalist = res.data;
          
        })
        .catch(error => {
          console.log(error, '실패함!!');
        });
    },


    qnasearch(){ 

      const form = new FormData();
      form.append('keyword', this.form.keyword);
      this.$axios
        .post('/api/qna/qnaList', form)
        .then(res => {
          console.log('조회성공:', res);
          this.qnalist = res.data;
        })
        .catch(error => {
          console.log(error);
        });
    },

  },
};
</script>
<style scoped>
    
    table{
        margin-left:auto; 
        margin-right:auto;
        width: 100%;
    }

    .qna-main{
        margin-left:auto; 
        margin-right:auto;
        width: 700px;
    }
    #qna-box{
      display: flex;
    }
</style>