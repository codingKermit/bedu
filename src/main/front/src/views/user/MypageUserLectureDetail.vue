<template>
    <b-container>
        <div class="contents">
            <div class="title-div">
                <h3 style="text-align: center;" class="fw-bold">
                    {{ userName }} 님의 현재 수강상세정보
                </h3>
            </div>
        </div>
         <!--<div v-for="(item, index) in paginatedData" :key="index">-->
            <b-container class="w-75 ms-auto py-5">
                    <b-container class="border rounded-3 py-3 mb-2">
                        <p class="fw-bold">프로그래밍 배워봅시다</p>
                        <p>
                            <span>강좌이름 : </span> {{ lecttitle }}
                        </p>
                        <p>
                            <span>강의설명 : </span> {{ lectdesc }}
                        </p>
                        <p>
                            <span>수강기간 : </span> {{ lectperiod }}
                        </p>
                    </b-container>
            </b-container>
                    <b-container class="w-75 ms-auto py-5">
                        <b-container class="border rounded-3 py-3 mb-2">
                                <p class="fw-bold">프로그래밍 배워봅시다</p>
                                <p>
                                    <span>강좌이름 : </span> {{ lecttitle }}
                                </p>
                                <p>
                                    <span>강의설명 : </span> {{ lectdesc }}
                                </p>
                                <p>
                                    <span>수강기간 : </span> {{ lectperiod }}
                                </p>
                         </b-container>
                </b-container>
                <div class="btn-cover">
                    <b-button :disabled="pageNumber === 0" @click="prevPage" class="page-btn">
                        이전
                    </b-button>
                    <span class="page-count">{{ pageNumber + 1 }} / {{ pageCount }} 페이지</span>
                    <b-button :disabled="pageNumber >= pageCount - 1" @click="nextPage" class="page-btn">
                        다음
                    </b-button>
                </div>
      <!--</div>-->
</b-container>
</template>
<script>
export default {
    name : "mypageAll",
    data() {
       return {
           userName : this.$store.state.nickname,
           pageNumber : 0,
           listArray : [],
           numOfPage : 10,
           list : [],         //수강내역 전체 데이터
           lectureInfo : {}, //화면에 노출되는 수강내역 데이터
           lectureCount : 0  //수강내역 전체보기 출력
         
        }
    }, 
    mounted(){
        this.getLectureList();

    },
    methods : {
        nextPage () {
            this.pageNumber += 1;
        },
        prevPage () {
            this.pageNumber -= 1;
        },
         /* 마이페이지 홈(유저아이디 가져오기, 데이터 출력) */
         getLectureList(){
            const userid = this.$store.getters.getEmail;
            let data = []
            this.$axiosSend('get','/api/mypageAll',{userid: userid},true)
            .then((res)=>{
                this.list = res.data;
                for(var i = 0; i < this.list.length; i++) {
                        data.push(res.data.Data[i]);
                }
                this.list = res.data.Data
                this.lectureInfo = data
                this.lectureCount = this.list
            })
            .catch((err)=>{
                console.log(err)
            })
            console.log("######",this.list)
            console.log("######22222",this.lectureInfo)
            console.log("#####!!!!",this.lectureCount)
        }
    },
    computed : {
        pageCount () {
            let listLeng = this.listArray.length,
            listSize = this.numOfPage,
            page = Math.floor(listLeng / listSize);
            
            if (listLeng % listSize > 0) page += 1;
            console.log("##### page",page)
            return page;
        },
        paginatedData () {
            const startNo = (this.pageNumber - 1) * this.numOfPage,
            end = startNo + this.numOfPage;
        return this.listArray.slice(startNo,end)
        },
        rows() {
        return this.items.length
      }
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