<template>
    <div>
        <div class="row">
            <div class="col">
                <b-form-group
                description="대분류를 선택해주세요"
                label="대분류">
                    <b-form-select v-model="topCate"></b-form-select>
                </b-form-group>
            </div>
            <div class="col">
                <b-form-group
                description="중분류를 선택해주세요"
                label="중분류">
                    <b-form-select v-model="midCate"></b-form-select>
                </b-form-group>
            </div>
            <div class="col">
                <b-form-group
                description="소분류를 선택해주세요"
                label="소분류">
                    <b-form-select v-model="botCate"></b-form-select>
                </b-form-group>
            </div>
        </div>
        <div>
            <b-form @submit.prevent="lectureInsert">
                <div class="d-flex">
                    <b-form-group
                    description="제목을 입력해주세요"
                    label="제목"
                    label-for="lect-manage-title"
                    class="w-50 me-3"
                    >
                        <b-form-input id="lect-manage-title" v-model="form.title"></b-form-input>
                    </b-form-group>
                    <b-form-group
                    description="강사의 이름을 입력해주세요"
                    label="강사명"
                    class="w-25 me-3"
                    label-for="lect-manage-teacher"
                    >
                        <b-form-input id="lect-manage-teacher"  v-model="form.teacher"></b-form-input>
                    </b-form-group>
                    <b-form-group
                    description="가격을 입력해주세요"
                    label="가격"
                    label-for="lect-manage-price"
                    >
                        <b-form-input id="lect-manage-price"  v-model="form.price"></b-form-input>
                    </b-form-group>
                </div>
                <div class="d-flex">
                    <b-form-group
                    description="썸네일을 업로드해주세요"
                    label="썸네일"
                    label-for="lect-manage-thumbnail"
                    class="w-50 me-3"
                    >
                        <input id="lect-manage-thumbnail" type="file" class="form-control"/>
                    </b-form-group>
                    <b-form-group
                    description="수강 기간을 입력해주세요"
                    label="수강 기간"
                    label-for="lect-manage-period"
                    >
                        <b-form-input id="lect-manage-period"></b-form-input>
                    </b-form-group>
                </div>
                <div class="mb-3">
                    <CkEditorComponentVue :urlProps="urlPath" ></CkEditorComponentVue>
                </div>
                <div class="d-flex">
                    <div class="ms-auto">
                        <b-button class="bedu-bg-custom-blue me-3 px-5 py-2" type="submit">저장</b-button>
                        <b-button class="px-5 py-2">취소</b-button>
                    </div>
                </div>
            </b-form>
        </div>
    </div>
</template>

<script>
import CkEditorComponentVue from '../../CkEditorComponent.vue';

export default{
    name : 'lectInsert',
    components:{CkEditorComponentVue},
    data() {
        return {
            form : {
                title : '',
                teacher : '',
                price : '',
                thumbnail : '',
                period : '',
                contents : '',
            },
            urlPath : '/api/admin/lectManage/ImageUpload',
            topCateCode : '',
            topCateKor : '',
            midCateCode : '',
            midCateKor : '',
            botCateCode : '',
            botCateKor : ''
        }
    },
    methods: {
        /** 강의 저장 */
        lectureInsert(){

        },
        /** 카테고리 조회 */
        getCategories(){
            
        }
    },
    computed:{

    }
}

</script>

