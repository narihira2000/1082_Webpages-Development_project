<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
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
				
				
				
        		
        		<h2>Login</h2>
				
				<form method="post" action="/project/login">
					<div class="form-group">
						<label>Username : </label>
						<input type="text" class="form-control" name="username" id="username" value='${param.username}' placeholder="Username">
					</div>

					<div class="form-group">
						<label>Password : </label>
						<input type="password" class="form-control" name="password" id="password" placeholder="Password">
					</div>

					<button type="submit" class="btn btn-primary">Login</button>
				</form>
				<br>

				<h6>Not a member?
					<a href="/project/register" class="card-link">Sign up</a>
				</h6>
			</div>
		</div>
	</div>

</body>
</html>