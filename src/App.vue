

<template>
  <div>
<!--    头部-->
    <Header/>
<!--    主体-->
  <div style="display: flex">
<!--    侧边栏-->
    <Aside/>
<!--    内容区域-->
    <router-view style="flex: 1"></router-view>
  </div>
  </div>

</template>

<style >

</style>

<script>
import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";

export default {
  name:"layout",
  components:{
    Header, //option+enter导入
    Aside
  }

}
</script>
