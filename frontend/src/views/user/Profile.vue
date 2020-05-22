<template>
    <div id="Profile" class="components-container">
            <!-- 수정할 내용들 들어간다 / 원래 정보 v-bind 로 넣어놔줌 -->
            <table class="profile-table">
            <tr><th colspan="2">내 정보 수정</th></tr>
            <tr><td class="td1">이름</td>
            <td class="td2"><input class="input1" type="text" readonly="readonly"></td></tr>
            <tr><td class="td1">이메일</td>
            <td class="td2"><input class="input1" type="text" readonly="readonly"></td></tr>
            <tr><td class="td1">비밀번호</td>
            <td class="td2"><input class="input1" type="text"></td></tr><!-- 이부분은 비어있다 -->
            <tr><td class="td1">비밀번호 확인</td>
            <td class="td2"><input class="input1" type="text"></td></tr><!-- 이부분은 비어있다 / 위랑 맞아야 됨 -->
            <tr><td class="td1">주소</td>
            <td class="td2"><input class="input1" type="text"></td></tr>
            <tr><td class="td1">폰번호</td>
            <td class="td2"><input class="input1" type="text"></td></tr>
            <tr><td class="td1">나이</td>
            <td class="td2"><input class="input1" type="number"></td></tr>
            <tr><td class="td1">성별</td>
            <td class="td2"><select v-model="gender"><option :value="0">남</option><option :value="1">여</option></select></td></tr>
            <!-- 현재 비밀번호 확인 / 이게 맞아야 수정도 탈퇴도 된다 / 이 부분은 비어있다-->
            <!-- 수정 버튼 -->
            <tr><td colspan="2"><button>수정</button></td></tr>
            <!-- 회원탈퇴 버튼 -->
            <button>회원탈퇴</button>
            </table>
  </div>
</template>

<script> //다이어로그 안 뜨는 이유 확인
import Axios from "axios";

export default {
  name: 'Profile',
  data() {
    return {
      name: "",
      email: "",
      password: "",
      passwordConfirm: "",
      address: "",
      phone: "",
      age: 0,
      gender: 0
    }
  }
}
</script>

<style>
.profile-table{
  width:90%;
  margin: 5%;
  height: auto;
  text-align: center;
}
.td1{
  width: 20%;
  height: auto;
  text-align: left;
}
.td2{
  width: 80%;
  height: auto;
}
th{
  text-align: center;
  font-size: 20px;
  padding: 10px;
}
td{
  padding: 2px;
}
.input1{
  border-radius: 10px;
  width: 80%;
}
</style>