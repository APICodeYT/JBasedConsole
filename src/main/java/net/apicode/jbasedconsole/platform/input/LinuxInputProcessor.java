package net.apicode.jbasedconsole.platform.input;

import net.apicode.jbasedconsole.platform.Console;

public class LinuxInputProcessor implements Console.InputProcessor {

  private final Console console;

  public LinuxInputProcessor(Console console) {
    this.console = console;
  }

  @Override
  public Console getConsole() {
    return console;
  }

  @Override
  public ConsoleInput convert(char ch) {
    if(ch == 9) {
      return new ConsoleInput(ch, InputType.KEY_TAB);
    } else if(ch == 10) {
      return new ConsoleInput(ch, InputType.KEY_ENTER);
    } else if(ch == 127) {
      return new ConsoleInput(ch, InputType.KEY_DELETE);
    } else if(ch == 27) {
      char lresult = next();
      if(lresult == 91) {
        char result = next();
        if(result == 65) {
          return new ConsoleInput(result, InputType.KEY_ARROW_UP);
        } else if(result == 67) {
          return new ConsoleInput(result, InputType.KEY_ARROW_RIGHT);
        } else if(result == 66) {
          return new ConsoleInput(result, InputType.KEY_ARROW_DOWN);
        } else if(result == 68) {
          return new ConsoleInput(result, InputType.KEY_ARROW_LEFT);
        } else if(result == 50) {
          char vresult = next();
          if(vresult == 126) {
            return new ConsoleInput(vresult, InputType.KEY_PASTE);
          } else if(vresult == 48) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F9);
            }
          } else if(vresult == 49) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F10);
            }
          } else if(vresult == 50) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F11);
            }
          } else if(vresult == 51) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F12);
            }
          }
        } else if(result == 51) {
          char vresult = next();
          if(vresult == 126) {
            return new ConsoleInput(vresult, InputType.KEY_REMOVE);
          }
        } else if(result == 72) {
          return new ConsoleInput(result, InputType.KEY_POS1);
        } else if(result == 70) {
          return new ConsoleInput(result, InputType.KEY_END);
        } else if(result == 53) {
          char vresult = next();
          if(vresult == 126) {
            return new ConsoleInput(vresult, InputType.KEY_PIC_1);
          }
        } else if(result == 54) {
          char vresult = next();
          if(vresult == 126) {
            return new ConsoleInput(vresult, InputType.KEY_PIC_2);
          }
        } else if(result == 49) {
          char vresult = next();
          if(vresult == 53) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F5);
            }
          } else if(vresult == 55) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F6);
            }
          } else if(vresult == 56) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F7);
            }
          } else if(vresult == 57) {
            char bresult = next();
            if(bresult == 126) {
              return new ConsoleInput(bresult, InputType.KEY_F8);
            }
          }
        }
      } else if(lresult == 79) {
        char result = next();
        if(result == 80) {
          return new ConsoleInput(result, InputType.KEY_F1);
        } else if(result == 81) {
          return new ConsoleInput(result, InputType.KEY_F2);
        } else if(result == 82) {
          return new ConsoleInput(result, InputType.KEY_F3);
        } else if(result == 83) {
          return new ConsoleInput(result, InputType.KEY_F4);
        }
      } else {
        return new ConsoleInput((char)27, InputType.KEY_ESCAPE);
      }
    } else if(ch == 32) {
      return new ConsoleInput(ch, InputType.KEY_SPACE);
    }
    return new ConsoleInput(ch, null);
  }
}
