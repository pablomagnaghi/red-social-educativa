<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<span class="titulo">Cartelera del cuatrimestre ${com.fiuba.Cuatrimestre.get(params.cuatrimestreId)}</span>    
            	<span class="cant">
            		<g:if test="${noticiasCurso.size() == 1}">1 noticia</g:if>
            		<g:if test="${noticiasCurso.size() > 1}">${noticiasCurso.size()} noticias</g:if>
            	</span>	
            </div>
            <g:if test="${flash.message}">
				<div class="box-content alerts">
		    		<div class="alert alert-info">
						<button class="close" data-dismiss="alert" type="button"></button>
						<strong></strong> 
						${flash.message}
				    </div>
				</div>    
			</g:if>
            <ul class="talk">
                <g:each in="${noticiasCurso}">
                		<li>
		                    <span class="title">${it.titulo}</span>
		                    <span class="time">
		                    	<div>${it.fecha} - ${it.hora}</div>
		                    	<div>${it.mediador.usuario.nombres} ${it.mediador.usuario.apellido}</div>
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
