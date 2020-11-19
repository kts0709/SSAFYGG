<template>
  
  <div id="safearea" style="width:375px; height:690px; padding:0px; ">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav></topnav> 
    <img id="nav2" src="@/img/background10.jpg" style="width:370px; height:560px; position:absolute; left:0px; top:60px;"/>
      <div style="width:370px; height:600px; position:absolute; left:0px; top:40px;"></div>
      <div style="position:absolute; left:20px; top: 100px;">
        <h4 style="font-family: 'Jua', sans-serif; font-size:18px;  float:left;"><strong>소환사명</strong></h4>
        <h4 style="font-family: 'Jua', sans-serif; font-size:18px;  float:left; margin-left:15px;">{{ user_nickname }}</h4>
      </div>
      
      <div style="position:absolute; left:20px; top: 140px;">
        <h4 style="font-family: 'Jua', sans-serif;  font-size:18px; font-weight:bold; float :left;"> 포지션 </h4>

        <select name="card.position" v-model="card.card_position" style="font-family: 'Jua', sans-serif;  width:150px; text-align:center; float:left; height:23px; margin-left:30px; margin-top:-3px;">
          <option disabled value="">라인을 선택해주세요.</option>
          <option value="Top">Top</option>
          <option value="Mid">Mid</option>
          <option value="Jungle">Jungle</option>
          <option value="Adc">Adc</option>
          <option value="Sup">Sup</option>
        </select>
      </div>

    <div id="text-area" style="position:absolute; left:11px; top: 170px; font-family: 'Jua', sans-serif;">
      <br />
      <textarea v-model="card.card_message" ref="card_message" required="required" minlength="1" maxlength="16" size="9" style="background-color:white;border:2px solid; height : 120px;width : 350px; resize : none;" placeholder="  함께 할 팀원을 구해보세요"></textarea>
      <br />
      <span style="font-family: 'Jua', sans-serif; color:#aaa; float : right; font-size:12px; width : 120px; position:absolute; left:250px; top: 120px;" id="counter" >({{ this.card.card_message.length }} / 최대 16)</span>
    </div>
    
    <div style="position:absolute; font-family: 'Jua', sans-serif; left:40px; top: 360px; background-color:white;">
      <div class="row">
        <div class="col s6 p-0 mg-l-1" style="left:0px height : 10px background-color:white;">
          <div class="card-panel teal background-color:white;">
            
            <img id="nav2" src="@/img/hash.png" style="width:40px;position:absolute; left:5px; top:-20px;"/>
            <input class="white-text" type="text" v-model="message" v-on:keyup.enter="submit"  placeholder="#키워드" required="required" minlength="1" maxlength="4" style=" border:2px solid; width:150px; background-color:white; text-align:center; position:absolute; left:50px; top: -14px;"/>
            <div style=" width:140px;position:absolute; left:190px; top: -12px; color:red;">
            <transition name="fade">
              <span class="card-title red-text" v-show="warning" >중복된 태그입니다.</span>
            </transition>
            </div>
          </div>
        </div>
        <div class="col s6" style="position:absolute; left:-20px; top:30px;" >
          <div class="card-panel teal">
            <div class="chip" v-for="(item, key) in array" :key="key" style="width:350px; margin-left:-25px;">
              <h2 style="webkit-border-radius:10px; background-color:#E68A7B; color:#F6F2CF;font-size:25px; width:100px; float:left; margin-left:15px; padding-top:4px;">  
                {{ item }}
                <i class="close material-icons" v-on:click="remove(key)" style="cursor:pointer;">x</i>
              </h2>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div>
      <button @click="cancle" style="font-family: 'Jua', sans-serif;width:160px; height:40px; margin-left:10px; background-color:#808080; color:#FFFFFF; position: absolute; left:10px; top: 545px;">취소</button>
      <button @click="handler" style="font-family: 'Jua', sans-serif;width:160px; height:40px; margin-left:10px; background-color:#002633; color:#FFFFFF; position: absolute; left:185px; top: 545px;"> 확인</button>
    </div>

    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>

    
  </div>
</template>

<script>
//import Hashadd from "../layouts/Hashadd.vue";

import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer.vue";
export default {
  components: {
    topnav,
    footernav,
  },
  name: "App",
  data() {
    return {
      user_nickname: "",
      item: "",

      message: "",
      warning: false,
      array: ["즐겜", "뉴메타"],

      card: {
        card_active: true,
        card_hashtag: "",
        card_host: "",
        card_host_rank: "",
        card_message: "",
        card_position: "",
        card_recruit_max: 1,
        card_recruit_now: 0,
        card_tier_end: 3,
        card_tier_start: 0,
        card_type: "솔랭"
      },
      apiKey:this.$session.get("apiKey"),
    };
  },

  methods: {
    cancle() {
      this.$router.push("/home");
    },
    back(){
      this.$router.go(-1);
    },
    handler() {
      let err = true;
      let msg = "";
      !this.card.card_message &&
        ((msg = "내용을 입력해주세요"),
        (err = false),
        this.$refs.card_message.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      this.card.card_hashtag = this.array[0];
      for (var i = 1; i < this.array.length; i++) {
        this.card.card_hashtag += "/" + this.array[i];
      }
      axios
        .post("https://i3a401.p.ssafy.io:443/api/card/insert", this.card)
        .then(() => {
          this.$router.push("/home");
        });
    },

    submit: function() {
      this.warning = false;
      var counter = this.array.length;

      for (var i = 0; i < counter; i++) {
        if (this.array[i] == this.message) {
          this.warning = true;
          break;
        }
      }
      if (this.array.length >= 3) {
        this.warning = true;
        alert("해쉬태그는 3개만 등록 가능합니다");
      }
      if (this.warning == false && this.array.length < 3) {
        this.array.push(this.message);
        this.message = "";
      }
    },
    remove: function(x) {
      this.array.splice(x, 1);
    }
  },
  created() {

    axios
      .get(`https://i3a401.p.ssafy.io:443/api/user/select`, {
        params: {
          user_auth: this.$session.get("user_auth"),
          user_type: this.$session.get("user_type")
        }
      })
      .then(({ data }) => {

        this.user_nickname = data[0].user_nickname;
        this.card.card_host = data[0].user_nickname;
        axios.get( `https://i3a401.p.ssafy.io:443/api/riot/summoner?apiKey=`+this.apiKey+`&summonerName=` +this.user_nickname)
          .then(({ data }) => {
            this.item = data;
            if (this.item.rank[0].rank == "I") {
              this.card.card_host_rank = this.item.rank[0].tier + "_1";
            } else if (this.item.rank[0].rank == "II") {
              this.card.card_host_rank = this.item.rank[0].tier + "_2";
            } else if (this.item.rank[0].rank == "III") {
              this.card.card_host_rank = this.item.rank[0].tier + "_3";
            } else if (this.item.rank[0].rank == "IV") {
              this.card.card_host_rank = this.item.rank[0].tier + "_4";
            }
          })
          .catch(error => {
            console.log(error);
            alert("랭크정보가 없습니다. 배치고사를 완료해주세요!!");
            this.$router.push("/home");
          });
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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 222;
}
</style>
