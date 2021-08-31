package io.diffblue.model;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Simulator implements AutoCloseable {

  private final RandomDataSource randomDataSource = new RandomDataSource();

  private final ExecutorService executor = Executors.newCachedThreadPool();

  private final DataInputStream dataInputStream = new DataInputStream(
      System.in);

  private final int workerCount;
 

  public Simulator(int workerCount) {
    this.workerCount = workerCount;
    randomDataSource.waitUntilReady();
  }

  public void run() {
    System.out.println("Simulation started. Press ENTER to quit.");
    Stream.generate(this::startWorker).limit(workerCount)
        .collect(Collectors.toSet()).forEach(CompletableFuture::join);
    System.out.println("Simulation complete.");
  }

  private CompletableFuture<?> startWorker() {
    return CompletableFuture.runAsync(this::retrieveAndOutputData, executor);
  }

  private  void retrieveAndOutputData() {
    while (noKeyPressed()) {
      Date timestamp = new Date();
      double dataPoint = -1.0;
      
      synchronized (System.out) {
      try {
        dataPoint = randomDataSource.getNextDataPoint();
      } catch (IOException e) {
    	  e.printStackTrace();
      }      
    	  System.out.printf("%tc%10.3f%n", timestamp, dataPoint);
    	  System.out.flush();   
      }
     
			/*
			 * ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); PrintStream
			 * print = new PrintStream(byteStream); //PrintStream current = System.out;
			 * 
			 * System.setOut(print); System.out.printf("%tc%10.3f%n", timestamp, dataPoint);
			 * System.out.flush();
			 */     
    }
  }

  private boolean noKeyPressed() {
    try {
      return dataInputStream.available() == 0;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void close() throws Exception {
    executor.shutdown();
  }

}
