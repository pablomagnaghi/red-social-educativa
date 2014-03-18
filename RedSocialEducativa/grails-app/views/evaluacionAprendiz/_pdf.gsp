<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="main"/>
	<style>
		@page {
		    size: 6in 4in;  /* width height */
		    margin: 0.25in;
		}
		body{
			font-family: "Times New Roman",Georgia,Serif;
		}
		.name{
			font-size: 20pt;
		}
		.birthdate{
			font-size: 15pt;
		}
		
	</style>
</head>
<body>
	<div class="body">
		<h1>Evaluacion ${evaluacion}</h1>
		<h2>Curso: ${evaluacion.curso}</h2>
		<h2>Fecha: ${evaluacion.fecha}</h2>
		<g:if test="${evaluaciones}">
			<table>
				<thead>
					<tr>
						<th>Padron</th>
						<th>Apellido</th>
						<th>Nombre</th>
						<th>Nota</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${evaluaciones}">
						<tr>
							<td>${it.aprendiz.usuario.padron}</td>
							<td>${it.aprendiz.usuario.apellido}</td>
							<td>${it.aprendiz.usuario.nombres}</td>
	           				<td>
	            	  			<g:if test="${it.calificado}">
	            					<g:if test="${!it.nota}">0.00</g:if>
	            	        		<g:else>${it.nota}</g:else>		
	            	            </g:if>
	            			</td>
		         		</tr>
					</g:each>   
	   			</tbody>
	 		</table>
 		</g:if>
  </div>
</body>
</html>