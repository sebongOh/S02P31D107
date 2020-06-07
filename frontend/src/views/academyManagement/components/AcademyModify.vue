<template>
    <div class="profile-div">
      <div v-if="!auth & !retirement">
        <table class="profile-table">
            <!-- 학원ID는 나중에 보이진 않고 서버에 수정할 때만 전송 -->
            <tr><th>학원ID</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="academyId"></td></tr>
            <tr><th>학원명</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="name"></td></tr>
            <tr><th>전화번호</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="phone"></td></tr>
            <tr><th>주소</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="address"></td></tr>
            <tr><th>카테고리</th></tr>
            <tr><td><input class="input1" type="text" readonly="readonly" v-model="category"></td></tr>
            <tr><th>학원 사진</th></tr>
            <tr><td><input id="imgUpload" type="file" accept="image/*" v-bind="imageUrl" @change="previewImage" /></td></tr>
            <tr><td><img v-if="imgData == ''" :src="imageUrl" class="img-size" />
            <img v-if="imgData != ''" :src="imgData" class="img-size" /></td></tr>
            <tr><td>학원 상세설명</td></tr>
            <tr><td colspan="2"><textarea class="input2" v-model="contents" /></td></tr>
            <tr><th>스케줄 추가</th></tr>
            <tr v-for="index in count" :key="index"><td><Schedule :academyId="academyId" /></td></tr>
            <tr><td><button class="add-btn2" @click="count++">+</button></td></tr>
            </table>
            <button @click="auth=true">학원 권한 변경</button>
            <div class="btn1"><button class="retire-btn" @click="retirement = true">학원 삭제</button></div>
            <button @click="modify()">학원 정보 수정</button>
      </div>
      <table v-if="auth" class="profile-table">
        <tr><th colspan="2">권한 넘겨줄 회원 ID</th></tr>
        <tr><td colspan="2"><input class="input1" type="text" v-model="memberId"></td></tr>
        <tr><th colspan="2">현재 비밀번호 확인</th></tr>
        <tr><td colspan="2"><input class="input1" type="password" v-model="password"></td></tr>
        <tr><td class="btn2"><button class="ok-btn" @click="passAuth()"><b>네</b></button></td>
        <td class="btn2"><button class="ok-btn" @click="auth = false"><b>취소</b></button></td></tr>
      </table>
      <table class="retirement-table" v-if="retirement">
        <tr><th colspan="2">현재 비밀번호 확인</th></tr>
        <tr><td colspan="2"><input class="input1" type="password" v-model="password"></td></tr>
        <tr><th colspan="2"><h2>정말 삭제하시겠습니까?</h2></th></tr>
        <tr><td class="btn2"><button class="ok-btn" @click="retire()"><b>네</b></button></td>
        <td><button class="ok-btn" @click="retirement = false"><b>취소</b></button></td></tr>
      </table>
    </div>
</template>

<script>
import Schedule from "@/views/academyManagement/components/Schedule";

export default {
  components: { Schedule },
  props: ["academyId"],
  mounted(){
    this.getAcademy();
  },
  watch:{
    academyId(academyId){
      this.getAcademy();
    }
  },
  data() {
    return {
      imageUrl: "",
      imgData: "",
      name: "",
      phone: "",
      address: "",
      category: "",
      password: "",
      contents: "",
      auth: false,
      retirement: false,
      count: 0
    }
  },
  methods:{
      getAcademy(){
        this.$store
          .dispatch("student/findAcademy", {
            academyId: this.academyId
          })
          .then((res) => {
            if (res.status == 200) {
              this.imageUrl = res.data.imageUrl;
              this.name = res.data.name;
              this.phone = res.data.phone;
              this.address = res.data.address;
              this.category = res.data.category;
              this.contents = res.data.contents.replace(/<br>/gi, '\n');
            }else{
              console.log("학원 상세 데이터를 가져오는데 문제가 발생했습니다.");
            }
          })
          .catch(() => {
            console.log("학원 상세 데이터를 가져오는데 문제가 발생했습니다. catch");
          });
      },
      modify(){
          // console.log(this.imageUrl);
          // console.log(this.imgData);
                          this.$store
          .dispatch("student/updateAcademy", {
            academyId : this.academyId,
            address : this.address,
            category : this.category,
            contents : this.contents,
            imageUrl : this.imageUrl,
            name : this.name,
            phone : this.phone
          })
          .then((res) => {
            if (res.status == 200) {
              console.log("학원 정보 수정이 완료되었습니다.");
            } else {
              this.contents = "학원 정보 수정에 실패했습니다."
            }
          })
          .catch(() => {
            console.log("학원 정보 수정에 실패했습니다. catch");
          });
      },
      passAuth(){
        if(this.memberId == ""){
          alert("권한을 이동하실 회원의 ID 를 입력해주세요!");
          return;
        }else if(this.password == ""){
          alert("현재 비밀번호를 입력해주세요!");
          return;
        }
        this.$store
          .dispatch("student/passwordCheck", {
            email: this.email,
            password: this.password,
          })
          .then((res) => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
              this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
            } else if (res.status == 200) {
              console.log("현재 비밀번호 인증 성공");
              //권한 이동
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
        console.log(this.memberId + " 님한테 " +this.academyId +" 권한이 넘어갔습니다.");
      },
      retire(){
      if(this.password == ""){
          alert("현재 비밀번호를 입력해주세요!");
          return;
        }
      this.$store
          .dispatch("student/passwordCheck", {
            email: this.email,
            password: this.password,
          })
          .then((res) => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
              this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
            } else if (res.status == 200) {
              console.log("현재 비밀번호 인증 성공");
              //회사 삭제
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
    },
    previewImage: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          this.imgData = e.target.result;
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
.retirement-table{
  width:90%;
  margin: 5%;
  height: auto;
  text-align: center;
  background-color: #F2F6FC;
  padding: 10px;
}
th, td{
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
.img-size{
  height: 50px;
  width: auto;
}
</style>