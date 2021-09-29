package com.pangaea.notification.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface EmitterRepository {

    void addOrReplaceEmitter(String topic, SseEmitter emitter);

    void remove(String topic);

    Optional<SseEmitter> get(String topic);
}
