package math;

/**
 * An object class to represent a complex number.
 * 
 * @author Ava Momberger, Rhea Morris, Eric Hernandez
 * @version 3/24/2021
 */
public class ComplexNumber
{
  private static String iString = "i";
  private static String zero = "0";

  private double real;
  private double img;

  /**
   * The constructor for Complex number.
   * 
   * @param real
   *          number in the expression
   * @param img
   *          the imaginary number in the expression
   */
  public ComplexNumber(final double real, final double img)
  {
    this.real = real;
    this.img = img;
  }

  /**
   * Get the real part.
   * 
   * @return double for the real part
   */
  public double getRealPart()
  {
    return real;
  }

  /**
   * Gets the imaginary part.
   * 
   * @return double for the imaginary part
   */
  public double getImaginaryPart()
  {
    return img;
  }

  /**
   * Parses the string into a complex number.
   * 
   * @param string
   *          of the expression
   * @return a complex number
   */
  public static ComplexNumber parse(final String string)
  {

    ComplexNumber result;
    String str = "";
    String real = "";
    String img = "";

    String[] strs = findUniary(string);
    
    str = strs[0];
    boolean hasUniary = (strs[1] != null);

    int i = 0;
    String num = "";
    while (i < str.length())
    {
      if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == '-')
      {
        int j = i;
        while (j < str.length() && (Character.isDigit(str.charAt(j)) || str.charAt(j) == '.'))
        {
          num = num + str.charAt(j);
          i = j;
          j++;
        }
        if (i - num.length() >= 0 && str.charAt(i - num.length()) == '-')
        {
          num = "-" + num;
        }
        if (num.equals("-"))
        {
          num = "-1";
        }
        if (i + 1 < str.length() && str.charAt(i + 1) == 'i')
        {
          i = i + 1;
          img = num;
        }
        else
        {
          real = num;
        }
      }
      num = "";
      i++;
    }

    if (real.isEmpty())
    {
      real = zero;
    }

    if (img.isEmpty() && string.contains(iString))
    {
      img = "1";
    }
    else if (img.isEmpty())
    {
      img = zero;
    }

    result = new ComplexNumber(Double.parseDouble(real), Double.parseDouble(img));

    if (!hasUniary)
    {
      return result;
    }
    return executeUniary(result, strs[1]);
  }

  /**
   * 
   */
  private static ComplexNumber executeUniary(ComplexNumber operand, String uniary)
  {
    if (uniary.equals("\u221A"))
    {
      return operand.sqrt();
    }
    else if (uniary.equals("Inv"))
    {
      return operand.inverse();
    }
    else if (uniary.equals("Con"))
    {
      return operand.conjugate();
    }
    else if (uniary.equals("LOG"))
    {
      return operand.log();
    }
    else if (uniary.equals("Re")) // real and img operator
    {
      return new ComplexNumber(operand.getRealPart(), 0);
    }
    else if (uniary.equals("Im"))
    {
      return new ComplexNumber(operand.getImaginaryPart(), 0);
    }
    else
    {
      return operand.exponent(Integer.parseInt(uniary));
    }
  }

  /**
   * Parse Helper Method.
   * 
   * @param str
   * @return
   */
  private static String[] findUniary(final String str)
  {
    String[] temp = new String[2];
    String string = str;
    if (string.contains("\u221A"))
    {
      // squareRoot
      temp[0] = string.substring(0, string.indexOf("\u221A"));
      temp[1] = string.substring(string.indexOf("\u221A") + 1);
    }
    else if (string.contains("Inv"))
    {
      // inverse
      temp[0] = str.substring(0, str.indexOf("Inv"));
      temp[1] = "Inv";
    }
    else if (string.contains("Con"))
    {
      temp[0] = string.substring(0, string.indexOf("Con"));
      temp[1] = "Con";
    }
    else if (string.contains("LOG"))
    {
      temp[0] = string.substring(0, string.indexOf("LOG"));
      temp[1] = "LOG";
    }
    else if (string.contains("^"))
    {
      temp[0] = string.substring(0, string.indexOf('^'));
      temp[1] = string.substring(string.indexOf('^') + 1);
    }
    else if (string.contains("Re")) // real operator
    {
      temp[0] = string.substring(0, string.indexOf("Re"));
      temp[1] = "Re";
    }
    else if (string.contains("Im")) // real operator
    {
      temp[0] = string.substring(0, string.indexOf("Im"));
      temp[1] = "Im";
    }
    else
    {
      temp[0] = string;
      temp[1] = null;
    }
    return temp;
  }

  /**
   * Adds two complex numbers.
   * 
   * @param other
   *          number to add to
   * @return complex number with the solution
   */
  public ComplexNumber add(final ComplexNumber other)
  {
    double r = getRealPart() + other.getRealPart();

    double i = getImaginaryPart() + other.getImaginaryPart();

    // returning the output complex number
    return new ComplexNumber(r, i);
  }

  /**
   * Finds the new complex number when raised to a power.
   * 
   * @param power
   *          the power that the base will be raised to.
   * @return new complex number raised to the power.
   */
  public ComplexNumber exponent(final int power)
  {
    ComplexNumber result = this;
    int pow = power;
    if (power == 1)
    {
      return result;
    }
    else if (pow == 0)
    {
      return ComplexNumber.parse("1");
    }

    while (pow > 1)
    {
      result = result.multiply(this);
      pow--;
    }
    return result;
  }

  /**
   * The log calculations.
   * 
   * @return complex number for the solution
   */
  public ComplexNumber log()
  {
    return new ComplexNumber(Math.log(mod()), arg());
  }

  /**
   * To subtract numbers.
   *
   * @param other
   *          number to be subtracted
   * @return the difference between the two numbers
   */
  public ComplexNumber subtract(final ComplexNumber other)
  {
    double r = getRealPart() - other.getRealPart();
    double i = getImaginaryPart() - other.getImaginaryPart();
    return new ComplexNumber(r, i);
  }

  /**
   * To multiply two numbers.
   *
   * @param other
   *          number to be multiplied
   * @return the product of the two numbers
   */
  public ComplexNumber multiply(final ComplexNumber other)
  {
    double r = (getRealPart() * other.getRealPart())
        + (getImaginaryPart() * other.getImaginaryPart() * -1);
    double i = (other.getImaginaryPart() * getRealPart())
        + (getImaginaryPart() * other.getRealPart());
    return new ComplexNumber(r, i);
  }

  /**
   * To divide two numbers.
   *
   * @param other
   *          number to be divided
   * @return the quotient of the two numbers
   */
  public ComplexNumber divide(final ComplexNumber other)
  {
    ComplexNumber numerator = this.multiply(other.conjugate());
    ComplexNumber denominator = other.multiply(other.conjugate());

    double realNum = numerator.getRealPart() / denominator.getRealPart();
    double imaginary = numerator.getImaginaryPart() / denominator.getRealPart();
    return new ComplexNumber(realNum, imaginary);
  }

  /**
   * Helper method to assist divide, gets the conjugate.
   * 
   * @return complex number
   */
  public ComplexNumber conjugate()
  {
    return new ComplexNumber(getRealPart(), getImaginaryPart() * -1);
  }

  /**
   * Calculate the inverse
   * 
   * @return the inverse of complex number
   */
  public ComplexNumber inverse()
  {
    double length = real * real + img * img;
    return new ComplexNumber(real / length, -img / length);
  }

  /**
   * Modulus of this Complex number.
   * 
   * @return |z| where z is this Complex number.
   */
  public double mod()
  {
    if (this.getRealPart() != 0 || this.getImaginaryPart() != 0)
    {
      return Math.sqrt(this.getRealPart() * this.getRealPart()
          + this.getImaginaryPart() * this.getImaginaryPart());
    }
    else
    {
      return 0d;
    }
  }

  /**
   * Argument of this Complex number.
   * 
   * @return arg(z) where z is this Complex number.
   */
  public double arg()
  {
    return Math.atan2(this.getImaginaryPart(), this.getRealPart());
  }

  /**
   * Calculates the square root of a complex number.
   * 
   * @return arg(z) where z is this Complex number.
   */
  public ComplexNumber sqrt()
  {
    if (this.getImaginaryPart() == 0 && this.getRealPart() > 0)
    {
      return new ComplexNumber(Math.sqrt(this.getRealPart()), 0);
    }
    if (this.getImaginaryPart() == 0 && this.getRealPart() < 0)
    {
      return new ComplexNumber(0, Math.sqrt(Math.abs(getRealPart())));
    }
    double r = Math.sqrt(this.mod());
    double theta = this.arg() / 2;
    return new ComplexNumber(r * Math.cos(theta), r * Math.sin(theta));
  }

  /**
   * Turns the decimal into a fraction.
   * 
   * @param num
   *          the decimal
   * @return the fraction form F
   */
  public String makeFraction(double num)
  {
    double n = 1;
    double d = 1;
    double error = 0.0001;
    boolean isNegative = num < 0;

    if (num % 1 == 0)
      return "" + (int) num;
    if (isNegative)
      num = Math.abs(num);

    while (Math.abs((n / d) - num) >= error)
    {
      if ((n / d) > num)
        d++;
      if ((n / d) < num)
        n++;
    }

    if (isNegative)
      n = n * -1;
    return (n == d) ? "1" : (int) n + "/" + (int) d;
  }

  /**
   * The greatest common divisor.
   * 
   * @param a
   *          the first input
   * @param b
   *          the second input
   * @return the divisor
   */
  public int gcd(final int a, final int b)
  {
    int dividend;
    int divisor;
    int remainder;

    if (a > b)
    {
      dividend = a;
      divisor = b;
    }
    else
    {
      dividend = b;
      divisor = a;
    }
    remainder = dividend % divisor;

    while (remainder != 0)
    {
      dividend = divisor;
      divisor = remainder;
      remainder = dividend % divisor;
    }

    return divisor;
  }

  /**
   * The string to be shown.
   */
  public String toString()
  {
    return this.toString(false);
  }

  /**
   * The toString with a boolean, correct output for fraction.
   *
   * @return the string for the result
   */
  public String toString(final boolean isFraction)
  {
    String r = "";
    String i = "";
    if (getRealPart() != 0)
    {
      if (!isFraction)
      {
        r = (getRealPart() % 1 == 0) ? "" + (int) getRealPart() : "" + getRealPart();
      }
      else
      {
        r += makeFraction(getRealPart());
      }
    }

    String tempI = (isFraction) ? makeFraction(getImaginaryPart()) : "" + getImaginaryPart();
    if (getImaginaryPart() % 1 == 0)
    {
      tempI = "" + (int) getImaginaryPart();
    }
    i = tempI + iString;
    if (i.equals("1i"))
      i = iString;
    if (i.equals("-1i"))
      i = "-" + iString;

    // change
    String result = "";
    if (real == 0 && img != 0)
    {
      result = "" + i;
    }
    else if (getRealPart() == 0 && getImaginaryPart() == 0)
    {
      result = zero;
    }
    else if (getImaginaryPart() == 0)
    {
      result = "" + r;
    }
    else if (getImaginaryPart() < 0)
    {
      result = "" + r + i;
    }
    else
    {
      result = "" + r + "+" + i;
    }

    return result;

  }
}
