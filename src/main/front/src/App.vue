<template>
  <div>
    <PageHeader/> <!-- 헤더 컴포넌트 -->
    <div class="body">
      <router-view class="py-5"/> <!-- 페이지 이동이 표시될 곳 -->
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
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.body {
    border-left: solid 100px #EDC268;
    border-right: solid 100px #EDC268;
    border-color: #EDC268;
}

</style>
