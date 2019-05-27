<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang | 뷰티</title>
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
	margin: 10px;
	border-bottom: 2px solid #3aabd3;
	height: 100px;
}

#header img, #footer img {
	width: 100%;
	height: 100%;
}

#categorybeauty ul li {
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
					<h1>Categorybeauty</h1>

					<div id="categorybeauty">
						<ul>
							<li><a href="foodorganic.do?p_category=realbeauty"><img
									src="./imgs/categoryimg/beauty/cosmeticver2.png" width=130
									alt="화장품">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>화장품</p></a>
								</button></li>

							<li><a href="foodorganic.do?p_category=perfume"><img
									src="./imgs/categoryimg/beauty/perfumever2.png" width=130
									alt="향수">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>향수</p>
									</button></a></li>

							<li><a href="foodorganic.do?p_category=tool"><img
									src="./imgs/categoryimg/beauty/Beautyver2.png" width=130
									alt="미용기구">
									<button id="btnset" type="button" class="btn-link btn-lg">
										<p>미용</p>
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