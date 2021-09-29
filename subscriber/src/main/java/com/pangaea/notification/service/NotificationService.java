package com.pangaea.notification.service;

import com.pangaea.notification.model.EventDto;

public interface NotificationService {

    void displayMessage(String topic, EventDto event);
}
