<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-user"></i>
                <span class="break"></span>Estadisticas</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Padron</th>      
                        <th>Apellido</th>  
                        <th>Nombres</th>                
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
    	                    <td>${aprendizInstance.usuario.padron}</td>	
    	                    <td class="center">${aprendizInstance.usuario.apellido}</td>	
    	                    <td class="center">${aprendizInstance.usuario.nombres}</td>			
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
