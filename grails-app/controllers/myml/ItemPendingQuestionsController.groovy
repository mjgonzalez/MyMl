package myml

import grails.converters.JSON
import grails.converters.XML

class ItemPendingQuestionsController {
	def questionService
	def itemService
	def defaultAction = 'pendingQuestions' 
	
	def pendingQuestions = {
		[items: itemService.getSellerItems(params.id as Long)]
	}
	
	def answerQuestion = {
		questionService.answerQuestion(params.questionId as Long, params.responseText)
		render "hola"
	}
	
	def deleteQuestion = {
		questionService.deleteQuestion(params.questionId as Long)
		render "ok"
	} 
}
