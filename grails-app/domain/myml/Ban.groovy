package myml

class Ban {	
	Customer bannedBy
	String banType
	Date dateCreated
	
	static belongsTo = [bannedUser:Customer]
	
    static constraints = {
		banType(inList:['TO_ASK','TO_BID'])
	}
	
	def banToAsk(Customer bannedBy, Customer bannedUser){
		banAction(bannedBy, bannedUser, 'TO_ASK')
	}
	
	def banToBid(Customer bannedBy, Customer bannedUser){
		banAction(bannedBy, bannedUser, 'TO_BID')		
	}
	
	def banAction(Customer bannedBy, Customer bannedUser, String banType){
		this.bannedBy = bannedBy
		this.bannedUser = bannedUser
		this.banType = banType
			
		save()
	}
}
