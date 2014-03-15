<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluaciones del aprendiz ${aprendiz.usuario.padron}-${aprendiz.usuario} 
                	que curso el cuatrimestre ${aprendiz.cuatrimestre}</h2>
            <div class="box-icon">
            	<!-- TODO -->
                <!--<g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>-->
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Evaluacion</th>
                        <th>Fecha</th>
                        <th>Nota</th>        
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluaciones}">
	                    <tr>
    	                    <td>${it.evaluacion.nombre}</td>
        	                <td class="center">${it.evaluacion.fecha}</td>
            	            <td class="center">
            	            	<g:if test="${it.nota}">${it.nota}</g:if>
            	            	<g:else>Resultado pendiente</g:else>
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