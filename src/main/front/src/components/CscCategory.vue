<template>
    <div>
        <div id="csc-main-side">
            <p v-if="titleShow" class="fs-2 fw-bold mb-3 mt-15">고객센터</p>
            <router-link class="fs-5 text-body text-decoration-none" id="cscside-view" to="/">
                <p>자주 묻는 질문</p>
            </router-link>
            <router-link class="fs-5 text-body text-decoration-none" id="csc-view-freepath" to="/">
                <p>1대1 문의</p>
            </router-link>
        </div>
    </div>
</template>
  
<script>
import '@/assets/css/CscViewStyle.css';
export default {

    name: 'comm-main-side',
    props: {
        titleShow: {
            type: Boolean,
            required: false,
            default: true,
        },
        currentTab: String,
    },

}

</script>