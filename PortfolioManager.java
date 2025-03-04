/*
 *Class Name: IFT210
 *Author: Alex Ochoa
 *Date Created: 02/02/2025
 *Purpose: Final Course Project
 */

 import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class PortfolioManager
{
    // class leve references
    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<TransactionHistory> portfolioList = new ArrayList<TransactionHistory>();
    private static double cash = 0.0;

    public static void main(String[] args)
    {
     
    // MOVE SCANNER TO CLASS LEVEL.     
        
    //Creat the loop control variable.
    int choice = 0;
        
    //Create the loop that runs until zero (0) is chosen.
    do
    {
        //Show the menu and get user's choice.
        System.out.println("Alex Ochoa Brokerage Account\n");
        System.out.println("0 - Exit");
        System.out.println("1 - Deposit Cash");
        System.out.println("2 - Withdraw Cash");
        System.out.println("3 - Buy Stock");
        System.out.println("4 - Sell Stock");
        System.out.println("5 - Display Transaction History");
        System.out.println("6 - Display Portfolio");
        
        try
        {
        System.out.print("\nEnter option (0 to 6): ");
        choice = scan.nextInt();
        }
        catch(InputMismatchException ex){

            scan.nextLine();
            System.out.println("\nError. Please use numbers only, do not use symbols or text.");
            System.out.print("Press Enter to continue.");
            scan.nextLine();
            System.out.println();
            choice = -1;
            continue;
        }
        
        //Run code based on the user's choice.
        switch(choice)
        {
            case 0:
                System.out.println("\nGoodbye!");
                break;
            case 1:
                depositCash();
                break;
            case 2: 
                withdrawCash();
                break;
            case 3: 
                buyStock();
                break;
            case 4: 
                sellStock();
                break;
            case 5: 
                displayTransactionHistory();
                break;
            case 6:
                displayPortfolio();
                break;
            default:
                System.out.println("\nError. Please select from the menu.\n");
                break;
        }
    }
    while(choice != 0);
        
    }
    
    private static void depositCash(){
       //Get data and amount of cash.
       System.out.print("Enter the deposit date: ");
       String date = scan.next();
       System.out.print("Enter amount of deposit: ");
       double amount = scan.nextDouble();
       //Increase cash and make transaction item.
       cash += amount;
       TransactionHistory lineItem = new TransactionHistory("CASH", date, "DEPOSIT", amount, 1.0); 
       portfolioList.add(lineItem);
       
       //Blank line.
       System.out.println();
    }

    private static void buyStock()
    {
        //Get the stock info.
        System.out.print("Enter stock purchase date: ");
        String date = scan.next();
        System.out.print("Enter stock ticker: ");
        String ticker = scan.next();
        ticker = ticker.toUpperCase();
        System.out.print("Enter stock quantity: ");
        double quantity = scan.nextDouble();
        System.out.print("Enter stock cost (basis): ");
        double cost = scan.nextDouble();
        
        //Check to see if we have the money to purchase the stock.
        double totalCost = quantity * cost;
        if(totalCost > cash){
            System.out.println("Error. You do not have the cash for that purchase.\n");
        }
          
            else{
            
            //If we have the money, make the stock transaction for our transactionHistory.
            portfolioList.add(new TransactionHistory(ticker, date, "BUY", quantity, cost));
        
            //Deduct cash and make the cash transaction line item. 
            cash -= totalCost;  
            portfolioList.add(new TransactionHistory("CASH", date, "WITHDRAW", totalCost * -1, 1.0)); //-1 to make total cost negative. 
            }
        
        //blank line
        System.out.println();
    }

    private static void displayTransactionHistory()
    {
        //Shows the heading 
        System.out.println("\n\t\tAlex Ochoa Brokerage Account");
        System.out.println("\t\t===========================\n");
        System.out.printf("%-16s%-10s%10s%15s     %s%n", "Date", "Ticker", "Quantity", "Cost Basis", "Trans Type");
        System.out.println("==================================================================");
        
        //Show the list of transactions in the list
        for (TransactionHistory record : portfolioList)
        {
            String costBasis = String.format("$%.2f", record.getCostBasis());
            System.out.printf("%-16s%-10s%10.0f%15s     %s%n", record.getTransDate(), record.getTicker(),
                    record.getQty(), costBasis, record.getTransType());
        }
         System.out.println();
    }

    private static void displayPortfolio()
    {
       //Show header 
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss");
        System.out.println("\nPortfolio as of: " + dtf.format(LocalDateTime.now()));
        System.out.println("====================================\n");
        System.out.printf("%-8s%s%n", "Ticker", "Quantity");
        System.out.println("================\n");
        
        //show the cash
        System.out.printf("%-8s%.2f%n", "CASH", cash);
        
        //Show stocks
        ArrayList<String> stocks = new ArrayList<String>();
        for(TransactionHistory record : portfolioList){
            if(record.getTicker().equals("CASH"))
                continue;
            if(stocks.contains(record.getTicker()) == false)
            stocks.add(record.getTicker());
                
         }
        
        //Get the number of each stock.
        for(String stock : stocks){
            double total = 0.0;
            
            for(TransactionHistory record : portfolioList){
                if(record.getTicker().equals(stock)){
                    if(record.getTransType().equals("BUY"))
                        total += record.getQty();
                    else
                        total -= record.getQty();
                }
            }
            if(total > 0){
            //Show stocks quantity.
            System.out.printf("%-8s%.0f%n", stock, total);
            }
        }
        System.out.println();
}

    private static void withdrawCash()
    {
        //Get data and amount of cash.
       System.out.print("Enter the withdraw date: ");
       String date = scan.next();
       System.out.print("Enter amount of withdraw or enter 0 to exit: ");
       double amount = scan.nextDouble();
       if(amount > cash){
          System.out.println("Insufficient Funds");
          System.out.print("Enter amount of withdraw or enter 0 to exit: ");
            amount = scan.nextDouble();
       }
       //Decrease cash and make transaction item.
       cash -= amount;
       amount *= -1; 
       if(amount != 0){
       TransactionHistory lineItem = new TransactionHistory("CASH", date, "WITHDRAW", amount, 1.0); 
       portfolioList.add(lineItem);
       }
       
       //Blank line.
       System.out.println();
    }

    private static void sellStock() {

        // Get the date
        System.out.print("Enter transaction date: ");
        String date = scan.next();
        // Show stocks
        ArrayList<String> stocks = new ArrayList<>();
        for (TransactionHistory record : portfolioList) {
            if (record.getTicker().equals("CASH"))
                continue;
            if (!stocks.contains(record.getTicker()))
                stocks.add(record.getTicker());
        }
    
        // Display the number of each stock
        System.out.println("Your owned stocks:");
        for (String stock : stocks) {
            double total = 0.0;
            for (TransactionHistory record : portfolioList) {
                if (record.getTicker().equals(stock)) {
                    if (record.getTransType().equals("BUY"))
                        total += record.getQty();
                    else
                        total -= record.getQty();
                }
            }
            System.out.printf("%-8s%.0f%n", stock, total);
        }
    
        System.out.println();
        System.out.print("Which stock would you like to sell? (Enter 'exit' to cancel): ");
        String stockToSell = scan.next().toUpperCase();
    
        while (!stocks.contains(stockToSell)) {
            if (stockToSell.equalsIgnoreCase("EXIT")) {
                System.out.println("Sell transaction canceled.");
                return;
            }

            System.out.println("You do not own this stock. Please enter a valid stock.");
            System.out.print("Which stock would you like to sell? (Enter 'exit' to cancel): ");
            stockToSell = scan.next().toUpperCase();
        }
    
        // Get total owned quantity
        double totalOwned = 0.0;
        for (TransactionHistory record : portfolioList) {
            if (record.getTicker().equals(stockToSell)) {
                if (record.getTransType().equals("BUY"))
                    totalOwned += record.getQty();
                else
                    totalOwned -= record.getQty();
            }
        }
    
        // Get how many shares the user wants to sell
        System.out.print("How many shares would you like to sell? or enter 0 to exit: ");
        double qtyToSell = scan.nextDouble();

        if(qtyToSell == 0){
            System.out.println();
            return;
        }
    
        // Ensure the user has enough shares to sell
        while (qtyToSell > totalOwned || qtyToSell < 0) {
            System.out.println("Error: You do not have enough shares to sell that amount, or you have entered and invalid quantity.");
            System.out.print("Enter a valid quantity to sell, or enter 0 to exit: ");
            qtyToSell = scan.nextDouble();

            if(qtyToSell == 0){
                System.out.println();
                return;
            }
        }
    
        // Ask the user for the cost basis
        System.out.print("Enter the cost basis: ");
        double costBasis = scan.nextDouble();
    
    
        // Calculate cash received from the sale
        double totalSaleValue = qtyToSell * costBasis;
        cash += totalSaleValue;  
        
    
        // Create transaction records
        
        portfolioList.add(new TransactionHistory(stockToSell, date, "SELL", qtyToSell, costBasis));
        portfolioList.add(new TransactionHistory("CASH", date, "DEPOSIT", totalSaleValue, 1.0));
        
    
        // Print success message
        System.out.printf("\nSuccessfully sold %.0f shares of %s for $%.2f per share. Total cash added: $%.2f%n",
                qtyToSell, stockToSell, costBasis, totalSaleValue);
        System.out.println();
        }
    }
   
