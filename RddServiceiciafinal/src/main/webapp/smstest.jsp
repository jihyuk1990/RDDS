<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>SMS 프로젝트</title>
</head>

<body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<div class="container">
		<form method="post" name="smsForm" action="sendsms.jsp">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="text-align: center;">문자 전송 양식</th>
				</thead>
				<tbody>
					<tr>

						<td><textarea class="form-control" maxlength="45" name="msg"
								style="height: 220px;"></textarea></td>
					</tr>
					<tr>
						<td><input class="form-control" type="text" name="rphone"
							value="" placeholder="받는 번호"></td>
					</tr>
					<tr>
						<td>( 받는 번호는 010-0000-0000과 같이 전체 번호를 작성해주세요. )</td>
					</tr>
					<tr>
						<td><input type="hidden" name="action" value="go"> <input
							type="hidden" name="sphone1" value="010"> <input
							type="hidden" name="sphone2" value="6676"> <input
							type="hidden" name="sphone3" value="3893"> <input
							type="submit" class="btn btn-primary pull-right" value="전송"></td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>

</body>

</html>