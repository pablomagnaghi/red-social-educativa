<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Temas del curso</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-condensed table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>
                        <th>Contenidos</th>                
                        <th>Materiales</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${temaInstanceList}" var="temaInstance">
	                    <tr>
    	                    <td><h3>${fieldValue(bean: temaInstance, field: "titulo")}</h3></td>
        	           		<td class="center">
        	           			<g:if test="${temaInstance.contenidos}">
		        	            	<g:each in="${temaInstance.contenidos}" var="c">
										<p>${c?.encodeAsHTML()}
											<g:link style="float: right;" class="btn btn-danger" controller="contenido" action="delete" method="DELETE" id="${c.id}" 
												params="['cursoId': params.cursoId, 'temaId': temaInstance.id]" 
												onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
												<i class="icon-trash "></i></g:link>
											<g:link style="float: right;" class="btn btn-info" controller="materialContenido" action="create" 
												params="['cursoId': params.cursoId, 'temaId': temaInstance.id, 'contenidoId': c.id]">
												 <i class="icon-plus">&nbsp;&nbsp;MC</i></g:link></p>	
										<g:each in="${c.materiales}" var="m">
											<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;${m?.encodeAsHTML()}								
												<g:link style="float: right;" class="btn btn-danger" controller="materialContenido" action="delete" method="DELETE" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id, 'contenidoId': c.id]"
													onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
													<i class="icon-trash "></i></g:link>
												<g:link style="float: right;" class="btn btn-info" controller="materialContenido" action="edit" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id, 'contenidoId': c.id]"><i class="icon-edit "></i></g:link>
												<g:link style="float: right;" class="btn btn-success" controller="materialContenido" action="show" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id, 'contenidoId': c.id]"><i class="icon-search"></i></g:link>
												<g:link style="float: right;" controller="materialContenido" action="show" id="${m.id}"
													params="['cursoId': params.cursoId, 'temaId': temaInstance.id, 'contenidoId': c.id]">Descargar(fix)</g:link></p>	
										</g:each>
									</g:each>	
								</g:if>
								<g:else>
									<p>No posee</p> 
								</g:else>
							</td> 	               
            	          	<td class="center">
            	          		<g:if test="${temaInstance.materiales}">
	        	                	<g:each in="${temaInstance.materiales}" var="m">
										<p>${m?.encodeAsHTML()}
											<g:link style="float: right;" class="btn btn-danger" controller="materialTema" action="delete" method="DELETE" id="${m.id}" 
												params="['cursoId': params.cursoId, 'temaId': temaInstance.id]" 
												onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
												<i class="icon-trash "></i></g:link>
											<g:link style="float: right;" class="btn btn-info" controller="materialTema" action="edit" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': temaInstance.id]"><i class="icon-edit "></i></g:link> 	
											<g:link style="float: right;" class="btn btn-success" controller="materialTema" action="show" id="${m.id}" 
												params="['cursoId': params.cursoId, 'temaId': temaInstance.id]"><i class="icon-search"></i></g:link></p>
									</g:each>  
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	          	</td>
	                        <td class="center">	                        
	                        	<g:link class="btn btn-success" controller="foroTema" action="general" 	params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">Foro</g:link>
	                            <g:link class="btn btn-info" controller="contenido" action="create" params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
	                            	<i class="icon-plus">&nbsp;&nbsp;CT</i></g:link>
	                            <g:link class="btn btn-info" controller="materialTema" action="create" params="['cursoId': params.cursoId, 'temaId': temaInstance.id]">
	                          	  <i class="icon-plus">&nbsp;&nbsp;MT</i></g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${temaInstance.id}" params="['cursoId': params.cursoId]"
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
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
