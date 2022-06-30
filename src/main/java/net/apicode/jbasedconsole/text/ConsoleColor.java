package net.apicode.jbasedconsole.text;

public class ConsoleColor implements BackgroundColor, ForegroundColor {

  public static ConsoleColor BLACK = new ConsoleColor(0, false);
  public static ConsoleColor RED = new ConsoleColor(1, false);
  public static ConsoleColor GREEN = new ConsoleColor(2, false);
  public static ConsoleColor YELLOW = new ConsoleColor(3, false);
  public static ConsoleColor BLUE = new ConsoleColor(4, false);
  public static ConsoleColor MAGENTA = new ConsoleColor(5, false);
  public static ConsoleColor CYAN = new ConsoleColor(6, false);
  public static ConsoleColor WHITE = new ConsoleColor(7, false);

  public static ConsoleColor BRIGHT_BLACK = new ConsoleColor(0, true);
  public static ConsoleColor BRIGHT_RED = new ConsoleColor(1, true);
  public static ConsoleColor BRIGHT_GREEN = new ConsoleColor(2, true);
  public static ConsoleColor BRIGHT_YELLOW = new ConsoleColor(3, true);
  public static ConsoleColor BRIGHT_BLUE = new ConsoleColor(4, true);
  public static ConsoleColor BRIGHT_MAGENTA = new ConsoleColor(5, true);
  public static ConsoleColor BRIGHT_CYAN = new ConsoleColor(6, true);
  public static ConsoleColor BRIGHT_WHITE = new ConsoleColor(7, true);

  private final String foregroundColorString;
  private final String backgroundColorString;
  
  private ConsoleColor(int color, boolean bright) {
    this(String.format(bright ? "\u001b[3%d;1m" : "\u001b[3%dm", color),
        String.format(bright ? "\u001b[4%d;1m" : "\u001b[4%dm", color));
  }

  private ConsoleColor(int color) {
    this(String.format("\u001b[38;5;%dm", color), String.format("\u001b[48;5;%dm", color));
  }

  private ConsoleColor(String foregroundColorString, String backgroundColorString) {
    this.foregroundColorString = foregroundColorString;
    this.backgroundColorString = backgroundColorString;
  }

  @Override
  public String getBackgroundColor() {
    return backgroundColorString;
  }

  @Override
  public String getForegroundColor() {
    return foregroundColorString;
  }

  public static ConsoleColor from256Color(int color) {
    return new ConsoleColor(color);
  }

  public static ConsoleColor fromRgb(int r, int g, int b) {
    return new ConsoleColor(String.format("\u001b[38;2;%d;%d;%dm", r, g, b),
        String.format("\u001b[48;2;%d;%d;%dm", r, g, b));
  }


}
