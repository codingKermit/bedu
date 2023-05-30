<template>
    <div>
        <b-navbar toggleable="md">
            <b-collapse id="categories" is-nav>
            <b-navbar-nav class="text-center d-flex mb-5 justify-content-center m-auto">
                <b-nav-item v-for="(item, index) in categories" :key="index">
                    <b-link @click="intoCate(index)" class="text-body text-decoration-none">
                        <b-container class="bg-secondary rounded-3 bg-opacity-10 py-3 mb-2">
                            <b-img :src="require('@/assets/imgs/categories/'+item.icon+'.png') " fluid></b-img>
                        </b-container>
                        <text class="fw-bold d-block">{{ item.cate_kor }}</text>
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
                getTop(){
                this.$axios.get('/api/getTop')
                .then((res)=>{
                    this.categories = res.data.item
                })
                .catch((err)=>{err})
                },
                intoCate(index){
                    this.$router.push({
                        name : 'lectureCategories',
                        query:{
                            index : index
                        }
                    })
                }
            },
            created() {

            },
            mounted() {
                this.getTop();
            },
        }
    </script>

    <style scoped="scoped">
        img {
            width: 65%;
            height: 65%;
        }
    </style>