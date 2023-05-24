<template>
  <div class="community">
    <div class="community-title">
      <h1>자유게시판</h1>
      <!-- <subtitle :subtitle="subtitle" /> -->
    </div>
    <div class="community-main">
      <div id="community-box">
        <b-form @submit="search()">
          <input type="text" class="search-form" ref="keyword" v-model="form.keyword">
          <b-button type="submit" class="btn btn-primary">검색</b-button>
        </b-form>
        <b-button :to="'/comm/freBdWrite'" style="margin-left: 310px;">글쓰기</b-button>
      </div>
      <table class="w3-table-all">
        <tbody>
          <tr v-for="community in communitylist" :key="community.num">
            <td>
              <b-link class="text-start" :to="'/comm/freBdDetail/' + community.comm_num">
                {{ community.title }}
              </b-link>
            </td>
            <td>{{ community.user_id }}</td>
            <td>{{ community.comm_date }}</td>
            <td>{{ community.comm_cnt }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <hr>
    <b-nav-item class="text-end ms-auto">
              <label class="text-black">
                {{ currentPage }} / {{totalPage }} 페이지
              </label>  
                  <font-awesome-icon @click="pageChange(currentPage-1)"  :icon="['fas', 'arrow-left-long']" class="mx-2 fs-5 text-dark"/>
                  <font-awesome-icon @click="pageChange(currentPage+1)" :icon="['fas', 'arrow-right-long']" class="fs-5 text-dark"/>
            </b-nav-item>
    <b-container>
      <page-nav :currentPage="currentPage" :totalPage="totalPage"></page-nav>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      communitylist: [],
      // communitys : {
      //           title : '',
      //           contents : '',
      //           writer : '',
      //           writeDate : '',
      //           view : 0,
      //           heart : 0,
      // },
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
    this.getTotal();          //끝페이지 번호 설정
    this.List();              //설정한 페이지 번호를 기반으로 게시물 조회
  },
  methods: {
    List() {
      this.$axios.get('/api/community/boardList', {
        params:{
          page: this.currentPage,
        }
      })
        .then(res => {
          console.log(res);
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
          console.log('확인:', res);
          this.communitylist = res.data;
        })
        .catch(error => {
          console.log(error);
        });
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
    #community-box{
      display: flex;
    }
</style>