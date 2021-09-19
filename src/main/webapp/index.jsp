<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="initialize.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="html-head.jsp"%>

		<style>
		</style>
	</head>
	<body>
		<%@include file="body-header-top.jsp"%>
	
		<main>
			<div style="width: 1200px; margin: auto; margin-bottom: 20px; background-color: #FFFFFF; height:600px;">
				Home
			</div>
		</main>
	
		<div style="clear: both; height: 30px;">&nbsp;</div>
	</body>

	<%@include file="../body-footer.jsp"%>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.3.1/dist/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css"
		href="https://cdn.jsdelivr.net/npm/fomantic-ui@2.8.8/dist/semantic.min.css">
	<script
		src="https://cdn.jsdelivr.net/npm/fomantic-ui@2.8.8/dist/semantic.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			console.log("Ready!");
			init();
		});
	
		function init() {
			$(".ui.dropdown").dropdown();
		}
	</script>
</html>