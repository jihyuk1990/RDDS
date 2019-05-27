<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang | Delivery</title>
<style>
#con {
	width: 700px;
	margin: 0 auto;
	text-align: center;
}

#con #title {
	width: 680px;
	margin: 10px;
	border-bottom: 2px solid #3aabd3;
}

#con #header {
	width: 680px;
	margin: 10px;
}

#con #table {
	border: 2px solid #3aabd3;
	overflow: hidden;
	width: 680px;
	margin: 10px;
}

#table_header {
	border-bottom: 1px solid #3aabd3;
}

#con #table .content {
	float: left;
	width: 134px;
	height: 135px;
	border: 1px solid #3aadb3
}

.content img {
	width: 100%;
	height: 100%;
	border-right: 2px solid #3aadb3;
}

.content .p_name {
	margin: 0px;
	padding: 5px 0px 7px;
	border-bottom: 2px solid #3aadb3;
}

.content .p_content {
	margin: 0px;
	padding-top: 34px;
}
</style>
</head>
<body>
	<div id="con">
		<div id="title">
			<h1>주문 / 배송 페이지</h1>
		</div>

		<div id="header">
			<h1>주문 정보</h1>
		</div>


		<c:forEach var="list" items="${orderListVO}">
			<div id="table">
				<div id="table_header">
					<h2>2019-05-03</h2>
				</div>

				<div id="table_nav" class="content">
					<img src="${list.p_photourl}">
				</div>

				<div class="content">
					<p class="p_name">상품이름</p>
					<p class="p_content">${list.p_name }</p>
				</div>
				<div class="content">
					<p class="p_name">수량/가격</p>
					<p class="p_content">${list.p_amount }/${list.p_price }</p>
				</div>

				<div class="content">
					<p class="p_name">총액</p>
					<p class="p_content">${list.allpay_price }</p>
				</div>

				<div class="content">
					<p class="p_name">배송상태</p>
					<p class="p_content">${list.p_delivery}</p>
				</div>

			</div>
		</c:forEach>
	</div>

</body>
</html>