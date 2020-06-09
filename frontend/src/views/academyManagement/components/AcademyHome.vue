<template>
  <div class="app-container">
    <div>
      <div style="padding:10px">
        <el-select v-model="value" placeholder="조회할 학원 선택" style="padding-bottom:30px">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </div>
      <el-row :gutter="20" v-if="value!=''">
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
                <Qna :academyId="academyId" />
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
import UserCard from "./UserCard";
import Notice from "./Notice";
import Databoard from "./Databoard";
import Qna from "./Qna";

export default {
  components: { UserCard, Notice, Databoard, Qna },
  data() {
    return {
      academyId: "",
      name: "",
      address: "",
      category: "",
      phone: "",
      imgUrl: "",
      options: [],
      value: "",
      pageNum: 1,
      activeTab: "notice"
    };
  },
  methods: {
    pagechange(num) {
      this.pageNum = num;
    },
    getPaylist(academyId) {
      this.$store
        .dispatch("student/academyPaylist", academyId)
        .then(res => {
          for (let i = 0; i < res.data.length; i++) {
            this.paylists.push(res.data[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getAcademy() {
      this.$store
        .dispatch("student/memberAcademy")
        .then(res => {
          if (res.status == 200) {
            if (res.data == "") {
              this.haveAcademy = false;
            }
            this.options = [];
            for (var data of res.data) {
              this.options.push({ value: data.academyId, label: data.name });
            }
          } else {
            this.haveAcademy = false;
          }
        })
        .catch(() => {
          this.haveAcademy = false;
          console.log("에러 catch");
        });
    },
    getSelectAcademy(academyId) {
      this.$store
        .dispatch("student/selectAcademy", academyId)
        .then(res => {
          (this.academyId = res.data.academyId),
            (this.name = res.data.name),
            (this.address = res.data.address),
            (this.category = res.data.category),
            (this.phone = res.data.phone),
            (this.imgUrl = res.data.imgUrl);
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  watch: {
    value: function() {
      this.getSelectAcademy(this.value);
    }
  },
  mounted() {
    this.getAcademy();
  }
};
</script>

<style>
</style>