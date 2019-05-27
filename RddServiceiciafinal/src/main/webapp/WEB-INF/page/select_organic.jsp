<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://code.jquery.com/jquery-3.4.0.js"
   integrity="sha256-DYZMCC8HTC+QDr5QNaIcfR7VSPtcISykd+6eSmBW5qo="
   crossorigin="anonymous"></script>

<scrit type="text/javascript" src=js/bootstrap.js"> </script>

<title>Insert title here</title>
<script language='javascript'>
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

   $(document).ready(function() {
      $("#btnst").click(function() {
         if (parseInt($("#p_amount").val()) <= parseInt($("#p_stock").val())) {


            document.f.submit();
         } else if (parseInt($("#p_amount").val()) > parseInt($("#p_stock").val())) {
            alert("재고가 부족합니다.");
      
            return false;
         }
      });
   });

   function sendIt() {

      if (f.p_stock.value == "0") {

         alert("해당상품은 품절입니다.")

         return;

      }

      if (f.p_amount.value <= f.p_stock.value) {

         alert("구매가 가능합니다.1234");
         alert(f.p_amount.value);
         alert(f.p_stock.value);

         document.f.submit();

      } else if (f.p_amount.value > f.p_stock.value) {

         alert("재고가 부족합니다.1234");
         alert(f.p_amount.value);
         alert(f.p_stock.value);

         return false;

      }

   }
</script>
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

#btnset {
   float: right;
   height: 55px;
   margin: 10px;
   margin-right: 100px;
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

#btnset #btnst {
   padding: 5px;
   height: 50px;
   font-size: 15pt;
   font-weight: bold;
   border: none;
}

#btnset, #inputToBasket, #backpage {
   padding: 5px;
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
<body>
   <form name="f" action="clickorder.do" method="post"
      onsubmit="return false;">
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
               <p class="viewinfo_p">수 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 량 :</p>
               <select name="p_amount" id="p_amount">
                  <option value="1">1</option>
                  <option value="2">2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                  <option value="6">6</option>
                  <option value="7">7</option>
                  <option value="8">8</option>
                  <option value="9">9</option>
               </select>
               <p
                  style="font-weight: bold; border: none; float: left; font-size: 130%; margin: 0px; line-height: 55px; text-align: right; border: none;">
                  개</p>
            </div>

            <!-- 재고 넣는 부분 -->
            <div class="viewinfo_div">
               <p class="viewinfo_p">재 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 고 :</p>
               
               <input class="viewinfo_input" id="p_stock" name="p_stock"
                  type="text" value="${select.p_stock}" readonly="readonly">
               <p
                  style="font-weight: bold; border: none; float: left; font-size: 130%; margin: 0px; line-height: 55px; text-align: right; border: none;">
                  개</p>
            </div>

            <!-- 재고 넣느 -->

            <div id="btnset">
               <input id="backpage" type="button" name="btnbasket"
                  class="btn btn-primary btn-xs" value="목록"
                  style="width: 100px; height: 40px;" onClick="history.go(-1)">
               <input id="inputToBasket" type="button" name="btnbasket"
                  class="btn btn-primary btn-xs" value="장바구니"
                  style="width: 100px; height: 40px; margin-left: 20px"><input
                  id="btnst" type="submit" name="btnbuy" value="바로구매"
                  style="width: 100px; height: 40px; margin-left: 20px"
                  class="btn btn-primary btn-xs">

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
</body>
</html>