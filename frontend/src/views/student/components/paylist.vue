<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item
        v-for="paylist in slicePaylist"
        :key="paylist.payId"
        :timestamp="paylist.approvedAt"
        placement="top"
      >
        <el-card>
          <h2>{{paylist.academySchedule.academy.name}}</h2>
          <h3>{{ paylist.itemName }}</h3>
          <h4 v-if="paylist.type==0">{{paylist.price}}원</h4>
          <h4 v-if="paylist.type==1" style="text-decoration:line-through">{{paylist.price}}원</h4>
          <span v-if="paylist.type==0" style="color:green">결제완료</span>
          <span v-if="paylist.type==1" style="color:red">결제취소</span>
          <p>
            <span class="link-black text-sm" @click="detail(paylist)">
              <i class="el-icon-more" />
              더보기
            </span>
          </p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
    <el-row>
      <el-col :span="24" style="text-align:center">
        <el-button type="warning" icon="el-icon-back" circle @click="previous"></el-button>
        <span style="padding:10px">
          <b>{{ curPageNum }}</b>
        </span>
        <el-button type="warning" icon="el-icon-right" circle @click="next"></el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dataPerpage: 2,
      curPageNum: 1,
      academySchedule: [{}],
      paylists: []
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.$store
        .dispatch("student/paylist")
        .then(res => {
          for (let i = 0; i < res.data.length; i++) {
            this.paylists.push(res.data[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    detail(paylist) {
      this.$emit("datareturn", paylist);
      this.$emit("changePagenum", 1);
    },
    previous() {
      if (1 < this.curPageNum) {
        return (this.curPageNum -= 1);
      }
      return this.curPageNum;
    },
    next() {
      if (this.numOfpages > this.curPageNum) {
        return (this.curPageNum += 1);
      }
      return this.curPageNum;
    }
  },
  computed: {
    startOffset() {
      return (this.curPageNum - 1) * this.dataPerpage;
    },
    endOffset() {
      return this.startOffset + this.dataPerpage;
    },
    numOfpages() {
      return Math.ceil(this.paylists.length / this.dataPerpage);
    },
    slicePaylist() {
      return this.paylists.slice(this.startOffset, this.endOffset);
    }
  }
};
</script>

<style>
.el-timeline {
  padding: 0;
}
.el-card__body {
  padding-top: 5px;
}
.el-timeline-item__timestamp {
  font-size: 18px;
  color: black;
}
.el-timeline-item__tail {
  background: black;
  border-left: 2px solid white;
}
.el-timeline-item__node {
  background-color: #a69d8d;
}
</style>