<template>
  <div class="user-notice" style="overflow:auto">
    <el-row style="text-align:right; padding-bottom:5px">
      <el-button
        type="warning"
        size="mini"
        round
        icon="el-icon-edit"
        @click="insertNotice(2)"
      />
    </el-row>
    <div
      class="list"
      v-infinite-scroll="load"
      infinite-scroll-disabled="disabled"
    >
      <div class="post" v-for="notice in sliceNotice" :key="notice.id">
        <div class="user-block">
          <img class="img-circle" :src="notice.src + avatarPrefix" />
          <span class="username text-muted">{{ notice.username }}</span>
          <span class="description">{{ notice.description }}</span>
        </div>
        <p>{{ notice.content }}</p>
        <ul class="list-inline">
          <li>
            <span class="link-black text-sm" @click="share(notice.id)">
              <i class="el-icon-share" />
              Share
            </span>
          </li>
          <li>
            <span class="link-black text-sm" @click="deleteNotice(notice.id)">
              <i class="el-icon-delete-solid" />
              Delete
            </span>
          </li>
        </ul>
      </div>
    </div>
    <div style="text-align:center">
      <p v-if="loading"><i class="el-icon-loading" /> Loading...</p>
      <p v-if="noMore">No more</p>
    </div>
  </div>
</template>

<script>
const avatarPrefix = "?imageView2/1/w/80/h/80";
const carouselPrefix = "?imageView2/2/h/440";

export default {
  data() {
    return {
      activeName: 1,
      endOffset: 3,
      loading: false,
      avatarPrefix,
      notices: [
        {
          id: 1,
          src:
            "https://wpimg.wallstcn.com/57ed425a-c71e-4201-9428-68760c0537c4.jpg",
          username: "Iron Man",
          description: "Shared publicly - 7:30 PM today",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans ",
        },
        {
          id: 2,
          src:
            "https://wpimg.wallstcn.com/9e2a5d0a-bd5b-457f-ac8e-86554616c87b.jpg",
          username: "Captain American",
          description: "Sent you a message - yesterday",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 3,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin1",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 4,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin2",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 5,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin3",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 6,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin4",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 7,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin5",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
        {
          id: 8,
          src:
            "https://wpimg.wallstcn.com/fb57f689-e1ab-443c-af12-8d4066e202e2.jpg",
          username: "admin6",
          description: "2020.06.02",
          content:
            "Lorem ipsum represents a long-held tradition for designers, typographers and the like. Some people hate it and argue for its demise, but others ignore the hate as they create awesome tools to help create filler text for everyone from bacon lovers to Charlie Sheen fans.",
        },
      ],
    };
  },
  destroyed() {
    this.endOffset = 3;
  },
  computed: {
    noMore() {
      return this.endOffset >= 8;
    },
    disabled() {
      return this.loading || this.noMore;
    },
    sliceNotice() {
      return this.notices.slice(0, this.endOffset);
    },
  },
  methods: {
    share(num) {
      this.$message({
        message: "테스트",
        type: "success",
        duration: 2 * 1000,
      });
    },
    deleteNotice(num) {
      this.$message({
        message: "해당 공지사항을 삭제했습니다.",
        type: "success",
        duration: 2 * 1000,
      });
    },
    insertNotice(num) {
      this.$emit("changePageNum", num);
    },
    load() {
      this.loading = true;
      setTimeout(() => {
        this.endOffset += 1;
        this.loading = false;
      }, 2000);
    },
  },
};
</script>

<style lang="scss" scoped>
.el-icon-edit:hover {
  color: lightblue;
}
.user-notice {
  .user-block {
    .username,
    .description {
      display: block;
      margin-left: 50px;
      padding: 2px 0;
    }

    .username {
      font-size: 16px;
      color: #000;
    }

    :after {
      clear: both;
    }

    .img-circle {
      border-radius: 50%;
      width: 40px;
      height: 40px;
      float: left;
    }

    span {
      font-weight: 500;
      font-size: 12px;
    }
  }

  .post {
    font-size: 14px;
    border-bottom: 1px solid #d2d6de;
    margin-bottom: 15px;
    padding-bottom: 15px;
    color: #666;

    .image {
      width: 100%;
      height: 100%;
    }

    .user-images {
      padding-top: 20px;
    }
  }

  .list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;

    li {
      display: inline-block;
      padding-right: 5px;
      padding-left: 5px;
      font-size: 13px;
    }

    .link-black {
      &:hover,
      &:focus {
        color: #999;
      }
    }
  }
}

.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}
</style>
