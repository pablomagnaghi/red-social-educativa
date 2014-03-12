<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Contenidos del tema</h2>
            <div class="box-icon">
                <g:link action="create" params="['cursoId': params.cursoId, 'temaId': params.temaId]"><i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-condensed table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>               
                        <th>Materiales</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${contenidoInstanceList}" var="contenidoInstance">
	                    <tr>
    	                    <td><h3>${fieldValue(bean: contenidoInstance, field: "titulo")}</h3></td>	               
            	          	<td class="center">
            	          		<g:if test="${contenidoInstance.materiales}">
	        	                	<g:each in="${contenidoInstance.materiales}" var="m">	
										<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;${m?.encodeAsHTML()}								
											<g:link style="float: right;" class="btn btn-danger" controller="materialContenido" action="delete" method="DELETE" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]"
												onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
												<i class="icon-trash "></i></g:link>
											<g:link style="float: right;" class="btn btn-info" controller="materialContenido" action="edit" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
												<i class="icon-edit "></i></g:link>
											<g:link style="float: right;" class="btn btn-success" controller="materialContenido" action="show" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
												<i class="icon-search"></i></g:link>
											<g:link style="float: right;" controller="materialContenido" action="show" id="${m.id}"
												params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">Descargar(fix)</g:link></p>	
									</g:each>
								</g:if>
								<g:else>
									<p>No posee</p>
								</g:else>	
            	          	</td>
	                        <td class="center">	                        
	                            <g:link class="btn btn-info" controller="materialContenido" action="create" 
	                            	params="['cursoId': params.cursoId, 'temaId': params.temaId, 'contenidoId': contenidoInstance.id]">
	                          	  <i class="icon-plus">&nbsp;&nbsp;MC</i></g:link>
	                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${contenidoInstance.id}" 
	                            	params="['cursoId': params.cursoId, 'temaId': params.temaId]"
	                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >
	                            	<i class="icon-trash "></i></g:link>
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