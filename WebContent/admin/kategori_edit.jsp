<%@page import="ac.id.stikompoltek.dto.Kategori"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Header -->
<%@include file="header.jsp"%>

<div class="col-sm-12 main">
	<h1 class="page-header">
		Artikel <small class="text-muted">Add new artikel</small>
	</h1>

	<% Kategori selectedKategori = (Kategori) request.getAttribute("kategoriSelected"); %>
	
	<form action="<%=request.getServletContext().getInitParameter("BASE_URL") %>/kategori" method="post" role="form">
		<div class="col-sm-12 hidden">
			<div class="form-group">
				<label for="id-kategori">ID Kategori</label>
				<input type="text" name="id-kategori" class="form-control" value="<%=selectedKategori.getIdKategori() %>" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="nama-kategori">Nama Kategori</label>
				<input type="text" name="nama-kategori" class="form-control" autofocus="autofocus" required="required" value="<%=selectedKategori.getNama() %>" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="ket-kategori">Ket Kategori</label>
				<input type="text" name="ket-kategori" class="form-control" value="<%=selectedKategori.getKet() %>" />
			</div>
		</div>
		<div class="col-sm-12">
			<div class="form-group">
				<input type="submit" name="act" value="save" class="btn btn-lg btn-default">
			</div>
		</div>
	</form>

</div>

<!-- Bootstrap core JavaScript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/bootstrap.min.js"></script>

<!-- Footer -->
<%@include file="footer.jsp"%>