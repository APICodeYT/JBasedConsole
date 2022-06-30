package net.apicode.jbasedconsole.text;

import java.util.regex.Pattern;

public interface ConsoleWritable {

  String toConsoleString();

  default String toPlainString() {
    return convertToPlainString(toConsoleString());
  }

  static String convertToPlainString(String consoleString) {
    return consoleString.replaceAll(Pattern.quote("\u001b[") + ".*?" + Pattern.quote("m"), "");
  }

}
