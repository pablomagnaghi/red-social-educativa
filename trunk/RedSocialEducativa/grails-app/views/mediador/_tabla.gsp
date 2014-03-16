<g:if test="${params.cursoId}">
	<div class="box-content buttons">
		<p class="btn-group">
			<g:link controller="curso" action="mediador" id="${params.cursoId}" params="['cursoId': params.cursoId]">
				<button class="btn">${com.cursado.Curso.get(params.cursoId)}</button></g:link>	  		
		</p>
	</div>
</g:if>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Mediadores</h2>
            <div class="box-icon"><g:link action="create" params="['cursoId': params.cursoId]"><i class="icon-plus"></i></g:link></div>
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
                    	<th>Curso</th>
                        <th>Jerarquia</th>
                        <th>DNI</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Email</th>
                        <th>Estado</th>
						<th>Acciones</th>      
                    </tr>
                </thead>                       
                <tbody>
                	<g:each in="${mediadorInstanceList}" var="mediadorInstance">
	                    <tr>
	                    	<td>${mediadorInstance.curso.asignatura.codigo} - ${mediadorInstance.curso}</td>
            	            <td class="center">${fieldValue(bean: mediadorInstance, field: "jerarquia")}</td>
    	                    <td class="center">${mediadorInstance.usuario.dni}</td>
            	            <td class="center">${mediadorInstance.usuario.apellido}</td>
            	            <td class="center">${mediadorInstance.usuario.nombres}</td>
            	            <td class="center">${mediadorInstance.usuario.email}</td>
            	            <td class="center">
                            	<g:if test="${mediadorInstance.activo}">
                            		<span class="label label-success">Activo</span>
                            	</g:if>
                            	<g:else>
	                            	<span class="label label-important">Inactivo</span>
                            	</g:else>		
                        	</td>
	                        <td class="center">
	                       		<g:if test="${mediadorInstance.activo}">
	                       			<g:link class="btn btn-info" action="editarJerarquia" id="${mediadorInstance.id}" params="['cursoId': params.cursoId]">
	                       				<i class="icon-edit"></i> </g:link>
                            		<g:link class="btn btn-danger" action="cambiarEstado" id="${mediadorInstance.id}" params="['cursoId': params.cursoId]"
                            			onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            			<i class="icon-off"></i></g:link>
                            	</g:if>
                            	<g:else>
	                            	 <g:link class="btn btn-success" action="cambiarEstado" id="${mediadorInstance.id}" params="['cursoId': params.cursoId]"
	                            	 	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
	                            	 	<i class="icon-ok"></i></g:link>
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