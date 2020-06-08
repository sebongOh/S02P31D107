<template>
  <div>
    <router-link to="/student-profile">
      <div>마이페이지 이동</div>
    </router-link>
    <button class="logout-btn" @click="logout()">로그아웃</button>
  </div>
</template>

<script>
import { removeToken } from "@/utils/auth";

export default {
  methods: {
    logout() {
      removeToken();
      this.$router.push("/");
    }
  }
};
</script>

<style>
.logout-btn {
  width: 100px;
  height: 40px;
  background-color: red;
  color: white;
  border-radius: 5px;
  border: 3px solid brown;
  margin: 10px;
}
</style>