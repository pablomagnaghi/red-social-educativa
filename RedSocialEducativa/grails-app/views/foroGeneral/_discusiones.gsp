<!--FOROS-->
<div class="row-fluid">
    <div class="span12 discussions">
        <ul>   
            <li>
   				<div class="author">
					<g:if test="${com.fiuba.Usuario.findByDni(tema.dni)?.foto}">
						<img src="${createLink(controller: 'usuario', action: 'mostrarFoto', id: com.fiuba.Usuario.findByDni(tema.dni).foto.id)}">
				 	</g:if>
					<g:else>
						<img src="${resource(dir: 'img', file: 'usuario.png')}" alt="avatar">
					</g:else>
	            </div>
                <div class="name"><h3>Tema ${tema.titulo}</h3></div>
                <div class="date">${tema.responsable}, ${tema.fecha} - ${tema.hora}</div>
                <div class="opciones">
                	<span><g:link controller="publicacionGeneral" action="nueva"><i class="icon-plus"></i></g:link></span>
                	<g:if test="${administrador}">
						<span><g:link controller="publicacionGeneral" action="editar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-edit"></i></g:link></span>
						<span><g:link controller="publicacionGeneral" action="eliminar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-remove"></i></g:link></span>
					</g:if>
				</div>
				<div class="message">${tema.contenido}</div>
				<g:each in="${respuestas}">	
	                <ul>
	                    <li>
	                        <div class="author">
	                        	<g:if test="${com.fiuba.Usuario.findByDni(it.dni)?.foto}">
					            	<img src="${createLink(controller: 'usuario', action: 'mostrarFoto', id: com.fiuba.Usuario.findByDni(it.dni).foto.id)}">
					            </g:if>
					            <g:else>
					            	<img src="${resource(dir: 'img', file: 'usuario.png')}" alt="avatar">
					            </g:else>
	                        </div>
	                        <div class="name"><h3>Re: ${tema.titulo}</h3></div>
	                        <div class="date">${it.responsable}, ${it.fecha} - ${it.hora}</div>
							<g:if test="${administrador}">
		                        <div class="opciones">
		                        	<span><g:link controller="publicacionGeneral" action="editar" 
		                        		id="${it.id}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-edit"></i></g:link>
		                        	</span>
		                        	<span><g:link controller="publicacionGeneral" action="eliminar" 
		                        		id="${it.id}" params="['pubInicialId': params.pubInicialId]">	                        	
		                        		<i class="icon-remove"></i></g:link>
		                        	</span>
		                        </div>
							</g:if>
	                        <div class="message" style="width: 99%">${it.contenido}</div>
	                    </li>
	                </ul>
	      		</g:each>	          
	      		<ul>
					<li>
						<div class="author">
							<g:if test="${usuario?.foto}">
								<img src="${createLink(controller: 'usuario', action: 'mostrarFoto', id: usuario.foto.id)}">
							</g:if>
							<g:else>
								<img src="${resource(dir: 'img', file: 'usuario.png')}" alt="avatar">
							</g:else>
						</div>
						<g:form controller="publicacionGeneral" action="guardar" params="['pubInicialId': params.pubInicialId]">
							<fieldset class="form">
								<div class="control-group">	
									<div class="controls">
										<textarea class="diss-form" name="contenido" placeholder="Escribe un comentario menor a 1024 caracteres"></textarea>
									</div>	
								</div>	
								<g:hiddenField name="titulo" value="${com.foro.PublicacionGeneral.get(params.pubInicialId).titulo}"/>
								<g:hiddenField name="responsable" value="${usuario.nombres} ${usuario.apellido}"/>
								<g:hiddenField name="dni" value="${usuario.dni}"/>
								<g:hiddenField name="foro.id" value="${com.foro.ForoGeneral.first().id}"/>	
							</fieldset>
							<fieldset class="buttons">
								<button type="submit" class="btn btn-primary">Publicar</button>
							</fieldset>
						</g:form>
					</li>	
	      		</ul>
            </li>
        </ul>
    </div>
</div>
