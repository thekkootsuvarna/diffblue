# Java UI Assessment

This Maven project provides a template Java console application which uses the PicoCLI library to read command line arguments. After compilation, you can run the application from the project root directory as follows:

    mvn exec:java --define exec.mainClass=io.diffblue.ui.Main <console|gui|both> <number-of-workers>

The ultimate goal is an application with three modes: Console output only, GUI window output only, and output to both at the same time. While you are expected to create a new output window and refactor the application to support it, you should *not* change the behaviour of the simulation or the data source itself, even though you may have to edit those classes to apply your changes.

The `number-of-workers` parameter controls the number of parallel threads which output simulated sample data to the console. The GUI mode will be implemented by you in the scope of this exercise. In the original state of the project, all three modes print to the console. As an example, the following command runs the program with console output and 100 workers:

    mvn exec:java --define exec.mainClass=io.diffblue.ui.Main console 100

## Exercise 1

Refactor the code such that different UI front-ends can be activated dynamically based on the first input argument. Users should be able to specify `console`, `gui` or `both` to select the UI front-ends. There is no need to actually implement a `gui` type front-end yet, this will be specified in exercise 3. Thus if a users specifies `console` or `both`, the current console output should be shown, if they specify `gui` no output should be currently shown.

## Exercise 2

When using high worker numbers (e.g. 100), the console output occasionally gets mangled. See below for an example: 
    ...
    Fri Aug 13 18:04:48 CEST 2021   -30.853
    Fri Aug 13 18:04:49 CEST 2021   -27.773
    Fri Aug 13 18:04:49 CEST 2021   -30.102
    Fri Aug 13 18:04:49 CEST 2021   -34.376
    Fri Aug 13 18:04:49 CEST 2021   -36.994
    Fri Aug 13 18:04:49 CEST 2021   -33.481
    
    Fri Aug 13 18:04:49 CEST 2021   -37.662
    Fri Aug 13 18:04:49 CEST 2021   -39.399
    Fri Aug 13 18:04:49 CEST 2021   -43.777
    Fri Aug 13 18:04:49 CEST 2021   -46.860
    java.io.IOException
    	at io.diffblue.model.RandomDataSource.getNextDataPoint(RandomDataSource.java:22)
    	at io.diffblue.model.Simulator.retrieveAndOutputData(Simulator.java:44)
    Fri Aug 13 18:04:49 CEST 2021	at java.base/java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1800)
    	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
    	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
    	at java.base/java.lang.Thread.run(Thread.java:832)
       -43.313
    Fri Aug 13 18:04:49 CEST 2021    -1.000
    Fri Aug 13 18:04:49 CEST 2021   -42.200
    Fri Aug 13 18:04:49 CEST 2021   -44.932
    Fri Aug 13 18:04:49 CEST 2021   -47.707
    Fri Aug 13 18:04:49 CEST 2021   -46.042
    Fri Aug 13 18:04:49 CEST 2021   -44.726
    Fri Aug 13 18:04:49 CEST 2021   -48.478
    java.io.IOException
    	at io.diffblue.model.RandomDataSource.getNextDataPoint(RandomDataSource.java:22)
    Fri Aug 13 18:04:49 CEST 2021	at io.diffblue.model.Simulator.retrieveAndOutputData(Simulator.java:44)
    	at java.base/java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1800)
    	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
    	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
    	at java.base/java.lang.Thread.run(Thread.java:832)
       -47.028
    Fri Aug 13 18:04:49 CEST 2021    -1.000
    Fri Aug 13 18:04:49 CEST 2021   -44.488
    Fri Aug 13 18:04:49 CEST 2021   -45.573
    ...

Fix this problem in your refactored code, such that all data point lines and exceptions are printed correctly. Specifically, every exception stack trace should be followed by a data point of `-1.000`.

## Exercise 3

Implement a GUI mode for the application. We expect a Swing window with the following features:
    * A suitable chart plotting all data points live. A suitable third party library and chart should be picked by the candidate for this purpose.
    * The three modes `<console|gui|both>` should be selectable at application startup and behave as specified.
    * A text area in the Swing window which shows the same text output as the console.
    * It should be conveniently possible to hide and show the text area.
    * A stop button which terminates the simulation, but leaves the window open.
    * On closing the window, the simulation should be stopped. In particular, when running in `both` mode, the correct stop messages should be printed on the console and the program terminated.
