package myml



class QuestionController {
	QuestionService questionService
	def defaultAction = 'pendingQuestions'
		
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
}
