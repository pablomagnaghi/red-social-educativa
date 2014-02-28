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
		                    <span class="name">${it.titulo} - ${it.mediador.usuario}</span>
		                    <span class="time">${it.fecha} - ${it.hora} </span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
    	</div>
    </div>	
</div>
