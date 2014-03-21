<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Encuestas del aprendiz ${aprendiz} que curso el cuatrimestre ${aprendiz.cuatrimestre}</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Encuesta</th>
                        <th>Fecha</th>      
					</tr>
				</thead>
                <tbody>
                	<g:each in="${encuestasAprendiz}" var="encuestaAprendiz">
	                    <tr>
    	                    <td>${encuestaAprendiz.encuesta.nombre}</td>
        	                <td class="center">${encuestaAprendiz.fecha}</td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
