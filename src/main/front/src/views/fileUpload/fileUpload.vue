<template>
    <div>
        <b-form class="">
            <input @change="onInputVideo()" id="input" type="file" accept="video/*">
        </b-form>
    </div>
    <button class="send-button" @click="onClickFormButton">
        영상업로드
    </button>
</template>


<script>
    export default {
    data() {
        return {
            input: {
                teacher: '',
                lectName: '',
                video:''
            }
        }
    },
    method: {
        onInputVideo() {
            this.input.video = this.$refs.serveyVideo.videos
            console.log("this.input.video")
        },
    }
    }
</script>
