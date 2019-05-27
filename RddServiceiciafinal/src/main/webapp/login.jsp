<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
#con {
   width: 500px;
   margin: 10px auto;
   text-align: center;
   overflow: hidden;
   display: block;
}

#header {
   border-bottom: 2px solid #3aabd3;
}

#id, #pw {
   width: 99%;
   height: 35px;
   margin: 5px 0;
   border: 2px solid #3aabd3;
   padding-left: 10px;
}

#member {
   width: 100%;
   height: 50px;
   margin: 5px 0;
   border: 1px solid #3aabd3;
}

#login, #search {
   width: 50%;
   float: left;
   margin: 5px 0;
   height: 40px;
   border: 1px solid #3aabd3;
}

img {
   width: 100%;
   margin: 5px 0;
}
</style>
</head>
<body>
   <form action="loginMember.do" method="post">
      <div id="con">

         <div id="header">
            <a href="hompage.do"><img src="imgs/logover2.png"></a>
         </div>

         <div id="content">
            <input id="id" type="text" placeholder="아이디" name="id" /> <input
               id="pw" type="password" placeholder="비밀번호" name="pw" /> <input
               id="search" type="button" style="height: 50px;"
               class="btn-link btn-lg" value="id / pw 찾기"
               onclick="location.href='SearchPw.jsp'"> <input id="login"
               type="submit" style="height: 50px;" class="btn-link btn-lg"
               value="로그인" id=loginbtn /> <input id="member" type="button"
               class="btn-link btn-lg" value="회원가입" name="joinbtn"
               onclick="location.href='join.jsp'">
         </div>

         <div id="footer">
            <img src="imgs/footer1.png">
         </div>

      </div>
   </form>
   <scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   <!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</body>
</html>