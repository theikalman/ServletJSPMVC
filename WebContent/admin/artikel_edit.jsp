<%@page import="java.util.List"%>
<%@page import="ac.id.stikompoltek.dto.Kategori"%>
<%@page import="ac.id.stikompoltek.dto.Artikel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Header -->
<%@include file="header.jsp" %>

		<div class="col-sm-12 main">
			<h1 class="page-header">Artikel <small class="text-muted">Edit artikel</small></h1>
			
			<%
			Artikel selectedArtikel = (Artikel) request.getAttribute("selectedArtikel");
			List<Kategori> kategoris = (List<Kategori>) request.getAttribute("listKategori");
			%>
			
			<form action="<%=request.getServletContext().getInitParameter("BASE_URL") %>/artikel" method="post" role="form">
				<div class="col-sm-12">
					<div class="form-group">
						<label for="id-kategori">Id Artikel</label>
						<input type="text" name="id-artikel" value="<%=selectedArtikel.getIdArtikel() %>" class="form-control"/>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label for="id-kategori">Judul</label>
						<input type="text" name="judul-artikel" value="<%=selectedArtikel.getJudul() %>" class="form-control" autofocus="autofocus" />
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="id-kategori">Nama Kategori</label>
						<select name="id-kategori" class="form-control">
						<% for(int i=0; i<kategoris.size(); i++) { %>
							<% if(kategoris.get(i).getIdKategori() == selectedArtikel.getKategori().getIdKategori()) { %>
									<option value="<%=kategoris.get(i).getIdKategori() %>" selected="selected"><%=kategoris.get(i).getNama() %></option>
							<% } else { %>
									<option value="<%=kategoris.get(i).getIdKategori() %>"><%=kategoris.get(i).getNama() %></option>
							<% } %>
						<% } %>
						</select>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="id-kategori">Keterangan</label>
						<input type="text" name="ket-artikel" value="<%=selectedArtikel.getKet() %>" class="form-control" />
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<label for="id-kategori">Isi Artikel</label>
						<textarea name="isi-artikel" class="form-control"><%=selectedArtikel.getIsi() %></textarea>
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
<%@include file="footer.jsp" %>