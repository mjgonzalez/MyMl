package myml

import grails.test.GrailsUnitTestCase
import myml.QuestionService
import myml.Question

class QuestionServiceTest extends GrailsUnitTestCase{
	def questionService
	
	/*
	 * Testea que si el usuario no tiene preguntas, el servicio no devuelva nada
	 */
	void testEmptyQuestions(){
		def sellerId = 1
		
		deleteAllQuestions(sellerId)
				
		assertNotHasPendingQuestions(sellerId)
	}

	/*
	 * Testeo responder pregunta
	 */
	void testNotPendingQuestions(){
		def sellerId = 2
		deleteAllQuestions(sellerId)
		
		Item testItem = new Item(siteId:"MLA", title:"Item de testeo")
		testItem.save()
		
		Question q = new Question()
		q.sellerId = sellerId
		q.askerNickname = "AGILE"
		q.questionText = "Tenes stock?"
		q.item = testItem
		
		q.save()
		
		assertHasPendingQuestions(sellerId)
		
		String answerText = "Todo bien vos?"
		
		questionService.answerQuestion(q.id, answerText)
		
		QuestionAction answerAction = QuestionAction.findByQuestion(q)
		assertNotNull answerAction
		assertEquals "ANSWER", answerAction.actionType
		
		Answer answer = Answer.findByQuestion(q)
		assertNotNull answer
		assertEquals answerText, answer.answerText
		
		assertNotHasPendingQuestions(sellerId)
	}
	
	/*
	 * Testeo eliminar una pregunta
	 */
	void testDeletePendingQuestion(){
		def sellerId = 2
		deleteAllQuestions(sellerId)
		
		Item testItem = new Item(siteId:"MLA", title:"Item de testeo")
		testItem.save()
		
		Question q = new Question()
		q.sellerId = sellerId
		q.askerNickname = "AGILE"
		q.questionText = "Haces envios?"
		q.item = testItem
		
		q.save()
		
		assertHasPendingQuestions(sellerId)
		
		questionService.deleteQuestion (q.id)
		
		QuestionAction answerAction = QuestionAction.findByQuestion(q)
		assertNotNull answerAction
		assertEquals "DELETE", answerAction.actionType
		
		assertNotHasPendingQuestions(sellerId)
	}
	
	boolean deleteAllQuestions(Long sellerId){
		Question.findAllBySellerId(sellerId)*.delete
	}
	
	boolean assertNotHasPendingQuestions(Long sellerId){
		assertTrue questionService.getPendingQuestions(sellerId).isEmpty()
	}
	
	boolean assertHasPendingQuestions(Long sellerId){
		assertFalse questionService.getPendingQuestions(sellerId).isEmpty()
	}
	
	/*
	 * Testea la respuesta sobre una pregunta que se encuentra en una lista de pendientes 
	 */
	void testAnswerOneQuestionOfAList(){
//		INVESTIGAR SOBRE MOCKS!!		
//		mockDomain(Answer)
//		mockDomain(Question)
//		mockDomain(QuestionAction)
//		mockDomain(Item)
		
		//Borramos todos los registros
		Question.findAllBySellerId(1)*.delete
		
		def i = new Item(siteId:"MLA", title:"Item de Testeo CASO Una sola pregunta")
		i.save()
		
		
		def question = new Question(sellerId:1, askerNickname:"TESTMLA003",
									questionText:"Tenés stock?", item:i)
		question.save()
		
		//
		def question2 = new Question(sellerId:1, askerNickname:"TESTMLA003",
									questionText:"Tenés stock?", item:i)
		question2.save()
		
		//Comprobamos que haya dos preguntas pendientes
		assertEquals 2, Question.findAllBySellerId(1).size()
		
		//Respondemos la pregunta
		questionService.answerQuestion(question.id, "No me queda más nada")
		
		//Comprobamos que exista la respuesta
		def answer = Answer.findByQuestion(question)
		
		assertEquals answer.answerText, "No me queda más nada"
		assertEquals answer.question.id, question.id
		
		//Comprobamos que exista la acción realizada
		def questionAction = QuestionAction.findByQuestion(question)
		assertEquals questionAction.actionType, "ANSWER"
		assertEquals questionAction.question.id, question.id
		
		//Comprobamos que quede 1 pregunta por responder
		assertEquals 1, questionService.getPendingQuestions(1).size()
	}
}