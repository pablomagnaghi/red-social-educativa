<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Evaluaciones de ${usuario.padron} - ${usuario.nombres} ${usuario.apellido}</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Fecha</th>
                        <th>Nota</th>         
					</tr>
				</thead>
                <tbody>
                	<g:each in="${evaluacionesAprendiz}">
		         		<tr>
	    	     			<td>${it.evaluacion.nombre}</td>
	  						<td class="center">${it.evaluacion.fecha}</td>
	           				<td class="center">
	           					<g:if test="${it.nota}">${it.nota}</g:if>
	           					<g:else>Nota pendiente</g:else>
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
