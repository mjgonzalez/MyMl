package myml
import myml.Question;



class QuestionService {
	Collection<Question> getPendingQuestions(Long sellerId){
//		Question.findAll("from Question as q where q.sellerId = ?", sellerId)
		//OPCION 1:		Question.findAll("from Question as q where q.sellerId = ? and answerText is null", sellerId)
		//OPCION 2: Muy Agile y scriptosa
//		Question.createCriteria().list {
//			eq("sellerId", sellerId)
//			isNull("answerText")
//		}
		Question.list()
	}
	
	void answerQuestion(Long questionId, String answerText){
		Question q = Question.get(questionId)
		
		Answer answer = new Answer(answerText:answerText, question:q)
		answer.save()
		
		QuestionAction action = new QuestionAction(actionType:"ANSWER", question:q)
		action.save()
		
		q.action = action
		q.answer = answer
	}
	
	void deleteQuestion(Long questionId){
		Question q = Question.get(questionId)
		
		QuestionAction action = new QuestionAction(actionType:"DELETE", question:q)
		action.save()
		
		q.action 
	}
}
