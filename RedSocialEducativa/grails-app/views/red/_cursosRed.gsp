<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Los cursos de la red</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Asignatura</th>
                        <th>Nro relativo</th>
                        <th>Nombre</th>
                        <th>Cuat dict</th>         
						<th>Acciones</th>                      
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${cursos}" var="cursoInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: cursoInstance, field: "asignatura")}</td>					
							<td>${fieldValue(bean: cursoInstance, field: "nroRelativo")}</td>						
							<td>${fieldValue(bean: cursoInstance, field: "nombre")}</td>
							<td>${fieldValue(bean: cursoInstance, field: "cuatDict")}</td>
							<td class="center">
                            	<g:link action="revisarRolEnCurso" params="['cursoId': cursoInstance.id]">
                            		<span class="label label-success">Acceder</span>
                            	</g:link><p>		
                        	</td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
