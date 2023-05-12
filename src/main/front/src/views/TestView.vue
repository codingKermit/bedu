<template>
    <div class="test">
      <h1>This is an Test page</h1>
      {{ this.black }}
      <br>
      {{ this.blue }}
      <br>
      {{ this.yellow }}
    </div>
  </template>
  
<script>

export default{
  data() {
    return {
      black : this.black,
      blue : this.blue,
      yellow : this.yellow,
    }
  },
}


</script>