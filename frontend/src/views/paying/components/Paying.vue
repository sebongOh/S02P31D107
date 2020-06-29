<template>
  <div>
    <el-card>
      <div class="succText">
        <img src="@/assets/images/pay.png" width="50%" height="40%" />
        <h1>
          <i class="el-icon-loading" />
        </h1>
        <h1>결제진행중</h1>
      </div>
      <hr />
      <el-row>
        <el-col :span="24">
          <el-button type="warning" style="width:100%">
            <b>홈으로</b>
          </el-button>
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
      loading: false
    };
  },
  methods: {}
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
