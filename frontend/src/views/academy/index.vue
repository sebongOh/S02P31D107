<template>
  <div class="app-container">
    <el-row>
      <Header />
    </el-row>
    <div v-if="user">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="공지사항" name="notice">
                <Notice />
              </el-tab-pane>
              <el-tab-pane label="자료게시판" name="databoard">
                <Databoard />
              </el-tab-pane>
              <el-tab-pane label="1대1문의" name="qna">
                <Qna :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import UserCard from "./components/UserCard";
import Notice from "./components/Notice";
import Databoard from "./components/Databoard";
import Qna from "./components/Qna";
import Header from "@/views/student/components/Header";

export default {
  name: "Profile",
  components: { UserCard, Notice, Databoard, Qna, Header },
  data() {
    return {
      user: {},
      activeTab: "notice",
    };
  },
  computed: {
    ...mapGetters(["name", "avatar", "roles"]),
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      this.user = {
        name: this.name,
        role: this.roles.join(" | "),
        email: "admin@test.com",
        avatar: this.avatar,
      };
    },
  },
};
</script>
