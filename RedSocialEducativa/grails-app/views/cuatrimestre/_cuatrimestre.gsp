<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Cuatrimestre ${cuatrimestreInstance}</h2>
            <div class="box-icon">
            	<g:link action="delete" method="DELETE" id="${cuatrimestreInstance.id}" params="['cursoId': params.cursoId]"
	        		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash"></i></g:link>
	        	<g:link action="indexHistoriales" params="['cursoId': params.cursoId]"><i class="icon-table"></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Anio: <g:formatNumber number="${cuatrimestreInstance.anio}" /></dt>
                <dt>Numero: ${fieldValue(bean: cuatrimestreInstance, field: "numero")}</dt>
                <dt><g:link class="btn btn-success" controller="foroCurso" action="general" id="${cuatrimestreInstance?.foro?.id}" 
					params="['cursoId': cuatrimestreInstance.curso.id, 'cuatrimestreId': cuatrimestreInstance.id]">	
					Foro</i></g:link>
				</dt>
                <g:if test="${cuatrimestreInstance.noticiasCurso}">
                	<dt><g:link class="btn btn-success" controller="noticiaCurso" action="index" 
                		params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreInstance.id]">Noticias cursado</g:link>
                	</dt>
                </g:if>
                <g:if test="${cuatrimestreInstance.actividades}">	
               		<dt><g:link class="btn btn-success" controller="actividad" action="index" 
               			params="['cursoId': params.cursoId, 'cuatrimestreId': cuatrimestreInstance.id]">Actividades</g:link>
               		</dt>
                </g:if>
                <dt>Aprendices:</dt> 
                	<g:each in="${cuatrimestreInstance.aprendices}" var="a">
                		<dd>${a.usuario.padron}-${a.usuario.nombres} ${a.usuario.apellido}</dd>
                	</g:each>
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
