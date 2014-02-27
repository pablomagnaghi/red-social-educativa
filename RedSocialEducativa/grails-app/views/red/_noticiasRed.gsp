<!--Cartelera-->
<div class="row-fluid">
	<div class="box chat span12">
        <div class="cartelera">
            <div class="actions">
            	<span class="titulo">Cartelera</span>    
            	<span class="cant">${noticiasRed.size()} noticias</span> 	
            </div>
            <ul class="talk">
                <g:each in="${noticiasRed}">
                	<g:if test="${it.visibilidad}">
                		<li>
		                    <span class="name">${it.titulo} - ${it.administrador.usuario}</span>
		                    <span class="time">${it.fecha} - ${it.hora} </span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
    	</div>
    </div>	
</div>
