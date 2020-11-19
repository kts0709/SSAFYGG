<template>
  <div>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <div id="safearea">
      <div id="GG"><img src="@/img/GG.png" /></div>
      <div id="submitID">
        <h4>본인의 아이디를 입력해주세요.</h4>
        <div id="inputBox">
          <input
            type="text"
            id="user_nickname"
            ref="user_nickname"
            placeholder="소환사명을 입력하세요"
            v-model="user_nickname"
          />
          <button id="btn" @click="handler()">인증</button>
        </div>
      </div>
      <div id="homelogo" class="fixed-bottom"><img src="@/img/img1.png" /></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "App",
  data() {
    return {
      session_user_nickname: "",
      user_nickname: "",
      user_auth: "",
      user_type: "",
      user:""
    };
  },
  methods: {
    handler() {
      let err = true;
      let msg = "";
      !this.user_nickname &&
        ((msg = "소환사명을 입력해주세요"),
        (err = false),
        this.$refs.user_nickname.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      this.$router.push("/riotconfirm?user_nickname=" + this.user_nickname);
    }
  },
  beforeCreate() {
    this.$session.set("apiKey","RGAPI-e1c7e3f2-5170-4c58-b8c6-4c3ecda3480e");

    axios
      .get(`https://i3a401.p.ssafy.io:443/api/user/select`, {
        params: {
          user_auth: this.$session.get("user_auth"),
          user_type: this.$session.get("user_type")
        }
      })
      .then(({ data }) => {
        this.user=data;
        if (data[0] != null) {
          this.user[0].user_token=this.$session.get("user_token");


          axios.put("https://i3a401.p.ssafy.io:443/api/user/update",{
            user_auth: this.user[0].user_auth,
            user_match: this.user[0].user_match,
            user_nickname:  this.user[0].user_nickname,
            user_report: this.user[0].user_report,
            user_score:  this.user[0].user_score,
            user_token:  this.user[0].user_token,
            user_type:  this.user[0].user_type
          })
            .then(() => {
      
              this.$router.push("/home");
            });

        }
      });
  }
};
</script>
<style scoped>
#safearea {
  font-family:'Jua',sans-serif;
  margin: 0 auto;
  min-width: 375px;
  height: 690px;
  padding: 0;
  position: relative;
  text-align: center;
}
#GG {
  padding: 0;
  margin-top: 90px;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0);
}
#GG > img {
  width: 230px;
  height: 120px;
}
#submitID > h4 {
  margin-top: 30px;
  font-family:'Jua',sans-serif;
  font-size: 20px;
  font-weight: bold;
}
#inputBox > input {
  border: black solid 1px;
  text-align: center;
  width: 250px;
  height: 40px;
}
#btn {
  width: 70px;
  height: 40px;
  margin-left: 10px;
  background-color: #101010;
  color: #ffffff;
}
#homelogo {
  padding: 0;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0);
}
</style>
