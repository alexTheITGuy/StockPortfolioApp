/*
 *Class Name: IFT210
 *Author: Alex Ochoa
 *Date Created: 02/02/2025
 *Purpose: Final Course Project
 */
class TransactionHistory
{
   private String ticker;   // Will store the ticker of the stock or cash.
   private String transDate;// Date when the transaction occured.
   private String transType;// Will store the type of trnsaction DEPOSIT/WITHDRAW for Cash.
   private double qty;      // Quantity for the transaction. 
   private double costBasis;// Will store how much was paid for the stock. 
   
   //constructors: constructs objects and sets initial values. 
   //default constructor
   public TransactionHistory(){
       ticker = "N/A";
       transDate = "N/A";
       transType = "N/A";
       qty = 0.0;
       costBasis = 0.0;
    
}

    //behaviores
    @Override
    public String toString(){
        return "TransactionHistory{" + "ticker=" + ticker + ", transDate=" + transDate + ", transType=" + transType
                + ", qty=" + qty + ", costBasis=" + costBasis + '}';
    }
   
    //overloaded constructor
   public TransactionHistory(String ticker, String transDate, String transType, double qty, double costBasis){
       this.ticker = ticker;
       this.transDate = transDate;
       this.transType = transType;
       this.qty = qty;
       this.costBasis = costBasis;
       
   }
   
   //getters and setters for all attributes
    public String getTicker()
    {
        return ticker;
    }

    public void setTicker(String ticker)
    {
        this.ticker = ticker;
    }

    public String getTransDate()
    {
        return transDate;
    }

    public void setTransDate(String transDate)
    {
        this.transDate = transDate;
    }

    public String getTransType()
    {
        return transType;
    }

    public void setTransType(String transType)
    {
        this.transType = transType;
    }

    public double getQty()
    {
        return qty;
    }

    public void setQty(double qty)
    {
        this.qty = qty;
    }

    public double getCostBasis()
    {
        return costBasis;
    }

    public void setCostBasis(double costBasis)
    {
        this.costBasis = costBasis;
    }
   
} 
