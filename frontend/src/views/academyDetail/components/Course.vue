<template>
    <div>
        <div class="schedule-list" v-for="d in datas" :key="d.academyScheduleId" @click="goPay(d.academyScheduleId, d.name, d.price)">
            <b>코스명</b> : {{d.name}}<br>
            <b>가격</b> : {{d.price}} 원
        </div>
    </div>
</template>
<script>
export default {
    props:['academyId'],
    data(){
        return{
            datas : []
        }
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
              academyScheduleId:id,
              name:name,
              price:price
            }
            });
        },
    }
}
</script>

<style>
.schedule-list{
    width: 100%;
    height: auto;
    border-bottom: 1px black solid;
}
.schedule-list:hover{
    background-color: #F2F6FC;
}
</style>