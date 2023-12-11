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
	
	<a href="#" id="sse" title="sse">sse client</a>
</body>
<script>
	const eventSource = new EventSource('http://localhost:9090/app/notifications/subscribe/1');

		eventSource.addEventListener('sse', event => {
    	console.log(event);
    	console.log(event.data);
    	console.log(event.data.name);
	});
</script>
</html>