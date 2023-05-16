<template>
  <div class="regist">
    <div>
    </div>
    <div class="container">
      <button @click="$router.push('/login')">
        login click test
      </button>
      <button @click="$router.push('/regist')">
        regist click test
      </button>
      <div class="button-container">
        <form class="login-form">
          test
        </form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      black: this.black,
      blue: this.blue,
      yellow: this.yellow
    };
  },
};
</script>

<style>
.regist {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #EDF0F5;
  text-align: center;
}

.container {
  padding-top: 100px;
  padding-bottom: 150px;
  border-radius: 1rem;
  background: white;
  margin-left: 350px;
  margin-right: 350px;
}

.button-container {
  padding: 1rem;
  text-align: center;
  align-items: center;
}

</style>