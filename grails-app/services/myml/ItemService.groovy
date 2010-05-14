package myml

class ItemService {
	def getSellerActiveItems = {sellerId ->
		Item.findAllBySellerAndStatus(Customer.get(sellerId), "A")			
	}
	
	def getSellerPendingItems = {sellerId ->
		Item.findAllBySellerAndStatus(Customer.get(sellerId), "P")
	}
}
