<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-10 col-lg-offset-1 page-header text-center">
							<c:choose>
					 			<c:when test="${not empty ampliacion.imagen}">
					 				<img src="<c:url value='/resources/images/expansion/logo/${ampliacion.imagen}'/>" class="img-ampliacion">
					 			</c:when>
					 			<c:otherwise>
				 					<h2>${ampliacion.nombre}</h2>
				 				</c:otherwise>
				 			</c:choose>
						</header>
					</div>
			      	<div class="panel">
			      		<div class="row">	
			      			<div class="col-xs-4">Siglas: ${ampliacion.siglas}</div>
				      		<div class="col-xs-4">Fecha: <fmt:formatDate value="${ampliacion.fLanzamiento}" /></div>
				      		<div class="col-xs-4">Bloque: ${ampliacion.principal.nombre}</div>
				      		<!-- <div class="col-xs-3">Icono: ${ampliacion.icono}</div>-->
				      	</div>
			      	</div>
	    			
    			
	    			
						<div class="panel panel-info">
					    	<div class="panel-heading">
					      		<div class="row">
									<div class="col-xs-3">Nombre</div>
									<div class="col-xs-1">Tipo</div>
									<div class="col-xs-1">Rareza</div>
									<div class="col-xs-1">Número</div>
									<div class="col-xs-2">Color</div>
									<div class="col-xs-1">Coste</div>
									<div class="col-xs-2"></div>
								</div>
							</div>
							
							<div class="panel-body panel-body-success">
								<c:choose>
								 	<c:when test="${not empty listadocartas}">	<!-- cartaController -->
								 		<c:forEach var="carta" items="${listadocartas}">
								 			<div class="row">
									 			<div class="col-xs-3">
									 					<a href="<c:url value='/cartas/verCarta/${carta.value.codigo}'/>" class="sin-linea">${carta.value.nombre}</a>
									 				</div>
									 			<div class="col-xs-1">${carta.value.tipo}</div>
									 			<div class="col-xs-1">${carta.value.rareza}</div>
									 			<div class="col-xs-1">${carta.value.numero}</div>
									 			<div class="col-xs-2">
									 				<c:forEach var="color" items="${carta.value.colores}">
									 						<img src="<c:url value='/resources/images/colors/${color.icono}'/>" class="img-color">
									 				</c:forEach>
									 			</div>
									 			<div class="col-xs-1">${carta.value.costeDeMana}</div>
									 			<div class="col-xs-3 btn-group">
									 				<a href="<c:url value='/cartas/verCarta/${carta.value.codigo}'/>"  class="btn btn-primary" role="button"><span class="glyphicon glyphicon-search"></span></a>
									 				<a href="<c:url value='/cartas/${carta.value.codigo}'/>"  class="btn btn-warning" role="button"><span class="glyphicon glyphicon-pencil"></span></a>
									 				<a href="<c:url value='/cartas/deleteCarta/${carta.value.codigo}'/>"  id="${carta.value.codigo}" class="btn btn-danger borrarCarta" role="button" ><span class="glyphicon glyphicon-trash"></span></a>
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
						<a href="<c:url value='/ampliaciones'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> volver</a>
					</div>
			</section>