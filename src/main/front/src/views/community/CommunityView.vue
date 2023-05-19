<template>
  <div class="community">
    <div class="community-title">
      <h1>커뮤니티</h1>
      <!-- <subtitle :subtitle="subtitle" /> -->
    </div>
    <div class="community-main">
      <div id="community-box">
        <b-form @submit="search">
          <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
          <b-button type="submit" class="btn btn-primary" style="margin-left: 300px;">검색</b-button>
        </b-form>
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
          <tr v-for="community in communitylist" :key="community.num">
            <td>{{ community.num }}</td>
            <td>
              <b-link class="text-start" :to="'/communitydetail/' + community.num">
                {{ community.title }}
              </b-link>
            </td>
            <td>{{ community.writer }}</td>
            <td>{{ community.writeDate }}</td>
            <td>{{ community.views }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div id="app"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      communitylist: [],
      form: {
        keyword: '',
      },
    };
  },
  mounted() {
    this.List();
  },
  methods: {
    List() {
      this.$axios.get('/api/community/boardList')
        .then(res => {
          console.log(res);
          this.communitylist = res.data;
        })
        .catch(error => {
          console.log(error, '실패함!!');
        });
    },
    search() {
      alert('검색');
      const form = new FormData();
      form.append('keyword', this.form.keyword);
      this.$axios
        .post('/api/community/boardList', form)
        .then(res => {
          console.log('확인:', res);
          this.form = res.data;
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

    .community-main{
        margin-left:auto; 
        margin-right:auto;
        width: 700px;
    }
</style>