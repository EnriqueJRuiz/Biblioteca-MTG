<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
	
			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-10 col-lg-offset-1 page-header text-center"><h3><spring:message code="titulo.cartas" /></h3></header>
					</div>
					<div class="row">
						<div class="panel-body">
							<a href="<c:url value='cartas/addCarta'/>" class="btn btn-success "><span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="btn.carta" /></a>
						</div>
						<div class="panel panel-info">
	           				<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3"><spring:message code="list.nombre" /></div>
									<div class="col-xs-1"><spring:message code="list.rareza" /></div>
									<div class="col-xs-3"><spring:message code="list.ampliacion" /></div>
									<div class="col-xs-2"><spring:message code="list.colores" /></div>
									<div class="col-xs-3"></div>
								</div>
							</div>
							<div class="panel-body panel-body-success">
								<c:choose>
								 	<c:when test="${not empty listadocartas}">	<!-- cartaController -->
								 		<c:forEach var="carta" items="${listadocartas}">
								 			<div class="row">
									 			<div class="col-xs-3"><a href="<c:url value='cartas/verCarta/${carta.value.codigo}'/>" class="sin-linea">${carta.value.nombre}</a></div>
									 			<div class="col-xs-1">${carta.value.rareza}</div>
									 			<div class="col-xs-3"><a href="<c:url value='ampliaciones/verAmpliacion/${carta.value.ampliacion.codigo}'/>" class="sin-linea">${carta.value.ampliacion.nombre}</a></div>
									 			<div class="col-xs-2">
									 				<c:forEach var="color" items="${carta.value.colores}">
									 						<img src="<c:url value='/resources/images/colors/${color.icono}'/>" class="img-color">
									 				</c:forEach>
									 			</div>
									 			
										 			<div class="col-xs-3 btn-group">
										 				<a href="<c:url value='cartas/verCarta/${carta.value.codigo}'/>"  class="btn btn-primary" role="button"><span class="glyphicon glyphicon-search"></span></a>
										 				<a href="<c:url value='cartas/${carta.value.codigo}'/>"  class="btn btn-warning" role="button"><span class="glyphicon glyphicon-pencil"></span></a>
										 				<a href="<c:url value='cartas/deleteCarta/${carta.value.codigo}'/>" id="${carta.value.codigo}" class="btn btn-danger  borrarCarta" role="button" ><span class="glyphicon glyphicon-trash"></span></a>
										 			</div>
										 		</div>
									 			<div class="col-xs-12 separador"></div>
									 		
								 		</c:forEach>	
								 	</c:when>
								 	<c:otherwise>
								 		<div class="row">No se han encontrado cartas en la Base de Datos</div>
								 	</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</section>	   		