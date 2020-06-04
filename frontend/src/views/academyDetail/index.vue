<template>
<div>
    <div class="academy-detail">
        <h2>{{academyName}}</h2>
        <img v-if="academyImg != ''" class="img-div" src="" />
        <div v-if="academyImg == ''" class="img-div"/>
        <div class="detail-div">{{academyDetail}}</div><br>
        <b>주소</b> : {{academyAddress}}<br>
        <b>연락처</b> : {{academyPhone}}<br>
    </div>
    <div class="footer-domain">
        <Footer/>
    </div>
    <button v-if="isAvailable & isPayed" class="port-btn" @click="goBoard()"><b>게시판<br>이동</b></button>
    <button v-if="isAvailable & !isPayed" class="port-btn" @click="goPay()"><b>결제하기</b></button>
</div>
</template>

<script>
import Footer from "@/views/academyDetail/components/Footer";
export default {
    components: { Footer },
    props: ["academyId", "name", "address", "phone"],
    mounted(){
        this.getAcademy();
    },
    data() {
        return {
            academyName: "",
            academyDetail: "",
            academyImg: "",
            academyAddress: "",
            academyPhone: "",
            isPayed: false,
            isAvailable: true
        }
    },
    methods: {
        getAcademy(){
            if(this.academyId == null){
                this.isAvailable = false;
                this.academyDetail = "아직 등록되지 않은 학원입니다."
                this.academyName = this.name;
                this.academyAddress = this.address;
                this.academyPhone = this.phone;
                //props로 값들 받아오기
            }else{
                this.isAvailable = true;
                //academyId로 학원상세정보 받아오기
                //등록한 회원이면 isPayed = true
            }
        },
        goBoard(){
            var router = this.$router;
            router.push("/");
        },
        goPay(){
            var router = this.$router;
            router.push("/");
        }
    }
}
</script>

<style>
h2{
    margin: 0;
}
.academy-detail{
    padding: 3%;
    width: 100%;
    height: 100%;
    min-height: 600px;
    background-color: #DCDFE6;
}
.img-div{
    width: 100%;
    height: 300px;
}
.detail-div{
    width: 100%;
    height: auto;
}
.port-btn{
    position: fixed;
	bottom: 70px;
	right: 30px;
    width: 100px;
    height: 70px;
    background-color: skyblue;
    text-align: center;
    border: none;
    border-radius: 80px;
    font-size: 15px;
    outline: 0;
    box-shadow: 3px 3px 3px gray;
}
.port-btn:active{
    bottom: 67px;
	right: 27px;
    box-shadow: none;
}
</style>
