<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang | 식품</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
h1 {
	height: 50px;
	font-size: 3em;
	font-weight: bold;
	color: #337ab7;
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 3em;
}

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

#categoryfood ul li {
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
		<center>
			<table>
				<tr>
					<h1>CategoryFood</h1>
					<div id="categoryfood">
						<ul>

							<li><a href="foodorganic.do?p_category=organic"><img
									src="./imgs/categoryimg/food/organicfoodver2.png" width=130
									alt="유기농식품">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>유기농 식품</p>
									</button></a></li>


							<li><a href="foodorganic.do?p_category=processed"><img
									src="./imgs/categoryimg/food/processedfoodver2.png" width=130
									alt="가공식품">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>가공식품</p>
									</button></a></li>

							<li><a href="foodorganic.do?p_category=instance"><img
									src="./imgs/categoryimg/food/junkfoodver2.png" width=130
									alt="인스턴스식품">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>인스턴스 식품</p>
									</button></a></li>

							<li><a href="foodorganic.do?p_category=dringkcafe"><img
									src="./imgs/categoryimg/food/dringkcafever2.png" width=130
									alt="커피음료">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>커피&음료</p>
									</button></a></li>
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