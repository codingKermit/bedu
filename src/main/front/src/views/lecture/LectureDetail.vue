<template>
    <div>
        <b-container class="py-5">
            <b-container>
                <div>
                    <p class="text-secondary fs-5 ms-3">{{ form.category }}</p>
                </div>
                <div class="d-flex">
                    <div class="w-50 me-5">
                    <b-img thumbnail rounded="5" :src="form.thumbnail" fluid></b-img>
                </div>
                <div class="w-50">
                    <p class="fw-bold fs-3">{{ form.title }}</p>
                    <div>
                        <span v-for="(star, i) in form.stars" :key="i" class="mx-1">
                            <font-awesome-icon :icon="['fas', 'star']" />
                        </span>
                    </div>
                </div>
            </div>
        </b-container>
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
                scoreUser : 0,
                teacher : '',
                duration : 0,
                thumbnail : '',
                view : 0,
                regiDate : '',
                price : 0,
                description : '',
                likeYn : '',
                totalTime : 0,
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
                for(var i = 0; i < 5;i++){
                    if( i <= this.form.score){
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