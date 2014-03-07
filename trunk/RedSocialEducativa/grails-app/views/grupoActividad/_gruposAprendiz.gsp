<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Grupos de la actividad</h2>
        	<div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
                	<i class="icon-plus"></i></g:link>
          	</div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Numero de grupo</th>  
						<th>Nombre de grupo</th>  
						<th>Materiales</th> 
						<g:if test="${com.fiuba.Actividad.get(params.actividadId).evaluable}">
							<th>Nota</th>
						</g:if>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${grupoActividadAprendices}">
		           		<tr>
	    	             	<td>${it.aprendiz.usuario.padron}-id:${it.aprendiz.id}</td>
	        	         	<td class="center">${it.aprendiz.usuario.apellido}</td>
	            			<td class="center">${it.aprendiz.usuario.nombres}</td>
	            	    	<td class="center">${it.grupo.numero}</td>
	            	    	<td class="center">${it.grupo.nombre}-id:${it.grupo.id}</td>
	            	    	
	            	    	<td class="center">
	            	    		<!-- REVISAR ESTO -->
	            	    		<g:each in="${it.grupo.materiales}" var="m">
									<p><g:link controller="materialGrupoActividad" action="show" id="${m.id}"
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
										'grupoActividadId': it.grupo.id]">${m?.encodeAsHTML()}</g:link></p>
								</g:each>		
	            	    	</td>
	            	    	
	            	    	<g:if test="${com.fiuba.Actividad.get(params.actividadId).evaluable}">
		            	    	<td class="center">${it.nota}</td>
	            	    	</g:if>
	            	    	
		                	<td class="center">          
								<g:set var="participa" value="${varGrupoActividadService.aprendizParticipa(it.grupo, aprendiz)}"/>
								<g:if test="${!participa}">
									<g:link class="btn btn-success" action="agregarme" id="${it.grupo.id}"
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
										<g:message code="Agregarme al grupo" />
									</g:link>
								</g:if>
								<g:else>
									<g:link action="editar" id="${it.grupo.id}"
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
										<g:message code="Editar nombre de grupo" />
									</g:link>
									<g:link controller="materialGrupoActividad" action="create"
										params="['grupoActividadId': it.grupo.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
										'actividadId': params.actividadId]">
										<g:message code="Agregar material al grupo" />
									</g:link>
								</g:else>
      					 	</td>
				 		</tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
