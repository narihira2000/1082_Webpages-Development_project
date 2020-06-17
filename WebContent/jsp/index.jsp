<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Blog</title>
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
	 				<a class="nav-link" href="">Home</a>
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
		
		<div class="jumbotron text-center">
			<h1>Welcome to Blog</h1>
		</div>
		
		<div class="container">
			<h2>Newest Post</h2><br>
			<f:forEach var="message" items="${requestScope.newest}">
				<div class="card">
					<div class="card-header" style="display:flex;  justify-content: space-between; align-items:center;">
						<form method='post' action='process_article'>
		                    <%--  <input type='hidden' name='title' value='${message.title}'>
		                    <input type='hidden' name='content' value='${message.content}'> --%>
		                    <input type='hidden' name='ID' value='${message.ID}'>
		                    <%-- <input type='hidden' name='time' value='${message.localDateTime}'>
		                    <input type='hidden' name='username' value='${message.username}'> --%>
		                    <button type="submit" class="btn btn-link btn-lg" style="padding:0%;"><h3>${message.title}</h3></button>
		                </form>
						<div>
							<h6>${message.localDateTime}</h6>
							<h6 style="float:right;">Author : ${message.username} ID : ${message.ID}</h6>
						</div>
					</div>
		
					<div class="card-body">
						<h6>${message.content}</h6><br>
					</div>
				</div>
				<br>
			</f:forEach>
		</div>
    </body>
</html>