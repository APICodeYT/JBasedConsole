package net.apicode.jbasedconsole.util;

public final class ConsolePoint implements ConsoleCoordinate {

  private int x;
  private int y;

  public ConsolePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public ConsolePoint() {
    this(0, 0);
  }

  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public ConsolePoint setX(int x) {
    this.x = x;
    return this;
  }

  public ConsolePoint setY(int y) {
    this.y = y;
    return this;
  }

  public ConsolePoint addX(int value) {
    x += value;
    return this;
  }

  public ConsolePoint removeX(int value) {
    x -= value;
    return this;
  }

  public ConsolePoint multiplyX(int value) {
    x *= value;
    return this;
  }

  public ConsolePoint divideX(int value) {
    x /= value;
    return this;
  }

  public ConsolePoint addY(int value) {
    y += value;
    return this;
  }

  public ConsolePoint removeY(int value) {
    y -= value;
    return this;
  }

  public ConsolePoint multiplyY(int value) {
    y *= value;
    return this;
  }

  public ConsolePoint divideY(int value) {
    y /= value;
    return this;
  }

  public ConsolePoint add(int factor) {
    x += factor;
    y += factor;
    return this;
  }

  public ConsolePoint remove(int factor) {
    x -= factor;
    y -= factor;
    return this;
  }

  public ConsolePoint multiply(int factor) {
    x *= factor;
    y *= factor;
    return this;
  }

  public ConsolePoint divide(int factor) {
    x /= factor;
    y /= factor;
    return this;
  }

  public ConsolePoint add(ConsolePoint consolePoint) {
    x += consolePoint.x;
    y += consolePoint.y;
    return this;
  }

  public ConsolePoint remove(ConsolePoint consolePoint) {
    x -= consolePoint.x;
    y -= consolePoint.y;
    return this;
  }

  public ConsolePoint multiply(ConsolePoint consolePoint) {
    x *= consolePoint.x;
    y *= consolePoint.y;
    return this;
  }

  public ConsolePoint divide(ConsolePoint consolePoint) {
    x /= consolePoint.x;
    y /= consolePoint.y;
    return this;
  }

  public ConsolePoint reset() {
    x = 0;
    y = 0;
    return this;
  }

  public double distance(ConsolePoint consolePoint) {
    int x2 = consolePoint.x;
    int y2 = consolePoint.y;

    int xVal = x2-x;
    int yVal = y2-y;

    return Math.sqrt(xVal*xVal + yVal*yVal);
  }

  @Override
  public String toString() {
    return "ConsolePoint{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  public ConsolePoint clone() {
    return new ConsolePoint(x, y);
  }

  public ConsoleDimension toDimension() {
    return new ConsoleDimension(x, y);
  }

  @Override
  public int getColumn() {
    return x;
  }

  @Override
  public int getLine() {
    return y;
  }
}
