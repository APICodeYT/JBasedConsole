package net.apicode.jbasedconsole.text;

public class ConsoleDecoration {

  public static final ConsoleDecoration BOLD = new ConsoleDecoration("\u001b[1m");
  public static final ConsoleDecoration UNDERLINE = new ConsoleDecoration("\u001b[4m");
  public static final ConsoleDecoration REVERSED = new ConsoleDecoration("\u001b[7m");
  public static final ConsoleDecoration RESET = new ConsoleDecoration("\u001b[0m");
  public static final ConsoleDecoration STRIKE = new ConsoleDecoration("\u001b[9m");
  public static final ConsoleDecoration UNDERLINE_BOLD = new ConsoleDecoration("\u001b[21m");
  public static final ConsoleDecoration FRAME = new ConsoleDecoration("\u001b[52m");

  private final String ansiCode;

  private ConsoleDecoration(String ansiCode) {
    this.ansiCode = ansiCode;
  }

  public String getAnsiCode() {
    return ansiCode;
  }

  @Override
  public String toString() {
    return ansiCode;
  }

  public static ConsoleDecoration getDecoration(int id) {
    return new ConsoleDecoration(String.format("\u001b[%dm", id));
  }
}
