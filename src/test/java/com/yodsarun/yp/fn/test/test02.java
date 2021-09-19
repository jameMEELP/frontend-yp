package com.yodsarun.yp.fn.test;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class test02 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		RestTemplate restTemplate = new RestTemplate();
		// restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

//		String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-line-lists";
//		ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);

//		String responseEntityBody = (String) responseEntity.getBody();
//		byte[] ptext = responseEntityBody.getBytes(Charset.forName("ISO-8859-1"));
//		responseEntityBody = new String(ptext, Charset.forName("UTF-8"));
//		System.out.println("ptext = " + ptext);
//		System.out.println("responseEntityBody = " + responseEntityBody);

		try {
			String test = Test01();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static String Test01() throws IOException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_XML);

		HttpEntity httpEntity = new HttpEntity(httpHeaders);

		String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-line-lists";
		ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

		String responseEntityBody = (String) responseEntity.getBody();

		String[] elements = responseEntityBody.split("\\n");
		List<String> fixedLenghtList = Arrays.asList(elements);
		ArrayList<String> listOfMap = new ArrayList<String>(fixedLenghtList);

		ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
		for (int i = 0 ; i < listOfMap.size() ; i++) {
			HashMap<String, String> map_test = new HashMap<String, String>();
			elements = listOfMap.get(i).split(",");
			fixedLenghtList = Arrays.asList(elements);
			ArrayList<String> listOfString = new ArrayList<String>(fixedLenghtList);
			// System.out.println("listOfString = " + listOfString);

			String txn_date = String.valueOf(listOfString.get(0));
			String gender = String.valueOf(listOfString.get(1));
			String age_number = String.valueOf(listOfString.get(2));
			String age_range = String.valueOf(listOfString.get(3));
			String nationality = String.valueOf(listOfString.get(4));
			String job = String.valueOf(listOfString.get(5));
			String risk = String.valueOf(listOfString.get(6));
			String patient_type = String.valueOf(listOfString.get(7));
			String province = String.valueOf(listOfString.get(8));

			map_test.put("txn_date", txn_date);
			map_test.put("gender", gender);
			map_test.put("age_number", age_number);
			map_test.put("age_range", age_range);
			map_test.put("nationality", nationality);
			map_test.put("job", job);
			map_test.put("risk", risk);
			map_test.put("patient_type", patient_type);
			map_test.put("province", province);

			myArrList.add(map_test);
		}
		// System.out.println("myArrList = " + myArrList);

		return responseEntity.toString();
	}
}
