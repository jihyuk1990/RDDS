<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv='Refresh' content="5 url=selectorganic.do?p_number=${select.p_number}&p_category=${select.p_category}&pagenum=${pageval}"> 

<script src="https://code.jquery.com/jquery-3.4.0.js"
	integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
	crossorigin="anonymous"></script>

<script type="text/javascript" src=js/bootstrap.js"> </script>

<title>Insert title here</title>

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

#viewinfo {
	display: inline-block;
	margin: 10px;
	overflow: hidden;
}

#viewinfo .viewinfo_img {
	float: left;
	width: 300px;
}

#viewinfo .viewinfo_img img {
	width: 100%;
	height: 300px;
	border: 1px #cccccc solid;
	height: 300px;
}

#viewinfo .viewinfo_div {
	float: left;
	width: 350px;
	height: 55px;
	margin: 10px;
	margin-left: 60px;
}

#viewinfo .viewinfo_input {
	width: 150px;
	height: 40px;
	border: none;
	border-bottom: 2px solid #3aabd3;
	float: left;
	font-size: 130%;
	line-height: 55px;
	margin-left: 10px;
}

#viewinfo .viewinfo_p {
	width: 150px;
	height: 40px;
	font-weight: bold;
	border: none;
	float: left;
	font-size: 130%;
	margin: 0px;
	line-height: 55px;
	text-align: right;
	border: none;
}


#btnset #btnst, #modifyproduct  {
	padding: 5px;
	width: 200px;
	height: 50px;
	font-size: 15pt;
	font-weight: bold;
	border: none;
}

#btnset, #inputToBasket, #backpage {
	padding: 5px;
	width: 200px;
	height: 50px;
	font-size: 15pt;
	font-weight: bold;
	border: none;
}

#explain {
	width: 780px;
	height: 400px;
	boder-top: 2px solid #3aabd3;
	overflow-y: scroll;
	border: 3px #337ab7 dashed;
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

#btnset {
	overflow: hidden;
}

#btnset input {
	float: left;
	width: 100px;
}

#p_amount {
	font-weight: bold;
	border: none;
	border-bottom: 2px dashed #337ab7;
	float: left;
	font-size: 130%;
	margin: 0 0 0 20px;
	line-height: 30px;
	text-align: right;
	width: 50px;
	height: 55px;
}
</style>
</head>
<body oncontextmenu="return false">
	<form action="deleteproduct.do?pagenum=${pageval}" method="post">
		<div id="con">

			<div id="header">
				<a href="hompage.do"><img src="imgs/logover2.png"></a>
			</div>

			<div>
				<h1 id="headtext">품목 상세</h1>
			</div>

			<div id="viewinfo">
				<div class="viewinfo_img">
					<img id="img1" src="${select.p_photourl}"> <input type="text"
						id="p_photourl" style="display: none;"
						value="${select.p_photourl }" name="p_photourl" />
				</div>

				<div class="viewinfo_div">
					<p>
						<input id="p_number" name="p_number" type="text"
							value="${select.p_number}" readonly="readonly"
							style="display: none;">
					</p>

					<p>
						<input id="p_category" name="p_category" type="text"
							value="${select.p_category}" readonly="readonly"
							style="display: none;">
					</p>

					<p class="viewinfo_p">상품 이름 :</p>
					<input class="viewinfo_input" id="p_name" name="p_name" type="text"
						value="${select.p_name}" readonly="readonly">
				</div>

				<div class="viewinfo_div">
					<p class="viewinfo_p">상품 가격 :</p>
					<input class="viewinfo_input" id="p_price" name="p_price"
						type="text" value="${select.p_price}" readonly="readonly">
				</div>

				<div class="viewinfo_div">
					<p class="viewinfo_p">재 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 고:</p>
					<input class="viewinfo_input" id="p_stock" name="p_stock"
						type="text" value="${select.p_stock}" readonly="readonly">

				</div>

                
				<div id="btnset" class="viewinfo_div">
					<a href="foodorganic.do?pagenum=${pageval}&p_category=${category}"><input id="backpage" type="button" name="btnbasket"
						class="btn btn-primary btn-xs" value="목록"
						style="width: 100px; height: 40px;"></a> 
						
						
						<a
						href="itemmodify.do?p_number=${select.p_number}&p_category=${select.p_category}&pagenum=${pageval}"><input
						id="modifyproduct" type="button" name="modifyproduct"
						class="btn btn-primary btn-xs" value="상품수정"
						style="width: 100px; height: 40px; margin-left: 20px"></a> 
						
						
						
						<input
						id="btnst" type="submit" name="deleteproduct" value="상품삭제"
						style="width: 100px; height: 40px; margin-left: 20px"
						class="btn btn-primary btn-xs" onclick="javascript:btn()">>

				</div>
			</div>

			<div id="explain">
				<p style="font-size: 2em; text-align: center;">품목 설명 란</p>
				<p style="font-size: 1.5em; margin-left: 10px;">${select.p_explain}</p>
			</div>

			<div id="footer">
				<img src="imgs/footer1.png">
			</div>
		</div>
	</form>

	<script type="text/javascript">
		function btn() {
			alert('상품이 삭제되었습니다.');
		}
	</script>
</body>
</html>