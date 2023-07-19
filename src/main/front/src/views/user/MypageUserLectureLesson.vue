<template>
    <b-container>
        <div class="contents">
            <div class="title-div">
                <h3 style="text-align: center;" class="fw-bold">
                    {{ userId }} 님의 현재 강의목록
                </h3>
            </div>
        </div>
        <div class="lecturelessoncontainer"><!--v-for="(item, index) in paginatedData" :key="index"-->
            <!-- 강의 목록 -->
            <h2 class="fw-bold my-5" id="videoList-container">강의목록</h2>
                    <ul class="list-unstyled mb-5">
                        <li class="py-3 fs-5 border-bottom border-1">
                            <b-link class="d-flex text-body text-decoration-none" ><!--:to="'/lectureLesson?lectDtlNum='+video.lectDtlNum"-->
                                <span class="me-auto ms-3 fw-bold">
                                    {{ userId }}.
                                    {{ userId }}
                                </span>
                                <span class="ms-auto me-3">
                                    {{ userId }}
                                </span>
                            </b-link>
                        </li><!--v-for="(video,i) in videos" :key="i"  li태그에 들어갈것--> 
                    </ul>
        </div>
    </b-container>
</template>
<script>
export default {
    name : "mypageLectureLesson",
    data() {
       return {
           item : {
                title : '',
                lectDesc : '',
                lectPeriod : ''
           },
           userId : this.$store.state.nickname,
        }
    }, 
    mounted(){
       
    },
    methods : {
        
    }
}

</script>
