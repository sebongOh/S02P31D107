<template>
  <div>
    <div class="academy-detail" v-if="!writeReview">
      <h2>{{academyName}}</h2>
      <img v-if="academyImg != ''" class="img-div" :src="academyImg" />
      <div v-if="academyImg == ''" class="img-div" />
      <div class="detail-div">{{academyDetail}}</div>
      <br />
      <b>주소</b>
      : {{academyAddress}}
      <br />
      <b>연락처</b>
      : {{academyPhone}}
      <br />
      <div v-if="isPayed && isAvailable" class="add-review-div">
        <div class="write-review-div" @click="writeReview = true">리뷰 쓰기</div>
      </div>
      <div class="review-div">
        <el-carousel :interval="10000" arrow="always" height="500px">
          <el-carousel-item v-for="r in reviews" :key="r.reviewId">
            <Review :review="r" />
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <div v-if="writeReview" class="write-div">
      <div class="line-div">
        평점(1~5)
        <input class="input3" type="number" v-model="score" />
      </div>
      <div class="line-div">제목</div>
      <div class="line-div">
        <input class="input4" type="text" v-model="title" />
      </div>
      <div class="line-div">내용(200자 이내)</div>
      <div class="line-div">
        <textarea class="input5" v-model="contents" />
      </div>
      <table class="btn-table">
        <tr>
          <td class="btn2">
            <button class="ok-btn" @click="pushReview()">
              <b>등록</b>
            </button>
          </td>
          <td class="btn2">
            <button class="ok-btn" @click="writeReview = false">
              <b>취소</b>
            </button>
          </td>
        </tr>
      </table>
    </div>
    <button v-if="isAvailable & isPayed & !writeReview" class="port-btn" @click="goBoard()">
      <b>
        게시판
        <br />이동
      </b>
    </button>
    <button v-if="isAvailable & !isPayed & !writeReview" class="port-btn" @click="goPay()">
      <b>결제하기</b>
    </button>
  </div>
</template>

<script>
import Review from "@/views/academyDetail/components/Review";

export default {
  components: { Review },
  props: ["academyId", "name", "address", "phone", "category"],
  mounted() {
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
      writeReview: false,
      score: 5,
      title: "",
      contents: ""
    };
  },
  methods: {
    getAcademy() {
      if (this.academyId == null) {
        this.isAvailable = false;
        this.academyDetail = "아직 등록되지 않은 학원입니다.";
        this.academyName = this.name;
        this.academyAddress = this.address;
        this.academyPhone = this.phone;
        //props로 값들 받아오기

        //임시 데이터 삽입
        this.academyId = "9749818";
        this.isAvailable = true;
        this.isPayed = true;
        this.academyDetail = "학원 상세 설명 부분";
        this.academyName = "싸피학원";
        this.academyAddress = "OO시 OO구 OO동";
        this.academyPhone = "010-0000-1111";
        this.academyImg = "http://edu.ssafy.com/asset/images/header-logo.jpg";
        this.reviews = [
          {
            reviewId: 1,
            title: "SSAFY학원 최고! 강추강추! 꼭 와서 들어보세요!",
            score: 5,
            createDate: "2020-06-04",
            contents:
              "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라"
          },
          {
            reviewId: 2,
            title: "SSAFY학원 추천합니다",
            score: 4,
            createDate: "2020-06-04",
            contents:
              "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English."
          },
          {
            reviewId: 3,
            title: "다시 가고 싶다...",
            score: 5,
            createDate: "2020-06-04",
            contents:
              "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text."
          }
        ];
        //임시 데이터 끝
      } else {
        this.isAvailable = true;
        //academyId로 학원상세정보 받아오기
        //등록한 회원이면 isPayed = true
        this.$store
          .dispatch("student/readReview", {
            //리뷰 받아오기
            academyId: this.academyId
          })
          .then(res => {
            if (res.status == 404) {
              console.log("aniVibro가 뭐죠 404");
              this.aniVibro("code", "리뷰 받아오기에 실패하였습니다.");
            } else if (res.status == 200) {
              console.log("리뷰 받아오기 성공");
              //받아온 리뷰들 reviews 에 넣기
            }
          })
          .catch(() => {
            console.log("aniVibro가 뭐죠 catch");
            this.aniVibro("code", "서버 접속을 실패했습니다.");
          });
      }
    },
    goBoard() {
      var router = this.$router;
      router.push("/academy-main");
    },
    goPay() {
      var router = this.$router;
      router.push("/payment");
    },
    pushReview() {
      console.log(this.academyId);
      console.log(this.score);
      console.log(this.title);
      console.log(this.contents);
      this.$store
        .dispatch("student/writeReview", {
          academy: { academyId: this.academyId },
          score: this.score,
          title: this.title,
          contents: this.contents
        })
        .then(res => {
          if (res.status == 200) {
            alert("리뷰를 등록했습니다!");
            this.writeReview = false;
          } else {
            console.log("리뷰 등록에 실패했습니다.");
          }
        })
        .catch(err => {
          console.log("리뷰 등록에 실패했습니다. catch");
        });
    }
  }
};
</script>

<style>
h2 {
  margin: 0;
}
.academy-detail {
  padding: 3%;
  width: 100%;
  height: 100%;
  min-height: 1000px;
  background-color: #dcdfe6;
}
.img-div {
  width: 100%;
  height: 300px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.detail-div {
  width: 100%;
  height: auto;
}
.port-btn {
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
.port-btn:active {
  bottom: 67px;
  right: 27px;
  box-shadow: none;
}
.el-carousel {
  z-index: 0;
}
.review-div {
  width: 100%;
  min-height: 250px;
  height: auto;
}
.add-review-div {
  width: 100%;
  height: 15px;
  padding: 2px;
}
.write-review-div {
  float: right;
  font-size: 12px;
}
.btn-table {
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
.write-div {
  padding: 3%;
}
.line-div {
  width: 100%;
  height: auto;
  margin-bottom: 10px;
}
.input3 {
  width: 30px;
  height: 30px;
  padding: 0;
  text-align: center;
  border: solid 1px black;
}
.input4 {
  width: 96%;
  height: 30px;
  border: solid 1px black;
  padding-left: 5px;
}
.input5 {
  width: 96%;
  height: 130px;
  border: solid 1px black;
  padding: 5px;
}
</style>
