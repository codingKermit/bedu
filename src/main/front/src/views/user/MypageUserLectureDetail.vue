<template>
    <div>
        <div class="p-4 p-md-5 w-100 d-flex">
            <!-- 좌측 네비 -->
            <my-page-cate-navi></my-page-cate-navi>

            <!-- 우측 컨테이너 -->
            <div class="w-100">
                <p class="fs-4 fw-bold">
                    {{ userId }} 님의 현재 수강상세정보
                </p>
                <div class="mb-3">
                    <b-input-group class="w-25 ms-auto">
                        <b-form-input v-model="keyword"></b-form-input>
                        <template #append>
                            <b-button class="bedu-bg-custom-blue" @click="getLectureAllList">검색</b-button>
                        </template>
                    </b-input-group>
                </div>
                <div class="d-flex w-100 mb-5">
                    <div class="me-auto d-flex">
                        <b-button @click="this.groupSelected = 'learning'" pill
                        >
                            학습중
                        </b-button>
                        <b-button class="mx-2" @click="this.groupSelected = 'done'" pill
                        >
                            완강
                        </b-button>
                        <b-button @click="this.groupSelected = 'all'" pill
                        >
                            전체
                        </b-button>
                    </div>
                    <div>
                        <b-form-select :options="orderOptions" v-model="orderSelected"></b-form-select>
                    </div>
                </div>
                <div>
                    <b-row cols="1" cols-md="3">
                        <b-col v-for="(item, index) in listArray" :key="index">
                            <b-container class="border p-1 mb-3">
                                <div class="ratio ratio-16x9 mb-3">
                                    <b-img :src="item.thumbnail"></b-img>
                                </div>
                                <div class="mb-3">
                                    {{ item.title }}
                                </div>
                                <div>

                                </div>
                            </b-container>
                        </b-col>
                    </b-row>
                </div>
                <div>
                    <b-pagination align="center" v-model="currentPage" :total-rows="lectureCount" :per-page="numOfPage"></b-pagination>
                </div>
            </div>
        </div>
        <!-- <div class="lecturedetailcontainer" v-for="(item, index) in paginatedData" :key="index">
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
        </div> -->
                <!-- <div class="btn-cover">
                    <b-button :disabled="pageNumber === 0" @click="prevPage()" class="page-btn">
                        이전
                    </b-button>
                    <span class="page-count">{{ pageNumber + 1 }} / {{ pageCount === 0 ? 1 : pageCount }} 페이지</span>
                    <b-button :disabled="pageNumber >= pageCount - 1" @click="nextPage()" class="page-btn">
                        다음
                    </b-button>
                </div> -->
    </div>
</template>
<script>
import MyPageCateNavi from '../../components/myPage/MyPageCateNavi.vue';
export default {
  components: { MyPageCateNavi },
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
           currentPage : 1,
           //listArray에 데이터 들어가는지 테스트중
           listArray : [], //수강내역 전체 데이터
           numOfPage : 9,
           lectureInfo : {}, //화면에 노출되는 수강내역 데이터
           lectureCount : 0,  //수강내역 전체보기 출력
           keyword : '',
           orderOptions : [
            {
                text : '제목순',
                value : 'title'
            },{
                text : '최근 결제순',
                value : 'recentlyPay'
            }, {
                text : '최근 수강순',
                value : 'recentlyView'
            }],
           orderSelected : 'title',
           groupSelected : 'all',

        }
    }, 
    mounted(){
        // this.getLectureAllList();
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
            // let data = []
            this.$axiosSend('get','/api/mypageAll',{
                userid: userid,
                page : this.currentPage,
                order : this.orderSelected,
                group : this.groupSelected,
                keyword : this.keyword
            })
            .then((res)=>{
                console.log(res)
                this.listArray = res.data.list
                this.lectureCount = res.data.count
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
    },
    watch:{
        currentPage:{
            immediate : true,
            handler(after, before){
                this.currentPage = after
                this.getLectureAllList();
            }
        }
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