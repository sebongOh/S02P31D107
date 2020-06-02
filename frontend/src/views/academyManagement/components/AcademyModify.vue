<template>
  <div class="profile-div">
    <div v-if="!auth">
      <table class="profile-table">
        <tr>
          <th>학원 사진</th>
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
        <!-- 학원ID는 나중에 보이진 않고 서버에 수정할 때만 전송 -->
        <tr>
          <th>학원ID</th>
        </tr>
        <tr>
          <td>
            <input
              class="input1"
              type="text"
              readonly="readonly"
              v-model="academyId"
            />
          </td>
        </tr>
        <tr>
          <th>학원명</th>
        </tr>
        <tr>
          <td><input class="input1" type="text" v-model="name" /></td>
        </tr>
        <tr>
          <th>전화번호</th>
        </tr>
        <tr>
          <td><input class="input1" type="text" v-model="phone" /></td>
        </tr>
        <tr>
          <th>주소</th>
        </tr>
        <tr>
          <td><input class="input1" type="text" v-model="address" /></td>
        </tr>
        <tr>
          <th>카테고리</th>
        </tr>
        <tr>
          <td>
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="어학원"
            />어학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="컴퓨터학원"
            />컴퓨터학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="무용학원"
            />무용학원<br />
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="공무원시험학원"
            />공무원시험학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="간호학원"
            />간호학원<br />
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="보습학원"
            />보습학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="음악학원"
            />음악학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="입시학원"
            />입시학원<br />
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="편입학원"
            />편입학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="미술학원"
            />미술학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="미용학원"
            />미용학원<br />
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="한문학원"
            />한문학원
            <input
              class="checkbox1"
              type="checkbox"
              v-model="category"
              value="수학학원"
            />수학학원
          </td>
        </tr>
        <tr>
          <th>스케줄 추가</th>
        </tr>
        <tr v-for="index in count" :key="index">
          <td><Schedule /></td>
        </tr>
        <tr>
          <td><button class="add-btn2" @click="count++">+</button></td>
        </tr>
      </table>

      <button @click="auth = true">학원 권한 변경</button>
      <div class="btn1"><button class="retire-btn">학원 삭제</button></div>
      <button @click="clickTest()">입력된 내용 확인</button>
    </div>
    <table v-if="auth" class="profile-table">
      <tr>
        <th colspan="2">권한 넘겨줄 회원 ID</th>
      </tr>
      <tr>
        <td colspan="2">
          <input class="input1" type="text" v-model="memberId" />
        </td>
      </tr>
      <tr>
        <th colspan="2">현재 비밀번호 확인</th>
      </tr>
      <tr>
        <td colspan="2">
          <input class="input1" type="password" v-model="password" />
        </td>
      </tr>
      <tr>
        <td class="btn2">
          <button class="ok-btn" @click="passAuth()"><b>네</b></button>
        </td>
        <td class="btn2">
          <button class="ok-btn" @click="auth = false"><b>취소</b></button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import Schedule from "@/views/academyManagement/components/Schedule";

export default {
  components: { Schedule },
  props: ["academyId"],
  data() {
    return {
      imageUrl: "",
      name: "",
      phone: "",
      address: "",
      category: [],
      memberId: "",
      password: "",
      auth: false,
      count: 0,
    };
  },
  methods: {
    clickTest() {
      console.log(this.category);
    },
    passAuth() {
      if (this.memberId == "") {
        alert("권한을 이동하실 회원의 ID 를 입력해주세요!");
        return;
      } else if (this.password == "") {
        alert("현재 비밀번호를 입력해주세요!");
        return;
      }
      console.log(
        this.memberId + " 님한테 " + this.academyId + " 권한이 넘어갔습니다."
      );
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
.input1 {
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 30px;
  margin-right: 1%;
}
.btn1 {
  width: 90%;
  height: auto;
  margin: 5%;
  text-align: right;
}
.btn2 {
  width: 50%;
  text-align: center;
}
.ok-btn {
  width: 50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
.retire-btn {
  width: 80px;
  height: 30px;
  background-color: red;
  color: white;
  border-radius: 5px;
  border: 3px solid brown;
}
.add-btn2 {
  margin: 10px;
  width: 50px;
  height: 35px;
  font-size: 25px;
  font-weight: 100;
  color: #909399;
  background: white;
  border: 1px solid #dcdfe6;
  border-radius: 3px;
  float: right;
}
.add-btn2:active {
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.42);
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
</style>
