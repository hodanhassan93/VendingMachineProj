package com.hodan.vending.test;


import java.math.BigDecimal;
import com.hodan.vending.dao.*;
import com.hodan.vending.exceptions.InsufficientFundsException;
import com.hodan.vending.exceptions.NoItemInventoryException;
import com.hodan.vending.exceptions.VendingMachinePersistenceException;
import com.hodan.vending.service.*;
import com.hodan.vending.dto.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class VendingMachineServiceLayerImplTest {
    
    VendingMachineDaoFileImpl testDao = new VendingMachineDaoFileImpl("VendingMachineTest.txt");  
    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDaoFileImpl testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);
    VendingMachineServiceLayerImpl testService = new VendingMachineServiceLayerImpl(testAuditDao,testDao);
    
    
    public VendingMachineServiceLayerImplTest(VendingMachineDaoFileImpl testDao, VendingMachineAuditDaoFileImpl testAuditDao, VendingMachineServiceLayerImpl testService) {
		super();
		this.testDao = testDao;
		this.testAuditDao = testAuditDao;
		this.testService = testService;
	}

	public VendingMachineServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        
    }
    @Test
    public void testCheckIfEnoughMoney() {
        
        Item hariboClone = new Item("Crisps");
        hariboClone.setCost(new BigDecimal("1.10"));
        hariboClone.setInventory(9);
        
        BigDecimal enoughMoney = new BigDecimal("2.00");
        BigDecimal notEnoughMoney = new BigDecimal("1.09");
        
      
        try {
            testService.checkIfEnoughMoney(hariboClone, enoughMoney);
        } catch (InsufficientFundsException e){
            fail("There is sufficient funds, exception should not have been thrown");
        }
       
        try {
            testService.checkIfEnoughMoney(hariboClone, notEnoughMoney);
            fail("There insufficient funds, exception should have been thrown");
        } catch (InsufficientFundsException e){
        }
    }
    
    @Test
    public void testGetItemsInStockWithCosts() {
        
    }
    
    @Test
    public void testGetChangePerCoin(){
        
        Item hariboClone = new Item("Drinks");
        hariboClone.setCost(new BigDecimal("1.80"));
        hariboClone.setInventory(9);
        
        BigDecimal money = new BigDecimal("2.50");
        
        
        Map<BigDecimal, BigDecimal> expectedChangePerCoin = new HashMap<>();
        expectedChangePerCoin.put(new BigDecimal("0.25"), new BigDecimal("2"));
        expectedChangePerCoin.put(new BigDecimal("0.10"), new BigDecimal("2"));
       
        
      
        Map<BigDecimal, BigDecimal> changePerCoin = testService.getChangePerCoin(hariboClone, money);
        
        
        assertEquals(changePerCoin.size(), 2, "There should be two coins");
        

    }
    
    @Test
    public void testGetItem() throws InsufficientFundsException, VendingMachinePersistenceException, NoItemInventoryException {
       
        Item snickersClone = new Item("Sweets");
        snickersClone.setCost(new BigDecimal("0.80"));
        snickersClone.setInventory(0);
        BigDecimal money = new BigDecimal("3.00");
        
        Item malteasersClone = new Item("Drinks");
        malteasersClone.setCost(new BigDecimal("2.80"));
        malteasersClone.setInventory(testDao.getItemInventory("Drinks"));
        
        Item itemWanted = null;
        
        try {
            itemWanted = testService.getItem("Crisps", money);
            fail("The item wanted is out of stock.");
        }catch (NoItemInventoryException e) {
        }
        try {
            itemWanted = testService.getItem("Drinks", money);
        }catch (NoItemInventoryException e) {
            if (testDao.getItemInventory("Drinks")>0){
            fail("The item wanted is in stock.");
        } 

        
        assertNotNull(itemWanted, "change should not be null");
        assertEquals(itemWanted, malteasersClone,"The item retrieved should be drinks");
    }
    }
    
    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
       
        String name = "Crisps";
        
        
        try{
            
            testService.removeOneItemFromInventory(name);
            
            fail("There are no crisps left, exception should be thrown");
        } catch (NoItemInventoryException e) {  
        }
        
        String Drinks = "Drinks";
        try{
            
            testService.removeOneItemFromInventory(Drinks);
        } catch (NoItemInventoryException e) {
            if (testDao.getItemInventory(Drinks)>0){
                fail("Drinks are in stock, exception should not be thrown");
            }
        } 
    }
    
    
}

