package myml

import grails.converters.JSON
import grails.converters.XML

class QuestionController {
	QuestionService questionService
	def defaultAction = 'pendingQuestions'
	def scaffold = true 
	
	def pendingQuestions = {
		[questions:questionService.getPendingQuestions(params.id as Long)]
	}
	
	def answerQuestion = {
		def answer = new Answer(params)
		
		if(answer.validate()){
			//arreglar esto :P
			questionService.answerQuestion(params.questionId as Long, params.responseText)
			render "hola"	
			
		}else{
			flash.message = "Error respondiendo una pregunta"
			return [answer: answer]
		}
		
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
