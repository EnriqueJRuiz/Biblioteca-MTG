<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><!--  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 text-center page-header"><h3>${men} <spring:message code="btn.color" /></h3></header>
					</div>
					<form:form action="save" nethod="post" commandName="color" cssClass="form-horizontal" enctype="multipart/form-data" >
						<c:if test="${not empty color}">
							<form:hidden path="codigo"/>
						</c:if>
					
						<div class="form-group">
							<form:label path="nombre" for="nombre" cssClass="col-xs-2 form-label"><spring:message code="form.nombre" />:</form:label>
							<div class=" col-xs-8">
								<form:input path="nombre" cssErrorClass="form-control" cssClass="form-control"  required="required"/>
							</div>
						</div>
						
						<div class="form-group">
							<div class=" col-xs-8 col-lg-offset-2">
								 <form:errors path="nombre" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>		
							
						<div class="form-group">
		            		<form:label path="icono" cssClass="control-label  col-xs-2">Icono:</form:label>
		            		<div class="col-xs-4">
		            			<c:set var="string" value="${color.icono}" />
		   						<c:set var="names" value="${fn:split(string, '/')}" />
		   						<c:set var="len" value="${fn:length(numList)}"/>
		   						<c:set var="value" value="${names[len-1]}" />
		   						${value}
								<form:input value="${value}" path="icono" id="icono" disabled="disabled" cssClass="form-control" cssErrorClass="text-danger" readonly="true" />
							</div>
							
							 <label class="btn btn-primary  col-xs-3">
		                		Examinar&hellip; <input type="file" id="fichero" name="fichero" style="display: none;" accept="image/*">
		            		</label>	
		            	</div>		
						<div class="form-group">
							<div class=" col-xs-8 col-lg-offset-2">
								 <form:errors path="icono" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>		
						
		
						
						<spring:message var="menssg" code="form.crear" />
						<c:if test="${color.codigo > 0}">
							<spring:message var="menssg"  code="form.editar" />
						</c:if>
						<a href="<c:url value='/colores'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> <spring:message code="form.volver" /></a>
						<button type="submit"  class="btn btn-success" ><span class="glyphicon glyphicon-ok"></span> ${menssg}</button>
					</form:form>
				</div>
			</section>
		