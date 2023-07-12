<template>
    <b-container>
        <div class="contents">
            <div class="title-div">
                <h3 style="text-align: center;" class="fw-bold">
                    {{ userId }} 님의 현재 수강상세정보
                </h3>
            </div>
        </div>
         <div v-for="(item, index) in paginatedData" :key="index">
            <b-container class="w-75 ms-auto py-5">
                    <b-container class="border rounded-3 py-3 mb-2">
                        <p class="fw-bold">프로그래밍 배워봅시다</p>
                        <p>
                            <span>강좌이름 : </span> {{ item.title }}
                        </p>
                        <p>
                            <span>강의설명 : </span> {{ item.lect_desc }}
                        </p>
                        <p>
                            <span>수강기간 : </span> {{ item.lect_period }}
                        </p>
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
                lect_desc : '',
                lect_period : ''
           },
           userNum : this.$store.state.usernum,
           userId : this.$store.state.nickname,
           userid : this.$store.state.email,
           pageNumber : 0,
           //listArray에 데이터 들어가는지 테스트중
           listArray : [{title : '프로그래밍이지요이지요'},{title : '프로그래밍이지요'},{title : '프로그래밍'},{title : '프로그래밍'},{title : '프로그래밍'},{title : '프로그래밍'}], //수강내역 전체 데이터
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
                        data.push(this.listArray[i]);
                }
                console.log(res)
                
                this.lectureIfo = data
                this.lectureCount = this.listArray.length
            })
            .catch((err)=>{
                console.log(err)
            })
            console.log("######22222",this.lectureInfo)
            console.log("#####!!!!",this.lectureCount)
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
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>