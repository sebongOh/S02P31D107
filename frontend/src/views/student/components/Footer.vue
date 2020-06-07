<template>
  <div class="footer">
    <div class="icon" @click="click(1)">
      <div class="icon--box">
        <span>
          <i class="fas fa-home"></i>
        </span>
      </div>
      <div class="icon--name">
        <span>홈</span>
      </div>
    </div>
    <div class="icon" @click="click(2)">
      <div class="icon--box">
        <span>
          <i class="fas fa-user"></i>
        </span>
      </div>
      <div class="icon--name">
        <span>My 학원</span>
      </div>
    </div>
    <div class="icon" @click="click(3)">
      <div class="icon--box">
        <span>
          <i class="fas fa-search"></i>
        </span>
      </div>
      <div class="icon--name">
        <span>학원 검색</span>
      </div>
    </div>
    <div class="icon" @click="click(4)">
      <div class="icon--box">
        <span>
          <i class="fas fa-ellipsis-h"></i>
        </span>
      </div>
      <div class="icon--name">
        <span>더보기</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    click(num) {
      this.$emit("changePage", num);
    }
  }
};
</script>

<style lang="scss">
.footer {
  font-family: "Yeon Sung", cursive;
  background-color: #a69d8d;
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: space-around;
  align-items: center;

  .icon {
    margin-top: 5px;
    text-align: center;
    width: 25%;
    height: 100%;
    font-size: 1.2em;
    cursor: pointer;
    .icon--box {
      margin-top: 7px;
    }
    .icon--name {
      font-size: 15px;
    }
  }
}
</style>