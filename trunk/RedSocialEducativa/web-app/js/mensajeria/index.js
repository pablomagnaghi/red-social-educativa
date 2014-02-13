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
			 start: function() {
			 }
		});
	});
	$( ".droppable" ).each(function(){
		$(this).droppable({
			accept: ".draggable",
			hoverClass: "ui-state-active",
			drop: function( event, ui ) {
				idConversacion = ui.draggable.attr('conversationId')
				idCarpeta = $(this).attr('id')
				$.ajax({
					url: "cambiarConversacion",
					type: "POST",
					data: { conversacion : idConversacion,
							carpeta : idCarpeta},
					success : function (reply){
						$("#panel_mensajes").html(reply)
						when_ready();
					}
				});
			}
		})
	});
	$("#paraBuscar, #deBuscar, #para").autocomplete({
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
			terms.pop();
			// add the selected item
			terms.push( ui.item.value);
			// add placeholder to get the comma-and-space at the end
			terms.push( "" );
			this.value = terms.join( ", " );
			return false;
		}
	});
	redactar_ready()
}

function buscarMensajes(){
	$.ajax({
		url: 'buscar_mensajes',
		type: 'POST',
		data : {
			de : $("#de").val(),
			para : $("#para").val()
		},
		success : function (response) {
			$("#listaConversaciones").html(response)
		}
	})
}

function mostrarConversacion(id){
	$.ajax({
		url: 'conversacion',
		type: 'POST',
		data: {
			id: id
		},
		success: function(reply){
			$("#contenidoMensajes").html(reply);
		}
	})
}

function mostrarMensajeEnConversacion(id){
	if ($("#conversacion-"+id).is(':empty')){
		$.ajax({
			url: 'mensaje',
			type: 'POST',
			data: {
				id: id
			},
			success: function(reply){
				$("#conversacion-"+id).html(reply);
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
			id: id
		},
		success: function(reply){
			$("#contenidoMensajes").html(reply);
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