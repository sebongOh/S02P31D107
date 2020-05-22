<template>
  <div class="app-container">
    <h1>여기가 메인임</h1>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  mounted() {},
  computed: {
    ...mapGetters(["name"])
  }
};
</script>

<style>
</style>