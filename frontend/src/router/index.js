import Vue from "vue";
import VueRouter from "vue-router";


import Login from "@/layouts/Login.vue";
import Home from "@/layouts/Home.vue";
import Riot from "@/layouts/Riot.vue";
import RiotConfirm from "@/layouts/RiotConfirm.vue";
import UserDetail from "@/layouts/UserDetail.vue";
import CardRegist from "@/layouts/CardRegist.vue";
import Alarm from "@/layouts/Alarm.vue";
import Matching_Complete from "@/layouts/Matching_Complete";
import Applicant from "@/layouts/Applicant.vue";
import MyPage from "@/layouts/MyPage.vue"
import Board from "@/layouts/Board.vue"
import BoardRegist from "@/layouts/BoardRegist.vue"
import BoardDetail from "@/layouts/BoardDetail.vue"
import test from "@/layouts/test.vue"
import LoadingTest from "@/layouts/LoadingTest.vue"

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "main",
      component: Login
    },
    {
      path: "/Home",
      name: "home",
      component: Home
    },
    {
      path: "/riot",
      name: "riot",
      component: Riot
    },
    {
      path: "/riotconfirm",
      name: "riotconfirm",
      component: RiotConfirm
    },
    {
      path: "/userdetail",
      name: "userdetail",
      component: UserDetail
    },
    {
      path: "/cardregist",
      name: "cardregist",
      component: CardRegist
    },
    {
      path: "/Alarm",
      name: "Alarm",
      component: Alarm
    },
    {
      path: "/Matching_Complete",
      name: "Matching_Complete",
      component: Matching_Complete
    },
    {
      path: "/Applicant",
      name: "Applicant",
      component: Applicant
    },
    {
      path: "/mypage",
      name: "MyPage",
      component: MyPage
    },
    {
      path: "/board",
      name: "Board",
      component: Board
    },
    {
      path: "/boardregist",
      name: "BoardRegist",
      component: BoardRegist
    },
    {
      path: "/boarddetail",
      name: "BoardDetail",
      component: BoardDetail
    },
    {
      path: "/test",
      name: "test",
      component: test
    },
    {
      path: "/loadingtest",
      name: "LoadingTest",
      component: LoadingTest
    }
  ]
});
