<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Grupos de la actividad</h2>
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
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${grupoActividadAprendices}">
		           		<tr>
	    	             	<td>${it.aprendiz.usuario.padron}</td>
	        	         	<td class="center">${it.aprendiz.usuario.apellido}</td>
	            			<td class="center">${it.aprendiz.usuario.nombres}</td>
	            	    	<td class="center">${it.grupo.numero}</td>
	            	    	<td class="center">${it.grupo.nombre}</td>
		                	<td class="center">
		                     	<g:if test="${true}">
		                     		<g:if test="${true}">
				               			${true}
				                    </g:if>
		                        	<g:else>
		                        		Inscripto
		                        	</g:else>
								</g:if>
								<g:else>
									<fieldset class="buttons">
										<g:link class="edit" action="inscribirme" params="['cursoId': params.cursoId]">
											Inscribirme: </g:link>
									</fieldset>
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
