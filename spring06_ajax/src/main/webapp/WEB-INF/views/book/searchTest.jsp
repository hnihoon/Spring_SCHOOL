<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchTest.jsp</title>
	<script src="../js/jquery-3.7.1.min.js"></script>
	<script src="../js/jquery.cookie.js"></script>
</head>
<body>

	<h3>교재 검색</h3>
	
	<form name="search" id="search">
		<input type="text" name="keyword" id="keyword">
		<input type="button" value="검색">
	</form>
	
	<!-- 검색 결과 출력 -->
	<div id="panel" style="display:none"></div>
	
	<script>
		$("#keyword").keyup(function(){ //키보드를 누른 후 손을때면 발생하는 이벤트
			//alert("Test");
			
			if($("#keyword").val()==""){ //검색어가 존재하지 않으면
				$("#panel").hide();		//출력결과 숨기기
			}
			//1) id="keyword" 값 가져오기
			//let params = $("#keyword").val();
			//alert(params);
			
			//2)<form id="search"></form>
			// 폼안의 모든 컨트롤 요소를 가져오기
			let params = $("#search").serialize();
			//alert(params);
			
			$.post("searchproc.do", params, responseProc);
			
		});
		
		function responseProc(data){
			//alert(data);
		}
	</script>
</body>
</html>