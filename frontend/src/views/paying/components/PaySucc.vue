<template>
  <div>
    <el-card style="border:1px solid grey; margin-top:10%">
      <div class="succText">
        <img src="@/assets/images/pay.png" width="50%" height="50%" />
        <h1>결제성공</h1>
      </div>
      <hr />

      <div class="succInfo">
        <div class="succInfoCol">
          <div style="float:left">결제번호 :</div>
          <div style="float:right">{{result.tid}}</div>
        </div>
        <br />
        <br />
        <div class="succInfoCol">
          <div style="float:left">결제시각 :</div>
          <div style="float:right">{{result.approvedAt}}</div>
        </div>
        <br />
        <br />
        <div class="succInfoCol">
          <div style="float:left">학원 :</div>
          <div style="float:right">{{result.academySchedule.academy.name}}</div>
        </div>
        <br />
        <br />
        <div class="succInfoCol">
          <div style="float:left">수강과목 :</div>
          <div style="float:right">{{result.itemName}}</div>
        </div>
        <br />
        <br />
        <div class="succInfoCol">
          <div style="float:left">가격 :</div>
          <div style="float:right">{{result.price}}원</div>
        </div>
        <br />
      </div>

      <hr />
      <el-row>
        <el-col :span="24">
          <el-button type="warning" style="width:100%" @click="go">
            <b>홈으로</b>
          </el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  props: ["result"],
  data() {
    return {
      payment: {},
      loading: false
    };
  },
  mounted() {},
  methods: {
    go() {
      this.$router.push({ path: "student-main" });
    }
  }
};
</script>

<style>
.succText {
  text-align: center;
  height: 30%;
  padding: 10%;
}
.succInfo {
  border: 1px solid;
  color: #777;
  font-weight: bold;
  border-radius: 6px;
  margin: auto;
  padding: 20px;
  background: #fddf1b;
}
.btnArea {
  width: 20%;
  height: 70px;
  margin: auto;
  padding: 14px;
  text-align: center;
  cursor: pointer;
}
.btnArea:hover {
  transition-duration: 300ms;
  background-color: darkgray;
  color: white;
}
</style>
