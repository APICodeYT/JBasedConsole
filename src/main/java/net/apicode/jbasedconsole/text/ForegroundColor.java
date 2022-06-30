package net.apicode.jbasedconsole.text;

public interface ForegroundColor {

  String getForegroundColor();

  default String getFGColor() {
    return getForegroundColor();
  }

}
