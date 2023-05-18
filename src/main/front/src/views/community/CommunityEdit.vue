<template>
    <b-container class="w-50">
        <h1>커뮤니티</h1>
      <b-form @submit="submit()">
          <b-form-input placeholder="작성자이름을 작성해주세요" class="my-5" ref="writer"></b-form-input>
          <b-form-input placeholder="제목을 작성해주세요" class="my-5" ref="title"></b-form-input>
          <b-form-textarea class="form-control col-sm-5" rows="5" placeholder="내용을 작성해주세요" ref="contents"></b-form-textarea>
          <b-container class="my-3 justify-content-md-end d-md-flex">
              <b-button class="" type="reset">취소</b-button>
              <b-button type="submit" class="btn-custom ms-2">수정</b-button>
          </b-container>
      </b-form>
    </b-container>
  </template>

<script>
// import router from '@/router';

// export default {
  
//     name: 'communityedit',
//     data() {
//         return {
//             community:{
//                 title:'',
//                 contents : '',
//                 writer: '',
//             }
//         };
//     },

//     methods: {
//       submit(){
//           const form = new FormData();
//           form.append("title",this.form.title);
//           form.append("contents",this.form.contents);
//           form.append("writer",this.form.writer);

//           this.$axios.post('/api/community/writer',form)
//           .then(
//               this.$swal('Success','작성완료!','success'),
//               router.push({
//                   name:"community"
//               })
//           )
//           .catch((error)=>{
//             console.log(error)
//             this.$swal('Error','게시글이 정상적으로 작성되지 않았습니다','error')
//           })
//       },
    //   path(num){
    //     this.result = num;
    //   },

    //   communityRead(result){ // 게시글 데이터 조회
    //         alert(num);
    //         this.$axios.get('/api/community/detail',{
    //             params : {
    //                 num : result,
    //             }
    //         })
    //         .then(response=>{
    //             this.community = response.data;
    //         })
    //         .catch((error)=>{console.log(error)})
    //     },

    

    // mounted() {
    //     const num = this.$route.params.num;
    //     communityRead(num);
    //     path(num);
    // },
</script>
<script setup>


</script>
<style scoped>
.btn-custom{
  --bs-btn-bg:#3498db !important;
  --bs-btn-border-color : null !important;
}

.btn-custom:hover{
  background-color: #7ebae2 !important;
}


</style>