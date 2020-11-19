<template>
  <div id="safearea" style="width:375px; height:690px;  padding:0px">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <topnav></topnav>

    <div>
        <v-card class="mx-auto" max-width="550">
          <img id="nav2" src="@/img/background10.jpg" style="width:375px; height:540px; position:absolute; left:0px; top:10px;"/>
        <v-virtual-scroll :items="items" :item-height="150" :max-width="375" height="560">
        <template v-slot="{ item }" style="position:relative">
          <div>
          <v-list-item>
            <v-list-item-content>
            <div @click="handler(item.board_no)" >
              <div style="width:0px;  margin-left:0px; padding:0px; position:relative; ">
                <img v-if="item.board_type ==0" class="cardimg"  src="@/img/card_haven.png"  style="width: 350px"/>
                <img v-if="item.board_type ==1" class="cardimg"  src="@/img/card_platinum.png"  style="width: 350px"/>
                <img v-if="item.board_type ==2" class="cardimg"  src="@/img/card_diamond.png"  style="width: 350px"/>
                <img id="img" src="@/img/teemo.png" style="width:100px; position:absolute; left:20px; top:30px;"/>
                <h4 style="width:290px; text-align:center; font-family: 'Jua', sans-serif; font-size:16px; font-weight:bold; position: absolute; left: 75px; top: 50px; ">{{item.user_nickname}}</h4>
                <h2 style="webkit-border-radius: 20px 10px; width:170px; height:35px; font-family: 'Jua', sans-serif; font-size:19px; font-weight:bold; position:absolute; left:135px; top:85px;  text-align: center;"> {{item.board_title}}</h2>
              </div>
            </div>  
            <!-- <h2 style="font-family: 'Jua', sans-serif; font-size:17px; font-weight:bold; text-align: center;"> {{ item }}</h2> -->
            </v-list-item-content>
          </v-list-item>
          </div>
        </template>
        </v-virtual-scroll>
        </v-card>
    </div>

    <!-- <div v-for="(v, index) in items" :key="index + '_items'">
      <div @click="handler(v.board_no)" >
        <div style="width:0px;  margin-left:0px; padding:0px; position:relative; ">
          <img v-if="v.board_type ==0" class="cardimg"  src="@/img/card_haven.png"  style="width: 400px"/>
          <img v-if="v.board_type ==1" class="cardimg"  src="@/img/card_platinum.png"  style="width: 400px"/>
          <img id="img" src="@/img/admin.jpg" style="width:100px; position:absolute; left:20px; top:40px;"/>
          <h4 style="width:290px; text-align:center; font-family: 'Jua', sans-serif; font-size:18px; font-weight:bold; position: absolute; left: 100px; top: 50px; ">{{v.user_nickname}}</h4>
          <h2 style="webkit-border-radius: 20px 10px; background-color:#FFFFFF; width:215px; height:35px; font-family: 'Jua', sans-serif; font-size:20px; font-weight:bold; position:absolute; left:135px; top:95px;  text-align: center;"> {{v.board_title}}</h2>
        </div>
      </div>
    </div> -->

    <footernav style="position: absolute; left:10px; top: 605px;"></footernav>
  </div>
</template>

<script>
import axios from "axios";
import topnav from "@/layouts/Topnav.vue";
import footernav from "@/layouts/Footer2.vue";

export default {
  name: "Board",
  components: {
    topnav,
    footernav,
  },
  data: function() {
    return {
      items: [],
    };
  },
  mounted() {
    axios.get(`https://i3a401.p.ssafy.io:443/api/board/selectAll`, {
      })
      .then(({ data }) => {
        this.items = data;
      });
  },
  methods: {
    handler(board_no) {
      this.$router.push("/boarddetail?board_no=" + board_no);
    },
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