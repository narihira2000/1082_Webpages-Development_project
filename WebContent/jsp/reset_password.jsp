<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Reset Password</title>
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
	 			<a class="nav-link" href="/project/register">Sign up</a>
	 		</li>
	 	
	 		<li class="nav-item">
	 			<a class="nav-link" href="login.jsp">Login</a>
	 		</li>
	 	</ul>
	</nav>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<f:if test="${requestScope.errors != null}">
			<div class="container">
				<f:forEach var="error" items="${requestScope.errors}">
           			<h6 class="alert alert-danger">${error}</h6>
        		</f:forEach>
			</div>
        </f:if>
		<div class="card">
			
			<div class="card-body">
        		<h2>Reset Password</h2>
				
				<form method="post" action="/project/reset_password">
					<input type="hidden" name="username" value='${requestScope.acct.name}'>
					<input type="hidden" name="email" value='${requestScope.acct.email}'>
					<input type="hidden" name="token" value='${sessionScope.token}'>
					<div class="form-group">
							<label>Password : </label>
							<input type="password" class="form-control" name="password" maxlength='16' id="password" placeholder="Password (8 ~ 16 characters)">
						</div>
				
						<div class="form-group">
							<label>Confirm Password : </label>
							<input type="password" class="form-control" name="password2" maxlength='16' id="password2" placeholder="Confirm Password">
						</div>

					<button type="submit" class="btn btn-primary">Reset Password</button>
				</form>
				<br>
			</div>
		</div>
	</div>

</body>
</html>