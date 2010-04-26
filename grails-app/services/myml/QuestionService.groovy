package myml
import myml.Question;



class QuestionService {
	Collection<Question> getPendingQuestions(Long sellerId){
		Question.findAll("from Question as q where q.sellerId = ? and answerText is null", sellerId)
	}
	
	void answerQuestion(Long questionId, String answerText){
		Question q = Question.get(questionId)
		q.answerText = answerText
		q.save()
	}
	
	void deleteQuestion(Long questionId){
		Question.get(questionId).delete()
	}
}
