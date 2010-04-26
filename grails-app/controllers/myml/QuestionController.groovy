package myml



class QuestionController {
	def questionService
	def defaultAction = 'pendingQuestions'
		
	def pendingQuestions = {
		[questions:questionService.getPendingQuestions(1)]
	}
	
	def answerQuestion = {
		questionService.answerQuestion(params.questionId as Long, params.responseText)
		render "ok"
	}
	
	def deleteQuestion = {
		questionService.deleteQuestion(params.questionId as Long)
		render "ok"
	}
	
	
	
}
