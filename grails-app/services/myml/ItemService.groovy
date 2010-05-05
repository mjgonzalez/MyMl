package myml

class ItemService {
	def getSellerItems = {sellerId ->
		println Customer.list()
		Item.findAllBySeller(Customer.get(sellerId))			
	}
}
