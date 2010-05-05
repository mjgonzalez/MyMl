package myml

import java.util.Collection;

class Item {
	String siteId
	String title
	Customer seller
	
	static constraints = {
		title(maxSize:60)
		siteId(maxSize:3)
	}
	
	Collection<Question> getPendingQuestions(){
		Question.findAllByItemAndProcessed(this,false)
	}
}
