<template>
    <div class="adminPage">
        <b-container class="py-5">
            <p class="fs-1 fw-bold">관리자 페이지</p>
            <div class="row p-5 fs-3">
                <div class="col">
                    <b-container>
                        <p class="fs-3 fw-bold ">강의</p>
                        <ul>
                            <li class="mb-3 ">
                                <b-link to="fileManage" class="text-decoration-none text-body">
                                    동영상 관리
                                </b-link>
                            </li>
                            <li class="mb-3">
                                <b-link to="lectureManage" class="text-decoration-none text-body">
                                    강의 관리
                                </b-link>
                                </li>
                            <li class="mb-3">
                                <b-link class="text-decoration-none text-body">
                                    카테고리 관리
                                </b-link>
                            </li>
                        </ul>
                    </b-container>
                </div>
                <div class="col"></div>
                <div class="col"></div>
            </div>
        </b-container>
    </div>
</template>

<script>
export default{
    name : 'adminPage',
    data() {
        return {

        }
    },
    methods: {
        
    },
    computed : {

    },
    mounted() {
        
    },
    created() {
        
    },
}

</script>