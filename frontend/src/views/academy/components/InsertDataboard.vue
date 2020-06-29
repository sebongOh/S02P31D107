<template>
  <el-form>
    <el-form-item label="제목">
      <el-input v-model="board.title" />
    </el-form-item>
    <el-form-item label="내용">
      <el-input type="textarea" v-model="board.content" :rows="7" />
    </el-form-item>
    <el-form-item label="자료">
      <input type="file" />
    </el-form-item>
    <el-form-item>
      <el-button type="warning" @click="gogo(1)">뒤로가기</el-button>
      <el-button type="primary" @click="gogo(1)">글쓰기</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      board: {},
    };
  },
  methods: {
    gogo(num) {
      this.$emit("changePageNum", num);
    },
  },
};
</script>

<style></style>
