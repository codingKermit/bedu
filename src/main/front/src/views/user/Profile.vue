<template>
    <div>
        <div class="p-4 p-md-5 w-100 d-flex">
            <!-- 좌측 네비 -->
            <my-page-cate-navi></my-page-cate-navi>

            <div class="w-100">
                <my-page-cate-navi-toggle></my-page-cate-navi-toggle>

                <div class="fs-3 mb-5">
                    <p class="fw-bold">닉네임</p>
                    <b-container class="bedu-bg-custom-blue rounded-3">
                        <div class="text-white p-2">{{ this.$store.getters.getNickname }}</div>
                    </b-container>
                </div>
                <div class="fs-3 mb-5">
                    <p>이메일</p>
                    <b-container class="bedu-bg-custom-blue rounded-3">
                        <div class="text-white p-2">{{ this.$store.getters.getEmail }}</div>
                    </b-container>
                </div>
                <div class="mb-5">
                    <b-button class="fs-4">비밀번호 변경</b-button>
                </div>
                <div>
                    <b-button variant="danger" class="fs-4">회원탈퇴</b-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import MyPageCateNavi from '../../components/myPage/MyPageCateNavi.vue'
import MyPageCateNaviToggle from '../../components/myPage/MyPageCateNaviToggle.vue'
export default{
  components: { MyPageCateNavi, MyPageCateNaviToggle },
    name : 'profile',
    data() {
        return {
            
        }
    },
    methods: {
        
    },
}

</script>