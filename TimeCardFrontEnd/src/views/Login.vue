<template>
  <div class="login">
    <!-- <img alt="fox-hound logo" src="../assets/fox-hound_logo.png" /> -->
    <img alt="vue logo" src="../assets/logo.png" />
    <LoginComponent msg="Welcome to Our TimeCard App" />
  </div>
</template>
<script>
// @ is an alias to /src
import LoginComponent from "@/components/LoginComponent.vue";

export default {
  name: "logincomponent",
  components: {
    LoginComponent
  }
};
</script>

<style scoped>
/* div img{
  padding-left:35px;
  width: 200px;
} */
</style>
