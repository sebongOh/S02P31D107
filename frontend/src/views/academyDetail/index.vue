<template>
  <div>
    <div class="header-footer-div">
      <Header />
    </div>
    <div class="academy-detail">
      <h2>{{name}}</h2>
      <img v-if="imgUrl != ''" class="img-div" :src="imgUrl" />
      <div v-if="imgUrl == ''" class="img-div" />
      <el-col :span="18" :xs="24">
        <el-card>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="상세정보" name="detail">
              <Detail
                :address="address"
                :category="category"
                :contents="contents"
                :phone="phone"
                :isPayed="isPayed"
              />
            </el-tab-pane>
            <el-tab-pane label="리뷰" name="reviewboard">
              <ReviewBoard :academyId="academyId" :isPayed="isPayed" />
            </el-tab-pane>
            <el-tab-pane label="코스목록" name="course">
              <Course />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </div>
    <div class="header-footer-div">
      <Footer />
    </div>
  </div>
</template>

<script>
import Detail from "@/views/academyDetail/components/Detail";
import ReviewBoard from "@/views/academyDetail/components/ReviewBoard";
import Course from "@/views/academyDetail/components/Course";
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";

export default {
    components: { Detail, ReviewBoard, Course, Header, Footer },
    props: ["academyId", "name", "address", "phone", "category"],
    mounted(){
        this.getAcademy();
    },
    data() {
        return {
            activeTab: "detail",
            contents: "",
            imgUrl: "",
            isPayed: false
        }
    },
    methods: {
        getAcademy(){
          console.log(this.academyId);
          console.log(this.name);
          //임시 데이터 삽입
          // this.academyId = "9749818";
          // this.isPayed = true;
          // this.contents = "학원 상세 설명 부분";
          // this.name = "싸피학원";
          // this.address = "OO시 OO구 OO동";
          // this.phone = "010-0000-1111";
          // this.imgUrl = "http://edu.ssafy.com/asset/images/header-logo.jpg";
          //임시 데이터 삽입 끝

          //일단 학원 가져오고 DB 에 없으면 아래 문구만 추가하기
          this.contents = "아직 등록되지 않은 학원입니다."
      }
  }
};
</script>

<style>
h2 {
  margin: 0;
  padding: 5px;
}
.academy-detail {
  width: 100%;
  height: auto;
  background-color: #dcdfe6;
}
.img-div {
  width: 100%;
  height: 300px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.header-footer-div {
  width: 100%;
  height: 60px;
}
</style>
