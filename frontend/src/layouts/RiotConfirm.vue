<template>
  <div id="safearea">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <div id="GG"><img src="@/img/GG.png" /></div>

    <div id="confirm1">
      <div id="confirm1Text">
        <h4>본인 계정이 맞습니까?</h4>
      </div>
    </div>

    <div id="confirm2">
      <div id="confirm2Text" class="d-flex flex-column">
        <h4 style="margin-top:25px; margin-left:100px; width:290px; text-align:center;  font-size:25px; font-weight:bold; ">{{this.user.user_nickname }}</h4>
      </div>
      <div v-if="this.item.rank[0] != null" id="rank">
        <img class="rank" :src="rank_adress" />
      </div>
      <div  v-if="this.item.rank[0] != null" id="confirm2Text2" class="d-flex flex-column" style="margin-left:105px;">
        <h4 style="width:290px; text-align:center;  font-size:25px; ">Level.{{ this.item.summonerLevel }} <strong>{{ this.rank }}</strong></h4>
        <h4 style="width:290px; text-align:center;  font-size:25px; font-weight:bold;">{{this.item.rank[0].wins}}승 {{this.item.rank[0].losses}}패</h4>
        <h4 style="width:290px; text-align:center;  font-size:25px; font-weight:bold;">[승률 {{ this.winrate }}]</h4>
      </div>

      <div v-if="this.item.rank[0] == null" id="rank">
        <img class="rank" src="@/img/unranked.png" style="width:200px; margin-top:30px;" />
      </div>
      <div  v-if="this.item.rank[0] == null" id="confirm2Text2" class="d-flex flex-column" style="margin-left:105px;">
        <h4 style="width:290px; text-align:center;  font-size:25px;">Level.{{ this.item.summonerLevel }}</h4>
        <h4 style="width:290px; text-align:center;  font-size:25px; font-weight:bold;">Unranked</h4> 
        <h4 style="width:300px;  font-size:16px; font-weight:bold;  text-align:center; margin-top:20px; "> - 랭크정보가 없는 소환사입니다. -</h4>
      </div>

    </div>

    <div id="btns" class="d-flex justify-content-center">
      <div><button id="btn1" @click="cancle">취소</button></div>
      <div class="blank"></div>
      <div><button id="btn2" @click="insert">확인</button></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data: function() {
    return {
      rank: "",
      winrate: "",
      rank_adress: "",
      item: "",
      user: {
        user_auth: this.$session.get("user_auth"),
        user_match: 0,
        user_nickname: "",
        user_score: 0,
        user_report:0,
        user_token: this.$session.get("user_token"),
        user_type: this.$session.get("user_type")
      },
      apiKey: "",
    };
  },
  methods: {
    cancle() {
      this.$router.push("/riot");
    },
    insert() {
      axios
        .post("https://i3a401.p.ssafy.io:443/api/user/insert", this.user)
        .then(() => {
          alert("저장완료");
          this.$router.push("/home");
        });
    }
  },
  created() {
    this.apiKey=this.$session.get("apiKey");
    const params = new URL(document.location).searchParams;
    this.user.user_nickname = params.get("user_nickname");
    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +params.get("user_nickname"))
      .then(({ data }) => {
        this.item = data;
        if(this.item.rank[0]!=null){
          if (this.item.rank[0].rank == "I") {
            this.rank = this.item.rank[0].tier + "_1";
          } else if (this.item.rank[0].rank == "II") {
            this.rank = this.item.rank[0].tier + "_2";
          } else if (this.item.rank[0].rank == "III") {
            this.rank = this.item.rank[0].tier + "_3";
          } else if (this.item.rank[0].rank == "IV") {
            this.rank = this.item.rank[0].tier + "_4";
          }
          this.rank_adress ="https://opgg-static.akamaized.net/images/medals/" +this.rank +".png?image=q_auto&v=1";
          this.winrate=this.item.rank[0].wins / (this.item.rank[0].wins+this.item.rank[0].losses)*100;
          this.winrate=this.winrate.toFixed(2);
          this.winrate=this.winrate+"%"
        }
        
      })
      .catch(error => {
        console.log(error);
        alert("소환사정보가 없습니다.");
        this.$router.push("/riot");
      });
    
  }
};
</script>

<style scoped>
#safearea {
  margin: 0 auto;
  min-width: 375px;
  height: 690px;
  padding: 0;
  position: relative;
  text-align: center;
  font-family:'Jua',sans-serif;
}
#GG {
  padding: 0;
  margin-top: 40px;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0);
}
#GG > img {
  width: 115px;
  height: 60px;
}
#confirm1 {
  justify-content: center;
  margin-top: 20px;
  height: 40px;
  background-image: url("../img/riotconfirm_1.png");
  background-repeat: no-repeat;
  background-size: 380px 100%;
  background-position-x: center;
}
#confirm1Text {
  padding-top: 5px;
  font-size: 20px;
  font-weight: bold;
}
#confirm2 {
  justify-content: center;
  margin: 0 auto;
  min-width: 500px;
  width: 80vw;
  max-width: 500px;
  height: 470px;
  margin-left:-60px;
  background-image: url("../img/riotconfirm_2.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  background-position-x: center;
}
#confirm2Text {
  padding-top: 50px;
  font-size: 20px;
  font-weight: bold;
  background-color: rgba(0, 0, 0, 0);
}
.rank {
  transform: translateY(-35px);
  width: 200px;
  margin-top:20px;
}
#confirm2Text2 {
  font-size: 20px;
  font-weight: bold;
  padding-top: 10px;
  transform: translateY(-40px);
  background-color: rgba(0, 0, 0, 0);
}
.progress {
  margin: 0 auto;
  min-width: 280px;
  width: 60vw;
  max-width: 380px;
  height: 20px;
  transform: translateY(-38px);
}
#btns {
  margin: 0 auto;
  min-width: 300px;
  max-width: 1000px;
  background-color: rgba(0, 0, 0, 0);
}
#btn1 {
  min-width: 150px;
  width: 25vw;
  max-width: 180px;
  height: 40px;
  background-color: #808080;
  color: #ffffff;
}
.blank {
  width: 10vw;
  max-width: 70px;
}
#btn2 {
  min-width: 150px;
  width: 25vw;
  max-width: 180px;
  height: 40px;
  background-color: #101010;
  color: #ffffff;
}
</style>
