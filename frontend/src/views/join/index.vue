<template>
  <div class="app-container">
    <el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
      <el-step title="Step 1"></el-step>
      <el-step title="Step 2"></el-step>
      <el-step title="Step 3"></el-step>
    </el-steps>
    <br />
    <div>
      <!--------------------------------- 회원선택 폼--------------------------------------->
      <el-row v-if="active === 0" style="text-align:center" id="memberselect">
        <el-form>
          <el-col :xs="8" :sm="8" :md="8">
            <el-button @click="normaluser = true">
              <el-avatar shape="square" :size="80" icon="el-icon-s-custom"></el-avatar>
              <br />
              <span>일반회원</span>
            </el-button>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8">
            <el-avatar
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
              :size="100"
            ></el-avatar>
          </el-col>
          <el-col :xs="8" :sm="8" :md="8">
            <el-button @click="normaluser = false">
              <el-avatar shape="square" :size="80" icon="el-icon-school"></el-avatar>
              <br />
              <span>학원회원</span>
            </el-button>
          </el-col>
        </el-form>
      </el-row>
      <!--------------------------------- 회원선택 폼--------------------------------------->

      <!--------------------------------- 회원가입 폼--------------------------------------->
      <el-row class="demo-autocomplete" v-if="active === 1" id="memberjoin">
        <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
          <el-form
            id="form"
            enctype="multipart/form-data"
            ref="member"
            :model="member"
            label-width="120px"
            label-position="left"
          >
            <el-form-item label="프로필이미지" required>
              <!-- <el-upload
                type="file"
                action="https://jsonplaceholder.typicode.com/posts/"
                list-type="picture-card"
                accept="image/jpeg, image/png"
                :on-preview="handlePictureCardPreview"
                :on-change="onUploadChange"
                :on-remove="handleRemove"
                name="profileFile"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt />
              </el-dialog>-->
              <input type="file" @change="previewImage" accept="image/*" name="profileFile" />
              <div class="image-preview" v-if="imageData.length > 0">
                <img class="preview" :src="imageData" />
              </div>
            </el-form-item>
            <p :class="ux.isAuthEmail" style="text-align:right">
              <span v-if="isIng.email" style="color:blue">Sending</span>
              <el-button
                size="mini"
                v-if="!isIng.email"
                round
                :class="{
                  primary: !pageInfo.isRequestEmail,
                  warning: pageInfo.isRequestEmail,
                }"
                @click="requestEmail"
              >{{ ux.sendEmailText[pageInfo.isRequestEmail ? 1 : 0] }}</el-button>
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
                  @click="emailCheck"
                  :class="{
                    'w3-border-red': !pageInfo.isAuthEmail,
                    'w3-border-blue': pageInfo.isAuthEmail,
                  }"
                >{{ ux.authEmailText[pageInfo.isAuthEmail ? 1 : 0] }}</span>
                <el-input
                  class="w3-input w3-border w3-hover-indigo"
                  v-model="member.code"
                  :disabled="pageInfo.isAuthEmail"
                  placeholder="Code"
                />
                <span class="error">{{ error.code }}</span>
              </template>
            </p>
            <p :class="ux.password">
              <el-form-item label="비밀번호" required>
                <el-input
                  placeholder="영문숫자포함8자이상 입력해주세요"
                  v-model="member.password"
                  name="password"
                  v-bind:class="{
                    error: error.checkPassword,
                    complete:
                      !error.checkPassword && member.password.length !== 0,
                  }"
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
                <label v-show="!pageInfo.equalsToPassword" style="color:red">비밀번호를 확인해주세요.</label>
              </el-form-item>
              <span class="error">{{ error.password }}</span>
            </p>

            <el-form-item label="이름" required>
              <el-input placeholder="Please input name" v-model="member.name" name="name"></el-input>
            </el-form-item>
            <el-form-item label="핸드폰" required>
              <el-input placeholder="-없이 입력해주세요" v-model="member.phone" name="phone"></el-input>
            </el-form-item>

            <p>
              <el-form-item label="주소" required>
                <span @click="pageInfo.showFindAddress = !pageInfo.showFindAddress">Find</span>
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

            <el-form-item label="성별" required>
              <el-select
                v-model="member.gender"
                name="gender"
                placeholder="please select your zone"
              >
                <el-option value="0">남자</el-option>
                <el-option value="1">여자</el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="나이" required>
              <el-input v-model="member.age" name="age" placeholder="나이를 입력하시오"></el-input>
            </el-form-item>
            <el-form-item label="학원이름" required v-if="!normaluser">
              <el-input class="inline-input" v-model="keyword" placeholder="학원 명을 입력해 주세요"></el-input>
              <el-button @click="searchPlaces">학원 검색</el-button>
            </el-form-item>
            <el-table
              :data="searchResult"
              stripe
              style="width: 100%"
              max-height="300"
              @row-click="rowClicked"
              v-if="searchResult.length > 0"
            >
              <el-table-column prop="place_name" label="상호명" width="100"></el-table-column>
              <el-table-column prop="road_address_name" label="주소"></el-table-column>
            </el-table>
            <el-form-item label="PortFolio" required v-if="!normaluser">
              <input name="imageFile" type="file" ref="file" />
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
      <!--------------------------------- 회원가입 폼--------------------------------------->

      <!--------------------------------- 회원가입 완료--------------------------------------->
      <el-form v-if="active === 2">
        <div>{{ this.member.name }}님 가입을 축하합니다</div>
      </el-form>
      <!--------------------------------- 회원가입 완료--------------------------------------->

      <!--------------------------------- 버튼--------------------------------------->
      <div style="text-align:center">
        <el-button v-if="active === 0" style="margin-top: 12px;" @click="next">Next step</el-button>
        <el-button v-if="active === 1" style="margin-top: 12px;" @click="join">Submit</el-button>
        <el-button v-if="active === 2" style="margin-top: 12px;" @click="go">OK</el-button>
      </div>
      <!--------------------------------- 버튼--------------------------------------->
    </div>
  </div>
</template>

<script>
import { VueDaumPostcode } from "vue-daum-postcode";
import PV from "password-validator";

let ps;

export default {
  components: {
    VueDaumPostcode
  },
  data: () => {
    return {
      normaluser: true,
      imageData: "",
      dialogImageUrl: "",
      dialogVisible: false,
      ux: {
        sendEmailText: ["이메일인증", "요청완료"],
        authEmailText: ["인증번호확인", "인증완료"],
        isAuthEmail: {},
        name: {},
        password: {}
      },
      error: {
        code: "",
        email: "",
        name: "",
        password: "",
        checkPassword: false
      },
      pageInfo: {
        isOverlapId: false,
        isAuthEmail: false,
        equalsToPassword: false,
        showFindAddress: false,
        isRequestEmail: false
      },
      active: 0,
      member: {
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
        portfolio: ""
      },
      academy: [{}],
      academyId: "",
      academyName: "",
      academyAddress: "",
      academyPhone: "",
      academyCategory: "",
      passwordSchema: new PV(),
      passwordConfirm: "",
      isIng: {
        email: false
      },
      keyword: "",
      searchResult: []
    };
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
    // 장소 검색 객체를 생성합니다
    ps = new kakao.maps.services.Places();

    const script = document.createElement("script");
    /* global kakao */
    script.src =
      "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=be28ab6abd772e2ef55ac5c2f1aa7792";
    document.head.appendChild(script);
  },
  methods: {
    rowClicked(row) {
      this.academyName = row.place_name;
      this.academyAddress = row.road_address_name;
      this.academyPhone = row.phone;
      this.keyword = row.place_name;
      this.academyCategory = row.category_name;
      this.academyId = row.id;
      this.searchResult = [];
    },
    noSearchResult() {
      this.$message({
        message: "검색 결과가 존재하지 않습니다.",
        type: "warning"
      });
    },
    searchError() {
      this.$message.error("검색 결과 중 오류가 발생했습니다.");
    },
    searchPlaces() {
      let keyword = this.keyword;
      // console.log(keyword);
      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return false;
      }
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      ps.keywordSearch(keyword, this.placesSearchCB);
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // console.log(data);
        this.searchResult = data;
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        this.noSearchResult();
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        this.searchError();
        return;
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    next() {
      if (this.active++ > 2) this.active = 0;
    },
    join() {
      this.member.address =
        this.member.addressObj.address + this.member.detailAddress;
      let formData = new FormData(document.getElementById("form"));
      formData.delete("address");
      formData.delete("detailAddress");
      formData.append("email", this.member.email);
      formData.append("address", this.member.address);
      if (this.normaluser) {
        this.$store
          .dispatch("student/join", formData)
          .then(res => {
            console.log(res.data);
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        formData.append("academyId", this.academyId);
        formData.append("academyName", this.academyName);
        formData.append("academyAddress", this.academyAddress);
        formData.append("academyPhone", this.academyPhone);
        formData.append("academyCategory", this.academyCategory);
        this.$store
          .dispatch("student/acajoin", formData)
          .then(res => {
            console.log(res.data);
          })
          .catch(err => {
            console.log(err);
          });
      }
      alert("join ok!");
      for (var key of formData.keys()) {
        console.log(key);
      }
      for (var value of formData.values()) {
        console.log(value);
      }
      this.next();
    },
    go() {
      this.$router.push("/");
    },
    aniVibro(sbj, text = "필수 항목을 채워주세요.") {
      if (sbj == "code") {
        this.ux.isAuthEmail = { "animation vibro": true };
        setTimeout(() => {
          this.ux.isAuthEmail = { "animation vibro": false };
        }, 1000);
        this.error.code = text;
      } else if (sbj == "email") {
        this.ux.isAuthEmail = { "animation vibro": true };
        setTimeout(() => {
          this.ux.isAuthEmail = { "animation vibro": false };
        }, 1000);
        this.error.email = text;
      } else if (sbj == "name") {
        this.ux.name = { "animation vibro": true };
        setTimeout(() => {
          this.ux.name = { "animation vibro": false };
        }, 1000);
        this.error.name = text;
      } else if (sbj == "password") {
        this.ux.password = { "animation vibro": true };
        setTimeout(() => {
          this.ux.password = { "animation vibro": false };
        }, 1000);
        this.error.password = text;
      }
    },
    requestEmail() {
      if (this.member.email.trim().length > 0) {
        this.isIng.email = true;
        this.$store
          .dispatch("student/requestEmail", { email: this.member.email })
          .then(res => {
            if (res.status == 200) {
              alert("이메일에 인증코드를 전송했습니다.");
              this.pageInfo.isRequestEmail = true;
              this.isIng.email = false;
            } else if (res.status == 409) {
              this.aniVibro("email", "중복된 이메일입니다.");
            } else {
              this.aniVibro("email", "이메일 전송에 실패했습니다.");
              this.pageInfo.isRequestEmail = false;
              this.isIng.email = false;
            }
          })
          .catch(err => {
            this.aniVibro("email", "서버 접속을 실패했습니다.");
            this.pageInfo.isRequestEmail = false;
            this.isIng.email = false;
            console.log(err);
          });
      } else {
        this.aniVibro("email", "이메일을 입력해주세요.");
      }
    },
    emailCheck() {
      if (this.member.code.trim().length > 0) {
        this.isIng.email = true;
        this.$store
          .dispatch("student/emailCheck", {
            email: this.member.email,
            code: this.member.code
          })
          .then(res => {
            if (res.status == 404) {
              this.aniVibro("code", "이메일 인증에 실패하였습니다.");
            } else if (res.status == 200) {
              alert("이메일 인증 성공했습니다.");
              this.pageInfo.isAuthEmail = true;
            }
          })
          .catch(() => {
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
      } else {
        this.aniVibro("code", "코드를 입력해주세요.");
      }
      this.isIng.email = false;
    },
    idOverlap() {
      console.log(this.member.id);
      if (this.member.id.trim().length > 0) {
        this.isIng.id = true;
        this.$store
          .dispatch("student/idOverlap", this.member.id)
          .then(res => {
            if (res.data == null || res.data == "") {
              alert("중복 체크 완료!!");
              this.pageInfo.isOverlapId = true;
            } else {
              this.aniVibro("id", "중복된 ID입니다.");
            }
          })
          .catch(err => {
            console.log(err);
            this.aniVibro("id", "서버 접속을 실패했습니다.");
          });
      } else {
        this.aniVibro("id", "ID를 입력해주세요.");
      }
      this.isIng.id = false;
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
        this.imageData = this.result;
      };
    },
    checkForm() {
      if (
        this.member.password.length >= 0 &&
        !this.passwordSchema.validate(this.member.password)
      )
        this.error.checkPassword = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.checkPassword = false;
    },
    querySearch(queryString, cb) {
      var links = this.links;
      var results = queryString
        ? links.filter(this.createFilter(queryString))
        : links;
      // call callback function to return suggestions
      cb(results);
    },
    createFilter(queryString) {
      return link => {
        return (
          link.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    handleSelect(item) {
      console.log(item);
    },
    previewImage: function(event) {
      // Reference to the DOM input element
      var input = event.target;
      // Ensure that you have a file before attempting to read it
      if (input.files && input.files[0]) {
        // create a new FileReader to read this image and convert to base64 format
        var reader = new FileReader();
        // Define a callback function to run, when FileReader finishes its job
        reader.onload = e => {
          // Note: arrow function used here, so that "this.imageData" refers to the imageData of Vue component
          // Read image as base64 and set to imageData
          this.imageData = e.target.result;
        };
        // Start the reader job - read file as a data url (base64 format)
        reader.readAsDataURL(input.files[0]);
      }
    }
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    "member.passwordConfirm"() {
      this.pageInfo.equalsToPassword =
        this.member.password === this.member.passwordConfirm ? true : false;
    }
  }
};
</script>

<style>
.app-container {
  font-family: "Yeon Sung", cursive;
}
.image-preview {
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

#memberselect {
  padding: 2%;
  background: #f5f7fa;
}
.demo-autocomplete {
  margin-left: auto;
  margin-right: auto;
}
.error {
  color: red;
  font-size: 0.85em;
}
</style>
