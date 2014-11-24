<%@page import="ac.id.stikompoltek.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/icons/icon.png">
		
		<title>Dashboard | Apps By Aji</title>
		
		<!-- Bootstrap core CSS -->
		<link href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom styles for this template -->
		<link href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/css/dashboard.css" rel="stylesheet">
		
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
		
		
		<%
		// Login checking
		User user = (User)session.getAttribute("userLoggedIn");
		if (user == null) {
			response.sendRedirect(request.getServletContext().getInitParameter("BASE_URL") + "/login");
			return;
		}
		%>	
	</head>
	<body>
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/dashboard ">JSPServlet</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-left">
						<li class="dropdown">
		                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Blog</a>
		                  <ul class="dropdown-menu" role="menu">
		                    <li><a href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/artikel">Artikel</a></li>
		                    <li><a href="kategori.jsp">Kategori</a></li>
		                    <li><a href="tag.jsp">Tag</a></li>
		                  </ul>
		                </li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
		                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
		                  	<span class="glyphicon glyphicon-user"></span>
		                  </a>
		                  <ul class="dropdown-menu" role="menu">
		                  	<li class="dropdown-header"><%=user.getUsername().toUpperCase() %></li>
		                  	<li class="divider"></li>
		                    <li><a href="#">Settings</a></li>
		                    <li class="divider"></li>
		                    <li><a href="http://localhost:8080/ServletJSPMVC/logout">Logout</a></li>
		                  </ul>
		                </li>
					</ul>
					<form class="navbar-form navbar-right">
						<input type="text" class="form-control" placeholder="Search...">
			        </form>
				</div>
			</div>
		</div>