<template>
    <b-container class="pt-5">
        <div class="mx-auto">

        </div>
    </b-container>
</template>

<script>
export default{
    name : 'lectureLesson',
    data() {
        return {
            lessonInfo : {
                lessonId : '',
                lessonCode : 0,
                lessonKor : '',
                lessonLink : '',
            },
            isAvailable : '',
        }
    },
    methods: {
        /** 강의 정보 및 수강 가능 여부 확인 */
        getLesson(){
            this.$axiosSend('get','/api/lect/getLesson',{
                num : this.lessonInfo.lessonId
            })
            .then((res)=>{
                console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })
        }
        
    },
    mounted() {
        
    },
    created() {
        this.lessonInfo.lessonId = this.$route.query.lessonId;
        this.getLesson();
    },
}

</script>