<template>
<div class="app-container">
    <el-row class="header-footer-div">
        <Header />
    </el-row>
    <el-select v-model="academyId" placeholder="관리할 학원 선택" class="select-div">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    <el-select v-model="type" placeholder="게시판 종류 선택" class="select-div">
        <el-option :label="'공지사항'" :value="'notice'"></el-option>
        <el-option :label="'자료게시판'" :value="'reference'"></el-option>
    </el-select>
  <el-form class="write-div">
    <el-form-item label="제목">
      <el-input v-model="title" />
    </el-form-item>
    <el-form-item label="내용">
      <el-input type="textarea" v-model="contents" :rows="7" />
    </el-form-item>
    <el-form-item label="자료">
      <input type="file" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addBoard()">글쓰기</el-button>
    </el-form-item>
  </el-form>
  <div v-if="!haveAcademy" class="no-have-academy">
      현재 권한을 가지고 계시는 학원이 없습니다.
      <br />권한 요청 승인을 기다려주세요!
      <br />문의 : learnacademy02@gmail.com
  </div>
  <br />
  <br />
    <el-row>
      <el-col :span="24">
        <el-button type="warning" style="width:100%" @click="logout()">
          <b>로그아웃</b>
        </el-button>
      </el-col>
    </el-row>
</div>
</template>

<script>
import Header from "@/views/student/components/Header";
import { removeToken } from "@/utils/auth";

export default {
  components: {
    Header
  },
  data() {
    return {
      academyId: '',
      title: '',
      contents: '',
      type: 'notice',
      options: [],
      haveAcademy: true
    };
  },
  mounted(){
      this.getAcademy();
  },
  methods: {
      getAcademy() {
      this.$store
        .dispatch("student/memberAcademy")
        .then(res => {
          if (res.status == 200) {
            if (res.data == "") {
              this.haveAcademy = false;
            }
            this.options = [];
            for (var data of res.data) {
              this.options.push({ value: data.academyId, label: data.name });
            }
          } else {
            this.haveAcademy = false;
          }
        })
        .catch(() => {
          this.haveAcademy = false;
          console.log("에러 catch");
        });
    },
    addBoard(){
        var url = 'student/addNotice';
        if(this.type == 'reference'){
            url = 'student/addReference';
        }
        this.$store
          .dispatch(url, {
              academy:{academyId:this.academyId},
              title:this.title,
              contents:this.contents
          })
          .then((res) => {
            if (res.status == 200) {
              console.log("등록 성공");
              this.title = '';
              this.contents = '';
              this.type = 'notice';
            } else {
              console.log("게시글 등록에 실패했습니다.");
            }
          })
          .catch(() => {
            console.log("게시글 등록에 실패했습니다. catch");
          });
    },
    logout() {
      removeToken();
      var router = this.$router;
      router.push("/");
    }
  },
};
</script>

<style>
.app-container {
  font-family: "Yeon Sung", cursive;
}
.header-footer-div{
    width: 100%;
    height: 60px;
}
.select-div{
    margin-top: 10px;
    margin-top: 5%;
    margin-left: 5%;
}
.write-div{
    width:90%;
    height:auto;
    margin-top: 10px;
    margin-left: 5%;
    margin-right: 5%;
}
.no-have-academy {
  width: 100%;
  text-align: center;
  margin-top: 50px;
}
</style>
