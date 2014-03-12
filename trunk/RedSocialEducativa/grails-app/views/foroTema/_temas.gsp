<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>${foro}</h2>
            <div class="box-icon">
                <g:link controller="publicacionTema" action="nueva" 
					params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
        	<g:if test="${flash.message}">
				<div class="box-content alerts">
					<div class="alert alert-info"><button class="close" data-dismiss="alert" type="button"></button><strong></strong> ${flash.message}</div>
				</div>    
			</g:if>      
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Tema</th>
                        <th>Autor</th>
                        <th>Replicas</th>
                        <th>Ultimo mensaje</th> 
                        <th>Acciones</th>         		        
                    </tr>
                </thead>
                <tbody>
					<g:each in="${publicaciones}" >
						<tr>
							<td>${it.titulo}</td>
							<td class="center">${it.responsable}</td>
							<td class="center">${it.respuestas?.size()}</td>
							<td class="center">
								<g:if test="${it.respuestas}">
									<p>${it.respuestas.last().fecha} - ${it.respuestas.last().hora}</p>
									<p>${it.respuestas.last().responsable}</p>
								</g:if>
								<g:else>
									<p>${it.fecha} - ${it.hora}</p>
									<p>${it.responsable}</p>
								</g:else>
							</td>
							<td class="center">
								<span><g:link class="btn btn-success" action="publicaciones" id="${it.id}" 
									params="['cursoId': params.cursoId, 'temaId': params.temaId]">Acceder</g:link> 	
		                       	</span>
		                       	<g:if test="${mediador}">
		                           	<span><g:link class="btn btn-danger" controller="publicacionTema" action="eliminar" id="${it.id}" 
		                         	  	params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]"
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
		                                <i class="icon-trash "></i></g:link>     
		                           	</span>     
	                           	</g:if>
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
		