<template>
    <div class="profile-div">
        <table class="profile-table">
            <tr><th>학원 증명서</th></tr>
            <tr><td><el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="academyAuthUrl" :src="academyAuthUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload></td></tr>
            <tr><th>학원 사진</th></tr>
            <tr><td><el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload></td></tr>
            <tr><th>학원명</th></tr>
            <tr><td><input class="input1" type="text" v-model="name"></td></tr>
            <tr><th>전화번호</th></tr>
            <tr><td><input class="input1" type="text" v-model="phone"></td></tr>
            <tr><th>주소</th></tr>
            <tr><td><input class="input1" type="text" v-model="address"></td></tr>
            <tr><th>카테고리</th></tr>
            <tr><td>
            <input class="checkbox1" type="checkbox" v-model="category" value="어학원">어학원
            <input class="checkbox1" type="checkbox" v-model="category" value="컴퓨터학원">컴퓨터학원
            <input class="checkbox1" type="checkbox" v-model="category" value="무용학원">무용학원<br>
            <input class="checkbox1" type="checkbox" v-model="category" value="공무원시험학원">공무원시험학원
            <input class="checkbox1" type="checkbox" v-model="category" value="간호학원">간호학원<br>
            <input class="checkbox1" type="checkbox" v-model="category" value="보습학원">보습학원
            <input class="checkbox1" type="checkbox" v-model="category" value="음악학원">음악학원
            <input class="checkbox1" type="checkbox" v-model="category" value="입시학원">입시학원<br>
            <input class="checkbox1" type="checkbox" v-model="category" value="편입학원">편입학원
            <input class="checkbox1" type="checkbox" v-model="category" value="미술학원">미술학원
            <input class="checkbox1" type="checkbox" v-model="category" value="미용학원">미용학원<br>
            <input class="checkbox1" type="checkbox" v-model="category" value="한문학원">한문학원
            <input class="checkbox1" type="checkbox" v-model="category" value="수학학원">수학학원
            </td></tr>
            </table>
            <button @click="addAcademy()">학원 추가</button>
    </div>
</template>

<script>

export default {
    data() {
    return {
      academyAuthUrl: "",
      imageUrl: "",
      name: "",
      phone: "",
      address: "",
      category: [],
      memberId: "",
      password: ""
    }
  },
  methods:{
      addAcademy(){
          alert("학원 인증을 요청하였습니다.\n인증되면 자동으로 학원이 추가됩니다.");
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
.input1{
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 30px;
  margin-right: 1%;;
}
.btn1{
  width:90%;
  height:auto;
  margin: 5%;
  text-align: right;
}
.btn2{
  width:50%;
  text-align: center;
}
.ok-btn{
  width:50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
.retire-btn{
  width: 80px;
  height: 30px;
  background-color: red;
  color: white;
  border-radius: 5px;
  border: 3px solid brown;
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
</style>