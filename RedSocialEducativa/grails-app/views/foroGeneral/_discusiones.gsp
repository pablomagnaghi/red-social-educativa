<!--FOROS-->
<div class="row-fluid">
    <div class="span12 discussions">
        <ul>   
            <li>
                <div class="author">
				</div>
                <div class="name">Tema ${tema.titulo} creado por ${tema.responsable} - ${tema.dni}}</div>
                <div class="date">${tema.fecha} - ${tema.hora}</div>
                <g:if test="${administrador}">
					<div class="delete">
						<!-- PONER ENLACE PARA CREAR NUEVO TEMA -->
						<div><i class="icon-plus"></i></div>
						<div><g:link class="create" controller="publicacionGeneral" action="editar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-edit"></i></g:link>
						</div>
						<div><g:link class="create" controller="publicacionGeneral" action="eliminar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-remove"></i></g:link>
						</div>
					</div>
				</g:if>
				<div class="message">
                   ${tema.contenido}
                </div>
				<g:each in="${respuestas}">	
	                <ul>
	                    <li>
	                        <div class="author">
	                           
	                        </div>
	                        <div class="name">${it.responsable}</div>
	                        <div class="date">${it.fecha} - ${it.hora}</div>
							<g:if test="${administrador}">
		                        <div class="delete">
		                        	<div><g:link controller="publicacionGeneral" action="editar" 
		                        		id="${it.id}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-edit"></i></g:link>
		                        	</div>
		                        	<div><g:link controller="publicacionGeneral" action="eliminar" 
		                        		id="${it.id}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-remove"></i></g:link>
		                        	</div>
		                        </div>
							</g:if>
	                        <div class="message">
								${it.contenido}
	                        </div>
	
	                    </li>
	                </ul>
	      		</g:each>	          
	      		<ul>
	      		
	                    <li>
	                        <div class="author">
	                            <img src="img/avatar6.jpg" alt="avatar" />
	                        </div>
	                        <div class="name">Megan Abbott</div>
	                        <div class="date">Today, 1:08 PM</div>
	               
	                        <g:if test="${administrador}">
		                        <div class="delete">
		                        	<div><i class="icon-plus"></i></div>
		                        	<div><g:link class="create" controller="publicacionGeneral" action="editar" 
		                        		id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-edit"></i></g:link>
		                        	</div>
		                        	<div><g:link class="create" controller="publicacionGeneral" action="eliminar" 
		                        		id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-remove"></i></g:link>
		                        	</div>
		                        </div>
							</g:if>
	                        <div class="message">
	                            Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
	                        </div>
	                    </li>
	
	                    <li>
	                        <div class="author">
	                            <img src="img/avatar.jpg" alt="avatar" />
	                        </div>
	                        <textarea class="diss-form" placeholder="Write comment"></textarea>
	          
	                    </li>
	                    
	         <g:link class="create" controller="publicacionGeneral" action="nueva" params="['pubInicialId': params.pubInicialId]">
					<g:message code="Publicar respuesta" /></g:link>
	      		
	      		</ul>

            </li>
        </ul>
    </div>
</div>


