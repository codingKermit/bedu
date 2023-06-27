<template>
    <div>  
        <b-container class="py-5">
            <b-card no-body>
                <b-card-header  header-tag="nav">
                    <b-nav card-header fill tabs>
                        <b-nav-item @click="this.currentTab='upload'" :active="currentTab =='upload' ? 'true':'false'">등록</b-nav-item>
                        <!-- <b-nav-item @click="this.currentTab='update'">수정</b-nav-item> -->
                        <b-nav-item @click="this.currentTab='delete'" :active="currentTab =='delete' ? 'true':'false'">삭제</b-nav-item>
                    </b-nav>
                </b-card-header>
                <b-card-body>
                    <FileUpload v-if="currentTab == 'upload'" :totalLists="totalLists"></FileUpload>
                    <FileDelete v-if="currentTab == 'delete'" :totalLists="totalLists"></FileDelete>
                </b-card-body>
            </b-card>
        </b-container>
    </div>
</template>


<script>
import FileUpload from '@/components/fileManage/FileUpload.vue';
import FileDelete from '@/components/fileManage/FileDelete.vue';

export default{
    name : 'fileManage',
    components:{ FileUpload, FileDelete },
    data() {
        return {
            currentTab : 'upload',

        }
    },
    created(){

    },
    methods: {

    },
    mounted() {
    
    },
}
</script>

<style scoped>


</style>