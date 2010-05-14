package myml

import grails.test.GrailsUnitTestCase;
import myml.objectaction.*

class ObjectActionsManagerTest extends GrailsUnitTestCase {
	void testGetItemActions(){
		Customer seller = new Customer(nickname: "CARACHAUCHA")
		realStateSeller.save()
		
		Item realStateItem = new Item()
		realStateItem.siteId = "MLA"
		realStateItem.title = "La casa de cara de chaucha"
		realStateItem.seller = seller 
		realStateItem.status = "A"
		realStateItem.currentPrice = 345.12
		realStateItem.mainImageURL = "http://www.google.com"
		realStateItem.vipURL = "http://www.clarin.com"
		realStateItem.quantityAvailable = 100
		
		realStateItem.save()
		
		def actions = new ObjectActionsManager().getItemActions(realStateItem)
		
		actions[0].equals(new ModifyItemAction())
	}
}
