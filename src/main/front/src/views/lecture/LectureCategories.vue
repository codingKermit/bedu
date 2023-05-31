<template>
    <div>
        <div class="p-5 w-100 d-flex"> <!-- 좌측 네비, 커리큘럼 컨테이너 -->
            <div class="w-25 ps-5 me-5"> <!-- 네비 컨테이너 -->
                <p class="fs-2 fw-bold mb-3">분야별 강의</p>
                <ul class="nav flex-column w-100">
                    <!-- 대분류 -->
                    <li class="nav-item me-4" v-for="(top, top_index) in categories" :key="top_index"> 
                    <span>
                        <a class="fs-5 text-body text-decoration-none d-flex" data-bs-toggle="collapse" :href='"#top-"+top.cate_code'>
                            <p>{{ top.cate_kor }}</p>
                            <p class="ms-auto">
                                <font-awesome-icon :icon="['fas','caret-down']" />
                            </p>
                        </a>
                    </span>
                        <div class="collapse text-secondary text-secondary mid-cate-container" :id='"top-"+top.cate_code'
                        :class='cnt_top_cate == top.cate_code ? "show":""'
                        >
                            <ul class="ps-2">
                                <!-- 중분류 -->
                                <li v-for="(mid, mid_index) in top.children" :key="mid_index" class="list-unstyled py-2"
                                >
                                <router-link
                                :to='"/lectureCategories/"+top.cate_code+"?cnt_mid_cate="+mid.cate_code'
                                class="text-decoration-none"
                                :class="mid.cate_code == cnt_mid_cate && top.cate_code == cnt_top_cate ? 'cnt_selected':'text-body'"
                                >
                                    {{ mid.cate_kor }}
                                </router-link>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="w-75 pe-5"><!-- 강의 기본정보 & 커리큘럼 컨테이너-->
                <p class="fs-2 fw-bold py-3">{{ cnt_mid_cate_kor }}</p>
                <p class="fs-3 fw-bold curriculum-head my-3">커리큘럼</p>
                <ul class="list-unstyled">
                    <!-- 소분류 목록 -->
                    <li
                    :class="index%2 == 0 ? 'curriculum-list-even':'curriculum-list-odd'" 
                    v-for="(bot, index) in lectures" :key="index"
                    class="my-4 align-middle"
                    >
                    <div>
                        <a class="d-flex text-body text-decoration-none my-auto fs-4 mb-4" 
                            data-bs-toggle="collapse" 
                            :href='"#bot-cate-list-"+index'
                        >
                            <span class="bot-cate-step">STEP {{ index+1 }}</span>  
                            <div class="bot-cate-split"></div>
                            <span class="text-body">{{ bot.cate_kor }}</span> 
                            <span class="ms-auto">
                                <font-awesome-icon :icon="['fas','caret-down']" />
                            </span>
                        </a>
                        <!-- 커리큘럼 목록 콜랩스 -->
                        <div class="collapse multi-collapse" :id='"bot-cate-list-"+index' ref="lecture_list"
                        :class='index == 0 ? "show":""'
                        >
                            <b-container>
                                <b-row cols="2">
                                    <!-- 커리큘럼 목록 -->
                                    <b-col v-for="(lect, index) in bot.item" 
                                    :key="index">
                                    <!-- {{ lect }} -->
                                        <b-link class="text-body text-decoration-none" :to='"/lectureDetail?num="+lect.lect_num'>
                                            <b-container class="border p-3 mb-4">
                                                <b-img thumbnail :src="lect.thumbnail" class="w-100 h-100 mb-2"></b-img>
                                                <p class="fs-5 fw-bold">{{ lect.title }}</p>
                                                <div class="d-flex">
                                                    <p class="teacher-container">{{ lect.teacher }} 선생님</p><p>총 {{ lect.total }}강</p>
                                                </div>
                                                <p class="text-secondary">수강 기간 : {{ lect.lect_period }}일</p>
                                            </b-container>
                                        </b-link>
                                    </b-col>
                                </b-row>
                            </b-container>
                        </div>
                    </div> 
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default{
    name : 'lectureCategories',
    data() {
        return {
            categories: [],
            cnt_mid_cate : '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_mid_cate_kor : '', // 현재 보고 있는 대분류의 한글 코드명
            cnt_top_cate : '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_top_cate_kor : '', // 현재 보고 있는 대분류의 한글 코드명
            lectures: [], // 소분류에 있는 강의 목록
        }
    },
    methods: {
        /** 카테고리 변경체크 */
        midCategoryChanger(top,mid){
            this.cnt_mid_cate = mid.cate_code;
            this.cnt_mid_cate_kor = mid.cate_kor;
            this.cnt_top_cate = top.cate_code;
            this.cnt_top_cate_kor = top.cate_kor;
            this.$route.params.index = top.cate_code;
            this.$route.query.mid = mid.cate_code;
        },
        /* 현재 선택된 대분류, 중분류를 기준으로 소분류 데이터 반환 */
        getCurrCurriculum() {
            const curriculum = this.categories.find((category) => category.cate_code === this.cnt_top_cate)
                ?.children.find((children) => children.cate_code === this.cnt_mid_cate)?.children;
            return curriculum || [];
        },

        /** 마운트시 카테고리 조회하는 함수 */
        getCategory(){
            this.$axios.get('/api/getCategory')
            .then((res)=>{
                const data = res.data;
                this.convertToHierarchy(data); // 트리구조로 변경하는 함수 호출
            })
            .catch((err)=>{console.log(err)})
        },

        /** 받은 카테고리를 트리 구조로 변경하는 함수 */
        convertToHierarchy(data) {
            const map = {}; // 부모-자식 관계를 저장할 맵
            // 맵에 카테고리 코드를 키로하여 카테고리 객체를 저장
            data.forEach(category => {
                category.children = []; // 자식 카테고리를 저장할 배열 생성
                map[category.cate_code] = category;
            });
            
            const hierarchy = []; // 최상위 부모 카테고리를 저장할 배열
            
            // 부모-자식 관계 설정
            data.forEach(category => {
                const parentCode = category.parent_code;
                if (parentCode) {
                const parent = map[parentCode];
                if (parent) {
                    parent.children.push(category);
                }
                } else {
                hierarchy.push(category);
                }
            });
            this.categories = hierarchy;
            this.cnt_top_cate = this.$route.params.index;
            this.cnt_mid_cate = this.$route.query.cnt_mid_cate;

        },
            /** 중분류에 따른 강의 정보 조회 함수 */
            getLectureList(){
                this.$axios.get('/api/lectureList',{
                    params:{
                        category : this.cnt_mid_cate
                    }
                })
                .then((res)=>{
                    this.lectures = res.data.item;
                })
                .catch((err)=>{console.log(err)})
            },
    },
    created() {
    },
    mounted() {
        this.getCategory(); // 마운트시 카테고리 목록부터 조회

    },
    watch:{
        cnt_mid_cate: function(){ // 중분류 변경되면 그에맞는 소분류, 강의 목록 조회
            this.getLectureList();
            var collapses = this.$refs.lecture_list;
            if(collapses){
                collapses.forEach((col)=>{
                    col.classList.remove('show') /* 중분류 변경시 콜랩스 닫기 */
                    collapses[0].classList.add('show') /* 첫번째 스텝만 보여주기 */
                })
            }
        },
        '$route.params.index':{
            immediate: true,
            handler(newTop){
                this.cnt_top_cate = newTop;
            }
        },
        '$route.query.cnt_mid_cate':{
            immediate: true,
            handler(newMid){
                if(newMid != undefined){
                    this.cnt_mid_cate = newMid;
                }
            }
        }
    }
}


</script>

<style scoped>

.mid-cate-container::after{
    content: '';
    width: 100%;
    margin: 1rem 0rem;
    background: gray;
    height: 1px;
    display: flex;
}
a{
    cursor: pointer;
}


.cnt_selected{
    color : var(--blue) !important;
    font-weight: bold !important;
}

.curriculum-list-even::before, .curriculum-list-odd::before{
    content: '';
    width: 100%;
    margin: 1rem 0rem;
    height: 3px;
    display: flex;
}
.curriculum-list-odd::before{
    background: var(--yellow);
    
}
.curriculum-list-even::before{
    background: var(--blue);
}

.bot-cate-step::after{
    content: '';
    width: 2px;
    margin: 0rem 0.5rem;
    background: black;
    height: 1em;
    display: inline-block;
    vertical-align: middle;
}

.teacher-container::after{
    content:'';
    width: 2px;
    margin : 0rem 0.5rem;
    background: gray;
    height: 1.2rem;
    display : inline-block;
    vertical-align: middle;
}

</style>