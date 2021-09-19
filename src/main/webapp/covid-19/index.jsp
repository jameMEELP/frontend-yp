<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../initialize.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file="../html-head.jsp"%>

		<style>
		.value {
			font-size: 45px;
			line-height: 1em;
			font-weight: 700;
		}
		.text-center {
			text-align: center !important;
		}
		</style>
	</head>
	<body>
		<%@include file="../body-header-top.jsp"%>
	
		<main>
			<div style="width: 1200px; margin: auto; margin-bottom: 20px; background-color: #FFFFFF;">
				<div style="margin-top: 50px;">
					<!-- class="ui center aligned segment" -->
					<h1 class="ui center aligned header">Covid - 19 Dashboard</h1>
					<h2 class="ui center aligned red header" id="date">Loading...</h2>
					<div class="ui stackable two column divided grid container">
						<div class="row">
							<div class="column">
								<div class="ui fluid horizontal red card" id="newCase">
									<div class="content">
										<h3 class="header">New Case in Thailand</h3>
										<div class="meta">Last update : <span id="lastUpdate">Loading...</span></div>
										<div class="right aligned description">
											<div class="value">0</div>
										</div>
										<div class="left aligned extra content">
											Returned from aboard : <span class="extraValue">0</span>
										</div>
									</div>
								</div>
							</div>
							<div class="column">
								<div class="ui fluid horizontal red card" id="totalCase">
									<div class="content">
										<h3 class="header">Total Case in Thailand</h3>
										<div class="meta">Last update : <span id="lastUpdate">Loading...</span></div>
										<div class="right aligned description">
											<div class="value">0</div>
										</div>
										<div class="left aligned extra content">
											Total returned from aboard : <span class="extraValue">0</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="sixteen wide column">
								<div class="ui fluid horizontal red card" id="caseByProvince">
									<div class="content">
										<h3 class="center aligned header">New Case in Thailand Divided by Province</h3>
										<div class="meta">Last update : <span id="lastUpdate">Loading...</span></div>
										<div class="right aligned description">
											<table id="kt_datatable" class="ui striped table" style="width: 100%"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<div style="clear: both; height: 8px;">&nbsp;</div>
	</body>

	<%@include file="../body-footer.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			// console.log("Ready!");
			init();
		});
	
		function init() {
			getCase("all");
			getCase("getByProvince");
		}
	
		function getCase(action) {
			let params = "?action=";
			if (action == "all") {
				params += "getAll";
				let obj = getData(params);
				updateCaseData(obj);
			} else if (action == "getByProvince") {
				params += "getByProvince";
				// let obj = getData(params);
				updateProvinceData(params);
			}
		}
	
		function getData(params) {
			let result = "";
			$.ajax({
				type: "POST",
				async: false,
				url : "<%=contextPath%>/service/covid-19/get.jsp" + params,
				success: function(data) {
					var obj = JSON.parse(data.trim());
					result = obj;
				}, error: function(data) {
					$('body').toast({
						class: 'error',
						showIcon: 'exclamation circle',
						message: "Error"
					});
				}
			});
			return result;
		}
	
		function updateCaseData(obj) {
			var txn_date = "";
			var update_date = "";
			var new_case = 0;
			var total_case = 0;
			var new_case_excludeabroad = 0;
			var total_case_excludeabroad = 0;
			obj.forEach(function(data, index) {
				new_case = data.new_case;
				total_case = data.total_case;
				new_case_excludeabroad = data.new_case_excludeabroad_net;
				total_case_excludeabroad = data.total_case_excludeabroad_net;
				txn_date = data.txn_date;
				update_date = data.update_date;
	
				$("#date").text(txn_date);
	
				$("#totalCase").find("#lastUpdate").text(update_date);
				$("#totalCase").find(".extraValue").text(total_case_excludeabroad).css({
					"color" : "#BF0000",
					"font-size": "20px",
					"margin-left" : "8px"
				});
				$("#totalCase").find(".value").text(total_case).css({
					"color" : "#BF0000"
				});
	
				$("#newCase").find("#lastUpdate").text(update_date);
				$("#newCase").find(".extraValue").text(new_case_excludeabroad).css({
					"color" : "#BF0000",
					"font-size": "20px",
					"margin-left" : "8px"
				});
				$("#newCase").find(".value").text(new_case).css({
					"color" : "#BF0000"
				});
			});
		}
	
		function updateProvinceData(params) {
			$("#kt_datatable").DataTable({
				pagingType: "full_numbers",
				responsive: true,
				select: true,
				buttons: [
					'colvis'
				],
				order: [[ 1, "desc" ]],
				lengthMenu: [5, 10, 25, 50],
				pageLength: 10,
				language: {
					'lengthMenu': 'Display _MENU_',
					'processing': '<center>Loading....</center>',
				},
				searchDelay: 500,
				processing: false,
				initComplete: function() {
					$("#kt_datatable").wrap("<div style='overflow:auto; width:100%;'></div>");
				},
	
				ajax: {
					url: "<%=contextPath%>/service/covid-19/get.jsp" + params,
					type: 'POST',
					dataSrc: ""
				},
	
				columns: [
					{
						data: 'province',
						title: 'Province',
					},
					{
						data: 'new_case',
						title: 'New case',
						className: 'text-center',
					},
					{
						data: 'total_case',
						title: 'Total Case',
						className: 'text-center',
					},
					{
						data: 'new_case_excludeabroad',
						title: 'New Case Excludeabroad',
						className: 'text-center',
					},
					{
						data: 'total_case_excludeabroad',
						title: 'Total case Excludeabroad',
						className: 'text-center',
					},
					/* {
						data: 'txn_date',
						title: 'Date',
						className: 'text-center',
					}, */
				]
			});
		}
	</script>
</html>