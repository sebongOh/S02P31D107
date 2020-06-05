<template>
  <div>
    <div class="my-academy">내 학원 가기</div>
    <div class="notice">새로운 공지사항</div>
    <div @click="goMypage()">마이페이지 이동</div>
  </div>
</template>

<script>
export default {
  methods:{
    goMypage(){
      console.log("클릭");
      this.$router.push("/student-profile");
    }
  }
};
</script>

<style>
</style>