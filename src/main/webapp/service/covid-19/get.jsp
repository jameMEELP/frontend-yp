<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="org.springframework.http.ResponseEntity"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="org.springframework.web.client.RestTemplate"%>
<%@page import="org.springframework.http.converter.StringHttpMessageConverter"%>
<%
	String json = "";
	String action = request.getParameter("action");

	String url = "";
	if (action.equals("getAll")) {
		url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-all";
	} else if (action.equals("getByProvince")) {
		url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";
	}

	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

	String responseEntityBody = responseEntity.getBody();
	byte[] bytes = responseEntityBody.getBytes(StandardCharsets.ISO_8859_1);
	responseEntityBody = new String(bytes, StandardCharsets.UTF_8);

	ObjectMapper objectMapper = new ObjectMapper();
	List list = new ArrayList();
	list = objectMapper.readValue(responseEntityBody, List.class);

	DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

	List listResp = new ArrayList();
	for (int i = 0 ; i < list.size() ; i++) {
		Map map = (Map) list.get(i);

		BigDecimal new_case = new BigDecimal(String.valueOf(map.get("new_case")));
		BigDecimal total_case = new BigDecimal(String.valueOf(map.get("total_case")));
		BigDecimal new_case_excludeabroad = new BigDecimal(String.valueOf(map.get("new_case_excludeabroad")));
		BigDecimal total_case_excludeabroad = new BigDecimal(String.valueOf(map.get("total_case_excludeabroad")));

		map.put("new_case", decimalFormat.format(new_case));
		map.put("total_case", decimalFormat.format(total_case));
		map.put("new_case_excludeabroad", decimalFormat.format(new_case_excludeabroad));
		map.put("new_case_excludeabroad_net", decimalFormat.format(new_case.subtract(new_case_excludeabroad)));
		map.put("total_case_excludeabroad", decimalFormat.format(total_case_excludeabroad));
		map.put("total_case_excludeabroad_net", decimalFormat.format(total_case.subtract(total_case_excludeabroad)));

		listResp.add(map);
	}

	json = objectMapper.writeValueAsString(listResp);
	// System.out.println("json = " + json);
%>
<%out.clear();%><%=json%>