var historial = new Array()
var firstItem = { "nombre": 'index', "id": "" };
historial.push(firstItem)

$(document).ready(
		function(){
			when_ready();
		}
);

function extractLast( term ) {
	return split( term ).pop();
}

function split( val ) {
	return val.split( /,\s*/ );
}

function when_ready(){
	$('#nueva_carpeta').click(function(){
		abrir_form_nueva_carpeta();
	});
	$( ".draggable" ).each(function(){
		$(this).draggable({
			 revert: "invalid",
			 helper: "clone",
			 appendTo: 'body',
			 start: function(e, ui) {
				 $(ui.helper).css("opacity",  0.4)
			 }
		});
	});
	$( ".droppable" ).each(function(){
		$(this).droppable({
			accept: ".draggable",
			hoverClass: "ui-state-active",
			tolerance: "pointer",
			drop: function( event, ui ) {
				idConversacion = ui.draggable.attr('conversationId')
				idCarpeta = $(this).attr('id')
				$.ajax({
					url: "cambiarConversacion",
					type: "POST",
					data: { conversacion : idConversacion,
							carpeta : idCarpeta},
					success : function (reply){
						window.open("mostrarMensajes?nombreCarpeta="+idCarpeta, '_self')
					}
				});
			}
		})
	});
	$("#paraBuscar, #deBuscar").autocomplete({
		source: function(request, response){
			if (request.term.match(/,/g)!=null){
				var regexp = /,\s*(.*)/g;
				var matcher = regexp.exec(request.term);
				if (matcher != null){
					request.term = matcher[1]; 
				}
			} 
			$.getJSON( "traerUsuariosFormateados", {
				term: extractLast( request.term )
			}, response );
		},
		minLength: 2, // triggered only after minimum 2 characters have been entered.
		select: function( event, ui ) {
			var terms = split( this.value );
			// remove the current input
			var data = ui.item.value
			var regExMatch = /&lt;/g;
			var replaceWith = "<";
			var resultSet = data.replace(regExMatch, replaceWith);
			regExMatch = /&gt;/g;
			replaceWith = ">";
			var data = resultSet.replace(regExMatch, replaceWith);
			terms.pop();
			// add the selected item
			terms.push(data);
			// add placeholder to get the comma-and-space at the end
			terms.push( "" );
			this.value = terms.join( ", " );
			return false;
		}
	});
	$(".showConv").click(function(){
		id = $(this).closest('li').attr('conversationid')
		mensajeId = $(this).closest('li').attr('mensajeId')
		carpeta = $(this).closest('ul').attr('carpetaSeleccionada')
		mostrarConversacion(id, mensajeId, carpeta)
		actualizar("conversacion", id)
	})
	redactar_ready()
}

function actualizarCarpetas(carpeta){
	$.ajax({
		url: 'mostrarCarpetas',
		type: 'POST',
		data: {
			carpetaSeleccionada : carpeta
		},
		success: function(reply){
			$("#carpetasUsuario").html(reply);
		}
	})
}

function mostrarConversacion(id, mensajeId, carpeta){
	$.ajax({
		url: 'conversacion',
		type: 'POST',
		data: {
			id: id,
			mensajeId : mensajeId
		},
		success: function(reply){
			$("#contenidoMensajes").html(reply);
			actualizarCarpetas(carpeta)
			
		}
	})
}

function mostrarMensajeEnConversacion(id, responder){
	if ($("#conversacion-"+id).is(':empty')){
		$.ajax({
			url: 'mensaje',
			type: 'POST',
			data: {
				id: id,
				responder : responder
			},
			success: function(reply){
				$("#conversacion-"+id).html(reply);
				actualizar('mensajeConversacion', id)
			}
		})
	} else {
		$("#conversacion-"+id).html('')
	}
}

function mostrarMensajeBorradores(id){
	if ($("#conversacion-"+id).is(':empty')){
		$.ajax({
			url: 'mensajeBorradores',
			type: 'POST',
			data: {
				id: id
			},
			success: function(reply){
				$("#conversacion-"+id).html(reply);
				actualizar('mensajeBorrador', id)
			}
		})
	} else {
		$("#conversacion-"+id).html('')
	}
}


function mostrarMensaje(id){
	$.ajax({
		url: 'mensaje',
		type: 'POST',
		data: {
			id: id,
			responder : false
		},
		success: function(reply){
			$("#contenidoMensajes").html(reply);
			actualizar('mensaje', id)
		}
	})
}


function cerrar_form_nueva_carpeta(){
	$('#div_nueva_carpeta').hide();
	$('#nombre').val('');
	when_ready();
}

function abrir_form_nueva_carpeta(){
	$("#div_nueva_carpeta").show();
}

function borrarConversacion(){
	$(".checkBoxConv").each(function(){
		if ($(this).is(':checked')){
			idConversacion = $(this).attr('id')
			$.ajax({
				url: "eliminarConversacion",
				type: "POST",
				data: {
					conversacion : idConversacion,
				}
			});
		}
	})
}

function actualizar(nombre, id){
	var idMap = ''
	if (id != null){
		idMap = id
	}
	var item = { "nombre": nombre, "id": idMap };
	historial.push(item)
}

function volver(){
	var item = historial.pop()
	item = historial.pop()
	if (item == null){
		window.open('index', '_self')
	} else if (item.nombre == 'index'){
		window.open('index', '_self')
	} else if (item.nombre == 'conversacion'){
		mostrarConversacion(item.id)
	} else if (item.nombre == 'carpeta') {
		$.ajax({
			url: "mostrarMensajes",
			type: "POST",
			data : {
				nombreCarpeta: item.id
			},
			success : function (reply){
				$("#inbox-content").html(reply)
				when_ready();
			}
		});
	} else if (item.nombre == 'mensajeConversacion'){
		mostrarMensajeEnConversacion(item.id)
	} else if (item.nombre == 'mensajeBorrador'){
		mostrarMensajeBorradores(item.id)
	} else if (item.nombre == 'mensaje'){
		mostrarMensaje(item.id)
	} else if (item.nombre == 'redactar'){
		$.ajax({
			url: "redactarMensaje",
			type: "POST",
			success : function (reply){
				$("#contenidoMensajes").html(reply)
				when_ready();
			}
		});
	}
}

function validarNombre(){
	if ($("#nombre").val()==""){
		$("#carpetaNuevaError").show()
		return false
	} else {
		$("#carpetaNuevaError").hide()
		return true
	}
}

function responder(id, tipo){
	$.ajax({
		url : 'responder',
		type: 'POST',
		data : {
			id : id,
			tipoRespuesta : tipo
		},
		success : function(reply){
			$("#responder-"+id).html(reply)
			$("#responder-" +id).show()
			when_ready();
		}
	})
}

