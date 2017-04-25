<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
			<section class="row">
				<div class="col-xs-12  col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-10 col-lg-offset-1 page-header text-center"><h3>${carta.nombre}</h3></header>
					</div>
					<div class="row">
						<div class="panel-body">
							<div class="col-xs-12 col-lg-5">
								<img src="<c:url value='/resources/images/cards/${carta.imagen}'/>">
							</div>
							<div class="col-xs-12 col-lg-7">	
								<div class="text-justify">
									<label class="col-xs-12 form-label"><spring:message code="form.texto" />: 
									<em><small>${carta.texto}</small></em></label>	
								</div>
								<div class="text-justify">
									<label class="col-xs-12 form-label"><spring:message code="form.rareza" />: 
									<em><small>${carta.rareza}</small></em></label>
								</div>
								<div class="">
									<label class="col-xs-12 form-label"><spring:message code="form.coste" />: 
									<em><small>${carta.costeDeMana}</small></em></label>
								</div>
								<div class="">
									<label class="col-xs-12 form-label"><spring:message code="form.tipo" />: 
									<em><small>${carta.tipo}</small></em></label>
								</div>
								<div class="">
									<label class="col-xs-12 form-label"><spring:message code="form.numero" />: 
									<em><small>${carta.numero}</small></em></label>
								</div>
								<div class="">
									<label class="col-xs-12 form-label"><spring:message code="form.ampliacion" />: 
									<em><small>${carta.ampliacion.nombre}</small></em></label>
								</div>
								<div class="">
									<label class="col-xs-12 "><spring:message code="form.color" />: 
									<c:forEach var="color" items="${carta.colores}">
					 					<img src="<c:url value='/resources/images/colors/${color.icono}'/>" class="img-colorCarta" />
				 					</c:forEach>
				 					</label>
					 			</div>
							</div>
						</div>
					</div>
					<div class="row">
    					<a href="<c:url value='/cartas'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> volver </a>
					</div>
    			</div>
			</section>