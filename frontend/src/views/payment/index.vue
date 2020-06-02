<template>
  <div class="app-container">
    <Pay />
    <PaySucc />
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
    return {};
  },
};
</script>

<style></style>
