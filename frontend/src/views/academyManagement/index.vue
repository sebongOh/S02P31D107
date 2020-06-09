<template>
  <div class="board">
    <Header />
    <AcademyHome v-if="pageNum==0" />
    <AcademyEdit v-if="pageNum ==1" />
    <AcademyBoard v-if="pageNum==2" />
    <AcademyMore v-if="pageNum==3" />
    <Footer @changePage="changeNum" />
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import { removeToken } from "@/utils/auth";
import Footer from "./components/Footer";
import AcademyEdit from "./components/AcademyEdit";
import AcademyMore from "./components/AcademyMore";
import AcademyBoard from "./components/AcademyBoard";
import AcademyHome from "./components/AcademyHome";
export default {
  components: {
    Header,
    Footer,
    AcademyEdit,
    AcademyMore,
    AcademyBoard,
    AcademyHome
  },
  mounted() {
    this.getAcademy();
  },
  data() {
    return {
      haveAcademy: true,
      options: [],
      value: "",
      isAdd: false,
      pageNum: 0
    };
  },
  methods: {
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
    changeNum(num) {
      this.pageNum = num;
    },
    logout() {
      removeToken();
      var router = this.$router;
      router.push("/");
    }
  }
};
</script>

<style>
.board {
  font-family: "Yeon Sung", cursive;
  padding: 60px 0;
}
</style>
