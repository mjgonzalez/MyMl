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
		Question.findAllBySellerId(1)*.delete
				
		assertTrue questionService.getPendingQuestions(1).isEmpty()
	}

	/*
	 * Testeo responder pregunta
	 */
	void testNotPendingQuestions(){
//		mockDomain(Question)
//		mockDomain(Answer)
//		mockDomain(QuestionAction)
//		mockDomain(Item)
		
		Question.findAllBySellerId(1)*.delete
		
		Item testItem = new Item(siteId:"MLA", title:"Item de testeo")
		testItem.save()
		
		Question q = new Question()
		q.sellerId = 2
		q.askerNickname = "AGILE"
		q.questionText = "Tenes stock?"
		q.item = testItem
		
		q.save()
		
		assertFalse questionService.getPendingQuestions(2).isEmpty()
		
		String answerText = "Todo bien vos?"
		questionService.answerQuestion(q.id, answerText)
		
		QuestionAction answerAction = QuestionAction.findByQuestion(q)
		assertNotNull answerAction
		assertEquals "ANSWER", answerAction.actionType
		
		Answer answer = Answer.findByQuestion(q)
		assertNotNull answer
		assertEquals answerText, answer.answerText
	}
}