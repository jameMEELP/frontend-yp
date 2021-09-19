package com.yodsarun.yp.fn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {
	@RequestMapping("/notification")
	public String getAllNotification() {
		return "notification";
	}
}
