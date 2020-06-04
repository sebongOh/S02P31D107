<template>
    <div class="write-div">
        <div class="line-div">평점(1~5) <input class="input3" type="number"></div>
        <div class="line-div">제목</div><div class="line-div"><input class="input4" type="text"></div>
        <div class="line-div">내용(200자 이내)</div><div class="line-div"><textarea class="input5" /></div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style>
.write-div{
    padding: 3%;
}
.line-div{
    width: 100%;
    height: auto;
    margin-bottom: 10px;
}
.input3 {
  width: 30px;
  height: 30px;
  padding: 0;
  text-align: center;
  border: solid 1px black;
}
.input4{
    width: 96%;
    height: 30px;
    border: solid 1px black;
    padding-left: 5px;
}
.input5{
    width: 96%;
    height: 130px;
    border: solid 1px black;
    padding: 5px;
}
</style>