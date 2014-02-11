package com.fiuba

import grails.transaction.Transactional

@Transactional
class RedService {

	def guardar(Red red) {

		if(red.save(flush: true)) {
			return red
		}

		return null
	}
}
