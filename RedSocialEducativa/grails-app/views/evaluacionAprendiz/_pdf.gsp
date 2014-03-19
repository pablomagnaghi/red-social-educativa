<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="layout" content="main"/>
	<style>
		@page {
		    size: 8.5in 11in;  /* width height */
		    margin: 0.25in;
		}
		body{
			font-family: "Times New Roman",Georgia,Serif;
		}
		.name{
			font-size: 12pt;
		}
		.birthdate{
			font-size: 10pt;
		}
		table,th,td {
			border:1px solid black;
			border-collapse:collapse;
		}
		th,td {
			padding:5px;
		}
		th {
			text-align:left;
		}
	</style>

</head>
<body>
	<div class="body">
		<h1>Evaluacion: ${evaluacion.nombre}</h1>
		<h2>Curso: ${evaluacion.curso}</h2>
		<h2>Fecha: ${evaluacion.fecha}</h2>
		<h2>Listado de alumnos inscriptos:</h2>
		<g:if test="${evaluaciones}">
			<table style="width:100%">
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