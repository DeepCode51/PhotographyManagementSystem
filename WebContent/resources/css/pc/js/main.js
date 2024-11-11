// PC端========================================================
// 样片
// var nameArr=["新增","历史","可出售"]
// if(window.innerWidth<768){
//  new Swiper('.swiper-contai', {
//    slidesPerView:1.03,
//        pagination: {
//            el: '.swiper-pagination',
//            clickable: true,
//            renderBullet: function (index, className) {
//              return '<span class="' + className + '">' +nameArr[index] +'</span>';
//            },
//        },
//     } );
// }else{
//  new Swiper('.swiper-contai', {
//    slidesPerView: 4,// 一行显示slider的个数
//    slidesPerGroup: 4,// 定义slides的数量多少为一组
//    spaceBetween: 40,
//    //   freeMode: true,
//    pagination: {
//        el: '.swiper-pagination',
//        clickable: true,
//        renderBullet: function (index, className) {
//          return '<span class="' + className + '">' +nameArr[index] +'</span>';
//        },
//    },
// } );
// }

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
