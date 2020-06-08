<template>
  <div class="more--container">
    <div class="title">
      <div class="title--title">
        <span>내 정보</span>
      </div>
      <router-link to="/student-profile">
        <div class="update--profile">수정</div>
      </router-link>
    </div>
    <div class="my--profile">
      <div class="profile--img">
        <img :src="user.profileUrl" alt />
      </div>
      <div class="profile--data">
        <div class="name">{{user.name}}</div>
        <div class="email">{{user.email}}</div>
      </div>
      <div class="logout">
        <button @click="logout()">로그아웃</button>
      </div>
    </div>
    <div class="title">
      <span>내 결제 정보</span>
    </div>
    <div class="pay--history">
      <paylist v-if="pagenum==0" @changePagenum="changenum" @datareturn="paylist" />
      <paydetail v-if="pagenum==1" @changePagenum="changenum" :payli="payli" />
      <!-- <div class="no-pay-history" v-if="payHistory.length==0">
        <span>결제 내역이 없습니다.</span>
      </div>-->
    </div>
  </div>
</template>

<script>
import { removeToken } from "@/utils/auth";
import paylist from "./paylist";
import paydetail from "./paydetail";

export default {
  components: { paylist, paydetail },
  data() {
    return {
      user: {
        name: "",
        address: "",
        profileUrl: ""
      },
      payli: {},
      pagenum: 0,
      payHistory: ""
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
            this.user.name = res.data.name;
            this.user.email = res.data.email;
            this.user.profileUrl = res.data.profileUrl;
          }
        })
        .catch(() => {
          console.log("aniVibro가 뭐죠 catch");
        });
    },
    paylist(paylist) {
      this.payli = paylist;
    },
    changenum(num) {
      this.pagenum = num;
    },
    logout() {
      removeToken();
      this.$router.push("/");
    }
  }
};
</script>

<style lang='scss'>
.more--container {
  padding: 10px;
  .title {
    display: flex;
    justify-content: space-between;
    align-items: center;
    span {
      font-size: 23px;
    }
    a {
      text-decoration: none;
      color: #403a33;
    }
    .update--profile {
      margin-right: 10px;
    }
  }
  .my--profile {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 20px;
    height: 70px;
    background-color: #f2e6ce;
    .profile--img {
      img {
        width: 50px;
      }
    }
    .logout {
      button {
        font-family: "Yeon Sung", cursive;
        width: 80px;
        height: 40px;
        background-color: #403a33;
        color: white;
        border-radius: 5px;
      }
    }
  }
  .pay--history {
    padding: 10px;
    padding-top: 20px;
    background-color: #f2e6ce;
    border-radius: 10px;
    min-height: 330px;
    .no-pay-history {
      text-align: center;
      line-height: 330px;
    }
  }
}
</style>