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