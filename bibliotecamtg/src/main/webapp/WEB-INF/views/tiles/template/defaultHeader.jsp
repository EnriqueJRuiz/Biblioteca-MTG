<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="row">
<nav class="navbar navbar-custom" role="navigator" >
		    <div class="navbar-header">
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      			<span class="sr-only">Desplegar navegación</span>
			     <span class="icon-bar"></span>
			     <span class="icon-bar"></span>
			     <span class="icon-bar"></span>
			  </button>
		      <a class="navbar-brand" href="/bibliotecamtg"><spring:message code="aplicacion.nombre" /></a>
		    </div>
		    <div class="collapse navbar-collapse navbar-ex1-collapse">
    			<ul class="nav navbar-nav">
			      <li><a href="<c:url value='/cartas'/>"><spring:message code="link.carta" /></a></li>
			      <li><a href="<c:url value='/ampliaciones'/>"><spring:message code="link.ampliacion" /></a></li>
			       <li class="dropdown">
			       		<a class="dropdown-toggle" data-toggle="dropdown"  href="#"><span class="glyphicon glyphicon-cog"></span> <spring:message code="link.ajustes" /> <span class="caret"></span></a>
			       		<ul class="dropdown-menu">
				            <li><a href="<c:url value='/colores'/>"><spring:message code="link.color" /></a></li>
				            <li><a href="<c:url value='swagger-ui.html'/>">Swagger UI</a>
				        </ul>
			       </li>
			    </ul>
			    <ul class="nav navbar-nav navbar-right  btn-group">
			    	<li>
				 		<sec:authorize access="isAnonymous()">
			                <form method="POST" action="<c:url value='/login'/>" role="form"  class="navbar-form navbar-right">
			                    <div class="input-group">
									<span class="input-group-addon label-info"><i class="glyphicon glyphicon-user"></i></span> 
									<input name="userId" type="text" value="${SPRING_SECURITY_LAST_USERNAME}"/> 
			                   </div>
			                    <div class="input-group">
									<span class="input-group-addon label-info"><i class="glyphicon glyphicon-lock "></i></span>
									<input name="password" type="password"/>
			                   </div>					                    
			                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			              		<div class="form-group ">
			                    	<input type="submit" value="Login" class="btn btn-info"/>
			                    </div>
			                </form>
			            </sec:authorize>
			            <sec:authorize access="isAuthenticated()">
			            	<div class="form-group navbar-form navbar-right">
			                	<a href="<c:url value="/logout" />" class="btn btn-info" type="button" >Logout</a>
			            	</div>
			            </sec:authorize>
				 	</li>
					<li class="btn-group">
						<a class="nav-link btn btn-link" href="?locale=es" role="button">	
							<img id="Spain" class="link" src="<c:url value='/resources/images/icon-flag/Spain.png'/>"/> 
 							<!-- <spring:message code="idioma.castellano" /> -->
 						</a>
 						<a class="nav-link btn btn-link" href="?locale=en" role="button">
 							<img id="United_Kingdom" class="link" src="<c:url value='/resources/images/icon-flag/United_Kingdom.png'/>"/> 
 							<!--<spring:message code="idioma.ingles" /> -->
 						</a>
 					</li>
 				</ul>
		    </div>
		</nav>
	 </div>
		