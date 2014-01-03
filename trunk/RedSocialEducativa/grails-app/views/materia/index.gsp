<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		</head>
	<body>
		<div class="nav" role="navigation">
		</div>
		<div>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<br>
			<ol>
				<g:each in="${cursos}" var="curso">
					<li>
						<span class = "menuButton">
							<g:link action="index" controller="curso" id="${curso.id}">
							<g:message code="${curso}" /></g:link>
						</span>
					</li>
				</g:each>
			</ol>
		</div>
	</body>
</html>
