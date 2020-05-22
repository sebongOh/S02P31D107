<template>
  <div class="app-container">
    <h1>여기는 학생 페이지</h1>
    <Footer />
  </div>
</template>

<script>
import { mapGetters } from "vuex";

import Footer from "@/components/footer/StudentFooter";

export default {
  components: {
    Footer
  },
  mounted() {},
  computed: {
    ...mapGetters(["name"])
  }
};
</script>

<style>
</style>