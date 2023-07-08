package com.hodan.vending.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hodan.vending.dao.VendingMachineDao;
import com.hodan.vending.dao.VendingMachineDaoFileImpl;
import com.hodan.vending.dto.Item;
import com.hodan.vending.exceptions.InsufficientFundsException;
import com.hodan.vending.exceptions.NoItemInventoryException;
import com.hodan.vending.exceptions.VendingMachinePersistenceException;


public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    //The service layer is responsible for the business logic of an application. It sits between
    //the controller and DAOs.
    
    private VendingMachineAuditDaoFileImpl auditDao;
    private VendingMachineDaoFileImpl dao;

    public VendingMachineServiceLayerImpl(VendingMachineAuditDaoFileImpl auditDao, VendingMachineDaoFileImpl dao) {
    	super();
        this.auditDao = auditDao;
        this.dao = dao;
    }


	@Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws InsufficientFundsException {

        if (item.getCost().compareTo(inputMoney)==1) {
            throw new InsufficientFundsException (
            "ERROR: insufficient funds, you have only input "+ inputMoney);  
        }
    }
    
    @Override
    public Map<String, BigDecimal>  getItemsInStockWithCosts () throws VendingMachinePersistenceException{
         Map<String, BigDecimal> itemsInStockWithCosts = dao.getMapOfItemNamesInStockWithCosts();
         return itemsInStockWithCosts;
    }
    
    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemCost, money);
        return changeDuePerCoin;
    }
    
    @Override
    public Item getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item wantedItem = dao.getItem(name);   
        if (wantedItem == null) {
            throw new NoItemInventoryException (
                "ERROR: there are no " + name + "'s in the vending machine.");
        }
        
      
        checkIfEnoughMoney(wantedItem,inputMoney);
        
 
        removeOneItemFromInventory(name);
        return wantedItem;

    }
    
    
    public void removeOneItemFromInventory (String name) throws NoItemInventoryException, VendingMachinePersistenceException {
       
        if (dao.getItemInventory(name)>0) {
            dao.removeOneItemFromInventory(name);
          
            auditDao.writeAuditEntry(" One " + name + " removed");
        } else {
        
            throw new NoItemInventoryException (
            "ERROR: " + name + " is out of stock.");
        }
    }

}
    
