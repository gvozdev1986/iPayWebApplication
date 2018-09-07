<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.locale}">
	<c:set var="locale" value="en" scope="session"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>iPay</title>
</head>
<body>
	<jsp:forward page="/ServletController">
		<jsp:param name="command" value="greeting_page_view" />
	</jsp:forward>
</body>
</html>