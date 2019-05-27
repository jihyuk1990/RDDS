<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
   function sample6_execDaumPostcode() {

      new daum.Postcode(
            {
               oncomplete : function(data) {
                  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                  // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                  // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                  var addr = ''; // 주소 변수
                  var extraAddr = ''; // 참고항목 변수

                  //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                  if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                     addr = data.roadAddress;
                  } else { // 사용자가 지번 주소를 선택했을 경우(J)
                     addr = data.jibunAddress;
                  }

                  // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                  if (data.userSelectedType === 'R') {
                     // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                     // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                     if (data.bname !== ''
                           && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                     }
                     // 건물명이 있고, 공동주택일 경우 추가한다.
                     if (data.buildingName !== ''
                           && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', '
                              + data.buildingName : data.buildingName);
                     }
                     // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                     if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                     }
                     // 조합된 참고항목을 해당 필드에 넣는다.
                     document.getElementById("sample6_detailAddress").value = extraAddr;

                  } else {
                     document.getElementById("sample6_detailAddress").value = '';
                  }

                  // 우편번호와 주소 정보를 해당 필드에 넣는다.
                  document.getElementById('sample6_postcode').value = data.zonecode;
                  document.getElementById("sample6_address").value = addr;
                  // 커서를 상세주소 필드로 이동한다.
                  document.getElementById("sample6_detailAddress")
                        .focus();
               }
            }).open();
   }
</script>

<title>회원수정</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
/*전체 화면의 크기 지정 iframe에 출력되는 크기 %로 표현*/
#outline {
   width: 90%;
   hight: 99%;
   text-aligin: center;
}
/*회원 정보 수정 안내 제목*/
div #memberModifyTitle {
   width: 99%;
   height: 100px;
   font-size: 30px;
   border-bottom: 2px solid #3aabd3;
   text-align: center;
   font-size: 30px;
}
/*회원정보 입력 칸 전체의 input의 전체 디자인*/
table input {
   border: none;
   border-bottom: 1px solid #3aabd3;
   width: 100px;
}

/*회원 정보 입력 칸 전체 글씨 크기*/
table {
   font-size: 20px;
   margin-left: 50px;
}
/*회원 정보 입력 칸 상하 간격*/
table tr {
   font-size: 20px;
   height: 80px;
}
/*회원정보 입력창에 해당 input창 앞 텍스트의 넓이 지정*/
table tr th {
   width: 150px;
}
/*아이디입력창, 비밀번호 입력창, 비밀번호 입력 확인창,이메일 input 크기*/
#userId, #userPassword, #userPasswordCk, #userEmail {
   width: 200px;
}
/*성별 전화번호 생년월일 입력창 크기*/
#gender, #firstNum, #middleNum, #lastNum, #year, #month, #day {
   width: 55px;
}
/*주소 입력창 글씨 크기와 폭 지정*/
#address input {
   font-size: 15px;
   width: 200px;
}
</style>
</head>
<body>
   <center>
      <form action="modifyMember.do" method="post" target="_parent">
         <div id="outline">
            <br>
            <div id="memberModifyTitle">
               <p>회원 정보 수정</p>
               <p>수정할 정보를 입력</p>
            </div>
            <br> <br>

            <table id="memberInfo">
               <tr>
                  <th>아 이 디</th>
                  <td><input type="text" id="userId" name="id" readonly
                     value="${voForModify.id}"></td>

               </tr>

               <tr>

                  <th>비 밀 번 호</th>
                  <td><input type="text" id="userPassword" name="pw"
                     value="${voForModify.pw }"></td>

               </tr>

               <tr>
                  <th>비밀번호 확인</th>
                  <td><input type="password" id="userPasswordCk"></td>

               </tr>

               <tr>
                  <th>이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 름</th>
                  <td><input type="text" id="userName" name="name"
                     value="${voForModify.name}" readonly></td>

               </tr>


               <tr>
                  <th>생 년 월 일</th>
                  <td><input type="text" id="year" name="birthYY"
                     value="${voForModify.birthYY}" readonly>년 <input
                     type="text" id="month" name="birthMM"
                     value="${voForModify.birthMM}" readonly>월 <input
                     type="text" id="day" name="birthDD"
                     value="${voForModify.birthDD}" readonly>일</td>

               </tr>

               <tr>
                  <th>성 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 별</th>
                  <td><input type="text" id="gender" name="gender" readonly
                     value="${voForModify.gender}"></td>

               </tr>

               <tr>
                  <th>전 화 번 호</th>
                  <td><input type="text" id="firstNum" name="tel1"
                     value="${voForModify.tel1}"> - <input type="text"
                     id="middleNum" name="tel2" value="${voForModify.tel2}"> -
                     <input type="text" id="lastNum" name="tel3"
                     value="${voForModify.tel3}"></td>

               </tr>


               <tr>
                  <th>이 메 일</th>
                  <td><input type="text" id="userEmail" name="email"
                     value="${voForModify.email}"></td>

               </tr>


               <tr>
                  <th id="addressfont">주
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 소</th>
                  <td id="address"><input type="text" id="sample6_postcode"
                     value="${voForModify.address1}" placeholder="우편번호"
                     name="Address1" style="width: 80px; font-size: 20px;"> <input
                     type="button" id="addressbtn" style="width: 120px;"
                     class="btn-info btn-lg" onclick="sample6_execDaumPostcode()"
                     value="우편번호 찾기"><br> <input type="text"
                     id="sample6_address" placeholder="주소" name="Address2"
                     style="height: 40px;" value="${voForModify.address2}"> <input
                     type="text" id="sample6_detailAddress" placeholder="상세주소"
                     name="Address3" style="height: 40px;"
                     value="${voForModify.address3}"></td>


               </tr>

               <tr>
                  <th></th>
                  <td><br> <br> <input id="modifyMemberSubmit"
                     class="btn-info btn-lg" type="submit" style="width: 200px;"
                     value="수정완료"></td>

               </tr>
            </table>

         </div>
      </form>
   </center>

   <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   <!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
   <scrit type="text/javascript" src=js/bootstrap.js"> </script> </script>
</body>
</html>