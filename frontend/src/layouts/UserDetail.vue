<template>
  <div id="safearea" style="width:375px; height:690px; padding:0px">
    
    <topnav></topnav> 
    <img id="nav2" src="@/img/background10.jpg" style="width:370px; height:560px; position:absolute; left:0px; top:60px;"/>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">

    <h4 style="font-family: 'Jua', sans-serif; width:300px; font-family: 'Jua', sans-serif; color:#666666; font-size:14px;   text-align:left; position:absolute; left:20px; top:75px;"> *최근전적 10게임의 통계입니다.</h4>

    <div>
      <img id="img" src="@/img/applicantdetail_1.png" style="position: absolute; left:-5px; top: 75px; width:390px; height:340px;"/>
      
      <h4 style="font-family:'Jua',sans-serif; width:290px; text-align:center;  font-size:20px; font-weight:bold; position: absolute; left: 45px; top: 140px; ">{{ this.item.rank[0].summonerName }}</h4>
      
      <div>
        <img id="img" src="@/img/heart1.png" style="position:absolute; width:18px; left:290px; top: 140px;"/>
        <h6 style="font-family: 'Jua', sans-serif; font-size:18px;  position: absolute; left: 310px; top: 140px;">{{this.score}}</h6>
      </div>
      <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${user_rank}.png?image=q_auto&v=1595653530`" style="width:140px; position:absolute; left:120px; top: 160px;"/>
      
      
      <div v-if="this.report >= 50">
        <img  id="img" src="@/img/warning-stamp.png"  style="width:140px; position:absolute; left:120px; top: 190px;"/>
        <h6  style="font-family: 'Jua', sans-serif; background-color:white; color:red; font-size:12px;  position: absolute; left: 235px; top: 265px;">비매너 유저</h6>
         <h6 style="font-family: 'Jua', sans-serif; background-color:white; color:red; font-size:12px;  position: absolute; left: 235px; top: 250px;">신고율 : {{this.report}}%</h6>
      </div>
      
      
      <h4 style="width:290px; text-align:center; font-family: 'Jua', sans-serif; font-size:20px;  position: absolute; left:45px;top: 305px;">Level.{{ this.item.summonerLevel}} <strong>{{ user_rank }}</strong></h4>
      <h4 style="width:290px; text-align:center; font-family: 'Jua', sans-serif; font-size:20px; position: absolute; left:45px;top: 335px;"><strong>{{this.item.rank[0].wins}}승 {{this.item.rank[0].losses}}패</strong>  (승률 {{ this.winrate }}%)</h4>
    </div>

    <div style="position:relative; margin-top:380px; width:0px;">
      <div style=" padding:0px; ">
        <img id="img" src="@/img/applicantdetail_1.png" style="position:absolute; left:5px; top:-15px; width:200px; height: 190px;"/>

        <div>
          <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[0].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 35px; height: 35px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:19px;"/>
          <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[1].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 35px; height: 35px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:63px;"/>
          <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[2].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 35px; height: 35px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:107px;"/>
        </div>

        <div style="width:130px;  position:absolute; left:70px; top:18px; text-align:left">
          <h4 style="font-family: 'Jua', sans-serif; font-size:14px; font-weight:bold; ">{{ this.matches.champion[0].name }}</h4>
          <h4  style="font-family: 'Jua', sans-serif; font-size:12px; margin-top:-5px;">승률:{{this.matches.champion[0].winRate}}% / {{this.matches.champion[0].kda}}:1  </h4>

          <h4 style="font-family: 'Jua', sans-serif; font-size:14px; font-weight:bold;  margin-top:12px;">{{ this.matches.champion[1].name }}</h4>
          <h4  style="font-family: 'Jua', sans-serif; font-size:12px; margin-top:-5px;  margin-top:-5px;">승률:{{this.matches.champion[1].winRate}}% / {{this.matches.champion[1].kda}}:1  </h4>

          <h4 style="font-family: 'Jua', sans-serif; font-size:14px; font-weight:bold;  margin-top:10px;">{{ this.matches.champion[2].name }}</h4>
          <h4  style="font-family: 'Jua', sans-serif; font-size:12px; margin-top:-5px;  margin-top:-5px;">승률:{{this.matches.champion[2].winRate}}% / {{this.matches.champion[2].kda}}:1  </h4>
        </div>
      </div>
    </div>

    <div style="margin-top:-15px; margin-left:195px;  width:0px;">
      <div style="position:relative; padding:0px;">
        <img id="img" src="@/img/applicantdetail_1.png" style="position:absolute; width:175px; height: 189px;"/>
        <h4 style="width:110px;font-family: 'Jua', sans-serif; font-size:13px; font-weight:bold; position:absolute; left:30px; top:40px;" >선호 포지션(랭크)</h4>

        <div style="width:115px; height:120px; position:absolute; left:30px; top:60px;">
          <div>
            <div v-if="this.position1 == 'TOP'">
              <img id="img" src="@/img/position/Top.png" style="width: 40px; position:absolute; left:-10px; top:0px;"/>
            </div>
            <div v-if="this.position1 == 'MID'">
              <img id="img" src="@/img/position/Mid.png" style="width: 40px; position:absolute; left:-10px; top:0px;"/>
            </div>
            <div v-if="this.position1 == 'JUG'">
              <img id="img" src="@/img/position/Jug.png" style="width: 40px; position:absolute; left:-10px; top:0px;"/>
            </div>
            <div v-if="this.position1 == 'ADC'">
              <img id="img" src="@/img/position/Adc.png" style="width: 40px; position:absolute; left:-10px; top:0px;" />
            </div>
            <div v-if="this.position1 == 'SUP'">
              <img id="img" src="@/img/position/Sup.png" style="width: 40px; position:absolute; left:-10px; top:0px;"/>
            </div>

            <div style="width:95px;  position:absolute; left:37px; top:5px; text-align:left" >
              <h4 style="font-family: 'Jua', sans-serif; font-size:13px; font-weight:bold; ">{{ this.position1 }}</h4>
              <h4 style="font-family: 'Jua', sans-serif; font-size:11px; font-weight:bold; margin-top:-5px;"> {{this.matches.lane[0].pickRate}}%/승률 {{this.matches.lane[0].winRate}}%</h4>
            </div>
          </div>

          <div v-if="matches.lane[1] != null">
            <div>
              <div v-if="this.position2 == 'TOP'">
                <img id="img" src="@/img/position/Top.png" style="width: 40px; position:absolute; left:-10px; top:55px;"/>
              </div>
              <div v-if="this.position2 == 'MID'">
                <img id="img" src="@/img/position/Mid.png" style="width: 40px; position:absolute; left:-10px; top:55px;"/>
              </div>
              <div v-if="this.position2 == 'JUG'">
                <img id="img" src="@/img/position/Jug.png" style="width: 40px; position:absolute; left:-10px; top:55px;"/>
              </div>
              <div v-if="this.position2 == 'ADC'">
                <img id="img" src="@/img/position/Adc.png"  style="width: 40px; position:absolute; left:-10px; top:55px;"/>
              </div>
              <div v-if="this.position2 == 'SUP'">
                <img id="img" src="@/img/position/Sup.png" style="width: 40px; position:absolute; left:-10px; top:55px;"/>
              </div>
            </div>

            <div style="width:95px;  position:absolute; left:37px; top:60px; text-align:left">
              <h4 style="font-family: 'Jua', sans-serif; font-size:13px; font-weight:bold; ">{{ this.position2 }} </h4>
              <h4 style="font-family: 'Jua', sans-serif; font-size:11px; font-weight:bold; margin-top:-5px;" >{{this.matches.lane[1].pickRate}}%/승률 {{this.matches.lane[1].winRate}}%</h4>
            </div>
          </div>
        </div>


        
      </div>
    </div>

    <div v-if="where == null">
      <button @click="cancle" style="font-family: 'Jua', sans-serif;width:160px; height:40px; margin-left:10px; background-color:#808080; color:#FFFFFF; position: absolute; left:9px; top: 570px;">취소</button>
      <button @click="handler" style="font-family: 'Jua', sans-serif;width:160px; height:40px; margin-left:10px; background-color:#101010; color:#FFFFFF; position: absolute; left:184px; top: 570px;">신청</button>
    </div>

    <div v-if="where =='alarm'">
      <button @click="goApplicant" style="font-family: 'Jua', sans-serif;width:320px; height:40px; margin-left:10px; background-color:#101010; color:#FFFFFF; position: absolute; left:18px; top: 570px;">매칭방 설정으로 이동</button>
    </div>

    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer.vue";


export default {
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      position1: "",
      position2: "", //Top,Jug,Mid,Adc,Sup
      item:"",
      matches:"",
      user_rank:"",
      alarm: {
        alarm_body:this.$session.get("user_nickname") + "님이 듀오를 요청합니다.",
        alarm_from: this.$session.get("user_nickname"), //발신
        //alarm_no: 0,
        alarm_read: 0,
        alarm_title: "",
        alarm_to: "", //수신
        card_no: 0
      },
      host:"",//유저 상세정보를 확인하는 유저의 정보 - 매너점수, 신고, 메세지 전송에 사용,
      score:0, //host변수안 user_scoer와 user_match의 평점
      report:0,
      upHere : false,
      card_host_alarm:"",
      sendAlarm:false,
      where:"",
      apiKey:this.$session.get("apiKey"),
      Matching_Possible:true
    };
  },
  methods: {
    cancle() {
      this.$router.push("/home");
    },
    handler() {
      if(this.Matching_Possible==false){
        alert("랭크정보가 없습니다. 배치고사를 완료해주세요.");
      }
      else if(this.$session.get("card_no") != null){
        alert("이미 만들어진 매칭방이 있습니다. 매칭 상태에서는 듀오신청이 불가능합니다!");
      }else{

        axios.get("https://i3a401.p.ssafy.io:443/api/alarm/selectAllAlarm")
        .then(({ data }) => {
          this.card_host_alarm = data;
          for(var i=0; i<this.card_host_alarm.length; i++){
            if(this.card_host_alarm[i].alarm_body == this.alarm.alarm_body){
              this.sendAlarm=true;
              break;
            }
          }
          if(this.sendAlarm == false){

            axios.post("https://i3a401.p.ssafy.io:443/api/alarm/insert", this.alarm)
            .then(() => {
              var key ="AAAA9uf5aY4:APA91bE-yYBS6gkcTWHH2JNvIjtBABg2KvnQdSeUi-5g-xbyhCz39VOPr8pgA_BB_WVd8Fm9SJ7kgsUFshji9Ujpmb5pQQGnysFFjVBPgA1qQq7vk20RGLzmsJeETiDdSpJQpzI_Cfhb";
              var to = this.host[0].user_token;
              var notification = {
                title: "듀오 신청!!",
                body: this.alarm.alarm_body,
                icon: "firebase-logo.png",
                click_action: "https://i3a401.p.ssafy.io"
              };
    
              fetch("https://fcm.googleapis.com/fcm/send", {
                method: "POST",
                headers: {
                  Authorization: "key=" + key,
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({
                  notification: notification,
                  to: to
                })
                })
                .then(function(response) {
                  console.log(response);
                })
                .catch(function(error) {
                  console.error(error);
                });
    
              alert("알람을 보냈습니다.");
              this.$router.push("/home");
            });
          }else alert("이미 듀오신청하였습니다.");
        });
      }
    },
    goApplicant(){
      this.$router.push("/applicant");
    },
    back(){
      this.$router.go(-1);
    },

  },
  created() {

    //알람신청 부분
    const params = new URL(document.location).searchParams;
    this.alarm.alarm_to = params.get("card_host");
    this.alarm.card_no = params.get("card_no");
    this.where = params.get("where");
    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.alarm.alarm_to)
      .then(({ data }) => {
        this.item = data;
        if (this.item.rank[0].rank == "I") {
          this.user_rank = this.item.rank[0].tier + "_1";
        } else if (this.item.rank[0].rank == "II") {
          this.user_rank = this.item.rank[0].tier + "_2";
        } else if (this.item.rank[0].rank == "III") {
          this.user_rank = this.item.rank[0].tier + "_3";
        } else if (this.item.rank[0].rank == "IV") {
          this.user_rank = this.item.rank[0].tier + "_4";
        }     
        this.winrate=this.item.rank[0].wins / (this.item.rank[0].wins+this.item.rank[0].losses)*100;
        this.winrate=this.winrate.toFixed(2);
    });

    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.$session.get("user_nickname"))
      .then(({ data }) => {
        if (data.rank[0].rank == "I") {
          this.alarm.alarm_title = data.rank[0].tier + "_1";
        } else if (data.rank[0].rank == "II") {
          this.alarm.alarm_title = data.rank[0].tier + "_2";
        } else if (data.rank[0].rank == "III") {
          this.alarm.alarm_title = data.rank[0].tier + "_3";
        } else if (data.rank[0].rank == "IV") {
          this.alarm.alarm_title = data.rank[0].tier + "_4";
        }     
      }).catch(error => {
        console.log(error);
        this.Matching_Possible=false;
      });


    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/matches?apiKey=`+this.apiKey+`&summonerName=` +this.alarm.alarm_to)
      .then(({ data }) => {
        this.matches = data;
        this.position1=this.matches.lane[0].role;
        if(this.matches.lane[1] != null){
          this.position2=this.matches.lane[1].role;
        }
    });

    axios.get("https://i3a401.p.ssafy.io:443/api/user/select2", {
      params: {
         user_nickname: this.alarm.alarm_to
      }
    })
    .then(({ data }) => {
      this.host = data;
      if(this.host[0].user_match ==0){
        this.score=3;
      }else{
        this.score=this.host[0].user_score/this.host[0].user_match;
        this.score=this.score.toFixed(2);
        this.report=(this.host[0].user_report/this.host[0].user_match)*100;
        this.report=this.report.toFixed(1);
      }

    });
    
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