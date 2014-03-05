<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Datos del cuatrimestre</h2>			
        </div>
        <div class="box-content">
            
            
            
                         <g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				
				
				
				
				<g:if test="${cuatrimestreInstance?.actividades}">
				<li class="fieldcontain">
					<span id="actividades-label" class="property-label"><g:message code="cuatrimestre.actividades.label" default="Actividades" /></span>
					
						<g:each in="${cuatrimestreInstance.actividades}" var="a">
						<span class="property-value" aria-labelledby="actividades-label"><g:link controller="actividad" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label"><g:message code="cuatrimestre.anio.label" default="Anio" /></span>
					
						<span class="property-value" aria-labelledby="anio-label">
							<g:formatNumber number="${cuatrimestreInstance.anio}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cuatrimestreInstance?.aprendices}">
				<li class="fieldcontain">
					<span id="aprendices-label" class="property-label"><g:message code="cuatrimestre.aprendices.label" default="Aprendices" /></span>
					
						<g:each in="${cuatrimestreInstance.aprendices}" var="a">
						<span class="property-value" aria-labelledby="aprendices-label">
							<g:link controller="usuario" action="muestraMenuMed" id="${a.usuario.id}"
								params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreInstance.id]">${a?.encodeAsHTML()}</g:link></span>
							<p>Mostrar si participa y cursa sacar de curso</p>
							<p>agregar la opcion de dejar de participar</p>
						</g:each>
					
				</li>
				</g:if>
					
				<g:if test="${cuatrimestreInstance?.foro}">
				<li class="fieldcontain">
					<span id="foro-label" class="property-label"><g:message code="cuatrimestre.foro.label" default="Foro" /></span>
					
						<span class="property-value" aria-labelledby="foro-label">
							<g:link controller="foroCurso" action="general" id="${cuatrimestreInstance?.foro?.id}" 
								params="['cursoId': cuatrimestreInstance.curso.id, 'cuatrimestreId': cuatrimestreInstance.id]">		
								${cuatrimestreInstance?.foro?.encodeAsHTML()}</g:link></span>
				</li>
				</g:if>
			

			
				<g:if test="${cuatrimestreInstance?.noticiasCurso}">
				<li class="fieldcontain">
					<span id="noticiasCurso-label" class="property-label"><g:message code="cuatrimestre.noticiasCurso.label" default="Noticias Curso" /></span>
					
						<g:each in="${cuatrimestreInstance.noticiasCurso}" var="n">
						<span class="property-value" aria-labelledby="noticiasCurso-label"><g:link controller="noticiaCurso" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>

			
				<g:if test="${cuatrimestreInstance?.numero}">
				<li class="fieldcontain">
					<span id="numero-label" class="property-label"><g:message code="cuatrimestre.numero.label" default="Numero" /></span>
					
						<span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${cuatrimestreInstance}" field="numero"/></span>
					
				</li>
				</g:if>
				
				
				
            
            
            
            
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->








				
			
