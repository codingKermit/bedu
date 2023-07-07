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
            <p class="fs-4 fw-bold text-center">선택된 강의 정보</p>
            <div class="d-flex">
                <b-form-group
                    label="강의번호"
                    class="w-25 mx-1"
                >
                    <b-form-input v-model="targetLect.lectNum" readonly></b-form-input>
                </b-form-group>
                <b-form-group
                    label="강의명"
                    class="w-25 mx-1"
                >
                    <b-form-input v-model="targetLect.title" readonly></b-form-input>
                </b-form-group>
                <b-form-group
                    label="강사명"
                    class="w-25 mx-1"
                >
                <b-form-input v-model="targetLect.teacher" readonly></b-form-input>
                </b-form-group>
                <b-form-group
                    label="카테고리"
                    class="w-25 mx-1"
                >
                    <b-form-input v-model="targetLect.cateKor" readonly></b-form-input>
                </b-form-group>
            </div>
            <div class="text-end">
                <b-button class="bedu-bg-custom-blue px-4 me-2" @click="lectDelete">삭제</b-button>
                <b-button class="px-4">취소</b-button>
            </div>
        </b-container>
    </div>
</template>

<script>
export default{
    name : 'lectureDelete',
    data() {
        return {
            lectList : [],
            showList : [],
            targetLect : {
                lectNum : '',
                teacher : '',
                title : '',
                thumbnail : '',
                lectDesc : '',
                cateCode : '',
                cateKor : '',
            },
        }
    },
    methods: {
        filtering(){
            this.showList = this.lectList.filter((item)=> item.title.includes(this.keyword))
        },
        /** 강의 조회 */
        getLectureList(){
            this.$axiosSend('get','/api/lect/getAllLectures')
            .then((res)=>{
                this.lectList = res.data.item
                this.showList = res.data.item
                this.targetLect.lectNum = '';
                this.targetLect.cateKor = '';
                this.targetLect.title = '';
                this.targetLect.teacher = '';
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        lectSelect(item){
            this.targetLect.lectNum = item.lectNum;
            this.targetLect.thumbnail = item.thumbnail;
            this.targetLect.lectDesc = item.lectDesc;
            this.targetLect.cateCode = item.category;
            this.targetLect.cateKor = item.korCategory;
            this.targetLect.title = item.title;
            this.targetLect.teacher = item.teacher;

        },
        lectDelete(){
            if(this.targetLect.lectNum == '' || this.targetLect.lectNum == 0 || this.targetLect.lectNum == null){
                this.$swal({
                    title : '강의를 선택해주세요',
                    icon : 'info',
                    text : '강의가 선택되지 않았습니다.'
                })
                return;
            }
            this.$swal({
                title : '정말 삭제하시겠습니까?',
                text : '강의를 삭제하면 복구할 수 없습니다',
                showCancelButton : true,
                cancelButtonText : '아니오',
                confirmButtonText : '예'
            })
            .then((result)=>{
                if(result.isConfirmed){
                    this.$axiosSend('get','/api/admin/lectManage/lectDelete',{
                        num : this.targetLect.lectNum,
                        thumbnail : this.targetLect.thumbnail,
                        lectDesc : this.targetLect.lectDesc,
                    })
                    .then(()=>{
                        this.$swal({
                            title : 'success',
                            icon : 'success',
                            text : '강의가 삭제되었습니다'
                        })
                    })
                    .finally(()=>{
                        this.getLectureList();
                    })
                }
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