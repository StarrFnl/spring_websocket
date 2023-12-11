package com.shinhan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SSEController {

    @GetMapping("/notifications/sse-send-data/{userId}")
    public ResponseEntity<SseEmitter> sendNotifications(@PathVariable("userId") String userId) {
        SseEmitter emitter = new SseEmitter();

        // SSE로 데이터 전송
        try {
            Map<String, String> data = new HashMap<>();
            data.put("name", "mynameiskim");
            emitter.send(SseEmitter.event().data(data));

            // 필요에 따라 추가적인 데이터 전송을 수행할 수 있습니다.
            // emitter.send(SseEmitter.event().data(...));
            // ...

            emitter.complete(); // SSE 연결 종료
        } catch (IOException e) {
            emitter.completeWithError(e); // SSE 연결 에러 처리
        }
        
        
        // SSE 연결 종료 시 처리
        emitter.onCompletion(() -> {
            // SSE 연결 종료 시 필요한 처리를 추가합니다.
        });

        // SSE 연결 에러 시 처리
        emitter.onError((ex) -> {
            // SSE 연결 에러 시 필요한 처리를 추가합니다.
        });

        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }
}
