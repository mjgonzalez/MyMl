package myml

import grails.test.GrailsUnitTestCase
import myml.QuestionService

class QuestionServiceTest extends GroovyTestCase{
	QuestionService questionService = new QuestionService()
	
	void testEmptyQuestions(){
		Question.findAllBySellerId(1)*.delete
		
		assertTrue questionService.getPendingQuestions(1).empty()
	}
}