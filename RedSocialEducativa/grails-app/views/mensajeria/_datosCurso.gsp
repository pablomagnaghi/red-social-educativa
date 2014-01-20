<g:each in='${mediadores}' var='mediador'>
	<div style="margin-left: 10px">
		<img src="../images/black-dot.jpg" class="link-aprendiz" id="mediador-${mediador.id}"/>
		<span >${mediador.usuario.apellido}, ${mediador.usuario.nombres}</span>
	</div>
</g:each>
