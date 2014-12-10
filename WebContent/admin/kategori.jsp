<%@page import="ac.id.stikompoltek.dto.Kategori"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Header -->
<%@include file="header.jsp"%>

<div class="col-sm-12 main">
	<div class="row">
		<div class="col-xs-10">
			<h1 class="page-header">Kategori</h1>
		</div>
		<div class="col-xs-2 pull-right">
			<a
				href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/kategori?act=add"
				class="btn btn-primary pull-right"><i
				class="glyphicon glyphicon-plus"> </i></a>
		</div>
	</div>
	<div class="row">
		<%
		List<Kategori> kategoris = (List<Kategori>) request.getAttribute("kategoriList");
		for(Kategori kategori : kategoris) {
		%>
		<div class="col-sm-4">
			<div class="artikel-list">
				<a
					href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/kategori?act=delete&url=<%=kategori.getSeoUrl() %>"
					class="btn btn-lg btn-circle marginable pull-right glyphicon glyphicon-remove-sign"
					alt="delete"></a> <a
					href="<%=request.getServletContext().getInitParameter("BASE_URL") %>/kategori?act=edit&url=<%=kategori.getSeoUrl() %>">
					<h3><%=kategori.getNama() %>
						<small class="glyphicon glyphicon-pencil"></small>
					</h3>
				</a>
				<div class="small text-muted border-bottom marginable-bottom">
					<span class="glyphicon glyphicon-link"></span>
					<%=kategori.getSeoUrl() %>
				</div>
				<%=kategori.getKet() %>
			</div>
		</div>
		<%
		}
		%>
	</div>
</div>

<!-- Bootstrap core JavaScript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/jquery-2.1.1.min.js"></script>
<script src="<%=request.getServletContext().getInitParameter("BASE_URL") %>/js/bootstrap.min.js"></script>


<!-- Footer -->
<%@include file="footer.jsp"%>

