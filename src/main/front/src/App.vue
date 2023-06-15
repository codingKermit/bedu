<template>
    <BeduView/>
</template>

<script>
import BeduView from '@/components/BeduView'

export default {
  name: 'App',
  components: {
    BeduView
  },
  created() {
        this.checkToken()
    },
    methods: {
      checkToken() {
          const token = localStorage.getItem('user_token');
          if (token) {
            this.$store.commit('IS_AUTH', true);
          } else {
            this.$store.commit('IS_AUTH', false);
          }
        }
    }
}
</script>

<style>

</style>
