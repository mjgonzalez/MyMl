package myml


class Question {
	Long sellerId
	String askerNickname
	String questionText
	Date dateCreated
	Boolean processed = false
	static belongsTo = [item:Item]
	     
	static constraint = {
		questionText(maxSize:2000)
		askerNickname(maxSize:30)
	}
}
