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
              this.reviews = [];
              for(var data of res.data){
                  this.reviews.push({reviewId:data.reviewId, score:data.score, title:data.title, contents:data.contents});
              }
            }else{
                console.log("리뷰를 가져오는데 문제가 생겼습니다.");
            }
          })
          .catch(() => {
            console.log("리뷰를 가져오는데 문제가 생겼습니다. catch");
          });
        },
        pushReview(){
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
    min-height: 500px;
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
    height: 400px;
}
.line-div{
    width: 100%;
    height: auto;
    margin-bottom: 10px;
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