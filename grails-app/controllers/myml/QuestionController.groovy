package myml

import grails.converters.JSON
import grails.converters.XML

class QuestionController {
	QuestionService questionService
	def defaultAction = 'pendingQuestions'
	def scaffold = true 
	
	def pendingQuestions = {
		[questions:questionService.getPendingQuestions(1)]
	}
	
	def answerQuestion = {
		questionService.answerQuestion(params.questionId as Long, params.responseText)
		render "hola"
	}
	
	def deleteQuestion = {
		questionService.deleteQuestion(params.questionId as Long)
		//un cambio
		render "ok"
	} 
	
	def restXML = {
		render Question.list() as XML
	}
	

	def restJSON = {
		render Question.list() as JSON
	}
}
