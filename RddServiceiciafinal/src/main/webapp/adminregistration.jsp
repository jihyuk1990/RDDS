<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="http://code.jquery.com/jquery-latest.js"></script>

<script src="https://code.jquery.com/jquery-3.4.0.js"
	integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
	crossorigin="anonymous"></script>

<script type="text/javascript" src=js/bootstrap.js">
	
</script>

<!-- <script language='javascript'>
   $(function() {
      $('#inputToBasket').on('click', function() {

         $.ajax({
            type : "POST",
            url : "inputToBasket.do",
            data : {
               "p_number" : $('#p_number').val(),
               "p_name" : $('#p_name').val(),
               "p_price" : $('#p_price').val(),
               "p_amount" : $('#p_amount').val(),
               "p_photourl" : $('#p_photourl').val(),
               "p_category" : $('#p_category').val()
            },
            success : function(data) {
               var result = data;
               console.log(data);
               if (data == "YES") {
                  alert("해당 상품이 장바구니에 추가되었습니다.");
               } else if (data == "NO") {
                  alert("재고부족입니다.");
               } else {
                  alert("로그인부터 하세요.");
               }
            }
         })
      })

   });
</script> -->

<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->

<style>
#con {
	width: 800px;
	margin: 0 auto;
}

#header {
	height: 100px;
	margin: 10px;
	border-bottom: 2px solid #3aabd3;
}

#header img {
	width: 100%;
	height: 100%;
}

#headtext {
	color: #337ab7;
	font-weight: bold;
	margin: 10px;
	height: 80px;
	border-bottom: 2px #337ab7 dashed;
	font-size: 3em;
	margin: 10px;
}

#registration {
	width: 780px;
	height: 420px;
	margin: 10px;
	overflow: hidden;
}

#registration_img {
	width: 350px;
	height: 350px;
	margin: 10px;
	float: left;
}

#registration_img img {
	width: 350px;
	height: 350px;
}

#registration_inputspace {
	width: 350px;
	height: 350px;
	font-size: 15px;
	font-weight: bold;
	color: #3DA0C0;
	margin: 10px 5px;
	float: left;
	margin: 5px;
}

#registration_inputspace #registration_catechoice select {
	width: 150px;
	height: 40px;
	border: none;
	border-bottom: 2px #337ab7 dashed;
}

#registration_inputspace #p_number {
	width: 30px;
	height: 40px;
	border: none;
	border-bottom: 2px #337ab7 dashed;
	text-align: center;
}

#registration_inputspace .registration_input {
	width: 200px;
	height: 40px;
	border: none;
	border-bottom: 2px #337ab7 dashed;
}

#registration_explain {
	width: 780px;
	height: 400px;
	margin: 10px;
	border: 2px #337ab7 dashed;
}

#footer {
	clear: both;
	border: 1px solid #cccccc;
	margin: 5px;
	text-align: center;
	font-size: 1.5em;
	font-weight: bold;
	width: 780px;
}

#footer img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>

	<form action="iteminsert.do" method="post"
		enctype="multipart/form-data">
		<div id="con">

			<div id="header">
				<a href="hompage.do"><img src="imgs/logover2.png"></a>
			</div>

			<div>
				<h1 id="headtext">상품 등록</h1>
			</div>

			<div id="registration">
				<div id="registration_img">
					<img id="imgs">


					<div id="사진의 파일 이름이 들어감과 동시에 위에 이미지에 출력 해야함.">
						<input type="file" name="file" class="btn btn-primary btn-ms"
							id="fileimg" style="width: 350px; height: 40px;" />
					</div>
				</div>

				<div id="registration_inputspace">
					<div class="registration_p_number">
						<h3 style="color: #337ab7; margin-left: 100px; font-weight: bold;">상품
							정보 입력</h3>

					</div>
					<div id="registration_catechoice">
						<p>
							대분류 : <select name="p_tabler" id="select1">
								<option value="123">--선택--</option>
								<option value="rddfood">식품</option>
								<option value="rddclothes">의류</option>
								<option value="rdddaily">생활용품</option>
								<option value="rddbeauty">미용</option>

							</select>
						</p>

						<p>



							중분류 : <select name="p_category" id="select2">
								<option>--카태고리를 선택하세요--</option>

							</select>
						</p>
					</div>
					<div>
						<p class="registration_p">
							상품 이름 : <input class="registration_input" id="p_name"
								name="p_name" type="text" >
						</p>
					</div>

					<div class="registration_div">
						<p class="registration_p">
							상품 가격 : <input class="registration_input" id="p_price"
								name="p_price" type="text" >
						</p>
					</div>

					<div class="registration_div">
						<p class="registration_p">
							수 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 량 : <input type="text"
								value="" name="p_stock"
								style="width: 40px; height: 40px; border: none; border-bottom: 2px #337ab7 dashed; text-align: center;">
							개
						</p>
					</div>
				</div>


			</div>

			<div id="registration_explain">
				<p style="font-size: 2em; text-align: center;">품목 설명 란</p>
				<p style="font-size: 1.5em; margin-left: 10px;">
					<textarea name="p_explain"
						style="width: 700px; height: 320px; margin-left: 25px; border: none;"></textarea>
				</p>
			</div>
			<div class="registration_div">

				<button type="submit" class="btn btn-primary btn-lg" value="등록"
					id="submit"
					style="margin-right: 20px; margin-bottom: 20px; float: right; width: 100px; height: 50px;">등
					&nbsp;&nbsp;&nbsp;&nbsp; 록</button>
			</div>

			<div id="footer">
				<img src="imgs/footer1.png">
			</div>
		</div>
	</form>

	<script type="text/javascript">
	
	
	
	
		$(document).ready(function() {
			$("#fileimg").on("change", handIeImgFileSelect);
		});

		function handIeImgFileSelect(e) {
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);

			filesArr.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("확장자는 이미지 확장자만 가능합니다.");
					return;
				}

				sel_file = f;

				var reader = new FileReader();
				reader.onload = function(e) {
					$("#imgs").attr("src", e.target.result);
				}
				reader.readAsDataURL(f);
			});
		}

		$(document)
				.ready(
						function() {
							$("#select1")
									.click(
											function() {
												if ($("#select1").val() == "123") {
													$("select[id='select2']")
															.html(
																	'<option value="">--카태고리를 선택하세요--</option>');

												} else if ($("#select1").val() == 'rddfood') {
													$("select[id='select2']")
															.html(
																	'<option value="">--선택--</option>');
													$("select[id='select2']")
															.append(
																	'<option value="organic">유기농</option>');

													$("select[id='select2']")
															.append(
																	'<option value="processedfood">가공식품</option>');

													$("select[id='select2']")
															.append(
																	'<option value="junkfood">인스턴트식품</option>');

													$("select[id='select2']")
															.append(
																	'<option value="dringkcafe">커피&음료</option>');

												} else if ($("#select1").val() == 'rddclothes') {
													$("select[id='select2']")
															.html(
																	'<option value="">--선택--</option>');
													$("select[id='select2']")
															.append(
																	'<option value="girlclothes">여자옷</option>');

													$("select[id='select2']")
															.append(
																	'<option value="boyclothes">남자옷</option>');

													$("select[id='select2']")
															.append(
																	'<option value="babyclothes">아동복</option>');

												} else if ($("#select1").val() == 'rdddaily') {
													$("select[id='select2']")
															.html(
																	'<option value="">--선택--</option>');
													$("select[id='select2']")
															.append(
																	'<option value="kitch">주방용품</option>');
													$("select[id='select2']")
															.append(
																	'<option value="bathroom">욕실용품</option>');

													$("select[id='select2']")
															.append(
																	'<option value="storage">수납&정리</option>');

													$("select[id='select2']")
															.append(
																	'<option value="cleaning">청소용품</option>');

												} else if ($("#select1").val() == 'rddbeauty') {
													$("select[id='select2']")
															.html(
																	'<option value="">--선택--</option>');
													$("select[id='select2']")
															.append(
																	'<option value="cosmetic">화장품</option>');

													$("select[id='select2']")
															.append(
																	'<option value="perfume">향수</option>');

													$("select[id='select2']")
															.append(
																	'<option value="beauty">미용기구</option>');
												}
											});
						});
	</script>
</body>
</html>