<div class="inbox-nav-bar no-content-padding">

	<h1 class="page-title txt-color-blueDark hidden-tablet">
		<i class="fa fa-fw fa-inbox"></i> Inbox &nbsp;
	</h1>

	<div class="inbox-checkbox-triggered">

		<div class="btn-group">
			<g:link class="btn btn-default" data-placement="bottom" title=""
				rel="tooltip" action="principal" controller="red">
				<strong><i class="glyphicon glyphicon-home"></i></strong>
			</g:link>
			<a class="btn btn-default" data-placement="bottom" title=""
				rel="tooltip" onclick="volver()"><strong><i
					class="glyphicon glyphicon-arrow-left"></i></strong></a> 
			
			<g:link	class="deletebutton btn btn-default" data-placement="bottom"
				title="" rel="tooltip" action="mostrarMensajes" controller="mensajeria"
				params="['nombreCarpeta': 'Eliminados']"
				onclick="borrarConversacion()"><strong><i
					class="fa fa-trash-o fa-lg"></i></strong>
				</g:link>
		</div>

	</div>

	<div class="pagination btn-group pull-right inbox-paging" style="margin-left: 10px; right: 20px; margin-top: 0px;">
		<strong> ${offset+1}-${offset + params.max }
		</strong> de <strong> <g:if test="${conversacionCount != null }">
					${conversacionCount }
				</g:if> <g:else>
					${mensajesCount}
				</g:else>
		</strong>
		<g:if test="${conversacionCount != null }">
			<g:if test="${deBusqueda != null && paraBusqueda != null}">
				<g:paginate prev="" next="" total="${conversacionCount ?: 0}"
					params="['nombreCarpeta' : nombreCarpeta, 'de' : deBusqueda, 'para': paraBusqueda]" />
			</g:if>
			<g:else>
				<g:if test="${paraBusqueda != null }">
					<g:paginate prev="" next="" total="${conversacionCount ?: 0}"
						params="['nombreCarpeta' : nombreCarpeta, 'para': paraBusqueda]" />	
				</g:if>
				<g:else>
					<g:if test="${deBusqueda != null }">
						<g:paginate prev="" next="" total="${conversacionCount ?: 0}"
							params="['nombreCarpeta' : nombreCarpeta, 'de' : deBusqueda]" />
					</g:if>
					<g:else>
						<g:paginate prev="" next="" total="${conversacionCount ?: 0}"
						params="['nombreCarpeta' : nombreCarpeta]" />
					</g:else>
				</g:else>
			</g:else>
		</g:if>
		<g:else>
			<g:paginate prev="" next="" total="${mensajesCount ?: 0}"
				params="['nombreCarpeta' : nombreCarpeta]" />
		</g:else>
	</div>
	<form action="buscar_mensajes" style="display: inline;">
	<span> 
		<label class="input"> <i class="icon-prepend fa fa-user"></i> 
			<input type="text" id="deBuscar" name="de" placeholder="De:">
		</label> 
		<input type="hidden" name="nombreCarpeta" value="${nombreCarpeta}">
		<label class="input"> <i class="icon-prepend fa fa-user"></i>
			<input type="text" id="paraBuscar" name="para" placeholder="Para:">
		</label>
		<button class="btn btn-default" type="submit">
			<span class="glyphicon glyphicon-search"></span> Buscar
		</button>
	</span> 
	</form>

</div>