<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
	 			<a class="nav-link" href="/project">Home</a>
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
	
	<div class="container">
		<div class="row">

			<div class="col">
				<a href="javascript:history.back(-1)" class="btn btn-link">＜＜ Back</a>

				<div class="card">
					<div class="card-header" style="display:flex; align-items: center; justify-content: space-between;">
						<h3>${requestScope.messages.title}</h3>
						<div>
							<h6>${requestScope.messages.localDateTime}</h6>
							<h6 style="float:right;">Author : <a href="/project/user/${requestScope.messages.username}">${requestScope.messages.username}</a></h6>
						</div>
					</div>


					<div class="card-body">
						<h6>${requestScope.messages.content}</h6><br>
					</div>
				</div>
				<br>

				<h4>Comment</h4>
				
				<div class="col" style="padding-left: 3%;">
					<f:forEach var="reply" items="${requestScope.replies}">
				        <div class="card">
							<div class="card-header" style="display:flex; align-items: center; justify-content: space-between;">
								<h4><a href="/project/user/${reply.username}">${reply.username}</a></h4>
								<h6>${reply.localDateTime}</h6>
							</div>
		
		
							<div class="card-body">
								<h6>${reply.content}</h6><br>
								<c:if test="${reply.username == sessionScope.login}">
									<form method='post' action='del_reply'>
		                        		<input type='hidden' name='millis' value='${reply.millis}'>
		                        		<input type='hidden' name='ID' value='${requestScope.messages.ID}'>
		                        		<button type="submit" class="btn btn-danger" style="float: right;">delete post</button>
		                    		</form>
        						</c:if>
							</div>
						</div>
						<br>
			        </f:forEach>
		        </div>

				<f:if test="${sessionScope.login != null}">
					<form method="POST" action="new_reply">
						<div class="card" style="padding: 2%;">
							<input type='hidden' name='mID' value='${requestScope.messages.ID}'>
							<div class="form-group">
								<label for="inputContent">Content</label>
								<textarea class="form-control" rows="4" name="content" id="inputContent"></textarea>
							</div>
							<button type="submit" class="btn btn-outline-primary btn-lg btn-block">+ reply the post</button><br>
	
						</div>
					</form>
				</f:if>
				<br>
			</div>
		</div>
	</div>

</body>
</html>