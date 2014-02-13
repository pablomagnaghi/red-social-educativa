<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="mail" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/conversacion.css')}"
	type="text/css">
<g:javascript library="jquery" />
<r:layoutResources />
<g:javascript src="mensajeria/conversacion.js" />
<title>Conversacion</title>
</head>
<body>
	<g:pdfLink pdfController="mensajeria" pdfAction="conversacionAPdf"
		pdfId="${conversacionId }">Exportar a pdf</g:pdfLink>
	<g:render template="mensajes" model="['mensajes' : mensajes]"></g:render>
</body>
</html>