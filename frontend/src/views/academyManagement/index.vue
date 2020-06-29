<template>
    <div class="font-type">
      <div class="header-footer-div"><Header /></div>
      <div class="app-container" v-if="haveAcademy">
        <h1>학원관리 페이지</h1>
        <el-select v-model="value" placeholder="관리할 학원 선택">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"></el-option>
        </el-select>
        <button class="add-btn" @click="addAcademy()">+</button>
        <AcademyAdd v-if="isAdd & value==''"/>
        <AcademyModify v-if="value!=''" :academyId="value"/>
      </div>
      <div v-if="!haveAcademy" class="no-have-academy">
        현재 권한을 가지고 계시는 학원이 없습니다.<br>
        권한 요청 승인을 기다려주세요!<br>
        문의 : learnacademy02@gmail.com
      </div>
      <br><br><el-row>
          <el-col :span="24">
            <el-button type="warning" style="width:100%" @click="logout()">
            <b>로그아웃</b>
            </el-button>
          </el-col>
        </el-row>
    </div>
</template>

<script>
import AcademyModify from "@/views/academyManagement/components/AcademyModify";
import AcademyAdd from "@/views/academyManagement/components/AcademyAdd";
import Header from "@/components/common/Header";
import { removeToken } from "@/utils/auth";

export default {
  components: { AcademyModify, AcademyAdd, Header },
  mounted(){
    this.getAcademy();
  },
  data() {
    return {
      haveAcademy:true,
      options: [],
      value: "",
      isAdd: false,
    };
  },
  methods: {
    addAcademy() {
      this.isAdd = true;
      this.value = "";
    },
  getAcademy() {
    this.$store
        .dispatch("student/memberAcademy")
        .then((res) => {
            if (res.status == 200) {
              if(res.data == ""){
                this.haveAcademy = false;
              }
                this.options = [];
                for(var data of res.data){
                  this.options.push({value:data.academyId, label:data.name});
                }
            }else{
              this.haveAcademy = false;
            }
        })
        .catch(() => {
            this.haveAcademy = false;
            console.log("에러 catch");
        });
    },
    logout(){
      removeToken();
      var router = this.$router;
      router.push("/");
    }
  },
};
</script>

<style>
.font-type{
  font-family: "Yeon Sung", cursive;
}
.app-container{
  padding: 2%;
}
.add-btn{
  margin: 10px;
  width: 90px;
  height: 35px;
  font-size: 25px;
  font-weight: 100;
  color: #909399;
  background: white;
  border: 1px solid #dcdfe6;
  border-radius: 3px;
}
.add-btn:active {
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.42);
}
.no-have-academy{
  width: 100%;
  text-align: center;
  margin-top: 50px;
}
.header-footer-div{
  width: 100%;
  height: 60px;
}
</style>
