<div class="table-wrap custom-scroll animated fast fadeInLeft"
	style="height: 389px; margin-left: 0px; opacity: 1;">
	<h2 class="email-open-header">
		${mensaje.asunto }
		<a class="txt-color-darken pull-right" data-original-title="Export"
			data-placement="left" rel="tooltip" href="javascript:void(0);"><i
			class="fa fa-print"></i></a>
	</h2>

	<div class="inbox-info-bar">
		<div class="row">
			<div class="col-sm-9">
				<strong>${mensaje.emisor.nombres } ${mensaje.emisor.apellido }</strong> <span class="hidden-mobile">&lt;${mensaje.emisor.email}&gt; Para: 
					<strong>${mensaje.receptor.nombres} ${mensaje.receptor.apellido}</strong> el <i>${mensaje.fecha }</i>
				</span>
			</div>
			<div class="btn-group text-left">
				<button class="btn btn-primary btn-sm replythis">
					<i class="fa fa-reply"></i> Responder
				</button>
				<button data-toggle="dropdown" class="btn btn-primary btn-sm dropdown-toggle">
					<i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu pull-right">
					<li>
						<a class="replythis" href="javascript:void(0);"><i class="fa fa-reply"></i> Responder</a>
					</li>
					<li>
						<a class="replythis" href="javascript:void(0);"><i class="fa fa-mail-forward"></i> Reenviar</a>
					</li>
					<li>
						<a href="javascript:void(0);"><i class="fa fa-trash-o"></i> Borrar</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="inbox-message">
		${mensaje.cuerpo }
	</div>

	<script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		//pageSetUp();

		// PAGE RELATED SCRIPTS

		$(".table-wrap [rel=tooltip]").tooltip();

		$(".replythis").click(
				function() {
					loadURL("ajax/email-reply.html",
							$('#inbox-content &gt; .table-wrap'));
				})
	</script>
</div>