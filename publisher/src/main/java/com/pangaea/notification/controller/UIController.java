package com.pangaea.notification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
	
	@GetMapping({"/", "/welcome"})
	public String getMainUI() {
		return "index";
	}
	
}
