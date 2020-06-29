<template>
  <div class="category">
    <div class="radius">
      <h4>검색 반경</h4>
      <div class="radius-select">
        <el-slider v-model="options.radius" :marks="marks" :step="20"></el-slider>
      </div>
    </div>
    <div class="subject">
      <h4>분야 선택</h4>
      <div class="subject-kinds" v-for="(subject, idx) in subjects" :key="idx">
        <div class="subject-category">
          <span>{{idx}}</span>
        </div>
        <div class="subjects-name">
          <div
            class="subject-name"
            v-for="sub in subject"
            :key="sub"
            @click="clickCategory(idx, sub)"
          >
            <span>#{{sub}}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="tag">
      <h4>태그 검색</h4>
    </div>
    <div class="submit">
      <el-button type="warning" @click="setCategory">검색</el-button>
    </div>
  </div>
</template>

<script>
//단과
const subject1 = ["국어", "수학", "사회", "과학", "영어", "논술", "기타"];
//입시
const subject2 = ["국어", "수학", "사회", "과학", "영어", "논술", "기타"];
//편입
//외국어
const subject3 = ["영어", "중국어", "일본어"];
//공무원
const subject4 = ["임용고시"];
//컴퓨터
const subject5 = ["자격증", "게임", "코딩", "로봇", "알고리즘"];
//체육
const subject6 = [
  "태권도",
  "검도",
  "합기도",
  "유도",
  "수영",
  "농구",
  "축구",
  "유아체육"
];
//음약
const subject7 = [
  "피아노",
  "실용음악",
  "바이올린",
  "국악",
  "작곡",
  "보컬",
  "성악"
];
//미술
const subject8 = ["입시미술", "서예", "만화", "도예", "공예", "캘리그라피"];
//독서실
const subject9 = ["1인실", "다인실"];
//간호
//요리
const subject10 = ["한식", "중식", "일식", "제과"];
//기타
const subject11 = [
  "한자",
  "바둑",
  "웅변/스피치",
  "연극/연기",
  "공인중개사",
  "편입"
];
const checked = new Set();
export default {
  data() {
    return {
      options: {
        radius: 0,
        checked: ""
      },
      marks: {
        0: "1km",
        20: "2km",
        40: "3km",
        60: "4km",
        80: "5km",
        100: "6km"
      },
      subjects: {
        단과: subject1,
        입시: subject2,
        외국어: subject3,
        공무원: subject4,
        컴퓨터: subject5,
        체육: subject6,
        음악: subject7,
        미술: subject8,
        독서실: subject9,
        요리: subject10,
        기타: subject11
      },
      checkboxGroup: []
    };
  },
  methods: {
    setCategory() {
      this.options.checked = checked;
      this.$emit("setCategory", this.options);
    },
    clickCategory(idx, sub) {
      checked.add(idx);
      checked.add(sub);
    }
  }
};
</script>

<style lang="scss">
.category {
  padding: 0 30px;
  .range-select {
    width: 80%;
  }

  .subject-kinds {
    .subject-category {
      width: 70px;
    }

    .subjects-name {
      display: flex;
      flex-wrap: wrap;
      .subject-name {
        margin-right: 10px;
        cursor: pointer;
      }
    }
  }
}
</style>