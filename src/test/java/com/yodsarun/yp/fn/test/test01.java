package com.yodsarun.yp.fn.test;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.*;

import java.nio.charset.StandardCharsets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class test01 {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-all";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

		String responseEntityBody = responseEntity.getBody();
		byte[] ptext = responseEntityBody.getBytes(StandardCharsets.ISO_8859_1);
		responseEntityBody = new String(ptext, StandardCharsets.UTF_8);
		System.out.println("responseEntityBody = " + responseEntityBody);

		System.exit(0);
	}
}
