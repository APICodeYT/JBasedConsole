package net.apicode.jbasedconsole.platform.input;


public class ConsoleInput {

  private final char input;
  private final InputType type;

  public ConsoleInput(char input, InputType type) {
    if(type == null) {
      if(Character.isLetter(input)) type = InputType.LETTER;
      else if(Character.isDigit(input)) type = InputType.NUMBER;
      else if(isNormalSymbol(input)) type = InputType.NORMAL_SYMBOL;
      else type = InputType.OTHERS;
    }
    this.input = input;
    this.type = type;
  }

  public char getInput() {
    return input;
  }

  public InputType getType() {
    return type;
  }

  private boolean isNormalSymbol(char character) {
    return character == '!' || character == '.' || character == '\"' || character == '§' || character == '$' ||
        character == '%' || character == '&' || character == '/' || character == '(' || character == ')' ||
        character == '=' || character == '-' || character == ';' || character == ',' || character == '\'' ||
        character == '?' || character == '\\' || character == '}' || character == '{' ||
        character == '[' || character == ']' || character == '<' || character == '>' || character == '|' ||
        character == '#' || character == '+' || character == '*' || character == '~' || character == 'ß' ||
        character == '_' || character == ':' || character == '°'|| character == '^';
  }
}
