<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Actividades del aprendiz ${aprendiz.usuario.padron}-${aprendiz.usuario} 
                	durante el cuatrimestre ${aprendiz.cuatrimestre}</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Actividad</th>
                        <th>Grupo</th>
                        <th>Calificación</th>        
					</tr>
				</thead>
                <tbody>
                	<g:each in="${gruposActividadAprendiz}">
	                    <tr>
    	                    <td>${it.grupo.actividad}</td>
        	                <td class="center">${it.grupo}</td>
            	            <td class="center">
            	            	<g:if test="${it.grupo.actividad.evaluable}">
		           					<g:if test="${it.calificado}">
		            	            	<g:if test="${it.nota}">${it.nota}</g:if>
		            	            	<g:else>0.00</g:else>
		            	            </g:if>	
	            	            	<g:else>Resultado pendiente</g:else>
            	            	</g:if>
            	            	<g:else>
            	            		<g:if test="${it.cumplio}">
		            	    			<span class="label label-success">Cumplio</span>
		            	    		</g:if>
		            	    		<g:else>
			            	    		<span class="label label-important">No cumplio</span>
		            	    		</g:else>
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