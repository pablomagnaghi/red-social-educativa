var sendArr = [];

function split( val ) {
	return val.split( /,\s*/ );
}

function extractLast( term ) {
	return split( term ).pop();
}

function redactar_ready(){
	$('#img_clickeable').click(function(){
		$("#organigrama").show();
	});
	$('#cerrarOrganigrama').click(function(){
		$("#organigrama").hide();
	});
	$('.link-aprendiz').each(function(){
		$(this).click(function(){
			id = $(this).attr('id');
			open = $(this).hasClass('open')
			if (open == true){
				$("#" + id).attr('src', '../images/tree_plus.gif');
				$("#aprendiz-"+id).hide();
				$(this).removeClass('open')
			} else {
				if ($("#aprendiz-"+id).is(':empty')){
					traerDatosCurso(id);
				} else {
					$("#aprendiz-"+id).show();
				}	
				$(this).addClass('open')
			}
		});
	});
	$('.seleccionableOrg').each(function(){
		$(this).click(function(){
			id = $(this).attr('id');
			texto = $(this).html();
			agregarTipo(id, texto)
		})
	});
	$("#e6").select2({
		multiple: true,
		allowClear: true,
		minimumInputLength : 2,
		ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
			url: "traerUsuariosFormateados",
			data: function (request, page) {
				return {
					term: extractLast( request ), 
				};
			},
			results: function (data, page) { 
				return {results: data};
			}
		},
		 formatResult: formatResult, 
		 formatSelection: formatSelection, 
		 formatSearching : function(){
			 return "Buscando..."
		 },
		 formatNoMatches : function(){
			 return "No hay coincidencias"	
		 },
		 formatInputTooShort : function(term, minLength){
			 var faltantes = (minLength - term.length)
			 var stringLetra = "letras"
			 if (faltantes == 1){
				 stringLetra = "letra"
			 }
			 return "Ingrese "  + faltantes + " " + stringLetra + " más"
		 }
		 
	});
	prepararArbol()
	var input = $('#s2id_autogen1');
	input.on('keydown', function(event) {
		var key = event.keyCode || event.charCode;

		if( key == 8 || key == 46 ){
			if ($(this).val()==''){
				removeLastLi('select2-choices')
			}
		}
	});
}

function formatResult(data) {
    return '<div>' + data.value + '</div>';
};

function formatSelection(data) {
    return data.value;
};

function prepararArbol(){
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Abierto');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Cerrado').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Abierto').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
}

function agregarTipo(id, texto){
	tipo = null;
	idTipo = null;
	var regexp = /([^-]+)-([\d]+)/g;
	var matcher = regexp.exec(id);
	if (matcher != null){
		tipo = matcher[1]; 
		idTipo = matcher[2]
	}
	if (tipo == 'curso'){
		agregarCurso(idTipo, texto)
	} else {
		agregarMediador(idTipo, texto)
	}
}

function agregarCurso(id, texto){
	textoAnterior = $("#para").val();
	$("#para").val(textoAnterior + " Curso: " + texto + ",");
}

function agregarMediador(id){
	textoAnterior = $("#para").val();
	$("#para").val(textoAnterior + " Mediador: " + texto+ ",");
}

function submitMail(){
	$("#cuerpo").val($("#divCuerpo").html())
	console.log($("#cuerpo").html())
	
	var para = $.trim($("#e6").val()).length;
	var asunto = $.trim($("#asunto").val()).length;
	var cuerpo = $.trim($("#cuerpo").val()).length;
	var data = $("#e6").val()
	data += ","
	$("#e6").val(data)
	if (para > 0 || asunto > 0 || cuerpo > 0){
		var item = sendArr.pop()
		while (item != null){
			var val = $("#e6").val()
			val += item
			$("#e6").val(val)
			item = sendArr.pop()
		}
		return true;
	} else {
		return false;
	}
}

function agregarMensajeABorradores(){
	$("#cuerpo").val($("#divCuerpo").html())
	$.ajax({
		url: 'agregarMensajeABorradores',
		type: 'POST',
		data: {
			para: $("#para").val(),
			asunto : $("#asunto").val(),
			cuerpo : $("#cuerpo").val()
		},
		success: function(reply){
			window.open('index', '_self')
		}
	})
}

function traerDatosCurso(id){
	$("#spinner").show();
	$.ajax({
		url: 'traerDatosCurso',
		type: 'POST',
		data: {
			idCurso: id
		},
		success: function(reply){
			$("#aprendiz-" + id).html(reply);
			$("#spinner").hide();
			$("#" + id).attr('src', '../images/tree_minus.gif');
		}
	})
}

function removeFromSendArr(id){
	var index = sendArr.indexOf(id);
	if (index > -1) {
		sendArr.splice(index, 1);
	}
}

function agregarCurso(encabezado, nombreCurso, idCurso){
	if ($("#curso"+idCurso).length==0 && $('#'+encabezado+idCurso).is(':checked')){
		$(".select2-choices").each(function(){
			$(this).prepend("<li class='select2-search-choice generado' id='curso"+idCurso+"'>" +
					"<div>Curso: "+nombreCurso+"</div>    " +
					"<a tabindex='-1' class='select2-search-choice-close removeLink' onclick='removeCursoLi(\""+idCurso+"\")' href='#'></a></li>")
		});
		sendArr.push("Curso-" + idCurso + ",")
	} else {
		$("#curso"+ idCurso).remove()
		removeFromSendArr("Curso-" + idCurso + ",")
	}
}

function agregarGrupo(encabezado, idGrupo, nombreGrupo, nombreCurso, idCurso){
	if ($("#grupo"+idGrupo).length==0 && $('#'+encabezado+idGrupo).is(':checked')){
		$(".select2-choices").each(function(){
			$(this).prepend("<li class='select2-search-choice generado' id='grupo"+idGrupo+"-"+idCurso+"'>" +
					"<div>"+nombreGrupo+", "+ nombreCurso+"</div>    " +
					"<a tabindex='-1' class='select2-search-choice-close removeLink' onclick='removeGrupoLi(\""+idGrupo+"\", \""+idCurso+"\")' href='#'></a></li>")
		});
		sendArr.push("Grupo-" + idGrupo + "_Curso-" + idCurso + ",")
	} else {
		$("#grupo"+ idGrupo +"-"+ idCurso).remove()
		removeFromSendArr("Grupo-" + idGrupo + "_Curso-" + idCurso + ",")
	}
}

function removeCursoLi(idCurso){
	$("#curso"+ idCurso).remove()
	removeFromSendArr("Curso-" + idCurso + ",")
}

function removeMediadorLi(id){
	$("#mediador"+id).remove()
	removeFromSendArr("Mediador-"+id + ",")
}

function removeGrupoLi(idGrupo, idCurso){
	$("#grupo"+ idGrupo +"-"+ idCurso).remove()
	removeFromSendArr("Grupo-" + idGrupo + "_Curso-" + idCurso + ",")
}

function removeLastLi(className){
	$("."+className+" li:last").prev("li").remove()
}

function agregarMediador(chckboxId, mediadorId, mediadorNombres, mediadorApellido, mediadorEmail){
	if ($("#mediador"+mediadorId).length==0 && $('#'+chckboxId+mediadorId).is(':checked')){
		$(".select2-choices").each(function(){
			$(this).prepend("<li class='select2-search-choice generado' id='mediador"+mediadorId+"'>" +
					"<div>"+mediadorNombres+" "+ mediadorApellido+" &lt;"+mediadorEmail+"&gt;</div>    " +
					"<a tabindex='-1' class='select2-search-choice-close removeLink' onclick='removeMediadorLi(\""+mediadorId+"\")' href='#'></a></li>")
		});
		sendArr.push("Mediador-"+mediadorId+",")
	} else {
		$("#mediador"+mediadorId).remove();
		removeFromSendArr("Mediador-"+mediadorId + ",")
	}
}

function redactarRespuesta(idMensaje, tipoRespuesta, argumento, excepcion){
	redactar_ready()
	if (tipoRespuesta == 'respuesta' || tipoRespuesta == 'respuestaTodos'){
		$("input.para_" + idMensaje).each(function(){
			var paraArray = split( argumento );
			$.each(paraArray, function(index, value){
				if (value != ""){
					if (excepcion == null || (!(value.indexOf(excepcion) >= 0))){
						agregarCampoAPara(value)
					}
				}
			})
		})
	} else {
		$('.cuerpo_' + idMensaje).each(function(){
			$(this).html(argumento)
		})
	}
	$('#form_reply_' + idMensaje).show()
	$('.removeLink').click(function(){
		$(this).closest('li').remove()
	})
}

function agregarCampoAPara(value){
	
	$(".select2-choices").each(function(){
		$(this).prepend("<li class='select2-search-choice generado'>" +
				"<div>"+htmlEncode(value)+"</div>    " +
				"<a tabindex='-1' class='select2-search-choice-close removeLink' href='#'  onclick='return false;' ></a></li>")
	});
}

function htmlEncode(value){
	return $('<div/>').text(value).html();
}

