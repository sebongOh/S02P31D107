<template>
    <div>
        <div v-if="!writeReview">
            <div v-if="isPayed" class="add-review-div">
                <div class="write-review-div" @click="writeReview = true">리뷰 쓰기</div>
            </div>
            <div class="review-div">
                <el-carousel :interval="10000" arrow="always" height="500px">
                    <el-carousel-item v-for="r in reviews" :key="r.reviewId">
                        <Review :review="r"/>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </div>
        <div v-if="writeReview" class="write-div">
            <div class="line-div">평점(1~5) <input class="input3" type="number" v-model="score"></div>
            <div class="line-div">제목</div><div class="line-div"><input class="input4" type="text" v-model="title"></div>
            <div class="line-div">내용(200자 이내)</div><div class="line-div"><textarea class="input5" v-model="contents" /></div>
            <table class="btn-table"><tr><td class="btn2"><button class="ok-btn" @click="pushReview()"><b>등록</b></button></td>
            <td class="btn2"><button class="ok-btn" @click="writeReview = false"><b>취소</b></button></td></tr></table>
        </div>
    </div>
</template>

<script>
import Review from "@/views/academyDetail/components/Review";

export default {
    components: { Review },
    props:['academyId', 'isPayed'],
    mounted(){
        this.getReview();
    },
    data(){
        return{
            reviews: {},
            writeReview: false,
            score:5,
            title: "",
            contents: ""
        }
    },
    methods:{
        getReview(){
        this.$store
          .dispatch("student/readReview", {
            academyId: this.academyId
          })
          .then((res) => {
            if (res.status == 200) {
              console.log(res.data);
              //임시 리뷰
              this.reviews = [{ reviewId:1, title:"SSAFY학원 최고! 강추강추! 꼭 와서 들어보세요!", score:5, createDate:"2020-06-04", contents: "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라"},
                { reviewId:2, title:"SSAFY학원 추천합니다", score:4, createDate:"2020-06-04", contents: "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English."},
                { reviewId:3, title:"다시 가고 싶다...", score:5, createDate:"2020-06-04", contents: "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text."}];
                //임시 리뷰 끝
            }else{
                console.log("리뷰를 가져오는데 문제가 생겼습니다.");
            }
          })
          .catch(() => {
            console.log("리뷰를 가져오는데 문제가 생겼습니다. catch");
          });
        },
        pushReview(){
            console.log(this.academyId);
            console.log(this.score);
            console.log(this.title);
            console.log(this.contents);
            this.$store
          .dispatch("student/writeReview", {
            academy: {academyId: this.academyId},
            score: this.score,
            title: this.title,
            contents: this.contents,
          })
          .then((res) => {
            if (res.status == 200) {
                alert("리뷰를 등록했습니다!");
                this.writeReview = false;
            }else{
                console.log("리뷰 등록에 실패했습니다.");
            }
          })
          .catch((err) => {
            console.log("리뷰 등록에 실패했습니다. catch");
          });
        }
    }
}
</script>

<style>
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
.write-div{
    padding: 3%;
}
.line-div{
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
.input4{
    width: 96%;
    height: 30px;
    border: solid 1px black;
    padding-left: 5px;
}
.input5{
    width: 96%;
    height: 130px;
    border: solid 1px black;
    padding: 5px;
}
</style>