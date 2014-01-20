<g:each in='${mediadores}' var='mediador'>
	<div style="margin-left: 10px">
		<img src="../images/black-dot.jpg" class="link-aprendiz"/>
		<span class="seleccionableOrg" id="mediador-${mediador.id}">Mediador: ${mediador.usuario.apellido}, ${mediador.usuario.nombres}</span>
	</div>
</g:each>
