<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix"></div>

    <div class="user-profile">
      <div class="box-center">
        학원 이미지
        <!-- <pan-thumb
          :image="user.avatar"
          :height="'100px'"
          :width="'100px'"
          :hoverable="false"
        >
          <div>Hello</div>
          {{ user.role }}
        </pan-thumb> -->
      </div>
      <div class="box-center">
        <div class="user-name text-center">asdasdasdasdasd</div>
      </div>
    </div>

    <div class="user-bio">
      <el-card>
        <div class="user-education user-bio-section">
          <div class="user-bio-section-body">
            <div class="text-muted">
              <h1>싸피학원</h1>
              <h4>#컴퓨터 #코딩</h4>
              <h5>서울특별시 강남구 언주로 508</h5>
            </div>
          </div>
        </div>

        <div class="user-skills user-bio-section">
          <div class="user-bio-section-header">
            <i class="el-icon-s-order" /><span>Skills</span>
          </div>
          <div class="user-bio-section-header">
            <div style="float:left"><i class="el-icon-s-home" />수강과목</div>
            <div style="float:right">컴퓨터</div>
          </div>
          <div class="user-bio-section-header">
            <div style="float:left"><i class="el-icon-s-home" />수강날짜</div>
            <div style="float:right">월 수</div>
          </div>
        </div>
      </el-card>
    </div>
  </el-card>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: "",
          email: "",
          avatar: "",
          role: "",
        };
      },
    },
  },
};
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  text-align: center;
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 5px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .el-table {
    border-top: 1px solid #dfe6ec;
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
    font-size: 14px;
    padding: 5px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
