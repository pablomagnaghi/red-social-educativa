<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i>
                <span class="break"></span>Material ${materialGrupoActividadInstance}</h2>
        </div>
        <div class="box-content">
			<dl>
            	<dt>Titulo: ${fieldValue(bean: materialGrupoActividadInstance, field: "titulo")}</dt>
                <dt>Categoria: ${fieldValue(bean: materialGrupoActividadInstance, field: "categoria")}</dt>
                <dt>Responsable: ${fieldValue(bean: materialGrupoActividadInstance, field: "responsable")}</dt>
                <dt>Fecha: ${fieldValue(bean: materialGrupoActividadInstance, field: "fecha")}</dt>
                <dt>Autor: ${fieldValue(bean: materialGrupoActividadInstance, field: "autor")}</dt>
                <dt>Descripcion: </dt>
                	<dd>${fieldValue(bean: materialGrupoActividadInstance, field: "descripcion")}</dd>        	
			</dl>
        </div>
    </div>
    <!--/span-->
</div>
<!--/row-->
