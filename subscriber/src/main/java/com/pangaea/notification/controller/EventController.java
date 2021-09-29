package com.pangaea.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.pangaea.notification.model.EventDto;
import com.pangaea.notification.service.EmitterService;
import com.pangaea.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController {
    private final EmitterService emitterService;
    private final NotificationService notificationService;

  
    @GetMapping("/subscribe/{topic}")
    public SseEmitter monitorReceivedEvent(@PathVariable String topic) {
    	return emitterService.createEmitter(topic);
    }

    @PostMapping("/test1")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void SubscribedEventOne(@RequestBody EventDto event) {
    
    	log.info("Receiving event {} for topic {}", event, event.getTopic());
       
        notificationService.displayMessage(event.getTopic(),event);
    }
    
    @PostMapping("/test2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void SubscribedEventTwo(@RequestBody EventDto event) {
    
    	log.info("Receiving event {} for topic {}", event, event.getTopic());
       
        notificationService.displayMessage(event.getTopic(),event);
    }
}
