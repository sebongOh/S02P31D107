<template>
    <div class="profile-div">
        아카데미ID로 학원 정보 받아와서 수정 및 삭제
        <table class="profile-table">
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
            <!-- 학원ID는 나중에 보이진 않고 서버에 수정할 때만 전송 -->
            <tr><th>학원ID</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="academyId"></td></tr>
            <tr><th>학원명</th></tr>
            <tr><td><input class="input1" type="text" v-model="name"></td></tr>
            <tr><th>전화번호</th></tr>
            <tr><td><input class="input1" type="text" v-model="phone"></td></tr>
            <tr><th>주소</th></tr>
            <tr><td><input class="input1" type="text" v-model="address"></td></tr>
            <!-- 카테고리는 목록 중 선택 방식 / 복수 가능 -->
            <tr><th>카테고리</th></tr>
            <tr><td>
            <input class="checkbox1" type="checkbox" name="category" value="어학원">어학원
            <input class="checkbox1" type="checkbox" name="category" value="컴퓨터학원">컴퓨터학원
            <input class="checkbox1" type="checkbox" name="category" value="무용학원">무용학원<br>
            <input class="checkbox1" type="checkbox" name="category" value="공무원시험학원">공무원시험학원
            <input class="checkbox1" type="checkbox" name="category" value="간호학원">간호학원<br>
            <input class="checkbox1" type="checkbox" name="category" value="보습학원">보습학원
            <input class="checkbox1" type="checkbox" name="category" value="음악학원">음악학원
            <input class="checkbox1" type="checkbox" name="category" value="입시학원">입시학원<br>
            <input class="checkbox1" type="checkbox" name="category" value="편입학원">편입학원
            <input class="checkbox1" type="checkbox" name="category" value="미술학원">미술학원
            <input class="checkbox1" type="checkbox" name="category" value="미용학원">미용학원<br>
            <input class="checkbox1" type="checkbox" name="category" value="한문학원">한문학원
            <input class="checkbox1" type="checkbox" name="category" value="수학학원">수학학원
            </td></tr>
            <!-- 현재 비밀번호 확인(v-if 로 빼내자) / 이게 맞아야 학원 권한 이동 가능 -->
            <!-- 권한 이동(다른 사람한테 학원 넘겨줌) 버튼 -> 현재 비번 확인 -> 이동성공 -->
            <tr><th>현재 비밀번호 확인<span class="red">*필수</span></th></tr>
            <tr><td><input class="input1" type="password" v-model="password"></td></tr>
            </table>
            <button @click="clickTest()">입력된 내용 확인</button>
    </div>
</template>

<script>
export default {
    props:["academyId"],
    data() {
    return {
      name: "",
      phone: "",
      address: "",
      category: [],
      password: "",
      auth: false
    }
  },
  methods:{
      clickTest(){
          console.log(this.category);
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
.red{
  color: red;
  font-size: 10px;
  vertical-align: top;
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