<template>
  <div id="safearea" style="width:375px; height:690px;  padding:0px">
    
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav></topnav>
    <div>
      <div>
        <img id="img" src="@/img/admin.jpg" style="width:100px; "/>
        <h2 style="font-family: 'Jua', sans-serif; font-size:23px; font-weight:bold;"> {{ board.board_title }}</h2>
      </div>
      <div>
        <v-card class="mx-auto" max-width="375">
          <img id="nav2" src="@/img/background10.jpg" style="width:375px; height:400px; position:absolute; left:-1px; top:10px;"/>
        <v-virtual-scroll :items="body" :item-height="100" :max-width="375" height="375">
        <template v-slot="{ item }" style="position:relative">
          <div>
          <v-list-item>
            <v-list-item-content>
            <h2 style="font-family: 'Jua', sans-serif; font-size:17px; font-weight:bold; text-align: left;"> {{ item }}</h2>
            </v-list-item-content>
          </v-list-item>
          </div>
        </template>
        </v-virtual-scroll>
        </v-card>
      </div>
      <div>
        <button @click="back()" style="width:160px; height:40px; margin-left:10px; background-color:#101010; color:#FFFFFF; position: absolute; left:100px; top: 575px;">닫기</button>
      </div>
      <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer.vue";

export default {
  name: "BoardDetail",
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      user:{
        user_nickname: this.$session.get("user_nickname"),
        user_token: this.$session.get("user_token")
      },
      board: {},
      no : "",
      body: [],
      scrollY: 0,
      timer: null
    };
  },
  created: function () {
    // 핸들러 등록하기
    window.addEventListener('scroll', this.handleScroll)
  },
  beforeDestroy: function () {
    // 핸들러 제거하기(컴포넌트 또는 SPA의 경우 절대 잊지 말아 주세요!)
    window.removeEventListener('scroll', this.handleScroll)
  },
  mounted() {
    const params = new URL(document.location).searchParams;
    axios.get(`https://i3a401.p.ssafy.io:443/api/board/selectByNo`, {
        params: {
          board_no : params.get("board_no")
        }
      })
      .then(({ data }) => {
        this.board = data;
        this.body = data.board_body.split('\n');
      });
  },
  methods: {
    back(){
      this.$router.go(-1);
    },
    handleScroll: function () {
      if (this.timer === null) {
        this.timer = setTimeout(function () {
          this.scrollY = window.scrollY
          clearTimeout(this.timer)
          this.timer = null
        }.bind(this), 200)
      }
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