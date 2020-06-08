<template>
  <div class="app-container">
    <Header />
    <Pay
      :academyName="academyName"
      :scheduleId="scheduleId"
      :scheduleName="scheduleName"
      :price="price"
      @changePagenum="changepage"
      v-if="pagenum == 0"
    />
  </div>
</template>

<script>
import Header from "@/views/student/components/Header";
import Pay from "./components/Pay";
import { getToken } from "@/utils/auth";
export default {
  props: ["academyName", "scheduleId", "scheduleName", "price"],
  components: {
    Header,
    Pay
  },
  data: () => {
    return {
      pagenum: 0
    };
  },
  methods: {
    changepage(num) {
      this.pagenum = num;
    }
  }
};
</script>

<style>
.app-container {
  padding: 60px 0;
}
</style>
