<ul>
    <g:each in="${publicacion}">
        <p>Respuesta: ${it.titulo} - Responsable: ${it.responsable} - Fecha: ${it.fecha} - Hora: ${it.hora} </p>
		<p>[[[${it.contenido}]]]</p>
		<g:set var="padreId" value="${it.id}"/>
        <g:render template="respuestas" model="${[publicacion: it.respuesta]}" />
    </g:each>
</ul>