<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.4.0.js"
	integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
	crossorigin="anonymous"></script>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<title>회원가입</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
/*header 이미지 틀 지정*/
#header, #footer {
	width: 780px;
	height: 100px;
	margin: 10px;
	border-bottom: 2px solid #3aabd3;
	height: 100px;
}
/*이미지의 크기 지정*/
#header img, #footer img {
	width: 100%;
	height: 100%;
}
/*header 와 footer를 제외한 페이지의 전체 크기 조정 틀*/
#outline {
	width: 800px;
	margin: 0 auto;
	overflow: hidden;

}
/*페이지 타이틀 제목 css*/
div #joinTitle {
	width: 100%;
	height: 150px;
	font-size: 25px;
	border-bottom: 1px solid #3aabd3;
	text-align: center;
}
/*회원정보 입력 칸 전체의 input의 전체 디자인*/
table input {
	border: none;
	border-bottom: 2px solid #337ab7;
	width: 100px;
}
/*회원 정보 입력 칸 전체 글씨 크기*/
table {
	font-size: 20px;
}
/*회원 가입 폼 table의 폰트 사이즈 높이 지정*/
table tr {
	font-size: 20px;
	height: 50px;
}
/*입력창 설명란의 넓이 지정*/
table tr th {
	width: 150px;
}
/*아이디입력창, 비밀번호 입력창, 비밀번호 입력 확인창,이메일 input 크기*/
#joinUserId, #joinUserPassword, #joinUserPasswordCk, #joinUserEmail,
	#joinUserGender, #sample6_address, #sample6_detailAddress {
	width: 200px;
}
/*성별 전화번호 생년월일 입력창 크기*/
#joinFirstNum, #joinMiddleNum, #ljoinLastNum, #joinUserYear,
	#joinUserMonth, #joinUserDay {
	width: 55px;
}
</style>
</head>
<body>
	<form name="f" action="joinMember.do" method="post"
		onsubmit="return false;">
		<div id="outline">

			<div id="header">
				<a href="hompage.do"><img src="imgs/logover2.png"></a>
			</div>

			<div id="joinTitle">
		
				<p>가입 정보 입력</p>
				<p>로그인 정보 및 가입 정보를 입력하세요</p>
				<br>
			</div>

			<table id="joinMemberTable">
				<tr>
					<th>아 &nbsp;&nbsp; 이 &nbsp;&nbsp; 디</th>
					<td><input type="text" id="joinUserId" name="id"> <input
						id="checkid" class="btn-primary btn-lg" value="중복확인" type="button">
						<input type="hidden" id="isCheck" value="NO">
						</td>
				</tr>

				<tr>
					<th>비 밀 번 호</th>
					<td><input type="password" id="joinUserPassword" name="pw"></td>
				</tr>

				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" id="joinUserPasswordCk"
						onkeyup="passwordCheck();"></td>

				</tr>
				<tr>
					<td></td>
					<td class="pwCkResult" id="pwCkResult"></td>

				</tr>

				<tr>
					<th>이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 름</th>
					<td><input type="text" id="joinUserName" name="name"></td>
				</tr>

				<tr>
					<th>생 년 월 일</th>
					<td><input type="text" id="joinUserYear" name="birthYY">년
						<input type="text" id="joinUserMonth" name="birthMM">월 <input
						type="text" id="joinUserDay" name="birthDD">일</td>
				</tr>

				<tr>
					<th>성 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 별</th>
					<td><select id="joinUserGender" name="gender">
							<option value="남자">남자</option>
							<option value="여자">여자</option>
					</select></td>
				</tr>

				<tr>
					<th>전 화 번 호</th>
					<td><input type="text" id="joinFirstNum" name="tel1">
						- <input type="text" id="joinMiddleNum" name="tel2"> - <input
						type="text" id="joinLastNum" name="tel3"></td>
				</tr>

				<tr>
					<th>이 &nbsp;&nbsp; 메 &nbsp;&nbsp; 일</th>
					<td><input type="text" id="joinUserEmail" name="email"></td>
				</tr>

				<tr>
					<th>주 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 소</th>
					<td id="address"><input type="text" id="sample6_postcode"
						value="${voForModify.address1}" placeholder="우편번호" name="Address1"
						style="width: 80px; font-size: 20px;"> <input
						type="button" id="addressbtn" style="width: 200px;"
						class="btn-primary  btn-lg" onclick="sample6_execDaumPostcode()"
						value="우편번호 찾기"><br> <input type="text"
						id="sample6_address" placeholder="주소" name="Address2"
						style="height: 40px;" value="${voForModify.address2}"> <input
						type="text" id="sample6_detailAddress" placeholder="상세주소"
						name="Address3" style="height: 40px;"
						value="${voForModify.address3}"></td>

				</tr>

				<tr>
					<th></th>
					<td><br> <input id="joinMemberSubmit"
						class="btn-primary btn-lg" style="width: 200px;" type="submit"
						value="회원가입" onclick="sendIt()">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="joinMemberRetry" type="reset" value="다시작성"
						type="button" class="btn-primary btn-lg" style="width: 200px;"></td>

				</tr>
			</table>

			<div id="footer">
	<img src="imgs/footer1.png">
			</div>
		</div>
	</form>

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
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document
										.getElementById("sample6_detailAddress").value = extraAddr;

							} else {
								document
										.getElementById("sample6_detailAddress").value = '';
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

		function passwordCheck() {
			var pw = $('#joinUserPassword').val();
			var pwCheck = $('#joinUserPasswordCk').val();

			if (pw != pwCheck) {
				$('#pwCkResult').html('비밀번호가 일치하지 않습니다');
			} else {
				$('#pwCkResult').html('비밀번호가 일치합니다');
			}
		}

		function sendIt() {
			if (f.id.value == "") {

				alert("아이디를 입력하지 않았습니다.")

				f.id.focus()

				return false;

			}

			if ($('#isCheck').val() == "NO") {
				alert("중복확인 해주세요");

				return false;
			}

			if (document.f.joinUserPassword.value == "") {

				alert("비밀번호를 입력하지 않았습니다.")

				document.f.joinUserPassword.focus()

				return false;

			}

			if (document.f.joinUserName.value == "") {

				alert("이름을 입력하지 않았습니다.")

				document.f.joinUserName.focus()

				return false;

			}

			if (document.f.joinUserEmail.value == "") {

				alert("이메일을 입력하지 않았습니다.")

				document.f.joinUserEmail.focus()

				return false;

			}

			document.f.submit()
			

		}

		$(function() {
			//아이디 중복체크
			$('#checkid').on('click', function() {
				$.ajax({
					type : "POST",
					url : "checkId.do",
					data : {
						"id" : $('#joinUserId').val()
					},
					success : function(data) { //data : checkId.do에서 넘겨준 결과값

						if ($('#joinUserId').val() == "") {
							alert("아이디를 입력하세요")

						} else if ($.trim(data) == "YES") {

							alert("사용가능한 아이디입니다.");
							 $('#isCheck').val('YES');
						} else if ($.trim(data) == "NO") {

							alert("중복된 아이디 입니다");
							$('#joinUserId').val('');
							$('#joinUserId').focus();

						}
					}
				})
			})

		});
	</script>
	<scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</body>
</html>

