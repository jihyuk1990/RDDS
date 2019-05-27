<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script src="js/bootstrap.js"></script>

<script
   src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.4.0.js"
   integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
   crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/bootstrap.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호찾기1</title>
<style>
#outline {
   width: 500px;
   height: 700px;
   margin: 10px auto;
   text-align: center;
   overflow: hidden;
   display: block;
   margin: 10px auto;
}

#header {
   border-bottom: 2px solid #3aabd3;
}
/*메인 로고 전체 틀*/
#header {
   width: 480px;
   height: 80px;
   border-bottom: 2px solid #3aabd3;
   margin: 10px;
}
/*이미지의 크기 지정*/
#header img {
   width: 100%;
   height: 100%;
}

#input {
   border: none;
   border-bottom: 2px solid #337ab7;
}

#telinput {
   width: 500px;
}

#telinput div {
   display: inline-block;
   width: 125px;
   float: left;
}

#complete div {
   display: inline-block;
   float: left;
}

#finalcomplete div {
   display: inline-block;
   float: left;
}

#footer {
   width: 480px;
   height: 80px;
   margin: 10px;
}
/*이미지의 크기 지정*/
#footer img {
   width: 100%;
   height: 100%;
}
</style>
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/sap/resources/css/searchPw.css">

<script language="javascript">
   $(function() {
      $('#sendCheckCode').on('click', function() {
         var name = $('input[name=name]').val();
         var tel1 = $('input[name=tel1]').val();
         var tel2 = $('input[name=tel2]').val();
         var tel3 = $('input[name=tel3]').val();
         var action = $('input[name=action]').val();

         if ((name == "") || (tel1 == "") || (tel2 == "") || (tel3 == "")) {
            alert('이름 또는 휴대폰번호를 입력하세요');
            return;
         }

         $.ajax({
            type : "POST",
            url : "sendcheckcode.do",
            data : {
               "name" : name,
               "tel1" : tel1,
               "tel2" : tel2,
               "tel3" : tel3,
               "action" : action

            },
            success : function(data) {

               if (data == "fail") {
                  alert('회원정보가 없어서 인증번호발송불가');

               } else {

                  alert('인증번호 발송 완료.');

                  $('#randomvalue').val(data);
                  $('#checkCodeFlag').val('YES');
                  $('#checkCodeArea').val(data);

               }

            }
         })

      });

      $('#checkCodeBtn').on('click', function() {
         var checkcode = $('input[name=checkcode]').val();
         var randomvalue = $('#randomvalue').val();

         if (checkcode == "") {
            alert('인증번호를 입력하세요.');
            return;
         }

         if (checkcode != randomvalue) {

            alert('인증번호가 일치하지 않습니다.');
            return;
         }

         if (checkcode == randomvalue) {

            alert('인증번호가 일치합니다. 이메일을 입력 후 비밀번호 찾기 버튼을 클릭해주세요.');
            $('#completecheckcode').val('YES');
            $("#searchpassword").show();
            $("#emailinsert").show();

         }

      });

   });

   function findPw() {

      var name = $('input[name=name]').val();
      var email = $('input[name=email]').val();
      var tel1 = $('input[name=tel1]').val();
      var tel2 = $('input[name=tel2]').val();
      var tel3 = $('input[name=tel3]').val();

      if (email == "") {
         alert('이메일을 입력해주세요.')
         return;
      }

      $.ajax({

         type : "POST",
         url : "searchPw.do",
         data : {
            "email" : email,
            "tel1" : tel1,
            "tel2" : tel2,
            "tel3" : tel3,
            "name" : name

         },
         success : function(data) {

            if (data == "fail") {
               alert('해당 이메일과 일치하는 회원정보가 없습니다.');
            } else {
               alert('고객님의 아이디와 비밀번호를 포함한 이메일 발송이 완료 되었습니다.');

               location.href = "login.jsp";

            }

         }

      })

   }
</script>



</head>

<body>
   <div id="outline">
      <div id="header">
         <a href="hompage.do"><img src="imgs/logover2.png"></a>
      </div>
      <div class="contact-clean" style="width: 500px; height: 300px">
         <form action="searchPw.do" method="get">
            <h2 class="text-center">비밀번호 찾기</h2>
            <p class="text-center">
               아래 이메일주소를 입력하시면,<br> 입력하신 이메일로 암호를 보내드립니다<br> <br>
            <div class="form-group has-success has-feedback">
               <input id="input" type="text" name="name" placeholder="이름"
                  class="form-control" /><i aria-hidden="true"
                  class="form-control-feedback glyphicon glyphicon-ok"></i>
            </div>

            <div class="form-group has-success has-feedback">

               <div id="telinput">
                  <div id="tel1">
                     <input id="input" type="text" name="tel1" placeholder="핸드폰번호"
                        class="form-control" />

                  </div>
                  <div id="tel2">

                     <input id="input" type="text" name="tel2" placeholder="핸드폰번호"
                        class="form-control" />

                  </div>
                  <div id="tel3">
                     <input id="input" type="text" name="tel3" placeholder="핸드폰번호"
                        class="form-control" />
                  </div>

                  <div id="sendnumber">
                     <button type="button" class="btn btn-primary" id="sendCheckCode">인증번호발송</button>
                  </div>
                  <br> <br> <br>
               </div>

               <div id="complete">
                  <!-- 인증번호 받기 -->
                  <input type="text" id="randomvalue" value="랜덤한 값"
                     style="display: none;">

                  <!-- 인증번호 받기 -->


                  <!-- 인증번호를 받았다는 플래그 -->

                  <input type="text" id="checkCodeFlag" style="display: none;"NO" >

                  <!-- 인증번호를 받았다는 플래그 -->

                  <div id="inputtextnumber">
                     <input id="input" type="text" name="checkcode"
                        placeholder="인증번호입력" class="form-control" style="width: 380px;" />
                  </div>
                  <div id="nputtextbutton">
                     <button type="button" class="btn btn-primary" id="checkCodeBtn">인증번호확인</button>
                  </div>



                  <!-- 인증번호가 확인이 되었다는 플래그 -->

                  <input type="text" id="completecheckcode" style="display: none;"NO">

                  <!-- 인증번호가 확인이 되었다는 플래그 -->



                  <div id="finalcomplete">
                     <br>
                     <div class="form-group has-error has-feedback" id="emailinsert"
                        style="display: none;">
                        <input id="input" type="email" name="email" placeholder="email"
                           class="form-control" style="width: 380px;" /><i
                           aria-hidden="true"
                           class="form-control-feedback glyphicon glyphicon-remove"></i>
                        <p class="help-block">'@'포함한 이메일주소를 정확히 입력해주세요.</p>
                     </div>

                     <div id="searchpassword" class="form-group"
                        style="display: none;">
                        <button allign="center" class="btn btn-primary" type="button"
                           onclick="findPw();">비밀번호찾기</button>
                     </div>
                  </div>
               </div>
         </form>




         <!-- 컨트롤러에서 sms발송을 위해 필요한 것이다 지우면 큰일남 -->

         <input type="hidden" name="action" value="go">

         <!-- 컨트롤러에서 sms발송을 위해 필요한 것이다 지우면 큰일남 -->

      </div>
   </div>


   <div id="footer">
      <img src="imgs/footer1.png">
   </div>





</body>


</html>