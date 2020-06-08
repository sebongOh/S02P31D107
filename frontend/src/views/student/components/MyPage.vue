<template>
  <div class="my-page">
    <div class="title">
      <span>내 학원 가기</span>
    </div>
    <div class="my-academy">
      <div class="no-academy" v-if="myAcademy.length==0">
        <span>등록된 학원이 없습니다.</span>
      </div>
      <div class="academy" v-for="(academy, idx) in myAcademy" :key="idx">{{idx+1}}. {{academy}}</div>
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
      myAcademy: "",
      newNotice: ""
    };
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