<template>
  <div id="safearea" style="width:375px; height:690px; padding:0px; font-family: 'Jua', sans-serif;">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <img id="nav2" src="@/img/background10.jpg" style="width:370px; height:560px; position:absolute; left:0px; top:60px;"/>
    <topnav></topnav> 

    <h4 style="font-size:22px; font-weight:bold; position:absolute; left:30px; top: 70px; ">My Info</h4>
    <div v-if="rankInfo ==true">
      <div>
        <img id="img" src="@/img/applicantdetail_1.png" style="position: absolute; left:-5px; top: 85px; width:390px; height:340px;"/>
        
        <h4 style="width:290px; text-align:center;  font-size:20px; font-weight:bold; position: absolute; left: 45px; top:130px; ">{{ this.item.rank[0].summonerName }}</h4>
        
        <div>
          <img id="img" src="@/img/heart1.png" style="position:absolute; width:18px; left:280px; top: 130px;"/>
          <h6 style=" font-size:20px;  position: absolute; left: 305px; top: 129px;">{{this.score}}</h6>
        </div>
        
        <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${user_rank}.png?image=q_auto&v=1595653530`" style="width:150px; position:absolute; left:115px; top: 155px;"/>
        
        <div v-if="this.report >= 50">
          <img  id="img" src="@/img/warning-stamp.png"  style="width:140px; position:absolute; left:120px; top: 190px;"/>
          <h6  style="font-family: 'Jua', sans-serif; background-color:white; color:red; font-size:12px;  position: absolute; left: 235px; top: 265px;">비매너 유저</h6>
          <h6 style="font-family: 'Jua', sans-serif; background-color:white; color:red; font-size:12px;  position: absolute; left: 235px; top: 250px;">신고율 : {{this.report}}%</h6>
        </div>
        
        
        <h4 style="width:290px; text-align:center;  font-size:20px; position: absolute; left:45px;top: 320px;">Level.{{ this.item.summonerLevel }} <strong>{{ user_rank }}</strong></h4>
        <h4 style="width:290px; text-align:center; font-size:20px; position: absolute; left:45px;top: 350px;">{{this.item.rank[0].wins}}승 {{this.item.rank[0].losses}}패  <strong>(승률 {{ this.winrate }}%)</strong></h4>
        
      </div>

      <div style="position:relative; margin-top:410px; width:0px;">
        <div style=" padding:0px; ">
          <img id="img" src="@/img/applicantdetail_1.png" style="position:absolute; left:5px; top:-17px; width:200px; height: 205px;"/>

          <div>
            <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[0].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 40px; height: 40px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:19px;"/>
            <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[1].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 40px; height: 40px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:65px;"/>
            <img id="img" :src="`https://opgg-static.akamaized.net/images/lol/champion/${this.matches.champion[2].name }.png?image=q_auto,w_140&v=1595653530`" style="width: 40px; height: 40px; border-radius: 70%; overflow: hidden; position:absolute; left:25px; top:112px;"/>
          </div>

          <div style="width:130px;  position:absolute; left:70px; top:20px; text-align:left">
            <h4 style=" font-size:14px; font-weight:bold; ">{{ this.matches.champion[0].name }}</h4>
            <h4  style=" font-size:12px; margin-top:-5px;">승률:{{this.matches.champion[0].winRate}}% / {{this.matches.champion[0].kda}}:1  </h4>

            <h4 style=" font-size:14px; font-weight:bold;  margin-top:15px;">{{ this.matches.champion[1].name }}</h4>
            <h4  style=" font-size:12px; margin-top:-5px;  margin-top:-5px;">승률:{{this.matches.champion[1].winRate}}% / {{this.matches.champion[1].kda}}:1  </h4>

            <h4 style=" font-size:14px; font-weight:bold;  margin-top:15px;">{{ this.matches.champion[2].name }}</h4>
            <h4  style=" font-size:12px; margin-top:-5px;  margin-top:-5px;">승률:{{this.matches.champion[2].winRate}}% / {{this.matches.champion[2].kda}}:1  </h4>
          </div>
        </div>
      </div>

      <div style="margin-top:-18px; margin-left:195px;  width:0px;">
        <div style="position:relative; padding:0px;">
          <img id="img" src="@/img/applicantdetail_1.png" style="position:absolute; top:-17px;width:175px; height: 204px;"/>
          <h4 style="width:110px; font-size:13px; font-weight:bold; position:absolute; left:30px; top:20px;" >선호 포지션(랭크)</h4>

          <div style="width:115px; height:120px; position:absolute; left:30px; top:40px;">
            <div>
              <div v-if="this.position1 == 'TOP'">
                <img id="img" src="@/img/position/Top.png" style="width: 50px; position:absolute; left:-15px; top:0px;"/>
              </div>
              <div v-if="this.position1 == 'MID'">
                <img id="img" src="@/img/position/Mid.png" style="width: 50px; position:absolute; left:-15px; top:0px;"/>
              </div>
              <div v-if="this.position1 == 'JUG'">
                <img id="img" src="@/img/position/Jug.png" style="width: 50px; position:absolute; left:-15px; top:0px;"/>
              </div>
              <div v-if="this.position1 == 'ADC'">
                <img id="img" src="@/img/position/Adc.png" style="width: 50px; position:absolute; left:-15px; top:0px;" />
              </div>
              <div v-if="this.position1 == 'SUP'">
                <img id="img" src="@/img/position/Sup.png" style="width: 50px; position:absolute; left:-15px; top:0px;"/>
              </div>

              <div style="width:95px;  position:absolute; left:37px; top:10px; text-align:left" >
                <h4 style=" font-size:13px; font-weight:bold; ">{{ this.position1 }}</h4>
                <h4 style=" font-size:11px; font-weight:bold; margin-top:-5px;"> {{this.matches.lane[0].pickRate}}%/승률 {{this.matches.lane[0].winRate}}%</h4>
              </div>
            </div>

            <div v-if="matches.lane[1] != null">
              <div>
                <div v-if="this.position2 == 'TOP'">
                  <img id="img" src="@/img/position/Top.png" style="width: 50px; position:absolute; left:-15px; top:65px;"/>
                </div>
                <div v-if="this.position2 == 'MID'">
                  <img id="img" src="@/img/position/Mid.png" style="width: 50px; position:absolute; left:-15px; top:65px;"/>
                </div>
                <div v-if="this.position2 == 'JUG'">
                  <img id="img" src="@/img/position/Jug.png" style="width: 50px; position:absolute; left:-15px; top:65px;"/>
                </div>
                <div v-if="this.position2 == 'ADC'">
                  <img id="img" src="@/img/position/Adc.png"  style="width: 50px; position:absolute; left:-15px; top:65px;"/>
                </div>
                <div v-if="this.position2 == 'SUP'">
                  <img id="img" src="@/img/position/Sup.png" style="width: 50px; position:absolute; left:-15px; top:65px;"/>
                </div>
              </div>

              <div style="width:95px;  position:absolute; left:37px; top:75px; text-align:left">
                <h4 style=" font-size:13px; font-weight:bold; ">{{ this.position2 }} </h4>
                <h4 style=" font-size:11px; font-weight:bold; margin-top:-5px;" >{{this.matches.lane[1].pickRate}}%/승률 {{this.matches.lane[1].winRate}}%</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div><!--end of rankInfo == true -->

    <div v-if="rankInfo ==false">
        <img id="img" src="@/img/applicantdetail_1.png" style="position: absolute; left:-5px; top: 80px; width:390px; height:340px;"/>
        <h4 style="width:290px; text-align:center;  font-size:25px; font-weight:bold; position:absolute; left:43px; top:130px;">{{ this.nickname }}</h4>
        <img src="@/img/unranked.png" style="width:140px; margin-top:30px; position:absolute; left:120px; top:120px; " />
        <h4 style="width:290px; text-align:center;  font-size:18px;  position:absolute; left:43px; top:300px;">Level.{{ this.item.summonerLevel }}</h4>
        <h4 style="width:290px; text-align:center;  font-size:22px; font-weight:bold; position:absolute; left:43px; top:320px;">Unranked</h4> 
        <h4 style="width:300px;  font-size:16px; font-weight:bold;  text-align:center; margin-top:20px; position:absolute; left:43px; top:330px;"> - 랭크정보가 없는 소환사입니다. -</h4>
        <h4 style="width:300px;  font-size:18px; font-weight:bold;  text-align:left; margin-top:20px; position:absolute; left:20px; top:380px;"> 배치고사를 완료해주세요.</h4>
        <h4 style="width:330px;  font-size:22px; font-weight:bold; color:#666666; text-align:center; position:absolute; left:20px; top:420px;"> · · · · · · · · · · · · · · · · · · · · · · · · ·</h4>
        <h4 style="width:300px;  font-size:14px; color:#666666; font-weight:bold;  text-align:left; margin-top:20px; position:absolute; left:20px; top:430px;"> 배치고사 이후 서비스 안내사항</h4>
        <h4 style="width:300px;  font-size:14px; color:#666666; text-align:left; margin-top:20px; position:absolute; left:20px; top:452px;"> 1.상대방에게 듀오신청 가능합니다.</h4>
        <h4 style="width:300px;  font-size:14px; color:#666666; text-align:left; margin-top:20px; position:absolute; left:20px; top:474px;"> 2.직접카드를 만들어 듀오를 구할 수 있습니다.</h4>
        <h4 style="width:300px;  font-size:14px; color:#666666; text-align:left; margin-top:20px; position:absolute; left:20px; top:496px;"> 3.자신의 랭크 정보를 볼 수 있습니다.</h4>
        <h4 style="width:330px; font-size:14px; color:#666666; text-align:left; margin-top:20px; position:absolute; left:20px; top:518px;"> 4.선호 포지션, 모스트 챔피언 탑3를 볼 수 있습니다.</h4>
    </div>




    <div>
      <button @click="logout()" style="width:80px; height:30px; margin-left:10px; font-weight:bold; background-color:#d9d9d9; position: absolute; left:250px; top: 70px;">LogOut</button>
      <h4 style="width:330px;font-size:14px; color:#666666; text-align:left; margin-top:20px; position:absolute; left:20px; top:575px;"> SSAFYGG를 더 이상 이용하지 않는다면</h4>
      <button @click="deleteUser()" style="width:160px; height:40px; margin-left:10px; font-weight:bold; color:#cc0000; position: absolute; left:220px; top: 583px;">회원탈퇴</button>
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
      host:"",//유저 상세정보를 확인하는 유저의 정보 - 매너점수, 신고, 메세지 전송에 사용,
      score:0, //host변수안 user_scoer와 user_match의 평점
      report:0,
      upHere : false,
      where:"",
      apiKey: "",
      nickname: "",
      rankInfo:true
    };
  },

  methods: {
    test() {
      if (confirm("test를 위한 confirm")) {
        console.log("yes");
      } else {
        console.log("no");
      }
    },
    logout() {
      this.$session.clear();
      this.$router.push("/");
      alert("정상적으로 로그아웃 되었습니다.");
    },
    deleteUser() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        axios.delete(`http://i3a401.p.ssafy.io:8399/api/user/delete?user_auth=` + this.host[0].user_auth + `&user_type=` + this.host[0].user_type)
        .then(({ data }) => {
          console.log(data);
          this.$session.clear();
          this.$router.push("/");
          alert("회원탈퇴가 정상적으로 처리되었습니다. 감사합니다.");
        });
      } 
    }
  },

  created() {
    this.nickname = this.$session.get("user_nickname");
    this.apiKey = this.$session.get("apiKey");

    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.nickname)
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

      axios.get(`https://i3a401.p.ssafy.io:443/api/riot/matches?apiKey=`+this.apiKey+`&summonerName=` +this.nickname) //선호포지션,모스트챔피언 정보
        .then(({ data }) => {
          this.matches = data;
          this.position1=this.matches.lane[0].role;
          if(this.matches.lane[1] != null){
            this.position2=this.matches.lane[1].role;
          }
      });
  
      axios.get("https://i3a401.p.ssafy.io:443/api/user/select2", {params: {user_nickname: this.nickname}})
      .then(({ data }) => {
        this.host = data;
        if(this.host[0].user_match ==0) {
          this.score=3;
        }else{
          this.score=this.host[0].user_score/this.host[0].user_match;
          this.score=this.score.toFixed(2);
          this.report=(this.host[0].user_report/this.host[0].user_match)*100;
          this.report=this.report.toFixed(1);
        }
      });

    })
    .catch(error => {
      console.log(error);
      this.rankInfo=false;
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