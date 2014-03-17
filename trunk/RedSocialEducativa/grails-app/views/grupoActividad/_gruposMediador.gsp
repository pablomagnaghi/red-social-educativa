<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Grupos de la actividad ${com.cursado.Actividad.get(params.actividadId)}</h2>
                <div class="box-icon">
                	<g:link class="create" action="cambiarAprendiz" 
	                	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
						<i class="icon-refresh"></i></g:link>
            	</div>
        </div>
        <div class="box-content">
        	<g:if test="${flash.message}">
				<div class="box-content alerts">
		    		<div class="alert alert-info">
						<button class="close" data-dismiss="alert" type="button"></button>
						<strong></strong> 
						${flash.message}
				    </div>
				</div>    
			</g:if>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                    	<th>Grupo</th>  
                    	<th>Nombre</th>
						<th>Materiales</th> 
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${gruposActividad}">
		           		<tr>
		           			<td>${it.numero}</td>
	    	             	<td class="center">${it.nombre}</td>
	            	    	<td class="center">
	            	    		<g:each in="${it.materiales}" var="m">
									<p>${m?.encodeAsHTML()}
										<g:link style="float: right;" class="btn btn-success" controller="materialGrupoActividad" action="show" id="${m.id}" 
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
											'grupoActividadId': it.id]">
											<i class="icon-search"></i></g:link>
										<g:link style="float: right;" class="btn btn-success" controller="materialGrupoActividad" action="descargar" id="${m?.archivo?.id}" 
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
											'grupoActividadId': it.id]">
											<i class="icon-download-alt"></i></g:link>
									</p>	
								</g:each>
	            	    	</td>
		                	<td class="center">	    
		                		<g:link class="btn btn-success" controller="grupoActividad" action="grupoMediador" id="${it.id}"
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
									<i class="icon-group"></i></g:link>	            		
								<g:link class="btn btn-danger" action="eliminar" id="${it.id}"  
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
									<i class="icon-trash"></i></g:link>
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
