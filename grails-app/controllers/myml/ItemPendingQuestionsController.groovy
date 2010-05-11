package myml

import grails.converters.JSON
import grails.converters.XML

class ItemPendingQuestionsController {
	def questionService
	def itemService
	def defaultAction = 'pendingQuestions' 
	
	def pendingQuestions = {
		[items: itemService.getSellerActiveItems(params.id as Long)]
	}
	
	def answerQuestion = {
		questionService.answerQuestion(params.questionId as Long, params.responseText)
		render "hola"
	}
	
	def deleteQuestion = {
		questionService.deleteQuestion(params.questionId as Long)
		render "ok"
	} 
	
	def blockUser = {
		def q =  Question.get(params.idPregunta as Long)
		
		if(params.preguntar){
			def ban = new Ban()
			
			ban.banToAsk(q.receiver, q.sender)	
		}
		
		if(params.ofertar){
			def ban = new Ban()
			
			ban.banToBid(q.receiver, q.sender)
		}

		questionService.deleteQuestion(params.idPregunta as Long)
		render "ok"
	}
}
