package myml

class Answer {
	Long id
	String answerText
	Calendar creationDate
	static belongsTo = [question:Question]
}
