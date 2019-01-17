<html lang="en">
<head>

<%
	if (session.getAttribute("userDisplayName") == null) {
		response.sendRedirect("login.do");
	}
%>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAMITN Application</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- 
<link rel="stylesheet" href="css/gijgo.min.css">
 -->

<!-- jQuery library -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/expence-management.js"></script>
<script src="js/administration.js"></script>

<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Popper JS -->
<script src="js/popper.min.js"></script>
<!-- 
<script src="js/gijgo.min.js"></script>
 -->
<script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
<script type="text/javascript">
	$(document).ready(function() {
		$('#fdRecord').hide();
		addFDDetails();
		$('#fdMatDate').datepicker({
			uiLibrary : 'bootstrap4'
		});
		$('#fdDepDate').datepicker({
			uiLibrary : 'bootstrap4'
		});
		loadFinanceDashboard();
		$('#refereshFinDash').click(function() {
			loadFinanceDashboard();
		});
		$('#showFDRecords').click(function() {
			if($('#fdRecord').is(':visible')) {
				$('#showFDRecords').removeClass('fa-eye-slash');
				$('#showFDRecords').addClass('fa-eye');
				$('#fdRecord').slideUp(500);
			}
			else {
				$('#showFDRecords').removeClass('fa-eye');
				$('#showFDRecords').addClass('fa-eye-slash');
				
				$('#fdRecord').slideDown(500);
			}
		});
		addUserAction();
	});
</script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top">
		<a class="navbar-brand" href="#"><b>IAMI<font color='orange'>TN</font></b></a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav mr-auto">
 				<li class="nav-item"><a class="nav-link" href="#">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Mapping</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Add Resources </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#" data-toggle="modal" data-target="#addUserModal">Add Customer</a> 
						<a class="dropdown-item" href="#">Add Master Unit</a> 
						<a class="dropdown-item" href="#">Add Slave Unit</a> 
						<a class="dropdown-item" href="#">Add Sensor</a>
					</div>
				</li>

				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Finance </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#" data-toggle="modal"
							data-target="#addExpModal">Add Expence</a> <a
							class="dropdown-item" href="#" data-toggle="modal"
							data-target="#addFDModal">Add FD</a> <a class="dropdown-item"
							href="#">View Details</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#">Settings</a></li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Welcome <font color='orange'><%=(String) session.getAttribute("userDisplayName")%></font>
				</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="logout.do">Logout</a>
					</div></li>
			</ul>
		</div>
	</nav>
	<br />
	<br />
	<br />
	<div class="container">
		<script type="text/javascript">
			loadAddExpenceModel();
			addExpenceType();
			loadFDModel();
			writeAddUserModel();
		</script>
		<div>
			<p>
				<A href='#' id='refereshFinDash'>Referesh Data</A>
			</p>
			<h4>Fixed Deposit Details:</h4>
			<div class="card bg-light">
				<div class="card-body">
					<h4 class="card-title btn-secondary text-center">Total Deposit
						Form</h4>
					<p class="card-text text-center" id='totalFDFrm'>0</p>
				</div>
			</div>
			<br />
			<div class="card bg-light">
				<div class="card-body">
					<h4 class="card-title btn-info text-center">Total Deposit
						Amount</h4>
					<p class="card-text text-center" id='totalFDDeposit'>0 INR</p>
				</div>
			</div>
			<br />
			<div class="card bg-light">
				<div class="card-body">
					<h4 class="card-title btn-success text-center">Total Maturity
						Amount</h4>
					<p class="card-text text-center" id='totalFDMaturity'>0 INR</p>
				</div>
			</div>
			<br />
			<p>Show records: <i id='showFDRecords' class="far fa-eye"></i></p>
			<div id='fdRecord' style='border: 1px solid black'>
				<table id='FDTable' class="table table-striped">
					<thead>
						<tr>
							<th>Account</th>
							<th>Deposit Date</th>
							<th>Deposit Amount</th>
							<th>Maturity Date</th>
							<th>Maturity Amount</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<h4>Expence Details Details:</h4>
			<div class="card bg-light">
				<div class="card-body">
					<h4 class="card-title btn-secondary text-center">Total
						Expences</h4>
					<p class="card-text text-center" id='totalExpenceAmt'>0 INR</p>
				</div>
			</div>
		</div>
	</div>
	<input type='hidden' name='userID' id='userID'
		value='<%=(Integer) session.getAttribute("userID")%>' />
</body>
</html>
