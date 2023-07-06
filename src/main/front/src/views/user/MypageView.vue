<template>
    <b-container>
        <b-container>
            <p class="fs-4 fw-bold">마이페이지</p>
        </b-container>
        <p class="fs-4 fw-bold">현재 수강정보</p>
            <b-container class="text-dark fw-bold">
                    <!-- v-if문 이용하여  수강내역이 없을때 수강내역이 없다는 문구 보이게-->
                    <div class="curr-subjectInfo" v-if=" userList == '' || lectureList == 0 " >
                       <b-container class="w-75 ms-auto py-5">
                            <p style="text-align: center;">수강내역이 없습니다.</p>
                       </b-container>
                    </div>
                    <!--v-else를 이용하여 userList가 있고 수강내역이 있을때이라면 현재 수강정보가 보이게-->
                    <div v-else class="lecture">
                        <div style="text-align: right;"><!-- v-if="lectureCount+0 > numOfLecture+0"> 나중에 div 안에 넣어야함-->
                            <a  @click="getLectureCount" style="cursor:pointer; text-align: right;">전체보기</a>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6" :key="index" v-for="(item,index) in lectureList">
                            <div class="lect text-start">
                               <!--  링크걸어서 화면 이동 테스트중 -->
                                <b-link class="text-decoration-none text-body h-100 d-block" :to='"/mypageAll"'>
                                <b-container class="w-25 ms-auto py-5" style="float: left;">
                                    <b-container class="border rounded-3 py-3 mb-2" >
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
                            </b-link>
                        </div>
                    </div>
                </div>
            </b-container>
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
                <div style="text-align: right;"><!-- v-if="lectureCount+0 > numOfLecture+0"> 나중에 div 안에 넣어야함-->
                    <a  @click="getLectureCount" style="cursor:pointer; text-align: right;">전체보기</a>
                </div>
                <!--  링크걸어서 화면 이동 테스트중 -->
                <b-link class="text-decoration-none text-body h-100 d-block" :to='"/mypageAll"'>
                    <b-container class="w-25 ms-auto py-5" style="float: left;">
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
                    
                        <b-container class="w-25 ms-auto py-5" style="float: left;">
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
                        <b-container class="w-25 ms-auto py-5" style="float: left;">
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
                    </b-link>
                </b-container>
        <!--
        <div style="bottom;">
            <b-container>
                <p class="fs-4 fw-bold">북마크</p>
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
                    </b-container>
        </div>-->
</template>
<script>

export default {
    name : "mypage",
    data() {
       return {
           // bookmarkList : [],
            lectureList : [], //수강내역 전체 데이터
            lectureInfo : {}, //화면에 노출되는 수강내역 데이터
            lectureCount : 0, //수강내역 전체보기 출력
            numOfLecture : 3, //처음에 출력할 수강내역 개수
            lectureLsitFirst : 0,
            dataFull : false,
            userlectnum : 0,
            userId : this.$store.state.nickname,
            lectNm : this.$store.state.lectnm,
            lectregdate : '',
            regdate : '',
            regid : '',
            userList : [],
            lectNum : this.$store.state.lectNum,
            lecttitle : this.$store.state.title,
            username : this.$store.state.nickname,
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
        /* 마이페이지 홈(유저아이디 가져오기, 데이터 출력) */
        getLectureList(){
            const userid = this.$store.getters.getEmail;
            let data = []
            this.$axiosSend('get','/api/mypage',{userid: userid},true)
            .then((res)=>{
                this.userList = res.data;
                this.lectureList = res.data;
                for(var i = 0; i < this.userList.length; i++) {
                    for(var j = 0; j < this.numOfLecture; j++) {
                        data.push(res.data.Data[j]);
                    }
                    this.userid = this.userList[i].user_id;
                }
                this.lectureList = res.data.Data
                this.lectureInfo = data
                this.lectureCount = this.lectureList
            })
            .catch((err)=>{
                console.log(err)
            })
            console.log("######",this.lectureList)
            console.log("######22222",this.lectureInfo)
            console.log("#####!!!!",this.lectureCount)
        },
         getLectureCount() {
            const userid = this.$store.getters.getEmail;
            this.$axiosSend("get", "/api/mypageAll", {userid : userid}, true)
                    .then(res => {
                        if(this.numOfLecture < this.lectureCount) {
                            this.numOfLecture += 3; //수강정보 3개 증가

                        var data = []
                        for (var k = 0; k < this.lectureCount; k++){ //전체 수강정보에서 노출 개수만큼 데이터 추출하여 data배열에 추가
                                data.push(this.lectureList[k]) //전체 수강정보에서 노출
                        }
                                this.lectureInfo = data //lectureInfo객체에 data 배열 업데이트
                        }else {
                                this.dataFull = true //dataFull 객체를 true로 변경
                                alert('List items are fully loaded') //모든 데이터 출력 알림
                        }

                        console.log(res);
                        
                        // 요청이 성공적으로 완료된 후 전체보기 수강정보 목록 페이지로 리디렉션
                        this.$router.push('/mypageAll',{userid : userid}, true)
                    })
                    .catch(error => {
                        // 요청 실패 시 에러 처리
                        console.error(error);
                    });
             
        },
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
