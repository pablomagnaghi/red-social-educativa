function desplegarMensaje(id){
	if ($("#cuerpoMensaje-" + id).is(':visible')){
		$("#cuerpoMensaje-" + id).hide('slow')	
	} else {
		$("#cuerpoMensaje-" + id).show('slow')
	}
}

function responder(para, id){
	$("#respuesta-para-" + id).val(para);
	var asunto = "Re: " + $("#asunto-" + id).val();
	$("#respuesta-asunto-" + id).val(asunto);
	$("#botones-respuesta-" + id).hide();
	$("#respuestaMensaje-" + id).show('slow');
}

function reenviarMensaje(id){
	var asunto = "Fw: " + $("#asunto-" + id).val();
	$("#respuesta-asunto-" + id).val(asunto);
	var cuerpo = $("#cuerpo-" + id).val();
	$("#respuesta-cuerpo-" + id).val(cuerpo)
	$("#botones-respuesta-" + id).hide();
	$("#respuestaMensaje-" + id).show('slow');
}

function mensajeEnviado(id){
	$("#respuestaMensaje-" + id).hide('slow');
	$("#respuesta-para-" + id).val('');
	$("#respuesta-asunto-" + id).val('')
	$("#respuesta-cuerpo-" + id).val('')
	$("#botones-respuesta-" + id).show();
}

function responderMensaje(id){
	var para = $("#de-" + id).val();
	responder(para, id);
}

function responderATodos(id){
	var para = $("#para-" + id).val();
	var de = $("#de-" + id).val();
	para += de;
	responder(para, id)
}

function cancelarRespuesta(id){	
	$("#respuestaMensaje-" + id).hide('slow');
	$("#respuesta-para-" + id).val('');
	$("#respuesta-asunto-" + id).val('')
	$("#respuesta-cuerpo-" + id).val('')
	$("#botones-respuesta-" + id).show();
}