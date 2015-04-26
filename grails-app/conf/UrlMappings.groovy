class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"musee",action: "renderMusee" )
        "500"(view:'/error')
	}
}
