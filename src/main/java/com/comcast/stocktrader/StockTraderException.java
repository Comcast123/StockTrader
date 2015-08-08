package com.comcast.stocktrader;

public class StockTraderException extends Exception{
  
    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    public StockTraderException(String errorMessage){
      super(errorMessage);
    }
    
    public StockTraderException(String errorMessage,Throwable cause){
      super(errorMessage,cause);
    }
}
