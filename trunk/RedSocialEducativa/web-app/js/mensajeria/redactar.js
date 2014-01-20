$(document).ready(
		function(){
			when_ready()
		}
);

function split( val ) {
	return val.split( /,\s*/ );
}

function extractLast( term ) {
	return split( term ).pop();
}

function when_ready(){
	$('#img_clickeable').click(function(){
		$("#organigrama").addClass('center_div');
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
	$("#para").autocomplete({
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

