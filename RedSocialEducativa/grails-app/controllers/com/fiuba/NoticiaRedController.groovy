package com.fiuba

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class NoticiaRedController {

    static scaffold = true
}
