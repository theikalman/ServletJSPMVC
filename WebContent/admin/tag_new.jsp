<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- Header -->
<%@include file="header.jsp"%>

<div class="col-sm-12 main">
	<h1 class="page-header">
		Tag <small class="text-muted">Add new artikel</small>
	</h1>

	<form action="<%=request.getServletContext().getInitParameter("BASE_URL") %>/admin/tag" method="post" role="form">
		<div class="col-sm-3">
			<div class="form-group">
				<label for="nama-tag">Nama</label>
				<input type="text" name="nama-tag" class="form-control" autofocus="autofocus" />
			</div>
		</div>
		<div class="col-sm-3">
			<div class="form-group">
				<label for="ket-tag">Keterangan</label>
				<input type="text" name="ket-tag" class="form-control" />
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