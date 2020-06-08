<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item
        v-for="(item, index) of sliceTimeline"
        :key="index"
        :timestamp="item.timestamp"
        placement="top"
      >
        <el-card>
          <h4>{{ item.title }}</h4>
          <p>{{ item.content }}</p>
          <span class="link-black text-sm" @click="detail(index)">
            <i class="el-icon-more" />
            더보기
          </span>
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
      dataPerpage: 3,
      curPageNum: 1,
      timeline: [
        {
          timestamp: "2019/4/20",
          title: "싸피학원",
          content: "+200000원"
        },
        {
          timestamp: "2019/4/21",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/21 20:46"
        },
        {
          timestamp: "2019/4/22",
          title: "Build Template",
          content: "PanJiaChen committed 2019/4/22 20:46"
        },
        {
          timestamp: "2019/4/23",
          title: "Release New Version",
          content: "PanJiaChen committed 2019/4/23 20:46"
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46"
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46"
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46"
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46"
        }
      ]
    };
  },
  methods: {
    detail(num) {
      this.$emit("changeNum", 1);
      this.$message({
        message: num + "detail",
        type: "success",
        duration: 2 * 1000
      });
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
    },
    insertBoard(num) {
      this.$emit("changePageNum", num);
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
      return Math.ceil(this.timeline.length / this.dataPerpage);
    },
    sliceTimeline() {
      return this.timeline.slice(this.startOffset, this.endOffset);
    }
  }
};
</script>

<style>
</style>