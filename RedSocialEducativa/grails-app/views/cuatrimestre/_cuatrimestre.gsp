<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Cuatrimestre ${cuatrimestreInstance}</h2>
            <div class="box-icon">
            	<g:link action="delete" method="DELETE" id="${cuatrimestreInstance.id}" params="['cursoId': params.cursoId]"
	        	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash"></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Anio: ${fieldValue(bean: cuatrimestreInstance, field: "anio")}</dt>
                <dt>Numero: ${fieldValue(bean: cuatrimestreInstance, field: "numero")}</dt>
                <dt>Foro: <g:link controller="foroCurso" action="general" id="${cuatrimestreInstance?.foro?.id}" 
					params="['cursoId': cuatrimestreInstance.curso.id, 'cuatrimestreId': cuatrimestreInstance.id]">	
					${fieldValue(bean: cuatrimestreInstance, field: "foro")}</g:link>
                </dt>
                <g:if test="${cuatrimestreInstance.noticiasCurso}">
                	<dt>Noticias curso: <g:link controller="noticiaCurso" action="index" params="['cursoId': params.cursoId]"><i class="icon-trash"></i></g:link></dt>
                </g:if>
                <g:if test="${cuatrimestreInstance.actividades}">	
               		<dt>Actividades: <g:link controller="actividad" action="index" params="['cursoId': params.cursoId]"><i class="icon-trash"></i></g:link></dt>
                </g:if>
                <dt>Aprendices:</dt> 
                	<g:each in="${cuatrimestreInstance.aprendices}" var="a">
                		<dd>${a.usuario.padron}-${a.usuario}</dd>
                	</g:each>
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
