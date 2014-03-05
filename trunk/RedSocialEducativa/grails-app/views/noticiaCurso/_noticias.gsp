<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<span class="titulo">Cartelera</span>    
            	<span class="cant">${noticiasCurso.size()} noticias</span> 	
            </div>
            <ul class="talk">
                <g:each in="${noticiasCurso}">
                		<li>
		                    <span class="title">${it.titulo}</span>
		                    <span class="name"> publicada por ${it.mediador.usuario}</span>
		                    <span class="time">
		                    	<div>${it.fecha} - ${it.hora}</div>
		                    	<div>${it.mediador.usuario}</div>	
		                    	<div> 		
		                    		<g:if test="${it.visibilidad}">
		                    			<g:link class="btn btn-success" action="cambiarVisibilidad" id="${it.id}"
		                    			params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus-sign"></i></g:link>	
		                    		</g:if>
		                    		<g:else>
		                    			<g:link class="btn btn-inverse" action="cambiarVisibilidad" id="${it.id}"                   		
										params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-plus-sign"></i></g:link>	
									</g:else>	
			                    	<g:link class="btn btn-info" action="edit" id="${it.id}"
				                    	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"><i class="icon-edit "></i> 
		                            </g:link>
		                            <g:link class="btn btn-danger" action="delete" method="DELETE" id="${it.id}" 
		                            	params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]"
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
	            <g:form class="form-horizontal" action="create" params="['cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
					<fieldset>	
						<button type="submit" class="btn btn-primary span12">Publica nueva noticia</button>	
					</fieldset>	
				</g:form>
	        </div>
    	</div>
    </div>	
</div>			
