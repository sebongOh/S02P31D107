<template>
    <div>
      <div class="app-container" v-if="haveAcademy">
        <h1>학원관리 페이지</h1>
        <el-select v-model="value" placeholder="관리할 학원 선택">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"></el-option>
        </el-select>
        <!-- 학원 추가용 + 버튼 -->
        <button class="add-btn" @click="addAcademy()">+</button>
        <AcademyAdd v-if="isAdd & value==''"/>
        <!-- 학원이 선택되어 있다면 해당 학원 안에 수정/삭제 기능 -->
        <AcademyModify v-if="value!=''" :academyId="value"/>
      </div>
      <div v-if="!haveAcademy" class="no-have-academy">
        현재 권한을 가지고 계시는 학원이 없습니다.<br>
        권한 요청 승인을 기다려주세요!<br>
        문의 : learnacademy02@gmail.com
      </div>
    </div>
</template>

<script>
import AcademyModify from "@/views/academyManagement/components/AcademyModify";
import AcademyAdd from "@/views/academyManagement/components/AcademyAdd";
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

export default {
  components: { AcademyModify, AcademyAdd},
  computed: {
    ...mapGetters(["email"]),
  },
  mounted(){
    this.getAcademy();
  },
  data() {
    return {
      haveAcademy:true,
      ///academy-management-authority/member 해서 받아온 값들 중 academyId 를 value 에 들어가도록 하고 name 을 label 에 들어가도록 한다
      options: [
        {
          value: "9472674",
          label: "홀릭미술학원 본원",
        },
        {
          value: "10139665",
          label: "구미제과제빵학원",
        },
        {
          value: "9749818",
          label: "르네상스미술학원",
        },
        {
          value: "9318985",
          label: "이레실용음악학원",
        },
        {
          value: "17710983",
          label: "구미미용학원",
        },
      ],
      value: "",
      isAdd: false,
    };
  },
  methods: {
    addAcademy() {
      this.isAdd = true;
      this.value = "";
    },
  getAcademy() {
    this.$store
        .dispatch("student/memberAcademy")
        .then((res) => {
            if (res.status == 200) {
              if(res.data == ""){
                this.haveAcademy = false;
              }
                console.log(res.data);
                this.options = [];
                for(var data in res.data){
                  this.options.append({value:data.academyId, label:data.name});
                }
            }else{
              this.haveAcademy = false;
            }
        })
        .catch(() => {
            this.haveAcademy = false;
            console.log("에러 catch");
        });
    }
  },
};
</script>

<style>
.app-container{
  padding: 2%;
}
.add-btn{
  margin: 10px;
  width: 90px;
  height: 35px;
  font-size: 25px;
  font-weight: 100;
  color: #909399;
  background: white;
  border: 1px solid #dcdfe6;
  border-radius: 3px;
}
.add-btn:active {
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.42);
}
.no-have-academy{
  width: 100%;
  text-align: center;
  margin-top: 50px;
}
</style>
