<template>

  <div id="safearea" style="width:375px; height:690px;padding:0px">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav></topnav>
    <div style="margin-top:60px; margin-left:-160px;">
      <span style="font-size:18px; text-align:center;font-family: 'Jua', sans-serif; "><strong>방장 |</strong> {{ card.card_host }}</span>
    </div>
     <h2 style="color:red; font-size:18px;  text-align:center;font-family: 'Jua', sans-serif; position:absolute; left:270px; top: 74px;">모집중...</h2>

    <div style="margin-top:-160px;"></div>
    <!-- 화면 내용 -->
    <div style="margin-top:110px;">
      <!-- 배경 테두리 -->
      <div style=" margin-left:px; padding:0px; position:relative; ">
        <img  id="img" src="@/img/applicantdetail_1.png" style="margin-top:10px; margin-left:7px;width:365px; height:610px;position:relative;" />

        <div style="margin-top:60px">
          <span v-if="card.card_host == user_nickname" style="width:100px;margin-left:5px; position:absolute; font-weight: bold; font-family: 'Jua', sans-serif; font-size:12;left:133px; top:94px; text-align:center">신청자</span>
        </div>
        <div style="margin-top:-700px;"></div>

        <!-- 신청자 리스트 -->
        <!-- ---------------------------------------------------------------------------- 카드 호스트일 경우-->
        <div v-if="card.card_host == user_nickname" style="position:absolute; top:124px; padding:0px; margin-left:35px;">

        <v-card class="mx-auto" min-width="300">
          <img id="nav2" src="@/img/background10.jpg" style="width:310px; height:400px; position:absolute; left:-5px; top:0px;"/>
          <v-virtual-scroll :items="Alarms" :item-height="40" :max-width="375" height="355">
              <template v-slot="{ item }">
                <div v-if="item.card_no == $session.get('card_no')" style="padding:0px; margin-bottom:0px;">
                  <div style="padding:5px;position:relative; height:80px;">
                    <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${item.alarm_title}.png?image=q_auto&v=1595653530`" style="width:30px; position:absolute; top:8px; left:10px;"/>
                    <img v-show="!upHere" v-if="item.alarm_body>50" @mouseover="upHere = true" @mouseleave="upHere = false"  id="img" src="@/img/warning-troll.png" style="width:26px; position:absolute; left:190px; top: 11px;"/>
                    <h6 v-show="upHere" v-if="item.alarm_body>50" style="width:90px; height : 5px; font-family: 'Jua', sans-serif; background-color:white; color:red; font-size:14px; font-weight:bold;  position:absolute; left:135px; top: 15px;">비매너 유저</h6>
                    <h2 style="margin-top :3px;  width:240px; height:32px;font-weight:bold; font-family: 'Jua', sans-serif; font-size:15px; font-weight:bold; text-align: left; margin-left:35px;border-bottom-style:dashed;border-bottom-color:silver;"></h2>
                    <h2 @click="goUserDetail(item.alarm_from,item.card_no,item.alarm_no)" style="margin-top:-33px; margin-left:35px; cursor:pointer; width:140px; font-family: 'Jua', sans-serif; font-size:15px; font-weight:bold; text-align: left;">{{ item.alarm_from }}</h2>

                    <!-- 거절 및 수락 버튼 -->
                    <div v-if="card.card_host==user_nickname">
                      <div v-if="isHead" @click="deny(item.alarm_from,item.alarm_no)" style="cursor:pointer">
                        <img id="img" src="@/img/x.png" style="position:absolute; height : 31px;left:210px; top: 7px;"/>
                      </div>
                      <div v-if="isHead" @click="accept(item.alarm_from,item.alarm_no)" style="cursor:pointer">
                        <img id="img" src="@/img/v.png" style="position:absolute; height:31px;left:240px; top: 8px;"/>
                      </div>
                    </div>
                  </div>
                </div>
              </template>
          </v-virtual-scroll>
        </v-card>

          <div v-if="Alarms[0] == null">
              <h2 style="width:300px; font-family: 'Jua', sans-serif; font-size:18px; font-weight:bold; position:absolute; left:5px; top:40px;"> 신청한 사람이 없습니다</h2>
          </div>
        </div>

        <!-- ---------------------------------------------------------------------------- 카드 게스트일 경우-->
        <div v-if="card.card_host != user_nickname" style="position:absolute; top:124px; padding:0px; margin-left:35px;">
          <img id="nav2" src="@/img/background10.jpg" style="width:310px; height:400px; position:absolute; left:-5px; top:0px;"/>
          <h2 style="width:300px;font-family: 'Jua', sans-serif; font-size:18px; font-weight:bold; position:absolute; left:5px; top:40px;"> 방장이 수락하길 기다리는 중입니다.</h2>
        </div>



        <!-- 하단 버튼 -->
        <div v-if="card.card_host == user_nickname"  style="position:absolute; left:120px; top:84px; padding:0px; margin-left:5px;">
          <button  @click="exit" style="width:120px; height:30px; margin-left:0px; background-color:#101010; color:#FFFFFF; position: absolute; left:0px; top: 410px;">나가기</button>
        </div>
        <div v-if="card.card_host != user_nickname" style="position:absolute; left:120px; top:84px; padding:0px; margin-left:5px;">
          <button @click="delAlarm" style="width:120px; height:30px; margin-left:0px; background-color:#101010; color:#FFFFFF; position: absolute; left:0px; top: 410px;">듀오 신청 취소</button>
        </div>

      </div>
    </div>


    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer.vue";

export default {
  name: "Applicant",
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      card:"",
      user_nickname:this.$session.get("user_nickname"),
      isHead: true,
      item:"",
      Alarms:[],
      upHere : false,
      denyAlarm: {
        alarm_body:this.$session.get("user_nickname") + "님이 듀오를 거절하였습니다.",
        alarm_from: this.$session.get("user_nickname"), //발신
        //alarm_no: 0,
        alarm_read: 0,
        alarm_title: "",
        alarm_to: "", //수신
        card_no: this.$session.get("card_no")
      },
      acceptAlarm: {
        alarm_body:this.$session.get("user_nickname") + "님과 듀오를 시작합니다.",
        alarm_from: this.$session.get("user_nickname"), //발신
        //alarm_no: 0,
        alarm_read: 0,
        alarm_title: "",
        alarm_to: "", //수신
        card_no: this.$session.get("card_no")
      },
      report: [],
      k:0,
      apiKey:this.$session.get("apiKey"),
      msg:""
    };
  },
  created() {
    axios.get("https://i3a401.p.ssafy.io:443/api/alarm/selectAll?alarm_to=" +this.$session.get("user_nickname"))//알람정보 받아오기
      .then(({ data }) => {
        this.Alarms = data;
        for(var i=0;i<this.Alarms.length; i++){
          this.k = i;
          axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname=" +this.Alarms[i].alarm_from)//알람을 보낸 유저의 정보 받아오기
          .then(({ data }) => {
            var tmp = 0;
            if(data[0].user_match != 0) tmp = data[0].user_report / data[0].user_match * 100;
            tmp = tmp.toFixed(1);
            
            this.report.push(tmp);

            if(tmp>50){
              this.Alarms[this.k].alarm_body=tmp;
            }
            
          });
          
        }
        
      });
    
    axios.get(`https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.$session.get("user_nickname"))
      .then(({ data }) => {
        this.item = data;
        if (this.item.rank[0].rank == "I") {
          this.denyAlarm.alarm_title = this.item.rank[0].tier + "_1";
          this.acceptAlarm.alarm_title = this.item.rank[0].tier + "_1";
        } else if (this.item.rank[0].rank == "II") {
          this.denyAlarm.alarm_title = this.item.rank[0].tier + "_2";
          this.acceptAlarm.alarm_title = this.item.rank[0].tier + "_2";
        } else if (this.item.rank[0].rank == "III") {
          this.denyAlarm.alarm_title = this.item.rank[0].tier + "_3";
          this.acceptAlarm.alarm_title = this.item.rank[0].tier + "_3";
        } else if (this.item.rank[0].rank == "IV") {
          this.denyAlarm.alarm_title = this.item.rank[0].tier + "_4";
          this.acceptAlarm.alarm_title = this.item.rank[0].tier + "_4";
        }      
      });
      
      axios.get(`https://i3a401.p.ssafy.io:443/api/card/select?card_no=`+this.$session.get("card_no"))
        .then(({ data }) => {
          if(data == null){
            this.$session.remove("card_no");
            this.$router.push("/home");
          }
          this.card=data;
          
        });


      if(this.$session.get("card_no") != null){
        //Applicant에 들어올때 card_active가 false일 경우 바로 matching_complete로 이동
        axios.get(`https://i3a401.p.ssafy.io:443/api/card/select?card_no=`+this.$session.get("card_no"))
        .then(({ data }) => {
          if(data.card_active == false){
            this.$router.push("/matching_complete");
          }
        });
      }else if(this.$session.get("card_no") == null){
        alert("만들어진 매칭방이 없습니다.");
        this.$router.push("/home");
      }
      
  },
  methods: {
    //나가기 버튼을 누를 경우 카드 삭제하기 + 세션에 저장된 card_no 삭제하기
    exit() { 
      axios.delete(`https://i3a401.p.ssafy.io:443/api/card/delete?card_no=`+this.$session.get("card_no"))
      .then(({ data }) => {
        let msg = "카드 삭제시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "카드삭제 완료되었습니다.";
          this.$session.remove("card_no");
        }
        alert(msg);
        this.$router.push("/home");
      });

    },
    back(){
      this.$router.go(-1);
    },

     //v버튼을 누르면 신청을 수락하고 나머지 인원에게 이미 매칭이 완료되었습니다. 메세지 보내기
    accept(alarm_from,alarm_no) {
      this.alarm_from=alarm_from;
      this.alarm_no=alarm_no;
      this.acceptAlarm.alarm_to=this.alarm_from;
      alert("신청을 수락하였습니다");


      for(var i=0 ; i<this.Alarms.length;i++){ //수락자를 제외한 모든 인원에게 거절 메세지 보내기

        if(this.Alarms[i].alarm_from != this.alarm_from){
          this.denyAlarm.alarm_to=this.Alarms[i].alarm_from;
          axios.post("https://i3a401.p.ssafy.io:443/api/alarm/insert", this.denyAlarm)
            .then(() => {

              axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname="+this.denyAlarm.alarm_to)//FCM처리
              .then(({ data }) => {
      
                //FCM - start
                var key ="AAAA9uf5aY4:APA91bE-yYBS6gkcTWHH2JNvIjtBABg2KvnQdSeUi-5g-xbyhCz39VOPr8pgA_BB_WVd8Fm9SJ7kgsUFshji9Ujpmb5pQQGnysFFjVBPgA1qQq7vk20RGLzmsJeETiDdSpJQpzI_Cfhb";
                var to = data[0].user_token;
                var notification = {
                  title: "새로운 듀오 상대를 찾아주세요!",
                  body: this.denyAlarm.alarm_body,
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
                    this.msg=response;
                  })
                  .catch(function(error) {
                    this.msg=error;
                });
                //FCM - end
              });



              axios.delete(`https://i3a401.p.ssafy.io:443/api/alarm/delete?alarm_no=`+this.Alarms[i].alarm_no)
                .then(({ data }) => {
                  if (data === "success") {
                    this.msg=data;
                  }
              });



          });
          }
      }

      axios.post("https://i3a401.p.ssafy.io:443/api/alarm/insert", this.acceptAlarm)//수락 메세지 보냄
        .then(() => {
          axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname="+this.alarm_from)//FCM처리
            .then(({ data }) => {
              //FCM - start
              var key ="AAAA9uf5aY4:APA91bE-yYBS6gkcTWHH2JNvIjtBABg2KvnQdSeUi-5g-xbyhCz39VOPr8pgA_BB_WVd8Fm9SJ7kgsUFshji9Ujpmb5pQQGnysFFjVBPgA1qQq7vk20RGLzmsJeETiDdSpJQpzI_Cfhb";
              var to = data[0].user_token;
              var notification = {
                title: "듀오신청이 수락되었습니다. 알림목록을 확인해주세요",
                body: this.acceptAlarm.alarm_body,
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
                  this.msg=response;
                })
                .catch(function(error) {
                  this.msg=error;
              });
              //FCM - end
          });

          axios.delete(`https://i3a401.p.ssafy.io:443/api/alarm/delete?alarm_no=`+this.alarm_no)//해당 알람 삭제
            .then(({ data }) => {
              let msg = "알람 삭제시 문제가 발생했습니다.";
              if (data === "success") {
                msg = "수락자의 신청 알람을 삭제하였습니다.";
                this.msg=msg;
              }
          });




          
          axios.get(`https://i3a401.p.ssafy.io:443/api/card/select?card_no=`+this.$session.get("card_no"))//카드 비활성화
          .then(({ data }) => {
            data.card_active=false;
            data.card_recruit_now=1;
            data.card_guest=this.alarm_from;
            axios.put("https://i3a401.p.ssafy.io:443/api/card/update",data)
                .then(() => {
                  this.$router.push("/matching_complete");
                });  
          });
      });


    },


    deny(alarm_from,alarm_no) { //거절 메세지 보내기 + 해당 알람 삭제하기
      this.alarm_from=alarm_from;
      this.alarm_no=alarm_no;
      this.denyAlarm.alarm_to=this.alarm_from;

      axios.post("https://i3a401.p.ssafy.io:443/api/alarm/insert", this.denyAlarm)//거절 메세지 전송
        .then(() => {


          axios.get("https://i3a401.p.ssafy.io:443/api/user/select2?user_nickname="+this.alarm_from)//FCM처리
            .then(({ data }) => {
              //FCM - start
              var key ="AAAA9uf5aY4:APA91bE-yYBS6gkcTWHH2JNvIjtBABg2KvnQdSeUi-5g-xbyhCz39VOPr8pgA_BB_WVd8Fm9SJ7kgsUFshji9Ujpmb5pQQGnysFFjVBPgA1qQq7vk20RGLzmsJeETiDdSpJQpzI_Cfhb";
              var to = data[0].user_token;
              var notification = {
                title: "새로운 듀오 상대를 찾아주세요!",
                body: this.denyAlarm.alarm_body,
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
                  this.msg=response;
                })
                .catch(function(error) {
                  this.msg=error;
              });
              //FCM - end
          });

          axios.delete(`https://i3a401.p.ssafy.io:443/api/alarm/delete?alarm_no=`+this.alarm_no)//해당 알람 삭제
            .then(({ data }) => {
              let msg = "알람 삭제시 문제가 발생했습니다.";
              if (data === "success") {
                msg = "듀오신청을 거절하였습니다.";
              }
              alert(msg);
              this.$router.push("/matching_complete");
          });

     });

      
      
    },
    goUserDetail(alarm_from,card_no,alarm_no) {
      this.alarm_from=alarm_from;
      this.card_no=card_no;
      this.alarm_no=alarm_no;
      alert(this.alarm_from+"님의 정보를 확인합니다.");
          this.$router.push("/userdetail?card_host=" + this.alarm_from + "&card_no=" + this.card_no+"&where=alarm");
    },
    delAlarm(){
      axios.delete(`https://i3a401.p.ssafy.io:443/api/alarm/delete?alarm_no=`+this.$session.get("alarm_no"))//해당 알람 삭제
        .then(({ data }) => {
          let msg = "듀오 신청 취소시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "듀오 신청을 취소하였습니다.";
            this.$session.remove("alarm_no");
            this.$session.remove("card_no");
          }
          alert(msg);
          this.$router.push("/home");
      });
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