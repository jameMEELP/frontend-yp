package com.yodsarun.yp.fn.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CustomerController {
	@RequestMapping(value = "/getCustomerById/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getCustomerById(@PathVariable String id) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = new LinkedHashMap();
		System.out.println("id = " + id);

		return new ResponseEntity("jame Test", HttpStatus.OK);
	}
}
