<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<section>
	<header><h2>La expansion solicitada no exite en BBDD</h2></header>
	<div>
		<p>URL = ${url}</p>
		<p>Exception = ${exception.message}</p>
		<c:forEach items = "${exception.stackTrace}" var="st">
			${st}
		</c:forEach>
	</div>
</section>