<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Encuestas del curso</h2>
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
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Habilitada</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${encuestaInstanceList}" var="encuestaInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: encuestaInstance, field: "nombre")}</td>
        	                <td class="center">
        	                	<g:if test="${encuestaInstance.habilitada}">Habilitada</g:if>
        	                	<g:else>Inhabilitada</g:else>
	                        <td class="center">
	                        	<g:link class="btn btn-success" action="descargar" id="${encuestaInstance?.id}" params="['cursoId': params.cursoId]">
	                        		AGREGAR CHOICE</g:link>
	                        	<g:link class="btn btn-success" action="descargar" id="${encuestaInstance?.id}" params="['cursoId': params.cursoId]">
	                        		AGREGAR DESARROLLAR</g:link>
	                        	<g:link class="btn btn-success" action="descargar" id="${encuestaInstance?.id}" params="['cursoId': params.cursoId]">
	                        		AGREGAR PUNTAJER</g:link>
	                        	<g:link class="btn btn-success" action="show" id="${encuestaInstance.id}"params="['cursoId': params.cursoId]">
	                        		<i class="icon-search "></i></g:link>
	                            <g:if test="${mediador}">
		                            <g:link class="btn btn-info" action="edit" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]">
		                            	<i class="icon-edit "></i></g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${encuestaInstance.id}" params="['cursoId': params.cursoId]"
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
		                                <i class="icon-trash"></i></g:link>
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