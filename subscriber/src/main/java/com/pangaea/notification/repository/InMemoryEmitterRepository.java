package com.pangaea.notification.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InMemoryEmitterRepository implements EmitterRepository {

    private Map<String, SseEmitter> topicEmitterMap = new ConcurrentHashMap<>();

    @Override
    public void addOrReplaceEmitter(String topic, SseEmitter emitter) {
        topicEmitterMap.put(topic, emitter);
    }

    @Override
    public void remove(String topic) {
        if (topicEmitterMap != null && topicEmitterMap.containsKey(topic)) {
            log.debug("Removing emitter for topic: {}", topic);
            topicEmitterMap.remove(topic);
        } else {
            log.debug("No emitter to remove for topic: {}", topic);
        }
    }

    @Override
    public Optional<SseEmitter> get(String topic) {
        return Optional.ofNullable(topicEmitterMap.get(topic));
    }
}
