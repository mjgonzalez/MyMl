package myml

class PendingItemsController {
    
    def defaultAction = 'list'
    def itemService
    	
    def list = {
    		[ sellerItems : itemService.getSellerPendingItems(params.id as Long) ]
    }
}
