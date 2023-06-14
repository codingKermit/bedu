<template>
    <div>
        <h2>강의업로드</h2>
    </div>

<div class="input-group mb-3">
    <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-small">
</div>

<div class="input-group mb-3">
    <span class="input-group-text" id="inputGroup-sizing-default">강사이름</span>
    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-small">
</div>

<div class="mb-3">
    <label for="formFile" class="form-label"></label>
    <input class="form-control" type="file" id="formFile">
</div>

<div>
    <button>Upload</button>
</div>
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
