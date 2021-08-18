<template>
  <div>
    <!--    导航栏-->
    <gloabalheader/>


    <Footer/>

  </div>
</template>

<script>
import Gloabalheader from "@/views/index/Globalheader";
import Footer from "../components/Footer";

export default {
  name: "mainpage",
  components: { Gloabalheader,Footer}
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>