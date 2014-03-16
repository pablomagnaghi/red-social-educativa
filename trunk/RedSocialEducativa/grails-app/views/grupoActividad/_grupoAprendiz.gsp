<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Mi grupo</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Numero de grupo</th>  
						<th>Nombre de grupo</th>  
						<th>Aprendices</th>
						<th>Materiales</th> 
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
		           	<tr>
	    	            <td>${grupoActividadInstance.numero}</td>
	        	        <td class="center">${grupoActividadInstance.nombre}</td>
	            		<td class="center">
							<g:each in="${grupoActividadInstance.aprendices}" var="a">
								<p>${a.aprendiz.usuario.padron}-${a.aprendiz.usuario}-${a.aprendiz.usuario.email}</p>
							</g:each>
	            		</td>
	            	    <td class="center">
	            	   		<g:each in="${grupoActividadInstance.materiales}" var="m">
								<p><g:link controller="materialGrupoActividad" action="show" id="${m.id}" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
									'grupoActividadId': grupoActividadInstance.id]">${m?.encodeAsHTML()}</g:link>
									<g:link controller="materialGrupoActividad" action="edit" id="${m.id}" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId,
									'grupoActividadId': grupoActividadInstance.id]">editar material</g:link>
								</p>
							</g:each>
	            	    </td>
		                <td class="center">          
							<g:link action="editar" id="${grupoActividadInstance.id}"
								params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
								<g:message code="Editar nombre de grupo" /></g:link>
							<g:link controller="materialGrupoActividad" action="create"
								params="['grupoActividadId': grupoActividadInstance.id, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
								'actividadId': params.actividadId]">
								<g:message code="Agregar material al grupo" />
							</g:link>
      					 </td>
				 	</tr>
			
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
