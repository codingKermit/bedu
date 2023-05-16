<template>
  <div class="py-5">
    <categories></categories>
    <div class="mb-5">
      <text class="ms-5 fs-5 fw-light">
        {{ korCategory }}
      </text>
    </div>
    <b-container class="">
      <b-row class="row-cols-auto">
        <b-col v-for="item in lectures" :key="item" class="mb-3 col-lg-3 col-md-4 col-sm-9">
          <b-link class="text-decoration-none text-body">
            <div class="border-1 border-opacity-10">
              <img :src="item.thumbnail" >
              <b-container class="p-4 border">
                <div>
                  <p class="fw-bold">{{ item.title }}</p>
                </div>
                <p class="fw-bold"> <span  class="teacher-name">{{ item.teacher }} 선생님</span><span> 총 {{ item.total }}강</span></p>
                <p class="text-secondary">수강기간:{{ item.duration }}일</p>
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
import Categories from '../../components/Categories.vue'

export default{
  components: { Categories },
  name : 'LectureCourse',
  data() {
    return {
      category : this.$route.query.category,
      korCategory : this.$route.query.korCategory,
      lectures : [
        {
          title : '스케치업으로 완성하는 웹툰 배경',
          teacher : '문연교',
          total : 20,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/239235/local_courselist/thumbnailimg/3522/%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg',
        },{
          title : '디지털 드로잉 기본기 완전판',
          teacher : '남궁법석',
          total : 20,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/231858/local_courselist/thumbnailimg/3512/%EC%8D%B8%EB%84%A4%EC%9D%BC%28%EB%94%94%EC%A7%80%ED%84%B8%20%EB%93%9C%EB%A1%9C%EC%9E%89%29.png',
        },{
          title : '웹툰, 기획부터 제작까지',
          teacher : '김지윤',
          total : 20,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/226430/local_courselist/thumbnailimg/3497/4.%EC%8D%B8%EB%84%A4%EC%9D%BC_%EC%9B%B9%ED%88%B0%EA%B8%B0%ED%9A%8D.png'
        },{
          title : '웹툰 제작',
          teacher : '유선미',
          total : 17,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/213855/local_courselist/thumbnailimg/3490/15_%EA%B7%B8%EB%9E%98%ED%94%BD.png'
        },{
          title : '하나하나 꼼꼼히! 웹툰 드로잉 네 걸음',
          teacher : '문연교',
          total : 30,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/197945/local_courselist/thumbnailimg/3278/%EC%8D%B8%EB%84%A4%EC%9D%BC.png'
        },
        {
          title : '하나하나 꼼꼼히! 웹툰 드로잉 세 걸음',
          teacher : '문연교',
          total : 30,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/197945/local_courselist/thumbnailimg/3278/%EC%8D%B8%EB%84%A4%EC%9D%BC.png'
        },{
          title : '하나하나 꼼꼼히! 웹툰 드로잉 두 걸음',
          teacher : '문연교',
          total : 30,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/197945/local_courselist/thumbnailimg/3278/%EC%8D%B8%EB%84%A4%EC%9D%BC.png'
        },{
          title : '하나하나 꼼꼼히! 웹툰 드로잉 첫걸음',
          teacher : '문연교',
          total : 30,
          duration : 30,
          thumbnail : 'https://www.ddazua.com/pluginfile.php/197945/local_courselist/thumbnailimg/3278/%EC%8D%B8%EB%84%A4%EC%9D%BC.png'
        }
      ],
    }
  },
  methods: {
    
  },
  mounted() {
    this.category = this.$route.query.category
  },
  created() {
    this.category = this.$route.query.category
  },
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