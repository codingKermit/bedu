<template>
    <b-container>
        <p class="fs-4 fw-bold" style="padding-top: 5rem;">현재 수강정보</p>
            <b-container class="text-dark fw-bold">
                    <!-- v-if문 이용하여  수강내역이 없을때 수강내역이 없다는 문구 보이게-->
                    <div class="curr-subjectInfo" v-if="lectureListFirst == 0">
                       <b-container class="w-75 ms-auto py-5">
                            <p style="text-align: center;">수강내역이 없습니다.</p>
                       </b-container>
                    </div>
                    <!--v-else를 이용하여 수강내역이 있을때이라면 현재 수강정보가 보이게-->
                    <div v-else class="lecture">
                        <div style="text-align: right;">
                            <a  @click="getLectureCount" style="cursor:pointer; text-align: right;">전체보기</a>
                        </div>
                        <div class="mypagecontainer" style="float:left;" v-for="(item, index) in lectureListFirst" :key="index">
                            <div class="lect text-start">
                               <!--  링크걸어서 화면 이동 테스트중 -->
                                <b-link class="text-decoration-none text-body h-100 d-block" :to='"/mypageAll"'>
                                    <div class="mypageInfo">
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
                                </b-link>
                            </div>
                        </div>
                    </div>
            </b-container>
            <b-container class="mypagebookmark">
                <div class="mypageBookmark">
                    <b-container>
                        <p class="fs-4 fw-bold">북마크</p>
                            <b-container class="w-75 ms-auto py-5">
                                    <!-- v-if문 이용하여  북마크 내역이 없을때 북마크내역이 없다는 문구 보이게-->
                                    <div class="curr-subjectInfo" ><!--v-if="bookmarkFirst == 0"-->
                                        <b-container class="w-75 ms-auto py-5">
                                                <p style="text-align: center;">북마크가 없습니다.</p>
                                        </b-container>
                                    </div>
                                   <!--v-else를 이용하여 수강내역이 있을때이라면 현재 수강정보가 보이게-->
                                   <!-- <div  class="bookMark"> --><!--v-else-->
                                        <!--
                                        <div style="text-align: right;">
                                            <a  @click="getLectureCount" style="cursor:pointer; text-align: right;">전체보기</a>
                                        </div>
                                        <div class="mypagebookmarkcontainer" style="float:left;" v-for="(item, index) in bookmarkFirst" :key="index">
                                            <div class="lect text-start">-->
                                            <!--  링크걸어서 화면 이동 테스트중 -->
                                                <!--<b-link class="text-decoration-none text-body h-100 d-block" :to='"/mypageAll"'>
                                                    <div class="mypagebookmarkInfo">
                                                        <div class="mypagebookmarkContain">
                                                            <p class="fw-bold">
                                                                <span>강좌이름 : </span> {{ item.title }}
                                                            </p>
                                                            <p class="fw-bold">
                                                                <span>강의설명 : </span> {{ item.lectDesc }}
                                                            </p>
                                                            <p class="fw-bold">
                                                                <span>수강기간 : </span> {{ item.lectPeriod }}
                                                            </p>
                                                        </div>
                                                    </div>
                                                </b-link>
                                            </div>
                                        </div>
                                    </div>-->
                            </b-container>
                    </b-container>
                </div>
        </b-container>        
    </b-container>
    
</template>

<style>
.mypagecontainer{
    border-radius : 15px;
    float:left;
    width : 20rem;
    padding : 4px;
    margin-right: 10px;
    text-align : left;
    border : 1px solid rgb(15, 10, 1);
}
.mypagebookmark{
    margin-top : 20rem;
    display: flex;
    flex-direction: column;
}
</style>
<script>

export default {
    name : "mypage",
    data() {
       return {
           // bookmarkList : [],
            title : '',
            lectDesc : '',
            lectPeriod : '',
            lectureListFirst : [], //수강내역 전체 데이터
            lectureInfo : {}, //화면에 노출되는 수강내역 데이터
            lectureCount : 0, //수강내역 전체보기 출력
            numOfLecture : 3, //처음에 출력할 수강내역 개수
            dataFull : false,
            userid : this.$store.state.email,
            userlectnum : 0,
            lectNum : 0,
            lectregdate : '',
            regdate : '',
            regid : '',
        }
    }, 
    created (){
        this.getLectureList();
    },
    methods : {
        /* 마이페이지 홈(유저아이디 가져오기, 데이터 출력) */
        getLectureList(){
            const userid = this.$store.getters.getEmail;
            let data = [];
            this.$axiosSend('get','/api/mypage',{userid: userid},true)
            .then((res)=>{
                for(var i = 0; i < this.numOfLecture; i++) {
                    data.push(res.data[i])
                }
                this.lectureListFirst = res.data
                this.lectureInfo = data
                this.lectureCount = this.lectureListFirst.length
                
            })
            .catch((err)=>{
                console.log(err)
            })
        },
         getLectureCount() {
            const userid = this.$store.getters.getEmail;
            this.$axiosSend("get", "/api/mypageAll", {userid: userid}, true)
                    .then(res => {
                        if(this.numOfLecture < this.lectureCount) {
                            this.numOfLecture += 3; //수강정보 3개 증가

                        var data = []
                        for (var k = 0; k < this.lectureCount; k++){ //전체 수강정보에서 노출 개수만큼 데이터 추출하여 data배열에 추가
                                data.push(this.lectureListFirst[k].lectureInfo) //전체 수강정보에서 노출
                        }
                                this.lectureInfo = data //lectureInfo객체에 data 배열 업데이트
                        }else {
                                this.dataFull = true //dataFull 객체를 true로 변경
                                alert('List items are fully loaded') //모든 데이터 출력 알림
                        }

                        console.log(res);
                        
                        // 요청이 성공적으로 완료된 후 전체보기 수강정보 목록 페이지로 리디렉션
                        this.$router.push('/mypageAll',{userid: userid}, true)
                    })
                    .catch(error => {
                        // 요청 실패 시 에러 처리
                        console.error(error);
                    });
             
        },
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
    }
}
</script>
