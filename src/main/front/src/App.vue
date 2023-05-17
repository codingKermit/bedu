<template>
  <div class="side">
    <PageHeader/> <!-- 헤더 컴포넌트 -->
    <div class="body">
      <router-view/> <!-- 페이지 이동이 표시될 곳 -->
    </div>
    <PageFooter/> <!-- 푸터 컴포넌트 -->
    <b-link :to="test"></b-link>
  </div>
</template>

<script>
import PageHeader from '@/components/PageHeader'
import PageFooter from '@/components/PageFooter'

export default {
  name: 'App',
  components: {
    PageFooter,
    PageHeader
  },
  methods: {
      test(){
        this.$axios.get("/api/hello").
        then((res)=>{
          console.log(res.data)
        })
        .catch((error)=>{console.log(error)})
        ;
      }
    },
    created() {
      this.test();
    },
}
</script>

<style>
#app {
  font-family: 'Noto Sans KR' ,'NanumMyeongjo', 'Times';
}

.body {
  margin: 0px;
  padding: 0px;
  background-color: white;
}

.side {
    padding-left: 6%;
    padding-right: 6%;
    background-color: #EDC268;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>
