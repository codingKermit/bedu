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
                        <b-form-input @keyup="keywordBinding" @keyup.enter="keywordBindingWithSearch"></b-form-input>
                        <template #append>
                            <b-button class="bedu-bg-custom-blue" @click="getLectureAllList">검색</b-button>
                        </template>
                    </b-input-group>
                </div>
                <div class="d-flex w-100 mb-5">
                    <div class="me-auto d-flex">
                        <b-button 
                         pill
                         :to="{
                            query:{
                                group : 'learning',
                                order : this.orderSelected,
                            }
                         }"
                        >
                            학습중
                        </b-button>
                        <b-button class="mx-2" 
                         pill
                         :to="{
                            query:{
                                group : 'done',
                                order : this.orderSelected,
                            }
                         }"
                        >
                            완강
                        </b-button>
                        <b-button 
                        pill
                        :to="{
                            query:{
                                group : 'all',
                                order : this.orderSelected,
                            }
                         }"
                        >
                            전체
                        </b-button>
                    </div>
                    <div>
                        <b-form-select :options="orderOptions" v-model="orderSelected" @input="orderChange"></b-form-select>
                    </div>
                </div>
                <div>
                    <b-row cols="1" cols-md="3">
                        <b-col v-for="(item, index) in listArray" :key="index">
                            <b-container class="border rounded-3 p-3 mb-3">
                                <b-link class="text-body text-decoration-none"
                                :to="{
                                    name : 'lectureDetail',
                                    query :{
                                        num : item.lectNum
                                    }
                                }"
                                >
                                    <div class="ratio ratio-16x9 mb-3">
                                        <b-img :src="item.thumbnail"></b-img>
                                    </div>
                                    <div class="mb-3">
                                        {{ item.title }}
                                    </div>
                                    <hr>
                                    <div class="my-3">
                                        {{ item.lectSum }}
                                    </div>
                                    <div>
                                        <span>수강기간 : </span>
                                        {{ item.lectPeriod }}
                                        <span>일</span>
                                    </div>
                                </b-link>
                            </b-container>
                        </b-col>
                    </b-row>
                </div>
                <div>
                    <b-pagination @page-click="pageChange" align="center" v-model="currentPage" :total-rows="lectureCount" :per-page="numOfPage"></b-pagination>
                </div>
            </div>
        </div>
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
           userNum : this.$store.getters.getUsernum,
           userId : this.$store.getters.getNickname,
           currentPage : 1,
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
        
    },
    methods : {
        pageChange(e){
            console.log(e)
        },
        orderChange(e){
            this.$routerPush('',{
                group : this.groupSelected,
                order : e,
            },true);
        },
        keywordBindingWithSearch(e){
            this.keyword = e.target.value
            this.getLectureAllList();
        },
        keywordBinding(e){
            this.keyword = e.target.value
        },
        nextPage () {
            this.pageNumber += 1;
        },
        prevPage () {
            this.pageNumber -= 1;
        },
        /* 마이페이지 홈(유저아이디 가져오기, 데이터 출력) */
        getLectureAllList(){
            this.$axiosSend('get','/api/mypageAll',{
                userName: this.$store.getters.getNickname,
                page : this.currentPage,
                order : this.orderSelected,
                group : this.groupSelected,
                keyword : this.keyword
            })
            .then((res)=>{
                this.listArray = res.data.list
                this.lectureCount = res.data.count
            })
            .catch((err)=>{
                console.log(err)
            })
            
        },
    },
    computed : {

    },
    watch:{
        currentPage:{
            immediate : true,
            handler(after){
                this.currentPage = after
                this.getLectureAllList();
            }
        },
        '$route.query.group':{
            immediate : true,
            handler(newGroup){
                this.groupSelected = newGroup
                this.getLectureAllList();
            }
        },
        '$route.query.order':{
            immediate : true,
            handler(newOrder){
                this.orderSelected = newOrder;
                this.getLectureAllList();
            }
        },
    }
}

</script>