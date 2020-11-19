<template>
    <div id="footer" class="fixed-bottom">   
      <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
      <img id="line" src="@/img/line.png"/>
      <div class="footer d-flex justify-content-between">
        <div> <router-link :to="'/board'" ><img id="nav1" src="@/img/nav1.png" style="margin-left:0px;"/></router-link></div>
        <div> <router-link :to="'/applicant'" ><img id="nav2" src="@/img/nav2.png" style="margin-left:-50px;"/></router-link></div>
        <div> <router-link :to="'/home'"><img id="nav3" src="@/img/nav3.png" style="margin-left:-45px;"/></router-link></div>
        <div> <router-link :to="'/alarm'" ><img id="nav4" src="@/img/nav4_new.png" style="width:30px; margin-top:-3px; margin-left:-45px;"/></router-link></div>
        <div> <router-link :to="'/mypage'" ><img id="nav5" src="@/img/nav5_new.png" style="width:30px; margin-top:-3px; margin-left:-45px;"/></router-link></div>
      </div>
      <h6 style="width:50px; font-family: 'Jua', sans-serif; font-size:12px; font-weight:bold; position:absolute; left:3px; top:66px;">게시판</h6>
      <h6 style="width:50px; font-family: 'Jua', sans-serif; font-size:12px; font-weight:bold; position:absolute; left:79px; top:66px;">매칭방</h6>
      <h6 style="width:50px; font-family: 'Jua', sans-serif; font-size:12px; font-weight:bold; position:absolute; left:153px; top:66px;">홈</h6>
      <h6 style="width:50px; font-family: 'Jua', sans-serif; font-size:12px; font-weight:bold; position:absolute; left:226px; top:66px;">알람</h6>
      <h6 style="width:50px; font-family: 'Jua', sans-serif; font-size:12px; font-weight:bold; position:absolute; left:298px; top:66px;">내정보</h6>
    </div>
</template>
<script>
export default {
    name: "Footer",
}
</script>
<style scoped>
#footer {
  padding: 10px;
  width: 355px;
}
.footer > img {
  width: 30px;
  height: 30px;
}
#line{
  width: 320px;
}
#nav1{
  margin-top:-9px;
  cursor:pointer;
}
#nav2{
  margin-top:-9px;
  cursor:pointer;
}
#nav3{
  margin-top:-9px;
  cursor:pointer;
}
#nav4{
  margin-top:-9px;
  cursor:pointer;
}
#nav5{
  width:30px;
  margin-top:-6px;
  cursor:pointer;
}

</style>