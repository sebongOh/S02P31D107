<template>
  <div class="app-container">
    <Header />
    <Paying v-if="pagenum==0" />
    <PaySucc v-if="pagenum==1" :result="result" />
  </div>
</template>

<script>
import Paying from "./components/Paying";
import PaySucc from "./components/PaySucc";
import Header from "@/views/student/components/Header";
export default {
  components: { Paying, Header, PaySucc },
  data: () => {
    return {
      pagenum: 0,
      pg_token: "",
      memberId: "",
      scheduleId: "",
      result: {}
    };
  },
  mounted() {
    this.pg_token = this.$route.query.pg_token;
    this.scheduleId = this.$route.query.scheduleId;
    console.log(this.pg_token);
    console.log(this.scheduleId);
    this.$store
      .dispatch("student/paySuccess", {
        pg_token: this.pg_token,
        scheduleId: this.scheduleId
      })
      .then(res => {
        this.pagenum = 1;
        this.result = res.data;
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  },
  methods: {}
};
</script>

<style>
.app-container {
  font-family: "Yeon Sung", cursive;
  padding: 60px 0;
}
</style>
