package com.pangaea.notification.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pangaea.notification.model.EventDto;
import com.pangaea.notification.repository.SubscriberRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final SubscriberRepository subscriberRepository;
    private final WebClient client = WebClient.create();
    private final ExecutorService nonBlockingService = Executors
  	      .newCachedThreadPool();

    @Override
    public void sendNotification(String topic, EventDto event) {
        if (event == null) {
            log.info("No server event to send");
            return;
        }
        doSendNotification(topic, event);
    }

    private void doSendNotification(String topic, EventDto event) {
    	 subscriberRepository.get(topic).ifPresentOrElse(subscriberUrls ->{ 
    		 nonBlockingService.execute(() -> {
    			 sendMessageToSubscribers(subscriberUrls,event);
    			 log.info("Sending event to subscribers on topic {}", topic);
             });},() -> log.info("No subscribers for topic {}", topic));
    }
    
    
    private void sendMessageToSubscribers(List<String> subscriberEndPoints,EventDto event) {
    	subscriberEndPoints.forEach(url -> {
    		 log.info("Sending event to subscribers {}", event);
    		 client.post().uri(url)
    		.contentType(MediaType.APPLICATION_JSON)
            .bodyValue(event)
            .exchange().doOnError(s -> log.info("Error sending event to subscriber with message {}",s.getMessage()))
            .map(response -> response.statusCode()).subscribe();
    	});
    }

}
