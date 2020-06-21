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
	    <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top" style="display:flex; justify-content:space-between; justify-items:center;">
	 	
	 		<ul class="navbar-nav">
				<li class="nav-item">
	 				<a class="nav-link" href="">Home</a>
	 			</li>
	 			
	 			<f:choose>
		 			<f:when test='${sessionScope.login == null}'>
		 				<li class="nav-item">
			 				<a class="nav-link" href="register">Sign up</a>
			 			</li>
			 		
			 			<li class="nav-item">
			 				<a class="nav-link" href="jsp/login.jsp">Login</a>
			 			</li>
		 			</f:when>
		 			<f:otherwise>
		 				<li class="nav-item">
			 				<a class="nav-link" href="member">My Blog</a>
			 			</li>
		 				<li class="nav-item">
			 				<a class="nav-link" href="logout">Logout</a>
			 			</li>
		 			</f:otherwise>
	 			</f:choose>
	 		</ul>
	 		
	 		<f:if test="${sessionScope.login != null}">
	 			<ul class="navbar-nav ml-auto nav-flex-icons">
			 		<li class="nav-item avatar dropdown">
			 		
		        		<a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          			<img src="data:image/jpeg;base64,${sessionScope.UserAvatar}" class="rounded-circle z-depth-0" alt="avatar image" height="35">
		        		</a>
		        		
		        		<div class="dropdown-menu dropdown-menu-lg-right dropdown-secondary" aria-labelledby="navbarDropdownMenuLink">
		        			<h5 class="dropdown-header">${sessionScope.login}</h5>
		        			<div class="dropdown-divider"></div>
		          			<a class="dropdown-item" href="jsp/set_avatar.jsp">Set avatar</a>
		        		</div>
		        		
		      		</li>
		      	</ul>
	 		
	 			<span style="color:white; " class="nav-item">Hi, ${sessionScope.login}</span>
	 		</f:if>
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
							<h6 style="float:right;">Author : <a href='/project/user/${message.username}'>${message.username}</a></h6>
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