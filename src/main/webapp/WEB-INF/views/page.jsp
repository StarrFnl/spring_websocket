<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page</h1>
	<h1>${user1 }</h1>
	<h1>${user2 }</h1>
	
	<a href="#" id="chat" title="채팅">채팅</a>
</body>
<script>
	document.querySelector("#chat").addEventListener('click', function(e){
		e.preventDefault();
		window.open("echo.do","chat","width=500, height=800, top=200, left=200");
        // 경로, 파일, 너비, 높이, 위치 지정
	})
</script>
</html>