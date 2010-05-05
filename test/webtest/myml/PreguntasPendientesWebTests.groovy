package myml

class PreguntasPendientesWebTests extends grails.util.WebTest {
	def questionService
	def questionToAnswerId
	def questionNotToAnswerId
	def sellerTestId = 1
	
	/*
	 * Inicializamos el test. 
	 */
	void setUp(){
		//Creamos un item al cual asociarle una pregunta
		def i = new Item(siteId:"MLA", title:"Item de testeo para preguntar")
		i.save(flush:true)
		
		//Creamos una pregunta a la cual poder responder
		def questionToAnswer = new Question(sellerId:sellerTestId, askerNickname:"TESTMLA001",
									questionText:"¿Disculpá tenés stock?", item:i)
	
		questionToAnswer.save(flush:true)
		
		//Nos guardamos el id de la pregunta a responder
		questionToAnswerId = questionToAnswer.id
		
		//Creamos una segunda pregunta sobre la cual no vamos a hacer nada
		def questionNotToAnswer = new Question(sellerId:sellerTestId, askerNickname:"TESTMLA001",
									questionText:"¿Aceptás Mercadopago?", item:i)
		
		questionNotToAnswer.save(flush:true)
		
		//Nos guardamos el id de la pregunta que no vamos a hacer nada
		questionNotToAnswerId = questionNotToAnswer.id
	}
	
	/*
	 * Rollback de lo que hizo el test.
	 */
	void tearDown(){
		Question.list()*.delete()
		Item.list()*.delete()
	}
	
	/*
	 * Chequea que la página de preguntas pendientes exista
	 */
    void testPendingQuestionsPageExists() {
       webtest("Como seller debería poder entrar a mi página de preguntas pendientes"){
	    	//Vamos a la pagina de preguntas pendientes
	    	goToPendingQuestions(sellerTestId)
	    	   
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
    		goToPendingQuestions(sellerTestId)
			
			//Chequeamos que se listen todas las preguntas del seller
	        def preguntasPendientes = getPendingQuestions(sellerTestId)
	        preguntasPendientes.each {
				chequearExistePregunta(it)
			}
    	}
    }
    
    /*
     * Chequea que se pueda responder una pregunta pendiente y la misma desaparezca
     */
	void testUpdatePendingQuestionListingWhenAnswering() {
		webtest("Como seller debo poder responder una pregunta pendiente y no verla más"){
    		//Vamos al listado de preguntas pendientes
			goToPendingQuestions(sellerTestId)

			//Respondemos una pregunta del seller
			answerPendingQuestion(questionToAnswerId, "Si nos queda bastante stock")

			//Chequeo que el listado de preguntas pendientes se haya actualizado
			validatePendingQuestionListingUpdated()
			
			//Volvemos a cargar la página
			irAPreguntasPendientes(sellerTestId)
			
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
		validateQuestionDisappeared("¿Disculpá tenés stock?")
		
		//Verificamos que la pregunta no respondida esté
		validatePendingQuestionExists(questionNotToAnswerId)				
	}
    /*
     * Responde la pregunta questionToAnswerId con el texto answerText
     */
    void answerPendingQuestion(questionToAnswerId, answerText){
		setInputField value:answerText, formName:"questionAnswerForm${questionToAnswerId}",
						name:"responseText"
		clickButton name:"btnAnswer${questionToAnswerId}"
    }
    
	/*
	 * Valida que la pregunta haya desaparecido
	 */
	void validateQuestionDisappeared(questionText){
		//Chequeo que el texto de la pregunta respondida no esté
		not{
			verifyText text:questionText
		}
	}
	
	void validatePendingQuestionExists(id){
		//Verificamos que la pregunta no respondida esté
		verifyText text:Question.get(id).questionText
	}
	
	/*
	 * Va al listado de preguntas pendientes del seller
	 * cuyo id es sellerId
	 */
	void goToPendingQuestions(sellerId) {
		 invoke "/question/pendingQuestions/${sellerId}"
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
	
	/*
	 * Devuelve el listado de preguntas pendientes para el sellerId
	 */
	void getPendingQuestions(sellerId){
		questionService.getPendingQuestions(sellerId)
	}
}