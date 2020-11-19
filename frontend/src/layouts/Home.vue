<template>
  <div id="safearea" style="width:375px; height:690px; padding:0px;">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav_home></topnav_home> 
    
    <div>
        <v-card class="mx-auto" max-width="550">
        <img id="nav2" src="@/img/background10.jpg" style="width:350px; height:550px; position:absolute; left:0px; top:0px;"/>
        <v-virtual-scroll :items="items" :item-height="150" :max-width="375" height="560">
        <template v-slot="{ item }" style="position:relative">
          <div>
          <v-list-item>
            <v-list-item-content>
            <div v-if="item.card_active == true"   @click="handler(item.card_host, item.card_no)" >
              <div style="width:0px;  margin-left:0px; padding:0px; position:relative; ">
                <img v-if=" item.card_host_rank.includes('IRON')" class="cardimg" src="@/img/card_1.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('BRONZE')"  class="cardimg"  src="@/img/card_2.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('SILVER')"  class="cardimg"  src="@/img/card_3.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('GOLD')"  class="cardimg"  src="@/img/card_4.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('PLATINUM')"  class="cardimg"  src="@/img/card_5.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('DIAMOND')"  class="cardimg"  src="@/img/card_6.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('MASTER')"  class="cardimg"  src="@/img/card_7.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('GRANDMASTER')"  class="cardimg"  src="@/img/card_8.png" style="width: 350px"/>
                <img v-else-if="item.card_host_rank.includes('CHALLENGER')"  class="cardimg"  src="@/img/card_9.png" style="width: 350px"/>
                <img  v-else  class="cardimg"  src="@/img/card_1.png" style="width: 350px"/>
                <div style="width:70px;top : 30px;left: 25px;text-align : center; position : absolute">
                  <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${item.card_host_rank}.png?image=q_auto&v=1595653530`" style="left:10px;width:90px; position:absolute;  top:-10px; left: -5px;text-align:center;" />
                  <h2 style="width: 100px;font-family: 'Jua', sans-serif; text-align:center; font-size:13px; font-weigth:bold; left:-10px; top:79px; position:absolute;"> {{item.card_host_rank}}</h2>
                </div>
                <h2 style="text-align: left; width:130px; font-family: 'Jua', sans-serif; font-size:17px; font-weight:bold; position:absolute; left:120px; top:35px;"> {{ item.card_host }}</h2>
                <div>
                  <div id="card_position" v-if="item.card_position == 'Top'">
                    <img id="img" src="@/img/position/Top.png" style="width: 25px; left:280px; top:30px;"/>
                  </div>
                  <div id="card_position" v-if="item.card_position == 'Mid'">
                    <img id="img" src="@/img/position/Mid.png" style="width: 25px; left:280px; top:30px;"/>
                  </div>
                  <div id="card_position" v-if="item.card_position == 'Jungle'">
                    <img id="img" src="@/img/position/Jug.png" style="width: 25px; left:280px; top:30px;"/>
                  </div>
                  <div id="card_position" v-if="item.card_position == 'Adc'">
                    <img id="img" src="@/img/position/Adc.png" style="width: 25px; left:280px; top:30px;"/>
                  </div>
                  <div id="card_position" v-if="item.card_position == 'Sup'">
                    <img id="img" src="@/img/position/Sup.png" style="width: 25px; left:280px; top:30px;"/>
                  </div>
                </div>
                <h2 style="webkit-border-radius: 20px; width:205px; height:35px; font-family: 'Jua', sans-serif; font-size:15px; font-weight:bold; position:absolute; left:118px; top:65px;  text-align: left;"> {{ item.card_message }}</h2>
                <div style="position:absolute; left:105px; top:95px; padding:0px; margin-left:5px;" >
                  <td v-for="(q, index) in item.card_hashtag" :key="index + '_v.card_hashtag'">
                    <h2 style=" webkit-border-radius:5px 5px; background-color:#a6a6a6; width:62px; height:18px; font-family: 'Jua', sans-serif; font-size:13px; font-weight:bold; text-align: center; margin-left:5px; padding-top:1px;"> #{{ q }}</h2>
                  </td>
                </div>
              </div>
            </div>  
            </v-list-item-content>
          </v-list-item>
          </div>
        </template>
        </v-virtual-scroll>
        </v-card>
    </div>
    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav_home from "@/layouts/Topnav_Home.vue";
import footernav from "@/layouts/Footer2.vue";

export default {
  name: "Home",
  components: {
    topnav_home,
    footernav,
    
  },
  data: function() {
    return {
      dialog: false,
      items: [],
      user:{
        user_token: this.$session.get("user_token"),
      },
      apiKey:this.$session.get("apiKey"),
      alarm_body:this.$session.get("user_nickname") + "님이 듀오를 요청합니다.",
    };
  },
  mounted() {
    this.$session.remove("card_no");
    
    axios.get(`https://i3a401.p.ssafy.io:443/api/user/select`, {
        params: {
          user_auth: localStorage.getItem("user_auth"),
          user_type: localStorage.getItem("user_type")
        }
      })
      .then(({ data }) => {
        this.$session.set("user_nickname", data[0].user_nickname);
        localStorage.setItem("user_nickname",data[0].user_nickname)
        this.user_nickname=localStorage.getItem("user_nickname");


        axios.get("https://i3a401.p.ssafy.io:443/api/card/selectAll")  //만들어진 카드가 있는지 확인
          .then(({ data }) => {
            this.items = data;
            for (var i = 0; i < this.items.length; i++) {
              this.items[i].card_hashtag = this.items[i].card_hashtag.split("/");
              if(this.items[i].card_host == this.$session.get("user_nickname")){
                this.$session.set("card_no", this.items[i].card_no);
              }
            }
          });
      });



    axios.get("https://i3a401.p.ssafy.io:443/api/alarm/selectAllAlarm")//모든 알람을 받아오고 듀오신청한 알람이 있는지, 수락된 카드 알람이 있는지 확인
        .then(({ data }) => {
          for(var i=0; i<data.length; i++){
            if(data[i].alarm_body == this.alarm_body){
              this.$session.set("card_no", data[i].card_no);
              this.$session.set("alarm_no",data[i].alarm_no);
            }
            if(data[i].alarm_body.includes('시작')){
              this.$session.set("card_no", data[i].card_no);
            }
          }
        });
  },
  methods: {
    handler(card_host, card_no) {
      this.card_host = card_host;
      this.card_no = card_no;
      if(this.$session.get("user_nickname") == this.card_host){
        alert("자신의 카드에는 신청할 수 없습니다.");
      }
      else this.$router.push("/userdetail?card_host=" + card_host + "&card_no=" + card_no);
    },
    logout() {
      this.$session.clear();
      this.$router.push("/");
    },
    goCardRegist(){
      if(this.$session.get("card_no") != null){
        alert("이미 만든 카드가 있습니다.");
      }else{
        this.$router.push("/cardregist");
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
.cardimg{
  width:360px; position:relative;left : -10px;
}
#card_position > img{
 width: 30px; 
 position:absolute; 
 left:300px; 
 top:35px;
}
#card_position > img:hover {
  cursor : "정글러"
}
router-link {
  text-decoration: none;
  color: #000000;
}
</style>