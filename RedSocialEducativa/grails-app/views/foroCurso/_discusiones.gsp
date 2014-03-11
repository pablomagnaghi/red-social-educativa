<!--FOROS-->
<div class="row-fluid">
    <div class="span12 discussions">
        <ul>   
            <li>
   				<div class="author">
					<img src="${resource(dir: 'img', file: 'avatar2.jpg')}">
	            </div>
                <div class="title"><h3>Tema ${tema.titulo} <small>creado por ${tema.responsable}</h3></div>
                <div class="date">${tema.fecha} - ${tema.hora}</div>
                <div class="nombre">${tema.responsable}</div>
                <div class="opciones">
                	<span>${tema.responsable}</span>
                	<span><g:link controller="publicacionCurso" action="nueva"
		                	params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">
                			<i class="icon-plus"></i></g:link></span>
                	<g:if test="${mediador || ((aprendiz?.cursando) && (aprendiz?.usuario?.username == tema.dni))}">
						<span><g:link controller="publicacionCurso" action="editar" id="${tema.id}" 
							params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">      	
							<i class="icon-edit"></i></g:link></span>
						<span><g:link controller="publicacionCurso" action="eliminar" id="${tema.id}" 
							params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">      	
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
	                        <div class="name"><h3>${it.responsable}</h3></div>
	                        <div class="nombre">${it.responsable}</div>
	                        <div class="date">${it.fecha} - ${it.hora}</div>
							<g:if test="${mediador || ((aprendiz?.cursando) && (aprendiz?.usuario?.username == it.dni))}">
		                        <div class="opciones">
		                        	<span><g:link controller="publicacionCurso" action="editar" id="${it.id}" 
		                        		params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">                	
		                        		<i class="icon-edit"></i></g:link>
		                        	</span>
		                        	<span><g:link controller="publicacionCurso" action="eliminar" id="${it.id}" 
		                        			params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">          	
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
						<g:form controller="publicacionCurso" action="guardar" 
							params="['pubInicialId': params.pubInicialId, 'cursoId': params.cursoId, 'cuatrimestreId': params.cuatrimestreId]">   
							<fieldset class="form">
								<div class="control-group">	
									<div class="controls">
									<textarea class="diss-form" name="contenido" placeholder="Escribe un comentario"></textarea>
									</div>	
								</div>	
								<div><g:hiddenField name="titulo" value="${com.foro.PublicacionCurso.get(params.pubInicialId).titulo}"/></div>
								<div><g:hiddenField name="responsable" value="${usuario}"/></div>
								<div><g:hiddenField name="dni" value="${usuario.username}"/></div>
								<div><g:hiddenField name="foro.id" value="${foro.id}"/></div>	
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
