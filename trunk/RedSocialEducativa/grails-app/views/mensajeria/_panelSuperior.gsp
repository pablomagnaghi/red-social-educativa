<div class="inbox-nav-bar no-content-padding" style="height: 80px">
	<span class="span2">
		<h1 class="page-title txt-color-blueDark hidden-tablet">
			<i class="fa fa-fw fa-inbox"></i> Inbox &nbsp;
		</h1>
	</span>
	<span class="span2">
		<div class="btn-group">
			<g:link class="btn btn-default" data-placement="bottom" title=""
				rel="tooltip" action="principal" controller="red">
				<strong><i class="icon-home"></i></strong>
			</g:link>
			<a class="btn btn-default" data-placement="bottom" title=""
				rel="tooltip" onclick="volver()"><strong>
				<i class="icon-arrow-left"></i></strong></a> 
			
			<g:link	class="deletebutton btn btn-default" data-placement="bottom"
				title="" rel="tooltip" action="mostrarMensajes" controller="mensajeria"
				params="['nombreCarpeta': 'Eliminados']"
				onclick="borrarConversacion()"><strong><i
					class="fa fa-trash-o fa-lg"></i></strong>
				</g:link>
		</div>
	</span>
	
	<span class="span8"> 
	<form action="buscar_mensajes">
		<label style="display:inline"> <i class="icon-prepend fa fa-user"></i> 
			<input style="height: 28px" type="text" id="deBuscar" name="de" placeholder="De:">
		</label> 
		<input type="hidden" name="nombreCarpeta" value="${nombreCarpeta}">
		
		<label style="display:inline"> <i class="icon-prepend fa fa-user"></i>
			<input style="height: 28px" type="text" id="paraBuscar" name="para" placeholder="Para:">
		</label>
		
		<button class="btn btn-default" type="submit" style="display:inline">
			<span class="glyphicon glyphicon-search"></span> Buscar
		</button>
	</form>
	</span> 
	
	

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

</div>