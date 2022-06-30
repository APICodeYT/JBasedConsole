package net.apicode.jbasedconsole.platform;

import net.apicode.jbasedconsole.system.SystemInformation;
import net.apicode.jbasedconsole.system.SystemInformation.OperationSystem;
import net.apicode.jbasedconsole.system.UnsupportedSystemException;
import net.apicode.jbasedconsole.text.BackgroundColor;
import net.apicode.jbasedconsole.text.ConsoleDecoration;
import net.apicode.jbasedconsole.text.ConsoleWritable;
import net.apicode.jbasedconsole.text.ForegroundColor;
import net.apicode.jbasedconsole.util.ConsoleCoordinate;
import net.apicode.jbasedconsole.util.ConsoleDimension;
import net.apicode.jbasedconsole.platform.input.ConsoleInput;

public interface Console {


  void write(String text);

  default void writeFormat(String text, Object...objects) {
    write(String.format(text, objects));
  }

  void writeLine(String text);

  default void writeLineFormat(String text, Object...objects) {
    writeLine(String.format(text, objects));
  }

  void write(ConsoleWritable consoleWritable);

  void writeLine(ConsoleWritable consoleWritable);

  void setForegroundColor(ForegroundColor foregroundColor);

  void setBackgroundColor(BackgroundColor backgroundColor);

  void setDecoration(ConsoleDecoration decoration);

  ConsoleDimension getSize();

  String readLine();

  char readInput();

  void resetInputBuffer();

  void setTitle(String title);

  String getTitle();

  void setCursorVisible(boolean b);

  boolean isCursorVisible();

  void systemClear();

  void clear();

  void reset();

  void moveCursor(ConsoleCoordinate point);

  void moveCursorUp(int num);

  void moveCursorDown(int num);

  void moveCursorLeft(int num);

  void moveCursorRight(int num);

  void clearLine();

  void saveCursorPoint();

  void loadCursorPoint();

  InputProcessor getInputProcessor();

  default ConsoleInput readConsoleInput() {
    InputProcessor inputProcessor = getInputProcessor();
    return inputProcessor.convert(readInput());
  }

  static Console getConsole() {
    SystemInformation current = SystemInformation.current();
    if(current.getOperationSystem() == OperationSystem.WINDOWS) {
      if(WindowsConsoleImpl.instance == null) {
        WindowsConsoleImpl.instance = new WindowsConsoleImpl();
      }
      return WindowsConsoleImpl.instance;
    } else if(current.getOperationSystem() == OperationSystem.LINUX) {
      if(LinuxConsoleImpl.instance == null) {
        LinuxConsoleImpl.instance = new LinuxConsoleImpl();
      }
      return LinuxConsoleImpl.instance;
    }
    throw new UnsupportedSystemException();
  }

  interface InputProcessor {

    Console getConsole();

    ConsoleInput convert(char start);

    default char next() {
      return this.getConsole().readInput();
    }

  }






}
