<template>
    <b-container>
        <div class="contents">
            <div class="title-div">
                <h3 style="text-align: center;" class="fw-bold">
                    {{ userId }} 님의 현재 수강상세정보
                </h3>
            </div>
        </div>
         <div class="lecturedetailcontainer" v-for="(item, index) in paginatedData" :key="index">
            <b-container class="w-75 ms-auto py-5" >
                    <b-container class="border rounded-3 py-3 mb-2">
                        <div class="mypageAll">
                            <div class="mypageContain">
                                <h3> {{ item.title }} </h3>
                                <hr>
                                <div class="lectdesc" v-html="item.lectDesc"></div>
                                <div class="lectperid">
                                    <span>수강기간 : </span> {{ item.lectPeriod }}
                                    <span>일</span>
                                </div>
                            </div>
                        </div>
                    </b-container>
            </b-container>
        </div>
                <div class="btn-cover">
                    <b-button :disabled="pageNumber === 0" @click="prevPage()" class="page-btn">
                        이전
                    </b-button>
                    <span class="page-count">{{ pageNumber + 1 }} / {{ pageCount === 0 ? 1 : pageCount }} 페이지</span>
                    <b-button :disabled="pageNumber >= pageCount - 1" @click="nextPage()" class="page-btn">
                        다음
                    </b-button>
                </div>
        </b-container>
</template>
<script>
export default {
    name : "mypageAll",
    data() {
       return {
           item : {
                title : '',
                lectDesc : '',
                lectPeriod : ''
           },
           userNum : this.$store.state.usernum,
           userId : this.$store.state.nickname,
           userid : this.$store.state.email,
           pageNumber : 0,
           //listArray에 데이터 들어가는지 테스트중
           listArray : [], //수강내역 전체 데이터
           numOfPage : 5,
           lectureInfo : {}, //화면에 노출되는 수강내역 데이터
           lectureCount : 0,  //수강내역 전체보기 출력
        }
    }, 
    mounted(){
        this.getLectureAllList();
    },
    methods : {
        nextPage () {
            this.pageNumber += 1;
        },
        prevPage () {
            this.pageNumber -= 1;
        },
        /* 마이페이지 홈(유저아이디 가져오기, 데이터 출력) */
         getLectureAllList(){
            const userid = this.$store.getters.getEmail;
            let data = []
            this.$axiosSend('get','/api/mypageAll',{userid: userid},true)
            .then((res)=>{
                for(var i = 0; i < this.numOfPage; i++) {
                        data.push(res.data[i]);
                }
                this.listArray = res.data
                this.lectureIfo = data
                this.lectureCount = this.listArray.length
            })
            .catch((err)=>{
                console.log(err)
            })
            
        },
    },
    computed : {
      pageCount() {
            let listLeng = this.listArray.length,
            listSize = this.numOfPage,
            page = Math.floor(listLeng / listSize);
            
            if (listLeng % listSize > 0) page += 1;
            
            return page;
        },
        paginatedData() {
            const startNo = this.pageNumber * this.numOfPage;
            const end = startNo + this.numOfPage;
            
            return this.listArray.slice(startNo,end)
            
        },
    }
}

</script>
<style>
.btn-cover {
  margin-top: 5rem;
  text-align: center;
  padding-bottom : 5rem;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.lecturedetailcontainer{
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
    margin-bottom : -5rem;
}
@media (min-width: 768px) {
    .lecturedetailcontainer {
    width: 1000px;
  }
}
</style>