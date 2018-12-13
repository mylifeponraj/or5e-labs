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

<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Popper JS -->
<script src="js/popper.min.js"></script>
<!-- 
<script src="js/gijgo.min.js"></script>
 -->
    <script src="https://unpkg.com/gijgo@1.9.11/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.11/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		addFDDetails();
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
				<!-- 
 				<li class="nav-item"><a class="nav-link" href="#">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Home Automation</a></li>
-->

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Finance </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#" data-toggle="modal" data-target="#addExpModal">Add Expence</a> 
						<a class="dropdown-item" href="#" data-toggle="modal" data-target="#addFDModal">Add FD</a> 
						<a class="dropdown-item" href="#">View Details</a>
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
	<div class="container-fluid">
		<script type="text/javascript">
			loadAddExpenceModel();
			addExpenceType();
		</script>

		<!-- The Modal -->
		<div class="modal" id="addFDModal" data-keyboard='false' data-backdrop='static'>
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Add Fixed Deposit</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">FD number</span>
							</div>
							<input type="text" id='fdNum' name='fdNum' class="form-control" placeholder="FD number">
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">FD Name</span>
							</div>
							<input type="text" id='fdName' name='fdName' class="form-control" placeholder="FD Name">
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text">FD Date</span>
							</div>
							<input id="fdDepDate" name="fdDepDate" class="form-control" width="276" />
							<script>
						        $('#fdDepDate').datepicker({
						            uiLibrary: 'bootstrap4'
						        });
							</script>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend"">
								<span class="input-group-text">FD Amount</span>
							</div>
							<input type="text" id='fdDepAmt' name='fdDepAmt' class="form-control" placeholder="FD Amount">
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend"">
								<span class="input-group-text">FD Maturity Date</span>
							</div>
							<input id="fdMatDate" name="fdMatDate" class="form-control" width="276" />
							<script>
						        $('#fdMatDate').datepicker({
						            uiLibrary: 'bootstrap4'
						        });
							</script>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend"">
								<span class="input-group-text">FD Maturity Amount</span>
							</div>
							<input type="text" id='fdMatAmt' name='fdMatAmt' class="form-control" placeholder="FD Maturity Amount">
						</div>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type='button' class='btn btn-primary' id='addFDBtn' name='addFDBtn'>Add Deposit</button>
						<button type="button" id='clearAddFDBtn' class="btn btn-secondary" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<input type='hidden' name='userID' id='userID'
		value='<%=(Integer) session.getAttribute("userID")%>' />
</body>
</html>
