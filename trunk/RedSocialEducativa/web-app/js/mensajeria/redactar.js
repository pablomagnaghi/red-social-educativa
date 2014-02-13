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
		placeholder: "Para",
		multiple: true,
		minimumInputLength : 2,
		ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
			url: "traerUsuariosFormateados",
			dataType: 'json',
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
		 allowClear: true,
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
}

function formatResult(movie) {
    return '<div>' + movie.value + '</div>';
};

function formatSelection(data) {
    return data.value;
};

function prepararArbol(){
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
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
	if ($("#para").is(':empty') || $("#asunto").is(':empty') || $("#cuerpo").is(':empty')){
		return false;
	} else {
		return true;
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
