package myml

import java.util.Collection;

class Question {
	String questionText
	Date dateCreated
	Boolean processed = false
	Customer sender
	Customer receiver
	static belongsTo = [item:Item]
	                    
	static constraint = {
		questionText(maxSize:2000)
	}
	
	void answer(String answerText){
		new Answer(answerText:answerText, question:this).save()
		
		new QuestionAction(actionType:"ANSWER", question:this).save()
		
		markAsProcessed() 
	}
	
	void markAsDelete(){
		new QuestionAction(actionType:"DELETE", question:this).save()
		
		markAsProcessed()
	}
	
	private void markAsProcessed(){
		processed = true
		save()
	}
}
