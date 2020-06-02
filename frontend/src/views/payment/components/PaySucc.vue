<template>
  <div>
    <el-card>
      <div class="succText">
        <img src="@/assets/images/pay.png" width="50%" height="50%" />
        <h1>결제성공</h1>
      </div>
      <hr />

      <div class="succInfo">
        <div class="succInfoCol">
          <div style="float:left">결제번호 :</div>
          <div style="float:right">12312412512</div>
        </div>
        <br /><br />
        <div class="succInfoCol">
          <div style="float:left">결제시각 :</div>
          <div style="float:right">2020.06.02</div>
        </div>
        <br /><br />
        <div class="succInfoCol">
          <div style="float:left">학원 :</div>
          <div style="float:right">싸피학원</div>
        </div>
        <br /><br />
        <div class="succInfoCol">
          <div style="float:left">수강과목 :</div>
          <div style="float:right">싸피2기</div>
        </div>
        <br /><br />
        <div class="succInfoCol">
          <div style="float:left">가격 :</div>
          <div style="float:right">200,000원</div>
        </div>
        <br />
      </div>

      <hr />
      <el-row>
        <el-col :span="24">
          <el-button type="warning" style="width:100%"><b>홈으로</b></el-button>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      payment: {},
      loading: false,
    };
  },
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
