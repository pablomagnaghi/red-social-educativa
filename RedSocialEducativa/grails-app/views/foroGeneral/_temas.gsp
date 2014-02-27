<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>${com.fiuba.ForoGeneral.first()}</h2>
            <div class="box-icon">
                <g:link controller="publicacionGeneral" action="nueva">
                	<i class="icon-plus"></i></g:link>
            </div>
        </div>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                    <tr>
                        <th>Tema</th>
                        <th>Autor</th>
                        <th>Replicas</th>
                        <th>Ultimo mensaje</th>         			               
                    </tr>
                </thead>
                <tbody>
					<g:each in="${publicaciones}" >
						<tr>
							<td><g:link action="publicaciones" id="${it.id}">${it.titulo}</g:link></td>
							<td>${it.responsable}</td>
							<td>${it.respuestas?.size()}</td>
							<td>
								<g:if test="${it.respuestas}">
									<p>${it.respuestas.last().fecha} - ${it.respuestas.last().hora}</p>
									<p>${it.respuestas.last().responsable}</p>
								</g:if>
								<g:else>
									<p>${it.fecha} - ${it.hora}</p>
									<p>${it.responsable}</p>
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
