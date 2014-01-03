package com.facultad

import com.fiuba.Miembro
import grails.transaction.Transactional

@Transactional
class MiembroService {

    def guardartodo(Miembro admin, Miembro apre) {
		admin.save()
		apre.save()
    }
}
