<!--FOROS-->
<div class="row-fluid">
    <div class="span12 discussions">
        <ul>   
            <li>
   				<div class="author">
					<img src="${resource(dir: 'img', file: 'avatar2.jpg')}">
	            </div>
                <div class="name">Tema ${tema.titulo} creado por ${tema.responsable}</div>
                <div class="date">${tema.fecha} - ${tema.hora}</div>
                <g:if test="${administrador}">
					<div class="opciones">
						<!-- PONER ENLACE PARA CREAR NUEVO TEMA -->
						<span><g:link controller="publicacionGeneral" action="nueva">
                			<i class="icon-plus"></i></g:link></span>
						<span><g:link controller="publicacionGeneral" action="editar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-edit"></i></g:link></span>
						<span><g:link controller="publicacionGeneral" action="eliminar" 
							id="${params.pubInicialId}" params="['pubInicialId': params.pubInicialId]">	                        	
							<i class="icon-remove"></i></g:link></span>
					</div>
				</g:if>
				<div class="message">
                   ${tema.contenido}
                </div>
				<g:each in="${respuestas}">	
	                <ul>
	                    <li>
	                        <div class="author">
	                            <img src="${resource(dir: 'img', file: 'avatar3.jpg')}" alt="avatar">
	                        </div>
	                        <div class="name">${it.responsable}</div>
	                        <div class="date">${it.fecha} - ${it.hora}</div>
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
	                        <div class="message">
								${it.contenido}
	                        </div>
	
	                    </li>
	                </ul>
	      		</g:each>	          
	      		<ul>
					<li>
						<div class="author">
							<img src="img/avatar.jpg" alt="avatar" />
						</div>
						<g:form controller="publicacionGeneral" action="guardar" 
							params="['pubInicialId': params.pubInicialId]">
							<fieldset class="form">
								<div class="control-group">	
									<div class="controls">
									<textarea class="diss-form" name="contenido" placeholder="Escribe un comentario"></textarea>
									</div>	
								</div>	
								<div><g:hiddenField name="titulo" value="${com.fiuba.PublicacionGeneral.get(params.pubInicialId).titulo}"/></div>
								<div><g:hiddenField name="responsable" value="${usuario}"/></div>
								<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
								<div><g:hiddenField name="foro.id" value="${com.fiuba.ForoGeneral.first().id}"/></div>	
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


	









