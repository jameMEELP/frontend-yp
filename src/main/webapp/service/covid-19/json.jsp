<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.springframework.http.MediaType"%>
<%@page import="org.springframework.http.HttpMethod"%>
<%@page import="org.springframework.http.HttpEntity"%>
<%@page import="org.springframework.http.HttpHeaders"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="org.springframework.http.ResponseEntity"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="org.springframework.web.client.RestTemplate"%>
<%
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
		map_test.put("age_range", age_range.replaceAll("\"", ""));
		map_test.put("nationality", nationality);
		map_test.put("job", job);
		map_test.put("risk", risk);
		map_test.put("patient_type", patient_type);
		map_test.put("province", province);

		myArrList.add(map_test);
	}
	ObjectMapper objectMapper = new ObjectMapper();
%>
<%out.clear();%><%=objectMapper.writeValueAsString(myArrList)%>