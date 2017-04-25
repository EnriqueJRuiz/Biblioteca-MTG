<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><!--  -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%><!--  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 

			<section class="row">
				<div class="col-xs-12  col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3 jumbotron">
					<div class="row">
						<header class="col-xs-12  col-md-10 col-md-offset-1  col-lg-6 col-lg-offset-3 text-center page-header"><h3>${men} <spring:message code="btn.carta" /></h3></header>
					</div> 
					<form:form action="save" nethod="post" commandName="carta" cssClass="form-horizontal" enctype="multipart/form-data">
						<c:if test="${not empty carta}">
							<form:hidden path="codigo"/>
						</c:if>
					
						<div class="form-group">
							<form:label path="nombre" for="nombre" cssClass="col-xs-3 form-label"><spring:message code="form.nombre" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="nombre" cssErrorClass="" cssClass="form-control"  />
							</div>
						</div>
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="nombre" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>	
						
						<div class="form-group">
							<form:label path="texto" for="texto" cssClass="col-xs-3 form-label "><spring:message code="form.texto" />:</form:label>
							<div class=" col-xs-9">
								<form:textarea path="texto" cssErrorClass="" cssClass="form-control" />
							</div>	
							<form:errors path="texto" cssClass="badge badge-danger" />
						</div>
						
						<div class="form-group">
							<form:label path="rareza" for="rareza" cssClass="col-xs-3 form-label"><spring:message code="form.rareza" />:</form:label>
							<div class=" col-xs-9">
								<form:select path="rareza" cssErrorClass="" cssClass="form-control" >
									<form:option value="s/n" label="-- Seleccione rareza --"/>
									<form:option value="mitica" label="Mítica"/>
									<form:option value="rara" label="Rara" />
									<form:option value="infrecuente" label="Infrecuente" />
									<form:option value="comun" label="comun" />
								</form:select>
							</div> 
							<form:errors path="rareza" cssClass="badge badge-danger" />
						</div>
						
						<div class="form-group">
							<form:label path="costeDeMana" for="costeDeMana" cssClass="col-xs-3 form-label"><spring:message code="form.coste" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="costeDeMana" cssErrorClass="" cssClass="form-control" />
							</div>
							<form:errors path="costeDeMana" cssClass="badge badge-danger" />
						</div>
						
						<!--  <div class="form-group">
							<form:label path="supertipo" for="supertipo" cssClass="col-xs-3 form-label"><spring:message code="form.supertipo" />:</form:label>
							<div class=" col-xs-9">
								<form:input path="supertipo" cssErrorClass="form-control alert-danger" cssClass="form-control" />
							</div>
							<form:errors path="supertipo" cssClass="badge badge-danger"  />
						</div>-->
						
						<div class="form-group">
							<form:label path="tipo" for="fNacimiento" cssClass="col-xs-3 form-label"><spring:message code="form.tipo" />:</form:label>
							<div class="col-xs-9">
								<form:select path="tipo" cssErrorClass="" cssClass="form-control" >
									<form:option value="s/n" label="-- Seleccione tipo --"/>
									<form:option value="conjuro" label="Conjuro"/>
									<form:option value="instanteneo" label="Instantaneo" />
									<form:option value="criatura" label="Criatura" />
									<form:option value="artefacto" label="Artefacto" />
									<form:option value="encantamiento" label="Encantamiento" />
									<form:option value="tierra" label="Tierra" />
									<form:option value="planeswalkers" label="Planeswalkers "/>
								</form:select>
							</div>
							<form:errors path="tipo"  cssClass="badge badge-pill badge-danger" />
						</div>
						
						<!--  <div class="form-group">
							<form:label path="subtipo" for="subtipo" cssClass="col-xs-3 form-label"><spring:message code="form.subtipo" />:</form:label>
							<div class="col-xs-9">
								<form:input path="subtipo" cssErrorClass="form-control alert-danger" cssClass="form-control" />
							</div>
							<form:errors path="subtipo" cssClass="badge  badge-danger" />
						</div>-->
						
						<div class="form-group">
							<form:label path="numero" for="numero" cssClass="col-xs-3 form-label"><spring:message code="form.numero" />:</form:label>
							<div class="col-xs-9">
								<form:input path="numero" cssErrorClass="" cssClass="form-control" />
							</div>
							<form:errors path="numero" cssClass="badge  badge-danger" />
						</div>
						
						<div class="form-group">
							<form:label path="ampliacion" for="ampliacion" cssClass="col-xs-3 form-label"><spring:message code="form.ampliacion" />:</form:label>
							<div class="col-xs-9">
								<form:select path="ampliacion" cssErrorClass="" cssClass="form-control">
									<form:options items="${listadoampliaciones}" itemValue="codigo" itemLabel="nombre" />
								</form:select>
							</div>
							<form:errors path="ampliacion" cssClass="badge  badge-danger" />
						</div>
						

						<div class="form-group">
							<form:label path="colores" for="colores" cssClass="col-xs-3 form-label"><spring:message code="form.color" />:</form:label>
							<div class="col-xs-9">
								<form:checkboxes path="colores" items="${listadocolores}"  itemValue="codigo"   itemLabel="nombre"/>
							
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
			                		Examinar&hellip; <input type="file" id="imgcarta" name="imgcarta" style="display: none;" accept="image/*">
			            		</label>
			            		
		            	</div>	
		          
						<div class="form-group">
							<div class=" col-xs-9 col-lg-offset-3">
								 <form:errors path="imagen" cssClass="col-xs-12 alert alert-danger well-xs" />
							</div>
						</div>		
						
						<spring:message var="menssg" code="form.crear" />
						<c:if test="${carta.codigo > 0}" >
							<spring:message var="menssg"  code="form.editar" />
						</c:if> 
						<a href="<c:url value='/cartas'/>" class="btn btn-danger" ><span class="glyphicon glyphicon-arrow-left"></span> <spring:message code="form.volver" /></a>
						<button type="submit"  class="btn btn-success" ><span class="glyphicon glyphicon-ok"></span> ${menssg}</button>
					</form:form>
				</div>
			</section>		
			
		
		