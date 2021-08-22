package com.yodsarun.yp.fn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webController {
	@RequestMapping("/test")
	public String main() {
		return "test";
	}
}
