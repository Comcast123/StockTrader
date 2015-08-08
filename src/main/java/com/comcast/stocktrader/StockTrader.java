package com.comcast.stocktrader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/stockTrader")
public interface StockTrader {
  
  /**
   * Method to Buy the Stock form Stock Brokerage System
   * @param accountNumber
   * @param stockSymbol
   * @param stockAmount
   * @return
   * @throws Exception
   */
  @POST
  @Path("/buys/{accountNumber}/{stockSymbol}/{stockAmount}")
  @Consumes("text/plain")
  @Produces("text/plain")  
  public String buyStocks(@PathParam("accountNumber") Integer accountNumber,@PathParam("stockSymbol") String stockSymbol,@PathParam("stockAmount") Double stockAmount ) throws Exception;
  
  /**
   * Method to Sell the Stock form Stock Brokerage System
   * @param accountNumber
   * @param stockSymbol
   * @param stockAmount
   * @return
   * @throws Exception
   */
  @POST
  @Path("/sells/{accountNumber}/{stockSymbol}/{stockAmount}")
  @Consumes("text/plain")
  @Produces("text/plain")  
  public String sellStocks(@PathParam("accountNumber") Integer accountNumber,@PathParam("stockSymbol") String stockSymbol,@PathParam("stockAmount") Double stockAmount ) throws Exception;

}
