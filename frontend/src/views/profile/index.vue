<template>
    <div id="Profile" class="app-container">
            <div class="profile-div" v-if="!retirement">
            <h2>내 정보 수정</h2>
            <table class="profile-table">
            <tr><th>이름</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="nameInput"></td></tr>
            <tr><th>이메일</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="emailInput"></td></tr>
            <tr><th>비밀번호</th></tr>
            <tr><td><input class="input1" type="password" v-model="password"></td></tr>
            <tr><th>비밀번호 확인</th></tr>
            <tr><td><input class="input1" type="password" v-model="passwordConfirm"></td></tr>
            <tr><th>주소</th></tr>
            <tr><td><input class="input1" type="text" v-model="addressInput"></td></tr>
            <tr><th>폰번호</th></tr>
            <tr><td><input class="input1" type="text" v-model="phoneInput"></td></tr>
            <tr><th>나이</th></tr>
            <tr><td><input class="input1" type="number" v-model="ageInput"></td></tr>
            <tr><th>성별&nbsp;&nbsp;
              <select v-model="genderInput"><option :value="0">남</option><option :value="1">여</option></select></th></tr>
            <!-- 현재 비밀번호 확인 / 이게 맞아야 수정도 탈퇴도 된다-->
            <tr><th>현재 비밀번호 확인<span class="red">*필수</span></th></tr>
            <tr><td><input class="input1" type="password" v-model="currentPassword"></td></tr>
            </table>
            <button class="modify-btn" @click="modify()"><b>수정</b></button>
            <div class="btn1"><button class="retire-btn" @click="retirement = true">회원탈퇴</button></div>
            </div>
            <table class="retirement-table" v-if="retirement">
              <tr><th colspan="2"><h2>정말 탈퇴하시겠습니까?</h2></th></tr>
              <tr><td class="btn2"><button class="ok-btn" @click="retire()"><b>네</b></button></td>
              <td><button class="ok-btn" @click="retirement = false"><b>취소</b></button></td></tr>
            </table>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

export default {
  name: 'Profile',
  computed: {
    ...mapGetters(["name", "email", "address", "phone", "age", "gender"])
  },
  mounted(){
    this.getInputData()
  },
  data() {
    return {
      nameInput: "",
      emailInput: "",
      password: "",
      passwordConfirm: "",
      addressInput: "",
      phoneInput: "",
      ageInput: 0,
      genderInput: 0,
      currentPassword: "",
      retirement: false
    }
  },
  methods: {
    getInputData(){
      this.nameInput = this.name;
      this.emailInput = this.email;
      this.addressInput = this.address;
      this.phoneInput = this.phone;
      this.ageInput = this.age;
      this.genderInput = this.gender;
    },
    modify(){
      console.log("수정");
      //근데 우리 수정할 때 password 안 보내면 그냥 비번 빼고 수정해주나? 아니면 비번 비어있을 경우 current 값 넣어줘야하나
      //currentPassword 를 확인하고 알맞을 경우 수정 - member/checkPassword
      //비밀번호가 입력되어 있을 경우 확인이랑 같지 않으면 수정X
      //그리고 수정 성공하면 입력된 부분을 state 변경
    },
    retire(){
      console.log("탈퇴");
      //탈퇴누르면 다이어로그로 보이고 진짜 탈퇴할지 한번 더 묻고 ok 누르면 이 함수로
      //currentPassword 를 확인하고 알맞을 경우 탈퇴 - member/checkPassword
    }
  }
}
</script>

<style>
.profile-div{
  text-align: center;
}
.profile-table{
  width:90%;
  margin: 5%;
  height: auto;
  text-align: left;
  background-color: antiquewhite;
  padding: 10px;
}
th, td{
  padding: 3px;
}
.btn1{
  width:90%;
  height:auto;
  margin: 5%;
  text-align: right;
}
.btn2{
  width:50%;
}
.input1{
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 30px;
  margin-right: 1%;;
}
.red{
  color: red;
  font-size: 10px;
  vertical-align: top;
}
.modify-btn{
  width: 50%;
  height: 50px;
  font-size: 18px;
  background-color: green;
  border-radius: 5px;
  border: 3px solid darkgreen;
}
.retire-btn{
  width: 80px;
  height: 30px;
  background-color: red;
  color: white;
  border-radius: 5px;
  border: 3px solid brown;
}
.retirement-table{
  width:100%;
  height: auto;
  text-align: center;
}
.ok-btn{
  width:50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
</style>