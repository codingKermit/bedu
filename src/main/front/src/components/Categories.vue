<template>
    <div>
        <b-navbar toggleable="md">
            <b-collapse id="categories" is-nav>
            <b-navbar-nav class="text-center d-flex mb-5 justify-content-center m-auto">
                <b-nav-item v-for="(item, index) in categories" :key="index">
                    <b-link :to='"/lectureCategories/"+item.cateCode+"?cnt_mid_cate="+item.children[0].cateCode' class="text-body text-decoration-none">
                        <b-container class="bg-secondary rounded-3 bg-opacity-10 py-3 mb-2">
                            <b-img :src="require('@/assets/imgs/categories/'+item.icon+'.png') " fluid></b-img>
                        </b-container>
                        <text class="fw-bold d-block">{{ item.cateKor }}</text>
                    </b-link>
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
                categories: [],
            }
        },
        methods: {
            // 이미지 주소를 변환시켜주는 함수
            // getImg(imgUrl){
            //     console.log('url ::: ', imgUrl)
            //     // const url = require('@'+imgUrl)
            //     const url = require('@/assets/imgs/categories/free-icon-base.png')
            //     //                      @/assets/imgs/categories/free-icon-base.png'
            //     // api로 받아온 이미지를 변수로 가져옴
            //     // img: require('@/assets/imgs/categories/free-icon-base.png')
            //     console.log('img url ::: ', url)
            //     return url
            // },
            getData(){
                let cateData = [];
                this.$axiosSend('get', '/api/getCategory')
                .then((res) => {
                    console.log('res::: ', res)
                    if (this.$isNotEmpty(res?.data)) {
                        cateData = res?.data;
                    }
                    console.log('cateData ::: ', cateData)
                    if(cateData.length != 0){
                        this.convertToHierarchy(cateData)
                    }
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
                
                // 부모-자식 관계 설정
                data.forEach(category => {
                    const parentCode = category.parentCode;
                    if (parentCode) {
                        const parent = map[parentCode];
                        if (parent) parent.children.push(category)
                    } else {
                        hierarchy.push(category);
                    }
                    this.categories = hierarchy;
                });
            }
        },
        mounted() {
            this.getData();
        }
    }
</script>

    <style scoped="scoped">
        img {
            width: 65%;
            height: 65%;
        }
    </style>
