<template>
    <div class="w-25 ps-5 me-5">
        <!-- 네비 컨테이너 -->
        <p class="fs-2 fw-bold mb-3">분야별 강의</p>
        <ul class="nav flex-column w-100">
            <!-- 대분류 -->
            <li
                class="nav-item me-4"
                v-for="(top, top_index) in categories"
                :key="top_index">
                <span>
                    <a
                        class="fs-5 text-body text-decoration-none d-flex"
                        data-bs-toggle="collapse"
                        :href='"#top-"+top.cateCode'>
                        <p>{{ top.cateKor }}</p>
                        <p class="ms-auto">
                            <font-awesome-icon :icon="['fas','caret-down']"/>
                        </p>
                    </a>
                </span>
                <div
                    class="collapse text-secondary text-secondary mid-cate-container"
                    :id='"top-"+top.cateCode'
                    :class='cnt_top_cate == top.cateCode ? "show":""'
                    >
                    <ul class="ps-2">
                        <!-- 중분류 -->
                        <li
                            v-for="(mid, mid_index) in top.children"
                            :key="mid_index"
                            class="list-unstyled py-2">
                            <router-link
                                :to='"/lectureCategories/"+top.cateCode+"?cnt_mid_cate="+mid.cateCode'
                                class="text-decoration-none"
                                :class="mid.cateCode == cnt_mid_cate && top.cateCode == cnt_top_cate ? 'cnt_selected':'text-body'"
                                >
                                {{ mid.cateKor }}
                            </router-link>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default{
    name : 'cateNavi',
    data() {
        return {
            categories: [], 
            cnt_mid_cate: '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_mid_cate_kor: '', // 현재 보고 있는 대분류의 한글 코드명
            cnt_top_cate: '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_top_cate_kor: '', // 현재 보고 있는 대분류의 한글 코드명
        }
    },
    methods: {
        /** 마운트시 카테고리 조회하는 함수 */
        getCategory() {
            this
                .$axiosSend('get', '/api/lect/getCategory')
                .then((res) => {
                    const data = res.data;
                    this.convertToHierarchy(data); // 트리구조로 변경하는 함수 호출
                })
                .catch((err) => {
                    console.log(err)
                })
            },

        /** 받은 카테고리를 트리 구조로 변경하는 함수 */
        convertToHierarchy(data) {
            const map = {}; // 부모-자식 관계를 저장할 맵
            // 맵에 카테고리 코드를 키로하여 카테고리 객체를 저장
            data.forEach(category => {
                category.children = []; // 자식 카테고리를 저장할 배열 생성
                map[category.cateCode] = category;
            });

            const hierarchy = []; // 최상위 부모 카테고리를 저장할 배열

            console.log(data)
            // 부모-자식 관계 설정
            data.forEach(category => {
                const parentNum = category.parentNum;
                if (parentNum) {
                    const parent = map[parentNum];
                    if (parent) {
                        parent
                            .children
                            .push(category);
                    }
                } else {
                    hierarchy.push(category);
                }
            });
            this.categories = hierarchy;
            this.cnt_top_cate = this.$route.params.index;
            this.cnt_mid_cate = this.$route.query.cnt_mid_cate;

        },
    },
    created() {
        
    },
    mounted() {
        this.getCategory(); // 마운트시 카테고리 목록부터 조회
    },
    watch:{
        '$route.params.index': { 
            /** LectureCategories.vue 와 중복되는 내용이지만 
             * 해당 이 부분은 화면에 css를 적용하기 위한 기능, 조회기능 X
            */
                immediate: true,
                handler(newTop) {
                    this.cnt_top_cate = newTop;
                }
            },
            '$route.query.cnt_mid_cate': {
                immediate: true,
                handler(newMid) {
                    if (newMid != undefined) {
                        this.cnt_mid_cate = newMid;
                    }
                }
            }
    }
}


</script>

<style scoped>
    a {
        cursor: pointer;
    }

.lecture_item:hover{
    transition: 0.1s;
    box-shadow: 0px 0px 0px 3px black inset !important;
    scale: 102%;
}

    .mid-cate-container::after {
        content: '';
        width: 100%;
        margin: 1rem 0;
        background: gray;
        height: 1px;
        display: flex;
    }

    .cnt_selected {
        color: var(--blue) !important;
        font-weight: bold !important;
    }


</style>