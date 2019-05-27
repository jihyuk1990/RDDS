<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang | 생활용품</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
a {
	text-decoration: none;
	color: #blue
}

ul li p {
	margin: 0 10px;
	font-weight: bold;
}

ul li #btnset {
	margin-top: 10px;
}

h1 {
	height: 50px;
	font-size: 3em;
	font-weight: bold;
	color: #337ab7;
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 3em;
}

#header, #footer {
	width: 780px;
	height: 100px;
	margin: 10px;
	border-bottom: 2px solid #3aabd3;
}

#header img, #footer img {
	width: 100%;
	height: 100%;
}

#categorydailysupplies ul li {
	display: inline-block;
	width: 180px;
	height: 100px;
	text-align: center;
}

#wrap {
	width: 800px;
	margin: 0 auto;
	text-align: center;
}
/*보더 라인 생성*/
#history2::first-letter, #history1::first-letter {
	font-size: 2em;
}

#btnset {
	border: 2px outset #337ab7;
}
</style>
</head>
<body>
	<div id="wrap">
		<!-- wrap 으로 감싸 놓았다 이러한 형식도 있다-->
		<div id="header">
			<a href="hompage.do"><img src="imgs/logover2.png"></a>
		</div>
	</div>
	<center>
		<table>
			<tr>
				<h1>CategoryDailySupplies</h1>
				<div id="categorydailysupplies">
					<ul>
						<li><a href="foodorganic.do?p_category=kitchen"><img
								src="./imgs/categoryimg/dailysupplies/kitchenver2.png" width=130
								alt="주방">
								<button id="btnset" type="button" class="btn-link btn-lg">
									<p>주방</p></a>
							</button></li>

						<li><a href="foodorganic.do?p_category=bathroom"><img
								src="./imgs/categoryimg/dailysupplies/bathroomver2.png"
								width=130 alt="욕실">
								<button id="btnset" type="button" class="btn-link btn-lg">
									<p>욕실</p></a>
							</button></li>

						<li><a href="foodorganic.do?p_category=storage"><img
								src="./imgs/categoryimg/dailysupplies/storagecleanver2.png"
								width=130 alt="수납정리">
								<button id="btnset" type="button" class="btn-link btn-lg">
									<p>수납&정리</p></a>
							</button></li>

						<li><a href="foodorganic.do?p_category=cleaning"><img
								src="./imgs/categoryimg/dailysupplies/cleaningver2.png"
								width=130 alt="청소">
								<button id="btnset" type="button" class="btn-link btn-lg">
									<p>청소</p></a>
							</button></li>
					</ul>
				</div>
		</table>
	</center>
	</div>
	<center>
		<div id="footer">
			<img src="./imgs/footer1.png">
	</center>
	</div>
	<scrit type="text/javascript" src=js/bootstrap.js"> </script> <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->
</body>
</html>