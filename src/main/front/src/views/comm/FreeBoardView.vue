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
    <b-nav-item class="text-end ms-auto">
              <label class="text-black">
                {{ currentPage }} / {{totalPage }} 페이지
              </label>  
                  <font-awesome-icon @click="pageChange(currentPage-1)"  :icon="['fas', 'arrow-left-long']" class="mx-2 fs-5 text-dark"/>
                  <font-awesome-icon @click="pageChange(currentPage+1)" :icon="['fas', 'arrow-right-long']" class="fs-5 text-dark"/>
            </b-nav-item>
    <b-container>
      <page-nav></page-nav>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pathnum:0,
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
    this.getTotal();          //끝페이지 번호 설정
    this.List();              //설정한 페이지 번호를 기반으로 게시물 조회
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

  },
};

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