<template>
    <div>
        <ckeditor :editor="editor" :config="editorConfig"></ckeditor>
    </div>
</template>

<script>
import Editor from 'ckeditor5-custom-build/build/ckeditor'

export default{
    name : 'ckeditorCom',
    data() {
        return {
            editor : Editor,
            editorConfig : {
                // The configuration of the editor.
                simpleUpload: {
                    // 업로드 URL
                    uploadUrl: this.urlProps,
                    method : 'POST'
                },
                mediaEmbed: {
                    previewsInData: true
                }
            },
        }
    },
    props : {
        urlProps : String,
    }
}

</script>