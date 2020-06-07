<template>
  <div class="home--container">
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
    <div class="big--box">
      <div class="select">
        <el-row>
          <el-col :span="8">
            <div class="select--box border--right border--left border--top">
              <div class="icon">
                <i class="fas fa-book"></i>
              </div>
              <span>단과</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="select--box border--right border--top">
              <div class="icon">
                <i class="fas fa-university"></i>
              </div>
              <span>입시</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="select--box border--right border--top">
              <div class="icon">
                <i class="fas fa-globe-americas"></i>
              </div>
              <span>외국어</span>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <div class="select--box border--right border--left">
              <div class="icon">
                <i class="fas fa-chalkboard-teacher"></i>
              </div>
              <span>공무원</span>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="select--box border--right">
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
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="select--box border--left">
              <div class="icon">
                <i class="fas fa-palette"></i>
              </div>
              <span>미술</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="select--box border--right border--left">
              <div class="icon">
                <i class="fas fa-pencil-alt"></i>
              </div>
              <span>독서실</span>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="select--box border--right">
              <div class="icon">
                <i class="fas fa-cookie-bite"></i>
              </div>
              <span>요리</span>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="news">
        <div class="news--title">
          <span>실시간뉴스</span>
        </div>
        <div class="news--body">
          <div class="article">
            <span>2021 SKY대 수시 대격변!...서,연,고 학생부종합전형 합격전략</span>
          </div>
          <div class="article">
            <span>[정부] 어린이집, 유치원, 초등학교 3월 2~6일 긴급돌봄 실시</span>
          </div>
          <div class="article">
            <span>[Science&Tech] 초지능 시대의 도래, 인간은 무엇을 대비해야 하나?</span>
          </div>
          <div class="article">
            <span>서울 교육청 '2021 대입 진학 지도' 동영상 서비스, 유튜브로 보자!</span>
          </div>
          <div class="article">
            <span>[2021 사관학교, 경찰대학교 입시] 전형 일정 및 지원 전략</span>
          </div>
          <div class="article">
            <span>개학은 연기됐는데, 학부모총회는 어떻게 하지?</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.home--container {
  font-family: "Yeon Sung", cursive;
}
.big--box {
  padding: 10px;
  // background-color: #fffff5;
}
.ad {
  text-align: center;

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
  margin-bottom: 10px;
  border-radius: 10px;
  // background-color: #f2e6ce;
  .select--box {
    border-radius: 10px;
    border-bottom: 1px solid #a69d8d;
    text-align: center;
    height: 80px;
    padding: 10px;

    .icon {
      font-size: 38px;
      margin-bottom: 10px;
    }
    span {
      font-size: 20px;
    }
  }
  .select--box:hover {
    background-color: #f2e4c9;
  }
  .border--top {
    border-top: 1px solid #a69d8d;
  }
  .border--right {
    border-right: 1px solid #a69d8d;
  }
  .border--left {
    border-left: 1px solid #a69d8d;
  }
}
.news {
  padding: 10px;
  background-color: #eee;
  border-radius: 10px;
  .news--title {
    margin-bottom: 10px;
    span {
      font-size: 23px;
      font-weight: bold;
    }
  }
  .news--body {
    .article {
      border-bottom: 1px solid;
      margin-bottom: 10px;
    }
  }
}
</style>