package com.shinhan.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.shinhan.dto.testVO;
import com.shinhan.model.NotificationService;
import com.shinhan.model.OwnerDAO;

import lombok.RequiredArgsConstructor;

//@CrossOrigin
@Async  //필수
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications") 
public class NotificationController {

	private final NotificationService notificationService;

	@Autowired
	OwnerDAO oDao;
	/*
	 * SseEmitter 객체를 반환하는데 이는 클라이언트에서 SSE 정보를 보관해야 하기 때문에 
	 * 무조건 반환을 해주어야합니다. 
	 * 만약 반환해주지 않는다면 sse eventsource's response has a mime type 
	 * ("text/plain") that is not "text/event-stream". aborting the connection. 오류가 발생할 수 있습니다.
	 */
	
	//구독 연결만
	@CrossOrigin
    @GetMapping(value = "/subscribe/{id}", 
    		produces = "text/event-stream" )//MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Long id, testVO test) {
    	SseEmitter sseObj = notificationService.subscribe(id);
    	System.out.println(test.toString());
    	System.out.println(sseObj);
        return sseObj;
    }
    
    //연결된 곳에 공지 보내기
    @CrossOrigin
    @PostMapping("/send-data/{id}")
    public void sendData(@PathVariable Long id, @RequestBody String body) {
        System.out.println(body);
        
        int owner_id = 1;
    	notificationService.notify(id, oDao.selectOwnerById(owner_id).getOwner_content());
    }
    
    
    //연결된 곳에 공지 보내기
    @PostMapping("/my-send-data/{id}")
    public void mysendData(@PathVariable Long id) {
        notificationService.notify(id, "test");
    }
}