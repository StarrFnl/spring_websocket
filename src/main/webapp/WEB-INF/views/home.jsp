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
<h1>hello</h1>
<h2>${serverTime }</h2>
<c:forEach items="${test}" var="tested">
	<h3>${tested.test_id }</h3>
	<h3>${tested.test_content }</h3>
</c:forEach>
<button onclick="location.href='page.do'">페이지 가기</button>



</body>
</html>