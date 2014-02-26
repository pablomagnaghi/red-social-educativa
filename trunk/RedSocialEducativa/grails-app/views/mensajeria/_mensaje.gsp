<div class="table-wrap custom-scroll animated fast fadeInLeft"
	style="height: 200px; margin-left: 0px; opacity: 1;">
	<h2 class="email-open-header">
		${mensaje.asunto }
		<a class="txt-color-darken pull-right" data-original-title="Export"
			data-placement="left" rel="tooltip" href="javascript:void(0);"><i
			class="fa fa-print"></i></a>
	</h2>

	<div class="inbox-info-bar" style="margin-right: 0px;">
		<div class="row" style="width: 800px">
			<div class="span9">
				<strong>${mensaje.emisor.nombres } ${mensaje.emisor.apellido }</strong> <span class="hidden-mobile">&lt;${mensaje.emisor.email}&gt; Para: 
					<strong>${mensaje.receptor.nombres} ${mensaje.receptor.apellido}</strong> el <i>${mensaje.fecha }</i>
				</span>
			</div>
			<g:if test="${responder == true}">
				<div class="btn-group text-left span1">
					<button class="btn btn-primary btn-sm replythis" onclick="responder('${mensaje.id}', 'Responder')">
						<i class="fa fa-reply"></i> Responder
					</button>
					<button data-toggle="dropdown" class="btn btn-primary btn-sm dropdown-toggle">
						<i class="fa fa-angle-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li>
							<a class="replythis" onclick="responder('${mensaje.id}', 'Responder')"><i class="fa fa-reply"></i> Responder</a>
						</li>
						<li>
							<a class="replythis" onclick="responder('${mensaje.id}', 'ResponderTodos')"><i class="fa fa-reply"></i> Responder A Todos</a>
						</li>
						<li>
							<a class="replythis" onclick="responder('${mensaje.id}', 'Reenviar')"><i class="fa fa-mail-forward"></i> Reenviar</a>
						</li>
					</ul>
				</div>
			</g:if>
		</div>
	</div>

	<script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		//pageSetUp();

		// PAGE RELATED SCRIPTS

		$(".table-wrap [rel=tooltip]").tooltip();

		
	</script>
</div>

<g:if test="${responder == true}">
	<div id="responder-${mensaje.id}"  style="display:none; border:medium solid; border-color: #F5F5F5" >
			
	</div>			
</g:if>