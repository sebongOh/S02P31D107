<template>
    <div>
      <div class="app-container">
        <h1>학원관리 페이지</h1>
        <el-select v-model="value" placeholder="관리할 학원 선택">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"></el-option>
        </el-select>
        <!-- 학원 추가용 + 버튼 -->
        <button class="add-btn" @click="addAcademy()">+</button>
        <AcademyAdd v-if="isAdd & value==''"/>
        <!-- 학원이 선택되어 있다면 해당 학원 안에 수정/삭제 기능 -->
        <AcademyModify v-if="value!=''" :academyId="value"/>
      </div>
        <div class="footer-domain">
            <Footer/>
        </div>
    </div>
</template>

<script>
import AcademyModify from "@/views/academyManagement/components/AcademyModify";
import AcademyAdd from "@/views/academyManagement/components/AcademyAdd";
import Footer from "@/views/academyManagement/components/Footer";

export default {
    components: { AcademyModify, AcademyAdd, Footer },
    data() {
      return {
        options: [{
          value: 'Value1',
          label: 'Option1'
        }, {
          value: 'Value2',
          label: 'Option2'
        }, {
          value: 'Value3',
          label: 'Option3'
        }, {
          value: 'Value4',
          label: 'Option4'
        }, {
          value: 'Value5',
          label: 'Option5'
        }],
        value: '',
        isAdd: false
      }
    },
    methods :{
      addAcademy(){
        this.isAdd = true;
        this.value ='';
      }
    }
};
</script>

<style>
.app-container{
  padding: 2%;
}
.add-btn{
  margin: 10px;
  width: 90px;
  height: 35px;
  font-size: 25px;
  font-weight: 100;
  color: #909399;
  background: white;
  border: 1px solid #DCDFE6;
  border-radius: 3px;
}
.add-btn:active{
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.42);
}
.footer-domain{
  width: auto;
  height: 80px;
}
</style>