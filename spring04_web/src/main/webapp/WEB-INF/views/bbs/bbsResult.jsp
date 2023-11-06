<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bbsResult.jsp</title>
</head>
<body>

	<h3>* 글쓰기 결과 *</h3>
	
	1)JSP<br>
	작성자 : <%=request.getParameter("wname")%><br>
	제목 : <%=request.getParameter("subject")%><br>
	내용 : <%=request.getParameter("content")%><br>
	비밀번호 : <%=request.getParameter("passwd")%><br>
	<hr>
	
	2)EL<br>
	작성자 : ${requestScope.wname}<br>
	제목 : ${requestScope.subject}<br>
	내용 : ${requestScope.content}<br>
	비밀번호 : ${requestScope.passwd}<br>
	<hr>
	
	작성자 : ${wname}<br>
	제목 : ${subject}<br>
	내용 : ${content}<br>
	비밀번호 : ${passwd}<br>

</body>
</html>