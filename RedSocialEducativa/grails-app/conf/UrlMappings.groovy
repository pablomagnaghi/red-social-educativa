class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

		"/"(controller: "login",
			action: "auth")
		//"/"(view:"/index")
		"404"(controller:'login', action:'pagenotexist')
		"500"(view:'/error')

	}
}
