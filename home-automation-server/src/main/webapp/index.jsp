<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<form class="form-signin" action="login.do" method="get">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">User ID</label> 
			<input type="text" id="userKID" class="form-control" placeholder="User ID" required autofocus> 
			<label for="inputPassword" class="sr-only">User Key</label> 
			<input type="password" id="userKey" class="form-control" placeholder="User Key" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>
	</div>
</body>
</html>
