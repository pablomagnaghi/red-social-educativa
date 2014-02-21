<%@ page import="com.fiuba.Administrador" %>

<div><g:hiddenField name="rol.id" value="${com.fiuba.Rol.findByAuthority(com.fiuba.Utilidades.ROL_ADMIN).id}" /></div>
<div><g:hiddenField name="usuario.id" value="${params.usuarioId}" /></div>