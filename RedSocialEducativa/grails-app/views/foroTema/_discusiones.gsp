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
                	<span><g:link controller="publicacionTema" action="nueva"
		                	params="['cursoId': params.cursoId, 'temaId': params.temaId]">
                			<i class="icon-plus"></i></g:link></span>
                	<g:if test="${mediador}">
						<span><g:link controller="publicacionTema" action="editar" id="${tema.id}" 
							params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">      	
							<i class="icon-edit"></i></g:link></span>
						<span><g:link controller="publicacionTema" action="eliminar" id="${tema.id}" 
							params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">      	
							<i class="icon-remove"></i></g:link></span>
					</g:if>
				</div>
				<div class="message">
                   ${tema.contenido}
                </div>
				<g:each in="${respuestas}">	
	                <ul>
	                    <li>
	                        <div class="author">
	                            <img src="${resource(dir: 'img', file: 'avatar3.jpg')}" alt="avatar">
	                        </div>
	                        <div class="date">${it.fecha} - ${it.hora}<br>${it.responsable}</div>
							<g:if test="${mediador}">
		                        <div class="opciones">
		                        	<span><g:link controller="publicacionTema" action="editar" id="${it.id}" 
		                        		params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">                	
		                        		<i class="icon-edit"></i></g:link>
		                        	</span>
		                        	<span><g:link controller="publicacionTema" action="eliminar" id="${it.id}" 
		                        			params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">          	
		                        		<i class="icon-remove"></i></g:link>
		                        	</span>
		                        </div>	                        
							</g:if>
	                        <div class="message">${it.contenido}</div>
	                    </li>
	                </ul>
	      		</g:each>	          
	      		<ul>
					<li>
						<div class="author">
							<img src="img/avatar.jpg" alt="avatar" />
						</div>
						<g:form controller="publicacionTema" action="guardar" params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'temaId': params.temaId]">   
							<fieldset class="form">
								<div class="control-group">	
									<div class="controls">
									<textarea class="diss-form" name="contenido" placeholder="Escribe un comentario menor a 1024 caracteres"></textarea>
									</div>	
								</div>	
								<g:hiddenField name="titulo" value="${com.foro.PublicacionTema.get(params.pubInicialId).titulo}"/>
								<g:hiddenField name="responsable" value="${usuario.nombres} ${usuario.apellido}"/>
								<g:hiddenField name="dni" value="${usuario.dni}"/>
								<g:hiddenField name="foro.id" value="${foro.id}"/>	
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
