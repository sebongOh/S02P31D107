<template>
  <el-form>
    <el-form-item label="제목">
      <el-input v-model.trim="user.name" />
    </el-form-item>
    <el-form-item label="내용">
      <el-input type="textarea" :rows="7" v-model.trim="user.email" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">문의하기</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: "",
          email: "",
        };
      },
    },
  },
  methods: {
    submit() {
      this.$message({
        message: "User information has been updated successfully",
        type: "success",
        duration: 5 * 1000,
      });
    },
  },
};
</script>
