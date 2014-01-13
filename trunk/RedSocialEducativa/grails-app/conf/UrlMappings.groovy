class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

		"/"(controller: "red",
			action:"principal")
		//"/"(view:"/index")
		"500"(view:'/error')

	}
}
