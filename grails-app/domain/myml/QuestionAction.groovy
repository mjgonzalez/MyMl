package myml

class QuestionAction {
	String actionType
	Date dateCreated
	static belongsTo = [question:Question]
	                    
	static constraints = {
		actionType(inList:["ANSWER","DELETE"])
	}
}
