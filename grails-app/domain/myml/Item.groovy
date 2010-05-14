package myml

import java.util.Collection;

class Item {
	String siteId
	String title
	Customer seller
	String status = "P"
	BigDecimal currentPrice
	String mainImageURL
	String vipURL
	Integer quantitySelled = 0
	Integer offersReceived = 0
	Integer quantityAvailable
	
	static constraints = {
		title(maxSize:60)
		siteId(maxSize:3)
		status(inList:["A","P"])
		mainImageURL(url:true)
		vipURL(url:true)
		quantitySelled(min:0)
		offersReceived(min:0)
		quantityAvailable(min:0)
	}
	
	Collection<Question> getPendingQuestions(){
		Question.findAllByItemAndProcessed(this,false)
	}
}
