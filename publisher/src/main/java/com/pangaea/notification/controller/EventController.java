package com.pangaea.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pangaea.notification.model.EventDto;
import com.pangaea.notification.model.SubscribeDto;
import com.pangaea.notification.repository.SubscriberRepository;
import com.pangaea.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventController {

	private final NotificationService notificationService;
	private final SubscriberRepository subscriberRepository;

	@PostMapping("/subscribe/{topic}")
	public SubscribeDto subscribeToEvents(@PathVariable String topic, @RequestBody SubscribeDto subscribeDto) {
		log.info("Subscribing on topic {}", topic);
		subscribeDto.setTopic(topic);
		subscriberRepository.addSubscriberEndpoints(topic, subscribeDto.getUrl());
		return subscribeDto;
	}

	@PostMapping("/publish/{topic}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void publishEvent(@PathVariable String topic, @RequestBody EventDto event) {
		event.setTopic(topic);
		log.info("Publishing event {} for topic {}", event, topic);
		notificationService.sendNotification(topic, event);
	}
}
