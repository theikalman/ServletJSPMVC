<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="ac.id.stikompoltek.dto.Artikel"%>
<%@page import="ac.id.stikompoltek.service.ArtikelService"%>
<%@page import="ac.id.stikompoltek.dto.User" %>
<%@page import="java.util.Locale"%>
<%@page import="org.apache.catalina.Server"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Header -->
<%@include file="header.jsp" %>
	
		<div class="col-sm-12 main">
			<h1 class="page-header">Artikel</h1>
			<%
			List<Artikel> artikels = (List<Artikel>) request.getAttribute("artikelList");
			for(int i=0; i<artikels.size(); i++) {
			%>
				<div class="col-sm-4">
					<div class="col-sm-12 artikel-list">
						<a href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/artikel?act=delete&url=<%=artikels.get(i).getSeoUrl() %>" class="btn btn-lg btn-circle marginable pull-right glyphicon glyphicon-remove-sign" alt="delete"></a>
						<a href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/artikel?act=edit&url=<%=artikels.get(i).getSeoUrl() %>">
							<h3><%=artikels.get(i).getJudul() %> 
							<small class="glyphicon glyphicon-pencil"></small></h3>
						</a>
						<div class="small text-muted border-bottom marginable-bottom">
							<span class="glyphicon glyphicon-user"></span> <%=artikels.get(i).getUser().getNamaLengkap() %> | 
							<span class="glyphicon glyphicon-folder-close"></span> <%=artikels.get(i).getKategori().getNama() %>
						</div>
						<%=artikels.get(i).getIsi() %>
					</div>
				</div>
			<%
			}
			%>
		</div>
	
		<!-- Bootstrap core JavaScript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/jquery-2.1.1.min.js"></script>
		<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/bootstrap.min.js"></script>


<!-- Footer -->
<%@include file="footer.jsp" %>

