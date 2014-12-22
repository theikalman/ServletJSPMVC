<%@page import="java.util.List"%>
<%@page import="ac.id.stikompoltek.dto.Kategori"%>
<%@page import="ac.id.stikompoltek.dto.Artikel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Header -->
<%@include file="header.jsp"%>

<div class="col-sm-12 main">
	<h1 class="page-header">
		Artikel <small class="text-muted">Add new artikel</small>
	</h1>

	<%
			List<Kategori> kategoris = (List<Kategori>) request.getAttribute("listKategori");
			%>

	<form
		action="<%=request.getServletContext().getInitParameter("BASE_URL") %>/admin/artikel"
		method="post" role="form">
		<div class="col-sm-6">
			<div class="form-group">
				<label for="id-kategori">Judul</label> <input type="text"
					name="judul-artikel" class="form-control" autofocus="autofocus" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="id-kategori">Nama Kategori</label> <select
					name="id-kategori" class="form-control">
					<% for(int i=0; i<kategoris.size(); i++) { %>
					<option value="<%=kategoris.get(i).getIdKategori() %>"><%=kategoris.get(i).getNama() %></option>
					<% } %>
				</select>
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="id-kategori">Keterangan</label> <input type="text"
					name="ket-artikel" class="form-control" />
			</div>
		</div>
		<div class="col-sm-12">
			<div class="form-group">
				<label for="id-kategori">Isi Artikel</label>
				<textarea name="isi-artikel" class="form-control"></textarea>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="form-group">
				<input type="submit" name="act" value="save"
					class="btn btn-lg btn-default">
			</div>
		</div>
	</form>

</div>

<!-- Bootstrap core JavaScript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/jquery-2.1.1.min.js"></script>
<script
	src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/bootstrap.min.js"></script>

<!-- Footer -->
<%@include file="footer.jsp"%>