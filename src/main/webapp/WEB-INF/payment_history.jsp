<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:include page="user_navbar.jsp" />
<div class="container-fluid">
	<div class="row">
		<jsp:include page="user_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"
			style="padding-bottom: 85px;">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Card history</h1>
			<div class="btn-toolbar mb-2 mb-md-0">CARD HISTORY</div>
		</div>
		<div class="container">
			<form action="ServletController" method="post">
				<input type="hidden" name="currentPage" value="0" /> <input
					type="hidden" name="clientId" value="${client.id}" />
				<div class="form-row">
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="idCard"><i class="far fa-credit-card"></i>
							Card</label> <select class="custom-select" id="idCard" name="idCard"
							style="line-height: 14px; height: 31px;">
							<c:forEach items="${cards}" var="creditCards">
								<option value="${creditCards.id}">${creditCards.cardNumber}</option>
							</c:forEach>
						</select>
						<script type="text/javascript">
								      var val = ${searchedCardId};
								      var text = '${searchedCardNumber}';
								      $("select option[value=" + val + "]").attr('selected', 'true').text(text);
					    </script>
					</div>
					<div class="col-md-3 mb-3 input-group-sm">
						<label for="startHistory"><i class="far fa-calendar-alt"></i> From</label>
						<input type="date"
							   class="form-control"
                               id="startHistory"
                               name="startHistory"
                               value="${startDate}"
                               required>
						<!-- <div class="valid-feedback">Looks good!</div> -->
					</div>
					<div class="col-md-3 mb-3 input-group-sm">
						<label for="endHistory"><i class="far fa-calendar-alt"></i>
							To</label> <input type="date" class="form-control" id="endHistory"
							name="endHistory" value="${endDate}" required>
						<!-- <div class="valid-feedback">Looks good!</div> -->
					</div>
					<div class="col-md-2 mb-3 input-group-sm">
						<div style="margin-top: 31px;"></div>
						<button class="btn btn-success btn-sm form-control" type="submit"
							onclick="window.localStorage.setItem('panel', 'payment_history');"
							name="command" value="payment_history">
							<i class="fas fa-search"></i> Search
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="container" style="margin-top: -27px;">
			<ul class="nav nav-tabs" id="myTab" role="tablist"
				style="height: 25px; font-size: 13px; line-height: 10px;">
				<li class="nav-item"><a style="color: #000000"
					class="nav-link active" id="table-tab" data-toggle="tab"
					href="#table" role="tab" aria-controls="home" aria-selected="true"><i
						class="fas fa-table"></i> Table</a></li>
				<li class="nav-item"><a style="color: #000000" class="nav-link"
					id="chart-tab" data-toggle="tab" href="#chart" role="tab"
					aria-controls="profile" aria-selected="false"><i
						class="fas fa-chart-line"></i> Chart</a></li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="table" role="tabpanel"
					aria-labelledby="table-tab">
					<div class="card">
						<div class="card-body">
							<table id="historyTable"
								class="table table-bordered table-hover table-sm table-striped"
								style="width: 100%; font-size: 9pt;">
								<tr style="text-align: center; vertical-align: middle;">
									<td rowspan="2"
										style="vertical-align: middle; font-weight: bold;">#</td>
									<td rowspan="2"
										style="vertical-align: middle; font-weight: bold;">Date</td>
									<td rowspan="2"
										style="vertical-align: middle; font-weight: bold;">Time</td>
									<td rowspan="2"
										style="vertical-align: middle; font-weight: bold;">Brief
										about payment</td>
									<td colspan="3"
										style="vertical-align: middle; font-weight: bold;">Service</td>
									<td rowspan="2"
										style="vertical-align: middle; font-weight: bold;">Amount</td>
								</tr>
								<tr style="text-align: center; vertical-align: middle;">
									<td style="vertical-align: middle; font-weight: bold;">Group</td>
									<td style="vertical-align: middle; font-weight: bold;">Name</td>
									<td style="vertical-align: middle; font-weight: bold;">Description</td>
								</tr>
								<c:forEach items="${paymentReport}" var="pagination">
									<tr>
										<td>${pagination.id}</td>
										<td style="text-align: center;">${pagination.datePayment}</td>
										<td style="text-align: center;">${pagination.timePayment}</td>
										<td>${pagination.descriptionPayment}</td>
										<td>${pagination.paymentDataGroup}</td>
										<td>${pagination.paymentDataName}</td>
										<td>${pagination.paymentDataDescription}</td>
										<td style="text-align: right;">${pagination.amountPayment}</td>
									</tr>
								</c:forEach>
							</table>

							<!-- PAGINATION -->
							<jsp:include page="pagination.jsp" />

						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="chart" role="tabpanel"
					aria-labelledby="chart-tab">
					<div class="card">
						<div class="card-body">
							<div id="chart"
								style="display: block; width: 570px; height: 285px;">
								<canvas id="chart-area" width="570" height="285"
									class="chartjs-render-monitor"
									style="display: block; width: 570px; height: 285px;"></canvas>
								<script>
				var labelsF = ${chartLabel};
				var datasF = ${chartData};
				var ctx = document.getElementById("chart-area").getContext('2d');
				var myChart = new Chart(ctx, {
				    type: 'pie',
				    data: {
				        labels: labelsF,
				        datasets: [{
				            label: '# of Votes',
				            data: datasF,
				            backgroundColor: [
				                'rgba(255, 99, 132, 0.2)',
				                'rgba(54, 162, 235, 0.2)',
				                'rgba(255, 206, 86, 0.2)',
				                'rgba(75, 192, 192, 0.2)',
				                'rgba(153, 102, 255, 0.2)',
				                'rgba(255, 159, 64, 0.2)'
				            ],
				            borderColor: [
				                'rgba(255,99,132,1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				    	legend: {
			                display: true,
			                fullWidth : true,
					        display: true,
					        position: "right",
			                labels: {
			                    fontColor: "#000000",
			                }
			            },
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
				</script>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
	</div>
</div>
<ctg:footer />
