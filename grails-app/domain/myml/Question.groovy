package myml


class Question {
	Long id
	String questionText
	String answerText
	Long itemId
	Long askerId
	Long sellerId
	
	static mapping = {
		version false
		table 'questions'
	}
	
	static constraints = {
		answerText(nullable:true)
	}
}
