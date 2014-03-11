<div class="box-header" data-original-title="">
	<h2><i class="icon-table"></i><span class="break"></span>Usuario ${usuarioInstance.username}</h2>
	<div class="box-icon"><g:link action="index"><i class="icon-table"></i></g:link></div>
</div>
<div class="box-content">
	<dl>
		<dt>DNI: ${fieldValue(bean: usuarioInstance, field: "dni")}</dt>
  		<dt>Apellido: ${fieldValue(bean: usuarioInstance, field: "apellido")}</dt>
        <dt>Nombres: ${fieldValue(bean: usuarioInstance, field: "nombres")}</dt>
       	<dt>Email: ${fieldValue(bean: usuarioInstance, field: "email")}</dt>
    	<dt>Fecha solicitud: <g:formatNumber number="${usuarioInstance.fechaSolicitud}"/></dt>
       	<dt>Fecha membresia: <g:formatNumber number="${usuarioInstance.fechaMembresia}"/></dt>
         <dt>Legajo:
	  		<g:if test="${fieldValue(bean: usuarioInstance, field: "legajo")}">
				${fieldValue(bean: usuarioInstance, field: "legajo")}
			</g:if>	
			<g:else>
				No tiene
			</g:else>
      	</dt>
 		<dt>Padron: 
    		<g:if test="${fieldValue(bean: usuarioInstance, field: "padron")}">${fieldValue(bean: usuarioInstance, field: "padron")}</g:if>		
    		<g:else>No tiene</g:else>	
        </dt>
		<dt>Estado: 
			<g:if test="${usuarioInstance.enabled}"><span class="label label-success">Habilitado</span></g:if>
			<g:else><span class="label label-important">Inhabilitado</span></g:else>
		</dt>  	
	</dl>
</div>
