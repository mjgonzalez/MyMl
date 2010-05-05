package myml

class ItemService {
	def getSellerItems = {sellerId ->
		Item.findAllBySeller(Customer.get(sellerId))			
	}
}
