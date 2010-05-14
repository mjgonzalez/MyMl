package myml



class SellerPendingItemsWebTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

	def sellerItems
	def sellers

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Customer c1 = new Customer(nickname: "TEST_SELLER1")
//		Customer c2 = new Customer(nickname: "TEST_SELLER2")
		
		c1.save();
//		c2.save();
		
//    	sellers = [c1.id,c2.id]
    	
    	sellerItems = [new Item(title:"Libro Analisis Y Diseño De Sistemas De Kendall Y Kendall", siteId:"MLA", seller:c1, status:"P", 
							vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
							mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
				    		quantitySelled:5, offersReceived:1,quantityAvailable:2),
	                   new Item(title:"Notebook Hp 14,1 Serie Dv2000 Modelo Dv2320la", siteId:"MLA", seller:c1, status:"P", 
	                		vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84214347",
	                		mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84214347_300.jpg&v=I", currentPrice:2400,
	                		quantitySelled:0, offersReceived:1,quantityAvailable:1),
	                	new Item(title:"Mitsubishi Lancer Evolution IX - Verde Esmeralda", siteId:"MLA", seller:c1, status:"P", 
	    							vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213107",
	    							mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213107_3249.jpg&v=I", currentPrice:175,
	    				    		quantitySelled:0, offersReceived:0,quantityAvailable:2)]
    	
    	sellerItems*.save()
		
	}
	
   void test_01_WebTestUP() {
	   webtest ("Como host de la aplicacion debo tener mi server funcionando"){
	        invoke '/'
	        
	        assertEquals true, true		   
	   }
    }
   void test_02_ViewPendingItems() {
	   webtest("Como usuario vendedor debo poder mis articulos pendientes (que no fueron activados todavia)"){
		   goToPendingItemsUrl(sellerItems[0].seller.id)
			   
		   assertPendingItems(sellerItems)
	   }
   }
   void goToPendingItemsUrl(sellerId){
	   invoke "/pendingItems/list/${sellerId}"
   }
   void assertPendingItems(sellerItems){
		sellerItems.each { 
			verifyText text:it.title
			verifyText text:"(#$it.id)"
			
			verifyXPath xpath:"//img[@src='$it.mainImageURL']"
			verifyXPath xpath:"//a[@href='$it.vipURL']"
			verifyXPath xpath:"//span[@id='currentPrice$it.id']", text:it.currentPrice
			verifyXPath xpath:"//span[@id='quantityAvailable$it.id']", text:it.quantityAvailable
		}
   }

}