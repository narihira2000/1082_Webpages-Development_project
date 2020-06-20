<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Reset password mail sent</title>
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
	
	<c:choose>
		<c:when test="${requestScope.isExist}">
			<div class="container">
			 	<div class="alert alert-success">
			 		<h4>Reset password mail sent!</h4>
			 		<h6>Reset password mail has been sent to ${requestScope.email}<br>
			 		Please check your email inbox to reset the password.</h6><br>
			 		<h6>Back to <a href="/project">home</a></h6>
			 	</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="alert alert-danger">
					<h4>Account not found!</h4>
					<h6>${requestScope.email} is not a registered email.</h6><br>
					<h6><a href="/project/forgot.html">Retry</a> or Back to <a href="/project">home</a></h6>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
	
</body>
</html>