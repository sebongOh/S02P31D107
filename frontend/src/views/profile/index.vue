<template>
  <div id="app-container">
    <Header />
    <div class="profile">
      <div class="title">
        <span>내 정보 수정</span>
      </div>
      <div class="profile--data">
        <div class="small--title">프로필 사진</div>
        <div class="form--data profile-img">
          <img v-if="profileFile==''" :src="profileUrl" class="img-size" />
          <img v-if="profileFile!=''" :src="prifileFile" class="img-size" />
          <input
            id="imgUpload"
            type="file"
            accept="image/*"
            v-bind="profileUrl"
            @change="previewImage"
          />
        </div>
        <div class="small--title">이름</div>
        <div class="form--data">
          <input type="text" readonly="readonly" v-model="name" />
        </div>
        <div class="small--title">이메일</div>
        <div class="form--data">
          <input type="text" readonly="readonly" v-model="email" />
        </div>
        <div class="small--title">
          비밀번호
          <span v-if="error.password">{{error.password}}</span>
        </div>
        <div class="form--data">
          <input type="password" v-model="password" @input="checkPasswordForm" />
        </div>
        <div class="small--title">
          비밀번호 확인
          <span v-if="error.same">{{error.same}}</span>
        </div>
        <div class="form--data">
          <input type="password" v-model="passwordConfirm" @input="checkSame" />
        </div>
        <div class="small--title">주소</div>
        <div class="form--data">
          <input type="text" v-model="address" />
        </div>
        <div class="small--title">폰번호</div>
        <div class="form--data">
          <input type="text" v-model="phone" />
        </div>

        <div class="small--title">성별</div>
        <div class="form--data">
          <select v-model="gender">
            <option :value="0">남</option>
            <option :value="1">여</option>
          </select>
        </div>
        <div class="small--title">
          현재 비밀번호 확인
          <span>*필수</span>
        </div>
        <div class="form--data">
          <input type="password" v-model="currentPassword" />
        </div>
      </div>
      <div class="btn">
        <div class="modify">
          <button @click="modify()">수정</button>
        </div>
        <div class="retire">
          <button @click="retire()">탈퇴</button>
        </div>
      </div>
    </div>
    <Footer />
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
    Footer
  },
  created() {
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
  mounted() {
    this.getMemberData();
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
      profileFile: "",
      type: "",
      error: {},
      passwordSchema: new PV(),
      showFindAddress: false
    };
  },
  methods: {
    getMemberData() {
      this.$store
        .dispatch("student/memberInfo")
        .then(res => {
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
    modify() {
      if (this.currentPassword == "") {
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      this.$store
        .dispatch("student/passwordCheck", {
          email: this.email,
          password: this.currentPassword
        })
        .then(res => {
          if (res.status == 404) {
            console.log("aniVibro가 뭐죠 404");
            this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
          } else if (res.status == 200) {
            console.log("현재 비밀번호 인증 성공");
            if (this.error.password) {
              alert("비밀번호 형식이 알맞지 않습니다!");
              return;
            } else if (this.error.same) {
              alert("비밀번호와 비밀번호확인이 일치하지 않습니다!");
              return;
            }
            if (this.password == "") {
              this.password = this.currentPassword;
              this.passwordConfirm = this.currentPassword;
            }
            // let formData = new FormData();
            // formData.enctype='multipart/form-data';
            // formData.methods='put';
            // formData.append("email", this.email);
            // formData.append("name", this.name);
            // formData.append("password", this.password);
            // formData.append("address", this.address);
            // formData.append("phone", this.phone);
            // formData.append("age", this.age);
            // formData.append("gender", this.gender);
            // formData.append("profileUrl", this.profileUrl);
            // formData.append("profileFile", this.profileUrl);
            // formData.append("type", this.type);
            // for (var key of formData.keys()) {
            //   console.log(key);
            // }
            // for (var value of formData.values()) {
            //   console.log(value);
            // }
            this.$store
              .dispatch("student/updateProfile", {
                email: this.email,
                name: this.name,
                password: this.password,
                address: this.address,
                phone: this.phone,
                age: this.age,
                gender: this.gender,
                profileUrl: this.profileUrl,
                type: this.type
              })
              .then(res => {
                this.name = res.data.name;
                this.email = res.data.email;
                this.address = res.data.address;
                this.phone = res.data.phone;
                this.age = res.data.age;
                this.gender = res.data.gender;
                this.profileUrl = res.data.profileUrl;
                this.password = "";
                (this.passwordConfirm = ""), (this.currentPassword = "");
              })
              .catch(err => {
                console.log(err);
              });
          }
        })
        .catch(() => {
          console.log("aniVibro가 뭐죠 catch");
          this.aniVibro("code", "서버 접속을 실패했습니다.");
        });
    },
    retire() {
      if (this.currentPassword == "") {
        alert("현재 비밀번호는 반드시 입력해야 합니다!");
        return;
      }
      this.$store
        .dispatch("student/passwordCheck", {
          email: this.email,
          password: this.currentPassword
        })
        .then(res => {
          if (res.status == 404) {
            console.log("aniVibro가 뭐죠 404");
            this.aniVibro("code", "현재 비밀번호 인증에 실패하였습니다.");
          } else if (res.status == 200) {
            console.log("현재 비밀번호 인증 성공");
            this.$store
              .dispatch("student/retire")
              .then(res => {
                if (res.status == 404) {
                  console.log("aniVibro가 뭐죠 404");
                } else if (res.status == 200) {
                  var router = this.$router;
                  router.push("/");
                }
              })
              .catch(() => {
                console.log("aniVibro가 뭐죠 catch");
                this.aniVibro("code", "서버 접속을 실패했습니다.");
              });
          }
        })
        .catch(() => {
          console.log("aniVibro가 뭐죠 catch");
          this.aniVibro("code", "서버 접속을 실패했습니다.");
        });
    },
    checkPasswordForm(e) {
      var ps = e.target.value;
      if (ps != this.passwordConfirm) {
        this.error.same = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
      } else {
        this.error.same = null;
      }
      if (ps.length > 0 && !this.passwordSchema.validate(ps)) {
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      } else {
        this.error.password = false;
      }
    },
    checkSame(e) {
      if (e.target.value != this.password) {
        this.error.same = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
      } else {
        this.error.same = false;
      }
    },
    previewImage: function(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = e => {
          this.profileFile = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      }
    }
  }
};
</script>

<style lang="scss">
.profile {
  padding: 60px 10px;

  .title {
    margin-top: 15px;
    text-align: center;
    span {
      font-size: 28px;
    }
  }
  .profile-img {
    background-color: #d9cab0;
  }
  .profile--data {
    padding: 10px;
    background-color: #d9cab0;
    border-radius: 10px;
    margin: 15px 0;
    img {
      width: 50px;
    }

    .small--title {
      margin: 10px 0 5px 0;
      span {
        font-size: 0.8em;
        color: red;
      }
    }
    .form--data {
      input {
        width: 90%;
        height: 25px;
        border: 0px;
        border-radius: 5px;
        background-color: white;
      }
    }
  }

  .btn {
    text-align: center;
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
    .modify {
      margin-right: 15px;
      button {
        width: 80px;
        height: 40px;
        background-color: #403a33;
        color: white;
        border-radius: 5px;
      }
    }
    .retire {
      button {
        width: 80px;
        height: 40px;
        color: #fff;
        background-color: #f56c6c;
        border-color: #f56c6c;
        border-radius: 5px;
      }
    }
  }
}
</style>
