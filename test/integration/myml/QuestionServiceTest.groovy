package myml

import grails.test.GrailsUnitTestCase
import myml.QuestionService
import myml.Question

class QuestionServiceTest extends GroovyTestCase{
	def questionService
	
	void testEmptyQuestions(){
		Question.findAllBySellerIdAndProcessed(1, false)
		
		assertTrue "No tiene preguntas", questionService.getPendingQuestions(1).isEmpty()
	}

	void testNotPendingQuestions(){
		Question.findAllBySellerId(1)*.delete
		
		assertTrue questionService.getPendingQuestions(1).isEmpty()
	}
}