<template>
  <div class="app-container">
    <Header />
    <Home v-if="page==1" />
    <MyPage v-if="page==2" />
    <Search v-if="page==3" />
    <More v-if="page==4" />
    <Footer @changePage="changePage" />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./components/Home";
import More from "./components/More";
import MyPage from "./components/MyPage";
import Search from "./components/Search";

export default {
  components: {
    Header,
    Footer,
    Home,
    More,
    MyPage,
    Search
  },
  data() {
    return {
      page: 1
    };
  },
  mounted() {
    // console.log(getToken());
    console.log(this.name);
  },
  computed: {
    ...mapGetters(["name", "token"])
  },
  methods: {
    changePage(num) {
      this.page = num;
    }
  }
};
</script>

<style>
.app-container {
  padding: 60px 0;
}
</style>
