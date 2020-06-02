<template>
  <div class="category">
    <div class="radius">
      <h4>검색 반경</h4>
      <div class="radius-select">
        <el-slider v-model="options.radius" :marks="marks" :step="20"></el-slider>
      </div>
    </div>
    <div class="subject">
      <h4>분야 선택</h4>
    </div>
    <div class="student">
      <h4>수강생</h4>
    </div>
    <div class="tag">
      <h4>태그 검색</h4>
    </div>
    <div class="submit">
      <el-button type="warning" @click="setCategory">검색</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: {
        radius: 0
      },
      marks: {
        0: "100 m",
        20: "300 m",
        40: "500 m",
        60: "1km",
        80: "3km",
        100: "5km"
      }
    };
  },
  methods: {
    setCategory() {
      this.$emit("setCategory", this.options);
    }
  }
};
</script>

<style lang="scss">
.category {
  padding: 0 30px;
  .range-select {
    width: 80%;
  }
}
</style>