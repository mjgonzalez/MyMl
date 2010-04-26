package myml

class QuestionAction {
	Long id
	String actionType
	Date creationDate
	static belongsTo = [question:Question]
}
