package com.pangaea.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.pangaea.notification.repository.EmitterRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmitterService {

    private final long eventsTimeout;
    private final EmitterRepository repository;

    public EmitterService(@Value("${events.connection.timeout}") long eventsTimeout,
                          EmitterRepository repository) {
        this.eventsTimeout = eventsTimeout;
        this.repository = repository;
    }
   
    public SseEmitter createEmitter(String topic) {
        SseEmitter emitter = new SseEmitter(eventsTimeout);
        emitter.onCompletion(() -> repository.remove(topic));
        emitter.onTimeout(() -> repository.remove(topic));
        emitter.onError(e -> {
            log.error("Create SseEmitter exception", e);
            repository.remove(topic);
        });
        repository.addOrReplaceEmitter(topic, emitter);
        return emitter;
    }

}
