<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/icons/icon.png">

<!-- Bootstrap core CSS -->
<link href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/css/login.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->

</head>
<body>

	<div class="container">
		<form class="form-signin" role="form" action="<%=request.getServletContext().getInitParameter("BASE_URL") + "/admin/login" %>" method="post">
			<h2 class="form-signin-heading">Login</h2>
			<input name="username" type="text" class="form-control" placeholder="Username" required autofocus />
			<input name="password" type="password" class="form-control" placeholder="Password" required />
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</div>
	<!-- /container -->

</body>
</body>
</html>