package net.apicode.jbasedconsole.text;

import java.util.Arrays;
import java.util.Objects;
import net.apicode.jbasedconsole.util.ConsoleCoordinate;
import net.apicode.jbasedconsole.util.ConsolePoint;

public class ConsoleStringBuilder implements ConsoleWritable {

  private final StringBuilder stringBuilder;

  public ConsoleStringBuilder() {
    this(new StringBuilder());
  }

  public ConsoleStringBuilder(String s) {
    this(new StringBuilder(s));
  }

  public ConsoleStringBuilder(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }

  public ConsoleStringBuilder append(String text) {
    stringBuilder.append(text);
    return this;
  }

  public ConsoleStringBuilder append(Object obj) {
    stringBuilder.append(obj);
    return this;
  }

  public <T> ConsoleStringBuilder append(T[] array) {
    stringBuilder.append(Arrays.toString(array));
    return this;
  }

  public ConsoleStringBuilder append(ConsoleWritable text) {
    stringBuilder.append(text.toConsoleString());
    return this;
  }

  public ConsoleStringBuilder foreground(ConsoleColor color) {
    stringBuilder.append(color.getForegroundColor());
    return this;
  }

  public ConsoleStringBuilder background(ConsoleColor color) {
    stringBuilder.append(color.getBackgroundColor());
    return this;
  }

  public ConsoleStringBuilder foreground(int r, int g, int b) {
    stringBuilder.append(ConsoleColor.fromRgb(r, g, b).getForegroundColor());
    return this;
  }

  public ConsoleStringBuilder background(int r, int g, int b) {
    stringBuilder.append(ConsoleColor.fromRgb(r, g, b).getBackgroundColor());
    return this;
  }

  public ConsoleStringBuilder foreground(int color256) {
    stringBuilder.append(ConsoleColor.from256Color(color256).getForegroundColor());
    return this;
  }

  public ConsoleStringBuilder background(int color256) {
    stringBuilder.append(ConsoleColor.from256Color(color256).getBackgroundColor());
    return this;
  }

  public ConsoleStringBuilder decoration(ConsoleDecoration decoration) {
    stringBuilder.append(decoration.getAnsiCode());
    return this;
  }

  public ConsoleStringBuilder reset() {
    return decoration(ConsoleDecoration.RESET);
  }

  public ConsoleStringBuilder bold() {
    return decoration(ConsoleDecoration.BOLD);
  }

  public ConsoleStringBuilder underline() {
    return decoration(ConsoleDecoration.UNDERLINE);
  }

  public ConsoleStringBuilder frame() {
    return decoration(ConsoleDecoration.FRAME);
  }

  public ConsoleStringBuilder reverse() {
    return decoration(ConsoleDecoration.REVERSED);
  }

  public ConsoleStringBuilder newLine() {
    stringBuilder.append("\n");
    return this;
  }

  public ConsoleStringBuilder positionMove(int x, int y) {
    stringBuilder.append("\u001b[").append(x).append(";").append(y).append("H");
    return this;
  }

  public ConsoleStringBuilder positionMove(ConsoleCoordinate point) {
    return positionMove(point.getColumn(), point.getLine());
  }

  public ConsoleStringBuilder positionMoveUp(int count) {
    stringBuilder.append("\u001b[").append(count).append("A");
    return this;
  }

  public ConsoleStringBuilder positionMoveDown(int count) {
    stringBuilder.append("\u001b[").append(count).append("B");
    return this;
  }

  public ConsoleStringBuilder positionMoveRight(int count) {
    stringBuilder.append("\u001b[").append(count).append("C");
    return this;
  }
  public ConsoleStringBuilder positionMoveLeft(int count) {
    stringBuilder.append("\u001b[").append(count).append("D");
    return this;
  }

  @Override
  public String toString() {
    return toConsoleString();
  }

  @Override
  public String toConsoleString() {
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ConsoleStringBuilder that = (ConsoleStringBuilder) o;
    return Objects.equals(stringBuilder, that.stringBuilder);
  }

  @Override
  public int hashCode() {
    return stringBuilder != null ? stringBuilder.hashCode() : 0;
  }

  public ConsoleStringBuilder clone() {
    return new ConsoleStringBuilder(stringBuilder.toString());
  }
}
