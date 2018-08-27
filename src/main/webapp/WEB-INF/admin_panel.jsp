<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:include page="admin_navbar.jsp" />

</style>
<div class="container-fluid">
	<div class="row">
		<jsp:include page="admin_menu.jsp" />
		<!-- Меню для админа -->
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"> <jsp:include
			page="welcome_page.jsp" /> <!-- Приветственная страница  --> </main>
	</div>
</div>
<ctg:footer />