<template>
  <form>
    <div class="star-box" style="padding:0px; width:310px;">
      <span :class="star.star1_left" @click="onstar1()"></span>
      <span :class="star.star1_right" @click="onstar1"></span>
      <span :class="star.star2_left" @click="onstar2"></span>
      <span :class="star.star2_right" @click="onstar2"></span>
      <span :class="star.star3_left" @click="onstar3"></span>
      <span :class="star.star3_right" @click="onstar3"></span>
      <span :class="star.star4_left" @click="onstar4"></span>
      <span :class="star.star4_right" @click="onstar4"></span>
      <span :class="star.star5_left" @click="onstar5"></span>
      <span :class="star.star5_right" @click="onstar5"></span>
    </div>
    
  </form>
</template>

<script>
export default {
  name: "star-form",

  props: {
    dataBackgroundColor: {
      type: String,
      default: ""
    }
  },

  data: function() {
    return {
      star: {
        star1_left: "star star_left on",
        star1_right: "star star_right on",
        star2_left: "star star_left on",
        star2_right: "star star_right on",
        star3_left: "star star_left on",
        star3_right: "star star_right on",
        star4_left: "star star_left",
        star4_right: "star star_right",
        star5_left: "star star_left",
        star5_right: "star star_right"
      }
    };
  },

  methods: {
    onstar1() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left";
      this.star.star2_right = "star star_right";
      this.star.star3_left = "star star_left";
      this.star.star3_right = "star star_right";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.$session.set("score",1);
      
    },
    onstar2() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left";
      this.star.star3_right = "star star_right";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.$session.set("score",2);
    },
    onstar3() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left";
      this.star.star4_right = "star star_right";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.$session.set("score",3);
    },
    onstar4() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left on";
      this.star.star4_right = "star star_right on";
      this.star.star5_left = "star star_left";
      this.star.star5_right = "star star_right";
      this.$session.set("score",4);
    },
    onstar5() {
      this.star.star1_left = "star star_left on";
      this.star.star1_right = "star star_right on";
      this.star.star2_left = "star star_left on";
      this.star.star2_right = "star star_right on";
      this.star.star3_left = "star star_left on";
      this.star.star3_right = "star star_right on";
      this.star.star4_left = "star star_left on";
      this.star.star4_right = "star star_right on";
      this.star.star5_left = "star star_left on";
      this.star.star5_right = "star star_right on";
      this.$session.set("score",5);
    },
    
  }
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
.star {
  display: inline-block;
  width: 30px;
  height: 60px;
  cursor: pointer;
}
.star_left {
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat 0
    0;
  background-size: 60px;
  margin-right: -3px;
}
.star_right {
  background: url(http://gahyun.wooga.kr/main/img/testImg/star.png) no-repeat -30px
    0;
  background-size: 60px;
  margin-left: 2px;
}
.star.on {
  background-image: url(http://gahyun.wooga.kr/main/img/testImg/star_on.png);
}
</style>
