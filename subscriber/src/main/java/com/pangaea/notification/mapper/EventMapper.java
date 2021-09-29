package com.pangaea.notification.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.pangaea.notification.model.EventDto;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EventMapper {

    public SseEmitter.SseEventBuilder toSseEventBuilder(EventDto event) {
        return SseEmitter.event()
                .name(event.getTopic())
                .data(event.getData());
    }
    
}
