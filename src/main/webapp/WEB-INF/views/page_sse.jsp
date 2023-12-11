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
	<h1>${user3 }</h1>
	<%-- <form method="post" action="http://${user1.user_content}:9090/app/notifications/subscribe/2">
		<input type="text" name="content">
		<input type="submit" id="send" value="send">
	</form> --%>
	<button id="send">보내기</button>
	<script>
	
	/* const sendButton = document.getElementById('send');

	sendButton.addEventListener('click', () => {
	  fetch('http://172.27.192.1:9090/notifications/my-send-data/3', {
	    method: 'POST',
	    headers: {
	      'Content-Type': 'application/json'
	    },
	    mode: 'cors', // CORS를 사용하여 요청 보내기
	    body: JSON.stringify({ key: 'starrfnl' })
	  })
	    .then(response => console.log(response))
	    .catch(error => {
	      console.error('Error:', error);
	    });
	}); */
	
	const userId = 3;
	const eventSource = new EventSource(`http://192.168.0.89:9090/notifications/sse-send-data/3`);

	eventSource.onmessage = (event) => {
	  // SSE 메시지 수신 시 처리
	  const data = JSON.parse(event.data);
	  console.log(data);
	};

	eventSource.onerror = (error) => {
	  // SSE 에러 처리
	  console.error('SSE Error:', error);
	};

	eventSource.onclose = () => {
	  // SSE 연결 종료 처리
	  console.log('SSE Connection Closed');
	};

	</script>
	
	
</body>
</html>