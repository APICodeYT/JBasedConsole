
import net.apicode.jbasedconsole.platform.Console;
import net.apicode.jbasedconsole.platform.LinuxConsole;
import net.apicode.jbasedconsole.platform.WindowsConsole;
import net.apicode.jbasedconsole.platform.input.InputReader;
import net.apicode.jbasedconsole.text.ConsoleDecoration;
import net.apicode.jbasedconsole.text.ConsoleStringBuilder;

public class Test {

  public static void main(String[] args) {
    Console console = Console.getConsole();
    //if windows
    if(console instanceof WindowsConsole) {
      WindowsConsole windowsConsole = (WindowsConsole) console;

    }

    //if linux
    if(console instanceof LinuxConsole) {
      LinuxConsole linuxConsole = (LinuxConsole) console;

    }
    console.setTitle("Custom Title");
    console.write("Wait of keypress...");
    console.readInput();
    console.writeLine(new ConsoleStringBuilder()
        .foreground(10)
        .append("Green text")
        .reset()
    );
    new InputReader(consoleInput -> {
      console.writeLine(consoleInput.getType().name() + " is pressed.");
    });




  }

}
