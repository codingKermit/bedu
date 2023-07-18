<template>
    <div>
        <b-container class="h-100 pt-5">
            <b-row class="row pt-5" cols="1" cols-lg="2">
                <b-col class="mb-3 mb-lg-0">
                    <b-container class="border-4 border-dark border rounded-5 p-0 overflow-hidden"
                    @mouseenter="mouseover('month')" @mouseleave="mouseleave('month')" >
                        <div class="h-25 bedu-bg-custom-yellow-75 p-5" ref="month">
                            <p class="text-start fs-3 fw-bold">월간 구독</p>
                            <p>B:EDU의 강의를 무제한으로 수강</p>
                        </div>
                        <div class="p-5">
                            <p><span class="fs-3 fw-bold">39,800원</span>/월</p>
                            <b-button class="w-100 p-3 bedu-bg-custom-blue mb-3" @click="getSubscribe('month')">구독하기</b-button>
                            <p>+ 모든 영상/노트/실습 강의 무제한 수강</p>
                            <p>+ 강의/로드맵 추천</p>   
                            <p>+ Q&A 커뮤니티 이용</p>
                            <p>+ 매달 신규 강의 오픈</p>
                            <p>+ 웹 코드 실행기 무제한 이용</p>
                            <p>+ 개인별 맞춤 수강 독려</p>
                            <p>+ 코딩 챌린지 참여</p>
                        </div>
                    </b-container>
                </b-col>
                <b-col>
                    <b-container class="border-4 border-dark border rounded-5 p-0 overflow-hidden"
                    @mouseover="mouseover('year')" @mouseleave="mouseleave('year')" >
                        <div class="h-25 bedu-bg-custom-yellow-75 p-5" ref="year">
                            <p class="text-start fs-3 fw-bold">연간 구독</p>
                            <p>B:EDU의 강의를 무제한으로 수강</p>
                        </div>
                        <div class="p-5 pt-4">
                            <span class="fs-6 fw-bold bedu-text-custom-yellow">월간 결제보다 31% 할인</span>
                            <p><span class="fs-3 fw-bold">27,417원</span>/월</p>
                            <b-button class="w-100 p-3 bedu-bg-custom-blue mb-3" @click="getSubscribe('year')">구독하기</b-button>
                            <p>+ 모든 영상/노트/실습 강의 무제한 수강</p>
                            <p>+ 강의/로드맵 추천</p>
                            <p>+ Q&A 커뮤니티 이용</p>
                            <p>+ 매달 신규 강의 오픈</p>
                            <p>+ 웹 코드 실행기 무제한 이용</p>
                            <p>+ 개인별 맞춤 수강 독려</p>
                            <p>+ 코딩 챌린지 참여</p>
                        </div>
                    </b-container>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
export default{
    name : 'membership',
    data() {
        return {
            
        }
    },
    methods: {
        mouseover(type){
            let tar = this.$refs[type]
            tar.classList.replace('bedu-bg-custom-yellow-75','bedu-bg-custom-yellow')
        },
        mouseleave(type){
            let tar = this.$refs[type]
            tar.classList.replace('bedu-bg-custom-yellow','bedu-bg-custom-yellow-75')
        },
        getSubscribe(type){

            /*

             결제 API 호출 후 완료 되었다는 전제 하에 코드 작성
            
             */

            // 구독자 저장하는 익명 함수
            const addToSub = () =>{
                if(result){
                    this.$axiosSend('get','/api/membership/getSubscribe',{
                        nickName : this.$store.getters.getNickname,
                        type : type,
                    })
                    .then((res)=>{
                        console.log(res)
                    })
                    .catch((err)=>{
                        console.log(err)
                    })
                } else{
                    this.$swal({
                        title: 'Error',
                        icon: 'error',
                        text : '결제에 실패했습니다'
                    })
                }
            }
            

            this.$swal({
                title : '구독하시겠습니까?',
                icon : 'info',
                text : 'B:EDU에 구독하세요!',
                showCancelButton: true,
                cancelButtonText : '돌아가기',
                confirmButtonText : '결제하기',
            })
            .then((result)=>{
                if(result.isConfirmed){
                    addToSub();
                }
            })
            
            var result = true;




        },
    },
    
}


</script>