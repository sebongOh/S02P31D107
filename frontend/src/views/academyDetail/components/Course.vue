<template>
    <div class="course-div">
        <hr>
        <div class="schedule-list" v-for="d in datas" :key="d.academyScheduleId" @click="goPay(d.academyScheduleId, d.name, d.price)">
            <b>코스명</b> : {{d.name}}<br>
            <b>가격</b> : {{d.price}} 원
        </div>
    </div>
</template>
<script>
export default {
    props:['academyId', 'academyName'],
    data(){
        return{
            datas : []
        }
    },
    mounted(){
        this.getSchedule();
    },
    methods:{
        getSchedule(){
            this.$store
          .dispatch("student/getSchedule", {
            academyId: this.academyId
          })
          .then((res) => {
            if (res.status == 200) {
              for(var data of res.data){
                  this.datas.push({academyScheduleId: data.academyScheduleId, name:data.name, price:data.price});
              }
            }else{
                console.log("스케줄을 가져오는데 문제가 생겼습니다.");
            }
          })
          .catch(() => {
            console.log("스케줄을 가져오는데 문제가 생겼습니다. catch");
          });
        },
        goPay(id, name, price) {
            var router = this.$router;
            router.push({
            name: "payment",
            params: {
              academyName:this.academyName,
              scheduleId:id,
              scheduleName:name,
              price:price
            }
            });
        },
    }
}
</script>

<style>
.course-div{
    width: 96%;
    height: auto;
    min-height: 500px;
    margin-left: 2%;
}
.schedule-list{
    width: 100%;
    height: 60px;
    border-bottom: 1px solid gray;
    padding-top: 10px;
}
.schedule-list:hover{
    background-color: #F2F6FC;
}
</style>