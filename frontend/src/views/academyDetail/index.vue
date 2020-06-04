<template>
<div>
    <div class="academy-detail" v-if="!writeReview">
        <h2>{{academyName}}</h2>
        <img v-if="academyImg != ''" class="img-div" :src="academyImg" />
        <div v-if="academyImg == ''" class="img-div"/>
        <div class="detail-div">{{academyDetail}}</div><br>
        <b>주소</b> : {{academyAddress}}<br>
        <b>연락처</b> : {{academyPhone}}<br>
        <div v-if="isPayed && isAvailable" class="add-review-div">
            <div class="write-review-div" @click="writeReview = true">리뷰 쓰기</div>
        </div>
        <div class="review-div">
        <el-carousel arrow="always" height="500px">
            <el-carousel-item v-for="r in reviews" :key="r.reviewId">
                <Review :review="r"/>
            </el-carousel-item>
        </el-carousel>
        </div>
    </div>
    <div v-if="writeReview">
        <ReviewWrite :academyId="academyId" :email="email" />
        <table class="btn-table"><tr><td class="btn2"><button class="ok-btn"><b>등록</b></button></td>
        <td class="btn2"><button class="ok-btn" @click="writeReview = false"><b>취소</b></button></td></tr></table>
    </div>
    <div class="footer-domain">
        <Footer/>
    </div>
    <button v-if="isAvailable & isPayed & !writeReview" class="port-btn" @click="goBoard()"><b>게시판<br>이동</b></button>
    <button v-if="isAvailable & !isPayed & !writeReview" class="port-btn" @click="goPay()"><b>결제하기</b></button>
</div>
</template>

<script>
import Footer from "@/views/academyDetail/components/Footer";
import Review from "@/views/academyDetail/components/Review";
import ReviewWrite from "@/views/academyDetail/components/ReviewWrite";
import { mapGetters } from "vuex";
import { getToken } from "@/utils/auth";

export default {
    components: { Footer, Review, ReviewWrite },
    props: ["academyId", "name", "address", "phone"],
    computed: {
    ...mapGetters(["email"]),
    },
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
            reviews: {},
            isPayed: false,
            isAvailable: true,
            writeReview: false
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

                //임시 데이터 삽입
                this.isAvailable = true;
                this.isPayed = true;
                this.academyDetail = "학원 상세 설명 부분"
                this.academyName = "SSAFY학원";
                this.academyAddress = "OO시 OO구 OO동";
                this.academyPhone = "010-0000-1111";
                this.academyImg = "http://edu.ssafy.com/asset/images/header-logo.jpg";
                this.reviews = [{ reviewId:1, title:"SSAFY학원 최고! 강추강추! 꼭 와서 들어보세요!", score:5, createDate:"2020-06-04", contents: "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라"},
                { reviewId:2, title:"SSAFY학원 추천합니다", score:4, createDate:"2020-06-04", contents: "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English."},
                { reviewId:3, title:"다시 가고 싶다...", score:5, createDate:"2020-06-04", contents: "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text."}];
            }else{
                this.isAvailable = true;
                //academyId로 학원상세정보 받아오기
                //등록한 회원이면 isPayed = true
                //백엔드 통신으로 전체 리뷰 리턴되면 props로  Review 컴포넌트에 다 갖다주기
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
    min-height: 1000px;
    background-color: #DCDFE6;
}
.img-div{
    width: 100%;
    height: 300px;
    margin-top: 10px;
    margin-bottom: 10px;
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
.el-carousel{
    z-index: 0;
}
.review-div{
    width: 100%;
    min-height: 250px;
    height: auto;
}
.add-review-div{
    width: 100%;
    height: 15px;
    padding: 2px;
}
.write-review-div{
    float: right;
    font-size: 12px;
}
.btn-table{
    width: 90%;
    height: auto;
    margin: 5%;
}
.btn2 {
  width: 50%;
  text-align: center;
}
.ok-btn {
  width: 50%;
  height: 40px;
  border-radius: 5px;
  background-color: coral;
  border: solid 3px chocolate;
}
</style>
