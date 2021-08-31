package io.diffblue.model;

import java.io.IOException;
import java.util.Random;

public class RandomDataSource {

  private static final double MAX_STEP = 5.0;

  private final Random random = new Random();

  private double currentValue = 0.0;

  public void waitUntilReady() {
    simulateRandomDelay();
  }

  public  double getNextDataPoint() throws IOException {
    simulateRandomDelay();
    double increment = random.nextDouble() * MAX_STEP;
    if (increment > 4.5) {
      throw new IOException();
    }

    if (random.nextBoolean())
      currentValue += increment;
    else
      currentValue -= increment;

    return currentValue;
  }

  private void simulateRandomDelay() {
    try {
      Thread.sleep(Math.abs(random.nextLong() % 1000L));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
