<!-- npm install v-pagination-3 설치해야함 -->
<template>
    <b-container>
        <div class="contents">
            <div class="title-div">
                <h3 style="text-align: center;" class="fw-bold">
                    {{ userName }} 님의 현재 수강상세정보
                </h3>
            </div>
        </div>
       <!-- <div v-for="(item, index) in lectureDetailInfo" :key="index"> v-for문 넣기-->
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
          <!--  </div>-->
        </b-container>
    
</template>
<script>
export default {
    name : "mypageAll",
    data() {
       return {
           userName : this.$store.state.nickname
        }
    }
}

</script>
