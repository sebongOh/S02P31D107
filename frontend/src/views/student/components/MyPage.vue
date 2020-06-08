<template>
  <div>
    <h3>현재 수강중인 학원</h3>
    <table class="list-table">
      <tr class="academy-tr" v-for="academy in academys" :key="academy.academyId" @click="goBoard(academy)">
        <td class="left-div"><b>{{academy.name}}</b></td><td class="right-div">></td></tr>
    </table>
  </div>
</template>

<script>
export default {
  data(){
    return{
      academys: []
    }
  },
  methods:{
    getAcademy(){
      this.$store
          .dispatch("student/getMemberAcademy")
          .then((res) => {
            if (res.status == 200) {
              for(var data of res.data){
                  this.academys.push({academyId: data.academyId, name:data.name, address:data.address, category:data.category, phone:data.phone, contents:data.contents, imgUrl:data.imageUrl});
              }
            }else{
                console.log("수강 학원을 가져오는데 문제가 생겼습니다.");
            }
          })
          .catch(() => {
            console.log("수강 학원을 가져오는데 문제가 생겼습니다. catch");
          });
    },
    goBoard(academy) {
      var router = this.$router;
      router.push({
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

<style>
.list-table{
  width: 100%;
  height: auto;
  border-top: 1px solid black;
}
.academy-tr{
  width: 100%;
  height: auto;
  padding: 5%;
  border-bottom: 1px solid black;
}
.left-div{
  width: 75%;
  height: auto;
  float: left;
}
.right-div{
  width:15%;
  height: auto;
  float: right;
}
</style>