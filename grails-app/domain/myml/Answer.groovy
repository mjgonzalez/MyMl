package myml

class Answer {
	String answerText
	Date dateCreated
	static belongsTo = [question:Question]
	                    
	static constraints = {
		answerText(maxSize:2000)
	}
}
