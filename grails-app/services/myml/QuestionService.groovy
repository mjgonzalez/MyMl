package myml
import myml.Question;



class QuestionService {
	boolean transactional = true
	
	Collection<Question> getPendingQuestions(Long sellerId){
		Question.findAllBySellerIdAndProcessed(sellerId, false)
	}
	
	void answerQuestion(Long questionId, String answerText){
		Question q = Question.get(questionId)
		
		Answer answer = new Answer(answerText:answerText, question:q)
		answer.save()
		
		QuestionAction action = new QuestionAction(actionType:"ANSWER", question:q)
		action.save()
		
		updateQuestion(q) 
	}
	
	void updateQuestion(Question q){
		q.processed = true
		q.save()
	}
	
	void deleteQuestion(Long questionId){
		Question q = Question.get(questionId)
		
		QuestionAction action = new QuestionAction(actionType:"DELETE", question:q)
		action.save()
		
		updateQuestion(q) 
	}
}
