<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container">
			<c:if test="${not empty mensaje}">
				<div class="${mensaje.type.styles}">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		   			<strong>${mensaje.msg}</strong> 
		  		</div>
	  		</c:if>
		</div>