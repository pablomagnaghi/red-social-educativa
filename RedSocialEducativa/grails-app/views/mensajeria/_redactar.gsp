<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-edit"></i>Redactar Mensaje
		</h2>
	</div>
	<div class="box-content">
		<form id="email-compose-form" class="form-horizontal" method="POST"
			action="enviarMensajes" onsubmit="return submitMail();"
			enctype="multipart/form-data">
			<fieldset>
				<div class="control-group" style="margin-bottom: 0px;">
					<label for="typeahead" class="control-label">Para </label>
					<div class="controls">
						<g:img file="Treeview.gif" id="img_clickeable"
							style="cursor: pointer;width: 21px;float: right;margin-right: 65px; margin-top: 6px;" />
						<g:if test="${para != null }">
							<input type='hidden' id="e6" name="para" value="${para }"
								style="margin-bottom: 11px;" />
						</g:if>
						<g:else>
							<input type='hidden' id="e6" name="para"
								style="margin-bottom: 11px;" />
						</g:else>

					</div>
				</div>
				<div class="control-group" style="margin-bottom: 13px;">
					<label for="date01" class="control-label">Asunto</label>
					<div class="controls">
						<g:if test="${asunto != null }">
							<input type="text" name="asunto" id="asunto"
								placeholder="Asunto del mensaje" class="span10"
								style="color: #685555 !important" "
								value="${asunto }">
						</g:if>
						<g:else>
							<input type="text" name="asunto" id="asunto"
								placeholder="Asunto del mensaje" class="span10"
								style="color: #685555 !important">
						</g:else>

					</div>
				</div>

				<div class="control-group hidden-phone">
					<label for="textarea2" class="control-label">Texto </label>
					<div class="controls">
						<div class="cleditorMain" style="width: 500px; height: 250px;">
							<g:if test="${cuerpo != null }">
								<textarea rows="3" id="cuerpo" class="cleditor" id="cuerpo"
									name="mensaje"
									style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 73px; display: none; width: 500px;"></textarea>
								<div contenteditable="true" id="divCuerpo" class="note-editable"
									style="height: 250px;">
									${cuerpo }
								</div>
							</g:if>
							<g:else>
								<textarea rows="3" id="cuerpo" class="cleditor" id="mensaje"
									name="mensaje"
									style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 73px; display: none; width: 500px;"></textarea>
								<div contenteditable="true" id="divCuerpo" class="note-editable"
									style="height: 250px;"></div>
							</g:else>

						</div>
					</div>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit">Enviar</button>
					<button class="btn" type="reset"
						onclick="agregarMensajeABorradores()">Borradores</button>
				</div>
			</fieldset>
		</form>

	</div>
</div>


<div id="organigrama" class="tree center_div"
	style="display: none; margin-top: 0px; top: 0px;">
	<header role="heading">
		<div role="menu" class="jarviswidget-ctrls" style="float: right;">
			<a data-original-title="Delete" href="javascript:void(0);"
				id="cerrarOrganigrama" class="button-icon jarviswidget-delete-btn"
				rel="tooltip" title="" data-placement="bottom"> <i
				class="fa fa-times"></i>
			</a>
		</div>

		<h2 style="margin-top: 0px; margin-bottom: 0px;">Seleccionar
			Destinatario</h2>

		<span style="display: none;" class="jarviswidget-loader"> <i
			class="fa fa-refresh fa-spin"></i>
		</span>
	</header>
	<ul style="margin-top: 16px;">
		<li><span><i class="icon-calendar"></i> Cursos Aprendiz</span>
			<ul>
				<g:each in="${cursosAprendiz}" var="cursoAprendiz">
					<li style="display: none"><span class="badge badge-success"><i
							class="icon-minus-sign"></i> ${cursoAprendiz.nombre }</span> <input
						type="checkbox" id="checkboxCA${cursoAprendiz.id }"
						onchange="agregarCurso('checkboxCA', '${cursoAprendiz.nombre }', '${cursoAprendiz.id }')">
						<ul>
							<g:if
								test="${!datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA").empty}">
								<li style="display: none"><span class="badge badge-success"><i
										class="icon-minus-sign"></i> Mediadores</span> <g:each
										in="${datosCursosAprendiz.get(cursoAprendiz.id +"-mediadoresA")}"
										var="mediador">
										<ul>
											<li>
												${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido }
												<input type="checkbox" id="checkBoxA${mediador.id }"
												onchange="agregarMediador('checkBoxA', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
											</li>
										</ul>
									</g:each></li>
							</g:if>
							<g:if
								test="${datosCursosAprendiz.get(cursoAprendiz.id +"-cuatrimestreA") != null && 
								!datosCursosAprendiz.get(cursoAprendiz.id +"-cuatrimestreA").actividades.empty}">
								<li style="display: none">
									<span class="badge badge-success"><i class="icon-minus-sign"></i> Actividades</span> 
										<g:each	in="${datosCursosAprendiz.get(cursoAprendiz.id +"-cuatrimestreA").actividades}"
										var="actividad">
										<ul>
											<li style="display: none"><span
												class="badge badge-success"><i
													class="icon-minus-sign"></i> ${actividad.titulo}</span> <g:each
													in="${actividad.grupos}" var="grupo">
													<ul>
														<li style="display: none">
															${grupo.nombre} <input type="checkbox"
															id="checkBoxGA${grupo.id }"
															onchange="agregarGrupo('checkBoxGM', '${grupo.id }', '${grupo.nombre }', '${cursoAprendiz.nombre}','${cursoAprendiz.id}')">
														</li>
													</ul>
												</g:each></li>
										</ul>
									</g:each></li>
							</g:if>
						</ul></li>
				</g:each>
			</ul></li>
		<li><span><i class="icon-calendar"></i> Cursos Mediador</span>
			<ul>
				<g:each in="${cursosMediador}" var="cursoMediador">
					<li style="display: none"><span class="badge badge-success"><i
							class="icon-minus-sign"></i> ${cursoMediador.nombre }</span> <input
						type="checkbox" id="checkboxCM${cursoMediador.id}"
						onchange="agregarCurso('checkboxCM', '${cursoMediador.nombre }', '${cursoMediador.id }')">
						<ul>
							<g:if
								test="${!datosCursosMediador.get(cursoMediador.id +"-mediadoresM").empty}">
								<li style="display: none"><span class="badge badge-success"><i
										class="icon-minus-sign"></i> Mediadores</span> <g:each
										in="${datosCursosMediador.get(cursoMediador.id +"-mediadoresM")}"
										var="mediador">
										<ul>
											<li style="display: none">
												${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido }
												<input id="checkBoxM${mediador.id }" type="checkbox"
												onchange="agregarMediador('checkBoxM', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
											</li>
										</ul>
									</g:each></li>
							</g:if>
							<g:if
								test="${datosCursosMediador.get(cursoMediador.id +"-cuatrimestreM") != null &&
								!datosCursosMediador.get(cursoMediador.id +"-cuatrimestreM").actividades.empty}">
								<li style="display: none"><span class="badge badge-success"><i
										class="icon-minus-sign"></i> Actividades</span> <g:each
										in="${datosCursosMediador.get(cursoMediador.id +"-cuatrimestreM").actividades}"
										var="actividad">
										<ul>
											<li style="display: none"><span
												class="badge badge-success"><i
													class="icon-minus-sign"></i> ${actividad.titulo}</span> <g:each
													in="${actividad.grupos}" var="grupo">
													<ul>
														<li style="display: none">
															${grupo.nombre} <input type="checkbox"
															id="checkBoxGM${grupo.id }"
															onchange="agregarGrupo('checkBoxGM', '${grupo.id }', '${grupo.nombre }', '${cursoMediador.nombre}','${cursoMediador.id}')">
														</li>
													</ul>
												</g:each></li>
										</ul>
									</g:each></li>
							</g:if>
						</ul></li>
				</g:each>
			</ul> <g:if test="${!cursosTotales.empty }">
				<li><span><i class="icon-calendar"></i>Mediadores</span>
					<ul>
						<g:each in="${cursosTotales}" var="curso">
							<li style="display: none"><span class="badge badge-success"><i
									class="icon-minus-sign"></i> ${curso.materia} - ${curso.nombre }</span> <g:each
									in="${datosMediadores.get(curso.id +"-mediadoresC")}"
									var="mediador">
									<ul>
										<li style="display: none">
											${mediador.jerarquia } - ${mediador.usuario.nombres } ${mediador.usuario.apellido }
											<input type="checkbox" id="checkBoxMM${mediador.id }"
											onchange="agregarMediador('checkBoxMM', '${mediador.id }', '${mediador.usuario.nombres }', '${mediador.usuario.apellido}', '${mediador.usuario.email }')">
										</li>
									</ul>
								</g:each></li>
						</g:each>
					</ul></li>
			</g:if></li>
	</ul>

</div>
${usuariosFormateados }
<div id="spinner" class="center_div"
	style="display: none; height: 16px; width: 16px;">
	<g:img file="spinner.gif" />
</div>
