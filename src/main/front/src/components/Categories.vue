<template>
    <div>
        <b-container class="bg-secondary rounded-3 bg-opacity-10 py-3 mb-2">
            <img :src="imgPath">
        </b-container>
        <text class="fw-bold d-block">{{ title }}</text>
    </div>
</template>

<script>
export default{
    name : 'lectureCategories',
    data() {
        return {

        }
    },
    methods: {
        
    },
    created() {
        
    },
    mounted() {
        
    },
    props:{
        imgPath : String,
        title : String,
    }
}


</script>

<style scoped>

img{
    width: 70%;
    height: 70%;
}

/* .bg-secondary{
    width: 90px;
    height: 90px;
} */

</style>