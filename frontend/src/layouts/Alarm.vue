<template>
  <div id="safearea" style="width:375px; height:690px;  padding:0px">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav></topnav> 

    <!-- Alarm 내용 -->

    <div v-if="Alarms[0] != null" style="margin-left:5px;">
      
      <v-card class="mx-auto" max-width="375">
      <img id="nav2" src="@/img/background10.jpg" style="width:359px; height:570px; position:absolute; left:0px; top:0px;"/>
      <v-virtual-scroll :items="Alarms" :item-height="80" :max-width="375" height="567">
        <template v-slot="{ item }">
          <!-- <img id="nav2" src="@/img/background10.jpg" style="width:340px; height:400px; position:absolute; left:0px; top:-00px;"/> -->
          <div @click="AlarmClick(item.alarm_body,item.alarm_from,item.card_no,item.alarm_no)" >
            <div style="width:0px; margin-left:-6px; padding:0px; position:relative; ">
              <img id="img" src="@/img/applicantdetail_2.png" style="width:350px; margin-left : 12px;height:80px;position:absolute;"/>
              <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${item.alarm_title}.png?image=q_auto&v=1595653530`" style="width:30px; position:absolute; left:20px; top:28px;"/>
              <h2 style="webkit-border-radius: 20px 10px; background-color:#FFFFFF; width:290px; height:20px;  font-family: 'Jua', sans-serif; font-size:11.5px; font-weight:bold; position:absolute; left:55px; top:38px;  text-align: left;">
                {{ item.alarm_body}}
              </h2>
              <img v-if="item.alarm_read == 0" id="img" src="@/img/circle.png" style="width:11px; position:absolute; left:340px; top:25px;"/>
            </div>
          </div>
        
        </template>
      </v-virtual-scroll>
      </v-card>
    </div>
    
    <div v-if="Alarms[0] == null" style="width:250px; height: 120px; position:absolute; left:50px; top:100px;">
      <img id="nav2" src="@/img/background10.jpg" style="width:350px; height:550px; position:absolute; left:-50px; top:-40px;"/>
      <h2 style="width:300px;  font-family: 'Jua', sans-serif; font-size:18px; font-weight:bold; position:absolute; left:0px; top:0px;"> 받은 알람이 없습니다.</h2>
    </div>

    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer2.vue";

export default {
  name: "Alarm",
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      Alarms: [],
      apiKey:this.$session.get("apiKey"),
    };
  },
  created() {
    axios.get("https://i3a401.p.ssafy.io:443/api/alarm/selectAll?alarm_to=" +this.$session.get("user_nickname"))
      .then(({ data }) => {
        this.Alarms = data;
      });
  },
  methods: {
    AlarmClick(alarm_body,alarm_from,card_no,alarm_no) {
      this.alarm_body=alarm_body;
      this.alarm_from=alarm_from;
      this.card_no=card_no;
      this.alarm_no=alarm_no;
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      if(this.alarm_body.includes('요청')){
        axios.put("https://i3a401.p.ssafy.io:443/api/alarm/update?alarm_no=" + this.alarm_no)
          .then(() => {
            alert(this.alarm_from+"님이 보낸 알람을 확인합니다.");
            this.$router.push("/userdetail?card_host=" + this.alarm_from + "&card_no=" + this.card_no+"&where=alarm");
          });
      }
      else if(this.alarm_body.includes('시작')){

         axios.put("https://i3a401.p.ssafy.io:443/api/alarm/update?alarm_no=" + this.alarm_no)
          .then(() => {
            this.$session.set("card_no", this.card_no);
            this.$router.push("/applicant");
          });
        
      }
      else if(this.alarm_body.includes('거절')){
        axios.put("https://i3a401.p.ssafy.io:443/api/alarm/update?alarm_no=" + this.alarm_no)
          .then(() => {
            alert("요청하신 듀오신청이 거절되었습니다.");
          });
        
      }

    },
    goCardRegist(){
      if(this.$session.get("card_no") != null){
        alert("이미 만든 카드가 있습니다.");
      }else{
        this.$router.push("/cardregist");
      }
    },
    back(){
      this.$router.go(-1);
    }
  }
};
</script>
<style>
#safearea {
  margin: 0 auto;
  width: 375px;
  height: 690px;
  padding: 0;
  position: relative;
  text-align: center;
}

</style>