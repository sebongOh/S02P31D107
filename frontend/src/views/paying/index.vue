<template>
  <div>
    <Paying />
  </div>
</template>

<script>
import Paying from "./components/Paying";
export default {
  components: { Paying },
  data: () => {
    return {
      pg_token: "",
      memberId: "",
      scheduleId: "",
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
        scheduleId: this.scheduleId,
      })
      .then((res) => {
        this.$router.push({ path: "Payment" });
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {},
};
</script>

<style></style>
