package com.comcast.stocktrader;

public class StockTraderTest {

  private Dispatcher dispatcher;
  /**
   * 
   */
  @BeforeMethod
  public void beforeMethod() {
  }

  /**
   * @throws Exception
   */
  
  @Test
  public void testBuyStocks() throws Exception {
   
    Integer accountNumber = new Integer("123456789");
    String stockSymbol = "CMCSA";
    Double stockAmount = new Double("100.00");
      MockHttpRequest request =
          get("/stockTrader/buys/" + accountNumber+stockSymbol+stockAmount).accept("text/plain").contentType(
              "text/plain");
      MockHttpResponse response = new MockHttpResponse();
      dispatcher.invoke(request, response);

      assertEquals(response.getStatus(), HttpStatus.SC_OK);

     
      String responseMessage =  response.getContentAsString();
      assertNotNull(responseMessage);
     
    
  }
  
  @Test
  public void testSellStocks() throws Exception {
   
    Integer accountNumber = new Integer("123456789");
    String stockSymbol = "CMCSA";
    Double stockAmount = new Double("100.00");
      MockHttpRequest request =
          get("/stockTrader/sells/" + accountNumber+stockSymbol+stockAmount).accept("text/plain").contentType(
              "text/plain");
      MockHttpResponse response = new MockHttpResponse();
      dispatcher.invoke(request, response);

      assertEquals(response.getStatus(), HttpStatus.SC_OK);

     
      String responseMessage =  response.getContentAsString();
      assertNotNull(responseMessage);
     
    
  }
}
