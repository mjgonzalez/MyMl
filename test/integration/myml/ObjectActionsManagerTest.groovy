package myml

import grails.test.GrailsUnitTestCase;
import myml.objectaction.*
import myml.Item

class ObjectActionsManagerTest extends GrailsUnitTestCase {
	void testActiveRealStateItemActions(){
		Customer seller = new Customer(nickname: "CARACHAUCHA")
		seller.save()
		
		Item realStateItem = new Item()
		realStateItem.siteId = "MLA"
		realStateItem.title = "La casa de cara de chaucha"
		realStateItem.seller = seller 
		realStateItem.status = "A"
		realStateItem.currentPrice = 345.12
		realStateItem.mainImageURL = "http://www.google.com"
		realStateItem.vipURL = "http://www.clarin.com"
		realStateItem.quantityAvailable = 100
		realStateItem.mainCategory = Item.MainCategory.REALSTATE
		realStateItem.save()
		
		def actionManager = new ObjectActionsManager()
		
		def actions = actionManager.getItemActions(realStateItem)
		
		assertTrue actions[0].toString().equals("Modificar")
		
		realStateItem.status = "P"
			
		actions = actionManager.getItemActions(realStateItem)
		
		assertTrue actions.isEmpty()
	}
	
	void testActiveCoreItemActions(){
		Customer seller = new Customer(nickname: "CARACHAUCHA")
		seller.save()
		
		Item coreItem = new Item()
		coreItem.siteId = "MLA"
		coreItem.title = "La casa de cara de chaucha"
		coreItem.seller = seller 
		coreItem.status = "A"
		coreItem.currentPrice = 345.12
		coreItem.mainImageURL = "http://www.google.com"
		coreItem.vipURL = "http://www.clarin.com"
		coreItem.quantityAvailable = 100
		coreItem.mainCategory = Item.MainCategory.CORE
		coreItem.save()
		
		def actionManager = new ObjectActionsManager()
		
		def actions = actionManager.getItemActions(coreItem)
		
		assertTrue actions[0].toString().equals("Modificar")
		
		coreItem.status = "P"
			
		actions = actionManager.getItemActions(coreItem)
		
		assertTrue actions.isEmpty()
	}
	
	void testActiveMotorsItemActions(){
		Customer seller = new Customer(nickname: "CARACHAUCHA")
		seller.save()
		
		Item motorsItem = new Item()
		motorsItem.siteId = "MLA"
		motorsItem.title = "La casa de cara de chaucha"
		motorsItem.seller = seller 
		motorsItem.status = "A"
		motorsItem.currentPrice = 345.12
		motorsItem.mainImageURL = "http://www.google.com"
		motorsItem.vipURL = "http://www.clarin.com"
		motorsItem.quantityAvailable = 100
		motorsItem.mainCategory = Item.MainCategory.MOTORS
		motorsItem.save()
		
		def actionManager = new ObjectActionsManager()
		
		def actions = actionManager.getItemActions(motorsItem)
		
		assertTrue actions[0].toString().equals("Modificar")
		
		motorsItem.status = "P"
			
		actions = actionManager.getItemActions(motorsItem)
		
		assertTrue actions.isEmpty()
	}
	
	void testActiveServiceItemActions(){
		Customer seller = new Customer(nickname: "CARACHAUCHA")
		seller.save()
		
		Item serviceItem = new Item()
		serviceItem.siteId = "MLA"
		serviceItem.title = "La casa de cara de chaucha"
		serviceItem.seller = seller 
		serviceItem.status = "A"
		serviceItem.currentPrice = 345.12
		serviceItem.mainImageURL = "http://www.google.com"
		serviceItem.vipURL = "http://www.clarin.com"
		serviceItem.quantityAvailable = 100
		serviceItem.mainCategory = Item.MainCategory.SERVICE
		serviceItem.save()
		
		def actionManager = new ObjectActionsManager()
		
		def actions = actionManager.getItemActions(serviceItem)
		
		assertTrue actions[0].toString().equals("Modificar")
		
		serviceItem.status = "P"
			
		actions = actionManager.getItemActions(serviceItem)
		
		assertTrue actions.isEmpty()
	}
}
