<div class="table-wrap custom-scroll animated fast fadeInRight" style="height: 950px; opacity: 1; margin-left: 0px;"><h2 class="email-open-header">
	Redactar Mensaje
</h2>

<form id="email-compose-form" class="form-horizontal" method="POST" action="enviarMensajes" onsubmit="submitMail()" enctype="multipart/form-data">

	<div class="inbox-info-bar no-padding">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-1"><strong>Para</strong></label>
					<div class="col-md-9">
						<input type='hidden' id="e6" style="width: 700px;" />
					</div>
					<div class="col-md-2">
						<g:img file="Treeview.gif" id="img_clickeable"
								style="cursor: pointer;width: 21px;float: right" />
					</div>
				</div>
		</div>	
	</div>
	
	<div class="inbox-info-bar no-padding">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-1"><strong>Asunto</strong></label>
				<div class="col-md-11">
				<g:if test="${asunto != null }">
					<input type="text" name="asunto" id="asunto" placeholder="Asunto del mensaje" class="form-control" value="${asunto }">
				</g:if>
				<g:else>
					<input type="text" name="asunto" id="asunto" placeholder="Asunto del mensaje" class="form-control">
				</g:else>					
				</div>
			</div>
		</div>	
	</div>

	
	<div class="inbox-message no-padding">
		<div class="note-editor">
			<g:if test="${cuerpo != null }">
				<textarea class="note-codeable" id="cuerpo" name="mensaje"></textarea>
				<div contenteditable="true" id="divCuerpo" class="note-editable" style="height: 270px;">${cuerpo }</div>
			</g:if>
			<g:else>
				<textarea class="note-codeable" id="cuerpo" name="mensaje"></textarea>
				<div contenteditable="true" id="divCuerpo" class="note-editable" style="height: 270px;"></div>
			</g:else>
		</div>
	</div>
	
	<div class="inbox-compose-footer" style="margin-right: 0px; width: 820px">

	<button type="button" class="btn btn-info" onclick="agregarMensajeABorradores()">
		Borradores
	</button>

	<button id="send" type="submit" class="btn btn-primary pull-right" data-loading-text="&lt;i class='fa fa-refresh fa-spin'&gt;&lt;/i&gt; &nbsp; Sending...">
		Enviar <i class="fa fa-arrow-circle-right fa-lg"></i>
	</button>

	</div>

</form>
</div>

<div id="organigrama" class="tree center_div"  style="display:none; margin-top: 0px; top: 0px;">
  	<g:img file="cross-icon.png" id="cerrarOrganigrama" style="float:right; cursor: pointer" />
    <ul style="margin-top: 16px;">
        <li>
            <span><i class="icon-calendar"></i> Cursos Aprendiz</span>
                        <ul>
            	<g:each in="${cursosAprendiz}" var="cursoAprendiz">
	            		<li>
	                	<span class="badge badge-success"><i class="icon-minus-sign"></i> ${cursoAprendiz.nombre }</span>
	                    <ul>
	                    	<g:if test="${!datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA").empty}">
		                        <li>
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Mediadores</span>
			                        <g:each in="${datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA")}" var="mediador">
			                        	<ul>
					                        <li>
						                        <a href="">${mediador.usuario.nombres }</a>
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    	<g:if test="${!datosCursosAprendiz.get(cursoAprendiz.id +"-gruposA").empty}">
		                        <li>
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Grupos</span>
									<g:each in="${datosCursosAprendiz.get(cursoAprendiz.id +"-gruposA")}" var="grupo">
			                        	<ul>
					                        <li>
						                        <a href="">${grupo.nombre} ${grupo.numero }</a>
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    </ul>
	                </li>
            	</g:each>
		    </ul>
        </li>
        <li>
            <span><i class="icon-calendar"></i> Cursos Mediador</span>
            <ul>
            	<g:each in="${cursosMediador}" var="cursoMediador">
	            		<li>
	                	<span class="badge badge-success"><i class="icon-minus-sign"></i> ${cursoMediador.nombre }</span>
	                    <ul>
	                    	<g:if test="${!datosCursosMediador.get(cursoMediador.id +"-mediadoresM").empty}">
		                        <li>
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Mediadores</span>
			                        <g:each in="${datosCursosMediador.get(cursoMediador.id +"-mediadoresM")}" var="mediador">
			                        	<ul>
					                        <li>
						                        <a href="">${mediador.usuario.nombres }</a>
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    	<g:if test="${!datosCursosMediador.get(cursoMediador.id +"-gruposM").empty}">
		                        <li>
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Grupos</span>
									<g:each in="${datosCursosMediador.get(cursoMediador.id +"-gruposM")}" var="grupo">
			                        	<ul>
					                        <li>
						                        <a href="">${grupo.nombre} ${grupo.numero }</a>
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    </ul>
	                </li>
            	</g:each>
		    </ul>
        </li>
    </ul>



<!--  
  	<div>Cursos de aprendiz</div>
  	<g:each in='${cursosAprendiz}' var='cursoAprendiz'>
  		<div>
  		<img src="../images/tree_plus.gif" class="link-aprendiz" id="${cursoAprendiz.id}" style="cursor: pointer"/>
  		<span class="seleccionableOrg" style="margin-left: 10px;" id="curso-${cursoAprendiz.id}">${cursoAprendiz.nombre} - ${cursoAprendiz.materia.codigo}</span>
  		<div id="aprendiz-${cursoAprendiz.id}"></div>
  		</div>
  	</g:each>
-->
  </div>
  ${usuariosFormateados }
	<div id="spinner" class="center_div"
		style="display: none; height: 16px; width: 16px;">
		<g:img file="spinner.gif" />
	</div>