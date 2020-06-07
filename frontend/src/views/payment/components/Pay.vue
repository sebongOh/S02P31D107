<template>
  <el-card style="border:1px solid grey; margin-top:10%">
    <div slot="header" style="text-align:center;">
      <span style="font-size:20px">
        <b>주문 /결제</b>
      </span>
    </div>
    <el-row style="margin-bottom:10%">
      <el-col :span="24" style="padding:10px">
        <div class="text-muted" style="float:left; font-size:20px">
          <b>결제금액</b>
        </div>
        <div style="float:right; font-size:30px">
          <b>{{price}}원</b>
        </div>
      </el-col>
    </el-row>
    <div class="user-profile" style="background-color:#FDDF1B">
      <div class="box-center">
        <span style="font-size:25px">
          <b>카카오머니</b>
        </span>
        <div style="padding-top:20%">
          <div style="float:left">현재잔액</div>
          <div style="float:right">0원</div>
        </div>
        <div>
          <div style="float:left">자동충전</div>
          <div style="float:right">+{{price}}원</div>
        </div>
      </div>
    </div>

    <div class="user-bio">
      <hr />
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-home" />학원
          </div>
          <div style="float:right">{{academyName}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />수강과목
          </div>
          <div style="float:right">{{scheduleName}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />기간
          </div>
          <div style="float:right">1개월</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />구매자
          </div>
          <div style="float:right">{{memberName}}</div>
        </div>
      </div>
    </div>
    <hr />
    <el-row>
      <el-col :span="24">
        <el-button type="warning" style="width:100%" @click="pay">
          <b>결제하기</b>
        </el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
import { Store } from "vuex";

export default {
  props: ["academyName", "scheduleId", "scheduleName", "price"],
  data: () => {
    return {
      memberId: "",
      memberName: "",
      token: "",
      month: 1
    };
  },
  mounted() {
    this.getMemberData();
  },
  methods: {
    getMemberData() {
      this.$store
        .dispatch("student/memberInfo")
        .then(res => {
          if (res.status == 404) {
            console.log("aniVibro가 뭐죠 404");
          } else if (res.status == 200) {
            this.memberId = res.data.memberId;
            this.memberName = res.data.name;
          }
        })
        .catch(() => {});
    },
    pay() {
      this.$store
        .dispatch("student/pay", {
          scheduleId: this.scheduleId
        })
        .then(res => {
          console.log(res);
          window.location.href = res.data;
          this.$emit("changePagenum", 2);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.el-icon-chat-round {
  font-size: 20px;
}
.box-center {
  display: table;
  padding: 10%;
  margin: 0 auto;
}

.text-muted {
  color: #777;
}

.user-profile {
  border: 1px solid;
  border-radius: 6px;
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 15px;
    padding: 15px 0;

    .user-bio-section-header {
      padding-bottom: 8%;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
