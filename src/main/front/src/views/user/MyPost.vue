<template>
    <div>
        <div class="p-4 p-md-5 w-100 d-flex">
            <!-- 좌측 네비 -->
            <my-page-cate-navi></my-page-cate-navi>

            <div class="w-100">
                <my-page-cate-navi-toggle></my-page-cate-navi-toggle>

                <!-- 네비 -->
                <div>
                    <b-nav>
                        <b-nav-item>질문&답변</b-nav-item>
                        <b-nav-item>자유게시판</b-nav-item>
                        <b-nav-item class="ms-auto">
                            <b-form-select
                            v-model="orderSelected"
                            :options="orderOptions"></b-form-select>
                        </b-nav-item>
                    </b-nav>
                </div>

                <!-- 리스트 목록 -->
                <div>
                    <b-table>
                        
                    </b-table>
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
    name : 'myPost',
    data() {
        return {
            list : [],
            currentTab : 'qna',
            orderSelected : 'title',
            orderOptions : [
                {
                    text : '제목순',
                    value: 'title'
                },
                {
                    text : '최신순',
                    value : 'newst'
                },
                {
                    text : '오래된순',
                    value : 'oldest'
                },
            ],

        }
    },
    methods: {
        
    },
}

</script>