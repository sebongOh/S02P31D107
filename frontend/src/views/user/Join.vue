<template>
  <div>
    <el-steps
      :active="active"
      finish-status="success"
      simple
      style="margin-top: 20px"
    >
      <el-step title="Step 1"></el-step>
      <el-step title="Step 2"></el-step>
      <el-step title="Step 3"></el-step>
    </el-steps>
    <br />
    <div id="joinform">
      <!--------------------------------- 회원선택 폼--------------------------------------->
      <el-row class="demo-autocomplete" v-if="active === 0">
        <el-col :span="12">
          <el-form>
            <div>회원을 고르세요</div>
          </el-form>
        </el-col>
      </el-row>
      <!--------------------------------- 회원선택 폼--------------------------------------->

      <!--------------------------------- 회원가입 폼--------------------------------------->
      <el-row
        class="demo-autocomplete"
        v-if="active === 1"
        id="memberjoin"
        style="max-width:50%"
      >
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-form
            id="formData"
            enctype="multipart/form-data"
            ref="member"
            :model="member"
            label-width="120px"
            label-position="left"
          >
            <el-form-item label="프로필이미지" required>
              <el-upload
                action="https://jsonplaceholder.typicode.com/posts/"
                list-type="picture-card"
                accept="image/jpeg,image/png"
                :on-preview="handlePictureCardPreview"
                :on-change="onUploadChange"
                :on-remove="handleRemove"
                name="image"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="" />
              </el-dialog>
            </el-form-item>

            <p :class="ux.isOverlapId" style="text-align:right">
              <el-button
                size="mini"
                round
                :class="{
                  primary: !pageInfo.isOverlapId,
                  warning: pageInfo.isOverlapId,
                }"
                @click="idOverlap"
              >
                {{ ux.overlapText[pageInfo.isOverlapId ? 1 : 0] }}
              </el-button>
              <el-form-item label="아이디" required>
                <el-input v-model="member.id" name="id"></el-input>
              </el-form-item>
            </p>

            <p :class="ux.password">
              <el-form-item label="비밀번호" required>
                <el-input
                  placeholder="비밀번호입력하시오"
                  v-model="member.password"
                  name="password"
                  show-password
                ></el-input>
              </el-form-item>
            </p>
            <p :class="ux.password">
              <el-form-item label="비밀번호확인" required>
                <el-input
                  placeholder="비밀번호를 다시 입력하시오"
                  v-model="member.passwordConfirm"
                  show-password
                ></el-input>
                <label v-show="!pageInfo.equalsToPassword" style="color:red"
                  >비밀번호를 확인해주세요.</label
                >
              </el-form-item>
              <span class="error">{{ error.password }}</span>
            </p>

            <p :class="ux.isAuthEmail" style="text-align:right">
              <span v-if="isIng.email" class="w3-right w3-text-blue"
                >Sending</span
              >
              <el-button
                size="mini"
                v-if="!isIng.email"
                round
                :class="{
                  primary: !pageInfo.isRequestEmail,
                  warning: pageInfo.isRequestEmail,
                }"
                @click="requestEmail"
              >
                {{ ux.sendEmailText[pageInfo.isRequestEmail ? 1 : 0] }}
              </el-button>
              <el-form-item
                prop="email"
                label="Email"
                :rules="[
                  {
                    required: true,
                    message: 'Please input email address',
                    trigger: 'blur',
                  },
                  {
                    type: 'email',
                    message: '이메일 형식이 아닙니다',
                    trigger: ['blur', 'change'],
                  },
                ]"
              >
                <el-input
                  v-model="member.email"
                  type="email"
                  name="email"
                  :disabled="pageInfo.isAuthEmail"
                ></el-input>
                <span class="error">{{ error.email }}</span>
              </el-form-item>
              <template v-if="pageInfo.isRequestEmail">
                <span
                  class="w3-right w3-button w3-white w3-border w3-round-large"
                  style="font-family:jjl; "
                  @click="emailAutho"
                  :class="{
                    'w3-border-red': !pageInfo.isAuthEmail,
                    'w3-border-blue': pageInfo.isAuthEmail,
                  }"
                  >{{ ux.authEmailText[pageInfo.isAuthEmail ? 1 : 0] }}</span
                >
                <el-input
                  class="w3-input w3-border w3-hover-indigo"
                  name="email"
                  v-model="member.code"
                  :disabled="pageInfo.isAuthEmail"
                  placeholder="Code"
                />
                <span class="error">{{ error.code }}</span>
              </template>
            </p>

            <el-form-item label="이름" required>
              <el-input
                placeholder="Please input name"
                v-model="member.name"
                name="name"
              ></el-input>
            </el-form-item>
            <el-form-item label="핸드폰" required>
              <el-input
                placeholder="-없이 입력해주세요"
                v-model="member.phone"
                name="phone"
              ></el-input>
            </el-form-item>

            <p>
              <el-form-item label="주소" required>
                <span
                  @click="pageInfo.showFindAddress = !pageInfo.showFindAddress"
                  >Find</span
                >
                <el-input
                  name="address"
                  type="text"
                  v-model="member.addressObj.address"
                  v-show="!pageInfo.showFindAddress"
                  @click="pageInfo.showFindAddress = !pageInfo.showFindAddress"
                />
                <br />
                <el-input
                  class="w3-input w3-border w3-hover-indigo"
                  name="detailAddress"
                  type="text"
                  v-model="member.detailAddress"
                  v-show="!pageInfo.showFindAddress"
                  placeholder="Detail Address"
                />
                <VueDaumPostcode
                  v-if="pageInfo.showFindAddress"
                  @complete="
                    (member.addressObj = $event),
                      (pageInfo.showFindAddress = !pageInfo.showFindAddress)
                  "
                  style="height: 450px; overflow: scroll;"
                ></VueDaumPostcode>
              </el-form-item>
            </p>

            <el-form
              label-position="top"
              inline="true"
              class="demo-form-inline"
            >
              <el-form-item label="성별" required>
                <el-select
                  v-model="member.gender"
                  name="gender"
                  placeholder="please select your zone"
                >
                  <el-option label="남자" value="0"></el-option>
                  <el-option label="여자" value="1"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="Age" required>
                <el-input
                  v-model="member.age"
                  name="age"
                  placeholder="나이를 입력하시오"
                ></el-input>
              </el-form-item>
            </el-form>
          </el-form>
        </el-col>
      </el-row>
      <!--------------------------------- 회원가입 폼--------------------------------------->

      <!--------------------------------- 회원가입 완료--------------------------------------->
      <el-form v-if="active === 2">
        <div>가입을 축하합니다</div>
      </el-form>
      <!--------------------------------- 회원가입 완료--------------------------------------->

      <!--------------------------------- 버튼--------------------------------------->
      <div style="text-align:center">
        <el-button v-if="active === 0" style="margin-top: 12px;" @click="next"
          >Next step</el-button
        >
        <el-button v-if="active === 1" style="margin-top: 12px;" @click="next"
          >Submit</el-button
        ><el-button v-if="active === 2" style="margin-top: 12px;" @click="go"
          >OK</el-button
        >
      </div>
      <!--------------------------------- 버튼--------------------------------------->
    </div>
  </div>
</template>

<script>
import { VueDaumPostcode } from "vue-daum-postcode";
export default {
  components: {
    VueDaumPostcode,
  },
  data: () => {
    return {
      imageData: "",
      dialogImageUrl: "",
      dialogVisible: false,
      ux: {
        isOverlapId: {},
        overlapText: ["Overlap", "Okay!!"],
        sendEmailText: ["Request", "Okay!!"],
        authEmailText: ["Auth", "Okay!!"],
        isAuthEmail: {},
        name: {},
        password: {},
      },
      error: {
        id: "",
        code: "",
        email: "",
        name: "",
        password: "",
      },
      pageInfo: {
        isOverlapId: false,
        isAuthEmail: false,
        equalsToPassword: false,
        showFindAddress: false,
        isRequestEmail: false,
      },
      active: 0,
      member: {
        image: "",
        id: "",
        name: "",
        password: "",
        passwordConfirm: "",
        gender: "",
        email: "",
        age: "",
        addressObj: "",
        detailAddress: "",
        address: "",
        phone: "",
      },
      passwordConfirm: "",
      isIng: {
        id: false,
        email: false,
      },
    };
  },
  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
    joinsuc() {
      console.log(document.getElementById("image"));
      console.log(document.getElementById("id"));
      console.log(document.getElementById("password"));
      console.log(document.getElementById("name"));
      console.log(document.getElementById("address"));
      console.log(document.getElementById("gender"));
      console.log(document.getElementById("age"));

      let formData = new FormData(document.getElementById("formData"));
      console.log("formData");
      console.log(formData);
      alert("join ok!");
      console.log(this.member);
      console.log(this.imageData);
      this.$router.push("/");
    },
    go() {
      this.$router.push("/");
    },
    idOverlap() {
      alert("중복");
    },
    andleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    onUploadChange(file) {
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = function(e) {
        console.log(this.result); //base64 data for pictures
        this.imageData = this.result;
      };
    },
  },
  watch: {
    "member.passwordConfirm"() {
      this.pageInfo.equalsToPassword =
        this.member.password === this.member.passwordConfirm ? true : false;
    },
  },
};
</script>

<style>
.image-preview {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  padding: 20px;
}
img.preview {
  width: 30%;
  background-color: white;
  border: 1px solid #ddd;
  padding: 5px;
}
#memberjoin {
  @media screen and (width>960px;) {
    max-width: 50%;
  }
  max-width: 100%;
  padding: 2%;
  background: #f5f7fa;
}
.demo-autocomplete {
  margin-left: auto;
  margin-right: auto;
}
</style>
