class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/rest/$type/$domain/$id?"{
    	  controller = "rest"
    	  action = [GET:"show", PUT:"create", POST:"update", DELETE:"delete"]
    	  constraints {
    		  		type(inList:["xml","json"])
    	  }
      }
      
      "/"(view:"/index")
	  "500"(view:'/error')
	}
}
