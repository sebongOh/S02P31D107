<template>
  <div>
    <table v-if="!end" class="course-table">
      <tr>
        <td>스케줄명</td>
        <td><input class="input1" type="text" v-model="name" /></td>
      </tr>
      <tr>
        <td>가격</td>
        <td><input class="input1" type="number" v-model="price" /></td>
      </tr>
      <tr>
        <td colspan="2"><button @click="pushSchedule()">스케줄 등록</button></td>
      </tr>
    </table>
    <div v-if="end & !isDelete">
      스케줄명 : {{name}}<br>
      가격 : {{price}}<br>
      <button @click="deleteScehdule">스케줄 삭제</button>
    </div>
    <div v-if="end & isDelete">삭제되었습니다!</div>
  </div>
</template>

<script>
export default {
  props: ["academyId", "academyScheduleId", "name", "price"],
  data(){
    return{
      end : false,
      isDelete : false
    }
  },
  mounted(){
    if(this.academyId != ''){
      this.end = true;
    }
  },
  methods:{
    pushSchedule(){
      this.$store
          .dispatch("student/addSchedule", {
            academy : {academyId : this.academyId},
            name : this.name,
            price : this.price
          })
          .then((res) => {
            if (res.status == 200) {
              this.end = true;
              this.academyScheduleId = res.data.academyScheduleId;
            } else {
              this.contents = "스케줄 등록에 실패했습니다."
            }
          })
          .catch(() => {
            console.log("스케줄 등록에 실패했습니다");
          });
    },
    deleteScehdule(){
      this.$store
          .dispatch("student/deleteSchedule", {
            academyScheduleId : this.academyScheduleId
          })
          .then((res) => {
            if (res.status == 200) {
              this.isDelete = true;
            } else {
              this.contents = "스케줄 삭제에 실패했습니다."
            }
          })
          .catch(() => {
            console.log("스케줄 삭제에 실패했습니다");
          });
    }
  }
}
</script>

<style>
.course-table {
  width: 100%;
  height: auto;
  text-align: left;
}
.input2 {
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 60px;
  margin-right: 1%;
}
</style>
