<template>
  <div>
    <div id="safearea">

      <br>
      
      <div id="GG" style="position:relative;">
        <img src="@/img/login.png" style="width:300px; margin-left:65px;"/>
      </div>
      <br>

      <div id="logins">
        <NaverLogin class="text-center" client-id="o1YdNUHe3Pw0mLoqvsej" callback-url="https://i3a401.p.ssafy.io" v-bind:is-popup="false" v-bind:button-type="3" v-bind:button-height="48" button-color="green" :callbackFunction="callbackFunction"/>
        <div class="blank"></div>
        <KakaoLogin api-key="33a2739cf555e249f8fdf2f60f535d32" image="kakao_account_login_btn_medium_narrow" :on-success="onSuccess" :on-failure="onFailure"/>
      </div>

      <!-- <div id="homelogo" class="fixed-bottom"><img src="@/img/img1.png" style="width:400px;"/></div> -->
    </div>
    <!-- <div v-if="memberid != null">{{ gohome() }}</div> -->
  </div>
</template>





<script>
//style="margin-top: 87px; padding:0px"
import axios from "axios";
import NaverLogin from "vue-naver-login";
import KakaoLogin from "vue-kakao-login";

let onFailure = data => {
  console.log(data);
};

let callbackFunction = status => {
  if (status) {
    /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
    var email = NaverLogin.user.getEmail();
    if (email == undefined || email == null) {
      alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
      /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
      NaverLogin.reprompt();
      return;
    }

    window.location.replace(
      "http://" +
        window.location.hostname +
        (location.port == "" || location.port == undefined
          ? ""
          : ":" + location.port) +
        "/sample/main.html"
    );
  } else {
    console.log("callback 처리에 실패하였습니다.");
  }
};

export default {
  name: "App",
  data() {
    return {
      
    };
  },
  components: {
    NaverLogin,
    KakaoLogin
  },
  methods: {
    onSuccess(data) {
      axios
        .get("https://i3a401.p.ssafy.io:443/api/user/kakao", {
          params: {
            code: data.access_token
          }
        })
        .then(({ data }) => {

          this.$session.set("user_auth", data);
          localStorage.setItem("user_auth", data);
          this.$session.set("user_type", "kakao");
          localStorage.setItem("user_type", "kakao");
          this.$session.set("user_token", localStorage.getItem("token"));
          alert("환영합니다. " + this.$session.get("user_auth") + "님");
          this.$router.push("/riot");
        });
    },
    onFailure,
    callbackFunction,
    gohome() {
      this.$router.push("/riot");
    }
  },
  created() {
    let uri = window.location.href.split("#");
    
    if (uri.length == 2) {
      let vars = uri[1].split("&");
      let vars2 = vars[0].split("=");
      //axios.get('http://localhost:8399/api/user/naver', {
      axios.get("https://i3a401.p.ssafy.io:443/api/user/naver", {params: {code: vars2[1]}})
        .then(({ data }) => {

          this.$session.set("user_auth", data.response.id);
          localStorage.setItem("user_auth", data.response.id);
          this.$session.set("user_type", "naver");
          localStorage.setItem("user_type", "naver");
          this.$session.set("user_token", localStorage.getItem("token"));
          
          alert("환영합니다. " + this.$session.get("user_auth") + "님");
          this.$router.push("/riot");
        });
    }
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
}
#GG {
  padding: 0;
  margin-left:-70px;
  margin-top: -50px;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0);
}
#logins {
  margin-top: 20px;
}
.blank {
  padding: 0;
  height: 15px;
}
#homelogo {
  padding: 0;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0);
}
</style>
