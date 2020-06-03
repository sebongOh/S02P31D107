<template>
  <div id="Profile" class="app-container">
    <div class="profile-div" v-if="!retirement">
      <h2>내 정보 수정</h2>
      <table class="profile-table">
        <tr>
          <th>프로필 사진</th>
        </tr>
        <tr>
          <td>
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </td>
        </tr>
        <tr>
          <th>이름</th>
        </tr>
        <tr>
          <td>
            <input
              class="input1"
              type="text"
              readonly="readonly"
              v-model="nameInput"
            />
          </td>
        </tr>
        <tr>
          <th>이메일</th>
        </tr>
        <tr>
          <td>
            <input
              class="input1"
              type="text"
              readonly="readonly"
              v-model="emailInput"
            />
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
        </tr>
        <tr>
          <td><input class="input1" type="password" v-model="password" /></td>
        </tr>
        <tr>
          <th>비밀번호 확인</th>
        </tr>
        <tr>
          <td>
            <input class="input1" type="password" v-model="passwordConfirm" />
          </td>
        </tr>
        <tr>
          <th>주소</th>
        </tr>
        <tr>
          <td><input class="input1" type="text" v-model="addressInput" /></td>
        </tr>
        <tr>
          <th>폰번호</th>
        </tr>
        <tr>
          <td><input class="input1" type="text" v-model="phoneInput" /></td>
        </tr>
        <tr>
          <th>나이</th>
        </tr>
        <tr>
          <td><input class="input1" type="number" v-model="ageInput" /></td>
        </tr>
        <tr>
          <th>
            성별&nbsp;&nbsp;
            <select v-model="genderInput"
              ><option :value="0">남</option
              ><option :value="1">여</option></select
            >
          </th>
        </tr>
        <!-- 현재 비밀번호 확인 / 이게 맞아야 수정도 탈퇴도 된다-->
        <tr>
          <th>현재 비밀번호 확인<span class="red">*필수</span></th>
        </tr>
        <tr>
          <td>
            <input class="input1" type="password" v-model="currentPassword" />
          </td>
        </tr>
      </table>

      <button class="modify-btn" @click="modify()"><b>수정</b></button>
      <div class="btn1">
        <button class="retire-btn" @click="retirement = true">회원탈퇴</button>
      </div>
    </div>
    <table class="retirement-table" v-if="retirement">
      <tr>
        <th colspan="2"><h2>정말 탈퇴하시겠습니까?</h2></th>
      </tr>
      <tr>
        <td class="btn2">
          <button class="ok-btn" @click="retire()"><b>네</b></button>
        </td>
        <td>
          <button class="ok-btn" @click="retirement = false">
            <b>취소</b>
          </button>
        </td>
      </tr>
    </table>

    <div class="footer-domain">
      <Footer />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";
import Footer from "@/views/profile/components/Footer";

export default {
  name: "Profile",
  components: {
    Footer,
  },
  computed: {
    ...mapGetters(["name", "email", "address", "phone", "age", "gender"]),
  },
  mounted() {
    this.getInputData();
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
      imageUrl: "", //나중에 imgUrl 을 받아서 getInputData 에서 넣어준다
    };
  },
  methods: {
    getInputData() {
      this.nameInput = this.name;
      this.emailInput = this.email;
      this.addressInput = this.address;
      this.phoneInput = this.phone;
      this.ageInput = this.age;
      this.genderInput = this.gender;
    },
    modify() {
      console.log("수정");
      if (this.currentPassword == "") {
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      } else {
        //currentPassword 를 서버에 보내서 확인하고 틀릴 경우 alert 보낸 후 return - member/checkPassword
      }
      if (this.password == "") {
        this.password = this.currentPassword;
        this.passwordConfirm = this.currentPassword;
      } else if (this.password != this.passwordConfirm) {
        alert("비밀번호와 비밀번호확인이 다릅니다!");
        return;
      }
      //수정 성공하면 입력(Input)된 내용으로 state 변경
    },
    retire() {
      console.log("탈퇴");
      if (this.currentPassword == "") {
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      } else {
        //currentPassword 를 서버에 보내서 확인하고 틀릴 경우 alert 보낸 후 return - member/checkPassword
      }
      //탈퇴하기
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("Avatar picture must be JPG format!");
      }
      if (!isLt2M) {
        this.$message.error("Avatar picture size can not exceed 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
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
.retirement-table {
  width: 100%;
  height: auto;
  text-align: center;
}
.ok-btn {
  width: 50%;
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
.footer-domain {
  width: auto;
  height: 80px;
}
</style>
