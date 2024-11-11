// PC====================================================

var swiper = new Swiper('.swiper-container', {
    autoplay:true,
    loop:true,
    slidesPerView: 6,
    spaceBetween: 30,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
  });
// 广告高随以及头部变化
var header=document.querySelector(".header")
   // 广告当前距离页面顶部的距离 = 滚动条的高度 + 初始广告距离页面顶部的距离
   var originY = advance.offsetTop;

   window.onscroll = function() {
     // advance.style.top = originY + sct() + "px";
     move(advance, originY + sct());
     //PC端 页面滚动头部导航栏高度变化
     if(sct()>60){
      header.classList.add("active")
     }else{
      header.classList.remove("active")
     }
   };

   function move(ele, t) {
     t = Math.floor(t);
     if (ele.timer) clearInterval(ele.timer);
     ele.timer = setInterval(function() {
       // 起点
       var start = ele.offsetTop;
       // 步长
       var step = (t - start) / 10;
       if (Math.abs(step) < 1) {
         step = step > 0 ? 1 : Math.floor(step);
       }
       // 运动
       ele.style.top = start + step + "px";
      //  console.log(start, step, t);
       if (start + step === t) {
        //  console.log("stop ...");
         clearInterval(ele.timer);
       }
     }, 17);
   }

   function sct() {
     return (
       document.documentElement.scrollTop ||
       window.pageYOffset ||
       document.body.scrollTop
     );
   }
  //  M===========================================================
  //头部工具栏点击事件-m
var containner=document.querySelector(".containner")
// var content=document.querySelector(".content")
 console.log(containner)
  var swiper = new Swiper('.swiper-weddingDress', {
   slidesPerView:2,//显示两个
   spaceBetween: 30,
   centeredSlides: true,
  });
window.onresize=function () {          //当浏览器大小变化时
    console.log(this.width)
};

