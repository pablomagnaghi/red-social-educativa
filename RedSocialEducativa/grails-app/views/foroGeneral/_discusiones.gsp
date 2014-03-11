<!--FOROS-->
<div class="row-fluid">
    <div class="span12 discussions">
        <ul>   
            <li>
   				<div class="author">
					<img src="${resource(dir: 'img', file: 'avatar2.jpg')}">
	            </div>
                <div class="name"><h3>Tema ${tema.titulo}</h3></div>
                <div class="date">${tema.fecha} - ${tema.hora}<br>${tema.responsable}</div>
                <div class="opciones">
                	<span><g:link controller="publicacionGeneral" action="nueva">
                		<i class="icon-plus"></i></g:link></span>
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
	                            <img src="${resource(dir: 'img', file: 'avatar3.jpg')}" alt="avatar">
	                        </div>
	                        <div class="name"><h3>${it.responsable}</h3></div>
	                        <div class="date">${it.fecha} - ${it.hora}<br>${it.responsable}</div>
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
	                        <div class="message" style="width: 99%">
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
								<div><g:hiddenField name="titulo" value="${com.foro.PublicacionGeneral.get(params.pubInicialId).titulo}"/></div>
								<div><g:hiddenField name="responsable" value="${usuario}"/></div>
								<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
								<div><g:hiddenField name="foro.id" value="${com.foro.ForoGeneral.first().id}"/></div>	
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
