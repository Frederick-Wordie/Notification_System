package com.pangaea.notification.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.pangaea.notification.mapper.EventMapper;
import com.pangaea.notification.model.EventDto;
import com.pangaea.notification.repository.EmitterRepository;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class SseNotificationService implements NotificationService {

    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;
    private final ExecutorService nonBlockingService = Executors
  	      .newCachedThreadPool();

    @Override
    public void displayMessage(String topic, EventDto event) {
        if (event == null) {
            log.debug("No server event to display");
            return;
        }
        doSendNotification(topic, event);
    }

    private void doSendNotification(String topic, EventDto event) {
    	 emitterRepository.get(topic).ifPresentOrElse(sseEmitter ->{ 
    		 nonBlockingService.execute(() -> {
                 try {
                	 log.debug("Displaying event: {} for topic: {}", event, topic);
                	 sseEmitter.send(eventMapper.toSseEventBuilder(event));
                	
                 } catch (IOException | IllegalStateException e) {
                	 log.debug("Error while emiting event: {} for topic: {} - exception: {}", event, topic, e);
                	 sseEmitter.complete();
                	 emitterRepository.remove(topic);
                 }
             });},() -> log.debug("No emitter for topic {}", topic));
    }

}
