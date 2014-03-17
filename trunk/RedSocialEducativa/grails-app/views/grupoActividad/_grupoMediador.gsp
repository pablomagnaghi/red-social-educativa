<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Grupo: ${grupoActividadInstance} - ${grupoActividadInstance.nombre}</h2>
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
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Email</th>
						<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
							<th>Nota</th>
						</g:if>
						<g:else>
							<th>Cumplio</th>
						</g:else>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
               		<g:each in="${grupoActividadInstance.aprendices}">
		           		<tr>
	    	             	<td>${it.aprendiz.usuario.padron}</td>
	        	         	<td class="center">${it.aprendiz.usuario.apellido}</td>
	            			<td class="center">${it.aprendiz.usuario.nombres}</td>
	            			<td class="center">${it.aprendiz.usuario.email}</td>
	            	    	<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
		            	    	<td class="center">
			            	    	<g:if test="${it.calificado}">
	            	            		<g:if test="${!it.nota}">0.00</g:if>
	            	            		<g:else>${it.nota}</g:else>		
	            	            	</g:if>
	            	            	<g:else>
		            	           		<g:form class="form-horizontal" controller="grupoActividadAprendiz" action="guardarCalificacion" id="${it.id}" 
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
											'actividadId': params.actividadId, 'grupoActividadId':it.grupo.id]">			
											<g:hiddenField name="grupo.id" value="${it.grupo.id}"/>
											<g:hiddenField name="aprendiz.id" value="${it.aprendiz.id}"/>
											<fieldset>		
												<g:field name="nota" type="number decimal" value="${it.nota}" style="width: 10%; text-align: center"/>							
												<button class="btn btn-success" type="submit" class="btn btn-primary">Calificar</button>	    
											</fieldset>
									 	</g:form> 
									 </g:else>	
								</td>	 
	            	    	</g:if>
	            	    	<g:else>
		            	    	<td class="center">
		            	    		<g:if test="${it.nota}">${it.nota}</g:if>
	            	            	<g:else>0.00</g:else>
		            	    	</td>
	            	    	</g:else>
		                	<td class="center">	                		
		                		<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
		                			<g:if test="${it.calificado}">
										<g:link class="btn btn-success" controller="grupoActividadAprendiz" action="calificar" id="${it.id}"								
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
											'grupoActividadId': it.grupo.id]">Recalificar</g:link>
									</g:if>	
								</g:if>
								<g:else>
									<g:if test="${it.cumplio}">
										<g:link class="btn btn-danger" controller="grupoActividadAprendiz" action="cambiarEstado" id="${it.id}"								
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
											'grupoActividadId': it.grupo.id]"><i class="icon-off"></i></g:link>
									</g:if>
									<g:else>
										<g:link class="btn btn-success" controller="grupoActividadAprendiz" action="cambiarEstado" id="${it.id}"								
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
											'grupoActividadId': it.grupo.id]"><i class="icon-ok"></i></g:link>
									</g:else>		
								</g:else>
								<g:link class="btn btn-danger" controller="grupoActividadAprendiz" action="delete" id="${it.id}"  
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
