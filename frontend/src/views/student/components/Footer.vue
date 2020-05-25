<template>
  <div class="footer">
    <div class="icon">
      <span>
        <i class="fas fa-user"></i>
      </span>
    </div>
    <div class="icon">
      <span>
        <i class="fas fa-home"></i>
      </span>
    </div>
    <div class="icon">
      <span>
        <i class="fas fa-search"></i>
      </span>
    </div>
    <div class="icon">
      <span>
        <i class="fas fa-ellipsis-h"></i>
      </span>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.footer {
  background-color: #c99d4e;
  position: fixed;
  bottom: 0;
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: space-around;
  align-items: center;

  .icon span {
    font-size: 1.2em;
  }
}
</style>