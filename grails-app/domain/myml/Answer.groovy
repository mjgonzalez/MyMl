package myml

class Answer {
	String answerText
	Calendar creationDate
	static belongsTo = [question:Question]
}
