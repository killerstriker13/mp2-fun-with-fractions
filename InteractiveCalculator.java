import java.io.PrintWriter;
import java.util.Scanner;
/** 
 * Interactive Calculator: Calculator through the Command-Line-Interface which 
 *                         handles multiple line inputs
 * @author Shibam Mukhopadhyay 
 * @author Samuel A. Rebelsky (Implemented code from instructions)
*/
public class InteractiveCalculator {
  public static void main (String[] args) throws Exception {

    // Create new instance of the PrintWriter
    PrintWriter pen = new PrintWriter(System.out, true);
    // Create new instance of the register
    Registers register = new Registers();
    // Create a new instance of BFCalculator
    BFCalculator calc = new BFCalculator();
    // Create a new instance of the scanner and getting input
    Scanner eyes = new Scanner(System.in);
    printInstructions(pen);
    String[] input = getInput(eyes, pen);
    String first = input[0];
    while (!first.equals("QUIT")) {
      try {
        BigFraction result = evalu(first, input, register, calc);
        if (!first.equals("STORE")) {
          pen.println(result);
        }
      } catch (Exception ex) {
        System.err.println("Invalid Expression. Note Initial Instructions");
      }

      input = getInput(eyes, pen);
      first = input[0];
    }
    
    eyes.close();
    pen.close();
  }
  // gets input from the command line
  public static String[] getInput(Scanner eyes, PrintWriter pen) {
    pen.println("Enter space separated expression to continue or 'QUIT' to exit");
    pen.printf("> ");
    String input = eyes.nextLine();
    String[] strings = BFCalculator.stringSplits(input);
    return strings;
  }
  // evaluate function for InteractiveCalculator
  public static BigFraction evalu(String oper, String[] input, Registers register, BFCalculator cal) {
    if (oper.equals("STORE")) {
      char letter = input[1].charAt(0);
      register.store(letter, cal.get()); 
    } else {
      cal.eval(input, register);
    } 
    return cal.get();
  }
  // prints all instructions to use InteractiveCalculator
  public static void printInstructions(PrintWriter pen) {
    pen.println("Welcome to the Interactive Calculator! Please consider the rules below to use the Interactive Calculator.");
    pen.println("Ensure all operands and operators are separated by spaces, valid operators are '+','-','/','*'.");
    pen.println("Valid operands are whole numbers, fractions and letters if values have been stored.");
    pen.println("Expressions must alternate between operands and operators.");
    pen.println("STORE operations must be followed by a single letter character as the register.");
  }
}