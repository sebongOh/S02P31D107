<template>
  <div class="block" style="overflow:auto">
    <!-- <el-row style="text-align:right; padding-bottom:5px">
      <el-button
        type="warning"
        size="mini"
        round
        icon="el-icon-edit"
        @click="insertBoard(2)"
      />
    </el-row> -->
    <el-timeline>
      <el-timeline-item
        v-for="(item, index) of sliceTimeline"
        :key="index"
        :timestamp="item.timestamp"
        placement="top"
      >
        <el-card>
          <h4>{{ item.title }}</h4>
          <p v-html="item.content"></p>
          <span class="link-black text-sm" @click="deleteDataboard(index)">
            <i class="el-icon-delete-solid" />
            Delete
          </span>
        </el-card>
      </el-timeline-item>
    </el-timeline>
    <el-row>
      <el-col :span="24" style="text-align:center">
        <el-button
          type="warning"
          icon="el-icon-back"
          circle
          @click="previous"
        ></el-button>
        <span style="padding:10px"
          ><b>{{ curPageNum }}</b></span
        >
        <el-button
          type="warning"
          icon="el-icon-right"
          circle
          @click="next"
        ></el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  props:['academyId'],
  data() {
    return {
      dataPerpage: 3,
      curPageNum: 1,
      timeline: [
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46",
        },
        {
          timestamp: "2019/4/21",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/21 20:46",
        },
        {
          timestamp: "2019/4/22",
          title: "Build Template",
          content: "PanJiaChen committed 2019/4/22 20:46",
        },
        {
          timestamp: "2019/4/23",
          title: "Release New Version",
          content: "PanJiaChen committed 2019/4/23 20:46",
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46",
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46",
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46",
        },
        {
          timestamp: "2019/4/20",
          title: "Update Github template",
          content: "PanJiaChen committed 2019/4/20 20:46",
        },
      ],
    };
  },
  destroyed() {},
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
    },
  },
  mounted(){
    this.getBoard();
  },
  methods: {
    getBoard(){
      this.$store
        .dispatch("student/getReference", {academyId : this.academyId})
        .then(res => {
          if (res.status == 200) {
              this.timeline = [];
            for (var data of res.data) {
              this.timeline.push({ timestamp:data.create_at, title: data.title, contents: data.contents });
            }
            console.log(this.timeline);
          } else {
            console.log('게시물을 가져오는데 실패했습니다.');
          }
        })
        .catch(() => {
          console.log("게시물을 가져오는데 실패했습니다. catch");
        });
    },
    deleteDataboard(num) {
      this.$message({
        message: num + "해당 글을 삭제했습니다",
        type: "success",
        duration: 2 * 1000,
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
    },
  },
};
</script>
