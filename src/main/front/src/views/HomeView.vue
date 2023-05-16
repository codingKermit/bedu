<template>
  <div class="lecture py-5" >
    <!-- <img src="@/assets/imgs/character/reverse_byul_book.png" class="character"> -->
    <p class="fw-bold fs-1 text-center">어떤 강의를 찾고 있나요?</p>
    <p class="fs-5 fw-light fs-bedu-yellow text-center">What's wanna be?</p>
    <b-form class="w-50 mx-auto">
      <div class="border border-3 rounded-pill p-2 d-flex align-middle text-center w-75 m-auto mb-5 border-bedu">
        <font-awesome-icon class="m-auto mx-3" :icon="['fas', 'magnifying-glass']" />
        <b-form-input class="border-0 me-2"></b-form-input>
      </div>
    </b-form>

    <p class="fw-bold fs-4 text-start ms-5">분야별 강의</p>
    <categories></categories>

    <p class="fw-bold fs-4 text-start ms-5">분야별 강의</p>
  </div >
</template>

<script>
import Categories from '../components/Categories.vue'


export default{
  components: { Categories },
  name : 'lectureView',
  data() {
    return {
      category : this.$route.params.category,
    }
  },
  methods: {
    
  },
  mounted() {
    this.category = this.$route.params.category
  },
  created() {
    
  },
}


</script>

<style scoped>

.border-bedu {
  border-color: var(--yellow) !important;
}

.fs-bedu-yellow{
  color : var(--blue) !important;
}

.character{
  position: absolute;
  top : 120px;
  left : -170px;
  width: 350px;
}


</style>