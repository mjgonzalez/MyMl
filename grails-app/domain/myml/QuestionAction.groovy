package myml

class QuestionAction {
	Long id
	String actionType
	Date dateCreated
	static belongsTo = [question:Question]
}
