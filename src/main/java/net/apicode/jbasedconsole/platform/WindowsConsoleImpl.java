package net.apicode.jbasedconsole.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import net.apicode.jbasedconsole.ConsoleException;
import net.apicode.jbasedconsole.platform.input.WindowsInputProcessor;
import net.apicode.jbasedconsole.system.NativeLibrary;
import net.apicode.jbasedconsole.system.SystemInformation;
import net.apicode.jbasedconsole.system.SystemInformation.OperationSystem;
import net.apicode.jbasedconsole.text.BackgroundColor;
import net.apicode.jbasedconsole.text.ConsoleDecoration;
import net.apicode.jbasedconsole.text.ConsoleWritable;
import net.apicode.jbasedconsole.text.ForegroundColor;
import net.apicode.jbasedconsole.util.ConsoleCoordinate;
import net.apicode.jbasedconsole.util.ConsoleDimension;
import net.apicode.jbasedconsole.util.ConsolePoint;

class WindowsConsoleImpl extends NativeLibrary implements WindowsConsole {

  public static WindowsConsole instance = null;

  private final PrintStream stream;
  private final BufferedReader reader;
  private final InputProcessor inputProcessor;

  private String title = "console";
  private boolean cursorVisible = true;
  private boolean consoleVisible = true;


  public WindowsConsoleImpl() {
    super("console", System::load);
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
      if(SystemInformation.current().getOperationSystem() == OperationSystem.WINDOWS) {
        try {
          new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
          throw new Error("Failed to set UTF-8 on windows");
        }
      }
    } catch (Exception e) {
      throw new ConsoleException("Failed to load console encoding");
    }

    stream = System.out;
    reader = new BufferedReader(new InputStreamReader(System.in));
    inputProcessor = new WindowsInputProcessor(this);

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
    int[] size0 = getSize0();
    return new ConsoleDimension(size0[0], size0[1]);
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
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
    writeFormat("\u001b[%d;%dH", point.getColumn(), point.getLine());
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
  public void setConsoleVisible(boolean b) {
    this.consoleVisible = b;
    setConsoleVisible0(b);
  }

  @Override
  public boolean isConsoleVisible() {
    return consoleVisible;
  }

  @Override
  public void setScrollbarVisible(boolean b) {
    setScrollbarVisible0(b);
  }

  @Override
  public void setMode(int mode) {
    setMode0(mode);
  }

  @Override
  public boolean isKeyPressed(int keycode) {
    return isKeyPressed0(keycode);
  }

  @Override
  public PixelRenderer getPixelRenderer() {
    return new PixelRenderer() {
      @Override
      public void draw(Pixel... pixels) {
        int size = pixels.length;
        int[] xArray = new int[size];
        int[] yArray = new int[size];

        int[] rArray = new int[size];
        int[] gArray = new int[size];
        int[] bArray = new int[size];

        for (int i = 0; i < pixels.length; i++) {
          Pixel pixel = pixels[i];
          xArray[i] = pixel.getX();
          yArray[i] = pixel.getY();
          rArray[i] = pixel.getRedColor();
          gArray[i] = pixel.getGreenColor();
          bArray[i] = pixel.getBlueColor();
        }
        drawPixels0(xArray, yArray, rArray, gArray, bArray);
      }

      @Override
      public void removeAll() {
        removePixels0();
      }
    };
  }

  @Override
  public InputProcessor getInputProcessor() {
    return inputProcessor;
  }

  @Override
  public native void flash();

  @Override
  public native void beep();

  private native int[] getSize0();

  private native char readCharacter0();

  private native void resetInputBuffer0();

  private native void setTitle0(String title);

  private native void setCursorVisible0(boolean b);

  private native void setConsoleVisible0(boolean b);

  private native void setScrollbarVisible0(boolean b);

  private native void setMode0(int mode);

  private native boolean isKeyPressed0(int keycode);

  private native void drawPixels0(int[] xCoords, int[] yCoords, int[] rColors, int[] gColors, int[] bColors);

  private native void removePixels0();




}
