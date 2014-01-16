$(document).ready(
		function(){
			when_ready();
		}
);

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
				idConversacion = ui.draggable.attr('id')
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
}

function cerrar_form_nueva_carpeta(){
	$('#div_nueva_carpeta').hide();
	$('#nombre').val('');
	$("#div_nueva_carpeta").removeClass('center_div');
	when_ready();
}

function abrir_form_nueva_carpeta(){
	$("#div_nueva_carpeta").addClass('center_div');
	$("#div_nueva_carpeta").show();
}