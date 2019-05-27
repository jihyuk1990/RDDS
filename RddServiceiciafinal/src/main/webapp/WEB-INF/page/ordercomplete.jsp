.<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->

<scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->

<script src="https://code.jquery.com/jquery-3.4.0.js"
   integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
   crossorigin="anonymous"></script> <script language="javascript">
      $(function() {

         var da = $('input[name=droneaddr]').val();
         if (da == "") {
            $('#droneexplain').hide();
            $('#droneBtn').hide();
         } else {
            $('#droneexplain').show();
            $('#droneBtn').show();
         }

      });

      function calcHeight() {
         //아이프레임의 진짜 높이를 가져옵니다.
         var the_height = document.getElementById('iframe_test').contentWindow.document.body.scrollHeight;

         //아이프레임의 높이를 변경하여 스크롤을 없게 합니다.
         document.getElementById('iframe_test').height = the_height + 30;
      }

      function sendDroneAddr() {

         $('#senddroneaddr').submit();

      }
   </script>


<style>
/*header 이미지 틀 지정*/
#header {
   width: 99%;
   height: 100px;
   line-height: 100px;
   text-align: center;
   margin: 5px;
   padding-bottom: 5px;
   border-bottom: 2px solid #3aabd3;
   height: 100px;
}
/*이미지의 크기 지정*/
#header img {
   width: 100%;
   height: 100%;
}
/*페이지 설명 문구의 글씨 크기 형태*/
h1 {
   font-weight: bold;
   font-size: 3em;
}

#ordermember {
   width: 60%;
   height: 200px;
   border: 3px dotted #3aabd3;
   margin: 0 auto;
}
/*header 와 footer를 제외한 페이지의 전체 크기 조정 틀*/
#outline {
   width: 800px;
   margin: 0 auto;
   overflow: hidden;
   padding: 5px;
   text-align: center;
}
/*footer 이미지 틀 지정*/
#footer {
   clear: both;
   border: noen;
   margin: 5px;
   text-align: center;
   width: 99%;
}
/*footer 이미지 크기 지정*/
#footer img {
   width: 100%;
   height: 100px;
}
</style>
</head>
<body>
   <div id="outline">
      <div id="header">
         <a href="hompage.do"><img src="imgs/logover2.png"></a>
      </div>
      <div>
         <h1>주문 완료</h1>
      </div>
      <div id="ordermember">
         <p style="font-size: 20px;">
            <br>${name}님 감사합니다. <br>상품이 준비 되는 대로 배송이 시작 됩니다.<br>

         </p>
         <p style="font-size: 20px;" id="droneexplain">
            드론배송지주소확인<br> <input type="text" name="droneaddr"
               value="${droneAddress}"
               style="border: none; border-bottom: 2px solid #337ab7; width: 50%; text-align: center;">
         </p>
         <iframe width="80%" onLoad="calcHeight();" height="25%"
            scrolling="no" id="iframe_test" name="iframe_test" frameborder="0"></iframe>
      </div>
      <div>
         <br> <a href="./mypage.jsp"'><input type="button"
            value="주문 확인" class="btn-info btn-lg" style="width: 130px;"></a>


         <!-- 드론버튼을 클릭하면 sendDroneAddr() 메소드가 실행되어  아이디가 senddroneaddr인 폼태그가 제출됨. 
         제출될 때 드론주소를 가지고 와이파이모듈에 형성된 서버에 전달되고 드론주소를 받은 와이파이모듈에 형성된 서버가 아이프레임에 띄워짐 -->
         <a href="#" onclick="sendDroneAddr()"><input type="button"
            id="droneBtn" value="드론 배송" class="btn-info btn-lg"
            style="width: 130px;"></a>
      </div>

      <div id="footer">
         <br> <img src="imgs/footer1.png">
      </div>
   </div>

   <form action="http://192.168.1.106" id="senddroneaddr" method="POST"
      target='iframe_test' style="display: none;">

      <input type="text" name="droneaddr" value="${droneAddress}"> <input
         type="text" name="ordernumber" value="${orderNumber}">

   </form>


</body>
</html>