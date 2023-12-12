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
	<h1>${owner }</h1>
	
	
	<a href="#" id="sse" title="sse">sse client</a>
</body>
<script type="text/javascript" charset="utf-8">

	var addr = window.location.origin+"/app/";
	console.log(addr);
	//현 컴퓨터 ip로 서버 열기. 해당 내용 db에 업로드 필요
	const eventSource_2 = new EventSource('${owner.owner_path}/notifications/subscribe/100');
	/* const eventSource = new EventSource('http://localhost:9090/app/notifications/subscribe/2');

	eventSource.addEventListener('sse', event => {
    	console.log(event);
    	console.log(event.data);
    	console.log(event.data.name);
	}); */
	
	//이벤트 자동으로 n초마다 반복되는 설정 확인
	eventSource_2.addEventListener('sse', event => {
		console.log(event);
		console.log(event.data);
		const decodedString = decodeURIComponent(event.data);
		console.log(decodedString);
		//let base64Str = event.data;
		//let utf8Str = decodeURIComponent(escape(atob(event.data)));

    	console.log(decodedString);
    	console.log("한글");
    	helloWorld(decodeURIComponent(decodedString));
	});
	
	function helloWorld(str){
        let date = new Date().toLocaleString();
        let notification;
        let notificationPermission = Notification.permission;
        if (notificationPermission === "granted") {
            //Notification을 이미 허용한 사람들에게 보여주는 알람창
            //맨 위 제목
            notification = new Notification(`빵이오 주문!`, {
                body: `알림 내용:`+decodeURIComponent(str),
                //icon: 'hello.png',
            });
        } else if (notificationPermission !== 'denied') {
            //Notification을 거부했을 경우 재 허용 창 띄우기
            Notification.requestPermission(function (permission) {
                if (permission === "granted") {
                    notification = new Notification(`빵이오 주문 접수 시작!`, {
                        body: `첫방문일시: ${date}`,
                        //icon: 'hello.png',
                    });
                }else {
                    alert("알람 허용이 거부되었습니다.")
                }
            });
        }
    }

    

</script>
</html>