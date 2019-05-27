.<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
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
   height: 150px;
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
         <h1>회원가입 완료</h1>
      </div>
      <div id="ordermember">
         <p style="font-size: 20px;">
            <br>${name}님 회원가입이 완료되었습니다 <br>로그인 부탁드립니다<br>
         </p>
      </div>
      <div>
         <br> <a href="./login.jsp"'><input type="button"
            value="로그인하기" class="btn-info btn-lg" style="width: 130px;"></a>
      </div>

      <div id="footer">
         <br> <img src="imgs/footer1.png">
      </div>
   </div>
   <scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   <!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</body>
</html>