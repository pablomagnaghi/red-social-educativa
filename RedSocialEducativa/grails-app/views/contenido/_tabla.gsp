<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Contenidos del tema ${com.cursado.Tema.get(params.temaId)}</h2>
            <g:if test="${mediador}">    
	            <div class="box-icon">
	                <g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-plus"></i></g:link>
	            </div>
            </g:if>
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
            <table class="table table-condensed table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>               
                        <th>Materiales</th>
                        <g:if test="${mediador}">
							<th>Acciones</th>        
						</g:if>    
					</tr>
				</thead>
                <tbody>
                	<g:each in="${contenidoInstanceList}" var="contenidoInstance">
	                    <tr>
    	                    <td><h3>${fieldValue(bean: contenidoInstance, field: "titulo")}</h3></td>	               
            	          	<td class="center">
            	          		<g:if test="${contenidoInstance.materiales}">
	        	                	<g:each in="${contenidoInstance.materiales}" var="m">	
										<p>${m?.encodeAsHTML()}			
											<g:if test="${mediador}">					
												<g:link style="float: right;" class="btn btn-danger" controller="materialContenido" action="delete" method="DELETE" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]"
													onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
													<i class="icon-trash "></i></g:link>
												<g:link style="float: right;" class="btn btn-info" controller="materialContenido" action="edit" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
													<i class="icon-edit "></i></g:link>
											</g:if>		
											<g:link style="float: right;" class="btn btn-success" controller="materialContenido" action="show" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
												<i class="icon-search"></i></g:link>
											<g:link style="float: right;" class="btn btn-success" controller="materialContenido" action="descargar" id="${m?.archivo?.id}" 
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
												<i class="icon-download-alt"></i></g:link></p>
									</g:each>
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	          	</td>
            	          	<g:if test="${mediador}">
		                        <td class="center">	                        
		                            <g:link class="btn btn-success" controller="materialContenido" action="create" 
		                            	params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">Agregar material</g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${contenidoInstance.id}" 
		                            	params="['cursoId': params.cursoId, 'temaId': params.temaId]"
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
		                            	<i class="icon-trash "></i></g:link>
		                        </td>
	                        </g:if>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->