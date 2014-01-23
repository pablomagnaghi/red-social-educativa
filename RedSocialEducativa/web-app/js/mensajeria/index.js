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
	$("#para, #de").autocomplete({
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