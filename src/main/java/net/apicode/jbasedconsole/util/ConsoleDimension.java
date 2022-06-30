package net.apicode.jbasedconsole.util;

public final class ConsoleDimension implements ConsoleCoordinate{

  private int height;
  private int width;

  public ConsoleDimension(int width, int height) {
    this.height = height;
    this.width = width;
  }

  public ConsoleDimension(ConsolePoint dimension) {
    this.height = dimension.getY();
    this.width = dimension.getX();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public ConsolePoint toPoint() {
    return new ConsolePoint(width, height);
  }

  @Override
  public int getColumn() {
    return width;
  }

  @Override
  public int getLine() {
    return height;
  }
}
