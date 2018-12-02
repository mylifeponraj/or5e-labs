<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IAMITN Application</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- jQuery library -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/expence-management.js"></script>

<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Popper JS -->
<script src="js/popper.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

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
					</div>
				</li>
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
<div class="modal" id="addFDModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Modal body..
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
	</div>
	<input type='hidden' name='userID' id='userID'
		value='<%=(Integer) session.getAttribute("userID")%>' />
</body>
</html>
