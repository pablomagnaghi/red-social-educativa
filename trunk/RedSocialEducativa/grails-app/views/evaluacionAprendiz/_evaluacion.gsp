<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluacion ${evaluacion}</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]"><i class="icon-plus"></i></g:link>
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
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>Nota</th>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluaciones}">
	                    <tr>
    	                    <td>${it.aprendiz.usuario.padron}</td>
        	                <td class="center">${it.aprendiz.usuario.apellido}</td>
        	                <td class="center">${it.aprendiz.usuario.nombres}</td>
            	            <td class="center">
            	            	<g:if test="${it.nota}">${it.nota}</g:if>
            	            	<g:else>
	            	           		<g:form class="form-horizontal" action="guardarCalificacion" id="${it.id}" 
										params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId, 'aprendizId': params.aprendizId]"> 
										<g:hiddenField name="evaluacion.id" value="${it.evaluacion.id}"/>
										<g:hiddenField name="aprendiz.id" value="${it.aprendiz.id}"/>
										<fieldset>		
											<g:field name="nota" type="number" min="0" max="10" value="${it.nota}" style="width: 10%"/>							
											<button class="btn btn-success" type="submit" class="btn btn-primary">Calificar</button>	    
										</fieldset>
								 	</g:form> 
							 	</g:else>
            	            </td>
	                        <td class="center">
	                        	<g:if test="${it.nota}">
		                            <g:link class="btn-setting" class="btn btn-success" action="calificar" id="${it.id}" 
										params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]">Recalificar</g:link>
	                            </g:if>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${it.id}" 
	                            	params="['cursoId': params.cursoId, 'evaluacionId': params.evaluacionId]"
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
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
				
	