package com.hodan.vending.client;
import com.hodan.vending.controller.*;

import com.hodan.vending.dao.*;
import com.hodan.vending.dto.*;
import com.hodan.vending.exceptions.*;
import com.hodan.vending.presentation.*;
import com.hodan.vending.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {
    public static void main(String[] args) {
    	//Maven's project lifecycle is defined but is flexible — you can change it if you need to but, for most projects, the predefined lifecycle is sufficient. 
    	//The lifecycle consists of several stages, which are known as goals. These goals are simply the kinds of actions (like compiling and running unit tests) that we need to take as we build a software project. 
    	//NetBeans hides much of this from us but these goals are run behind the scenes when we ask NetBeans to build and run our applications.

    	//We will be using the following goals extensively:

    	//compile: compiles the project source code
    	//test-compile: compiles the project test source code
    	//test: runs the project unit tests
    	//package: builds and packages the project
    	//install: installs the project package into the local .m2 repository (the project package can then be used in other projects)
    	
        ApplicationContext springContainer = new AnnotationConfigApplicationContext(VendingConfig.class);

        VendingMachineController controller = springContainer.getBean("controller", VendingMachineController.class);

        controller.run();
        //     VendingMachineUI io = new VendingMachineUIImpl();
        //     VendingMachineView view = new VendingMachineView(io);
        //     VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        //      VendingMachineDao dao = new VendingMachineDaoFileImpl();
        //      VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
              
         //     VendingMachineController controller = new VendingMachineController(view, service);
          	
         //     ApplicationContext springContainer = new AnnotationConfigApplicationContext(VendingConfig.class);
              
         //     VendingMachineView view = springContainer.getBean("view", VendingMachineView.class);
          //    VendingMachineServiceLayer service = springContainer.getBean("service", VendingMachineServiceLayerImpl.class);
              
          //    VendingMachineController controller = new VendingMachineController(view, service);
              
          //    controller.run();

    }
}