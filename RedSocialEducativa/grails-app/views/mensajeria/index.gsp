<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="mail">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/mail-production.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/mail-skins.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/font-awesome.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/redactar.css')}"
	type="text/css">

<g:set var="entityName" value="Mensajeria" />
<title><g:message code="Mensajeria" args="[entityName]" /></title>
</head>
<body>
   	<div class="container-fluid-full">
		<div class="row-fluid">
			<g:render template="/templateRed/panel" />
			<div id="content" class="span10">
				<div class="row-fluid">
					<g:if test="${carpetaSeleccionada == "Enviados" }">
						<g:render template="panelSuperior"
							model="['mensajesCount' : mensajesCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
					</g:if>
					<g:else>
						<g:render template="panelSuperior"
							model="['conversacionCount' : conversacionCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
					</g:else>
				
				
					<g:render template="panelCarpetas"
						model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada' : seleccionada]"></g:render>
					<g:if test="${carpetaSeleccionada == "Enviados" }">
						<g:render template="panelConversaciones"
							model="['mensajes' : mensajes]"></g:render>
					</g:if>
					<g:else>
						<g:render template="panelConversaciones"
							model="['conversaciones' : conversaciones]"></g:render>
					</g:else>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>				
</body>
</html>