package net.apicode.jbasedconsole.platform.input;

import net.apicode.jbasedconsole.platform.Console;
import net.apicode.jbasedconsole.platform.Console.InputProcessor;


public class WindowsInputProcessor implements InputProcessor {

  private final Console console;

  public WindowsInputProcessor(Console console) {
    this.console = console;
  }

  @Override
  public Console getConsole() {
    return console;
  }

  @Override
  public ConsoleInput convert(final char ch) {
    if(ch == 9) {
      return new ConsoleInput(ch, InputType.KEY_TAB);
    } else if(ch == 13) {
      return new ConsoleInput(ch, InputType.KEY_ENTER);
    } else if(ch == 8) {
      return new ConsoleInput(ch, InputType.KEY_DELETE);
    } else if(ch == 27) {
      return new ConsoleInput(ch, InputType.KEY_ESCAPE);
    } else if(ch == 32) {
      return new ConsoleInput(ch, InputType.KEY_SPACE);
    } else if(ch == 224) {
      char rc = next();
      if(rc == 72) {
        return new ConsoleInput(rc, InputType.KEY_ARROW_UP);
      } else if(rc == 77) {
        return new ConsoleInput(rc, InputType.KEY_ARROW_RIGHT);
      } else if(rc == 80) {
        return new ConsoleInput(rc, InputType.KEY_ARROW_DOWN);
      } else if(rc == 75) {
        return new ConsoleInput(rc, InputType.KEY_ARROW_LEFT);
      } else if(rc == 83) {
        return new ConsoleInput(rc, InputType.KEY_REMOVE);
      } else if(rc == 71) {
        return new ConsoleInput(rc, InputType.KEY_POS1);
      } else if(rc == 89) {
        return new ConsoleInput(rc, InputType.KEY_END);
      } else if(rc == 73) {
        return new ConsoleInput(rc, InputType.KEY_PIC_1);
      } else if(rc == 81) {
        return new ConsoleInput(rc, InputType.KEY_PIC_2);
      } else if(rc == 82) {
        return new ConsoleInput(rc, InputType.KEY_PASTE);
      } else if(rc == 133) {
        return new ConsoleInput(rc, InputType.KEY_F11);
      } else if(rc == 134) {
        return new ConsoleInput(rc, InputType.KEY_F12);
      } else {
        return new ConsoleInput(rc, null);
      }
    } else if(ch == 0) {
      char rc = next();
      if(rc == 59) {
        return new ConsoleInput(rc, InputType.KEY_F1);
      } else if(rc == 60) {
        return new ConsoleInput(rc, InputType.KEY_F2);
      } else if(rc == 61) {
        return new ConsoleInput(rc, InputType.KEY_F3);
      } else if(rc == 62) {
        return new ConsoleInput(rc, InputType.KEY_F4);
      } else if(rc == 63) {
        return new ConsoleInput(rc, InputType.KEY_F5);
      } else if(rc == 64) {
        return new ConsoleInput(rc, InputType.KEY_F6);
      } else if(rc == 65) {
        return new ConsoleInput(rc, InputType.KEY_F7);
      } else if(rc == 66) {
        return new ConsoleInput(rc, InputType.KEY_F8);
      } else if(rc == 67) {
        return new ConsoleInput(rc, InputType.KEY_F9);
      } else if(rc == 68) {
        return new ConsoleInput(rc, InputType.KEY_F10);
      } else {
        return new ConsoleInput(rc, null);
      }
    }
    return new ConsoleInput(ch, null);
  }

}
