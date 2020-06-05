<template>
  <div class="app-container">
    <Pay @changePagenum="changepage" v-if="pagenum == 0" />
    <PaySucc @changePagenum="changepage" v-if="pagenum == 2" />
  </div>
</template>

<script>
import Pay from "./components/Pay";
import PaySucc from "./components/PaySucc";
export default {
  components: {
    Pay,
    PaySucc,
  },
  data: () => {
    return {
      pagenum: 0,
    };
  },
  methods: {
    changepage(num) {
      this.pagenum = num;
    },
  },
};
</script>

<style></style>
