<html>
<head>
	<meta name='layout' content='red'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>
<body>
	<div class="container-fluid-full">
		<div class="row-fluid">				
			<div class="row-fluid">
				<div class="span3">
				</div>
				<div class="span8">			
					<div class="span3">
						<g:render template="/red/membresia" />
					</div>
					<div class="span2">
					</div>
					<div class="span3">	
						<g:render template="/login/login"></g:render>	
					</div>
				</div>
				<div class="span1">
				</div>  	 
			</div><!--/row-->	
		</div><!--/fluid-row-->			
	</div><!--/.fluid-container-->

	<script type='text/javascript'>
		<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
		// -->
	</script>
</body>
</html>
