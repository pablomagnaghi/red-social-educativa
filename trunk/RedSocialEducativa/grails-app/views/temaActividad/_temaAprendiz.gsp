<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material de la actividad</h2>
        </div>
        <div class="box-content">
        	PONER LOS DATOS DEL TEMA A DEFINIR EL VIERNES
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Titulo</th>   
						<th>Acciones</th>            
					</tr>
				</thead>
                <tbody>
                	<g:each in="${temas}">
	                    <tr>
    	                    <td>${it.tema.titulo}</td>
	                        <td class="center">
								PONER LINK DE DESCARGA
	                        </td>
	                    </tr>
					</g:each>   
                </tbody>
            </table>
        </div>
    </div>
    <!--/span-->
</div>
<!--/ro
