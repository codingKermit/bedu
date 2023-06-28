<!-- npm install v-pagination-3 설치해야함 -->
<template>
    <b-container>
        <p class="fs-4 fw-bold">마이페이지</p>
    </b-container>
    <b-container>
            <p class="fs-4 fw-bold">현재 수강정보</p>
            <div class="bg-light text-white">
                <b-container class="text-dark fw-bold">
                    <div class="curr-subjectInfo" v-if="userList == '' || lectureList == 0" >
                       <b-container class="w-75 ms-auto py-5">
                            <p style="text-align: center;">수강내역이 없습니다.</p>
                       </b-container>
                    </div>
                    <div v-else class="lecture">
                        <div class="col-xl-3 col-lg-4 col-md-6" :key="index" v-for="(item,index) in lectureList">
                            <div class="lect text-start" style="width:18rem; border:0px">
                               <!-- <a @click="goToInfo(item.lectNum)" style="cursor:pointer">
                                </a>-->
                                <b-container class="w-75 ms-auto py-5">
                                    <b-container class="border rounded-3 py-3 mb-2">
                                        <p class="fw-bold">프로그래밍 배워봅시다</p>
                                        <p>
                                            <span>닉네임 : </span> {{ title }}
                                        </p>
                                        <p>
                                            <span>강의이름 : </span> {{ lectnm }}
                                        </p>
                                    </b-container>
                                </b-container>
                            </div>
                        </div>
                    </div>
                </b-container>
            </div>
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <div class="bg-light text-white">
                <b-container class="w-75 ms-auto py-5">
                    <div class="text-dark fw-bold" >
                        <p style="text-align: center;">자바</p>
                        <p style="text-align: center;">파이썬</p>
                        <p></p>
                        <p></p>
                        <p></p>
                    </div>
                </b-container>
            </div>
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <div class="bg-light text-white">
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
            </div>
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
            userid : this.$route.query.email,
            lectnm : this.$store.state.lectnm,
            lectregdate : '',
            regdate : '',
            regid : '',
            userList : [],
            lectnum : this.$store.state.lectnum,
            title : this.$store.state.nickname
            
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
        getLectureList(){
            const userid = this.$store.getters.getEmail;
            //const lectnum = this.$store.getters.getLectNum;
            this.$axiosSend('get','/api/mypage',{userid: userid },true)
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
       // goToInfo(lectNum) {
       //     this.$router.push({path:"/lectureList",query:{num : lectNum}})
       // },
        
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

