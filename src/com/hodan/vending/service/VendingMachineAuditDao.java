package com.hodan.vending.service;

import com.hodan.vending.exceptions.VendingMachinePersistenceException;

public interface VendingMachineAuditDao {
	public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

}
