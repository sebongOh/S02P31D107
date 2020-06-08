<template>
  <div class="app-container">
    <Header />
    <Home v-if="page==1" />
    <MyPage v-if="page==2" />
    <Location v-if="page==3" @setLocation="setLocation" />
    <Category v-if="page==5" @setCategory="setCategory" />
    <Search v-if="page==6" @prevPage="prevPage" :options="mapOptions" />
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
import Location from "./components/Location";
import Category from "./components/Category";
import Search from "./components/Search";

export default {
  components: {
    Header,
    Footer,
    Home,
    More,
    MyPage,
    Location,
    Search,
    Category
  },
  data() {
    return {
      page: 1,
      mapOptions: {
        place: "",
        options: {
          radius: 1000,
          selected: []
        }
      }
    };
  },
  mounted() {
    // console.log(getToken());
    // console.log(this.name);
  },
  computed: {
    ...mapGetters(["name", "token"])
  },
  methods: {
    changePage(num) {
      this.page = num;
    },
    setLocation(place) {
      this.page = 5;
      // console.log(place);
      this.mapOptions.place = place;
    },
    setCategory(options) {
      if (options.radius === 0) {
        this.mapOptions.options.radius = 1000;
      } else if (options.radius === 20) {
        this.mapOptions.options.radius = 2000;
      } else if (options.radius === 40) {
        this.mapOptions.options.radius = 3000;
      } else if (options.radius === 60) {
        this.mapOptions.options.radius = 4000;
      } else if (options.radius === 80) {
        this.mapOptions.options.radius = 5000;
      } else {
        this.mapOptions.options.radius = 6000;
      }
      // console.log("인덱스", options.selected);
      this.mapOptions.options.selected = options.selected;
      this.page = 6;
    },
    prevPage(page) {
      this.page = page;
    }
  }
};
</script>

<style>
.app-container {
  padding: 60px 0;
}
</style>
