package com.fiuba

class MateriaController {

	def general() {
		[cursos: Materia.get(params.id).cursos]
	}
	
	// metodos por defecto, usados en ABM materias del administrador
	// ver en detalle despues
    static scaffold = true
}
