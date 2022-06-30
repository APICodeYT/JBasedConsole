package net.apicode.jbasedconsole.platform;

import java.awt.Color;

public interface WindowsConsole extends Console {

  int ENABLE_ECHO_INPUT = 0x0004;
  int ENABLE_INSERT_MODE = 0x0020;
  int ENABLE_LINE_INPUT = 0x0002;
  int ENABLE_MOUSE_INPUT = 0x0010;
  int ENABLE_PROCESSED_INPUT = 0x0001;
  int ENABLE_QUICK_EDIT_MODE = 0x0040;
  int ENABLE_WINDOW_INPUT = 0x0008;
  int ENABLE_VIRTUAL_TERMINAL_INPUT = 0x0200;

  void setConsoleVisible(boolean b);

  boolean isConsoleVisible();

  void setScrollbarVisible(boolean b);

  void setMode(int mode);

  boolean isKeyPressed(int keycode);

  PixelRenderer getPixelRenderer();

  interface PixelRenderer {

    void draw(Pixel...pixels);

    void removeAll();

  }

  void flash();

  void beep();

  class Pixel {

    private final int x;
    private final int y;
    private final int r;
    private final int g;
    private final int b;

    public Pixel(int x, int y) {
      this(x, y, 255, 255, 255);
    }

    public Pixel(int x, int y, Color color) {
      this(x, y, color.getRed(), color.getGreen(), color.getBlue());
    }

    public Pixel(int x, int y, int r, int g, int b) {
      this.x = x;
      this.y = y;
      this.r = r;
      this.g = g;
      this.b = b;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getGreenColor() {
      return g;
    }

    public int getBlueColor() {
      return b;
    }

    public int getRedColor() {
      return r;
    }
  }

}
