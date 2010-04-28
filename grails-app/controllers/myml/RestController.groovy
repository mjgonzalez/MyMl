package myml

import static org.apache.commons.lang.StringUtils.*
import org.codehaus.groovy.runtime.InvokerHelper
import org.codehaus.groovy.grails.commons.GrailsDomainClass

/**
 * Scaffolding like controller for exposing RESTful web services
 * for any domain object in both XML and JSON formats.
 */
class RestController {
  private GrailsDomainClass domainClass
  private String domainClassName

  // RESTful actions excluded
  def beforeInterceptor = {
    domainClassName = capitalize(params.domain)
    domainClass = grailsApplication.getArtefact("Domain", "myml." + domainClassName)

  }

  private invoke(method, parameters) {
    InvokerHelper.invokeStaticMethod(domainClass.getClazz(), method, parameters)
  }
  
  private format(obj) {
	def restType
	def contentType
	if(params.type == "xml"){
		restType = "XML"
		contentType = "text/xml"
	}else{
		restType = "JSON"
		contentType = "application/json"
	}
    		
    render contentType: contentType, charset:"iso-8859-1", text:obj."encodeAs$restType"()
  }
  
  def show = {
		  def result
		  if(params.id) {
		    result = invoke("get", params.id)
		  } else {
		    if(!params.max) params.max = 10
		    result = invoke("list", params)
		  }
		  format(result)
		}
  
  def delete = {
		  def result = invoke("get", params.id);
		  if(result) {
		    result.delete()
		  } else {
		    result = new Error(message: "${domainClassName} not found with id ${params.id}")
		  }
		  format(result)
		}

  def update = {
		  def result
		  def domain = invoke("get", params.id)
		  if(domain) {
		    domain.properties = params
		    if(!domain.hasErrors() && domain.save()) {
		      result = domain
		    } else {
		      result = new Error(message: "${domainClassName} could not be saved")
		    }
		  } else {
		    result = new Error(message: "${domainClassName} not found with id ${params.id}")
		  }
		  format(result)
		}

  def create = {
		  def result
		  def domain = InvokerHelper.invokeConstructorOf(domainClass.getClazz(), null)
		  def input = ""
		  request.inputStream.eachLine {
		    input += it
		  }

		  // convert input to name/value pairs
		  if(input && input != '') {
		    input.tokenize('&').each {
		      def nvp = it.tokenize('=');
		      params.put(nvp[0],nvp[1]);
		    }
		  }
		  domain.properties = params
		  if(!domain.hasErrors() && domain.save()) {
		    result = domain
		  } else {
		    result = new Error(message: "${domainClassName} could not be created")
		  }
		  format(result)
		}


  
}
