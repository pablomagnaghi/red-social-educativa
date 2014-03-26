<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Respuestas</h2>
        </div>
        <div class="box-content">
			<dl>
				<g:set var="cant" value="${1}" />
				<g:each in="${respuestas}" var="respuesta">
					<dd><div class="message" style="width:95%">${cant}-${respuesta.respuesta}</div></dd>
					<br>
					<g:set var="cant" value="${cant + 1}" />
				</g:each>		
			</dl>
		</div>
    </div>
    <!--/span-->
</div>
<!--/row-->
