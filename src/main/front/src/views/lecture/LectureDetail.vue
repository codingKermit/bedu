<template>
    <div>
        <b-container class="py-5">
                <!-- category -->
                <div>  
                    <p class="text-secondary fs-5 ms-3">{{ form.category }}</p>
                </div>

                <!-- thumbnail -->
                <div class="d-flex">
                    <div class="w-50 me-5">
                    <b-img thumbnail rounded="5" :src="form.thumbnail" fluid></b-img>
                </div>

                <!-- info start-->
                <div class="w-50">
                    <p class="fw-bold fs-3">{{ form.title }}</p>

                    <!-- score start-->
                    <div class="d-flex mb-3">
                        <div>
                            <span v-for="(star, i) in form.stars" :key="i" class="mx-1 fs-5">
                                <font-awesome-icon :class="star =='y' ? 'text-danger' : 'text-secondary'" :icon="star =='y' ? ['fas','star'] : ['fas','star']"/>
                            </span>
                        </div>
                        <div class="ms-2 pt-1 align-middle">
                            {{ form.score }} 점 ( {{ form.scoreUsers }} 개)
                        </div>
                    </div>
                    <!-- score end-->
                    <div class="list-unstyled align-middle w-100">
                        <div class="mb-2 row"><span class="text-secondary col-3">강사</span><span class="text-body col">{{ form.teacher }}</span></div>
                        <div class="mb-2 row"><span class="text-secondary col-3">수강기간</span><span class="text-body col"> 구매일로부터 {{ form.duration }}일</span></div>
                        <div class="mb-2 row"><span class="text-secondary col-3">학습시간</span><span class="text-body col">{{ form.totalTimes }}</span></div>
                    </div>
                <!-- info end-->

                </div>
            </div>
        </b-container>
    </div>
</template>

<script>
export default{
    name : 'lectureDetail',
    data() {
        return {
            form: {
                num : this.$route.query.num,
                title : '',
                score : 0,
                scoreUsers : 0,
                teacher : '',
                duration : 0,
                thumbnail : '',
                view : 0,
                regiDate : '',
                price : 0,
                description : '',
                likeYn : '',
                totalTimes : 0,
                category : '',
                stars : ''
            }            
        }
    },
    methods: {
        getDetail(){
            this.$axios.get('/api/lectureDetail',{
                params:{
                    num : this.form.num
                }
            })
            .then((res)=>{
                this.form=res.data;
                this.form.stars = [];


                // 별점 체크하기 위한 배열 생성
                for(var i = 0; i < 5;i++){
                    if( i < this.form.score){
                        this.form.stars.push('y')
                    } else{
                        this.form.stars.push('n')
                    }
                }
            })
            .catch((err)=>{console.log(err)})
        }
    },
    mounted() {
        this.getDetail();
    },
    created() {
        
    },
}

</script>


<style scoped>

b-img{
    width: 100%;
}


</style>