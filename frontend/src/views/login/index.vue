<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1>로그인</h1>
      <div style="text-align:right; margin-bottom:10px">
        <el-button v-model="value" @click="change()" v-if="value"
          >학원회원</el-button
        >
        <el-button v-model="value" @click="change()" v-else>일반회원</el-button>
      </div>
      <div class="input-with-label">
        <input
          v-model="email"
          v-bind:class="{
            error: error.email,
            complete: !error.email && email.length !== 0,
          }"
          @keyup.enter="login"
          id="email"
          placeholder="이메일을 입력하세요."
          type="text"
        />
        <label for="email">이메일</label>
        <div class="error-text" v-if="error.email">
          {{ error.email }}
        </div>
      </div>

      <div class="input-with-label">
        <input
          v-model="password"
          type="password"
          v-bind:class="{
            error: error.password,
            complete: !error.password && password.length !== 0,
          }"
          id="password"
          @keyup.enter="login"
          placeholder="비밀번호를 입력하세요."
        />
        <label for="password">비밀번호</label>
        <div class="error-text" v-if="error.password">
          {{ error.password }}
        </div>
      </div>
      <button
        class="btn btn--back btn--login"
        @click="login"
        :disabled="!isSubmit"
        :class="{ disabled: !isSubmit }"
      >
        로그인
      </button>

      <div class="sns-login">
        <div class="text"></div>
      </div>
      <div class="add-option">
        <div class="text">
          <p>혹시</p>
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>비밀번호를 잊으셨나요?</p>
          <router-link v-bind:to="{ name: 'findPassword' }" class="btn--text"
            >비밀번호 찾기</router-link
          >
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link v-bind:to="{ name: 'join' }" class="btn--text"
            >가입하기</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";

export default {
  created() {
    this.component = this;

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
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    },
  },
  methods: {
    change() {
      this.value = !this.value;
      if (this.value) {
        this.type = "학원회원";
      } else {
        this.type = "일반회원";
      }
    },
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;
      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    login() {
      if (this.isSubmit) {
        this.isSubmit = false;
        this.$store
          .dispatch("student/login", {
            email: this.email,
            password: this.password,
            type: this.type,
          })
          .then((res) => {
            //메인으로 넘김
            if (this.type === "학원회원") {
              this.$router.push({ path: "/academy-management" });
            } else {
              this.$router.push({ path: "/student-main" });
            }
            this.isSubmit = true;
          })
          .catch((err) => {
            alert("아이디 비밀번호를 확인해주세요");
            this.isSubmit = true;
          });
      }
    },
  },
  data: () => {
    return {
      type: "일반회원",
      value: false,
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        passowrd: false,
      },
      isSubmit: false,
      component: this,
    };
  },
};
</script>
