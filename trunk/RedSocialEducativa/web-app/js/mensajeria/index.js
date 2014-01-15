$(document).ready(function(){
	$('#nueva_carpeta').click(function(){
		abrir_form_nueva_carpeta();
	});
});

function cerrar_form_nueva_carpeta(){
	$('#div_nueva_carpeta').hide();
	$('#nombre').val('');
	$("#div_nueva_carpeta").removeClass('center_div');
	$('#nueva_carpeta').click(function(){
		abrir_form_nueva_carpeta();
	});
}

function abrir_form_nueva_carpeta(){
	$("#div_nueva_carpeta").addClass('center_div');
	$("#div_nueva_carpeta").show();
}