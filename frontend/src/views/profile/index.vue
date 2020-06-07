<template>
    <div id="Profile">
      <div class="header-footer-div"><Header /></div>
      <div class="app-container">
            <div class="profile-div" v-if="!retirement">
            <h2>내 정보 수정</h2>
            <table class="profile-table">
            <tr><th>프로필 사진</th></tr>
            <tr><td><input id="imgUpload" type="file" accept="image/*" v-bind="profileUrl" @change="previewImage" /></td></tr>
            <tr><td><img :src="profileUrl" class="img-size" /></td></tr>
            <tr><th>이름</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="name"></td></tr>
            <tr><th>이메일</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="email"></td></tr>
            <tr><th>비밀번호</th></tr>
            <tr><td><input class="input1" type="password" v-model="password" @input="checkPasswordForm"></td></tr>
            <tr v-if="error.password"><td class="red">{{error.password}}</td></tr>
            <tr><th>비밀번호 확인</th></tr>
            <tr><td><input class="input1" type="password" v-model="passwordConfirm" @input="checkSame"></td></tr>
            <tr v-if="error.same"><td class="red">{{error.same}}</td></tr>
            <tr><th>주소</th></tr>
            <tr><td><input class="input1" type="text" v-model="address"></td></tr>
            <tr><th>폰번호</th></tr>
            <tr><td><input class="input1" type="text" v-model="phone"></td></tr>
            <tr><th>나이</th></tr>
            <tr><td><input class="input1" type="number" v-model="age"></td></tr>
            <tr><th>성별&nbsp;&nbsp;
              <select v-model="gender"><option :value="0">남</option><option :value="1">여</option></select></th></tr>
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
     <div class="header-footer-div"><Footer /></div>
  </div>
</template>

<script>
import Header from "@/components/common/Header";
import Footer from "@/components/common/Footer";
import PV from "password-validator";

export default {
  name: "Profile",
  components: {
    Header,
    Footer,
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
    this.getMemberData()
  },
  data() {
    return {
      name: "",
      email: "",
      password: "",
      passwordConfirm: "",
      address: "",
      phone: "",
      age: 0,
      gender: 0,
      currentPassword: "",
      retirement: false,
      profileUrl: "",
      type: "",
      error: {},
      passwordSchema: new PV(),
      showFindAddress: false
    }
  },
  methods: {
    getMemberData() {
      this.$store
          .dispatch("student/memberInfo")
          .then((res) => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
            } else if (res.status == 200) {
              this.name = res.data.name;
              this.email = res.data.email;
              this.address = res.data.address;
              this.phone = res.data.phone;
              this.age = res.data.age;
              this.gender = res.data.gender;
              this.profileUrl = res.data.profileUrl;
              this.type = res.data.type;
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
          });
    },
    modify(){
      if(this.currentPassword == ""){
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      this.$store
          .dispatch("student/passwordCheck", {
            email: this.email,
            password: this.currentPassword,
          })
          .then((res) => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
              this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
            } else if (res.status == 200) {
              console.log("현재 비밀번호 인증 성공");
              if(this.error.password){
                alert("비밀번호 형식이 알맞지 않습니다!");
                return;
              }else if(this.error.same){
                alert("비밀번호와 비밀번호확인이 일치하지 않습니다!");
                return;
              }
              if(this.password == ""){
                this.password = this.currentPassword;
                this.passwordConfirm = this.currentPassword;
              }
              let formData = new FormData();
              formData.enctype='multipart/form-data'; 
              formData.methods='put';
              formData.append("email", this.email);
              formData.append("name", this.name);
              formData.append("password", this.password);
              formData.append("address", this.address);
              formData.append("phone", this.phone);
              formData.append("age", this.age);
              formData.append("gender", this.gender);
              formData.append("profileUrl", this.profileUrl);
              formData.append("profileFile", this.profileUrl);
              formData.append("type", this.type);
              // for (var key of formData.keys()) {
              //   console.log(key);
              // }
              // for (var value of formData.values()) {
              //   console.log(value);
              // }
              this.$store
              .dispatch("student/updateProfile", formData)
              .then((res) => {
                this.name = res.data.name;
                this.email = res.data.email;
                this.address = res.data.address;
                this.phone = res.data.phone;
                this.age = res.data.age;
                this.gender = res.data.gender;
                this.profileUrl = res.data.profileUrl;
                this.password = "";
                this.passwordConfirm = "",
                this.currentPassword = ""
              })
              .catch((err) => {
                console.log(err);
              });
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
    },
    retire(){
      if(this.currentPassword == ""){
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      this.$store
          .dispatch("student/passwordCheck", {
            email: this.email,
            password: this.currentPassword,
          })
          .then((res) => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
              this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
            } else if (res.status == 200) {
              console.log("현재 비밀번호 인증 성공");
              //회원 탈퇴
              //탈퇴 성공하면 로그인 페이지로
              var router = this.$router;
              router.push("/");
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
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
    },
    previewImage: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          this.profileUrl = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      }
    }
  }
}
</script>

<style>
.profile-div {
  text-align: center;
}
.profile-table {
  width: 90%;
  margin: 5%;
  height: auto;
  text-align: left;
  background-color: #f2f6fc;
  padding: 10px;
}
th,
td {
  padding: 3px;
}
.btn1 {
  width: 90%;
  height: auto;
  margin: 5%;
  text-align: right;
}
.btn2 {
  width: 50%;
}
.input1 {
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 30px;
  margin-right: 1%;
}
.red {
  color: red;
  font-size: 10px;
  vertical-align: top;
}
.modify-btn {
  width: 50%;
  height: 50px;
  font-size: 18px;
  background-color: green;
  border-radius: 5px;
  border: 3px solid darkgreen;
}
.retire-btn {
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
.ok-btn {
  width: 50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
.img-size{
  height: 50px;
  width: auto;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
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
.header-footer-div{
  width: 100%;
  height: 60px;
}
</style>
