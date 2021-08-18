<template>
  <div>
    <!--    导航栏-->
    <gloabalheader/>
    <Footer/>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Gloabalheader from "@/views/index/Globalheader";

export default {
  name: "about",
  components: {Gloabalheader, Footer,}
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>