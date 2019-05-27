<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Drone Pang | ${organicVO.p_category }</title>
<link rel="stylesheet" href="css/bootstrap.css">
<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<style>
#con {
	width: 800px;
	margin: 5px auto;
}

#con #header, #con #header {
	width: 780px;
	height: 100px;
	border-bottom: 2px solid #3aabd3;
	margin: 10px;
}

#con #header img, #con #footer img {
	width: 100%;
	height: 100%;
}

#con #list {
	width: 790px;
	margin: 5px;
	height: 50px;
	line-height: 50px;
	text-align: center;
	font-size: 200%;
	font-weight: bold;
}

#con #content {
	width: 790px;
	margin: 5px;
}

#con #content ul {
	width: 790px;
	padding-left: 0px;
	overflow: hidden;
	text-align: center;
}

#con #content ul li {
	width: 185px;
	list-style: none;
	float: left;
	margin: 10px 5px;
}

#con #content ul li a dl {
	margin-bottom: 0px;
	margin-top: 0px;
}


#con #content ul li dl dd {
	padding-left: 0px;
	margin-left: 0px;
}

#con #content ul li dl dt img {
	width: 140px;
	height: 140px;
}

#content #dfooter {
	overflow: hidden;
	text-align: center;
}

#content #dfooter .pagebtn {
	width: 29px;
	height: 29px;
	outline: 0;
	background-color: #ffffff;
	border: 1px #cccccc solid;
}

#content #dfooter .aquo {
	width: 31px;
	height: 31px;
	outline: 0;
	background-color: #ffffff;
	border: 1px #cccccc solid;
}

.active {
	background-color: #3aabd3;
	color: white;
	width: 29px;
	height: 29px;
	outline: 0;
	border: 1px #cccccc solid;
}
</style>
</head>
<body>

	<div id="con">
		<div id="header">
			<a href="hompage.do"><img src="imgs/logover2.png"></a>
		</div>

		<div id="list">상품 리스트</div>

		<div id="content">
			<ul>
				<c:forEach var="list" items="${organiclist}">
					<li><a
						href="selectorganic.do?pagenum=${pageval }&p_number=${list.p_number}&p_category=${list.p_category}">
							<dl>
								<!-- 목록 -->
								<dt>
									<!--용어  -->
									<img src="${list.p_photourl }" alt="사진안뜸" class="img-circle">
								</dt>

								<dd>
									<!-- 설명 -->
									<div>${list.p_name}</div>
									<div>${list.p_price }</div>
									<input name="p_category" type="text" value="${list.p_category}"
										style="display: none;"> <input name="p_stock"
										type="text" value="${list.p_stock}" style="display: none;">
								</dd>
							</dl>
					</a></li>
				</c:forEach>
			</ul>

			<div id="dfooter">
				<c:if test="${page.prev}">
					<a style="text-decoration: none;" type="hidden"
						href="foodorganic.do?p_category=${organicVO.p_category}&pagenum=${page.startPage-1}&search=${search_val}">
						<input type="button" value="&laquo;" class="aquo">
					</a>
					<!-- 이전 페이지 화살표 -->
				</c:if>




				<!-- begin = forEach 시작지점, end = forEach 끝나는 지점 -->
				<c:forEach begin="${page.startPage}" end="${page.endPage }"
					var="idx">

					<a style="text-decoration: none;"
						href="foodorganic.do?p_category=${organicVO.p_category}&pagenum=${idx }&search=${search_val}">
						<input type="button" value="${idx }" class="pagebtn" id="text"
						name="text">
					</a>

				</c:forEach>



				<c:if test="${page.next}">
					<a style="text-decoration: none;"
						href="foodorganic.do?p_category=${organicVO.p_category}&pagenum=${page.endPage+1}&search=${search_val}">
						<input type="button" value="&raquo;" class="aquo">
					</a>
					<!-- 다음 페이지 화살표 -->
				</c:if>
			</div>


		</div>

		<div id="footer">
			<img src="./imgs/footer1.png">
		</div>
	</div>
	<script type="text/javascript" src=js/bootstrap.js">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- j쿼리 추가 부트스트랩의 js는 내부적으로 순수 자바스크립트가 아닌 j쿼리로 구성되어 있어서 반드시  j쿼리를 추가해야 사용가능 -->

	<script type="text/javascript">
		$(document).ready(function() {

			$("input[name=text]").each(function(idx) {
				var value = parseInt($(this).val());
				console.log("value : " + value);

				var pageval = ${page.pagenum}+1;
				
				console.log("val : " + pageval);

				var text = value + pageval;
				console.log("text : " + text);

				if (value == pageval) {
					$(this).removeClass('pagebtn');
					$(this).addClass('active');
				}
			});
		});
	</script>
</body>
</html>