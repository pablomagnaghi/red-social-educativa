<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page import="com.fiuba.UsuarioService" %>
<%@ page import="com.fiuba.MediadorService" %>
<%@ page import="com.fiuba.AprendizService" %>
<%
	def usuarioService = grailsApplication.classLoader.loadClass('com.fiuba.UsuarioService').newInstance()
	def mediadorService = grailsApplication.classLoader.loadClass('com.fiuba.MediadorService').newInstance()
	def aprendizService = grailsApplication.classLoader.loadClass('com.fiuba.AprendizService').newInstance()
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="mail">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/font-awesome.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/redactar.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mensajeria/select2.css')}"
	type="text/css">

<g:set var="entityName" value="Mensajeria" />
<title><g:message code="Mensajeria" args="[entityName]" /></title>
</head>
<body>
	<!-- Para el header y el panel lateral -->
    <g:set var="varUsuarioService" bean="usuarioService"/>
    <g:set var="varMediadorService" bean="mediadorService"/>
    <g:set var="varAprendizService" bean="aprendizService"/>
    <g:set var="usuario" value="${varUsuarioService.usuarioActual()}"/>
    <g:set var="administrador" value="${com.fiuba.Administrador.findByUsuario(usuario)}"/>
    <g:set var="cursosMediador" value="${varMediadorService.obtenerCursos(usuario)}"/>
    <g:set var="cursosAprendiz" value="${varAprendizService.obtenerCursos(usuario)}"/>

   	<div class="container-fluid-full">
		<div class="row-fluid">
			<g:render template="/templateRed/panel" />
			<div id="content" class="span10">
				<div class="row-fluid">
					<g:if test="(${'Enviados'.equals(carpetaSeleccionada) || 'Borradores'.equals(carpetaSeleccionada)})">
						<g:render template="panelSuperior"
							model="['mensajesCount' : mensajesCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
					</g:if>
					<g:else>
						<g:render template="panelSuperior"
							model="['conversacionCount' : conversacionCount, 'nombreCarpeta':nombreCarpeta, 'offset':offset]"></g:render>
					</g:else>
				
					<div id="carpetasUsuario">
						<g:render template="panelCarpetas"
							model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada' : carpetaSeleccionada]"></g:render>
					</div>
					<g:if test="(${'Enviados'.equals(carpetaSeleccionada) || 'Borradores'.equals(carpetaSeleccionada)})">
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