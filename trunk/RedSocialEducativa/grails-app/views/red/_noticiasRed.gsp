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
		                    <span class="time">${it.fecha} - ${it.hora} </span>
		                    <div class="message">${it.texto}</div>
		                </li>
					</g:if> 
				</g:each>                
            </ul>
            <div class="form">
                <input type="text" class="write-message" placeholder="Escriba una noticia" />
            </div>
        </div>

    </div>
</div>