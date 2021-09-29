package com.pangaea.notification.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InMemorySubscriberRepository implements SubscriberRepository {

    private Map<String, List<String>> subscriberMap = new ConcurrentHashMap<>();

    @Override
    public Optional<List<String>> get(String topic) {
        return Optional.ofNullable(subscriberMap.get(topic));
    }

	@Override
	public void addSubscriberEndpoints(String topic,String url) {
		 log.info("Adding subscriber to topic {}", topic);
		get(topic).ifPresentOrElse(subscribers ->{ 
			if (!subscribers.contains(url)) {subscribers.add(url);};
    	},() -> subscriberMap.put(topic, new ArrayList<>(Arrays.asList(url))));
	}
}
