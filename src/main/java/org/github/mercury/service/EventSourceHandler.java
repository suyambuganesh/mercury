package org.github.mercury.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EventSourceHandler {

    public void handleSseEmitter(String application, SseEmitter sseEmitter);

}
