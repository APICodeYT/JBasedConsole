package net.apicode.jbasedconsole.text;

public interface BackgroundColor {

  String getBackgroundColor();

  default String getBGColor() {
    return getBackgroundColor();
  }


}
