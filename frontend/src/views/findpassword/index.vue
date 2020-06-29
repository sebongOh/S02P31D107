<template>
  <div class="user" id="login">
    <div class="wrapC">
      <h1>비밀번호 찾기</h1>

      <div class="input-with-label">
        <input
          v-model="email"
          v-bind:class="{
            error: error.email,
            complete: !error.email && email.length !== 0,
          }"
          @keyup.enter="find"
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
          v-model="name"
          type="text"
          id="name"
          @keyup.enter="find"
          placeholder="이름을 입력해주세요"
        />
        <label for="password">이름</label>
      </div>
      <button
        class="btn btn--back btn--login"
        @click="find"
        :disabled="!isSubmit"
        :class="{ disabled: !isSubmit }"
      >
        비밀번호 찾기
      </button>
      <div class="sns-login">
        <div class="text"></div>
      </div>
      <div class="add-option">
        <div class="text">
          <div class="bar"></div>
        </div>
        <div class="wrap">
          <p>아직 회원이 아니신가요?</p>
          <router-link v-bind:to="{ name: 'join' }" class="btn--text"
            >가입하기</router-link
          >
        </div>
        <div class="wrap">
          <router-link v-bind:to="{ name: 'login' }" class="btn--text"
            >뒤로 가기</router-link
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/style.scss";
import "../../assets/css/user.scss";
import * as EmailValidator from "email-validator";

export default {
  data: () => {
    return {
      email: "",
      name: "",
      error: {
        email: false,
      },
      isSubmit: false,
    };
  },
  methods: {
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    find() {
      if (this.isSubmit) {
        this.isSubmit = false;
        this.$store
          .dispatch("student/findPassword", {
            email: this.email,
            name: this.name,
          })
          .then((res) => {
            if (res.status == 200) {
              alert("이메일로 임시비밀번호가 전송되었습니다.");
            } else {
              alert("가입된 정보가 없습니다.");
            }
            this.isSubmit = true;
          })
          .catch((err) => {
            alert("가입된 정보가 없습니다.");
            this.isSubmit = true;
          });
      }
    },
  },
  watch: {
    email: function(v) {
      this.checkForm();
    },
  },
};
</script>

<style></style>
