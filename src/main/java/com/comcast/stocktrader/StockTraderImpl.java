package com.comcast.stocktrader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockTraderImpl implements StockTrader {

  ConnectionPoolManager connectionPoolManager = null;
  

  /**
   * 
   */
  public String buyStocks(Integer accountNumber, String stockSymbol, Double stockAmount) throws Exception {
    
    if (null == accountNumber || "".equals(stockSymbol) || stockAmount == null) {
      throw new StockTraderException("Input values are missing or empty.");
    }
    String query1 = "update MainStockTable set bal_qty = bal_qty + ? where stockSymbol = ?";
    String query2 = "update PersonalStockTable set bal_qty = bal_qty + ? where accountNumber = ?";  
    
    return performTransction(accountNumber,stockSymbol,stockAmount,query1,query2);
  }


  public String sellStocks(Integer accountNumber, String stockSymbol, Double stockAmount) throws Exception {
    if (null == accountNumber || "".equals(stockSymbol) || stockAmount == null) {
      throw new StockTraderException("Input values are missing or empty.");
    }
    String query1 = "update MainStockTable set bal_qty = bal_qty - ? where stockSymbol = ?";
    String query2 = "update PersonalStockTable set bal_qty = bal_qty - ? where accountNumber = ?";  
    
    return performTransction(accountNumber,stockSymbol,stockAmount,query1,query2);
  }

  private String performTransction(Integer accountNumber, String stockSymbol, Double stockAmount,String query1,String query2) throws StockTraderException{
    String response = "";
    connectionPoolManager = new ConnectionPoolManager();
    Connection con = connectionPoolManager.getConnectionFromPool();
    try(PreparedStatement pstmt1 = con.prepareStatement(query1);
        PreparedStatement pstmt2 = con.prepareStatement(query2)
        ) {     
      con.setAutoCommit(false);
      pstmt1.setDouble(1, stockAmount.doubleValue());
      pstmt1.setString(2,stockSymbol);    
      int result1 = pstmt1.executeUpdate();
      
      pstmt2.setDouble(1, stockAmount.doubleValue());
      pstmt2.setInt(2,accountNumber.intValue());    
      int result2 = pstmt2.executeUpdate();
      
      if(result1>0 && result2 >0){
        con.commit();
        response = "Transaction successfully.";
      }else{
        con.rollback();
        response = "Transaction failure.";
      }
      
    } catch (Exception exp) {
      try {
        con.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      throw new StockTraderException("Exception occured while performing Transaction of " + stockSymbol
          + " for Account accountNumber " + accountNumber + " of amount " + stockAmount, exp);
    }   
    return response;
  }
}
