<template>
  <div id="app" style="width:375px; height:690px;  padding:0px">
    <div id="topnav">
      <img id="img" src="@/img/help.png" style="position:absolute; left:25px; top: 15px;"/>
      <img id="img" src="@/img/GG.png" style="width:60px; height:30px; position:absolute; left:170px; top: 15px;"/>
      <img @click="goCardRegist" id="img" src="@/img/add.png" style="position:absolute; left:350px; top: 15px;"/>
      <img id="img" src="@/img/line.png" style="width:365px; position:absolute; left:18px; top: 50px;"/>
    </div>

    <div style="margin-top:-100px;"></div>

    <div v-for="(v, index) in items" :key="index + '_items'">
      <div v-if="(v.card_host != user_nickname) && v.card_active == true"   @click="handler(v.card_host, v.card_no)" style="margin-top:140px;">
        <div style="width:0px;  margin-left:-5px; padding:0px; position:relative; ">
          <img v-if=" v.card_host_rank.includes('IRON') ||  v.card_host_rank.includes('BRONZE') || v.card_host_rank.includes('SILVER') " id="img" src="@/img/card_silver.png" style="width:380px; position:absolute;"/>
          <img v-else-if="v.card_host_rank.includes('GOLD') ||v.card_host_rank.includes('PLATINUM') " id="img" src="@/img/card_platinum.png" style="width:380px; position:absolute;"/>
          <img  v-else-if="v.card_host_rank.includes('DIAMOND') " id="img" src="@/img/card_diamond.png" style="width:380px; position:absolute;"/>
          <img  v-else id="img" src="@/img/card_haven.png" style="width:380px; position:absolute;"/>

          <img id="img" :src="`https://opgg-static.akamaized.net/images/medals/${v.card_host_rank}.png?image=q_auto&v=1595653530`" style="width:100px; position:absolute; left:30px; top:35px;" />

          <h2 style="width:150px; font-family:나눔고딕; font-size:18px; font-weight:bold; position:absolute; left:120px; top:40px;"> {{ v.card_host }}</h2>
          <h2 style="webkit-border-radius: 20px 10px; background-color:#FFFFFF; width:215px; height:35px; font-family:나눔고딕; font-size:15px; font-weight:bold; position:absolute; left:135px; top:85px;  text-align: center;"> {{ v.card_message }}
          </h2>
          <div
            style="position:absolute; left:122px; top:64px; padding:0px; margin-left:5px;"
          >
            <td
              v-for="(q, index) in v.card_hashtag"
              :key="index + '_v.card_hashtag'"
            >
              <h2
                style=" webkit-border-radius:10px 5px; background-color:#a6a6a6; width:50px; height:15px; font-family:나눔고딕; font-size:12px; font-weight:bold; text-align: center; margin-left:5px;"
              >
                {{ q }}
              </h2>
            </td>
          </div>
        </div>
      </div>
    </div>

    <div>
      <!-- <h2 style="font-size:10px; position:absolute; left:500px; top:0px;">{{this.memberid}}님 환영합니다.</h2> -->
      <button
        @click="logout"
        style="font-size:10px; width:40px; position:absolute; left:500px; top:10px;"
      >
        logout
      </button>
    </div>

    <div id="nav">
      <img id="img" src="@/img/line.png" style="width:365px; position:absolute; left:18px; top: 630px;"/>
      <router-link :to="'/board'" ><img id="img" src="@/img/nav1.png" style="position:absolute; left:25px; top: 645px;"/></router-link>
      <router-link :to="'/applicant'" ><img id="img" src="@/img/nav2.png" style="position:absolute; left:100px; top: 645px;"/></router-link>
      <router-link :to="'/home'"><img id="img" src="@/img/nav3.png" style="position:absolute; left:175px; top: 645px;"/></router-link>
      <router-link :to="'/alarm'" ><img id="img" src="@/img/nav4.png" style="position:absolute; left:255px; top: 645px;"/></router-link>
      <router-link :to="'/mypage'" ><img id="img" src="@/img/nav5.png" style="position:absolute; left:330px; top: 645px;"/></router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import moment from "moment";
export default {
  name: "BoardRegist",
  data: function() {
    return {
      items: [],
      user:{
        user_token: this.$session.get("user_token"),
      }
    };
  },
  mounted() {
    
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

        axios.get("https://i3a401.p.ssafy.io:443/api/card/selectAll")
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
  },
  methods: {
    goBoardRegist(){
        this.$router.push("/boardregist");
    }
  }
};
</script>

<style>
router-link {
  text-decoration: none;
  color: #000000;
}
</style>