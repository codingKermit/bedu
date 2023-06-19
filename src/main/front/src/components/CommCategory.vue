<template>
    <div>
        <div class="comm-main-side" id="comm-main-side">
            <p class="fs-2 fw-bold mb-3 mt-15">커뮤니티</p>
            <li class="nav-item me-4" style="width: 150px;">
                <span class="qna-view-qnapath" id="qna-view-qnapath">
                    <router-link class="fs-5 text-body text-decoration-none" to="/comm/qna"
                        style="cursor: pointer; text-align: right;">
                        <p>질문 & 답변</p>
                    </router-link>
                </span>
            </li>
            <ul class="nav flex-column w-100">
                <li class="nav-item me-4" style="width: 150px;">
                    <span class="qna-view-freepath" id="qna-view-freepath">
                        <router-link class="fs-5 text-body text-decoration-none" to="/comm/freBd"
                            style="cursor: pointer; text-align: right;">
                            <p>자유게시판</p>
                        </router-link>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default{

    name:'comm-main-side'

}

</script>
<style>

</style>