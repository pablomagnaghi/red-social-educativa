<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>${aprendiz.usuario.padron}-${aprendiz.usuario}</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Evaluacion</th>
                        <th>Fecha</th>
                        <th>Nota</th>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluaciones}">
	                    <tr>
    	                    <td>${it.evaluacion.nombre}</td>
        	                <td class="center">${it.evaluacion.fecha}</td>
            	            <td class="center">${it.nota}</td>
	                        <td class="center">
	                            <g:link class="btn btn-success" action="calificar" id="${it.id}" 
									params="['cursoId': params.cursoId, 'evaluacionId': it.evaluacion.id, 'aprendizId': aprendiz.id]">Calificar</g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${it.id}" 
	                            	params="['cursoId': params.cursoId, 'evaluacionId': it.evaluacion.id]"
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