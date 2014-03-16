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
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
						<th>Materiales</th> 
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
                	<g:each in="${grupoActividadAprendices}">
		           		<tr>
		           			<td>${it.grupo.numero} - ${it.grupo.nombre}-id:${it.id}</td>
	    	             	<td class="center">${it.aprendiz.usuario.padron}-id:${it.aprendiz.id}</td>
	        	         	<td class="center">${it.aprendiz.usuario.apellido}</td>
	            			<td class="center">${it.aprendiz.usuario.nombres}</td>
	            	    	<td class="center">
	            	    		<!-- REVISAR ESTO -->
	            	    		<g:each in="${it.grupo.materiales}" var="m">
									<p><g:link controller="materialGrupoActividad" action="materialAprendiz" id="${m.id}"
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
										'grupoActividadId': it.id]">${m?.encodeAsHTML()}</g:link></p>
								</g:each>
	            	    	</td>
	            	    	
	            	    	<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
		            	    	<td class="center">
			            	    	<g:if test="${it.calificado}">
	            	            		<g:if test="${!it.nota}">0.00</g:if>
	            	            		<g:else>${it.nota}</g:else>		
	            	            	</g:if>
	            	            	<g:else>
		            	           		<g:form class="form-horizontal" controller="grupoActividadAprendiz" action="guardarCalificacion" id="${it.id}" 
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 
											'actividadId': params.actividadId, 'grupoActividadId': params.grupoActividadId]">			
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
		            	    	<td class="center">${it.cumplio}</td>
	            	    	</g:else>
		                	<td class="center">	                		
		                		<g:if test="${com.cursado.Actividad.get(params.actividadId).evaluable}">
		                			<g:if test="${it.calificado}">
										<g:link class="btn btn-success" controller="grupoActividadAprendiz" action="calificar" id="${it.id}"								
											params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId, 
											'grupoActividadId': it.grupo.id]">Recalificar</g:link>
									</g:if>	
								</g:if>
								<g:link class="btn btn-danger" controller="grupoActividadAprendiz" action="delete" id="${it.id}"  
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
									<i class="icon-trash"></i></g:link>
								<g:link class="btn btn-danger" action="eliminar" id="${it.grupo.id}"  
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
									Eliminar grupo</i></g:link>
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
