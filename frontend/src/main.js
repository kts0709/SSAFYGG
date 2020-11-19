import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import VueSession from "vue-session";
//import Argon from "@/plugins/argon-kit";
import VeeValidate from "vee-validate";
// import store from "./store/store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import firebase from "firebase/app";
import "firebase/messaging";
import vuetify from "./plugins/vuetify";

Vue.use(BootstrapVue);

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount("#app");

var sessionOptions = {
  persist: true
};

Vue.use(VueSession, sessionOptions);
//Vue.use(Argon);
Vue.use(VeeValidate);

const firebaseConfig = {
  apiKey: "AIzaSyBn8HvczIJdnMKq9WsSSX-ieQlYJAC4NiQ",
  authDomain: "arboreal-lane-280314.firebaseapp.com",
  databaseURL: "https://arboreal-lane-280314.firebaseio.com",
  projectId: "arboreal-lane-280314",
  storageBucket: "arboreal-lane-280314.appspot.com",
  messagingSenderId: "1060453837198",
  appId: "1:1060453837198:web:98f59b5d693e7cc7551cdf",
  measurementId: "G-6BCVXLK63L"
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.usePublicVapidKey(
  "BGNLVHbC24v5o67HhqAot00upNyvmedP40DDjbs0wyCBwLG_DMN9V5r8DvcOoQ7IOnBMA_1FCfxFTT6_pP8rj48"
);

// 알림 수신을 위한 사용자 권한 요청
Notification.requestPermission().then(permission => {
  console.log("permission ", permission);
  if (permission !== "granted") {
    alert("알림을 허용해주세요");
  }
});

// TODO: Send token to server for send notification
messaging
  .getToken()
  .then(currentToken => {
    if (currentToken) {
      localStorage.setItem("token", currentToken);
      this.$session.set("user_token", localStorage.getItem("token"));
      console.log("user_token : " + this.$session.get("user_token"));
      console.log(currentToken);
    } else {
      console.log(
        "No Instance ID token available. Request permission to generate one."
      );
    }
  })
  .catch(err => {
    console.log("An error occurred while retrieving token. ", err);
  });

// Handle received push notification at foreground
messaging.onMessage(payload => {
  alert(
    "제목 : " +
      payload.notification.title +
      "\n내용 : " +
      payload.notification.body
  );
});
