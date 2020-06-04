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
      <div class="subject-select">
        <div class="subject-name">단과</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">입시</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">외국어</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">편입</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">공무원</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">컴퓨터</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">체육</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">음악</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">미술</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">독서실</div>
        <div class="subject-kinds"></div>
      </div>
      <div class="subject-select">
        <div class="subject-name">기타</div>
        <div class="subject-kinds"></div>
      </div>
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
        0: "1km",
        20: "2km",
        40: "3km",
        60: "4km",
        80: "5km",
        100: "6km"
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