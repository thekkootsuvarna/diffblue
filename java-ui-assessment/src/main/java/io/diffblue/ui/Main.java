package io.diffblue.ui;

import picocli.CommandLine;

public class Main {

  public static void main(String[] args) {
	 System.exit(new CommandLine(new RunSimulator(args))
        .setCaseInsensitiveEnumValuesAllowed(true).execute(args));

  }

}
