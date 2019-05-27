<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
   function calcHeight() {
      //아이프레임의 진짜 높이를 가져옵니다.
      var the_height = document.getElementById('iframe_test').contentWindow.document.body.scrollHeight;

      //아이프레임의 높이를 변경하여 스크롤을 없게 합니다.
      document.getElementById('iframe_test').height = the_height + 30;
   }
</script>

<title>Drone Pang | My Page</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
/*header 이미지 틀 지정*/
#header, #footer {
   width: 780px;
   height: 100px;
   margin: 10px;
   border-bottom: 2px solid #3aabd3;
}
/*이미지의 크기 지정*/
#header img, #footer img {
   width: 100%;
   height: 100%;
}
/*페이지 설명 문구의 글씨 크기 형태*/
h1 {
   font-weight: bold;
   font-size: 3em;
}
/*header 와 footer를 제외한 페이지의 전체 크기 조정 틀*/
#outline {
   width: 800px;
   margin: 0 auto;
   overflow: hidden;
   text-align: center;
}
/*모든 a태그의 디자인에 클릭 색이 변경 되지 않고 밑줄을 삭제*/
a {
   text-decoration: none;
}
/*Mypage에 category의 전체적인 틀의 크기*/
#categoryMypageList li {
   display: inline-block;
   width: 180px;
   text-align: center;
   text-size: 0.5em;
}
/*iframe의 전체 틀 세부 틀은 태그 style에 기재*/
#ctegoryMypageView {
   width: 90%;
   margin: 0 auto;
}
</style>

<scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</head>
<body>
   <div id="outline">
      <div id="header">
         <a href="hompage.do"><img src="imgs/logover2.png"></a>

      </div>
      <div>
         <h1 id="titleText" style="color: #3aabd3; , margin: 0 auto;">My
            Page</h1>
      </div>

      <div>
         <br>
         <ul id="categoryMypageList">
            <li><a href="shoppingbasket.do" target='iframe_test'><input
                  type="button" value="장바구니" class="btn-primary btn-lg"
                  style="width: 130px;"></a></li>
            <li><a href="orderdelivery.do" target="iframe_test"><input
                  type="button" value="주문배송" class="btn-primary btn-lg"
                  style="width: 130px;"></a></li>
            <li><a href="modify.do" target="iframe_test"><input
                  type="button" value="정보수정" class="btn-primary btn-lg"
                  style="width: 130px;"></a></li>
         </ul>
      </div>
      <div id="categoryMypageView">
         <!-- <iframe name="iframe_test" scrolling="no" width="95%" frameborder="0"
            height="100%" style="border: none;" onload="doResize()"></iframe> -->

         <iframe width="100%" onLoad="calcHeight();" height="100%"
            scrolling="no" id="iframe_test" name="iframe_test" frameborder="0"></iframe>
      </div>

      <div id="footer">
         <img src="imgs/footer1.png">
      </div>
   </div>

</body>
</html>