<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 text-center page-header"><h3>${men} <spring:message code="btn.ampliacion" /></h3></header>
					</div>
					<form:form action="save" nethod="post" commandName="ampliacion" cssClass="form-horizontal" enctype="multipart/form-data">
						<c:if test="${not empty ampliacion}">
							<form:hidden path="codigo"/>
						</c:if>
						
						<div class="form-group">
						<form:label path="nombre" for="nombre" cssClass="col-xs-3 form-label"><spring:message code="form.nombre" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="nombre" cssErrorClass="form-control alert-danger" cssClass="form-control"  />
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="nombre" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						<div class="form-group">
						<form:label path="siglas" for="siglas" cssClass="col-xs-3 form-label"><spring:message code="form.siglas" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="siglas" cssErrorClass="form-control alert-danger" cssClass="form-control"  />
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="siglas" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						<div class="form-group">
							<form:label path="principal" for="principal" cssClass="col-xs-3 form-label"><spring:message code="form.bloque" />:</form:label>
							<div class="col-xs-9">
								<form:select path="principal" cssErrorClass="form-control alert-danger" cssClass="form-control">
									<form:option value="0" >--Principal--</form:option>
									<form:options items="${listadoampliaciones}" itemValue="codigo" itemLabel="nombre" />
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="principal" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						
						<div class="form-group">
						<form:label path="fLanzamiento" for="fLanzamiento" cssClass="col-xs-3 form-label"><spring:message code="form.fecha" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="fLanzamiento" cssErrorClass="form-control alert-danger" cssClass="form-control"  />
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="fLanzamiento" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						<div class="form-group ">
		            		<form:label path="imagen"  cssClass="col-xs-3 form-label">Imagen:</form:label>
			            		
			            		<div class="col-xs-5">
			            			<c:set var="string" value="${color.icono}" />
			   						<c:set var="names" value="${fn:split(string, '/')}" />
			   						<c:set var="len" value="${fn:length(numList)}"/>
			   						<c:set var="value" value="${names[len-1]}" />
			   						${value}
									<form:input value="${value}" path="imagen" id="imagen" disabled="disabled" cssClass="form-control" cssErrorClass="text-danger" readonly="true" />
								</div>
								 <label class="btn btn-primary  col-xs-3">
			                		Examinar&hellip; <input type="file" id="imgampliacion" name="imgampliacion" style="display: none;" accept="image/*">
			            		</label>
		            	</div>	
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="imagen" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						<!-- <div class="form-group">
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
						</div>	-->
						
						<div class="form-group">
							<form:label path="especial" for="especial" cssClass="col-xs-3 form-label"><spring:message code="form.especial" />:</form:label>
							<div class=" col-xs-9">
								<form:radiobutton path="especial" value="1"/>SI
								<form:radiobutton path="especial" value="0"/>NO
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="especial" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						<div class="form-group">
							<form:label path="basica" for="basica" cssClass="col-xs-3 form-label"><spring:message code="form.basica" />:</form:label>
							<div class=" col-xs-9">
								<form:radiobutton path="basica" value="1"/>SI 
								<form:radiobutton path="basica" value="0"/>NO
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="basica" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						<spring:message var="menssg" code="form.crear" />
						<c:if test="${ampliacion.codigo > 0}" >
							<spring:message var="menssg" code="form.editar" />
						</c:if> 
						<a href="<c:url value='/ampliaciones'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> volver</a>
						<button type="submit"  class="btn btn-success" ><span class="glyphicon glyphicon-ok"></span> ${menssg}</button>
					</form:form>
				</div>
			</section>
		