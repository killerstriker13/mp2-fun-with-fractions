import java.lang.Character;

/** 
 * BigFraction Calculator: Handles arithmetic operations for BigFractions
 * @author Shibam Mukhopadhyay 
 * @author Samuel A. Rebelsky (Implemented code from instructions)
*/

public class BFCalculator {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+
  // Stores the last computed result
  BigFraction lastComputed;
  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+
  public BFCalculator(){
    this.lastComputed = new BigFraction(0, 1);
  }
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
  // gets the last computed value
  public BigFraction get() {
    return this.lastComputed;
  } 
  // adds `val` the last computed value 
  public void add(BigFraction val) {
    this.lastComputed = this.get().add(val);
  }
  // subtracts `val` from the last computed value
  public void subtract(BigFraction val) {
    this.lastComputed = this.get().sub(val);
  }

  // multiplies `val` to the last computed value
  public void multiply(BigFraction val) {
    this.lastComputed = this.get().multiply(val);
  }

  // divides the last computed value by `val`
  public void divide(BigFraction val) {
    this.lastComputed = this.get().divide(val);
  }

  // resets last computed value to 0
  public void clear() {
    this.lastComputed = new BigFraction(0, 1);
  }

  // splits the string by the space character into a string array
  public static String[] stringSplits(String strExpr) {
    String[] strings = strExpr.split(" ");
    return strings;
  }
  // checks if the string is a letter
  public static boolean isAlpha(String str) {
    if (str.length() == 1 && Character.isLetter(str.charAt(0))) {
      return true;
    } else {
      return false;
    }
  }
  // checks if an expression is a register or a number
  public static BigFraction decide(String str, Registers register) {
    BigFraction result;
    if (isAlpha(str)) {
      result = register.get(str.charAt(0));
    } else {
      result = new BigFraction(str);
    }
    return result;
  }

  // evaluates an expression
  public void eval(String[] strings, Registers register) {
    this.clear();
    BigFraction curTotal = null;
    for (int i = 0; i < strings.length; i++) {
        String token = strings[i];
        if (i == 0) {
            // First token can be either a value or a variable/command
            curTotal = decide(token, register);
            continue; // Skip to the next iteration
        }
        if (i % 2 == 1) {
            // Odd indices are operators
            BigFraction curVal = decide(strings[i + 1], register); // Get the operand
            switch (token.charAt(0)) {
                case '+':
                    curTotal = curTotal.add(curVal);
                    break;
                case '-':
                    curTotal = curTotal.sub(curVal);
                    break;
                case '*':
                    curTotal = curTotal.multiply(curVal);
                    break;
                case '/':
                    curTotal = curTotal.divide(curVal);
                    break;
            }
        } else {
            // Even indices can be the "STORE" command
            if (token.equals("STORE")) {
                char letter = strings[i + 1].charAt(0);
                register.store(letter, curTotal);
            }
        }
    }
    if (curTotal != null) {
        this.lastComputed = curTotal; // Update the lastComputed value
    }
  }
}