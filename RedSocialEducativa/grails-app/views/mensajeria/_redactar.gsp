<div class="table-wrap custom-scroll animated fast fadeInRight" style="height: 950px; opacity: 1; margin-left: 0px;"><h2 class="email-open-header">
	Redactar Mensaje
</h2>

<form id="email-compose-form" class="form-horizontal" method="POST" action="enviarMensajes" onsubmit="submitMail()" enctype="multipart/form-data">

	<div class="inbox-info-bar no-padding">
		<div class="row">
			<div class="form-group">
				<label class="control-label col-md-1"><strong>Para</strong></label>
					<div class="col-md-9">
						<input type='hidden' id="e6" style="width: 700px;" name="para" />
					</div>
					<div class="col-md-2" style="top: 7px;">
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
	<header role="heading">
					<div role="menu" class="jarviswidget-ctrls">  
					 <a data-original-title="Delete" href="javascript:void(0);" id="cerrarOrganigrama" class="button-icon jarviswidget-delete-btn" rel="tooltip" title="" data-placement="bottom">
					  <i class="fa fa-times"></i>
					 </a>
					</div>
					
					<h2 style="margin-top: 0px; margin-bottom: 0px;">Seleccionar Destinatario</h2>				
						
					<span style="display: none;" class="jarviswidget-loader">
						<i class="fa fa-refresh fa-spin"></i>
					</span>
	</header>
    <ul style="margin-top: 16px;">
        <li>
            <span><i class="icon-calendar"></i> Cursos Aprendiz</span>
                        <ul>
            	<g:each in="${cursosAprendiz}" var="cursoAprendiz">
	            		<li style="display:none">
	                	<span class="badge badge-success"><i class="icon-minus-sign"></i> ${cursoAprendiz.nombre }</span> <input type="checkbox" id="checkboxCA${cursoAprendiz.id }" onchange="agregarCurso('checkboxCA', '${cursoAprendiz.nombre }', '${cursoAprendiz.id }')"> 
	                    <ul>
	                    	<g:if test="${!datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA").empty}">
		                        <li style="display:none">
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Mediadores</span>
			                        <g:each in="${datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA")}" var="mediador">
			                        	<ul>
					                        <li>
						                        ${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido }  <input type="checkbox" id="checkBoxA${mediador.id }" onchange="agregarMediador('checkBoxA', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    	<g:if test="${!datosCursosAprendiz.get(cursoAprendiz.id +"-gruposA").empty}">
		                        <li style="display:none">
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Grupos</span>
									<g:each in="${datosCursosAprendiz.get(cursoAprendiz.id +"-gruposA")}" var="grupo">
			                        	<ul>
					                        <li>
						                        ${grupo.nombre} <input type="checkbox" id="checkBoxGA${grupo.id }" onchange="agregarGrupo('checkBoxGA', '${grupo.id }', '${grupo.nombre }', '${cursoAprendiz.nombre}','${cursoAprendiz.id}')">
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
	            		<li style="display:none">
	                	<span class="badge badge-success"><i class="icon-minus-sign"></i> ${cursoMediador.nombre }</span>  <input type="checkbox" id="checkboxCM${cursoMediador.id}" onchange="agregarCurso('checkboxCM', '${cursoMediador.nombre }', '${cursoMediador.id }')">
	                    <ul>
	                    	<g:if test="${!datosCursosMediador.get(cursoMediador.id +"-mediadoresM").empty}">
		                        <li style="display:none">
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Mediadores</span>
			                        <g:each in="${datosCursosMediador.get(cursoMediador.id +"-mediadoresM")}" var="mediador">
			                        	<ul>
					                        <li style="display:none">
						                        ${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido }  <input id="checkBoxM${mediador.id }" type="checkbox" onchange="agregarMediador('checkBoxM', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    	<g:if test="${!datosCursosMediador.get(cursoMediador.id +"-gruposM").empty}">
		                        <li style="display:none">
			                        <span class="badge badge-success"><i class="icon-minus-sign"></i> Grupos</span>
									<g:each in="${datosCursosMediador.get(cursoMediador.id +"-gruposM")}" var="grupo">
			                        	<ul>
					                        <li style="display:none">
						                        ${grupo.nombre} <input type="checkbox" id="checkBoxGM${grupo.id }" onchange="agregarGrupo('checkBoxGM', '${grupo.id }', '${grupo.nombre }', '${cursoMediador.nombre}','${cursoMediador.id}')">
					                        </li>
					                    </ul>
			                        </g:each>
		                        </li>
	                    	</g:if>
	                    </ul>
	                </li>
            	</g:each>
		    </ul>
		    <g:if test="${!cursosTotales.empty }">
		    <li>
	        	<span><i class="icon-calendar"></i>Mediadores</span>
	            <ul>
	            	<g:each in="${cursosTotales}" var="curso">
		            	<li style="display:none">
		            	 	<span class="badge badge-success"><i class="icon-minus-sign"></i> ${curso.materia} - ${curso.nombre }</span>
							<g:each in="${datosMediadores.get(curso.id +"-mediadoresC")}" var="mediador">
	                        	<ul>
			                        <li style="display:none">
				                       ${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido } <input type="checkbox" id="checkBoxMM${mediador.id }" onchange="agregarMediador('checkBoxMM', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
			                        </li>
			                    </ul>
	                        </g:each>
		                </li>
	            	</g:each>
			    </ul>
			</li>
        	</g:if>
        </li>
    </ul>

  </div>
  ${usuariosFormateados }
	<div id="spinner" class="center_div"
		style="display: none; height: 16px; width: 16px;">
		<g:img file="spinner.gif" />
	</div>