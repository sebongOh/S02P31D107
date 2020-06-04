<template>
    <div id="Profile">
      <div class="app-container">
            <div class="profile-div" v-if="!retirement">
            <h2>내 정보 수정</h2>
            <table class="profile-table">
            <tr><th>프로필 사진</th></tr>
            <tr><td><input id="imgUpload" type="file" accept="image/*" v-bind="imgUrl" /></td></tr>
            <tr><th>이름</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="nameInput"></td></tr>
            <tr><th>이메일</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="emailInput"></td></tr>
            <tr><th>비밀번호</th></tr>
            <tr><td><input class="input1" type="password" v-model="password" @input="checkPasswordForm"></td></tr>
            <tr v-if="error.password"><td class="red">{{error.password}}</td></tr>
            <tr><th>비밀번호 확인</th></tr>
            <tr><td><input class="input1" type="password" v-model="passwordConfirm" @input="checkSame"></td></tr>
            <tr v-if="error.same"><td class="red">{{error.same}}</td></tr>
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
     <div class="footer-domain">
     <Footer />
     </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";
import Footer from "@/views/profile/components/Footer";
import PV from "password-validator";

export default {
  name: 'Profile',
  components: {
    Footer
  },
  computed: {
    ...mapGetters(["name", "email", "address", "phone", "age", "gender"])
  },
  created(){
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
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
      retirement: false,
      imageUrl: "",
      error: {},
      passwordSchema: new PV()
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
      if(this.currentPassword == ""){
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      //currentPassword 를 서버에 보내서 확인하고 틀릴 경우 alert 보낸 후 return - member/checkPassword
      if(this.password == ""){
        this.password = this.currentPassword;
        this.passwordConfirm = this.currentPassword;
      }else if(this.error.password){
        alert("비밀번호 형식이 알맞지 않습니다!");
        return;
      }else if(this.error.same){
        alert("비밀번호와 비밀번호확인이 일치하지 않습니다!");
        return;
      }
      //수정 성공하면 입력(Input)된 내용으로 state 변경
      console.log("수정");
    },
    retire(){
      if(this.currentPassword == ""){
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      //currentPassword 를 서버에 보내서 확인하고 틀릴 경우 alert 보낸 후 return - member/checkPassword
      //탈퇴하기
      console.log("탈퇴");
    },
    checkPasswordForm(e){
      var ps = e.target.value;
      if(ps != this.passwordConfirm){
        this.error.same = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
      }else{
        this.error.same = null;
      }
      if (ps.length > 0 && !this.passwordSchema.validate(ps)){
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      }else{
        this.error.password = false;
      }
    },
    checkSame(e){
      if(e.target.value != this.password){
        this.error.same = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
      }else{
        this.error.same = false;
      }
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
  background-color: #F2F6FC;
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
  width:90%;
  margin: 5%;
  height: auto;
  text-align: center;
  padding: 10px;
}
.ok-btn{
  width:50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
.footer-domain{
  width: auto;
  height: 80px;
}
</style>