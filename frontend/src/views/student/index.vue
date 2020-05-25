<template>
  <div class="app-container">
    <Header />
    <Footer />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

import Header from "./components/Header";
import Footer from "./components/Footer";

export default {
  components: {
    Header,
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
