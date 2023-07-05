<template>
    <div>
        <b-container>
            <p class="text-center fs-4 fw-bold">검색하기</p>
            <b-form-input class="mb-5" v-model="keyword" @input="filtering"></b-form-input>
            <div class="vh-50 w-100 border rounded mb-5 text-truncate overflow-auto">
                <b-row class="py-1 text-center w-100 mb-3 fw-bold fs-5 m-0">
                    <b-col cols="1"></b-col>
                    <b-col cols="1">번호</b-col>
                    <b-col cols="4">제목</b-col>
                    <b-col cols="2">강사</b-col>
                    <b-col cols="4">소분류</b-col>
                </b-row>
                <b-row v-for="(item,index) in showList" :key="index" class="m-0">
                    <b-col cols="1" class="p-0"><b-form-radio class="m-auto" @change="lectSelect(item)" name="selected"></b-form-radio></b-col>
                    <b-col cols="1">{{ item.lectNum }}</b-col>
                    <b-col cols="4">{{ item.title }}</b-col>
                    <b-col cols="2">{{ item.teacher }}</b-col>
                    <b-col cols="4">{{ item.korCategory }}</b-col> 
                </b-row>
            </div>
        </b-container>
        <b-container>
            <b-form @submit.prevent="lectUpdateFunc">
                <div>
                    <div class="d-flex">
                        <b-form-group
                        description="제목을 입력해주세요"
                        label="제목"
                        label-for="lect-manage-title"
                        class="w-50 me-3"
                        >
                            <b-form-input id="lect-manage-title" required v-model="form.title"></b-form-input>
                        </b-form-group>
                        <b-form-group
                        description="강사의 이름을 입력해주세요"
                        label="강사명"
                        class="w-25 me-3"
                        label-for="lect-manage-teacher"
                        >
                            <b-form-input id="lect-manage-teacher" required v-model="form.teacher"></b-form-input>
                        </b-form-group>
                        <b-form-group
                        description="가격을 입력해주세요"
                        label="가격"
                        label-for="lect-manage-price"
                        >
                            <b-form-input id="lect-manage-price" type="number" v-model="form.price" required ref="price"></b-form-input>
                        </b-form-group>
                    </div>
                    <div class="d-flex">
                        <b-form-group
                        description="썸네일을 업로드해주세요"
                        label="썸네일"
                        label-for="lect-manage-thumbnail"
                        class="w-50 me-3"
                        >
                            <input id="lect-manage-thumbnail" type="file" class="form-control" required @change="fileChange"/>
                        </b-form-group>
                        <b-form-group
                        description="수강 기간을 입력해주세요"
                        label="수강 기간"
                        label-for="lect-manage-period"
                        >
                            <b-form-input type="number" id="lect-manage-period" v-model="form.period"  required ref="period"></b-form-input>
                        </b-form-group>
                    </div>
                    <div>
                        <b-form-group
                        description="강의 요약 설명을 입력해주세요"
                        label="강의 요약"
                        >
                            <b-form-textarea
                            v-model="form.summary"
                            max-rows="5"
                            rows="3"
                            no-resize
                            required
                            ></b-form-textarea>
                        </b-form-group>
                    </div>
                    <div class="mb-3">
                        <ckeditor :editor="editor" v-model="form.contents" :config="editorConfig" ref="contents"></ckeditor>
                    </div>
                    <div class="d-flex">
                        <div class="ms-auto">
                            <b-button class="bedu-bg-custom-blue me-3 px-5 py-2" type="submit">저장</b-button>
                            <b-button class="px-5 py-2">취소</b-button>
                        </div>
                    </div>
                </div>
            </b-form>
        </b-container>
    </div>
</template>

<script>
import Editor from 'ckeditor5-custom-build/build/ckeditor'

export default{
    name : 'lectureUpdate',
    data() {
        return {
            lectList : [],
            showList : [],
            editor : Editor,
            editorConfig : {
                // The configuration of the editor.
                simpleUpload: {
                    // 업로드 URL
                    uploadUrl: '/api/admin/lectManage/ImageUpload',
                    method : 'POST'
                },
                mediaEmbed: {
                    previewsInData: true
                },
                removePlugins: ["MediaEmbedToolbar"],
            },
            form: {
                lectNum : 0,
                title : '',
                teacher : '',
                price : '',
                thumbnail : null,
                period : '',
                contents : '',
                summary : '',
            },
            keyword : '',   
        }
    },
    methods: {
        /** 파일 업로드시 변경 */
        fileChange(e){
           this.form.thumbnail = e.target.files[0]
        },
        /** 강의 조회 */
        getLectureList(){
            this.$axiosSend('get','/api/lect/getAllLectures')
            .then((res)=>{
                this.lectList = res.data.item
                this.showList = res.data.item
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        lectSelect(item){
            this.form.lectNum = item.lectNum;
            this.form.contents = item.lectDesc;
            this.form.summary = item.lectSum;
            this.form.title = item.title;
            this.form.teacher = item.teacher;
            this.form.price = item.price;
            this.form.period = item.lectPeriod;
        },
        filtering(){
            this.showList = this.lectList.filter((item)=> item.title.includes(this.keyword))
        },
        lectUpdateFunc(){
            const formData = new FormData();

            formData.append("vo",this.form);
        }
    },
    computed:{

    },
    mounted() {
        
    },
    created() {
        this.getLectureList();
    },
}

</script>