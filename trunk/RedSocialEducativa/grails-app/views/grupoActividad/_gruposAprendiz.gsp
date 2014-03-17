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
                    	<th>Grupo</th>  
                    	<th>Nombre</th>
                        <th>Integrantes</th>
						<th>Acciones</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${gruposActividad}">
		           		<tr>
		           			<td>${it.numero}</td>
		           			<td class="center">${it.nombre}</td>
		                	<td class="center">
								<g:each in="${it.aprendices}" var="a">
									<p>${a.aprendiz.usuario.padron} - ${a.aprendiz.usuario.nombres} ${a.aprendiz.usuario.apellido} <small style="float: right;">${a.aprendiz.usuario.email}</small></p>
								</g:each>
	            			</td>
		                	<td class="center">          
								<g:link class="btn btn-success" action="agregarme" id="${it.id}"
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
