<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drone Pang | 장바구니</title>

<script src="https://code.jquery.com/jquery-3.4.0.js"
	integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
	crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="css/bootstrap.css">


<style>
a {
	text-decoration: none;
	color: black;
}

#sub div {
	width: 300px;
	display: inline-block;
	text-align: center;
	margin-top: 10px;
}

#sub div #allpay_price{
width: 190px;
border: none;
border-bottom: 3px #3aabd3 dashed;
}
#headtext {
	color: #3aabd3;
	margin: 0 auto;
	padding-bottom: 10px;
}

#con {
	width: 600px;
	margin: 0 auto;
	overflow: hidden;
	padding: 5px;
	text-align: center;
	font-size: 1.5em;
	font-weight: bold;
}

#viewinfo {
	width: 600px;
	margin: 0 auto;
	border-bottom: 2px dotted #3aabd3;
}

}
#header {
	width: 780px;
	height: 100px;
	line-height: 100px;
	text-align: center;
	margin: 5px;
	padding-bottom: 5px;
	border-bottom: 2px solid #3aabd3;
}

#header img {
	width: 100%;
	height: 100%;
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

ul {
	width: 600px;
	overflow: hidden;
	padding-left: 0px;
	margin: 10px 0px;
	border-bottom: 2px solid #3aabd3;
}

li {
	float: left;
	list-style: none;
	margin: 10px 15px;
	margin-left: 0px;
}

li img {
	width: 200px;
	height: 150px;
	margin-right: 30px;
}

.btn-primary{
width: 100px;
height: 40px;
font-size: 70%;
}
</style>
</head>
<body>
	<form id="form" name="form" action="buybasket1.do" method="post"
		target="_parent">
		<div id="con">
			<div>
				<h2 id="headtext">Shopping Basket</h2>

			</div>

			<div id="viewinfo">
				<p style="text-align: left; padding-left: 15px;">
					<input type="checkbox" id="checked_all"
						onchange="itemSum(this.form)" name="checked_all">전체선택
				</p>
				<c:forEach var="list" items="${basketList}">

					<ul>
						<li><input id="checkBox" type="checkbox" name="shoppinglist"
							class="shoppinglist" value="${list.basketnum}"
							onchange="itemSum(this.form)"></li>

						<li><input type="hidden" name="price"
							value="${list.p_price*list.p_amount}"></li>

						<li><img alt="상품사진" src="${list.p_photourl}"></li>

						<li style="margin-top: 20px;">상품이름 : ${list.p_name}</li>
						<li style="width: 250px; text-align: left;">상품가격 : ${list.p_price}</li>
						<li>수량 : ${list.p_amount}</li>
						<li style="display: none;">카테고리 : ${list.p_category}</li>
						<li style="display: none;">상품숫자 : ${list.p_number}</li>
					</ul>
				</c:forEach>
			</div>
			<div id="sub">
				<div>
					<p>
						합계 : <input id="allpay_price" name="allpay_price" type="text" size="20" readonly>
						&nbsp;&nbsp;원
					</p>
				</div>

				<div>
					<input type="submit" id="testBtn" value="결제하기" class="btn-primary btn-sm"> <input
						type="button" value="삭제하기" onclick="delete_checked()" class="btn-primary btn-sm">

				</div>
			</div>
			<div id="footer"></div>
		</div>

	</form>

	<script language="javascript">
		$(document).ready(function() {
			//최상단 체크박스 클릭
			$("#checked_all").click(function() {
				//클릭되었으면
				if ($("#checked_all").prop("checked")) {
					//input태그의 name이 shoppinglist인 태그들을 찾아서 checked옵션을 true로 정의
					$("input[name=shoppinglist]").prop("checked", true);
					//클릭이 안되있으면
				} else {
					//input태그의 name이 shoppinglist인 태그들을 찾아서 checked옵션을 false로 정의
					$("input[name=shoppinglist]").prop("checked", false);
				}
			})
		});

		function itemSum(frm) {

			var sum = 0;
			var count;
			count = frm.shoppinglist.length;

			if (typeof (frm.shoppinglist.length) == 'undefined') {
				if (checked_all.checked == true) {
					if (frm.shoppinglist.checked == true) {
						var liElement = frm.shoppinglist;
						var sibling = $(liElement).parent().siblings();
						var children = sibling.eq(0).children().first();

						sum += parseInt(children.val());
					}
				} else {
					if (frm.shoppinglist.checked == true) {
						var liElement = frm.shoppinglist;
						var sibling = $(liElement).parent().siblings();
						var children = sibling.eq(0).children().first();

						sum += parseInt(children.val());
					}

				}
			} else {
				count = frm.shoppinglist.length;

				for (var i = 0; i < count; i++) {
					if (checked_all.checked == true) {
						if (frm.shoppinglist[i].checked == true) {
							var liElement = frm.shoppinglist[i];
							var sibling = $(liElement).parent().siblings();
							var children = sibling.eq(0).children().first();

							sum += parseInt(children.val());

							console.log(liElement);
							console.log(sibling);
							console.log(children);
						}
					} else {
						if (frm.shoppinglist[i].checked == true) {
							var liElement = frm.shoppinglist[i];
							var sibling = $(liElement).parent().siblings();
							var children = sibling.eq(0).children().first();

							sum += parseInt(children.val());

							console.log(liElement);
							console.log(sibling);
							console.log(children);
						}

					}
				}
			}

			frm.allpay_price.value = sum;
		}

		var $sportAll = $('#checked_all');
		$sportAll.change(function() {
			var $this = $(this);
			var checked = $this.prop('checked'); // checked 문자열 참조(true, false)
			// console.log(checked);
			$('input[name="shoppinglist"]').prop('checked', checked);
			// shoppinglist 이름을 가진 input 박스에서 checked 를 실행한다.

		});

		var boxes = $('input[name="shoppinglist"]');
		boxes.change(function() {
			// 첫 번째 방법
			/*
			var selectAll = true;
			var count = boxes.length;
			var i = 0;
			for (; i < count; i++) {
			    if (!boxes[i].checked) {
			        selectAll = false;
			        break;
			    }
			}
			 */

			// 두 번째 방법
			var boxLength = boxes.length;
			// 체크된 체크박스 갯수를 확인하기 위해 :checked 필터를 사용하여 체크박스만 선택한 후 length 프로퍼티를 확인
			var checkedLength = $('input[name="shoppinglist"]:checked').length;
			var selectAll = (boxLength == checkedLength);

			$sportAll.prop('checked', selectAll);
			/* https://webclub.tistory.com/570 */
		});

		function delete_checked() {
			var nmeCardSeq = "";
			var checkArray = new Array();

			$("input[name=shoppinglist]:checked").each(function(i) {
				// for문을 돌려서 check된 값의 벨류를 배열에 넣음.
				checkArray.push($(this).val());
				console.log($("input[name=shoppinglist]:checked").eq(i).val());
			});

			if (checkArray.length == 0) {
				// check 된값이 없을경우 아래 안내문 출력.
				alert("삭제할 상품을 선택하세요.")
			} else {
				// check 된 값이 있을경우 아래 안내문을 보여주고 사용자가 확인을 누르면, ajax로 통신
				if (confirm("삭제하시겠습니까?") == true) {
					$.ajax({
						type : 'GET',
						url : 'deletebasket.do',
						traditional : true,
						data : {
							'checkArray' : checkArray
						},
						complete : function(data) {
							location.reload(true);
						},
						
					});
					checkArray = new Array();
					nmeCardSeq = "";
				} else { //취소	
					location.reload(true);
				}
			}

		}
	</script>


</body>
</html>