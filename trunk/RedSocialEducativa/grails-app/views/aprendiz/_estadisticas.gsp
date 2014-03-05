<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i>
                <span class="break"></span>Estadisticas</h2>
            <div class="box-icon">
            	<g:link action="create" params="['cursoId': params.cursoId, 
            		'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus"></i></g:link></a>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Usuario</th>                    
                        <th>Msj Enviados</th>    
                        <th>Msj Leidos</th>  
                        <th>Pub Foros</th>  
                        <th>Desc Material</th>    
                        <th>Ult Visita</th>  
                    </tr>
                </thead>
                <tbody>
                	<g:each in="${aprendizInstanceList}" var="aprendizInstance">
	                    <tr>
    	                    <td>${fieldValue(bean: aprendizInstance, field: "usuario")}</td>			
							<td class="center">${fieldValue(bean: aprendizInstance, field: "msjEnviados")}</td>
							<td class="center">${fieldValue(bean: aprendizInstance, field: "msjLeidos")}</td>		
							<td class="center">${fieldValue(bean: aprendizInstance, field: "pubForos")}</td>	
							<td class="center">${fieldValue(bean: aprendizInstance, field: "descMaterial")}</td>
							<td class="center">${fieldValue(bean: aprendizInstance, field: "ultVisita")}</td>	 
	                    </tr>
					</g:each>   
                </tbody>            
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
