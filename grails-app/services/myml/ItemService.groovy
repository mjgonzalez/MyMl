package myml

class ItemService {
	def getSellerActiveItems = {sellerId ->
		Item.findAllBySellerAndStatus(Customer.get(sellerId), "A")			
	}
}
