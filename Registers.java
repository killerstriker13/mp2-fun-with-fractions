/* 
 * Registers: Stores past computed values in register array of the Quick and Interactive calculators
 * @author Shibam Mukhopadhyay 
 * @author Samuel A. Rebelsky (Implemented code from instructions)
*/
public class Registers {
  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+
  // Register of 26 BigFraction values
  BigFraction[] register;
  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+
  public Registers() {
    this.register = new BigFraction[26];
  }
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
  // stores val into a position using char
  public void store(char register, BigFraction val) {
    int base = (int) 'a';
    int pos = (int) Character.toLowerCase(register) - base;
    this.register[pos] = val;
  }
  // gets val from stored position using char
  public BigFraction get(char register) {
    int base = (int) 'a';
    int pos = (int) Character.toLowerCase(register) - base;
    return this.register[pos];
  }
}