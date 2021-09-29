package com.pangaea.notification.service;

import com.pangaea.notification.model.EventDto;

public interface NotificationService {

    void sendNotification(String topic, EventDto event);
}
