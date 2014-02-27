<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<a>Cartelera</a>
            </div>
            <ul class="talk">
                <g:each in="${noticiaRedInstanceList}">
                	
                		<li>
		                    <span class="name">${it.titulo} - ${it.administrador.usuario} </span>
		                    <span class="time">
		                    	<div>${it.fecha} - ${it.hora}</div>
		                    	<div>
		                    		<g:if test="${it.visibilidad}"><a class="btn btn-success"></g:if>
		                    		<g:else><a class="btn btn-inverse"></g:else>	
									<i class="icon-plus-sign"></i></a>
			                    	<g:link class="btn btn-info" action="edit" id="${it.id}">
		                                <i class="icon-edit "></i> 
		                            </g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${it.id}" 
		                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >	                          
		                                <i class="icon-trash "></i>                        
									</g:link>
								</div>
		                    </span>
		                    <div class="message">${it.texto}</div>
		                </li>
				</g:each>                
            </ul>
	        <div class="form">
	            <g:form class="form-horizontal" action="create">
					<fieldset>	
						<button type="submit" class="btn btn-primary span12">Publica nueva noticia</button>	
					</fieldset>	
				</g:form>
	        </div>
    	</div>
    </div>	
</div>
