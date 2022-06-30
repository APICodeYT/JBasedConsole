package net.apicode.jbasedconsole.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import net.apicode.jbasedconsole.ConsoleException;
import net.apicode.jbasedconsole.platform.input.LinuxInputProcessor;
import net.apicode.jbasedconsole.system.NativeLibrary;
import net.apicode.jbasedconsole.text.BackgroundColor;
import net.apicode.jbasedconsole.text.ConsoleDecoration;
import net.apicode.jbasedconsole.text.ConsoleWritable;
import net.apicode.jbasedconsole.text.ForegroundColor;
import net.apicode.jbasedconsole.util.ConsoleCoordinate;
import net.apicode.jbasedconsole.util.ConsoleDimension;
import net.apicode.jbasedconsole.util.ConsolePoint;

class LinuxConsoleImpl extends NativeLibrary implements LinuxConsole {

  public static LinuxConsoleImpl instance;

  private final PrintStream stream;
  private final BufferedReader reader;
  private final InputProcessor inputProcessor;

  private String title = "console";
  private boolean cursorVisible = true;

  public LinuxConsoleImpl() {
    super("console", System::load);
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (Exception e) {
      throw new ConsoleException("Failed to load console encoding");
    }

    stream = System.out;
    reader = new BufferedReader(new InputStreamReader(System.in));
    inputProcessor = new LinuxInputProcessor(this);

    setTitle(title);
    systemClear();
  }

  @Override
  public void write(String text) {
    stream.print(text);
  }

  @Override
  public void writeLine(String text) {
    stream.println(text);
  }

  @Override
  public void write(ConsoleWritable consoleWritable) {
    stream.print(consoleWritable.toConsoleString());
  }

  @Override
  public void writeLine(ConsoleWritable consoleWritable) {
    stream.println(consoleWritable.toConsoleString());
  }

  @Override
  public void setForegroundColor(ForegroundColor foregroundColor) {
    write(foregroundColor.getForegroundColor());
  }

  @Override
  public void setBackgroundColor(BackgroundColor backgroundColor) {
    write(backgroundColor.getBackgroundColor());
  }

  @Override
  public void setDecoration(ConsoleDecoration decoration) {
    write(decoration.getAnsiCode());
  }

  @Override
  public ConsoleDimension getSize() {
    int[] size = getSize0();
    return new ConsoleDimension(size[0], size[1]);
  }

  @Override
  public String readLine() {
    try {
      return reader.readLine();
    } catch (IOException e) {
      throw new ConsoleException("Failed to read line", e);
    }
  }

  @Override
  public char readInput() {
    return readCharacter0();
  }

  @Override
  public void resetInputBuffer() {
    resetInputBuffer0();
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
    setTitle0(title);
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setCursorVisible(boolean b) {
    this.cursorVisible = b;
    setCursorVisible0(b);
  }

  @Override
  public boolean isCursorVisible() {
    return cursorVisible;
  }

  @Override
  public void systemClear() {
    try {
      new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
    } catch (IOException | InterruptedException e) {
      throw new ConsoleException("Failed to clear screen", e);
    }
  }

  @Override
  public void clear() {
    write("\u001b[" + 2 + "J");
    moveCursor(new ConsolePoint(0, 0));
  }

  @Override
  public void reset() {
    setDecoration(ConsoleDecoration.RESET);
  }

  @Override
  public void moveCursor(ConsoleCoordinate point) {
    writeFormat("\u001b[%d;%df", point.getColumn(), point.getLine());
  }

  @Override
  public void moveCursorUp(int num) {
    write("\u001b[" + num + "A");
  }

  @Override
  public void moveCursorDown(int num) {
    write("\u001b[" + num + "B");
  }

  @Override
  public void moveCursorLeft(int num) {
    write("\u001b[" + num + "D");
  }

  @Override
  public void moveCursorRight(int num) {
    write("\u001b[" + num + "C");
  }

  @Override
  public void clearLine() {
    write("\u001b[2K");
  }

  @Override
  public void saveCursorPoint() {
    write("\u001b[s");
  }

  @Override
  public void loadCursorPoint() {
    write("\u001b[u");
  }

  @Override
  public InputProcessor getInputProcessor() {
    return inputProcessor;
  }

  @Override
  public native void beep();

  private native int[] getSize0();

  private native char readCharacter0();

  private native void resetInputBuffer0();

  private native void setTitle0(String title);

  private native void setCursorVisible0(boolean b);



}
