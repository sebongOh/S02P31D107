<template>
  <div class="my-page">
    <div class="title">
      <span>내 학원 가기</span>
    </div>
    <div class="my-academy">
      <div class="no-academy" v-if="academys.length==0">
        <span>등록된 학원이 없습니다.</span>
      </div>
      <div class="my-academy-list" v-if="academys.length>0">
        <div
          class="academy"
          v-for="(academy, idx) in academys"
          :key="idx"
          @click="goBoard(academy)"
        >
          <div class="name">{{idx+1}}. {{academy.name}}</div>
          <div class="arrow">></div>
        </div>
      </div>
    </div>
    <div class="title">
      <span>새로운 공지사항</span>
    </div>
    <div class="new-notice">
      <div class="no-notice" v-if="newNotice.length==0">
        <span>새로 등록된 공지사항이 없습니다.</span>
      </div>
      <div class="notice" v-for="(notice, idx) in newNotice" :key="idx">{{idx+1}}. {{notice}}</div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      academys: [],
      newNotice: ""
    };
  },
  mounted() {
    this.getAcademy();
    console.log(this.academys);
  },
  methods: {
    getAcademy() {
      this.$store
        .dispatch("student/getMemberAcademy")
        .then(res => {
          if (res.status == 200) {
            for (var data of res.data) {
              this.academys.push({
                academyId: data.academyId,
                name: data.name,
                address: data.address,
                category: data.category,
                phone: data.phone,
                contents: data.contents,
                imgUrl: data.imageUrl
              });
            }
          } else {
            console.log("수강 학원을 가져오는데 문제가 생겼습니다.");
          }
        })
        .catch(() => {
          console.log("수강 학원을 가져오는데 문제가 생겼습니다. catch");
        });
    },
    goBoard(academy) {
      this.$router.push({
        name: "academyMain",
        params: {
          academyId: academy.academyId,
          name: academy.name,
          address: academy.address,
          category: academy.category,
          phone: academy.phone,
          contents: academy.contents,
          imgUrl: academy.imgUrl
        }
      });
    }
  }
};
</script>

<style lang='scss'>
.my-page {
  padding: 10px;

  .title span {
    font-size: 23px;
  }
  .my-academy {
    padding: 10px;
    min-height: 200px;
    max-height: 300px;
    // background-color: #d9caad;
    background-color: #f2e6ce;
    margin-bottom: 20px;
    border-radius: 10px;
    .no-academy {
      text-align: center;
      line-height: 200px;
    }
    .academy {
      margin-bottom: 10px;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
    }
  }
  .new-notice {
    padding: 10px;
    min-height: 200px;
    max-height: 300px;
    background-color: #f2e6ce;
    border-radius: 10px;
    .no-notice {
      text-align: center;
      line-height: 200px;
    }
  }
}
</style>