<%@page import="ac.id.stikompoltek.dto.Tag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- Header -->
<%@include file="header.jsp"%>

<div class="col-sm-12 main">
	<h1 class="page-header">
		Artikel <small class="text-muted">Edit artikel</small>
	</h1>

	<%
	Tag tagSelected = (Tag) request.getAttribute("tagSelected");
	%>

	<form action="<%=request.getServletContext().getInitParameter("BASE_URL") %>/admin/tag" method="post" role="form">
		<div class="col-sm-12">
			<div class="form-group">
				<label for="id-tag">Id Tag</label>
				<input type="text" name="id-tag" value="<%=tagSelected.getIdTag() %>" class="form-control" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="id-kategori">Judul</label>
				<input type="text" name="nama-tag" value="<%=tagSelected.getNama() %>" class="form-control" autofocus="autofocus" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="ket-tag">Keterangan</label>
				<input type="text" name="ket-tag" value="<%=tagSelected.getKet() %>" class="form-control" autofocus="autofocus" />
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