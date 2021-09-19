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
	/* CONTEXT PATH */
	String scheme = request.getScheme();
	String serverName = request.getServerName();
	int serverPort = request.getServerPort();
	String contextPath = request.getContextPath();
	String servletPath = request.getServletPath();
	contextPath = scheme + "://" + serverName + ":" + serverPort + contextPath;
	// System.out.println("contextPath: " + contextPath);
	// System.out.println("servletPath: " + servletPath);

	String Title = "";
	String Developer = "Yodsarun Dev";
	if (servletPath.matches("(.*)covid-19(.*)")) {
		Title = "Covid-19 Summary | " + Developer;
		// System.out.println(Title);
	} else {
		Title = "Home | " + Developer;
	}
%>