<template>
    <div>
        <div class="p-5 w-100 d-flex"> <!-- 좌측 네비, 커리큘럼 컨테이너 -->
            <div class="w-25 ps-5 me-5"> <!-- 네비 컨테이너 -->
                <p class="fs-2 fw-bold mb-3">분야별 강의</p>
                <ul class="nav flex-column w-100">
                    <li class="nav-item me-4" v-for="(top, index) in categories" :key="index">
                    <span>
                        <a class="fs-5 text-body text-decoration-none d-flex" data-bs-toggle="collapse" :href='"#top-"+top.cate_code'>
                            <p>{{ top.cate_kor }}</p>
                            <p class="ms-auto">
                                <font-awesome-icon :icon="['fas','caret-down']" />
                            </p>
                        </a>
                    </span>
                        <div class="collapse text-secondary text-secondary mid-cate-container" :id='"top-"+top.cate_code'>
                            <ul class="ps-2">
                                <li v-for="(mid, index) in top.children" :key="index" class="list-unstyled py-2">
                                    <a @click="midCategoryChanger(top,mid)" 
                                    :class="mid.cate_code == cnt_mid_cate && top.cate_code == cnt_top_cate ? 'cnt_selected':'text-body'"
                                    class="text-decoration-none"
                                    >{{ mid.cate_kor }}</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="w-75 pe-5"><!-- 강의 기본정보 & 커리큘럼 컨테이너-->
                <p class="fs-2 fw-bold py-3">{{ cnt_mid_cate_kor }}</p>
                <hr>
                <p class="fs-3 fw-bold curriculum-head my-3">커리큘럼</p>
                <ul class="list-unstyled">
                    <li 
                    :class="index%2 == 0 ? 'curriculum-list-even':'curriculum-list-odd'" 
                    v-for="(item, index) in getCurrCurriculum()" :key="index"
                    class="my-4 align-middle"
                    >
                    <div>
                        <a class="d-flex text-body text-decoration-none my-auto fs-4 mb-4" data-bs-toggle="collapse" :href='"#bot-cate-list-"+index'>
                            <span class="bot-cate-step">STEP {{ index+1 }}</span>  
                            <div class="bot-cate-split"></div>
                            <span class="text-body">{{ item.cate_kor }}</span> 
                            <span class="ms-auto">
                                <font-awesome-icon :icon="['fas','caret-down']" />
                            </span>
                        </a>
                        <div class="collapse" :id='"bot-cate-list-"+index'>
                            <b-container>
                                <b-row cols="2">
                                    <b-col v-for="(item, index) in lectures" :key="index">
                                        <b-link class="text-body text-decoration-none" :to='"/lectureDetail?num="+item.num'>
                                            <b-container class="border p-3 mb-4">
                                                <b-img thumbnail :src="item.thumbnail" class="w-100 h-100 mb-2"></b-img>
                                                <p class="fs-5 fw-bold">{{ item.title }}</p>
                                                <div class="d-flex">
                                                    <p class="teacher-container">{{ item.teacher }} 선생님</p><p>총 {{ item.total }}강</p>
                                                </div>
                                                <p class="text-secondary">수강 기간 : {{ item.period }}일</p>
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
            // categories : [
            //     {
            //         top_cate : 'base',
            //         top_cate_kor : '기초 강의',
            //         child: [
            //             {
            //                 mid_cate : 'overview',
            //                 mid_cate_kor:'코딩 기초 필수! 프로그래밍 오버뷰',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 오버뷰',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'webSite',
            //                 mid_cate_kor:'HTML/CSS로 웹사이트 만들기',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '웹 퍼블리싱',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'python',
            //                 mid_cate_kor:'Python 기초',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in Python',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'javascript',
            //                 mid_cate_kor:'JavaScript 기초',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in JavaScript',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'sql',
            //                 mid_cate_kor:'비개발자를 위한 SQL 데이터 분석',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : 'SQL로 하는 데이터 분석',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'figma',
            //                 mid_cate_kor:'Figma로 시작하는 UI 디자인',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : 'UI 디자인 시작하기',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '디자인 툴 다루기',
            //                     },
            //                 ]
            //             },
            //         ],
            //     },
            //     {
            //         top_cate : 'web',
            //         top_cate_kor : '웹 개발',
            //         child: [
            //             {
            //                 mid_cate : 'webSite',
            //                 mid_cate_kor:'HTML/CSS로 웹사이트 만들기',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '웹 퍼블리싱',
            //                         child:[]
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'react',
            //                 mid_cate_kor:'React 프론트엔드 개발',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in JavaScript',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '웹 퍼블리싱',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : 'JavaScript 중급',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 4',
            //                         bot_cate_kor : 'React 프론트엔드 개발',
            //                         child:[]
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'next.js',
            //                 mid_cate_kor:'Next.js 프론트엔드 개발',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in JavaScript',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '웹 퍼블리싱',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : 'JavaScript 중급',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 4',
            //                         bot_cate_kor : 'React 프론트엔드 개발',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 5',
            //                         bot_cate_kor : 'Next.js 프론트엔드 개발',
            //                         child:[]
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'django',
            //                 mid_cate_kor:'Django 웹 개발',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in Python',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '웹 퍼블리싱',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : 'CLI 환경 기본기',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 4',
            //                         bot_cate_kor : 'Django 웹 개발',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 5',
            //                         bot_cate_kor : '인터랙티브 Django',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 6',
            //                         bot_cate_kor : 'Django 백엔드 개발',
            //                         child:[]
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'node.js',
            //                 mid_cate_kor:'Node.js 백엔드 개발',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in JavaScript',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : 'JavaScript 중급',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : 'CLI 환경 기본기',
            //                         child:[]
            //                     },
            //                     {
            //                         bot_cate : 'STEP 4',
            //                         bot_cate_kor : 'Node.js 백엔드 개발',
            //                         child:[]
            //                     },
            //                 ]
            //             },
            //         ],
            //     },
            //     {
            //         top_cate : 'data',
            //         top_cate_kor : '데이터 분석',
            //         child: [
            //             {
            //                 mid_cate : 'basicToExpert',
            //                 mid_cate_kor:'데이터 분석, 기초에서 실전까지',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in Python',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '데이터 분석 기초',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : '데이터 분석 실전',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'non-majors',
            //                 mid_cate_kor:'비개발자를 위한 SQL 데이터 분석',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : 'SQL로 하는 데이터 분석',
            //                     },
            //                 ]
            //             }
            //         ],
            //     },
            //     {
            //         top_cate : 'ai',
            //         top_cate_kor : '인공지능',
            //         child: [
            //             {
            //                 mid_cate : 'machine',
            //                 mid_cate_kor:'머신 러닝',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in Python',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '데이터 사이언스 기본기',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : '머신 러닝',
            //                     },
            //                 ]
            //             },
            //             {
            //                 mid_cate : 'deep',
            //                 mid_cate_kor:'딥 러닝',
            //                 child:[
            //                     {
            //                         bot_cate : 'STEP 1',
            //                         bot_cate_kor : '프로그래밍 기초 in Python',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 2',
            //                         bot_cate_kor : '데이터 사이언스 기본기',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 3',
            //                         bot_cate_kor : '머신 러닝 기본기',
            //                     },
            //                     {
            //                         bot_cate : 'STEP 4',
            //                         bot_cate_kor : '딥 러닝',
            //                     },
            //                 ]
            //             }
            //         ],
            //     },
            // ],

            categories: [],
            cnt_mid_cate : '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_mid_cate_kor : '', // 현재 보고 있는 대분류의 한글 코드명
            cnt_top_cate : '', // 현재 어떤 중분류를 보고있는지를 코드로 저장
            cnt_top_cate_kor : '', // 현재 보고 있는 대분류의 한글 코드명
            lectures:[
                {   
                    num : 55,
                    title:'더미 강의 1',
                    teacher : '더미 강사 1',
                    thumbnail : 'https://pbs.twimg.com/media/FaxmkFVaAAAKm1f.jpg',
                    total : 30,
                    period : 30,
                },{
                    num : 56,
                    title:'더미 강의 2',
                    teacher : '더미 강사 2',
                    thumbnail : 'https://pbs.twimg.com/media/FaxmkFVaAAAKm1f.jpg',
                    total : 30,
                    period : 30,
                },{
                    num : 57,
                    title:'더미 강의 3',
                    teacher : '더미 강사 3',
                    thumbnail : 'https://pbs.twimg.com/media/FaxmkFVaAAAKm1f.jpg',
                    total : 30,
                    period : 30,
                },{
                    num : 58,
                    title:'더미 강의 4',
                    teacher : '더미 강사 4',
                    thumbnail : 'https://pbs.twimg.com/media/FaxmkFVaAAAKm1f.jpg',
                    total : 30,
                    period : 30,
                },{
                    num : 59,
                    title:'더미 강의 5',
                    teacher : '더미 강사 5',
                    thumbnail : 'https://pbs.twimg.com/media/FaxmkFVaAAAKm1f.jpg',
                    total : 30,
                    period : 30,
                }
            ], // 소분류에 있는 강의 목록
        }
    },
    methods: {
        /** 카테고리 변경체크 */
        midCategoryChanger(top,mid){
            this.cnt_mid_cate = mid.cate_code;
            this.cnt_mid_cate_kor = mid.cate_kor;
            this.cnt_top_cate = top.cate_code;
            this.cnt_top_cate_kor = top.cate_kor;
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
                const parentCode = category.parent;
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
            this.cnt_top_cate = this.categories[0].cate_code;
            this.cnt_top_cate_kor = this.categories[0].cate_kor;
            this.cnt_mid_cate = this.categories[0].children[0].cate_code;
            this.cnt_mid_cate_kor = this.categories[0].children[0].cate_kor;
            }
    },
    created() {

        
    },
    mounted() {
        this.getCategory();
    },
    watch:{
        cnt_mid_cate: function(){
        
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
    color : var(--blue);
    font-weight: bold;
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