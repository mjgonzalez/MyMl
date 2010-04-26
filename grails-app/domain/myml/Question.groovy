package myml


class Question {
	Long id
	String questionText
	String answerText
	Long itemIdas
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
