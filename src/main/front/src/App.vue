<template>
  <div class="side">
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
.body {
  margin: 0px;
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
