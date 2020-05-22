<template>
    <div id="Test">
        <img id="profile-img" src="../../assets/icon/user.png" @click="profile = !profile">
        <div id="profile-dialog">
            <img class="close" v-if="profile" src="../../assets/icon/close.png" @click="profile = false">
            <ProfileModify v-if="profile" />
        </div>
    </div>
</template>

<script>
import ProfileModify from "./Profile.vue";
export default {
    components: { ProfileModify },
  data() {
    return {
      profile:false
    }
  }
}
</script>

<style>
#Test{
    width:100%;
    height: 100%;
    background-color: gray;
}
#profile-dialog{
  background-color: white;
  border-radius: 10px;
  width:90%;
  height:auto;
  left: 5%;
  position: relative;
}
#profile-img{
    width:50px;
    height:50px;
    margin: 5px;
}
.close{
    float: right;
    width:20px;
    height:20px;
    margin: 5px;
}
</style>