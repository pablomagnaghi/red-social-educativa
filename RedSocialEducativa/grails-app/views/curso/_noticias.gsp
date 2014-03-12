<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<span class="titulo">Cartelera</span>    
            	<span class="cant">
            		<g:if test="${noticiasCurso.size() == 1}">1 noticia</g:if>
            		<g:if test="${noticiasCurso.size() > 1}">${noticiasCurso.size()} noticias</g:if>
            	</span>	
            </div>
            <ul class="talk">
                <g:each in="${noticiasCurso}">
                	<g:if test="${it.visibilidad}">
                		<li>
                			<span class="title">${it.titulo}</span>
		                    <span class="time">
			                    <div>${it.fecha} - ${it.hora}</div>
			                    <div>${it.mediador.usuario.nombres} ${it.mediador.usuario.apellido}</div>
		                   	</span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
    	</div>
    </div>	
</div>
