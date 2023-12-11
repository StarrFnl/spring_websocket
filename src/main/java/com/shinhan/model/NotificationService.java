package com.shinhan.model;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	// 기본 타임아웃
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 ; //* 60 60�� 

    private final EmitterRepository emitterRepository;

    /**
     * 클라이언트의 구독 호출
     *
     * @param userId - 구독하는 클라이언트의 사용자 아이디
     * @return SseEmitter - 서버에서 보낸 이벤트 Emitter
     */
    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = createEmitter(userId);

        sendToClient(userId, "EventStream Created. [userId=" + userId + "]");
        return emitter;
    }

    /**
     * 서버의 이벤트를 클라이언트에게 보내는 메서드
     * 다른 서비스 로직에서 이 메서드를 사용해 데이터를 Object event에 넣고 전송하면 된다.
     *
     * @param userId - 메세지 전송할 사용자의 아이디
     * @param event  - 전송할 이벤트 객체
     */
    public void notify(Long userId, Object event) {
        sendToClient(userId, event);
    }

    /**
     * 클라이언트에게 데이터 전송
     *
     * @param id   - 데이터 받는 사용자 아이디
     * @param data - 전송할 데이터
     */
    private void sendToClient(Long id, Object data) {
    	System.out.println("sendToClient:" + id + data);
        SseEmitter emitter = emitterRepository.get(id);
        System.out.println(emitter);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().id(String.valueOf(id))
                		.name("sse").data(data+"\n\n"));
            } catch (IOException exception) {
                emitterRepository.deleteById(id);
                emitter.completeWithError(exception);
                System.out.println("SSE 연결 실패");
            }
        }
    }

    /**
     * 사용자 ID 기반 이벤트 emitter 생성
     *
     * @param id - 사용자 아이디
     * @return SseEmitter - 생성된 이벤트 Emitter
     */
    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        // Emitter가 완료될 때(모든 데이터가 성공적으로 전송된 상태) Emitter를 삭제한다.
        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        // Emitter가 타임아웃 되었을 때(지정된 시간동안 어떠한 이벤트도 전송되지 않았을 때) Emitter를 삭제한다.
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }
}
