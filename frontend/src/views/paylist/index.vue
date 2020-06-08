<template>
  <div class="app-container">
    <Header />
    <el-card>
      <paylist v-if="pagenum==0" @changeNum="changenum" />
      <paydetail v-if="pagenum==1" @changeNum="changenum" />
    </el-card>
  </div>
</template>

<script>
import Header from "@/views/student/components/Header";
import paylist from "./components/paylist";
import paydetail from "./components/paydetail";
export default {
  components: { paylist, Header, paydetail },
  data() {
    return {
      pagenum: 0
    };
  },
  methods: {
    changenum(num) {
      this.pagenum = num;
    }
  }
};
</script>

<style>
.app-container {
  padding: 60px 0;
  font-family: "Yeon Sung", cursive;
}
</style>