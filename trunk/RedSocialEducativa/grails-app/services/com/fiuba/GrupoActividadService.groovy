package com.fiuba

import grails.transaction.Transactional

@Transactional
class GrupoActividadService {

	def obtenerAprendicesPorActividadPaginado(Long actividadId, Integer max, Integer offset) {
		
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendices = c.list([max: max, offset: offset]) {
			grupo {
				eq('actividad.id', actividadId)
			}
		}
		
		return aprendices
	}
	
	def obtenerAprendicesPorActividad(Long actividadId) {
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendices = c.list() {
			grupo {
				eq('actividad.id', actividadId)
			}
		}
		
		return aprendices
	}
	
	def obtenerGrupoAprendiz(Aprendiz aprendiz, Long actividadId) {
		def c = GrupoActividadAprendiz.createCriteria()
		def grupoActividadAprendiz = c.get {
			grupo {
				eq('actividad.id', actividadId)
			}
			eq('aprendiz.id', aprendiz.id)
		}
		
		return grupoActividadAprendiz
	}
	
	def aprendizParticipa(GrupoActividad grupo, Aprendiz aprendiz) {
		def c = GrupoActividadAprendiz.createCriteria()
		def aprendizParticipa = c.get {
			eq('grupo.id', grupo.id)
			eq('aprendiz.id', aprendiz.id)
		}
		
		return aprendizParticipa
	}

    def guardar(GrupoActividad grupo) {
		
		if (grupo.save(flush:true)) {
			return grupo
		}
			
		return null
    }
	
	def agregarAprendiz(GrupoActividad grupo, Usuario usuario, Long cuatrimestreId) {
		def aprendiz = Aprendiz.findByUsuarioAndCuatrimestre(usuario, Cuatrimestre.get(cuatrimestreId))
		
		if (guardar(grupo)) {
			def grupoActividadAprendiz = new GrupoActividadAprendiz(aprendiz: aprendiz, grupo: grupo)
			grupoActividadAprendiz.save flush:true
			return grupo
		}
		
		return null
	}

	def obtenerGrupoPorNumero(Actividad actividad, Integer numeroGrupo) {
		return GrupoActividad.findByActividadAndNumero(actividad, numeroGrupo)
	}
	
	def obtenerGrupos(Actividad actividad) {
		return GrupoActividad.findAllByActividad(actividad)
	}

	def realizarCambio(GrupoActividadAprendiz grupoActividadAprendiz, GrupoActividad grupo) {
	
		if (!grupo) {
			return null
		}
		
		grupoActividadAprendiz.grupo = grupo
		grupoActividadAprendiz.save flush:true
	
		return grupoActividadAprendiz
	}

	def eliminar(GrupoActividad grupo) {
		grupo.delete flush:true
	}
}
