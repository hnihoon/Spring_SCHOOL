<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calcResult.jsp</title>
</head>
<body>

	<h1>계산결과</h1>
		
	<h2>1) JSP 방식</h2>
	<%=request.getAttribute("message")%>
	숫자1 : <%=request.getAttribute("no1")%><br>
	숫자2 : <%=request.getAttribute("no2")%><br>
	결과  : <%=request.getAttribute("result")%><br>
	<%=request.getAttribute("img")%><br>
	<hr>
	
	<h2>2) EL 방식</h2>
	${message}
	숫자1 : ${no1}<br>
	숫자2 : ${no2}<br>
	결과  : ${result}<br>
		   ${img==null ? "" : img}
	
</body>
</html>