import java.math.BigInteger;
import java.lang.Character;
/**
 * BigFraction: A simple implementation of Fractions.
 * Implemented from Fraction Lab
 *
 * @author Samuel A. Rebelsky
 * @author Shibam Mukhopadhyay, Rommin Adl
 */
public class BigFraction {


  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
    simplify(this);
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Build a new fraction with numerator num and denominator denom.
   * 
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
    simplify(this);
  } // BigFraction(int, int)

  /**
   * Build a new fraction by parsing a string.
   *
   */
  public BigFraction(String str) {
    if (isNegative(str) && str.length() > 1) { // Add a check for string length
      BigFraction neg = new BigFraction(str.substring(1));
      neg = neg.multiply(new BigFraction(-1, 1));
      this.num = neg.numerator();
      this.denom = neg.denominator();
      return;
    }
    if (isNumeric(str)) {
      this.num = new BigInteger(str);
      this.denom = BigInteger.valueOf(1);
      return;
    }
    int index = str.indexOf("/");
    String numera = str.substring(0,index);
    String denomi = str.substring(index+1);
    this.num = new BigInteger(numera);
    this.denom = new BigInteger(denomi);
    simplify(this);
  } // BigFraction(str)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+
  public void simplify(BigFraction fraction) {
    BigInteger numera = fraction.numerator();
    BigInteger denomi = fraction.denominator();
    BigInteger gcdFactor = numera.gcd(denom);
    numera = numera.divide(gcdFactor);
    denomi = denomi.divide(gcdFactor);
    if (denomi.signum() == -1) {
      BigInteger neg = new BigInteger("-1");
      denomi = denomi.multiply(neg);
      numera = numera.multiply(neg);
    }
    fraction.num = numera;
    fraction.denom = denomi;
  }

  /**
   * @param strNum
   * returns true if string contains only numeric characters
   * @return boolean
   */
  public static boolean isNumeric(String strNum) {
    for (int i = 0; i < strNum.length(); i++) {
      char cur = strNum.charAt(i);
      boolean numeric = Character.isDigit(cur);
      if (!numeric) {
        return false;
      }
    }
    return true;
  }
  /**
   * @param strNum
   * returns true if string contains '-' sign before numbers
   * @return boolean
   */
  public static boolean isNegative(String strNum) {
    char sign = strNum.charAt(0);
    if (sign == '-') {
      return true;
    }
    return false;
  }

  /**
   * Express this fraction as a double.
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * Add the fraction `addMe` to this fraction.
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's 
    // denominator and addMe's denominator
    resultDenominator = this.denom.multiply(addMe.denom);
    // The numerator is more complicated
    resultNumerator = 
      (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));

    // Return the computed value
    return new BigFraction(resultNumerator, resultDenominator);
  } // add(BigFraction)

  /**
   * Subtract the fraction `subMe` to this fraction.
   * Works by making `subMe` negative and adding it
   */
  public BigFraction sub(BigFraction subMe) {
    BigInteger newNumerator = subMe.numerator().multiply(BigInteger.valueOf(-1));
    BigInteger newDenominator = subMe.denominator();
    BigFraction newSubMe;
    newSubMe = new BigFraction(newNumerator, newDenominator);
    return this.add(newSubMe);
  } // sub(BigFraction)

  /**
   * multiply the fraction `multiplyMe` to this fraction.
   */
  public BigFraction multiply(BigFraction multiplyMe) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;
    resultDenominator = this.denom.multiply(multiplyMe.denom);
    resultNumerator = this.num.multiply(multiplyMe.num);
    return new BigFraction(resultNumerator, resultDenominator);
  } // multiply(BigFraction)

  public BigFraction divide(BigFraction divMe) {
    BigFraction divider = new BigFraction(divMe.denominator(), divMe.numerator());
    return this.multiply(divider);
  } // divide(BigFraction)


  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()
  
  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()
  
  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    }
    if (this.denom.equals(BigInteger.valueOf(1))) {
      return this.num.toString();
    }
    return this.num + "/" + this.denom;
  } // toString()
} // class Fraction
