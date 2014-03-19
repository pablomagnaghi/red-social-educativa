<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Encuestas del curso</h2>
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
			<h2>Las encuestas respondidas son anónimas</h2>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${encuestaInstanceList}" var="encuestaInstance">
                		<g:if test="${encuestaInstance.habilitada}">
	                    	<tr>
    	                   		<td>${fieldValue(bean: encuestaInstance, field: "nombre")}</td>              
		                        <td class="center">
		                        	<g:link class="btn btn-success" action="encuestaCurso" id="${encuestaInstance.id}"params="['cursoId': params.cursoId]">
		                        		Responder</g:link>
	                        	</td>
	                    	</tr>
	                    </g:if>	
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->