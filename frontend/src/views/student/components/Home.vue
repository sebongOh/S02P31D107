<template>
  <div>
    <div class="ad">
      <el-carousel height="200px">
        <el-carousel-item>
          <img src="@/assets/images/ssafy.png" alt />
        </el-carousel-item>
        <el-carousel-item>
          <img src="@/assets/images/sample.png" alt />
        </el-carousel-item>

        <el-carousel-item>
          <img src="@/assets/images/cloud.png" alt />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="select">
      <el-row>
        <el-col :span="6">
          <div class="select--box border--right border--left border--top">
            <div class="icon">
              <i class="fas fa-book"></i>
            </div>
            <span>단과</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right border--top">
            <div class="icon">
              <i class="fas fa-university"></i>
            </div>
            <span>입시</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right border--top">
            <div class="icon">
              <i class="fas fa-globe-americas"></i>
            </div>
            <span>외국어</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right border--top">
            <div class="icon">
              <i class="fas fa-chalkboard-teacher"></i>
            </div>
            <span>공무원</span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <div class="select--box border--right border--left">
            <div class="icon">
              <i class="fas fa-desktop"></i>
            </div>
            <span>컴퓨터</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right">
            <div class="icon">
              <i class="fas fa-dumbbell"></i>
            </div>
            <span>체육</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right">
            <div class="icon">
              <i class="fas fa-music"></i>
            </div>
            <span>음악</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right">
            <div class="icon">
              <i class="fas fa-palette"></i>
            </div>
            <span>미술</span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <div class="select--box border--right border--left">
            <div class="icon">
              <i class="fas fa-pencil-alt"></i>
            </div>
            <span>독서실</span>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="select--box border--right">
            <div class="icon">
              <i class="fas fa-cookie-bite"></i>
            </div>
            <span>요리</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="news"></div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.ad {
  text-align: center;
  margin-bottom: 30px;

  .el-carousel__item img {
    width: 100%;
    height: 100%;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }
}
.select {
  .select--box {
    border-bottom: 1px solid #b8860b;
    text-align: center;
    height: 80px;
    padding: 10px;

    .icon {
      font-size: 38px;
      // color: #daa520;
      margin-bottom: 10px;
    }
    span {
      font-weight: bold;
    }
  }
  .border--top {
    border-top: 1px solid #b8860b;
  }
  .border--right {
    border-right: 1px solid #b8860b;
  }
  .border--left {
    border-left: 1px solid #b8860b;
  }
}
</style>