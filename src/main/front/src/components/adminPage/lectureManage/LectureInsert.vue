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
            <b-form>
                <b-form-input></b-form-input>
            </b-form>
        </div>
    </div>
</template>

<script>
export default{
    name : 'lectInsert',
    data() {
        return {
            form : {
                title : '',
                teacher : '',
                price : '',
                thumbnail : '',
                period : '',
                contents : '',
            }
        }
    },
    methods: {
        
    },
    computed:{

    }
}

</script>

