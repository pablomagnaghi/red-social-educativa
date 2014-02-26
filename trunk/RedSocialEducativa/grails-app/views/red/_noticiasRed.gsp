<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<a>Cartelera</a>
            </div>
            <ul class="talk">
                <g:each in="${noticiasRed}">
                	<g:if test="${it.visibilidad}">
                		<li>
		                    <span class="name">${it.titulo} - ${it.administrador.usuario}</span>
		                    <span class="time">
		                    	<div>${it.fecha} - ${it.hora} 
		                    	</div>
		                    	<div>
			                    	<g:link class="btn btn-info" controller="noticiaRed" action="edit" resource="${materiaInstance}" id="${it.id}">
		                                <i class="icon-edit "></i> 
		                            </g:link>
		                            <g:link class="btn btn-danger" controller="noticiaRed" action="delete" method="DELETE" id="${it.id}" 
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
		                                <i class="icon-trash "></i>                        
									</g:link>
								</div>
		                    </span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
	        <div class="form">
	            <g:form class="form-horizontal" controller="noticiaRed" action="create">
					<fieldset>	
						<button type="submit" class="btn btn-primary span12">Publica nueva noticia</button>	
					</fieldset>	
				</g:form>
	        </div>
    	</div>
    </div>	
</div>
