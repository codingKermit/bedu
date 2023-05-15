<!-- PageHeader.vue -->
<template>
    <header>
      <div id="nav">
        헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더헤더
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link> |
        <router-link to="/studyView">Test</router-link>
      </div>
    </header>
</template>

  <script>
  export default {
  
  }
  </script>
  
  <style scoped>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 50px;
  background-color: #EDC268;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
  
  </style>