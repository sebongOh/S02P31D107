<template>
  <div class="category">
    <div class="title">
      <span>반경 선택</span>
    </div>
    <div class="radius">
      <div class="radius-select">
        <el-slider v-model="options.radius" :marks="marks" :step="20"></el-slider>
      </div>
    </div>
    <div class="title">
      <span>분야 선택</span>
    </div>
    <div class="subjects">
      <!-- 입시 -->
      <div class="subject">
        <div class="subject--category">
          <span>입시</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('입시', '입시학원')">
            <span>#입시학원</span>
          </div>
        </div>
      </div>
      <!-- 단과 -->
      <div class="subject">
        <div class="subject--category">
          <span>단과</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('단과', '수학')">
            <span>#수학</span>
          </div>
          <div class="name" @click="select('단과', '영어')">
            <span>#영어</span>
          </div>
          <div class="name" @click="select('단과', '논술')">
            <span>#논술</span>
          </div>
          <div class="name" @click="select('단과', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 외국어 -->
      <div class="subject">
        <div class="subject--category">
          <span>외국어</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('외국어', '영어')">
            <span>#영어</span>
          </div>
          <div class="name" @click="select('외국어', '중국어')">
            <span>#중국어</span>
          </div>
          <div class="name" @click="select('외국어', '일본어')">
            <span>#일본어</span>
          </div>
          <div class="name" @click="select('외국어', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 공무원 -->
      <div class="subject">
        <div class="subject--category">
          <span>공무원</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('공무원', '임용고시')">
            <span>#임용고시</span>
          </div>
          <div class="name" @click="select('공무원', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 컴퓨터 -->
      <div class="subject">
        <div class="subject--category">
          <span>컴퓨터</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('컴퓨터', '게임')">
            <span>#게임</span>
          </div>
          <div class="name" @click="select('컴퓨터', '알고리즘')">
            <span>#알고리즘</span>
          </div>
          <div class="name" @click="select('컴퓨터', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 체육 -->
      <div class="subject">
        <div class="subject--category">
          <span>체육</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('체육', '검도')">
            <span>#검도</span>
          </div>
          <div class="name" @click="select('체육', '유도')">
            <span>#유도</span>
          </div>
          <div class="name" @click="select('체육', '수영')">
            <span>#수영</span>
          </div>
          <div class="name" @click="select('체육', '태권도')">
            <span>#태권도</span>
          </div>
          <div class="name" @click="select('체육', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 음악 -->
      <div class="subject">
        <div class="subject--category">
          <span>음악</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('음악', '피아노')">
            <span>#피아노</span>
          </div>
          <div class="name" @click="select('음악', '실용음악')">
            <span>#실용음악</span>
          </div>
          <div class="name" @click="select('음악', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 미술 -->
      <div class="subject">
        <div class="subject--category">
          <span>미술</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('미술', '만화')">
            <span>#만화</span>
          </div>
          <div class="name" @click="select('미술', '입시미술')">
            <span>#입시미술</span>
          </div>
          <div class="name" @click="select('미술', '기타')">
            <span>#기타</span>
          </div>
        </div>
      </div>
      <!-- 기타 -->
      <div class="subject">
        <div class="subject--category">
          <span>기타</span>
        </div>
        <div class="subject--name">
          <div class="name" @click="select('기타', '간호')">
            <span>#간호</span>
          </div>
          <div class="name" @click="select('기타', '요리')">
            <span>#요리</span>
          </div>
          <div class="name" @click="select('기타', '편입')">
            <span>#편입</span>
          </div>
          <div class="name" @click="select('기타', '웅변/스피치')">
            <span>#웅변/스피치</span>
          </div>
        </div>
      </div>
    </div>
    <div class="selected-subjects">
      <div
        class="selected-subject"
        v-for="(sub, idx) in selected"
        :key="idx"
        @click="deleteSub(sub)"
      >
        <span>{{sub}}</span>
      </div>
    </div>
    <div class="submit">
      <button @click="setCategory">검색</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: {
        radius: 0
      },
      marks: {
        0: "1km",
        20: "2km",
        40: "3km",
        60: "4km",
        80: "5km",
        100: "6km"
      },
      set: new Set(),
      selected: []
    };
  },
  methods: {
    setCategory() {
      this.$emit("setCategory", this.options);
    },
    select(category, subject) {
      let str = `${category} - #${subject}`;
      if (this.set.has(str)) {
        this.set.delete(str);
      } else {
        this.set.add(str);
      }
      this.selected = [];
      for (let item of this.set) this.selected.push(item);
    },
    deleteSub(subject) {
      this.set.delete(subject);
      this.selected = [];
      for (let item of this.set) this.selected.push(item);
    }
  }
};
</script>

<style lang="scss">
.category {
  padding: 10px 30px;
  .title {
    margin: 10px 0px;
    span {
      font-size: 23px;
    }
  }

  .radius {
    margin-bottom: 50px;
    .el-slider__bar {
      background-color: #403a33 !important;
    }
    .el-slider__button {
      border: 2px solid #403a33 !important;
    }
  }

  .subjects {
    padding: 10px;
    background-color: #f2e6ce;
    border-radius: 10px;
    margin-bottom: 10px;
    .subject {
      .subject--category {
        span {
          font-size: 18px;
          font-weight: 600;
        }
      }
      display: flex;
      margin-bottom: 10px;
      .subject--category {
        width: 70px !important;
      }
      .subject--name {
        display: flex;
        .name {
          margin-right: 10px;
        }
      }
    }
  }

  .selected-subjects {
    margin-bottom: 30px;
    .selected-subject {
      float: left;
      margin: 0 5px 5px 0;
      padding: 10px;
      background-color: #d9cab0;
      border-radius: 10px;
    }
  }

  .submit {
    clear: both;
    display: flex;
    justify-content: center;
    margin-top: 30px;

    button {
      font-family: "Yeon Sung", cursive;
      width: 80px;
      height: 40px;
      background-color: #403a33;
      color: white;
      border-radius: 5px;
    }
  }
}
</style>