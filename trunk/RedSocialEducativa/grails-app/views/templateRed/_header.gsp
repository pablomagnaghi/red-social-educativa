<div class="navbar">
	<g:if test="${usuario != null }">
	<g:set var="mensajes" value="${
		com.mensajeria.Mensaje.withCriteria {
			eq('receptor.id', usuario.id)
			and {
				order('leido', 'asc')
				order('fecha', 'desc')
			}
		}
	}"/>
	<g:if test="${mensajes != null && mensajes.size()>0}">
		<g:set var="ultimoMensaje" value="${mensajes.first() }"/>
	</g:if>

	</g:if>
    <div class="navbar-inner">
        <div class="container-fluid">
			<sec:ifLoggedIn>
            <!--Este es el boton para achicar el panel lateral-->
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a id="main-menu-toggle" class="hidden-phone open"><i class="icon-reorder"></i></a>	
            <div class="row-fluid">
                <a class="brand span2">
                    <span>Red Social FIUBA</span>
                </a>
            </div>
            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">
                    <!-- LISTA DE MENSAJES -->
                    <li class="dropdown hidden-phone">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-envelope"></i>
                            <g:if test="${cantMensajes}">
                            	<span class="label">${cantMensajes}</span>
                            </g:if>	
                        </a>                 
                        <ul class="dropdown-menu messages">
                        	<g:if test="${cantMensaje}">
	                        	<g:if test="${cantMensajes == 1}">
		                            <li>
		                                <span class="dropdown-menu-title">Usted tiene ${cantMensajes} mensaje nuevo</span>                  
		                            </li>
	                            </g:if>
	                            <g:else test="${cantMensajes > 1}">
	                            	<span class="dropdown-menu-title">Usted tiene ${cantMensajes} mensajes nuevos</span>      
	                            </g:else>
                            </g:if>
                            <g:else>
                            	<span class="dropdown-menu-title">Usted no tiene mensajes nuevos</span>     
                            </g:else>
                            <g:if test="${ultimoMensaje != null }">
	                            <li>
	                                <g:link controller="mensajeria" action="index">
	                                    <span class="avatar">
				                             <g:if test="${ultimoMensaje.emisor?.foto}">
								            	<img src="${createLink(controller: 'usuario', action: 'mostrarFoto', id: ultimoMensaje.emisor.foto.id)}">
								            </g:if>
								            <g:else>
								            	<img src="${resource(dir: 'img', file: 'usuario.png')}" alt="avatar">
								            </g:else> 
	                                    </span>
	                                    <span class="header">
	                                        <span class="from">
	                                            ${ultimoMensaje.emisor }
	                                        </span>
	                                    </span>
	                                     <div class="time">
	                                            ${ultimoMensaje.getFechaYHora() }
	                                    </div>
	                                </g:link>
                            </li>
                            </g:if>
                            <li>
                                <g:link controller="mensajeria" action="index">Ver todos mis mensajes</g:link>
                            </li>
                        </ul>
                    </li>
                    <!-- start: User Dropdown -->
                    <!-- BIENVENIDA-->
                    <li class="dropdown">
                        <a class="btn account dropdown-toggle" data-toggle="dropdown" href="#">
                        	<div class="avatar">
                        		<g:if test="${usuario?.foto}">
					            	<img src="${createLink(controller: 'usuario', action: 'mostrarFoto', id: usuario.foto.id)}">
					            </g:if>
					            <g:else>
					            	<img src="${resource(dir: 'img', file: 'usuario.png')}" alt="avatar">
					            </g:else> 
					        </div>
                            <div class="user">
                                <span class="hello">BIENVENIDO <sec:username/></span>
                                <span class="name">${usuario.nombres} ${usuario.apellido}</span>
                            </div>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-menu-title">
                            </li>
                            <li><g:link controller="usuario" action="edit" id="${usuario.id}"><i class="icon-user"></i> Perfil</g:link>
                            </li>
                            <li><g:link controller="usuario" action="cambiarEstado" id="${usuario.id}"
                            	onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            	<i class=" icon-signout"></i> Perder membresía</g:link>
                            </li>
                            <li><g:link controller='logout'><i class="icon-off"></i> Salir</g:link>
                            </li>
                        </ul>
                    </li>
                    <!-- end: User Dropdown -->
                </ul>
            </div>
			</sec:ifLoggedIn>
            <!-- end: Header Menu -->
        </div>
    </div>
</div>
