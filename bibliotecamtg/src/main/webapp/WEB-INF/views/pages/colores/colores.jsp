<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">					
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 page-header text-center"><h3><spring:message code="titulo.colores" /></h3></header>
					</div>
					<div class="panel-body">
						<a href="<c:url value='colores/addColor'/>" class="btn btn-success "><span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="btn.color" /></a>
					</div>
					 <div class="panel panel-info">
					 	<div class="panel-heading">
							<div class="row">
		           				<div class="col-xs-4"><spring:message code="list.nombre" /></div>
								<div class="col-xs-4 text-center"><spring:message code="list.icono" /></div>
								<div class="col-xs-4"></div>
							</div>
						</div>
						<div class="panel-body">
							<c:choose>
							 	<c:when test="${not empty listadocolores}">	<!-- cartaController -->
							 		<c:forEach var="color" items="${listadocolores}">
							 			<div class="row">
								 			<div class="col-xs-4">${color.nombre}</div>
								 			<div class="col-xs-4 text-center"><img class="img-colores" src="<c:url value='/resources/images/colors/${color.icono}'/>"></div>
								 			<div class="col-xs-4  btn-group  pull-right">
								 					<a href="<c:url value='colores/${color.codigo}'/>"  class="btn btn-warning " role="button"><span class="glyphicon glyphicon-pencil"></span></a>
								 					<a href="<c:url value='colores/deleteColor/${color.codigo}'/>"id="${color.codigo}" class="btn btn-danger  borrarColor" role="button"><span class="glyphicon glyphicon-trash"></span></a>
								 			</div>
								 			<div class="col-xs-12 separador"></div>
								 		</div>	
							 		</c:forEach>	
							 	</c:when>
							 	<c:otherwise>
							 		<div class="row">No se han encontrado ampliaciones en la Base de Datos</div>
							 	</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</section>	    