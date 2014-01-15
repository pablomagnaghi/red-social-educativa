<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'mensajeria/main.css')}" type="text/css">
<g:javascript library="jquery" />
<r:layoutResources/>
<g:javascript src="mensajeria/index.js" />
<title>Mensajería</title>
</head>
<body>
  <div class="header">
  	<ul class="menuBar">
  		<li><g:link id="redactarMensaje">Redactar</g:link>
  	</ul>
  </div>
  <div class="body" style="display:inline">
  	<div style="width: 150px; float: left">
	  	<ul id="lista_carpetas" style="list-style-type:none;">
		  	<g:render template="carpetas" model="['etiquetasCarpetas' : etiquetasCarpetas]"></g:render>
	  	</ul>
  	</div>
  	<div style="float: left">
  		<g:each in="${conversacionesEscr}" var="conversacion">
  			<msg:showConversation data="${conversacion}"/>
  		</g:each>
  	</div>
  </div>
  <div id="div_nueva_carpeta" style="display:none">
  	<g:formRemote onSuccess="cerrar_form_nueva_carpeta();" name="nuevaCarpetaForm" url="[controller:'mensajeria', action:'nuevaCarpeta']" update="lista_carpetas">
	    <label for="nombre">Nombre de carpeta</label><g:textField name="nombre"/><br/>
	    <g:submitButton name="Guardar" id="guardar_carpeta"/>
  	</g:formRemote>
  </div>
  <div class="footer">
  </div>
</body>
</html>