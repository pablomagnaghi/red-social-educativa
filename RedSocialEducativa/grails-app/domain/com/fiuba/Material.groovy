package com.fiuba

class Material {

	String titulo
	String descripcion // opcional
	String autor // opcional
	String fecha
	String responsable
	
	// Categorias: articulo, presentacion, cuestionario, ejercicios planteados, ejercicios resueltos
	// trabajo, referencia bibliografica, enlace, glosario, enunciado de evaluacion u otro
	// Las categorias referencia bibliografica, enlace y glosario deben visualizarse y mantenerse en conjunto
	// en forma similar a las carteleras
	// Para el resto de las categorias debe visualizarse en conjunto los datos de registro de cada material
	// con el enlace al archivo correspondiente
	// TODO ver
	Categoria categoria
	
    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
