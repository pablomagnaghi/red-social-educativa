<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-table"></i><span class="break"></span>Respuestas</h2>
        </div>
        <div class="box-content">
			<dl>
				<g:each in="${respuestas}" var="respuesta" status="i">
					<dd><div class="message" style="width:95%">${i}-${respuesta.respuesta}</div></dd>
					<br>
				</g:each>		
			</dl>
		</div>
    </div>
    <!--/span-->
</div>
<!--/row-->
