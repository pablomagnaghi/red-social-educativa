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
	<div class="inbox-nav-bar no-content-padding">

		<h1 class="page-title txt-color-blueDark hidden-tablet">
			<i class="fa fa-fw fa-inbox"></i> Inbox &nbsp;
		</h1>

		<div class="inbox-checkbox-triggered">

			<div class="btn-group">
			<a class="btn btn-default"  data-placement="bottom" title="" rel="tooltip" href="javascript:void(0);" onclick="volver()"><strong><i class="glyphicon glyphicon-arrow-left"></i></strong></a>
			<a class="deletebutton btn btn-default" data-placement="bottom" title="" rel="tooltip" href="javascript:void(0);" onclick="borrarConversacion()"><strong><i class="fa fa-trash-o fa-lg"></i></strong></a>
			</div>

		</div>
		
		<div class="btn-group pull-right inbox-paging">
			<g:remoteLink class="btn btn-default btn-sm" action="redactarMensaje" 
				update="[success:'inbox-content']" onSuccess="when_ready();"><strong><i
					class="fa fa-chevron-left"></i></strong></g:remoteLink> <a class="btn btn-default btn-sm"
				href="javascript:void(0);"><strong><i
					class="fa fa-chevron-right"></i></strong></a>
		</div>
		
		<span>
			<label class="input"> <i class="icon-prepend fa fa-user"></i>
				<input type="text" id="deBuscar" name="de" placeholder="De:">
			</label>
			<label class="input"> <i class="icon-prepend fa fa-user"></i>
				<input type="text" id="paraBuscar" name="para" placeholder="Para:">
			</label>
			<button class="btn btn-default" type="submit" onclick="buscarMensajes()">
				<span class="glyphicon glyphicon-search"></span> Buscar
			</button>
		</span>
		
		<span class="pull-right"><strong>1-30</strong> of <strong>3,452</strong></span>

	</div>

	<div class="inbox-body no-content-padding" id="inbox-content" style="height: 900px">
		<div class="inbox-side-bar">
			<g:remoteLink action="redactarMensaje"
				update="[success:'contenidoMensajes']" onSuccess="when_ready();" id="compose-mail"
				class="btn btn-primary btn-block">
				<span class="glyphicon glyphicon-envelope"></span>
				<strong>Redactar</strong>
			</g:remoteLink>
			<h6>
				Carpetas <a class="pull-right txt-color-darken"
					data-original-title="Refresh" data-placement="right" title=""
					rel="tooltip" href="javascript:void(0);"><i
					class="fa fa-refresh"></i></a>
			</h6>
			<ul class="inbox-menu-lg" id="lista_carpetas">
				<g:render template="carpetas"
					model="['etiquetasCarpetas' : etiquetasCarpetas, 'seleccionada' : carpetaSeleccionada]"></g:render>
			</ul>
		</div>
		<div class="table-wrap custom-scroll animated fast fadeInRight" id="contenidoMensajes" style="height: 950px; opacity: 1;">
			<table id="inbox-table" class="table table-striped table-hover">
				<tbody id="listaConversaciones">
					<g:render template="conversaciones" model="['conversaciones' : conversacionesEscr]"></g:render>
				</tbody>
			</table>
		</div>
	</div>
<div id="div_nueva_carpeta" class="center_div_carpeta" style="display:none">
   <div id="wid-id-0" class="jarviswidget jarviswidget-sortable" role="widget">
				
				<header role="heading">
					<div class="jarviswidget-ctrls" role="menu">  
					 <a data-placement="bottom" title="" onclick="cerrar_form_nueva_carpeta()" rel="tooltip" class="button-icon jarviswidget-delete-btn" href="javascript:void(0);" data-original-title="Delete">
					  <i class="fa fa-times"></i>
					 </a>
					</div>
					
					<h2>Crear Carpeta</h2>				
						
					<span class="jarviswidget-loader" style="display: none;">
						<i class="fa fa-refresh fa-spin"></i>
					</span>
				</header>
					
				<!-- widget div-->
				<div role="content" style="height: 150px">
					
					<!-- widget edit box -->
					<g:formRemote onSuccess="cerrar_form_nueva_carpeta();" name="nuevaCarpetaForm" url="[controller:'mensajeria', action:'nuevaCarpeta']" update="lista_carpetas">
					
					<div class="jarviswidget-editbox" style="display: block;">
						<!-- This area used as dropdown edit box -->
						<input type="text" name="nombre" id="nombre" class="form-control">
						<span class="note">Nombre de carpeta</span>
						
					</div>
					<!-- end widget edit box -->
					<div style="padding-left: 57px;">
						<button class="btn btn-default btn-lg" onclick="cerrar_form_nueva_carpeta()"> Cancelar </button>
						<g:submitButton name="Guardar" class="btn btn-default btn-lg" id="guardar_carpeta"/>
					</div>
					 </g:formRemote>
					<!-- end widget content -->
					
				</div>
				<!-- end widget div -->
				
			</div>
  </div>
	
	<div class="footer"></div>
</body>
</html>