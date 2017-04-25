/**
 * 
 */

$(document).ready(function() {
	//extrae la id de la clase borrarCarta y lo manda al Rest respectivo para 
	//borrar el registro de la tabla
	
	/*$('.borrarCarta').click(function(event) {
		var id_carta = this.id;
		const url="http://localhost:8080/bibliotecamtg/api/cartas/";
		$.ajax({
		 url : url+id_carta,
		 type: "DELETE",
		 cache: false,
		 dataType : "json",
		}).done(function() {
			location.reload();
        });
		
	});
	//////////////////////////////////////////
	$('.borrarAmpliacion').click(function(event) {
		var id_ampliacion = this.id;
		
		const url="http://localhost:8080/bibliotecamtg/api/ampliacipones/";
		$.ajax({
		 url : url+id_ampliacion,
		 type: "DELETE",
		 cache: false,
		 dataType : "json",
		}).done(function() {
			location.reload();
        });
	});
	//////////////////////////////////////////
	$('.borrarColor').click(function(event) {
		var id_color = this.id;
		const url="http://localhost:8080/bibliotecamtg/api/colores/";
		$.ajax({
		 url : url+id_color,
		 type: "DELETE",
		 cache: false,
		 dataType : "json",
		}).done(function() {
			location.reload();
        });
	});
	*/
////////////////////////////////////////////////////////////////////////////////////////	
///////////////////
/// FILE BUTTON ///	
///////////////////	
	$('#fichero').on('change',function(event){
		var nombre = this.value;
		$.ajax({    
        }).done(function(data) {
        	$("#icono").val(nombre);   
        });
	});
///////////////////////	
	$('#imgcarta').on('change',function(event){
		var nombre = this.value;
		$.ajax({    
        }).done(function(data) {
        	$("#imagen").val(nombre);   
        });
	});
///////////////////////
	$('#imgampliacion').on('change',function(event){
		var nombre = this.value;
		$.ajax({    
        }).done(function(data) {
        	$("#imagen").val(nombre);   
        });
	});
////////////////////////////////////////////////////////////////////////////////////////	

////////////////////////////////////////////////////////////////////////////////////////
	$('.ampliVerNombre').click(function(event) {
		
		alert("hola");
		
	});
////////////////////////////////////////////////////////////////////////////
});



	