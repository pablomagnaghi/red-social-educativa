$(document).ready(
		function(){
			when_ready()
		}
);

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
			$.ajax({
				url: "traerUsuariosFormateados", // remote datasource
				data: request,
				success: function(data){
					response($.map(data, function (item) {
                        return {
                            label: item.nombres + item.apellido,
                            value: item.nombres + item.apellido + "<" +item.email+">,"
                        }
					}));
				}
			});
		},
		minLength: 2, // triggered only after minimum 2 characters have been entered.
		select: function(event, ui) { // event handler when user selects a company from the list.
			//$("#company\\.id").val(ui.item.id); // update the hidden field.
			$("#para").val(ui.item.nombres+ "-" + ui.item.apellido) // populate the employee field with the nasdaq symbol.
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
	if (textoAnterior != "" ){
		textoAnterior += ", "
	}
	$("#para").val(textoAnterior + " Curso: " + texto);
}

function agregarMediador(id){
	textoAnterior = $("#para").val();
	if (textoAnterior != "" ){
		textoAnterior += ", "
	}
	$("#para").val(textoAnterior + " Mediador: " + texto);
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

