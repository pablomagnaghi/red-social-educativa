<div id="sidebar-left" class="span2">
    <div class="nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">         
            <g:if test="${administrador}">
            	<li><g:link controller="red" action="administrador"><i class="icon-home"></i><span class="hidden-tablet"> Inicio</span></g:link>
				</li> 
				<g:render template="/templateRed/tareasAdmin"></g:render>
            </g:if>   
            <g:else>
            	<li><g:link controller="red" action="miembro"><i class="icon-home"></i><span class="hidden-tablet"> Inicio</span></g:link>
				</li> 
            </g:else>
			<g:if test="${cursosMediador}">
				<g:render template="/templateRed/cursosMediador"></g:render>
            </g:if>
            <g:if test="${cursosAprendiz}">
				<g:render template="/templateRed/cursosAprendiz"></g:render>
            </g:if> 
            <li><g:link controller="foroGeneral" action="general"><i class="icon-comments-alt"></i><span class="hidden-tablet"> Foro general</span></g:link>
			</li>       
            <li><g:link controller="red" action="cursos"><i class="icon-edit"></i><span class="hidden-tablet"> Acceso a cursos</span></g:link>
            </li> 
        </ul>
    </div>
</div>
