<template>
    <div id="topNav" class="sticky-top">
      <div style="position:relative; margin-bottom:50px;">
        <div id="backImg" style="position:absolute; left:5px; top:0px;">  <img @click="back()" id="img" src="@/img/back.png"></div>
        <div id="GG" style="position:absolute; left:150px; top:0px;"> <router-link :to="'/home'"><img class="GG" src="@/img/GG.png"></router-link></div>
        <!-- <div id="addImg"> <img @click="goCardRegist" src="@/img/add.png"/></div> -->
        <img id="line" src="@/img/line.png" style="position:absolute; left:15px; top:35px;"/>
      </div>      
      
      
    </div>
</template>
<script>
export default {
  name: "Topnav",
methods: {
  goCardRegist(){
      if(this.$session.get("card_no") != null){
        alert("이미 만든 카드가 있습니다.");
      }else{
        this.$router.push("/cardregist");
      }
    },
    back(){
      this.$router.go(-1);
    },
  },
}
</script>
<style scoped>
#topNav {
  padding: 10px 10px 0 10px;
  width: 355px;
  background-color: white;
}
#backImg >img {
  width: 30px;
  height: 30px;
  cursor:pointer;
}
.GG{
  width:60px; 
  height:30px; 
  cursor:pointer;
}
#addImg > img{
  width: 30px;
  height: 30px;
  cursor:pointer;
}
#line{
  width: 320px;
}
</style>