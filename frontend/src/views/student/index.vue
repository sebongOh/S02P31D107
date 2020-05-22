<template>
  <div class="app-container">
    <h1>여기는 학생 페이지</h1>
    <el-button type="primary">Primary</el-button>
    <Footer />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

import Footer from "@/components/footer/StudentFooter";

export default {
  components: {
    Footer
  },
  mounted() {
    // console.log(getToken());
    console.log(this.name);
  },
  computed: {
    ...mapGetters(["name", "token"])
  }
};
</script>

<style></style>
