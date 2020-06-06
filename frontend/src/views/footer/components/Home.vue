<template>
  <div>
    <h1>Home</h1>
    <div class="ad"></div>
    <div class="select">
      <el-row>
        <el-col :span="6">
          <div class="select--box">
            <span>외국어</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>종합학원</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>단과학원</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>운동</span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <div class="select--box">
            <span>취미활동</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>음악/미술</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>예체능</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>자격증</span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <div class="select--box">
            <span>컴퓨터</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box">
            <span>요리</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="news"></div>
  </div>
</template>

<script>
export default {

};
</script>

<style lang="scss">
.select {
  .select--box {
    background-color: greenyellow;
    margin: 3px;
    height: 80px;
    border-radius: 10px;
  }
}
</style>