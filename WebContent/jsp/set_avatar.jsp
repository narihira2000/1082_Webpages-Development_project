<%@taglib prefix="f" uri="https://project.com/jstl/fake" %>  
<!DOCTYPE html>
<html>
<head>
	<title>Set Avatar</title>
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
	 		
	 		<li class="nav-item">
	 			<a class="nav-link" href="member">My Blog</a>
	 		</li>
	 	
	 		<li class="nav-item">
	 			<a class="nav-link" href="logout">Logout</a>
	 		</li>
		 </ul>
	 	
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
	</nav>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">	
		<h3>Upload image for your avatar</h3><br>
	
		<form method="post" enctype="multipart/form-data" action="/project/setAvatar">
			<input type="hidden" name="username" value=${sessionScope.login}>
			<div class="custom-file">	
      			<input type="file" class="custom-file-input" accept="image/*" id="customFile" name="file">
      			<label class="custom-file-label" for="customFile">Choose image</label>
    		</div>
    		<br>
    		<br>
    		<button type="submit" class="btn btn-primary">Upload</button>
   		</form>
   		
   		<script>
			$(".custom-file-input").on("change", function() {
  				var fileName = $(this).val().split("\\").pop();
  				$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
			});
		</script>
   	</div>
</body>
</html>