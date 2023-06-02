<template>
    <b-container class="pt-5">
        <iframe width="560" height="315" src="https://www.youtube.com/embed/dQw4w9WgXcQ" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
    </b-container>
</template>

<script>
export default{
    name : 'lectureLesson',
    data() {
        return {
            lessonId : '',
            videoLink : 'https://www.youtube.com/watch?v=dQw4w9WgXcQ',
        }
    },
    methods: {

        
        
    },
    mounted() {
        
    },
    created() {
        this.lessonId = this.$route.query.lessonId;
    },
}

</script>