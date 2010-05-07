package myml

class PreguntasPendientesWebTests extends grails.util.WebTest {
	def questionToAnswer
	def questionNotToAnswer
	def sellerTest
	
	/*
	 * Inicializamos el test. 
	 */
	void setUp(){
		//Creamos el Customer para el seller
		def s = new Customer(nickname:"TESTSELLER001")
		s.save()
		
		sellerTest = s
		
		//Creamos el Customer para el buyer
		def b = new Customer(nickname:"TESTBUYER001")
		b.save()
		
		//Creamos un item al cual asociarle una pregunta
		def i = new Item(title:"Item de testeo para preguntar", siteId:"MLA", seller:s, status:"A", 
    							vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
    							mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
    				    		quantitySelled:5, offersReceived:1,quantityAvailable:2)
		i.save()
		
		//Creamos una pregunta a la cual poder responder
		questionToAnswer = new Question(questionText:"¿Disculpá tenés stock?", item:i,
											sender:s , receiver:b)
	
		questionToAnswer.save()

		//Creamos una segunda pregunta sobre la cual no vamos a hacer nada
		questionNotToAnswer = new Question(questionText:"¿Aceptás Mercadopago?",
									    	   item:i,	sender:s , receiver:b)
		
		questionNotToAnswer.save()
	}
	
	/*
	 * Chequea que la página de preguntas pendientes exista
	 */
    void testPendingQuestionsPageExists() {
       webtest("Como seller debería poder entrar a mi página de preguntas pendientes"){
	    	//Vamos a la pagina de preguntas pendientes
	    	goToPendingQuestions(sellerTest)
	    	   
	    	//Validamos que haya cargado el título
	    	validateTitle("Preguntas pendientes")   
       }
    }

    /*
     * Chequea que se listen todas las preguntas pendientes del seller
     */
    void testPendingQuestionsListed(){
    	webtest("Como seller dedo poder ver el listado de mis preguntas pendientes"){
    		//Vamos al listado de preguntas pendientes
    		goToPendingQuestions(sellerTest) 
			
			validateQuestionExistence(questionToAnswer)
			
			validateQuestionExistence(questionNotToAnswer)
    	}
    }
    
    /*
     * Chequea que se pueda responder una pregunta pendiente y la misma desaparezca
     */
	void testUpdatePendingQuestionListingWhenAnswering() {
		webtest("Como seller debo poder responder una pregunta pendiente y no verla más"){
    		//Vamos al listado de preguntas pendientes
			goToPendingQuestions(sellerTest)

			//Respondemos una pregunta del seller
			answerPendingQuestion(questionToAnswer, "Si nos queda bastante stock")

			//Chequeo que el listado de preguntas pendientes se haya actualizado
			validatePendingQuestionListingUpdated()
			
			//Volvemos a cargar la página
			goToPendingQuestions(sellerTest)
			
			//Chequeo que el listado de preguntas pendientes se haya actualizado
			validatePendingQuestionListingUpdated()
		}
	}
	
	
	 /*
     * Chequea que se pueda eliminar una pregunta pendiente y la misma desaparezca
     */
	void testUpdatePendingQuestionListingWhenDeleting() {
		webtest("Como seller debo poder eliminar una pregunta pendiente y no verla más"){
    		//Vamos al listado de preguntas pendientes
			goToPendingQuestions(sellerTest)

			//Respondemos una pregunta del seller
			deletePendingQuestion(questionToAnswer)

			//Chequeo que el listado de preguntas pendientes se haya actualizado
			validatePendingQuestionListingUpdated()
			
			//Volvemos a cargar la página
			goToPendingQuestions(sellerTest)
			
			//Chequeo que el listado de preguntas pendientes se haya actualizado
			validatePendingQuestionListingUpdated()
		}
	}
	
	
	/*
	 * Valida que el listado de preguntas pendiente esté actualizado, o sea la pregunta pendiente esté respondida
	 * y la no respondida siga apareciendo
	 */
	void validatePendingQuestionListingUpdated(){
		//Chequeo que el texto de la pregunta respondida no esté
		validateQuestionDisappeared(questionToAnswer)
		
		//Verificamos que la pregunta no respondida esté
		validatePendingQuestionExists(questionNotToAnswer)				
	}
	
    /*
     * Responde la pregunta questionToAnswerId con el texto answerText
     */
    void answerPendingQuestion(questionToAnswer, answerText){
		setInputField value:answerText, formName:"questionAnswerForm${questionToAnswer.id}",
						name:"responseText"
		clickButton name:"btnAnswer${questionToAnswer.id}"
		//TODO: Parche loco momentáneo para que pueda validar Ajax.... Pensar forma genérica 
		sleep(seconds:2)
    }
    
    /*
     * Elimina la pregunta questionToAnswer
     */
    void deletePendingQuestion(question){
		clickButton name:"btnDelete${question.id}"
		//TODO: Parche loco momentáneo para que pueda validar Ajax.... Pensar forma genérica 
		sleep(seconds:2)
    }
    
	/*
	 * Valida que la pregunta haya desaparecido
	 */
	void validateQuestionDisappeared(question){
		//Chequeo que el texto de la pregunta respondida no esté
		not{
			verifyXPath xpath:"//div[@id='qText$question.id']", text:question.questionText
		}
	}
	
	void validatePendingQuestionExists(question){
		//Verificamos que la pregunta no respondida esté
		verifyXPath xpath:"//div[@id='qText$question.id']", text:question.questionText
	}
	
	/*
	 * Va al listado de preguntas pendientes del seller
	 * cuyo id es sellerId
	 */
	void goToPendingQuestions(seller) {
		invoke "/itemPendingQuestions/pendingQuestions/${seller.id}"
	}
	
	/*
	 * Valida que el título haya sido generado en la página
	 */
	void validateTitle(title) {
		verifyTitle text:title
	}
	
	/*
	 * Valida que la pregunta exista en la página
	 */
	void validateQuestionExistence(question) {
		verifyText text:question.questionText
	}
}