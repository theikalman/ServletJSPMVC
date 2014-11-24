<%@page import="ac.id.stikompoltek.dto.Artikel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Header -->
<%@include file="header.jsp" %>

		<div class="col-sm-12 main">
			<h1 class="page-header">Artikel</h1>
			<h3 class="sub-header">Edit artikel</h3>
			
			<%
			Artikel selectedArtikel = (Artikel) request.getAttribute("selectedArtikel");
			%>
			
			<%=selectedArtikel.getIdArtikel() %><br/>
			<%=selectedArtikel.getJudul() %><br/>
			<%=selectedArtikel.getSeoUrl() %><br/>
			<%=selectedArtikel.getIsi() %><br/>
			<%=selectedArtikel.getKategori().getNama() %><br/>
			<%=selectedArtikel.getUser().getNamaLengkap() %><br/>
			
		</div>

		<!-- Bootstrap core JavaScript ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/jquery-2.1.1.min.js"></script>
		<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/bootstrap.min.js"></script>

<!-- Footer -->
<%@include file="footer.jsp" %>