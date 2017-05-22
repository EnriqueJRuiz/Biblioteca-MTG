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
		
		const url="http://localhost:8080/bibliotecamtg/api/ampliaciones/";
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
////colores//////////////////
////////////////////////////////////////////////////////////////////////////////////////
	const urlColores ="http://localhost:8080/bibliotecamtg/api/colores";
	$.ajax({"method":"get","url": urlColores}
	).then(function(data){
			console.log(data);
			if (data.length > 0) {
                for (var a in data) {
                    console.log(a);
                    var texto = "<tr>" +
                        "<td>" +
                        "<input type='checkbox'  value='" + a.codigo + "'>" +
                        "</td>" +
                        "<td class=''>" +
                            a.nombre +
                        "</td>" +
                        "<td>" +
                            a.icono+
                        "</td>" +
                        "<td>" +
                            
                        "</td>" +
                        "</tr>";
                    $("#tablaAmpliaciones tbody").append(texto);
                }
                $("#tablaAmpliaciones tfoot td").html("<span class='text-error'>Total expansiones:"+data.length,10+"</span>");
            }else{
                $("#tablaAmpliaciones").remove();
                $("#listadoAmpliaciones").text("No se han encontrado alumnos")
            }
	});
////////////////////////////////////////////////////////////////////////////////////////
////////ampliaciones//////////////////
////////////////////////////////////////////////////////////////////////////////////////
	const url="http://localhost:8080/bibliotecamtg/api/ampliaciones";
    $.ajax({"url": url,"method":"get"})
        .then(function(data){
            console.log(data);
            if (data.length > 0) {
                for (var a in data) {
                    console.log(a);
                    var texto = "<tr>" +
                        "<td>" +
                        "<input type='checkbox'  value='" + a.codigo + "'>" +
                        "</td>" +
                        "<td class=''>" +
                            a.nombre +
                        "</td>" +
                        "<td>" +
                            a.siglas+
                        "</td>" +
                        "<td>" +
                            a.principal+
                        "</td>" +
                        "<td>" +

                        "</td>" +
                        "</tr>";
                    $("#tablaAmpliaciones tbody").append(texto);
                }
                $("#tablaAmpliaciones tfoot td").html("<span class='text-error'>Total expansiones:"+data.length,10+"</span>");
            }else{
                $("#tablaAmpliaciones").remove();
                $("#listadoAmpliaciones").text("No se han encontrado alumnos")
            }
        });
////////////////////////////////////////////////////////////////////////////
});



	