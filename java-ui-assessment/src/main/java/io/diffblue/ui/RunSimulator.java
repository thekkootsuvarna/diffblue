package io.diffblue.ui;

import java.util.concurrent.Callable;

import io.diffblue.model.Simulator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
public class RunSimulator implements Callable<Integer> {

  @Parameters(defaultValue = "console")
  private UserInterface userInterface;

  @Parameters(defaultValue = "1")
  private int workerCount;
  private String[] mainArgs;

  protected RunSimulator(String[] args) {
	try {
		mainArgs=args;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


@Override
  public Integer call() throws Exception {
	
	if(mainArgs.length>0) {
		System.out.println("Main Argument passed is : " + mainArgs[0]);
		if("console".equals(mainArgs[0]) || "both".equals(mainArgs[0])) {
			System.out.println("Creating simulator...");			
		    try (Simulator simulator = new Simulator(workerCount)) {
		      System.out.println("Simulator ready.");
		      simulator.run();
		    }		    
		}else if("gui".equals(mainArgs[0])) {
			
			System.out.println(" Argument is GUI.. Not processing anymore..");
		}
	}
    
	return 0;
  }

}
