package myml

import grails.test.GrailsUnitTestCase
import myml.QuestionService
import myml.Question

class QuestionServiceTest extends GroovyTestCase{
	QuestionService questionService
	
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
}