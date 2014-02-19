<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="mail" />
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
<g:javascript library="jquery" />
<r:require module="jquery-ui" />
<r:layoutResources />
<g:javascript src="mensajeria/redactar.js" />
<g:javascript src="select2.min.js" />
<g:javascript src="mensajeria/index.js" />
<title>Mensajería</title>
</head>
<body>
	<g:if test="${carpetaSeleccionada == "Enviados" }">
		<g:render template="panelSuperior"
			model="['mensajesCount' : mensajesCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
	</g:if>
	<g:else>
		<g:render template="panelSuperior"
			model="['conversacionCount' : conversacionCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
	</g:else>

	<div class="inbox-body no-content-padding" id="inbox-content"
		style="height: 900px">

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

	<div class="footer"></div>
</body>
</html>