package com.seguridad

import grails.plugin.springsecurity.SpringSecurityUtils

import javax.servlet.http.HttpServletResponse

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index() {
		
		/* TODO ESTO VENIA POR DEFECTO 
		if (!request.post && SpringSecurityUtils.getSecurityConfig().logout.postOnly) {
			println "retorno 4"
			response.sendError HttpServletResponse.SC_METHOD_NOT_ALLOWED // 405
			return
		}*/
		
		// TODO put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
