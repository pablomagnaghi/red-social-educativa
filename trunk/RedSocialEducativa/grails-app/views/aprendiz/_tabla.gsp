<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i>
                <span class="break"></span>Aprendices</h2>
            <div class="box-icon">
            	<a href="#" class="btn-setting"><g:link action="create" params="['cursoId': params.cursoId, 
            		'cuatrimestreId': params.cuatrimestreId]">nuevo aprendiz</g:link></a>
                <a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
                <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
                <a href="#" class="btn-close"><i class="icon-remove"></i></a>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Usuario</th>                    
                        <th>Estado</th>                
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${aprendizInstanceList}" var="aprendizInstance">
	                    <tr>
    	                    <td><g:link controller="usuario" action="muestraMenuMed" id="${aprendizInstance.usuario.id}" 
								params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
								${fieldValue(bean: aprendizInstance, field: "usuario")}</g:link>  
    	                    </td>
        	               
        	                <g:if test="${!aprendizInstance.participa}">
								<td>Esperando aceptacion (<g:link controller="mediador" action="activarAprendiz" id="${aprendizInstance.id}" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
									value="${message(code: 'Activar')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 
									default: 'Are you sure?')}');">
									<i class="icon-zoom-in "></i></g:link>)
								</td>
							</g:if>		
              				<g:else>
								<td>Aprendiz activo (<g:link action="delete" id="${aprendizInstance.id}" 
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', 
									default: 'Are you sure?')}');">
									<g:message code="Eliminar aprendiz" /></g:link>)
								</td>
							</g:else>

	                    </tr>
					</g:each>   
                </tbody>            
            </table>
        </div>
    </div>
    <!--/span-->

</div>
<!--/row-->
