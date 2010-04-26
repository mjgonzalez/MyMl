package myml


class Question {
	Long sellerId
	String askerNickname
	String questionText
	Calendar creationDate
	static belongsTo = [item:Item]
	static hasOne = [answer:Answer, action:QuestionAction]
}
