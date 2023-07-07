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
                <b-row v-for="(item,index) in showList" :key="index" class="m-0 mb-1">
                    <b-col cols="1" class="p-0"><b-form-radio class="m-auto" @change="lectSelect(item)" name="selected"></b-form-radio></b-col>
                    <b-col cols="1">{{ item.lectNum }}</b-col>
                    <b-col cols="4">{{ item.title }}</b-col>
                    <b-col cols="2">{{ item.teacher }}</b-col>
                    <b-col cols="4">{{ item.korCategory }}</b-col> 
                </b-row>
            </div>
        </b-container>
        <hr>
        <b-container>
            <b-form @submit.prevent="lectUpdateFunc">
                <div>
                    <div class="d-flex">
                        <div class="w-50 p-1 mt-auto">
                            <b-form-group 
                            description="썸네일을 업로드해주세요"
                            label="썸네일"
                            label-for="lect-manage-thumbnail"
                            class="w-100 me-3"
                            >
                            <div class="ratio ratio-16x9 mb-3">
                                <b-img ref="thumbnail" :src="previewImg != null ? previewImg : require('@/assets/imgs/noImg.jpg')" fluid thumbnail ></b-img>
                            </div>
                            <input :disabled="form.lectNum == 0" id="lect-manage-thumbnail" type="file" class="form-control" @change="fileChange"/>
                            </b-form-group>
                        </div>
                        <div class="w-50 p-1">
                            <b-form-group
                            description="제목을 입력해주세요"
                            label="제목"
                            label-for="lect-manage-title"
                            class="w-100 me-3"
                            >
                                <b-form-input id="lect-manage-title" v-model="form.title" :disabled="form.lectNum == 0"></b-form-input>
                            </b-form-group>
                            <b-form-group
                            description="강사의 이름을 입력해주세요"
                            label="강사명"
                            class="w-100 me-3"
                            label-for="lect-manage-teacher"
                            >
                                <b-form-input id="lect-manage-teacher" v-model="form.teacher" :disabled="form.lectNum == 0"></b-form-input>
                            </b-form-group>
                            <b-form-group
                            description="가격을 입력해주세요"
                            label="가격"
                            label-for="lect-manage-price"
                            >
                                <b-form-input id="lect-manage-price" type="number" v-model="form.price" ref="price" :disabled="form.lectNum == 0"></b-form-input>
                            </b-form-group>
                            <b-form-group
                            description="수강 기간을 입력해주세요"
                            label="수강 기간"
                            label-for="lect-manage-period"
                            >
                                <b-form-input type="number" id="lect-manage-period" v-model="form.period" :disabled="form.lectNum == 0" ref="period"></b-form-input>
                            </b-form-group>
                        </div>
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
                            :disabled="form.lectNum == 0"
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
import axios from 'axios'

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
            newThumbnail : '',
            previewImg : null,
        }
    },
    methods: {
        /** 파일 업로드시 변경 */
        fileChange(e){
            this.newThumbnail = e.target.files[0]
            this.previewImg = URL.createObjectURL(this.newThumbnail);
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
            this.form.thumbnail = item.thumbnail;
            console.log(item.thumbnail)
            this.form.period = item.lectPeriod;
            this.previewImg = item.thumbnail;
        },
        filtering(){
            this.showList = this.lectList.filter((item)=> item.title.includes(this.keyword))
        },
        lectUpdateFunc(){
            const formData = new FormData();

            const updateNum = this.$store.getters.getUsernum;

            formData.append("title",this.form.title);
            formData.append("lectNum", this.form.lectNum);
            formData.append("teacher",this.form.teacher);
            formData.append("price",this.form.price);
            formData.append("period",this.form.period);
            formData.append("contents",this.form.contents);
            formData.append("summary",this.form.summary);
            formData.append("newThumbnail",this.newThumbnail);
            formData.append("oldThumbnail",this.form.thumbnail);
            formData.append("updateNum",updateNum);


            axios.post('/api/admin/lectManage/lectUpdate',formData,{
                headers:{
                        "Content-Type" : "multipart/form-data"
                }
            })
            .then(()=>{
                this.$swal({
                    title : 'success',
                    icon : 'success',
                    text : '강의가 수정되었습니다'
                })
                .then(()=>{
                    this.form.lectNum = 0
                })
            })
            .catch((err)=>{
                console.log(err)
            })
            
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