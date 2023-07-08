package com.hodan.vending.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hodan.vending.controller.VendingMachineController;
import com.hodan.vending.dao.VendingMachineDaoFileImpl;
import com.hodan.vending.presentation.VendingMachineUIImpl;
import com.hodan.vending.presentation.VendingMachineView;
import com.hodan.vending.service.VendingMachineAuditDaoFileImpl;
import com.hodan.vending.service.VendingMachineServiceLayerImpl;
import com.hodan.vending.test.VendingMachineDaoFileImplTest;
import com.hodan.vending.test.VendingMachineServiceLayerImplTest;

@Configuration
public class VendingConfig {
	//Dependency Injection (DI) is a design pattern that implements a form of inversion of control. In fact, these two terms are used interchangeably.

	//In this case, 'inversion' of control is that client objects are no longer responsible for instantiating the objects (also known as services) on which they depend. 
	//Instead, the dependencies are handed to (i.e. injected into) the client objects by some other entity. Dependencies are handed to the client either through constructors or setter methods.

	//Of course, we have been using this pattern for a while now. We have built our components so that their dependencies are handed to them via their constructors. We assemble (or wire) the application in our App class. 
	//This arrangement works pretty well, but our configuration is still hard-coded in our App class. If we want to change anything, we have to modify the App class and recompile. 
	//The Spring framework allows us to externalize this configuration into XML files or use annotations to define our DI.

	//Using dependency injection has the following advantages:

	//Allows for loose coupling between the client and the concrete implementation of the service.
	//Allows the externalization (to configuration files) of the system's configuration information. This allows for configuration changes without forcing a recompilation of the application.
	//Allows for more flexible parallel development -- developers can program against the interface and use stubbed or mock implementations while the real implementation of the component is being built.
	@Bean("dao")
	public VendingMachineDaoFileImpl vendingMachineDaoImpl() {
		return new VendingMachineDaoFileImpl();
	}
	
	@Bean("auditDao")
	public VendingMachineAuditDaoFileImpl vendingMachineAuditDaoImpl() {
		return new VendingMachineAuditDaoFileImpl();
	}
	
	@Bean("service")
	public VendingMachineServiceLayerImpl vendingMachineServiceLayerImpl() {
		return new VendingMachineServiceLayerImpl(vendingMachineAuditDaoImpl(),vendingMachineDaoImpl());
	}
	
	@Bean("userInterface")
	public VendingMachineUIImpl vendingMachineUIImpl() {
		return new VendingMachineUIImpl();
	}
	@Bean("view")
	public VendingMachineView vendingMachineView() {
		return new VendingMachineView(vendingMachineUIImpl());
	}
	
	@Bean("daoTest")
	public VendingMachineDaoFileImplTest vendingMachineDaoFileImplTest() {
		return new VendingMachineDaoFileImplTest(vendingMachineDaoImpl());
	}
	
	@Bean("serviceTest")
	public VendingMachineServiceLayerImplTest vendingMachineServiceLayerImplTest() {
		return new VendingMachineServiceLayerImplTest(vendingMachineDaoImpl(),vendingMachineAuditDaoImpl(),vendingMachineServiceLayerImpl());
	}
	
	@Bean("controller")
	public VendingMachineController vendingMachineController() {
		return new VendingMachineController(vendingMachineView(),vendingMachineServiceLayerImpl());
	}
	
    //VendingMachineDaoFileImpl testDao = new VendingMachineDaoFileImpl("VendingMachineTest.txt");  
   // String testAuditFile = "testAuditFile.txt";
    //VendingMachineAuditDaoFileImpl testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);
    //VendingMachineServiceLayerImpl
	
}
