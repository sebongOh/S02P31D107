<template>
  <div>
    <button class="profile-btn" @click="goMypage()">프로필 수정</button><br>
    <button class="logout-btn" @click="logout()">로그아웃</button>
  </div>
</template>

<script>
import { removeToken } from "@/utils/auth";

export default {
  methods:{
    goMypage(){
      console.log("클릭");
      this.$router.push("/student-profile");
    },
    logout(){
      removeToken();
      var router = this.$router;
      router.push("/");
    }
  }
};
</script>

<style>
.profile-btn{
  width: 100px;
  height: 40px;
  background-color: #90EE90;
  color: white;
  border-radius: 5px;
  border: 3px solid #32CD32;
  margin: 10px;
}
.logout-btn{
  width: 100px;
  height: 40px;
  background-color: red;
  color: white;
  border-radius: 5px;
  border: 3px solid brown;
  margin: 10px;
}
</style>