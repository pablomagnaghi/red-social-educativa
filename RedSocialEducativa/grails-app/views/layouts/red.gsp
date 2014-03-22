<!DOCTYPE html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="RedSocialEducativaFIUBA"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.jpg')}" type="image/x-icon">
	
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.min.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'style-responsive.min.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'retina.css')}" type="text/css">
	
		<g:layoutHead/>
		<r:layoutResources />
		<!--<g:javascript src="/template/jquery-2.0.2.js"/>-->
		<g:javascript src="/template/jquery-1.10.2.min.js"/>
		<g:javascript src="/template/jquery-migrate-1.2.1.min.js"/>
		<g:javascript src="/template/jquery-ui-1.10.3.custom.min.js"/>
		<!--Arrastar objetos-->
		<g:javascript src="/template/jquery.ui.touch-punch.js"/>
		<g:javascript src="/template/modernizr.js"/>
		<g:javascript src="/template/bootstrap.min.js"/>
		<g:javascript src="/template/jquery.cookie.js"/>
		<!--Calendario en tamaño grande-->
		<g:javascript src='/template/fullcalendar.min.js'/>
		<!--Tablas-->
		<g:javascript src='/template/jquery.dataTables.min.js'></g:javascript>
		<!--Dibujos y animacionez-->
		<g:javascript src="/template/excanvas.js"></g:javascript>
		<!--Graficos -->
		<g:javascript src="/template/jquery.flot.js"></g:javascript>
		<g:javascript src="/template/jquery.flot.pie.js"></g:javascript>
		<g:javascript src="/template/jquery.flot.stack.js"></g:javascript>
		<g:javascript src="/template/jquery.flot.resize.min.js"></g:javascript>
		<g:javascript src="/template/jquery.flot.time.js"></g:javascript>
		<!--Menu para seleccionar opciones-->
		<g:javascript src="/template/jquery.chosen.min.js"/>
		<!--Formularios-->
		<g:javascript src="/template/jquery.uniform.min.js"/>	
		<!--Editor para escribir mensajes-->
		<g:javascript src="/template/jquery.cleditor.min.js"/>
		<!--Botones-->
		<g:javascript src="/template/jquery.noty.js"/>
		<!--Explorador de carpetas-->
		<g:javascript src="/template/jquery.elfinder.min.js"/>
		<!--Estrellas para puntuacion-->
		<g:javascript src="/template/jquery.raty.min.js"/>
		<!--Para telefonos-->
		<g:javascript src="/template/jquery.iphone.toggle.js"/>
		<g:javascript src="/template/jquery.uploadify-3.1.min.js"/>
		<g:javascript src="/template/jquery.gritter.min.js"/>
		<g:javascript src="/template/jquery.imagesloaded.js"/>
		<g:javascript src="/template/jquery.masonry.min.js"/>
		<g:javascript src="/template/jquery.knob.modified.js"/>
		<g:javascript src="/template/jquery.sparkline.min.js"/>
		<g:javascript src="/template/counter.min.js"/>
		<g:javascript src="/template/raphael.2.1.0.min.js"></g:javascript>
		<g:javascript src="/template/justgage.1.0.1.min.js"/>
		<g:javascript src="/template/jquery.autosize.min.js"/>
		<g:javascript src="/template/retina.js"></g:javascript>
		<g:javascript src="/template/jquery.placeholder.min.js"></g:javascript>
		<g:javascript src="/template/wizard.min.js"></g:javascript>
		<g:javascript src="/template/core.min.js"/>
		 <!--Graficos-->
		<g:javascript src="/template/charts.min.js"/>
		<!--Personalizar-->
		<g:javascript src="/template/custom.min.js"/>

	</head>
	<body>
		<!-- Para el header -->
		<sec:ifLoggedIn>
			<g:set var="varUsuarioService" bean="usuarioService"/>
	    	<g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
	    	<g:set var="cantMensajes" value="${com.mensajeria.Mensaje.findAllByReceptorAndLeido(usuario, Boolean.FALSE).size()}"/>
    	</sec:ifLoggedIn>
    	<div id="headerTemplate">
			<g:render template="/templateRed/header"/>
    	</div>
		<g:layoutBody/>
		<r:layoutResources />
		<g:render template="/templateRed/footer"/>
	</body>
</html>
