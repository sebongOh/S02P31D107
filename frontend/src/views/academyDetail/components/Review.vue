<template>
<div>
    <div class="card-div" v-if="!isDetail" @click="isDetail = true">
        <div class="title-div"><b>{{review.title}}</b></div>
        <div class="score-div"><span class="blue">★</span>{{review.score}}</div><div class="date-div">{{review.createDate}}</div>
        <div class="content-div">{{review.contents}}</div>
    </div>
    <div class="card-detail-div" v-if="isDetail" @click="isDetail = false">
        <div class="title-detail-div"><b>{{review.title}}</b></div>
        <div class="width-full"><div class="score-div"><span class="blue">★</span>{{review.score}}</div><div class="date-div">{{review.createDate}}</div></div>
        <div class="content-detail-div">{{review.contents}}</div>
    </div>
</div>
</template>

<script>
export default {
    props: ["review"],
    data() {
        return {
            isDetail: false
        }
    }
}
</script>

<style>
.card-div{
    background-color: white;
    width: 80%;
    height: 200px;
    margin: 5%;
    border-radius: 20px;
    padding: 5%;
}
.card-detail-div{
    background-color: white;
    width: 80%;
    height: auto;
    margin: 5%;
    border-radius: 20px;
    padding: 5%;
}
.title-div{
    width: 100%;
    height: 25px;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
}
.title-detail-div{
    width: 100%;
    height: auto;
}
.score-div{
    float: right;
}
.date-div{
    float: right;
    font-size: 12px;
    margin: 5px;
}
.content-div{
    width: 100%;
    height: 130px;
    margin-top: 40px;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: pre-wrap;
    word-wrap:break-word;
    display: -webkit-box;
    -webkit-line-clamp: 6;
    -webkit-box-orient: vertical;
}
.content-detail-div{
    width: 100%;
    height: auto;
    margin-top: 20px;
}
.center-div{
    text-align: center;
}
.width-full{
    width: 100%;
    height: 22px;
}
.blue{
    color: blue;
}
</style>