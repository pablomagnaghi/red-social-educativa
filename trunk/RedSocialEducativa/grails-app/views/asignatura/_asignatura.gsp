<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Asignatura ${asignaturaInstance}</h2>
            <div class="box-icon">
            	<g:link action="create"><i class="icon-plus"></i></g:link>
           		<g:link action="edit" id="${asignaturaInstance.id}"><i class="icon-edit"></i></g:link>
            	<g:link action="delete" method="DELETE" id="${asignaturaInstance.id}" 
	        	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" ><i class="icon-trash "></i></g:link>
            </div>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Codigo: ${fieldValue(bean: asignaturaInstance, field: "codigo")}</dt>
                <dt>Nombre: ${fieldValue(bean: asignaturaInstance, field: "nombre")}</dt>
                <dt>Creditos: ${fieldValue(bean: asignaturaInstance, field: "creditos")}</dt>
                <dt>Contenidos Minimos: ${fieldValue(bean: asignaturaInstance, field: "contenidosMinimos")}</dt>
                <dt>Cursos:</dt>
	                <g:each in="${asignaturaInstance.cursos}" var="c">
						<dd>${c}</dd>
					</g:each>		
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->