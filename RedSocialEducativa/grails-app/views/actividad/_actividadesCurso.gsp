<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Actividades</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Categoria</th>
                        <th>Objetivo</th>
     					<th>Material</th>
     					<th>Temas</th>
     					<th>Grupos</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${actividades}" var="actividadInstance">
	                    <tr>
    	                    <td>${actividadInstance.titulo} (${actividadInstance.id})</td>
        	                <td class="center">${actividadInstance.categoria}</td>
        	                <td class="center">${actividadInstance.objetivo}</td>
        	                <td class="center">
        	                	<g:link controller="materialActividad" action="materialAprendiz" params="['cursoId': params.cursoId, 
									'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
									<g:message code="Material de la actividad"/></g:link>
							</td>
							<td class="center">
								<g:link controller="temaActividad" action="temaAprendiz" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
									<g:message code="Temas asociados con la actividad"/></g:link>
        	                </td>
        	                <!-- REVISAR -->
        	                <td class="center">
        	                
								<g:link class="list" controller="grupoActividad" action="gruposAprendiz" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
									<g:message code="Los grupos de la actividad"/></g:link>
        	                
        	                </td>
        	                
        	                <td class="center">
        	                	<g:set var="grupoActividadAprendiz" 
        	                		value="${varAprendizService.obtenerGrupoPorActividad(usuario, params.cuatrimestreId.toLong(), actividadInstance.id)}"/>
        	                	<g:if test="${grupoActividadAprendiz}">	
									<g:link class="list" controller="grupoActividad" action="grupoAprendiz" id="${grupoActividadAprendiz.grupo.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
										<g:message code="Mi grupo ${grupoActividadAprendiz.grupo}"/></g:link>
								</g:if>
								<g:else>
									Sin grupo
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
