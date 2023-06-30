<!-- npm install v-pagination-3 설치해야함 -->
<template>
    <b-container>
        <p class="fs-4 fw-bold">마이페이지</p>
    </b-container>
    <b-container>
            <p class="fs-4 fw-bold">현재 수강정보</p>
            <!--<div class="bg-light text-white">-->
                <b-container class="text-dark fw-bold">
                    <!-- v-if문 이용하여  user_id값이 있지만 수강내역이 없을때 수강내역이 없다는 문구 보이게-->
                    <div class="curr-subjectInfo" v-if=" userList == '' " >
                       <b-container class="w-75 ms-auto py-5">
                            <p style="text-align: center;">수강내역이 없습니다.</p>
                       </b-container>
                    </div>
                    <!--v-else를 이용하여 userList가 있고 수강내역이 있을때이라면 현재 수강정보가 보이게-->
                    <div v-else class="lecture">
                        <div class="col-xl-3 col-lg-4 col-md-6" :key="index" v-for="(item,index) in userList">
                            <div class="lect text-start">
                               <!-- <a @click="goToInfo(item.lectNum)" style="cursor:pointer">
                                </a>-->
                                <b-container class="w-100 ms-auto py-5">
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
                            </div>
                        </div>
                    </div>
                </b-container>
            <!--</div>-->
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <div style="text-align: right;">
            <a style="cursor:pointer; text-align: right;">전체보기</a>
            </div>
            <!--<div class="bg-light text-white">-->
            <ul class="list-unstyled "> 
                <li v-for="(item,index) in lists" :key="index" class="mb-3 fs-5">
                <b-container class="w-100 ms-auto py-5">
                  <b-container class="border rounded-3 py-3 mb-2">
                        <p class="fw-bold">프로그래밍 배워봅시다</p>
                        <p>
                            <span>강좌이름 : </span> {{ item.lecttitle }}
                        </p>
                        <p>
                            <span>강의설명 : </span> {{ item.lectdesc }}
                        </p>
                        <p>
                            <span>수강기간 : </span> {{ item.lectperiod }}
                        </p>
                    </b-container>
                </b-container>
                </li>
            </ul>
              
           <!-- </div>-->
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <!--<div class="bg-light text-white">-->
                <b-container class="w-75 ms-auto py-5">
                    <div class="text-dark fw-bold">
                       <p style="text-align: center;">북마크내역이 없습니다.</p>
                    </div>
                    <div  class="bookmark">
                        <div class="col-xl-3 col-lg-4 col-md-6">
                            <div class="lect text-start" style="width:18rem; border:0px">
                                
                            </div>
                        </div>
                    </div>
                </b-container>
           <!-- </div>-->
        </b-container>
</template>
<script>

export default {
    name : "mypage",
    data() {
       return {
           // bookmarkList : [],
            lectureList : [],
            userlectnum : 0,
            userId : this.$store.state.nickname,
            lectNm : this.$store.state.lectnm,
            lectregdate : '',
            regdate : '',
            regid : '',
            userList : [],
            lectNum : this.$store.state.lectNum,
            lecttitle : this.$store.state.title
            
        }
    }, 
    mounted(){
       // this.getBookmarkList();
        this.getLectureList();
    },
    
    
    
    methods : {

    /*
     *북마크 완성되면 추가해야함. 
    
        getBookmarkList(){
            this.bookmarkList = this.$axiosSend('get','/api/bookmarkList',{});
            console.log('!!!!!!!!',this.bookmarkList);
        },
        goToDetail(bookmark_id){
            this.$router.push({path:"/bookmark",query:{bookmark_id : bookmark_id}})
        },
     */
        /* 마이페이지 홈(유저아이디 가져오기) */
        getLectureList(){
            const userid = this.$store.getters.getEmail;
            this.$axiosSend('get','/api/mypage',{userid: userid},true)
            .then((res)=>{
                this.userList = res.data;
                for(var i = 0; i < this.userList.length; i++) {
                    this.userid = this.userList[i].user_id;
                }
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        //goToInfo(lectNum) {
        //    this.$router.push({path:"/lectureList",query:{num : lectNum}})
        //},
    },
    watch:{ // 쿼리 데이터 변경되면 데이터도 변경되도록 설정
            '$route.query.userid':{
                immediate: true,
                handler(userid){
                    this.userid = userid;
                },
            }
        }
}
</script>

