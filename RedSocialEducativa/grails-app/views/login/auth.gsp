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
	
	<script type='text/javascript'>
		<!--
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
		// -->
	</script>
</body>
</html>
