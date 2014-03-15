<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluaciones del curso</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
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
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Horario</th>
                        <th>Aula</th>  
                        <th>Habilitada</th>
                        <th>Obligatoria</th>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluacionInstanceList}" var="evaluacionInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: evaluacionInstance, field: "nombre")}</td>
        	                <td class="center"><g:formatNumber number="${evaluacionInstance.fecha}"/></td>
            	            <td class="center"><g:formatNumber number="${evaluacionInstance.horario}"/></td>
            	            <td class="center">
            	            	<g:if test="${evaluacionInstance.aula}">${evaluacionInstance.aula}</g:if>
            	            	<g:else>No asignada</g:else>
            	            </td>
            	            <td class="center">
            	            	<g:if test="${evaluacionInstance.habilitada}">Si</g:if>
            	            	<g:else>No</g:else>
            	            </td>
            	            <td class="center">
            	            	<g:if test="${evaluacionInstance.obligatoria}">Si</g:if>
            	            	<g:else>No</g:else>
            	            </td>
	                        <td class="center">
	                        	<g:link class="btn btn-success" controller="evaluacionAprendiz" action="mostrarEvaluacion"
	                            	params="['cursoId': params.cursoId, 'evaluacionId': evaluacionInstance.id]"><i class="icon-search"></i> 
	                            </g:link>
	                            <g:link class="btn btn-info" action="edit" resource="${evaluacionInstance}" id="${evaluacionInstance.id}"
	                            	params="['cursoId': params.cursoId]">
	                                <i class="icon-edit"></i> 
	                            </g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${evaluacionInstance.id}" params="['cursoId': params.cursoId]"
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
	                                <i class="icon-trash"></i>                        
								</g:link>
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
