<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Temas del curso</h2>
            <g:if test="${mediador}">
            	<div class="box-icon"><g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link></div>
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
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${temaInstanceList}" var="temaInstance">
	                    <tr>
    	                    <td><h3>${fieldValue(bean: temaInstance, field: "titulo")}</h3></td>         
            	          	<td class="center">
            	          		<g:if test="${temaInstance.materiales}">
	        	                	<g:each in="${temaInstance.materiales}" var="m">
										<p>${m?.encodeAsHTML()}
											<g:if test="${mediador}">
												<g:link style="float: right;" class="btn btn-danger" controller="materialTema" action="delete" method="DELETE" id="${m.id}" 
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id]" 
													onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
													<i class="icon-trash "></i></g:link>
												<g:link style="float: right;" class="btn btn-info" controller="materialTema" action="edit" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id]"><i class="icon-edit "></i></g:link> 	
											</g:if>
											<g:if test="${mediador || aprendiz}">
												<g:link style="float: right;" class="btn btn-success" controller="materialTema" action="show" id="${m.id}" 
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id]"><i class="icon-search"></i></g:link>
												<g:link style="float: right;" class="btn btn-success" controller="materialTema" action="descargar" id="${m.idArchivo}" 
													params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-download-alt"></i></g:link>
											</g:if>
										</p>
									</g:each>  
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	          	</td>
	                        <td class="center">	                        
	                        	<g:link class="btn btn-success" controller="foroTema" action="general" 
	                        		params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Foro</g:link>
	                        	<g:if test="${mediador || aprendiz}">	
		                            <g:link class="btn btn-success" controller="contenido" action="index" 
		                            	params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Contenidos</g:link>
	                            </g:if>	
	                            <g:if test="${mediador}">
		                            <g:link class="btn btn-info" controller="materialTema" action="create" params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
		                          	  Agregar material</g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${temaInstance.id}" params="['cursoId': params.cursoId]"
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
		                            	<i class="icon-trash "></i></g:link>
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
