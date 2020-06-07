<template>
  <div class="app-container">
    <Header />
    <Pay @changePagenum="changepage" v-if="pagenum == 0" />
    <PaySucc @changePagenum="changepage" v-if="pagenum == 2" />
  </div>
</template>

<script>
import Header from "@/views/student/components/Header";
import Pay from "./components/Pay";
import PaySucc from "./components/PaySucc";
import { getToken } from "@/utils/auth";
export default {
  components: {
    Header,
    Pay,
    PaySucc
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
