<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Actividades</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Categoria</th>
                        <th>Objetivo</th>
                        <th>Fecha finalizacion</th>
                        <th>Grupal</th>
                        <th>Evaluable</th>  
                        <th>Visibilidad</th>
                        <th>Material</th>
                        <th>Temas</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${actividadInstanceList}" var="actividadInstance">
	                    <tr>
    	                    <td>${actividadInstance.titulo}</td>
        	                <td class="center">${actividadInstance.categoria}</td>
        	                <td class="center">${actividadInstance.objetivo}</td>
            	            <td class="center">${actividadInstance.fechaFinalizacion}</td>
            	            <td class="center">${actividadInstance.grupal}</td>
            	            <td class="center">${actividadInstance.evaluable}</td>
            	            <td class="center">${actividadInstance.visibilidad}</td>
            	            <td class="center">
            	           		<g:if test="${actividadInstance.materiales}">
	        	                	<g:each in="${actividadInstance.materiales}" var="m">
										<p>${m?.encodeAsHTML()}
											<g:link style="float: right;" class="btn btn-danger" controller="materialActividad" action="delete" method="DELETE" id="${m.id}" 
												params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]"
												onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
												<i class="icon-trash "></i></g:link>
											<g:link style="float: right;" class="btn btn-info" controller="materialActividad" action="edit" id="${m.id}"
												params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
												<i class="icon-edit "></i></g:link> 	
											<g:link style="float: right;" class="btn btn-success" controller="materialActividad" action="show" id="${m.id}" 
												params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
												<i class="icon-search"></i></g:link></p>
									</g:each>  
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	            </td>
            	            <td class="center">
            	            	<g:if test="${actividadInstance.temas}">
	        	           	     	<g:each in="${actividadInstance.temas}" var="t">
										<p>${t.tema}
											<g:link style="float: right;" class="btn btn-danger" controller="temaActividad" action="delete" method="DELETE" id="${t.id}" 
												params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]"
												onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
												<i class="icon-trash "></i></g:link>	
											<g:link style="float: right;" class="btn btn-success" controller="tema" action="index" id="${t.tema.id}" 
												params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">
												<i class="icon-search"></i></g:link></p>
									</g:each>  
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	            </td>
	                        <td class="center">
	                        	<g:link class="btn btn-success" controller="grupoActividad" action="gruposMediador" id="${actividadInstance.id}"
	                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id]">Grupos</g:link>
	                        	<g:link class="btn btn-success" controller="materialActividad" action="create"  
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance?.id]">M</g:link>
	          					<g:link class="btn btn-success" controller="temaActividad" action="create"  
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance?.id]">T</g:link>
	                        	<g:link class="btn btn-success" action="show" id="${actividadInstance.id}"
	                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-search "></i></g:link>
	                            <g:link class="btn btn-info" action="edit" id="${actividadInstance.id}"
	                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-edit "></i></g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${actividadInstance.id}" 
	                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
	                                <i class="icon-trash "></i></g:link>
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
