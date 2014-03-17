<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Grupos de la actividad</h2>
        	<div class="box-icon">
                <g:link action="crearGrupo" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
                	<i class="icon-plus"></i></g:link>
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
                    	<th>Numero de grupo</th>  
						<th>Nombre de grupo</th>  
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombres</th> 
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${grupoActividadAprendices}">
		           		<tr>
		           			<td>${it.grupo.numero}</td>
	            	    	<td class="center">${it.grupo.nombre}-id:${it.grupo.id}</td>
	    	             	<td class="center">${it.aprendiz.usuario.padron}-id:${it.aprendiz.id}</td>
	        	         	<td class="center">${it.aprendiz.usuario.apellido}</td>
	            			<td class="center">${it.aprendiz.usuario.nombres}</td>
		                	<td class="center">          
								<g:link class="btn btn-success" action="agregarme" id="${it.grupo.id}"
									params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId, 'actividadId': params.actividadId]">
									Agregarme
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
