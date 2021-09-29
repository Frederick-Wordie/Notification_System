package com.pangaea.notification.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topic;
    private Map<String, Object> data;
}
