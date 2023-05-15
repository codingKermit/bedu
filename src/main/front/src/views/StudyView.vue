<template>
    <div>
        <b-container class="text-center">
            <p class="fw-bold fs-1">어떤 강의를 찾고 있나요?</p>
            <p class="fs-4 fw-light">What's wanna be?</p>
        </b-container>
        <b-form>
            <div class="">

            </div>
        </b-form>
        {{ this.yellow }}
    </div>
</template>




<script>
export default{
    name : 'StudyView',
    data() {
        return {
            blue : this.$blue,
            black : this.$black,
            yellow : this.$yellow
        }
    },
    methods: {
        
    },
    mounted() {
        
    },
    created() {
        
    },
}


</script>


<style scoped>

</style>