<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'mensajeria/redactar.css')}" type="text/css">
<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources/>
<g:javascript src="mensajeria/redactar.js" />
<title>Redactar</title>
</head>
<body>
	<g:message code="Redactar mensaje" />
  <div class="body">
  	<g:form action="enviarMensajes">
			<div>
				<label for="para"> 
					<g:message code="Para:" /> 
				</label>
				<g:textField name="para" id="para" value="" />
				<g:img file="organigrama.jpg" id="img_clickeable" style="cursor: pointer"/>
			</div>
			<div>
				<label for="asunto"> 
					<g:message code="Asunto:" /> 
				</label>
				<g:textField name="asunto" value="" />
			</div>
			<div class="required">
				<label for="mensaje"> 
					<g:message code="Mensaje:" /> 
				</label>
				<g:textArea name="mensaje" value="" rows="5" cols="40"/>
			</div>

			<fieldset class="buttons">
				<g:submitButton name="enviar" class ="save" value="Enviar"/>	
			</fieldset>
		</g:form>
  </div>
  <div id="organigrama" style="display:none">
  	<g:img file="cross-icon.png" id="cerrarOrganigrama" style="float:right; cursor: pointer" />
  	<div>Cursos de aprendiz</div>
  	<g:each in='${cursosAprendiz}' var='cursoAprendiz'>
  		<div>
  		<img src="../images/tree_plus.gif" class="link-aprendiz" id="${cursoAprendiz.id}" style="cursor: pointer"/>
  		<span class="seleccionableOrg" style="margin-left: 10px;" id="curso-${cursoAprendiz.id}">${cursoAprendiz.nombre} - ${cursoAprendiz.materia.codigo}</span>
  		<div id="aprendiz-${cursoAprendiz.id}"></div>
  		</div>
  	</g:each>
  </div>
  ${usuariosFormateados }
	<div id="spinner" class="center_div"
		style="display: none; height: 16px; width: 16px;">
		<g:img file="spinner.gif" />
	</div>
	
</body>
</html>