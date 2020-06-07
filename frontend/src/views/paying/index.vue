<template>
  <div class="app-container">
    <Header />
    <Paying />
  </div>
</template>

<script>
import Paying from "./components/Paying";
import Header from "@/views/student/components/Header";
export default {
  components: { Paying, Header },
  data: () => {
    return {
      pg_token: "",
      memberId: "",
      scheduleId: ""
    };
  },
  mounted() {
    this.pg_token = this.$route.query.pg_token;
    this.memberId = this.$route.query.memberId;
    this.scheduleId = this.$route.query.scheduleId;
    console.log(this.pg_token);
    console.log(this.memberId);
    console.log(this.scheduleId);
    this.$store
      .dispatch("student/paySuccess", {
        pg_token: this.pg_token,
        memberId: this.memberId,
        scheduleId: this.scheduleId
      })
      .then(res => {
        this.$router.push({ path: "Payment" });
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
  padding: 60px 0;
}
</style>
