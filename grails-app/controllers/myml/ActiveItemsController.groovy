package myml

class ActiveItemsController {
	def itemService
	
	def defaultAction = 'list'
	
	def list = {
		[sellerItems:itemService.getSellerActiveItems(params.id as Long)]		
	}
}
