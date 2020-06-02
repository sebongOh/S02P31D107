<template>
  <div>
    <table class="course-table">
      <tr>
        <td>스케줄명</td>
        <td><input class="input1" type="text" /></td>
      </tr>
      <tr>
        <td>가격</td>
        <td><input class="input1" type="number" /></td>
      </tr>
      <tr>
        <td>시작일</td>
        <td><input class="input1" type="date" /></td>
      </tr>
      <tr>
        <td>종료일</td>
        <td><input class="input1" type="date" /></td>
      </tr>
      <tr>
        <td colspan="2">스케줄 상세정보</td>
      </tr>
      <tr>
        <td colspan="2"><textarea class="input2" /></td>
      </tr>
      <!-- <tr><td colspan="2"><button>상세 스케줄 추가</button></td></tr> -->
    </table>
  </div>
</template>

<style>
.course-table {
  width: 100%;
  height: auto;
  text-align: left;
}
.input2 {
  border-radius: 3px;
  border: solid 1px gray;
  width: 99%;
  height: 60px;
  margin-right: 1%;
}
</style>
