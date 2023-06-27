<!-- npm install v-pagination-3 설치해야함 -->
<template>
    <b-container>
        <p class="fs-4 fw-bold">마이페이지</p>
    </b-container>
    <b-container>
            <p class="fs-4 fw-bold">현재 수강정보</p>
            <div style="height: 100px; width: 100%; background-color: var(--yellow);" class="rounded-6">
                <b-container class="text-white fw-bold d-flex">
                    <div class="curr-subjectInfo" v-if="lectureList == 0" >
                       <p>수강내역이 없습니다.</p>
                    </div>
                    <div v-else class="lecture">
                        <div class="col-xl-3 col-lg-4 col-md-6" :key="index" v-for="(item,index) in lectureList">
                            <div class="lect text-start" style="width:18rem; border:0px">
                                <a @click="goToInfo(item.lectNum)" style="cursor:pointer">
                                </a>
                            </div>
                        </div>
                    </div>
                </b-container>
            </div>
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <div style="height: 100px; width: 100%; background-color: var(--yellow);" class="rounded-7">
                <b-container class="text-white fw-bold d-flex">
                    <div class="bookmark" >
                        <p>자바</p>
                        <p>파이썬</p>
                        <p></p>
                        <p></p>
                        <p></p>
                    </div>
                </b-container>
            </div>
        </b-container>
        <b-container>
            <p class="fs-4 fw-bold">북마크</p>
            <div style="height: 100px; width: 100%; background-color: var(--yellow);" class="rounded-7">
                <b-container class="text-white fw-bold d-flex">
                    <div class="bookmark" v-if="bookmarkList == 0">
                       <!-- <p>자바</p>
                        <p>파이썬</p>
                        <p></p>
                        <p></p>
                        <p></p>-->
                        <p class="m-4">북마크내역이 없습니다.</p>
                    </div>
                    <div v-else class="bookmark">
                        <div class="col-xl-3 col-lg-4 col-md-6" :key="index" v-for="(bookmark,index) in bookmarkList">
                            <div class="lect text-start" style="width:18rem; border:0px">
                                <a @click="goToDetail(bookmark.id)" style="cursor:pointer">
                                </a>
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
            lectnum : 0,
            lectnm : '',
            lectregdate : '',
            regdate : '',
            regid : '',
            userList : []
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
           this.$axiosSend('get','/api/mypage',{userid: userid},true)
            .then((res)=>{
                this.userList = res.data;
                for(var i=0; i< this.userList.length; i++){
                    this.userid = this.userList[i].user_id;
                }
            })
            .catch((err)=>{
            })
        },
        goToInfo(lectNum) {
            this.$router.push({path:"/lectureList",query:{lectNum : lectNum}})
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

