<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<jsp:include page="/WEB-INF/head.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="Resource"/>
<style>
	.card-header {
		padding: .75rem 1.25rem;
		margin-bottom: 0;
		position: relative;
		background: url("/img/j.jpg") no-repeat center center;
		width: 100%;
		height: 6%;
		background-size: 100% 100%;
		color: #fff;
	}
	.btn-success {
		color: #fff;
		background-color: #00ad7e;
		border-color: #00ad7e;
	}

	.btn-success:hover {
		color: #fff;
		background-color: #41c7a3;
		border-color: #41c7a3;
	}
</style>
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="ServletController?command=greeting_page_view">
			<img src="img/logo.png"
				 width="30"
				 height="30"
				 class="d-inline-block align-top"
				 alt=""> iPay
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>
</header>
<div class="container h-100">
	<div class="row h-100 justify-content-center align-items-center">
		<div class="card" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); width: 40%;">
			<div class="card-header">
				ERROR
			</div>
			<div class="card-body">
				<p>Session has been destroyed</p>
				<div class="card-footer text-muted" style="text-align: right; background-color: #ffffff;">
					<a href="http://localhost/"
					   class="btn btn-success"><i class="fas fa-angle-left"></i> Back</a>
				</div>
			</div>
		</div>
	</div>
</div>
<ctg:footer/>