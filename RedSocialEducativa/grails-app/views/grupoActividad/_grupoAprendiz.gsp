<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Mi grupo</h2>               
            <div class="box-icon"> 
                <g:link action="editar" id="${grupoActividadInstance.id}"
					params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
					<i class="icon-edit "></i></g:link> 	
				<g:link controller="materialGrupoActividad" action="create"
					params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
						'actividadId': params.actividadId, 'grupoActividadId': grupoActividadInstance.id]"><i class="icon-plus"></i></g:link>
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
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Numero de grupo</th>  
						<th>Nombre de grupo</th>  
						<th>Integrantes</th>
						<th>Materiales</th>     
						<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}"><th>Nota</th></g:if>
						<g:else><th>Cumplio</th></g:else>	
					</tr>
				</thead>
                <tbody>
		           	<tr>
	    	            <td>${grupoActividadInstance.numero}</td>
	        	        <td class="center">${grupoActividadInstance.nombre}</td>
	            		<td class="center">
							<g:each in="${grupoActividadInstance.aprendices}" var="a">
								<p>${a.aprendiz.usuario.nombres} ${a.aprendiz.usuario.apellido} <small style="float: right;">${a.aprendiz.usuario.email}</small></p>
							</g:each>
	            		</td>
	            	    <td class="center">
	            	   		<g:each in="${grupoActividadInstance.materiales}" var="m">
								<p><g:link style="float: right;" class="btn btn-danger" controller="materialGrupoActividad" action="delete" method="DELETE" id="${m.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id,
										'grupoActividadId': grupoActividadInstance.id]"
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
										<i class="icon-trash "></i></g:link>
									<g:link style="float: right;" class="btn btn-info" controller="materialGrupoActividad" action="edit" id="${m.id}"
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id,
										'grupoActividadId': grupoActividadInstance.id]">
										<i class="icon-edit "></i></g:link> 	
									<g:link style="float: right;" class="btn btn-success" controller="materialGrupoActividad" action="show" id="${m.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id, 
										'grupoActividadId': grupoActividadInstance.id]">
										<i class="icon-search"></i></g:link>
									<g:link style="float: right;" class="btn btn-success" controller="materialGrupoActividad" action="descargar" id="${m?.archivo?.id}" 
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': actividadInstance.id,
										'grupoActividadId': grupoActividadInstance.id]">
										<i class="icon-download-alt"></i></g:link></p>
							</g:each>
	            	    </td>      
	            	    <g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
		            	    <td class="center">
		            	    	<g:each in="${grupoActividadInstance.aprendices}">
									<p>${it.id} Nota ${it.nota}</p>
								</g:each>
		            	   	</td>
	            	    </g:if>
	            	    <g:else>
		            	   	<td class="center">
			            	   	<g:each in="${grupoActividadInstance.aprendices}">
									<p>${it.id} Nota${it.cumplio}</p>
								</g:each>	
		            	   	</td>
	            	    </g:else>      
				 	</tr>
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
