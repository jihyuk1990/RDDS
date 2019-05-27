<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- bxSlider 연결 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">

<!-- jQuery 연결 -->
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>

<!-- bxSlider 연결 -->
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<!-- 부트 스트랩 -->
<script type="text/javascript" src="js/bootstrap.js"></script>

<!--부트스트랩에서 제공하는 css를 사용 하겠다-->
<link rel="stylesheet" href="css/bootstrap.css">

<script>
	$(function() {
		var slider = $('.bxslider').bxSlider({
			pagerCustom : '#bx-pager', /* 호출기를 채우는 데 사용 된 요소가 호출기를 채 웁니다. 기본적으로 호출기는 bx-viewport에 추가됩니다. */
			auto : true, /* 슬라이드 자동 전환. */
			controls : true, /* true면 다음 / 이전 컨트롤 추가. */
			speed : 1000, /* 슬라이드 넘어가는 시간 */
			pause : 2000, /* 슬라이드 멈추는 시간 */
			minSlides : 1, // 표시할 슬라이드 최소 갯수
			moveSlides : 1, // 슬라이드 이동 갯수
			autoHover : true
		// 마우스 호버시 정지 여부
		});
	});
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang</title>
<style>
/*a태그의 밑줄 라인 제거*/
a {
	text-decoration: none;
}

/*페이지의 전체 틀*/
#outline {
	width: 800px;
	margin: 10px auto;
	text-align: center;
	font-size: 1.5em;
	font-weight: bold;
}
/*메인 로고 전체 틀*/
#header, #footer {
	width: 780px;
	height: 100px;
	border-bottom: 2px solid #3aabd3;
	margin: 10px;
}
/*이미지의 크기 지정*/
#header img, #footer img {
	width: 100%;
	height: 100%;
}
/*로그인 표시 부분의 크기와 디자인 지정*/
#login {
	width: 780px;
	height: 60px;
	margin: 10px;
	font-size: 1em;
	font-weight: bold;
}

#login #logout, #login #member, #login #mypage {
	float: right;
	margin: 0 5px;
}

#login #mypage input {
	padding: 10px 0px;
}

#login #member {
	padding: 14px 0px 10px 0px;
}

#login #logout input {
	padding: 10px 0px;
}
/*로그인 회원가입 아래부분 부터의 전체틀*/
#middleForm {
	width: 780px;
	height: 100px;
	overflow: hidden;
	margin: 10px;
	text-align: center;
}
/*검색 기능의 틀*/
#middleForm #search {
	width: 400px;
	height: 30px;
}
/*카테고리 메뉴 전체 틀*/
#middleForm ul {
	height: 50px;
	display: inline-block;
	padding-left: 0px;
	margin-bottom: 0px;
}
/*카테고리의 각 메뉴의 틀*/
#middleForm ul li {
	list-style: none;
	font-size: 0.5em;
	float: left;
	margin: 0 50px;
	position: relative;
	top: 20px;
}
/*배너의 크기 지정*/
#outline #Banner {
	width: 780px;
	height: 300px;
	overflow: hidden;
	margin: 10px;
	text-align: center;
}

.bx-wrapper {
	margin-bottom: 0px;
	border-width: 0px
}

.title {
	width: 780px;
	margin: 10px;
	text-align: center;
	height: 40px;
	line-height: 40px;
	background-color: #3aabd3;
	color: white;
}

/*슬라이드 기능 사진의 크기 지정*/
#Banner img {
	width: 100%;
	height: 300px;
}
/*각 카테고리 인기 상품 이지미 틀*/
#outline .Content {
	width: 780px;
	margin: 10px;
	overflow: hidden;
}

#outline .Content .nume {
	width: 190px;
	float: left;
	margin: 0 3px;
	text-align: center;
}

#outline .Content .nume1 {
	width: 210px;
	float: left;
	margin: 0 25px;
	text-align: center;
}

#outline .Content .nume dl {
	margin-bottom: 0px;
}

#outline .Content .nume dl dt {
	width: 100%;
	height: 150px;
}

#outline .Content .nume dl dt img {
	width: 100%;
	height: 100%;
}

#outline .Content .nume dl dd {
	margin: 5px 0px;
}

#outline .Content .nume1 dl {
	margin-bottom: 0px;
}

#outline .Content .nume1 dl dt {
	width: 100%;
	height: 150px;
}

#outline .Content .nume1 dl dt img {
	width: 100%;
	height: 100%;
}

#outline .Content .nume1 dl dd {
	margin: 5px 0px;
}

#outline .Content .nheader {
	text-align: center;
	font-size: 15px;
	margin-top: 5px;
	padding-bottom: 5px;
}
/*각 카테고리 메뉴 디자인*/
#catebtn {
	width: 100px;
	height: 30px;
	font-size: 15px;
	border: 2px solid #337ab7;
	font-weight: bold;
}
/*검색 창의 디자인*/
#search {
	border: none;
	border-bottom: 2px solid #337ab7;
}
/*돋보기 버튼의 배경 색과 선을 삭제하고 이미지만 출력 */
#searchbtn {
	border: none;
	background-color: transparent;
}
</style>

</head>
<body>
	<div id="outline">
		<div id="header">
			<a href="hompage.do"><img src="imgs/logover2.png"></a>
		</div>

		<div id="login">
			<a id="logout" href="logout.do"><input type="button" value="로그아웃"
				class="btn-primary btn-lg"
				style="font-weight: bold; font-size: 15px; width: 90px;"></a>

			<%
				String administratorId = "admin";
				if (session.getAttribute("id").equals(administratorId)) {
			%>
			<a id="mypage" href="adminregistration.jsp"><input type="button"
				value="상품등록" class="btn-primary btn-lg"
				" style="font-weight: bold; font-size: 15px; width: 90px;"></a>
			<p id="member" class="btn-lg"
				style="font-weight: bold; font-size: 20px; color: #337ab7">관리자님
				반갑습니다.</p>


			<%
				} else {
			%>

			<a id="mypage" href="mypage.jsp"><input type="button"
				value="Mypage" class="btn-link btn-lg" style="font-weight: bold;"><img
				alt="MyPage" src="imgs/categoryimg/mainpage/mypageVER2.png"
				width="25"></a>
			<p id="member" class="btn-lg"
				style="font-weight: bold; font-size: 20px; color: #337ab7">${id}님반갑습니다.</p>

			<%
				}
			%>
		</div>

		<form action="foodorganic.do" method="get">
			<div id="middleForm">
				<input id="search" type="text" placeholder="검색" name="search">
				<button id="searchbtn" type="submit" value="확인">
					<img src="imgs/search.png" style="width: 25px; height: 25px;">
				</button>

				<ul>
					<li><a href="./categoryfood.jsp"><input id="catebtn"
							type="button" value="식품" class="btn-link btn-xs"
							style="width: 90px"></a></li>
					<li><a href="./categorybeauty.jsp"><input id="catebtn"
							type="button" value="뷰티" class="btn-link btn-xs"
							style="width: 90px"></a></li>
					<li><a href="./categoryclothes.jsp"><input id="catebtn"
							type="button" value="의류" class="btn-link btn-xs"
							style="width: 90px"></a></li>
					<li><a href="./categorydailysupplies.jsp"><input
							id="catebtn" type="button" value="생활용품" class="btn-link btn-xs"
							style="width: 90px"></a></li>
				</ul>
			</div>
		</form>

		<div id="Banner">
			<ul class="bxslider">
				<li><img src="imgs/Babber1.png"></li>
				<li><img src="imgs/Babber2.png"></li>
				<li><img src="imgs/Babber3.png"></li>
			</ul>
		</div>

		<div class="title">식품 인기상품</div>
		<div class="Content">
			<div class="nume" style="margin-left: 0px;">
				<div class="nheader">유기농식품</div>
				<a
					href="selectorganic.do?p_number=${organic_item.p_number }&p_category=${organic_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${organic_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${organic_item.p_name}</div>
							<div>${organic_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
			<div class="nume">
				<div class="nheader">패스트푸드</div>
				<a
					href="selectorganic.do?p_number=${processedfood_item.p_number }&p_category=${processedfood_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${processedfood_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${processedfood_item.p_name}</div>
							<div>${processedfood_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
			<div class="nume">
				<div class="nheader">가공식품</div>
				<a
					href="selectorganic.do?p_number=${junkfood_item.p_number }&p_category=${junkfood_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${junkfood_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${junkfood_item.p_name}</div>
							<div>${junkfood_item.p_price }</div>
						</dd>
					</dl>
				</a>
			</div>
			<div class="nume" style="margin-right: 0px;">
				<div class="nheader">커피&음료</div>
				<a
					href="selectorganic.do?p_number=${dringkcafe_item.p_number }&p_category=${dringkcafe_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${dringkcafe_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${dringkcafe_item.p_name}</div>
							<div>${dringkcafe_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
		</div>

		<div class="title">뷰티 인기상품</div>
		<div class="Content">
			<div class="nume1">
				<div class="nheader">화장품</div>
				<a
					href="selectorganic.do?p_number=${cosmetic_item.p_number }&p_category=${cosmetic_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${cosmetic_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${cosmetic_item.p_name}</div>
							<div>${cosmetic_item.p_price }</div>
							<div style="display: none;">${cosmetic_item.p_category}</div>
						</dd>
					</dl>
				</a>
			</div>
			<div class="nume1">
				<div class="nheader">향수</div>
				<a
					href="selectorganic.do?p_number=${perfume_item.p_number }&p_category=${perfume_item.p_category }">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${perfume_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${perfume_item.p_name}</div>
							<div>${perfume_item.p_price }</div>
							<div style="display: none;">${perfume_item.p_category}</div>
						</dd>
					</dl>
				</a>
			</div>
			<div class="nume1" >
				<div class="nheader">미용기구</div>
				<a
					href="selectorganic.do?p_number=${beauty_item.p_number }&p_category=${beauty_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${beauty_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${beauty_item.p_name}</div>
							<div>${beauty_item.p_price }</div>
							<div style="display: none;">${beauty_item.p_category}</div>
						</dd>
					</dl>
				</a>
			</div>
		</div>

		<div class="title">의류 인기상품</div>
		<div class="Content">
			<div class="nume1">
				<div class="nheader">여성의류</div>
				<a
					href="selectorganic.do?p_number=${girlclothes_item.p_number }&p_category=${girlclothes_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${girlclothes_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${girlclothes_item.p_name}</div>
							<div>${girlclothes_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
			<div class="nume1">
				<div class="nheader">남성의류</div>
				<a
					href="selectorganic.do?p_number=${boyclothes_item.p_number }&p_category=${boyclothes_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${boyclothes_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${boyclothes_item.p_name}</div>
							<div>${boyclothes_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
			<div class="nume1"">
				<div class="nheader">아동의류</div>
				<a
					href="selectorganic.do?p_number=${babyclothes_item.p_number }&p_category=${babyclothes_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${babyclothes_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${babyclothes_item.p_name}</div>
							<div>${babyclothes_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>

		</div>

		<div class="title">생활용품 인기상품</div>
		<div class="Content">
			<div class="nume" style="margin-left: 0px;">
				<div class="nheader">주방 용품</div>
				<a
					href="selectorganic.do?p_number=${kitch_item.p_number }&p_category=${kitch_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${kitch_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${kitch_item.p_name}</div>
							<div>${kitch_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
			<div class="nume">
				<div class="nheader">욕실 도구</div>
				<a
					href="selectorganic.do?p_number=${bathroom_item.p_number }&p_category=${bathroom_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${bathroom_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${bathroom_item.p_name}</div>
							<div>${bathroom_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>

			<div class="nume">
				<div class="nheader">수납&정리</div>
				<a
					href="selectorganic.do?p_number=${storage_item.p_number }&p_category=${storage_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${storage_item.p_photourl  }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${storage_item.p_name}</div>
							<div>${storage_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>

			<div class="nume" style="margin-right: 0px;">
				<div class="nheader">청소 용품</div>
				<a
					href="selectorganic.do?p_number=${cleaning_item.p_number }&p_category=${cleaning_item.p_category}">
					<dl>
						<!-- 목록 -->
						<dt>
							<!--용어  -->
							<img src="${cleaning_item.p_photourl }" alt="식품사진">
						</dt>

						<dd>
							<!-- 설명 -->
							<div>${cleaning_item.p_name}</div>
							<div>${cleaning_item.p_price }</div>

						</dd>
					</dl>
				</a>
			</div>
		</div>

		<div id="footer">
			<img src="imgs/footer1.png">
		</div>
	</div>
</body>
</html>