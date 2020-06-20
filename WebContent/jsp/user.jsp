<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>  
<!DOCTYPE html>
<html>
<head>
<head>
	<title>${requestScope.username}'s Blog</title>
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
	 			<a class="nav-link" href="/project">Home</a>
	 		</li>
	 		
	 		<f:choose>
		 		<f:when test='${sessionScope.login == null}'>
		 			<li class="nav-item">
						<a class="nav-link" href="/project/register">Sign up</a>
					</li>
					
					<li class="nav-item">
						<a class="nav-link" href="/project/jsp/login.jsp">Login</a>
					</li>
		 		</f:when>
		 		<f:otherwise>
		 			<li class="nav-item">
						<a class="nav-link" href="/project/member">My Blog</a>
					</li>
		 			<li class="nav-item">
						<a class="nav-link" href="/project/logout">Logout</a>
					</li>
		 		</f:otherwise>
	 		</f:choose>
	 	</ul>
	 	<f:if test="${sessionScope.login != null}">
	 		<span style="color:white; " class="nav-item">Hi, ${sessionScope.login}</span>
	 	</f:if>
	</nav>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<div class="row">
			

			<div class="col">
				<br><h2>${requestScope.username}'s All Posts</h2><br>
				
		        <f:forEach var="message" items="${requestScope.messages}">
			        <div class="card">
						<div class="card-header" style="display:flex; align-items: center; justify-content: space-between;">
							<form method='post' action='/project/process_article'>
		                        <%-- <input type='hidden' name='title' value='${message.title}'>
		                        <input type='hidden' name='content' value='${message.content}'> --%>
		                        <input type='hidden' name='ID' value='${message.ID}'>
		                        <%-- <input type='hidden' name='time' value='${message.localDateTime}'>
		                        <input type='hidden' name='username' value='${message.username}'> --%>
		                        <button type="submit" class="btn btn-link btn-lg" style="padding:0%;"><h3>${message.title}</h3></button>
		                    </form>
							<div>
								<h6>${message.localDateTime}</h6>
								<h6 style="float:right;">Author : ${message.username}  id : ${message.ID}</h6>
							</div>
						</div>
	
	
						<div class="card-body">
							<h6>${message.content}</h6><br>
							
						</div>
					</div>
					<br>
		        </f:forEach>
			</div>
		</div>
	</div>

    
</body>
</html>