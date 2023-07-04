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
            <!--</div>-->
          <div class="btn-cover">
            <b-button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
                이전
            </b-button>
            <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
            <b-button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
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
           userName : this.$store.state.nickname,
           pageNum : 0,
           listArray : [],
           pageSize : 10
        }
    },
   methods : {
        nextPage () {
            this.pageNum += 1;
        },
        prevPage () {
            this.pageNum -= 1;
        }
    },
    computed : {
        pageCount () {
            let listLeng = this.listArray.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);
            
            if (listLeng % listSize > 0) page += 1;

            return page;
        },
        paginatedData () {
            const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
        return this.listArray.slice(start,end)
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