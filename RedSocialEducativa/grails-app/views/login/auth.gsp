<html>
<head>
	<meta name='layout' content='red'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>
<body>
	<div class="row-fluid">
		<div class="span3"></div>	
		<div class="span6">	
			<div class="row-fluid">
				<div class="span7">
					<div class="box-content">     
						<g:render template="/red/membresia"></g:render>
					</div>
				</div>		
				<div class="span1"></div>		
				<div class="span4">
					<div class="box-content">     
						<g:render template="/login/login"></g:render>
					</div>
				</div>		
			</div>
		</div>
		<div class="span3"></div>		
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
