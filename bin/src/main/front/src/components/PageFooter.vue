<!-- PageFooter.vue -->
<template>
  <footer>
    <div id="footer">
      <router-link to="/tou">이용약관</router-link>
      <router-link to="/privacy">개인정보 보호 방침</router-link>
      <router-link to="/csc">고객센터</router-link>
      <img src="../assets/imgs/test.png">
      <p>
        <span>(주)Test 통신판매업번호: 제 2019-서울강남-01371호</span><br/>
        <span>평생교육시설 신고: 제원-408호 Test 사이버평생교육원</span><br/>
        <span>Email: test@test.com FAX: 02-1234-1234 사업자번호: 123-12-1234 대표자: test 개인정보책임자 : test</span><br/>
        <span>대표전화: 1234-1234</span><br/>
        <span>주소: 서울시 강남구 강남대로 test</span>
      </p>
    </div>
  </footer>
</template>
  
<script>
export default {}
</script>

<style scoped>
footer {
  margin: 0 auto;
  padding-top: 3%;
  padding-bottom: 5%;
  text-align: left;
}

footer img {
  float: left;
  height: 130px;
  width: 150px;
  margin: 27px;
}

footer a {
  margin-right: 10px;
}

footer p {
  margin-top: 10px;
}

</style>