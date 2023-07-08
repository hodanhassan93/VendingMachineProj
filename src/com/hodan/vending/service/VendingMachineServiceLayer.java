package com.hodan.vending.service;


import com.hodan.vending.dto.Item;
import com.hodan.vending.exceptions.InsufficientFundsException;
import com.hodan.vending.exceptions.NoItemInventoryException;
import com.hodan.vending.exceptions.VendingMachinePersistenceException;

import java.math.BigDecimal;
import java.util.Map;


public interface VendingMachineServiceLayer {

    void checkIfEnoughMoney(Item item, BigDecimal inputMoney)throws 
            InsufficientFundsException;
    
    void removeOneItemFromInventory(String name) throws 
            NoItemInventoryException, 
            VendingMachinePersistenceException;
    
    Map<String, BigDecimal>  getItemsInStockWithCosts () throws 
            VendingMachinePersistenceException;

    Item getItem(String name, BigDecimal inputMoney) throws 
            InsufficientFundsException, 
            NoItemInventoryException, 
            VendingMachinePersistenceException;
    
    Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money);
    
}
