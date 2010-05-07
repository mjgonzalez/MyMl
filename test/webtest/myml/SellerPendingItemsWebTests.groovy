package myml



class SellerPendingItemsWebTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		Customer c1 = new Customer(nickname: "TEST_SELLER1")
		Customer c2 = new Customer(nickname: "TEST_SELLER2")
		
		
	}
	
   void testWebTestUP() {
        invoke '/'
        
        assertEquals true, true
    }

}