package com.hodan.vending.test;
import com.hodan.vending.dao.*;
import com.hodan.vending.dto.*;
import com.hodan.vending.exceptions.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class VendingMachineDaoFileImplTest {

    
    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTest");
    
    
    public VendingMachineDaoFileImplTest(VendingMachineDaoFileImpl testDao) {
		super();
		this.testDao = testDao;
	}
	public VendingMachineDaoFileImplTest() {
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
    public void testGetItem() throws VendingMachinePersistenceException {
        //ARRANGE
        Item snickersClone = new Item("Crisps");
        snickersClone.setCost(new BigDecimal("1.10"));
        snickersClone.setInventory(0);
        
        //ACT
        Item retrievedItem = testDao.getItem("Crisps");
        
        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be snickers");
        
    }
    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
    
        String itemName = "Drinks";

        int inventoryBefore = testDao.getItemInventory(itemName);
        
       
        testDao.removeOneItemFromInventory(itemName);
        
      
        int inventoryAfter = testDao.getItemInventory(itemName);
        
        assertTrue(inventoryAfter<inventoryBefore,"The inventory after should be less than before");
        assertEquals(inventoryAfter,inventoryBefore-1,"The inventory after should be one less than it was"
                + "before");
        
    }
    @Test
    public void testGetItemInventory() throws VendingMachinePersistenceException {
       
        String itemName = "Sweets";
        
        int retrievedInventory = testDao.getItemInventory(itemName);

        
        assertEquals(retrievedInventory,0,"There are 0 items of sweets left.");  
    }
    
    @Test
    public void testGetMapOfItemNamesInStockWithCosts() throws VendingMachinePersistenceException {
        
        
        Map<String,BigDecimal> itemsInStock = testDao.getMapOfItemNamesInStockWithCosts();
        

        assertFalse(itemsInStock.containsKey("Crisps"));
   
        assertEquals(itemsInStock.size(),6,"The menu list should contain 3 items.");
        assertTrue(itemsInStock.containsKey("Crisps") &&
                itemsInStock.containsKey("Drinks") &&
                itemsInStock.containsKey("Sweets"));
    }

}
