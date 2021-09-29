package com.pangaea.notification.repository;

import java.util.List;
import java.util.Optional;

public interface SubscriberRepository {

    Optional<List<String>> get(String topic);
    
    void addSubscriberEndpoints(String topic, String url);
}
