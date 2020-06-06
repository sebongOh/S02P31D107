<template>
    <div class="detail-div">
        {{contents}}<br>
        <b>카테고리</b> : {{category}}<br>
        <b>주소</b> : {{address}}<br>
        <b>연락처</b> : {{phone}}<br>
    </div>
</template>

<script>
export default {
    props:['address', 'category', 'contents', 'phone']
}
</script>

<style>
.detail-div{
    padding: 5px;
}
</style>