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

	<button id="send">보내기</button>
	<script>
	
	const sendButton = document.getElementById('send');
	const url = 'http://127.0.0.1:9090/app/notifications/send-data/100';
	const data = { name: 'my name is ~~' };
	
	
	sendButton.addEventListener('click', () => {
	  fetch(url, {
	    method: 'POST',
	    headers: {
            'Content-Type': 'application/json',
            'charset': 'utf-8'
        },
	    body: JSON.stringify(data)
	  })
	    .then(response => console.log(response))

	    .catch(error => {
	      console.error('Error:', error);
	    });
	}); 
	


	</script>
	
	
</body>
</html>