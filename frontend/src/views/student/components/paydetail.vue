<template>
  <el-card style="border:1px solid grey; margin-top:10%">
    <div slot="header" style="text-align:center;">
      <span style="font-size:20px">
        <b>결제 상세정보</b>
      </span>
    </div>
    <el-row style="margin-bottom:10%">
      <el-col :span="24" style="padding:10px">
        <div class="text-muted" style="float:left; font-size:20px">
          <b>결제금액</b>
        </div>

        <div v-if="payli.type==0" style="float:right; font-size:30px">
          <b>{{payli.price}}원</b>
        </div>
        <div
          v-if="payli.type==1"
          style="float:right; font-size:30px; text-decoration:line-through;"
        >
          <b>{{payli.price}}원</b>
        </div>
      </el-col>
    </el-row>

    <div class="user-bio">
      <hr />
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />결제번호
          </div>
          <div style="float:right">{{payli.tid}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />결제일시
          </div>
          <div style="float:right">{{payli.approvedAt}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-home" />학원
          </div>
          <div style="float:right">{{payli.academySchedule.academy.name}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />수강과목
          </div>
          <div style="float:right">{{payli.itemName}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />구매자
          </div>
          <div style="float:right">{{payli.member.name}}</div>
        </div>
        <div class="user-bio-section-header">
          <div style="float:left">
            <i class="el-icon-s-order" />결제상태
          </div>
          <div v-if="payli.type==0" style="float:right; color:green;">결제완료</div>
          <div v-if="payli.type==1" style="float:right; color:red;">결제취소</div>
        </div>
      </div>
    </div>
    <hr />
    <el-row>
      <el-col :span="6">
        <el-button type="warning" round style="width:100%;" @click="back()" icon="el-icon-back
"></el-button>
      </el-col>
      <el-col :span="18">
        <el-button
          v-if="payli.type==0"
          type="danger"
          round
          style="width:100%;"
          @click="paydelete()"
        >결제취소</el-button>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  props: ["payli"],
  data() {
    return {};
  },
  mounted() {
    console.log(this.payli.type);
  },
  methods: {
    back() {
      this.$emit("changePagenum", 0);
    },
    paydelete() {
      this.$store
        .dispatch("student/paydelete", {
          tid: this.payli.tid,
          cancel_amount: this.payli.price
        })
        .then(res => {
          this.$emit("changePagenum", 0);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style lang="scss" scoped >
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
