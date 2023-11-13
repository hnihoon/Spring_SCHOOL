<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bookTest.jsp</title>
	<script src="../js/jquery-3.7.1.min.js"></script>
	<script src="../js/jquery.cookie.js"></script>
</head>
<body>

	<h3>교재선택</h3>
	
	<select name="book" id="book">
		<option>--선택--</option>
		<option value="0">spring</option>
		<option value="1">Android</option>
		<option value="2">jQuery</option>
		<option value="3">javaScript</option>
	</select>
	<br>
	<div id="display"></div>
	
	<script>
		$("#book").change(function(){
			//alert($(this).val());
			//$.port("요청명령어", "전달값", 콜백함수)
			$.post("booksend.do", "book=" + $(this).val(), responseProc);
		});
		
		function responseProc(result){
			//alert(result);
			$("#display").html("<img src='../images/" + result + "'>");
		}
	</script>

</body>
</html>