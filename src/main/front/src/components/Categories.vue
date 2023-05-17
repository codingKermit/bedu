<template>
    <div>
        <b-navbar toggleable="md">
            <b-collapse id="categories" is-nav>
            <b-navbar-nav class="text-center d-flex mb-5 justify-content-center m-auto">
                <b-nav-item v-for="item in categories" :key="item">
                    <b-container class="bg-secondary rounded-3 bg-opacity-10 py-3 mb-2" @click="categoryChange(item)">
                            <b-img :src="item.img" fluid></b-img>
                        </b-container>
                            <text class="fw-bold d-block">{{ item.title }}</text>
                        </b-nav-item>
                    </b-navbar-nav>
                </b-collapse>
        </b-navbar>
    </div>
</template>

    <script>
        export default {
            name: 'lectureCategories',
            data() {
                return {
                    categories: [
                        {
                            img: require('@/assets/imgs/categories/free-icon-base.png'),
                            title: '기초 강의',
                            value : 'base'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-data.png'),
                            title: '데이터 분석',
                            value:'data'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-website-2463068.png'),
                            title: '웹 개발',
                            value:'web'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-coding-language-2721593.png'),
                            title: '프로그래밍 언어',
                            value : 'lang'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-ai-2814666.png'),
                            title: '인공지능',
                            value : 'ai'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-programming.png'),
                            title: '프로그래밍 교양',
                            value : 'programming'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-programming-tools.png'),
                            title: '개발 도구',
                            value : 'tools'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-graduation-2997279.png'),
                            title: '프로그래밍 전공 지식',
                            value : 'major'
                        }, {
                            img: require('@/assets/imgs/categories/free-icon-design-1688390.png'),
                            title: '디자인',
                            value : 'design'
                        }
                    ],
                }
            },
            methods: {
                categoryChange(item){
                    this.$router.push({
                        name : 'course',
                        params:{
                            category : item.value,
                            korCategory : item.title
                        }
                    })
                }
            },
            created() {

            },
            mounted() {},
            props: {
                imgPath: String,
                title: String
            }
        }
    </script>

    <style scoped="scoped">
        img {
            width: 65%;
            height: 65%;
        }
    </style>