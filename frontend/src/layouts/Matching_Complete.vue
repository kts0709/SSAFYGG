<template>
  <div id="safearea" style="width:375px; height:690px;  padding:0px; font-family: 'Jua', sans-serif;">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <img id="nav2" src="@/img/background10.jpg" style="width:370px; height:560px; position:absolute; left:0px; top:60px;"/>
    <topnav></topnav>
    
    <h4 style="width:380px;  font-size:24px; font-weight:bold;  text-align:center; position:absolute; left:0px; top:100px;"> 방장 | {{card.card_host}}</h4>

    <!-- 접속자가 게스트일 경우 -->
    <div v-if="$session.get('user_nickname') != card.card_host" style="width:0px;  margin-top:100px; margin-left:0px;">
      <div style="width:0px; padding:0px; position:relative; ">
        <img id="img" src="@/img/applicantdetail_1.png" style="width:380px; height:400px; position:absolute;"/>
        <h4 style="width:300px;  font-size:16px; font-weight:bold;  text-align:center; position:absolute; left:35px; top:60px;"> 아이디를 복사하여 친구추가 해주세요</h4>
        <h4 style="width:300px;  font-size:22px; font-weight:bold; color:#666666; text-align:center; position:absolute; left:35px; top:75px;"> · · · · · · · · · · · · · · · · · · · · · ·</h4>

        <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${host_rank}.png?image=q_auto&v=1595653530`" style="width:65px; position:absolute; left:35px; top:95px;"/>
        <h2 style="text-align:left; width:200px; font-size:25px; font-weight:bold; position:absolute; left:100px; top:117px;">{{card.card_host}}</h2>
       
      <button @click="copy(card.card_host)">
        <div style="width:140px; position:absolute; left:30px; top:170px; padding:0px;">
          <img id="img" src="@/img/Copy.png" style="width:45px;"/>
          <span style="color:black; font-weight:bold; font-size:16px;">아이디 복사</span>
          <!-- <input type="text" value="webisfree.com" onclick="this.select(); document.execCommand('copy'); alert('Copied')" /> -->
        </div>
      </button>
        <div  @click="addReport()" style="cursor:pointer; width:120px; position:absolute; left:210px; top:175px;  padding:0px;">
          <img id="img" src="@/img/Report.png" style="width:40px; margin-top:-5px;"/>
          <span style="color:red;  font-weight:bold; font-size:16px;">트롤 신고</span>
        </div>

        <h4 style="width:300px;  font-size:14px; font-weight:bold; color:#666666; text-align:center; position:absolute; left:35px; top:235px;"> · · · · · · 상대방의 매너는 몇점인가요? · · · · · ·</h4>
        <div class="star-box" style="padding:0px; width:310px; position:absolute; left:30px; top:250px; margin-bottom:0px;"> 
            <span :class="star.star1_left" @click="onstar1()"></span>
            <span :class="star.star1_right" @click="onstar1"></span>
            <span :class="star.star2_left" @click="onstar2"></span>
            <span :class="star.star2_right" @click="onstar2"></span>
            <span :class="star.star3_left" @click="onstar3"></span>
            <span :class="star.star3_right" @click="onstar3"></span>
            <span :class="star.star4_left" @click="onstar4"></span>
            <span :class="star.star4_right" @click="onstar4"></span>
            <span :class="star.star5_left" @click="onstar5"></span>
            <span :class="star.star5_right" @click="onstar5"></span>  
        </div>
        <button @click="addScore" style="width:100px; height:25px; background-color:#101010; color:#FFFFFF; position:absolute; left:140px; top:310px;"> 평가하기</button>
      </div>
    </div>





    <!-- 접속자가 호스트일 경우 -->
    <div v-if="$session.get('user_nickname') == card.card_host" style="width:0px;  margin-top:100px; margin-left:5px;">
      <div style="width:0px;  margin-left:-5px; padding:0px; position:relative; ">
        <img id="img" src="@/img/applicantdetail_1.png" style="width:380px; height:400px; position:absolute;"/>
        <h4 style="width:300px; font-size:16px; font-weight:bold;  text-align:center; position:absolute; left:35px; top:60px;"> 아이디를 복사하여 친구추가 해주세요</h4>
        <h4 style="width:300px;  font-size:22px; font-weight:bold; color:#666666; text-align:center; position:absolute; left:35px; top:75px;"> · · · · · · · · · · · · · · · · · · · · · ·</h4>

        <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${guest_rank}.png?image=q_auto&v=1595653530`" style="width:65px; position:absolute; left:35px; top:95px;"/>
        <h2 style="text-align:left; width:200px;  font-size:25px; font-weight:bold; position:absolute; left:100px; top:117px;">{{card.card_guest}}</h2>
       
       <button @click="copy(card.card_guest)">
        <div style="width:140px; position:absolute; left:30px; top:170px; padding:0px;">
          <img id="img" src="@/img/Copy.png" style="width:45px;"/>
          <span style="color:black; font-weight:bold; font-size:16px;">아이디 복사</span>
          <!-- <input type="text" value="14김태상" onclick="this.select(); document.execCommand('copy'); alert('Copied')" style="width:80px;color:black; font-family:나눔고딕; font-weight:bold; font-size:16px;" readonly/> -->
        </div>
       </button>
        <div @click="addReport()" style="cursor:pointer; width:120px; position:absolute; left:210px; top:175px;  padding:0px;">
          <img id="img" src="@/img/Report.png" style="width:40px; margin-top:-5px;"/>
          <span style="color:red;  font-weight:bold; font-size:16px;">트롤 신고</span>
        </div>

        <h4 style="width:300px; font-size:14px; font-weight:bold; color:#666666; text-align:center; position:absolute; left:35px; top:235px;"> · · · · · · 상대방의 매너는 몇점인가요? · · · · · ·</h4>
        <div class="star-box" style="padding:0px; width:310px; position:absolute; left:30px; top:250px; margin-bottom:0px;">
            <span :class="star.star1_left" @click="onstar1()"></span>
            <span :class="star.star1_right" @click="onstar1"></span>
            <span :class="star.star2_left" @click="onstar2"></span>
            <span :class="star.star2_right" @click="onstar2"></span>
            <span :class="star.star3_left" @click="onstar3"></span>
            <span :class="star.star3_right" @click="onstar3"></span>
            <span :class="star.star4_left" @click="onstar4"></span>
            <span :class="star.star4_right" @click="onstar4"></span>
            <span :class="star.star5_left" @click="onstar5"></span>
            <span :class="star.star5_right" @click="onstar5"></span>
        </div>
        <button @click="addScore" style="width:100px; height:25px; background-color:#101010; color:#FFFFFF; position:absolute; left:140px; top:310px;"> 평가하기</button>
      </div>
    </div>







    <button @click="gohome" style="width:160px; height:40px; margin-left:5px; background-color:#101010; color:#FFFFFF; position:absolute; left:105px; top:530px;"> 나가기</button>

    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer.vue";

export default {
  name: "Matching_Complete",
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      card:"",
      host:"",
      guest:"",
      user_host:"",
      user_guest:"",
      host_rank:"",
      guest_rank:"",
      score:3,
      addscore:false,
      star: {
        star1_left: "star star_left on",
        star1_right: "star star_right on",
        star2_left: "star star_left on",
        star2_right: "star star_right on",
        star3_left: "star star_left on",
        star3_right: "star star_right on",
        star4_left: "star star_left",
        star4_right: "star star_right",
        star5_left: "star star_left",
        star5_right: "star star_right"
      },
      apiKey:this.$session.get("apiKey"),
      msg:""

    };
  },
  created() {

    
    
    axios.get(`https://i3a401.p.ssafy.io:443/api/card/select?card_no=`+this.$session.get("card_no"))//카드 정보 받아오기
      .then(({ data }) => {
        this.card = data;

        if(this.card.card_active == true){
          this.$router.push("/applicant");
        }

        //호스트의 소환사 정보 받아오기
        axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` + this.card.card_host) 
          .then(({ data }) => {
            this.host = data;
            if (this.host.rank[0].rank == "I") {
              this.host_rank = this.host.rank[0].tier + "_1";
            } else if (this.host.rank[0].rank == "II") {
              this.host_rank = this.host.rank[0].tier + "_2";
            } else if (this.host.rank[0].rank == "III") {
              this.host_rank = this.host.rank[0].tier + "_3";
            } else if (this.host.rank[0].rank == "IV") {
              this.host_rank = this.host.rank[0].tier + "_4";
            }        
        });
        
        //게스트의 소환사 정보 받아오기
        axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.card.card_guest)
          .then(({ data }) => {
            this.guest = data;
            if (this.guest.rank[0].rank == "I") {
              this.guest_rank = this.guest.rank[0].tier + "_1";
            } else if (this.guest.rank[0].rank == "II") {
              this.guest_rank = this.guest.rank[0].tier + "_2";
            } else if (this.guest.rank[0].rank == "III") {
              this.guest_rank = this.guest.rank[0].tier + "_3";
            } else if (this.guest.rank[0].rank == "IV") {
              this.guest_rank = this.guest.rank[0].tier + "_4";
            }        
        });

        //호스트의 유저정보 받아오기
        axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname="+this.card.card_host)
          .then(({ data }) => {
            this.user_host = data;
        });

        //게스트의 유저정보 받아오기
        axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname="+this.card.card_guest)
          .then(({ data }) => {
            this.user_guest = data;
        });


      });

    

  },
  methods: {
    back(){
      this.$router.go(-1);
    },
    copy(tmp){
      var dummy = document.createElement("textarea");
      document.body.appendChild(dummy);
      dummy.value = tmp;
      dummy.select();
      document.execCommand("copy");
      document.body.removeChild(dummy);
      alert("아이디를 복사하였습니다. 친구 추가해주세요!!");
    },
    gohome() {//해당인원 점수 기본 3점으로 업데이트하고 매칭 수 +1로 늘리기 두명다

          this.user_guest[0].user_score=this.user_guest[0].user_score + 3;
          this.user_guest[0].user_match=this.user_guest[0].user_match +1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_guest[0])
          .then(() => {
            this.msg="평가완료";
          });
        
          this.user_host[0].user_score=this.user_host[0].user_score+3;
          this.user_host[0].user_match=this.user_host[0].user_match +1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_host[0])
          .then(() => {
            this.msg="평가완료";
          });

          axios.delete(`https://i3a401.p.ssafy.io:443/api/card/delete?card_no=`+this.$session.get("card_no"))
          .then(({ data }) => {
            let msg = "이용해주셔서 감사합니다.";
            if (data === "success") {
              this.$session.remove("card_no");
              msg = "카드삭제 완료되었습니다.";
            }
            alert(msg);
            this.$session.remove("addscore")//평가여부 초기화
            this.$session.remove("addReport")
            this.$router.push("/home");
          });

    },
    addScore(){ //해당 인원 점수 업데이트하기

      if(this.$session.get("addscore") == null){
        if(this.$session.get('user_nickname') == this.card.card_host){
          this.user_guest[0].user_score=this.user_guest[0].user_score + this.score;
          this.user_guest[0].user_match=this.user_guest[0].user_match +1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_guest[0])
          .then(() => {
            alert("평가완료하였습니다");
          });
        }else{
          this.user_host[0].user_score=this.user_host[0].user_score+this.score;
          this.user_host[0].user_match=this.user_host[0].user_match +1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_host[0])
          .then(() => {
            alert("평가완료하였습니다");
          });
        }
        this.$session.set("addscore","true")
      }else if(this.$session.get("addscore") != null){
        alert("이미 평가한 상대입니다.");
      }


    },
     onstar1() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left";
      this.star.star2_right = "star star_right";
      this.star.star3_left = "star star_left";
      this.star.star3_right = "star star_right";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.score=1;
    },
    onstar2() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left";
      this.star.star3_right = "star star_right";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.score=2;
    },
    onstar3() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.score=3;
    },
    onstar4() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left on";
      this.star.star4_right = "star star_right on";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.score=4;
    },
    onstar5() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left on";
      this.star.star4_right = "star star_right on";
      this.star.star5_left = "star star_left on";
      this.star.star5_right = "star star_right on";
      this.score=5;
    },
    addReport(){
      if(this.$session.get("addReport") == null){
        if(this.$session.get('user_nickname') == this.card.card_host){
          this.user_guest[0].user_report= this.user_guest[0].user_report+1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_guest[0])
          .then(() => {
            alert("신고가 완료되었습니다.");
          });
        }else{
          this.user_host[0].user_report=this.user_host[0].user_report+1;
          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",this.user_host[0])
          .then(() => {
            alert("신고가 완료되었습니다.");
          });
        }
        this.$session.set("addReport","true")
      }else if(this.$session.get("addReport") != null){
        alert("이미 신고한 상대입니다.");
      }
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
* {
  margin: 0;
  padding: 0;
}
.star {
  display: inline-block;
  width: 30px;
  height: 60px;
  cursor: pointer;
}
.star_left {
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat 0
    0;
  background-size: 60px;
  margin-right: -3px;
}
.star_right {
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat -30px
    0;
  background-size: 60px;
  margin-left: 2px;
}
.star.on {
  background-image: url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}
</style>
