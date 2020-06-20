<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>  
<!DOCTYPE html>
<html>
<head>
<head>
	<title>${sessionScope.login}'s Blog</title>
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
	 			<a class="nav-link" href="logout">Home</a>
	 		</li>
	 	
	 		<li class="nav-item">
	 			<a class="nav-link" href="logout">Logout</a>
	 		</li>
	 		
	 		
	 	</ul>
	 	
	 	<span style="color:white; " class="nav-item">Online:${requestScope.onlineUser} people  Hi, ${sessionScope.login}</span>
	</nav>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<div class="row">
			

			<div class="col">
				<h2>New Post</h2><br>
				<form method="POST" action="new_message">
					<f:if test="${param.title!=null}">
						<h6 class="alert alert-danger">Title can't exceed 140 characters.</h6><br>
        			</f:if>
					<div class="card" style="padding: 2%;">
						<div class="form-group">
							<label for="inputTitle">Title</label>
							<input type="text" class="form-control" name="title" id="imputTitle" value='${param.title}'>
						</div>

						<div class="form-group">
							<label for="inputContent">Content</label>
							<textarea class="form-control" rows="4" name="content" id="inputContent">${param.content}</textarea>
						</div>
						<button type="submit" class="btn btn-outline-primary btn-lg btn-block">+ add post</button><br>

					</div>
				</form>

				<br><h2>All Posts</h2><br>
				
		        <f:forEach var="message" items="${requestScope.messages}">
			        <div class="card">
						<div class="card-header" style="display:flex; align-items: center; justify-content: space-between;">
							<form method='post' action='process_article'>
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
							<form method='post' action='del_message'>
		                        <input type='hidden' name='millis' value='${message.millis}'>
		                        <input type='hidden' name='ID' value='${message.ID}'>
		                        <button type="submit" class="btn btn-danger" style="float: right;">delete post</button>
		                    </form>
						</div>
					</div>
					<br>
		        </f:forEach>
			</div>
		</div>
	</div>

    
</body>
</html>