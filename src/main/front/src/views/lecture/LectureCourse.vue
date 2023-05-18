<template>
  <div class="py-5 lectureCourse">
    <b-button @click="damn()">샘플데이터 와장창 넣어버리기</b-button>
    <b-container class="">
      <div class="mb-5">
        <text class="fs-5 text-secondary">
          {{ korCategory }}
        </text>
      </div>
      <b-row class="row-cols-auto">
        <b-col v-for="item in lectures" :key="item" class="mb-3 col-lg-3 col-md-4 col-sm-9">
          <b-link class="text-decoration-none text-body" :to='"/lectureDetail?num="+item.num'>
            <div class="border-1 border-opacity-10">
              <img :src="item.thumbnail" >
              <b-container class="p-4 border">
                <div>
                  <p class="fw-bold">{{ item.title }}</p>
                </div>
                <p class="fw-bold"> <span  class="teacher-name">{{ item.teacher }} 선생님</span><span> 총 {{ item.total }}강</span></p>
                <p class="text-secondary fs-6">수강기간:{{ item.duration }}일</p>
                <div class="text-center">
                  <b-button class="w-100" variant="outline-dark">수강신청</b-button>
                </div>
              </b-container>
            </div>
          </b-link>
            </b-col>
      </b-row>
    </b-container>
  </div>
</template>


<script>

export default{
  name : 'LectureCourse',
  data() {
    return {
      category : this.$route.params.category,
      korCategory : this.category == 'base' ? '기초 강의':'데이터 분석',
      lectures : [],
    }
  },
  methods: {
    getList(){
      this.$axios.get('/api/lectureList',{
        params:{
          category : this.category
        }
      })
      .then((res)=>{
        console.log(res)
        this.lectures = res.data;
      })
      .catch((err)=>{console.log(err)})
    },
    damn(){
      this.$axios.get('/api/damn')
      .then((res)=>{console.log(res)})
      .catch((err)=>console.log(err));
    }
  },
  mounted() {
    
  },
  created() {
    this.getList();
  },
  watch:{
    '$route.query.category':{
      immediate: true,
      handler(newCategory){
        this.category = newCategory;
      }
    },
    '$route.query.korCategory':{
      immediate: true,
      handler(newKorCategory){
        this.korCategory = newKorCategory;
      }
    }
  }
}


</script>

<style scoped>
img{
  width: 100%;
  height: 150px;
}
.teacher-name::after{
  content: '';
  width: 1px;
  background-color: black;
  margin: 0rem 0.5rem;
  background: black;
  height: 14px;
  display: inline-block;
  vertical-align: middle;
}



</style>