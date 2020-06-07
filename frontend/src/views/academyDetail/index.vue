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
                :academyId="academyId"
                :name="name"
                :address="address"
                :category="category"
                :contents="contents"
                :phone="phone"
                :imgUrl="imgUrl"
                :isPayed="isPayed"
              />
            </el-tab-pane>
            <el-tab-pane label="리뷰" name="reviewboard">
              <ReviewBoard :academyId="academyId" :isPayed="isPayed" />
            </el-tab-pane>
            <el-tab-pane label="코스목록" name="course">
              <Course :academyId="academyId" />
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
            this.$store
          .dispatch("student/findAcademy", {
            academyId : this.academyId,
          })
          .then((res) => {
            if (res.status == 200) {
              this.contents = res.data.contents;
              this.imgUrl = res.data.imageUrl;
            } else {
              this.contents = "아직 등록되지 않은 학원입니다."
            }
          })
          .catch(() => {
            this.contents = "아직 등록되지 않은 학원입니다."
            console.log("학원 상세정보 불러오기에 실패했습니다. catch");
          });
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
