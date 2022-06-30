package net.apicode.jbasedconsole.platform.input;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;
import net.apicode.jbasedconsole.platform.Console;

public class InputReader implements Closeable {

  private final Consumer<ConsoleInput> inputConsumer;
  private final Thread thread;

  public InputReader(Consumer<ConsoleInput> inputConsumer) {
    if(inputConsumer == null) {
      throw new NullPointerException("inputConsumer");
    }
    this.inputConsumer = inputConsumer;
    this.thread = new Thread(this::run);
    this.thread.start();
  }

  private void run() {
    Console console = Console.getConsole();
    while (true) {
      ConsoleInput consoleInput = console.readConsoleInput();
      inputConsumer.accept(consoleInput);
    }
  }

  public boolean isInputReading() {
    return thread.isAlive();
  }

  @Override
  public void close() {
    if(isInputReading()) {
      thread.stop();
    }
  }
}
