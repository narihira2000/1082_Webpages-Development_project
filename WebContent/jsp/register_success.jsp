<!DOCTYPE html>
<html>
<head>
	<title>Sign up Success!</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
	 	
	 	<ul class="navbar-nav">
			<li class="nav-item">
	 			<a class="nav-link" href=/project>Home</a>
	 		</li>
	 	
	 		<li class="nav-item">
	 			<a class="nav-link" href="register">Sign up</a>
	 		</li>
	 	
	 		<li class="nav-item">
	 			<a class="nav-link" href="jsp/login.jsp">Login</a>
	 		</li>
	 	</ul>
	</nav>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
	 	<div class="alert alert-success">
	 		<h4>${param.username} Sign up success!</h4>
	 		<h6>Please check your email inbox to validate the account.</h6><br>
	 		<h6>Back to <a href="/project">home</a></h6>
	 	</div>
	</div>
</body>
</html>