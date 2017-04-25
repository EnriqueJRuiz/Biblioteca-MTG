<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-10 col-lg-offset-1 page-header text-center"><h3><spring:message code="titulo.ampliaciones" /></h3></header>
					</div>
					<div class="panel-body">
						<a href="<c:url value='ampliaciones/addAmpliacion'/>" class="btn btn-success "><span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="btn.ampliacion" /></a>
					</div>
					<div class="panel panel-info">
           				<div class="panel-heading">
           					<div class="row">
								<div class="col-xs-5 col-md-4"><a href="" class="sin-linea ampliVerNombre"><spring:message code="list.nombre" /> <span class="caret"></span></a> </div>
								<div class="col-xs-2"><spring:message code="list.siglas" /> <span class="caret"></span></div>
								<!--  <div class="col-xs-3">Fecha lanzamiento <span class="caret"></span></a></div>-->
								<div class="col-xs-5 col-md-4"><spring:message code="list.bloque" /> <span class="caret"></span></div>
								<div class="col-xs-2"></div>
							</div>
						</div>
						
						<div class="panel-body">
							<c:choose>
							 	<c:when test="${not empty listadoampliaciones}">	<!-- cartaController -->
							 		<c:forEach var="ampliacion" items="${listadoampliaciones}">
							 			<div class="row">
								 			<div class="col-xs-5 col-md-4"><a href="<c:url value='ampliaciones/verAmpliacion/${ampliacion.codigo}'/>" class="sin-linea" >
								 			<c:set var="imagen" value="${ampliacion.imagen}" scope="request" />
								 			
								 			<c:choose>
								 			<c:when test="${not empty imagen}">
								 				<img src="<c:url value='/resources/images/expansion/logo/${ampliacion.imagen}'/>" class="img-ampli-list">
								 			</c:when>
								 			<c:otherwise>
							 					${ampliacion.nombre}
							 				</c:otherwise>
							 				</c:choose>
							 				
							 				</a></div>
								 			<div class="col-xs-2 col-md-2">${ampliacion.siglas}</div>
								 			<!--  <div class="col-xs-3"><fmt:formatDate pattern="dd/MM/yyyy" value="${ampliacion.fLanzamiento}" /></div>-->
								 			<div class="col-xs-5 col-md-4">${ampliacion.principal.nombre}</div>
								 			<div class="col-xs-6 col-sm-6  col-md-2 btn-group text-right">
								 					<a href="<c:url value='ampliaciones/verAmpliacion/${ampliacion.codigo}'/>"  class="btn btn-primary " role="button"><span class="glyphicon glyphicon-search"></span></a>
								 					<a href="<c:url value='ampliaciones/${ampliacion.codigo}'/>"  class="btn btn-warning " role="button"><span class="glyphicon glyphicon-pencil"></span></a>
								 					<a href="<c:url value='ampliaciones/deleteAmpliacion/${ampliacion.codigo}'/>" id="${ampliacion.codigo}" class="btn btn-danger  borrarAmpliacion" role="button" ><span class="glyphicon glyphicon-trash"></span></a>
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