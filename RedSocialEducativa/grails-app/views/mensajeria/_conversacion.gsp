<div class="conversation">
	<div class="actions span">
		<h6>Conversacion</h6>
	</div>
	<div class="span">
		<g:link action="renderPDF" id="${conversacionId}">
			<i class="fa fa-print"></i>
			Exportar
		</g:link>
	</div>
	<ul class="talk">
		<g:each in="${mensajes}" var="mensaje">
			<%
				def usuariosReplyAll = []
				def keyReplyAll = []
				def destinatariosReplyAll = []
				for (mensajePara in mensaje.para) {
    				usuariosReplyAll.add(mensajePara.value)
					keyReplyAll.add(mensajePara.key)
					destinatariosReplyAll.add(mensajePara.key)
				}
				destinatariosReplyAll.add(mensaje.emisor.nombres+" "+mensaje.emisor.apellido+"<"+mensaje.emisor.email+">")
			 %>
			<li style="border-bottom: 1px solid #D6D9E0;margin-bottom: 10px; padding-bottom: 10px;">
				<g:if test="${mensaje.emisor != currentUser}">
					<div class="btn-group text-left" style="float:right">
						<button class="btn btn-primary btn-sm replythis" onclick="redactarRespuesta('${mensaje.id}', 'respuesta', 
							'${mensaje.emisor.nombres} ${mensaje.emisor.apellido}&lt;${mensaje.emisor.email}&gt;', null, '${mensaje.emisor.id }')">
							<i class="fa fa-reply"></i> Responder
						</button>
						<button data-toggle="dropdown" class="btn btn-primary btn-sm dropdown-toggle">
							<i class="fa fa-angle-down"></i>
						</button>
						<ul class="dropdown-menu pull-right">
							<li>
								<a class="replythis" onclick="redactarRespuesta('${mensaje.id}', 'respuestaTodos', '${destinatariosReplyAll}', 
								'${mensaje.receptor.nombres} ${mensaje.receptor.apellido}&lt;${mensaje.receptor.email}&gt;', '${mensaje.emisor.id }')">
								<i class="fa fa-reply"></i> Responder a todos</a>
							</li>
							<li>
								<a class="replythis" href="javascript:void(0);" onclick="redactarRespuesta('${mensaje.id}', 'reenviar', '${mensaje.cuerpo }', null, null)">
								<i class="fa fa-mail-forward"></i> Reenviar</a>
							</li>
						</ul>
					</div>
				</g:if>
			<span class="name">
					${mensaje.emisor.nombres } ${mensaje.emisor.apellido }
			</span><br/>
			<span class="time">
				Para:
				 <g:each in="${keyReplyAll }" var='destinatario'>
				 	${destinatario }
				 	<br/>
				 </g:each>
			</span>
			<span class="time">
					${mensaje.fecha }
			</span> <span class="asunto"><h5>
						${mensaje.asunto }
					</h5></span>
				<div class="message">
					<msg:htmlMessage args="${mensaje.cuerpo.replaceAll('\r\n','<br/>')}" />
				</div>
				<g:if test="${mensaje.emisor != currentUser}">
				<form action="responderMensaje" onsubmit="return submitRespuesta('${mensaje.id}');" method="post" class="replyForm"
					style="display: none; margin-top: 9px;" id="form_reply_${mensaje.id }"
					enctype="multipart/form-data">
					<fieldset>
					<table class="table" style="background-color: #FFFFFF;">
						<tbody>
							<tr>
								<td class="span">
								<input type='hidden' name='mensajeId' value='${mensaje.id }'/>
								<span style="float: left">Para </span>
									<span class="span" style="width: 680px;">
										<g:img file="Treeview.gif" id="img_clickeable"
											style="cursor: pointer;width: 21px;float: right;margin-right: 65px; margin-top: 6px;" />
										<input type='hidden' class="para_${mensaje.id } autocomplete" name="para"
												style="margin-bottom: 11px;" />
										<input type='hidden' id="ids_${mensaje.id }"/>
										<input type='hidden' id='asunto_${mensaje.id }' name='asunto' value=''>
									</span>
								</td>
							</tr>
							<tr>
								<td>
										<textarea rows="3" class="cleditor"
										id="text_${mensaje.id }" name="mensaje"
										style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 73px; display: none; width: 500px;"></textarea>
									<div contenteditable="true" style="height: 140px;"
										class="note-editable" id="cuerpo_${mensaje.id }"></div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="actions">
										<button class="btn btn-success" type="submit" tabindex="3">Enviar
											Mensaje</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</fieldset>
				</form>
				</g:if>
			</li>
		</g:each>
	</ul>
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
								test="${datosCursosAprendiz.get(cursoAprendiz.id +"-cuatrimestreA") != null}">
								<li style="display: none"><span class="badge badge-success"><i
										class="icon-minus-sign"></i> Actividades</span> <g:each
										in="${datosCursosAprendiz.get(cursoAprendiz.id +"-cuatrimestreA").actividades}"
										var="actividad">
										<ul>
											<li style="display: none"><span
												class="badge badge-success"><i
													class="icon-minus-sign"></i> ${actividad.titulo}</span> 
												<g:each
													in="${actividad.grupos}" var="grupo">
													<ul>
														<li style="display: none">
															${grupo.nombre} <input type="checkbox"
															id="checkBoxGA${grupo.id }"
															onchange="agregarGrupo('checkBoxGA', '${grupo.id }', '${grupo.nombre }', '${cursoAprendiz.nombre}','${cursoAprendiz.id}')">
														</li>
													</ul>
												</g:each>
											</li>
										</ul>
									</g:each>
								</li>
							</g:if>
						</ul>
					</li>
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
								test="${datosCursosMediador.get(cursoMediador.id +"-cuatrimestreM") != null}">
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
									class="icon-minus-sign"></i> ${curso.asignatura} - ${curso.nombre }</span> <g:each
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