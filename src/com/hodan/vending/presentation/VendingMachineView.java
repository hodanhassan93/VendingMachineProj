package com.hodan.vending.presentation;
import java.math.BigDecimal;
import java.util.Map;


public class VendingMachineView {
    
    private VendingMachineUI ui;
    
    public VendingMachineView (VendingMachineUI ui){
    	super();
        this.ui = ui;
    }

    public BigDecimal getMoney() {
        return ui.readBigDecimal("Please input the amount money in dollars before making selection");
    }
    
    public void displayMenuBanner() {
        ui.print("=== Menu ===");
    }
    
    public void displayMenu(Map<String, BigDecimal> itemsInStockWithCosts){
        itemsInStockWithCosts.entrySet().forEach(entry ->{
        System.out.println(entry.getKey() + ": $" +entry.getValue());
        });
    }
    
    public String getItemSelection(){
        return ui.readString("Please select an item from the menu above or 'exit' to quit");
    }

    public void displayEnjoyBanner(String name) {
        ui.print("Here is your change.");
        ui.print("Enjoy your " + name + "!");
    }
    
    public void displayInsufficientFundsMsg(BigDecimal money){
        ui.print("Insufficent funds, you only have input $"+ money);
    }
    
    public void displayItemOutOfStockMsg(String name){
        ui.print("Error, " + name + " is out of stock.");
    }   

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + "c : " +entry.getValue());
         });
    }

    public void displayExitBanner() {
        ui.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        ui.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage (String errorMsg) {
        ui.print("=== Error ===");
        ui.print(errorMsg);
    }
    
    public void displayPleaseTryAgainMsg() {
        ui.print("Please select something else.");
    }


}
