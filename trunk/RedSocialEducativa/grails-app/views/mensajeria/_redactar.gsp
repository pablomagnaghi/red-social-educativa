<div class="box span12">
	<div class="box-header">
		<h2>
			<i class="icon-edit"></i>Redactar Mensaje
		</h2>
	</div>
	<div class="box-content">
		<form id="email-compose-form" class="form-horizontal" method="POST" action="enviarMensajes" onsubmit="return submitMail();" enctype="multipart/form-data">
			<fieldset>
				<div class="control-group" style="margin-bottom: 0px;">
					<label for="typeahead" class="control-label">Para
					</label>
					<div class="controls">
						<g:img file="Treeview.gif" id="img_clickeable"
								style="cursor: pointer;width: 21px;float: right;margin-right: 65px; margin-top: 6px;" />
						<g:if test="${para != null }">
							<input type='hidden' id="e6" name="para" value="${para }"/>
						</g:if>
						<g:else>
							<input type='hidden' id="e6" name="para" />
						</g:else>
						
					</div>
				</div>
				<div class="control-group" style="margin-bottom: 13px;">
					<label for="date01" class="control-label">Asunto</label>
					<div class="controls">
						<g:if test="${asunto != null }">
							<input type="text" name="asunto" id="asunto"
								placeholder="Asunto del mensaje" class="span10 typeahead"
								value="${asunto }">
						</g:if>
						<g:else>
							<input type="text" name="asunto" id="asunto"
								placeholder="Asunto del mensaje" class="span10 typeahead">
						</g:else>

					</div>
				</div>

				<div class="control-group hidden-phone">
					<label for="textarea2" class="control-label">Texto
						</label>
					<div class="controls">
						<div class="cleditorMain" style="width: 500px; height: 250px;">
							<g:if test="${cuerpo != null }">
								<textarea rows="3" id="textarea2" class="cleditor" id="cuerpo" name="mensaje"
								style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 73px; display: none; width: 500px;"></textarea>
								<div contenteditable="true" id="divCuerpo" class="note-editable"
									style="height: 250px;">
									${cuerpo }
								</div>
							</g:if>
							<g:else>
								<textarea rows="3" id="textarea2" class="cleditor" id="cuerpo" name="mensaje"
								style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 73px; display: none; width: 500px;"></textarea>
								<div contenteditable="true" id="divCuerpo" class="note-editable"
									style="height: 250px;"></div>
							</g:else>

						</div>
					</div>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit">Enviar</button>
					<button class="btn" type="reset" onclick="agregarMensajeABorradores()">Borradores</button>
				</div>
			</fieldset>
		</form>

	</div>
</div>