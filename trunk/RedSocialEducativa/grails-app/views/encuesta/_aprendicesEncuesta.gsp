<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Aprendices que respondieron la encuesta</h2>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Padron</th>
                        <th>Apellido</th>
                        <th>Nombres</th>
                        <th>Email</th>
                        <th>Cuatrimestre de cursado</th>
                        <th>Fecha</th>      
					</tr>
				</thead>
                <tbody>
                	<g:each in="${encuestasAprendiz}" var="encuestaAprendiz">
	                    <tr>
    	                    <td>${encuestaAprendiz.aprendiz.usuario.padron}</td>
    	                    <td class="center">${encuestaAprendiz.aprendiz.usuario.apellido}</td>
    	                    <td class="center">${encuestaAprendiz.aprendiz.usuario.nombres}</td>
    	                    <td class="center">${encuestaAprendiz.aprendiz.usuario.email}</td>
    	                    <td class="center">${encuestaAprendiz.aprendiz.cuatrimestre}</td>
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