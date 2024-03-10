import java.io.PrintWriter;
/** 
 * Quick Calculator: Calculator through the Command-Line-Interface which 
 *                   handles multiple arguments in a single line
 * @author Shibam Mukhopadhyay 
 * @author Samuel A. Rebelsky (Implemented code from instructions)
*/
public class QuickCalculator {
  public static void main (String[] args) throws Exception {
    if (args.length < 1) { // error-checking if no argument is entered
      System.err.println("No expressions entered");
      System.exit(-1);
    }
    // Create a new instance of PrintWriter
    PrintWriter pen = new PrintWriter(System.out, true);
    // Create a new instance of register
    Registers register = new Registers();
    // Create a new instance of Calculator
    BFCalculator cal = new BFCalculator();
    String[] expressions = args;
    for (int i = 0; i < expressions.length; i++) {
      try {
        String[] expr = BFCalculator.stringSplits(expressions[i]);
        String first = expr[0];
        BigFraction result = InteractiveCalculator.evalu(first, expr, register, cal);
        if (!first.equals("STORE")) {
          printStringArr(expr, pen);
          pen.print(" = ");
          pen.println(result);
        } else {
          pen.print(expr[1]);
          pen.print(" = ");
          pen.println(result);
        }
      } catch(Exception ex) {
        System.err.println("'" + expressions[i] + "'" + " is invalid. Unable to compute.");  
      }
    }
  }

   
  // prints the strings in a String array
  public static void printStringArr(String[] strings, PrintWriter pen) {
    for (int i = 0; i < strings.length; i++) {
      if (i == strings.length-1) {
        pen.print(strings[i]);
      } else {
        pen.print(strings[i]);
        pen.print(" ");
      }
    }
    
  }
}
