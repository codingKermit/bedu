<template>
    <b-container>
        <b-container>
            <p class="fs-4 fw-bold">마이페이지</p>
        </b-container>
        <p class="fs-4 fw-bold">현재 수강정보</p>
            <b-container class="text-dark fw-bold">
                    <!-- v-if문 이용하여  수강내역이 없을때 수강내역이 없다는 문구 보이게-->
                    <div class="curr-subjectInfo" v-if="userList == null || lectureListFirst == null">
                       <b-container class="w-75 ms-auto py-5">
                            <p style="text-align: center;">수강내역이 없습니다.</p>
                       </b-container>
                    </div>
                    <!--v-else를 이용하여 userList가 있고 수강내역이 있을때이라면 현재 수강정보가 보이게-->
                    <div v-else class="lecture">
                        <div style="text-align: right;"><!-- v-if="lectureCount+0 > numOfLecture+0"나중에 div 안에 넣어야함-->
                            <a  @click="getLectureCount" style="cursor:pointer; text-align: right;">전체보기</a>
                        </div>
                        <div class="col-xl-3 col-lg-4 col-md-6 "> <!--v-for="(item, index) in lectureListFirst" :key="index"-->
                            <div class="lect text-start">
                               <!--  링크걸어서 화면 이동 테스트중 -->
                                <b-link class="text-decoration-none text-body h-100 d-block" :to='"/mypageAll"'>
                                    <div class="mypageInfo">
                                        <div class="mypagecontainer">
                                                <p class="fw-bold">프로그래밍 배워봅시다</p>
                                                <p>
                                                    <span>강좌이름 : </span> {{ lectNm }}
                                                </p>
                                                <p>
                                                    <span>강의설명 : </span> {{ lectNm }}
                                                </p>
                                                <p>
                                                    <span>수강기간 : </span> {{ lectNm }}
                                                </p>
                                        </div>
                                    </div>
                                </b-link>
                            </div>
                        </div>
                    </div>
                </b-container>
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
<style>
.mypagecontainer{
    display: table-cell;
    float : left;
    width : 20rem;
    padding : 10px;
    margin-right: 10px;
    text-align : left;
    vertical-align : middle;
    border : 1px solid rgb(15, 10, 1);
}

</style>
<script>

export default {
    name : "mypage",
    data() {
       return {
           // bookmarkList : [],
            lectureListFirst : [], //수강내역 전체 데이터
            lectureInfo : {}, //화면에 노출되는 수강내역 데이터
            lectureCount : 0, //수강내역 전체보기 출력
            numOfLecture : 3, //처음에 출력할 수강내역 개수
            dataFull : false,
            userlectnum : 0,
            userNum : this.$store.state.usernum,
            lectNum : 0,
            lectregdate : '',
            regdate : '',
            regid : '',
            userList : [],
            lecttitle : this.$store.state.title,
            username : this.$store.state.nickname,
            lectNm : this.$store.state.lectnm
        }
    }, 
    created (){
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
            const userNum = this.$store.getters.getUsernum;
            let data = [];
            let lectureListFirst = []
            this.$axiosSend('get','/api/mypage',{userNum: userNum},true)
            .then((res)=>{
               this.lectureListFirst = res.data;
               this.userList = res.data;
            //    for(var l = 0; l<this.userList.length; l++) {
            //     for(var i = 0; i < this.lectureListFirst.length; i++) {
            //         lectureListFirst.push(res.data.Data[i]);
            //     }
            //         this.userList.push(res.data.Data[l])
            // }
                this.lectureListFirst = res.data.Data
                this.lectureInfo = data
                this.lectureCount = this.lectureListFirst
                this.userNum;
                this.lectNum;
                this.lectNm;
            })
            .catch((err)=>{
                console.log(err)
            })
            console.log("######",this.lectureListFirst)
            console.log("######22222",this.lectureInfo)
            console.log("#####!!!!",this.lectureCount)
            console.log("#####!!!!@@@@@@@",userNum)
        },
         getLectureCount() {
            const userNum = this.$store.getters.getUsernum;
            this.$axiosSend("get", "/api/mypageAll", {userNum: userNum}, true)
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
                        this.$router.push('/mypageAll',{userNum: userNum}, true)
                    })
                    .catch(error => {
                        // 요청 실패 시 에러 처리
                        console.error(error);
                    });
             
        },
    },
    watch:{ // 쿼리 데이터 변경되면 데이터도 변경되도록 설정
            '$route.query.lectNum':{
                immediate: true,
                handler(lectNum){
                    this.userid = lectNum;
                },
            }
        }
}
</script>
