<template>
  <div class="app-container">
    <Header />
    <div v-if="user">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <UserCard
            :academyId="academyId"
            :name="name"
            :address="address"
            :category="category"
            :phone="phone"
            :imgUrl="imgUrl"
          />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="공지사항" name="notice">
                <Notice v-if="pageNum == 1" @changePageNum="pagechange" :academyId="academyId" />
                <InsertNotice v-if="pageNum == 2" @changePageNum="pagechange" />
              </el-tab-pane>
              <el-tab-pane label="자료게시판" name="databoard">
                <Databoard v-if="pageNum == 1" @changePageNum="pagechange" :academyId="academyId" />
                <InsertDataboard v-if="pageNum == 2" @changePageNum="pagechange" />
              </el-tab-pane>
              <el-tab-pane label="1대1문의" name="qna">
                <Qna :user="user" :academyId="academyId" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <!-- <Footer /> -->
  </div>
</template>

<script>
import UserCard from "./components/UserCard";
import Notice from "./components/Notice";
import InsertNotice from "./components/InsertNotice";
import Databoard from "./components/Databoard";
import InsertDataboard from "./components/InsertDataboard";
import Qna from "./components/Qna";
import Header from "@/views/student/components/Header";
// import Footer from "./components/Footer";
import { getToken } from "@/utils/auth";

export default {
  name: "Profile",
  props: ["academyId", "name", "address", "phone", "category", "imgUrl"],
  components: {
    UserCard,
    InsertNotice,
    Notice,
    Databoard,
    InsertDataboard,
    Qna,
    Header
    // Footer
  },
  data() {
    return {
      pageNum: 1,
      user: {},
      activeTab: "notice"
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    pagechange(num) {
      this.pageNum = num;
    },
    getUser() {}
  }
};
</script>
<style lang="scss">
.app-container {
  padding: 60px 0;
  font-family: "Yeon Sung", cursive;
}
</style>
