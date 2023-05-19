<template>
  <div class="community">
    <div class="community-title">
      <h1>커뮤니티</h1>
      <subtitle :subtitle="subtitle" />
    </div>
    <div class="community-main">
      <div id="community-box">
        <!-- <b-form @submit="search()">
          <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
          <b-button class="btn btn-primary" style="margin-left: 300px;">검색</b-button>
        </b-form> -->
      </div>
      <table class="w3-table-all">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>날짜</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="community in communitylist" :key="community">
            <td>{{ community.num }}</td>
            <td><b-link class="text-start" :to='"/communitydetail" + "/" + community.num'>
                {{ community.title }}
              </b-link></td>
            <td>{{ community.writer }}</td>
            <td>{{ community.writeDate }}</td>
            <td>{{ community.views }}</td>
          </tr>

        </tbody>

      </table>
    </div>
    <div id="app">
    </div>
  </div>
</template>
  <script>

  export default {

    data() { //변수생성
      return {
        // requestBody: {}, //리스트 페이지 데이터전송
        communitylist: [], //리스트 데이터
        totalItems : 0,
        totalPage : 0,
        currentPage : 1,
        order : '최신순',
        form: {
          keyword: '',
        }
        // paging: {
        //   block: 0,
        //   end_page: 0,
        //   next_block: 0,
        //   page: 0,
        //   page_size: 0,
        //   prev_block: 0,
        //   start_index: 0,
        //   start_page: 0,
        //   total_block_cnt: 0,
        //   total_list_cnt: 0,
        //   total_page_cnt: 0,
        // }, //페이징 데이터
        // page: this.$route.query.page ? this.$route.query.page : 1,
        // size: this.$route.query.size ? this.$route.query.size : 10,
        // keyword: this.$route.query.keyword,
        // subtitle: '커뮤니티',
        // paginavigation: function () { //페이징 처리 for문 커스텀
        //   let pageNumber = [] //;
        //   let start_page = this.paging.start_page;
        //   let end_page = this.paging.end_page;
        //   for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
        //   return pageNumber;
        // }
      }
    },
    mounted() {
      // this.boo()
      this.List();
    },
    methods: {

      List(){
          this.$axios.get('/api/community/boardList')
          .then(res => {
          console.log(res);
          this.communitylist = res.data;
        })
          .catch(error => {
            console.log(error, '실패함!!')
          })
      },
      search() {
        alert('검색');
        const form = new FormData();
        form.append('keyword', this.form.keyword);
        this.$axios.post('/api/community/boardList', form)
          .then(res => {
            console.log('확인:', res);
            this.form = res.data;
          })
          .catch((error) => {
            console.log(error)
          })
      }
    },
  }
  </script>
<style scoped>
    
    table{
        margin-left:auto; 
        margin-right:auto;
        width: 100%;
    }

    .community-main{
        margin-left:auto; 
        margin-right:auto;
        width: 700px;
    }
</style>