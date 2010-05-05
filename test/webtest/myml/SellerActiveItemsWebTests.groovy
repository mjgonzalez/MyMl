package myml

class SellerActiveItemsWebTests extends grails.util.WebTest{
	def sellerItems
	def sellerId

    void setUp(){
    	Customer c = new Customer(nickname:"TEST1")
    	
    	c.save()
    	
    	sellerId = c.id
    	
    	sellerItems = [new Item(title:"Libro Analisis Y Diseño De Sistemas De Kendall Y Kendall", siteId:"MLA", seller:c, status:"A", 
    							vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84213167",
    							mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84213167_3249.jpg&v=I", currentPrice:75,
    				    		quantitySelled:5, offersReceived:1,quantityAvailable:2),
    	                   new Item(title:"Notebook Hp 14,1 Serie Dv2000 Modelo Dv2320la", siteId:"MLA", seller:c, status:"A", 
    	                		vipURL:"http://www.mercadolibre.com.ar/jm/item?site=MLA&id=84214347",
    	                		mainImageURL:"http://img1.mlstatic.com/jm/img?s=MLA&f=84214347_300.jpg&v=I", currentPrice:2400,
    	                		quantitySelled:0, offersReceived:1,quantityAvailable:1)]
    	
    	sellerItems*.save()
    }
	
	void testSellerActiveItemsListed(){
    	webtest("Como seller dedo poder ver el listado de articulos activos"){
    		goToSellerActiveItems(sellerItems[0].seller.id)
    		
    		assertHasItemListed(sellerItems)
    	}
    }
    
    void goToSellerActiveItems(sellerId){
		 invoke "/activeItems/list/${sellerId}"
    }
    
    void assertHasItemListed(sellerItems){
    	sellerItems.each { 
    		verifyText text:it.title
    		verifyText text:"($it.id)"

    		verifyXPath xpath:"//img[@src='$it.mainImageURL']"
    		verifyXPath xpath:"//a[@href='$it.vipURL']"
    		verifyXPath xpath:"//div[@id='currentPrice$it.id']", text:it.currentPrice
    		verifyXPath xpath:"//div[@id='quantitySelled$it.id']", text:it.quantitySelled
    		verifyXPath xpath:"//div[@id='offersReceived$it.id']", text: it.offersReceived
    		verifyXPath xpath:"//div[@id='quantityAvailable$it.id']", text:it.quantityAvailable
    	}
    }
}