<!-- 작성자 우지원 -->


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
                    </b-nav>
                </div>

                <!-- 리스트 목록 -->
                <div class="w-100">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">제목</th>
                                <th scope="col">작성일자</th>
                                <th scope="col">조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in list" :key="index">
                                <td>{{ item.title }}</td>
                                <td>{{ item.qnaDate }}</td>
                                <td>{{ item.qnaCnt }}</td>
                            </tr>
                            <InfiniteLoading
                            @infinite="infiniteHandler"
                            ref="InfiniteLoading"
                            >
                                <template #spinner>
                                    <!-- 로딩중일때 보여질 부분 -->
                                    <div class="text-center">
                                        <div class="spinner-border" role="status">
                                            <span class="visually-hidden"></span>
                                        </div>
                                    </div>
                                </template>
                                <template #no-more>
                                    <!-- 처리 완료 후, 최하단에 보여질 부분-->
                                    <span></span>
                                </template>
                                <template #no-results>
                                    <!-- 조회 데이터가 없을 때 보여질 부분 -->
                                    <b-container class="text-center py-5">
                                        <p class="fw-bold">검색 결과가 없습니다</p>
                                        <p>단어의 철자가 정확한지 확인해 보세요.</p>
                                    </b-container>
                                </template>
                            </InfiniteLoading>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import MyPageCateNavi from '../../components/myPage/MyPageCateNavi.vue'
import MyPageCateNaviToggle from '../../components/myPage/MyPageCateNaviToggle.vue'
import { InfiniteLoading } from 'infinite-loading-vue3-ts'

export default{
  components: { MyPageCateNavi, MyPageCateNaviToggle, InfiniteLoading },
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
            page : 1,

        }
    },
    methods: {
        infiniteHandler($state){
            this.$axiosSend('get','/api//qna/nameList',{
                userName : this.$store.getters.getNickname,
                page : this.page
            })
            .then((res)=>{
                if(res.data.length){
                    this.page++;
                    this.list.push(...res.data);
                    $state.loaded();
                } else {
                    if(this.page == 1){
                        $state.reset();
                    }
                    $state.complete();
                }
            })
            .catch((err)=>{
                console.log(err)
            })
        },
    },
    watch:{
        currentTab(){
            this.list = [];
            this.page = 1;
            this.$refs.InfiniteLoading.stateChanger.reset();

        }
    },
}

</script>