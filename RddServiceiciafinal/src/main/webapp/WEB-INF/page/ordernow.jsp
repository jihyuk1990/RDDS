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
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>Drone Pang | ${ordervo.p_name} 주문/결제</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<script type="text/javascript">
	function insertMemberInfo() {
		if ($('#checkbox').is(":checked")) {

			var name = $('#memberName').val();
			var address1 = $('#memberAddress1').val();
			var address2 = $('#memberAddress2').val();
			var address3 = $('#memberAddress3').val();
			var tel1 = $('#memberTel1').val();
			var tel2 = $('#memberTel2').val();
			var tel3 = $('#memberTel3').val();
			$('#name').val(name);
			$('#sample6_postcode').val(address1);
			$('#sample6_address').val(address2);
			$('#sample6_detailAddress').val(address3);
			$('#tel1').val(tel1);
			$('#tel2').val(tel2);
			$('#tel3').val(tel3);

		} else {

		}
	}

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

	/* function orderIt() {
	   if (f.name.value == "") {
	      alert("받는분의 성함을 입력하세요")
	      f.name.focus()
	      return false;
	   }

	   if (f.sample6_postcode.value == "") {
	      alert("받는분의 우편번호를 입력하세요")
	      f.sample6_postcode.focus()
	      return false;
	   }

	   if (f.sample6_address.value == "") {
	      alert("받는분의 주소를 입력하세요")
	      f.sample6_address.focus()
	      return false;
	   }

	   if (f.sample6_detailAddress.value == "") {
	      alert("받는분의 상세주소를 입력하세요")
	      f.sample6_detailAddress.focus()
	      return false;
	   }

	   if (f.tel1.value == "") {
	      alert("받는분의 전화번호를 입력하세요")
	      f.tel1.focus()
	      return false;
	   }

	   if (f.tel2.value == "") {
	      alert("받는분의 전화번호를 입력하세요")
	      f.tel2.focus()
	      return false;
	   }

	   if (f.tel3.value == "") {
	      alert("받는분의 전화번호를 입력하세요")
	      f.tel3.focus()
	      return false;
	   }

	   if ($(':radio[name="paymethod"]:checked').length < 1) {
	      alert("결제방법을 선택하세요")
	      return false;

	   }

	} */
</script>

<!-- <script>
   $("#check_module").click(function() {
      
      var IMP = window.IMP; // 생략가능
      IMP.init('imp20765320');
      // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
      // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
      IMP.request_pay({
         pg : 'inicis', // version 1.1.0부터 지원.
         /*
          'kakao':카카오페이,
          html5_inicis':이니시스(웹표준결제)
          'nice':나이스페이
          'jtnet':제이티넷
          'uplus':LG유플러스
          'danal':다날
          'payco':페이코
          'syrup':시럽페이
          'paypal':페이팔
          */

         pay_method : 'phone',
         /*
          'samsung':삼성페이,
          'card':신용카드,
          'trans':실시간계좌이체,
          'vbank':가상계좌,
          'phone':휴대폰소액결제
          */
         merchant_uid : 'merchant_' + new Date().getTime(),
         /*
          merchant_uid에 경우
          https://docs.iamport.kr/implementation/payment
          위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
          참고하세요.
          나중에 포스팅 해볼게요.
          */
         name : '주문명:결제테스트',
         //결제창에서 보여질 이름
         amount : 1000,
         //가격
         buyer_email : 'iamport@siot.do',
         buyer_name : '구매자이름',
         buyer_tel : '010-1234-5678',
         buyer_addr : '서울특별시 강남구 삼성동',
         buyer_postcode : '123-456',
         m_redirect_url : 'https://www.yourdomain.com/payments/complete'
      /*
       모바일 결제시,
       결제가 끝나고 랜딩되는 URL을 지정
       (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
       */
      }, function(rsp) {
         console.log(rsp);
         if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
         } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
         }
         alert(msg);
      });
   });
</script> -->









<style>
/*전체 틀*/
#con {
	width: 800px;
	margin: 10px auto;
	text-align: center;
	overflow: hidden;
}
/*로고 크기 지정*/
#header {
	width: 780px;
	height: 100px;
	border-bottom: 2px solid #3aabd3;
	margin: 10px;
}
/*이미지의 크기 지정*/
#header img {
	width: 100%;
	height: 100%;
}
/*소제목*/
#con #nav_header h3 {
	color: #337ab7;
	font-weight: bold;
	font-size: 40px;
	font-weight: bold;
}

#con #nav_member h2, #con #nav_members h2, #con #order h2, #con #nav_cash h2
	{
	color: #black;
	font-weight: bold;
	font-size: 30px;
	text-align: left;
}

#con #order h2 {
	margin-bottom: 20px;
}

#con #nav_members #test {
	color: #black;
	font-weight: bold;
	font-size: 20px;
}

#con #nav_members #test p {
	margin-top: 10px;
}

#con #nav_member {
	width: 780px;
	border-top: 2px dashed #337ab7;
	border-bottom: 1px dotted #337ab7;
}

#con #order #order_div {
	width: 780px;
	margin: 5px 0px;
}

#con #nav_members {
	width: 780px;
	border-bottom: 1px dotted #337ab7;
}

#con #order {
	width: 780px;
	font-size: 20px;
	font-weight: bold;
	border-bottom: 1px dotted #337ab7;
}

#con #nav_member table tr, #con #nav_members table tr {
	color: #black;
	float: left;
	font-weight: bold;
	font-size: 20px;
}

#con #nav_member table tr td, #con #nav_members table tr td, #con #nav_members table tr th
	{
	width: 100px;
	height: 40px;
}

#con #nav_members table tr th {
	padding-bottom: 80px;
}

#con #nav_members table tr td {
	width: 100px;
	height: 60px;
}

#con #nav_member table input, #con #nav_members table input {
	border: none;
	border-bottom: 2px solid #337ab7;
	float: left;
	text-align: center;
	font-weight: bold;
	font-size: 20px;
	font-weight: bold;
}
/*p태그 인풋 디자인 지정*/
#con #order #inputvalue input {
	border: none;
	border-bottom: 2px solid #337ab7;
	text-align: center;
	font-weight: bold;
	font-size: 20px;
	width: 80px;
	margin: 5px;
}

#con #order #inputvalue {
	margin-left: 20px;
}

#memberTel1, #memberTel2, #memberTel3, #memberName, #memberAddress1,
	#sample6_postcode, #name, #tel1, #tel2, #tel3 {
	width: 80px;
}

#memberAddress2, #memberAddress3, #sample6_address,
	#sample6_detailAddress {
	width: 280px;
}

#con #nav {
	width: 790px;
	margin: 5px;
}

#con #header img, #con #footer img {
	width: 100%;
}

#con #nav #nav_member table, #con #nav #nav_members table, #con #nav #nav_cash table,
	#con #nav #order table {
	width: 780px;
	margin-bottom: 5px;
}

#con #nav #nav_members table tr, #con #nav #nav_member table tr {
	width: 100%;
	margin: 5px 0px;
}

#con #nav #submit {
	margin: 5px 0px;
	text-align: center;
}
/*footer 이미지 틀 지정*/
#footer {
	width: 780px;
	height: 100px;
	margin: 10px;
}

#footer img {
	width: 100%;
	height: 100%;
}

#order div {
	display: inline-block;
	float: left;
}

#checkbox {
	width: 100px;
	height: 100px;
	border: 3px solid;
}

#nav_cash div p {
	text-align: left;
	font-size: 20px;
	font-weight: bold;
}

#nav_cash div p input {
	border: none;
	border-bottom: 2px solid #337ab7;
	width: 100px;
	text-align: center;
}

#nav_cash #cash_select {
	text-align: left
}

#nav_cash #cash_select ul {
	width: 790px;
	list-style-type: none;
	font-size: 20px;
	display: inline-block;
	list-style-type: none;
	margin: 10px 0px;
	border-bottom: 2px dashed #337ab7;
}

#nav_cash #cash_select ul li {
	display: inline-block;
	margin: 10px 30px;
	font-weight: bold;
}
</style>

</head>
<body>

	<form id="form" name="f" action="ordercomplete.do" method="post">

		<div id="con">
			<div id="header">
				<a href="hompage.do"><img src="imgs/logover2.png"></a>
			</div>
			<div id="nav">

				<div id="nav_header">
					<h3>주문/결제</h3>
				</div>

				<div id="nav_member">
					<h2>구매자정보</h2>
					<table>
						<tr>
							<td>이 &nbsp;&nbsp;&nbsp;&nbsp; 름 :</td>
							<td><input type="text" id="memberName" value="${covo.name}"
								readonly /></td>
						</tr>
						<tr>
							<td>이&nbsp;&nbsp;메&nbsp;&nbsp;일:</td>
							<td><input type="text" id="memberEmail"
								value="${covo.email}" readonly /></td>
						</tr>
						<tr>
							<td>휴대전화 :</td>
							<td><input type="text" id="memberTel1" value="${covo.tel1}"
								readonly />-</td>
							<td>-<input type="text" id="memberTel2" value="${covo.tel2}"
								readonly />
							</td>
							<td><input type="text" id="memberTel3" value="${covo.tel3}"
								readonly /></td>
						</tr>



						<tr>
							<td>주 &nbsp;&nbsp;&nbsp;&nbsp; 소 :</td>
							<td><input type="text" id="memberAddress1"
								value="${covo.address1}" readonly /></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>

						</tr>
						<tr>
							<td></td>
							<td><input type="text" id="memberAddress2"
								value="${covo.address2}" readonly /></td>
							<td>상세주소 :</td>
							<td><input type="text" id="memberAddress3"
								value="${covo.address3}" readonly /></td>
						</tr>
					</table>
				</div>

				<div id="nav_members">
					<div id="test">
						<h2>받는사람정보</h2>
						<p style="float: left;">
							<input id="checkbox" type="checkbox"
								onclick="insertMemberInfo();" style="width: 15px; height: 15px;">구매자정보와
							동일하다면 체크하세요
						</p>
					</div>
					<table>
						<tr>
							<td>이 &nbsp;&nbsp;&nbsp;&nbsp; 름 :</td>
							<td><input type="text" name="name" id="name"></td>
						</tr>

						<tr id="postbox">
						<tr>
							<th>배송주소 :</th>
							<td id="address"><input type="text" id="sample6_postcode"
								value="${voForModify.address1}" placeholder="우편번호"
								name="Address1"> <input type="button" id="addressbtn"
								class="btn-primary btn-xs" onclick="sample6_execDaumPostcode()"
								value="우편번호 찾기" style="margin-left: 20px;"><br> <input
								type="text" id="sample6_address" placeholder="주소"
								name="Address2" style="height: 40px;"
								value="${voForModify.address2}"> <input type="text"
								id="sample6_detailAddress" placeholder="상세주소" name="Address3"
								style="height: 40px;" value="${voForModify.address3}"></td>
						</tr>
						
						<td>드론배송지주소 :</td>
							<td><input type="text" id="droneaddress" name="droneaddress" placeholder ="드론배송지주소입력" /></td>


						<tr>
							<td>휴대전화 :</td>
							<td><input type="text" name="tel1" id="tel1">-</td>
							<td>-<input type="text" name="tel2" id="tel2"></td>
							<td><input type="text" name="tel3" id="tel3"></td>

						</tr>
						<tr>

							<td>요청사항 :</td>
							<td><input type="text" id="request" name="request" /></td>
						</tr>
					</table>
				</div>

				<div id="order">
					<!-- for문 써서 띄우자(여기선 장바구니에서 구매누르는 경우와 품목상세에서 구매누르는 경우로 나뉜다.) -->


					<div id="order_div">
						<h2>구매 상품</h2>
						<div>
							<img id="img1" src="${ordervo.p_photourl}"
								style="width: 200px; height: 150px;"> <input type="text"
								id="p_photourl" style="display: none;"
								value="${ordervo.p_photourl}" name="p_photourl" />

						</div>

						<div id="inputvalue">
							<p  style="display: none;">
								<input name="p_number" id="p_number" type="text"
									value="${ordervo.p_number}" readonly="readonly">
							</p>
							<p>
								상품 이름: <input name="p_name" id="p_name" type="text"
									value="${ordervo.p_name}" readonly="readonly">
							</p>
							<p>
								상품 수량:&nbsp; <input name="p_amount" type="text"
									value="${ordervo.p_amount}" readonly="readonly">
							</p>
							<p>
								상품 가격:&nbsp;<input name="p_price" type="text"
									value="${ordervo.p_price}" readonly="readonly"> <input
									name="p_category" type="text" value="${ordervo.p_category}"
									style="display: none;">
									<input
									name="p_stock" type="text" value="${organicvo.p_stock}"
									style="display: none;">
								
							</p>
						</div>

					</div>

				</div>

				<div id="nav_cash">
					<h2>결제정보</h2>
					<div style="margin: 15px 0px;">
						<p>
							총결제금액 : <input name="allpay_price" id="allpay_price" type="text"
								value="${ordervo.p_price * ordervo.p_amount}"
								readonly="readonly">
								
								<input id="delivery" name="p_delivery" type="text"
								value="배송전" style="display: none;">
						</p>
					</div>
					<div id="cash_select">
						<p>결제 방법</p>
						<ul>
							<li><input type="radio" name="paymethod" value="trans">
								<span>계좌이체</span></li>
							<li><input type="radio" name="paymethod" value="card">
								<span>신용/체크카드</span></li>
							<li><input type="radio" name="paymethod" value="phone">
								<span>휴대폰</span></li>
							<li><input type="radio" name="paymethod" value="vbank">
								<span>무통장입금</span></li>
						</ul>


					</div>
					<div id="submit">
						<button id="check_module" class="btn-primary btn-lg" value="결제하기"
							type="button">결제하기</button>
					</div>

					<script>
						$("#check_module")
								.click(
										function() {

											/* 유효성검사 시작 */

											if ($('#name').val() == "") {
												alert("받는분의 성함을 입력하세요")
												$('#name').focus();
												return;
											}

											if ($('#sample6_postcode').val() == "") {
												alert("받는분의 우편번호를 입력하세요")
												$('#sample6_postcode').focus()
												return;
											}

											if ($('#sample6_address').val() == "") {
												alert("받는분의 주소를 입력하세요")
												$('#sample6_address').focus()
												return;
											}

											if ($('#sample6_detailAddress')
													.val() == "") {
												alert("받는분의 상세주소를 입력하세요")
												$('#sample6_detailAddress')
														.focus()
												return;
											}

											if ($('#tel1').val() == "") {
												alert("받는분의 전화번호를 입력하세요")
												$('#tel1').focus()
												return;
											}

											if ($('#tel2').val() == "") {
												alert("받는분의 전화번호를 입력하세요")
												$('#tel2').focus()
												return;
											}

											if ($('#tel3').val() == "") {
												alert("받는분의 전화번호를 입력하세요")
												$('#tel3').focus()
												return;
											}

											if ($(':radio[name="paymethod"]:checked').length < 1) {
												alert("결제방법을 선택하세요")
												return;

											}

											/* 유효성검사 끝 */

											var productName = $('#p_name')
													.val();
											var totalPrice = $('#allpay_price')
													.val();
											var buyer_email = $('#memberEmail')
													.val();
											var buyer_name = $('#memberName')
													.val();
											var buyer_tel = $('#memberTel1')
													.val()
													+ "-"
													+ $('#memberTel2').val()
													+ "-"
													+ $('#memberTel3').val();
											var buyer_addr = $(
													'#memberAddress2').val()
													+ " "
													+ $('#memberAddress3')
															.val();
											var buyer_postcode = $(
													'#memberAddress1').val();
											
											var pay_method = $(':radio[name="paymethod"]:checked').val();
											
											alert(pay_method);

											var IMP = window.IMP; // 생략가능
								
											IMP.init('imp68849297');
											// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
											// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
											IMP
													.request_pay(
															{
																pg : 'html5_inicis', // version 1.1.0부터 지원.
																/*
																 'kakao':카카오페이,
																 html5_inicis':이니시스(웹표준결제)
																 'nice':나이스페이
																 'jtnet':제이티넷
																 'uplus':LG유플러스
																 'danal':다날
																 'payco':페이코
																 'syrup':시럽페이
																 'paypal':페이팔
																 */

																pay_method : pay_method,
																/*
																 'samsung':삼성페이,
																 'card':신용카드,
																 'trans':실시간계좌이체,
																 'vbank':가상계좌,
																 'phone':휴대폰소액결제
																 */
																merchant_uid : 'merchant_'
																		+ new Date()
																				.getTime(),
																/*
																 merchant_uid에 경우
																 https://docs.iamport.kr/implementation/payment
																 위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
																 참고하세요.
																 나중에 포스팅 해볼게요.
																 */
																name : productName,
																//결제창에서 보여질 이름
																amount : totalPrice,
																//가격
																buyer_email : buyer_email,
																buyer_name : buyer_name,
																buyer_tel : buyer_tel,
																buyer_addr : buyer_addr,
																buyer_postcode : buyer_postcode,
																m_redirect_url : 'https://www.yourdomain.com/payments/complete'
															/*
															 모바일 결제시,
															 결제가 끝나고 랜딩되는 URL을 지정
															 (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
															 */
															},
															function(rsp) {
																console
																		.log(rsp);
																if (rsp.success) {
																	//결제성공시 여기서 처리!!!
																	$('#form')
																			.submit();

																} else {
																	alert('결제에 실패하였습니다.');
																}

															});
										});
					</script>


				</div>
			</div>


			<div id="footer">
				<img src="./imgs/footer1.png">
			</div>
		</div>
	</form>
	<scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</body>