<template>
  <div class="detail-div">
    {{contents}}
    <br />
    <br />
    <b>카테고리</b>
    : {{category}}
    <br />
    <b>주소</b>
    : {{address}}
    <br />
    <b>연락처</b>
    : {{phone}}
    <br />
    <button class="port-btn" @click="goBoard()">
      <b>
        게시판
        <br />이동
      </b>
    </button>
  </div>
</template>

<script>
export default {
  props: [
    "academyId",
    "name",
    "address",
    "category",
    "contents",
    "phone",
    "imgUrl",
    "isPayed"
  ],
  methods: {
    goBoard() {
      var router = this.$router;
      router.push({
        name: "academyMain",
        params: {
          academyId: this.academyId,
          name: this.name,
          address: this.address,
          category: this.category,
          phone: this.phone,
          contents: this.contents,
          imgUrl: this.imgUrl
        }
      });
    }
  }
};
</script>

<style>
.detail-div {
  padding: 5px;
  width: 100%;
  height: auto;
  min-height: 500px;
}
.port-btn {
  position: fixed;
  bottom: 70px;
  right: 30px;
  width: 100px;
  height: 70px;
  background-color: skyblue;
  text-align: center;
  border: none;
  border-radius: 80px;
  font-size: 15px;
  outline: 0;
  box-shadow: 3px 3px 3px gray;
}
.port-btn:active {
  bottom: 67px;
  right: 27px;
  box-shadow: none;
}
</style>