package myml
import myml.Question;

class QuestionService {
	boolean transactional = true
	
	def answerQuestion = {questionId, answerText->
		Question q = Question.get(questionId)

		q.answer(answerText)
	}
	
	def deleteQuestion = {questionId->
		Question q = Question.get(questionId)
		
		q.markAsDelete()
	}
}
