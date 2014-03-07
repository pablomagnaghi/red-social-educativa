<html>
<head>
	<meta name='layout' content='red'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>
<body>
	<div class="row-fluid">
		<div class="span2"></div>	
		<div class="span8">	
			<div class="row-fluid">
				<div class="span8">  
					<div class="row-fluid">
						<br><br><br><br><br>
						<g:render template="info"></g:render>
					</div>
				</div>			
				<div class="span4">    
					<g:render template="/login/login"></g:render>
				</div>		
			</div>
		</div>
		<div class="span2"></div>		
	</div>
	
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><i class="icon-remove"></i></button>
			<h3>Obtener una nueva contraseña</h3>
		</div>
		<div class="modal-body">
			<g:form class="form-horizontal" controller="red" action="revisarUsername">
				<fieldset>					
					<div class="control-group">
						<label class="control-label" >Usuario</label>			
						<div class="controls">
							<g:textField name="username"/>
						</div>	
					</div>	
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Aceptar</button>
					</div>		    
				</fieldset>
			</g:form>
		</div>
	</div>
	
	<script type='text/javascript'>
		<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
		// -->
	</script>
</body>
</html>
