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
                	<g:if test="${it.visibilidad}">
                		<li>
		                    <span class="title">${it.titulo}</span>
		                    <span class="name"> publicada por ${it.mediador.usuario}</span>
		                    <span class="time">
		                    	<div>${it.fecha} - ${it.hora}</div>
								<div>${it.mediador.usuario}</div>							
							</span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
    	</div>
    </div>	
</div>
